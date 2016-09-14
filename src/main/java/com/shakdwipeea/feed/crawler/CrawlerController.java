package com.shakdwipeea.feed.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import java.util.ArrayList;
import java.util.concurrent.Executors;

/**
 * @author Akash
 *         Created on 23:26 01-09-2016
 */
public class CrawlerController {

    /**
     *
     * @param name unique name for crawler, this will be directory in which crawl data is stored
     * @param seeds The seeds for crawling
     */
    static void newCrawler(String name, ArrayList<String> seeds) {
        String crawlStorageFolder = "./data/crawl/" + name;

        // 4 concurrent threads for each crawler
        int numberOfCrawlers = 4;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setResumableCrawling(true);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

        CrawlController controller;
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        seeds.forEach(controller::addSeed);

        Executors.newSingleThreadExecutor()
                .submit(() -> controller.start(PostCrawler.class, numberOfCrawlers));
    }
}
