package com.sample.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/testdata")
public class TestService {

    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public String genJson() {
        //Generating JSON Test String
        return "{\"quiz\": { \"sport\": { \"q1\": { \"question\": \"Which one is correct team name in NBA?\", \"options\": [\"New York Bulls\", \"Los Angeles Kings\", \"Golden State Warriros\", \"Huston Rocket\"],\"answer\": \"Huston Rocket\" } }, \"maths\": {\"q1\": {\"question\": \"5 + 7 = ?\",\"options\": [\"10\", \"11\", \"12\", \"13\"],\"answer\": \"12\" },\"q2\": {\"question\": \"12 - 8 = ?\",\"options\": [\"1\", \"2\", \"3\", \"4\"],\"answer\": \"4\"}}}}";
    }

}