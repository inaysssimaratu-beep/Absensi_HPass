package com.mycompany.h.pass.objects;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoManager {
    private static MongoClient mongoClient;
    private static final String DATABASE_NAME = "h_pass_db";

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            // Koneksi ke MongoDB Lokal
            mongoClient = MongoClients.create("mongodb://localhost:27017");
        }
        return mongoClient.getDatabase(DATABASE_NAME);
    }
}