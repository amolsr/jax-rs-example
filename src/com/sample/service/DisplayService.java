package com.sample.service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/display")
public class DisplayService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String storeData() {
        String inline = "";

        //MongoDB connection URL
        MongoClientURI uri = new MongoClientURI("mongodb+srv://amolsr:root@test-5xceh.mongodb.net/test?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("quiz");

        //Getting Database Entries in String
        FindIterable<Document> findIterable = collection.find(new Document());
        for (Document d : findIterable) {
            inline += d.toString();
        }
        return inline;
    }
}
