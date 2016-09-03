import com.mongodb.async.client.MongoDatabase;

import static spark.Spark.*;
/**
 * @author Akash
 *         Created on 01:05 04-09-2016
 */
public class ApiServer {
    private MongoDatabase database;

    public ApiServer(MongoDatabase database) {
        this.database = database;
    }

    public void setUpRoutes() {
        get("/hello", (req, res) -> "Hello World");
    }
}
