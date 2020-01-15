package com.sample.service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

@Path("/store")
public class StoreService {

    @GET
    public Response storeData() throws URISyntaxException {
        //inline will store the JSON data streamed in string format
        String inline = "";
        try {
            URL url = new URL("http://localhost:8080/Sample_war/testdata");
            //Parse URL into HttpURLConnection in order to open the connection in order to get the JSON data
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            //Get the response status of the Rest API
            int responsecode = conn.getResponseCode();
            System.out.println("Response code is: " + responsecode);
            //Iterating condition to if response code is not 200 then throw a runtime exception
            //else continue the actual process of getting the JSON data
            if (responsecode != 200)
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            else {
                //Scanner functionality will read the JSON data from the stream
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    inline += sc.nextLine();
                }
                System.out.println("\nJSON Response in String format");
                System.out.println(inline);
                //Close the stream when reading the data has been finished
                sc.close();
            }

            //Connecting to Atlas MongoDB
            MongoClientURI uri = new MongoClientURI("mongodb+srv://amolsr:root@test-5xceh.mongodb.net/test?retryWrites=true&w=majority");
            MongoClient mongoClient = new MongoClient(uri);
            MongoDatabase database = mongoClient.getDatabase("test");
            System.out.println("Connected to the database successfully");
            Document myDoc = Document.parse(inline);
            MongoCollection<Document> collection = database.getCollection("quiz");

            //Saving the Data into mongoDB
            collection.insertOne(myDoc);
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Redirecting for the display
        URI location = new URI("./display");
        return Response.temporaryRedirect(location).build();
    }
}
