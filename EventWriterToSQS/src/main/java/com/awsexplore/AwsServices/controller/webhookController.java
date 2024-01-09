package com.awsexplore.AwsServices.controller;


import java.io.IOException;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.awsexplore.AwsServices.models.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
public class webhookController {

    @PostMapping
    public ResponseEntity<String> print(@RequestBody String requestBody)
            throws IOException, InterruptedException {
        System.out.println("###### Webhook Data Received from Todoist ##### \n" + requestBody);

        ObjectMapper mapper = new ObjectMapper();
        Event code = mapper.readValue(requestBody, Event.class);
        System.out.println(code.getUser_id());

        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<String> response = Unirest.post("https://api.todoist.com/rest/v2/tasks")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer 2c1c5d9a3196eaab20fc8643e2f93c56dba0fa19")
                    .header("Cookie",
                            "tduser=v4.public.eyJ1c2VyX2lkIjogNDU5MjUwMTAsICJleHAiOiAiMjAyNC0wMS0yM1QwNDowODowOSswMDowMCJ9-Mr_wMugAniYeW0wTPC_q-2Kf0CvJe2yR8qIoe1O_BmHf4LhmZ0XSv1gMdRRr_jEH8qGEKf_At2g4DzR0FdZDA; csrf=20b947f2e3f64ec4b20fe0a3bbc55a9c")
                    .body("{\r\n    \"content\": \"VIkram Jan\",\r\n    \"labels\": [\"09-01-2023\"],\r\n    \"project_id\":2324810916\r\n}")
                    .asString();
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<String>(requestBody, HttpStatusCode.valueOf(200));
    }
}
