package model;

/**
 * @author Akash
 *         Created on 01:00 04-09-2016
 */
public class User {
    private int id;
    private String username;
    private String[] links;

    public User(int id, String username, String[] links) {
        this.id = id;
        this.username = username;
        this.links = links;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getLinks() {
        return links;
    }

    public void setLinks(String[] links) {
        this.links = links;
    }
}
