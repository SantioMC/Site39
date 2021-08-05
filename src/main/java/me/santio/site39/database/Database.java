package me.santio.site39.database;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import me.santio.site39.Site39;

public class Database {
    @Getter private static MongoClient mongoClient;
    @Getter private static MongoDatabase mongoDatabase;

    public static void connect() {
        String uri = Site39.getInstance().getConfig().getString("mongoConnectionString");
        if (uri == null) return;
        ConnectionString connectionString = new ConnectionString(uri);
        mongoClient = MongoClients.create(connectionString);
        mongoDatabase = mongoClient.getDatabase("users");
    }
}
