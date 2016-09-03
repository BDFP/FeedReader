import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoDatabase;

/**
 * Created by Akash  on 01-09-2016.
 */
public class Application {
    public static final String MONGO_DB_NAME = "feed";

    public static void main(String[] args) {
        System.out.println("Starting Application. Please Wait..");

        //Connect to the database
        MongoDatabase database = getMongo();

        //Start the crawler
        try {
            CrawlerController.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // start API server
        ApiServer api = new ApiServer(database);
        api.setUpRoutes();
    }

    public static MongoDatabase getMongo() {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase(MONGO_DB_NAME);
        return database;
    }

}
