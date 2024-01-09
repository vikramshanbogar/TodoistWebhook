package com.awsexplore.AwsServices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.awsexplore.AwsServices.models.Codebeautify;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@RestController
public class webhookController {

    @PostMapping
    public ResponseEntity<String> print(@RequestBody String requestBody)
            throws JsonMappingException, JsonProcessingException {
        System.out.println("###### Webhook Data Received from Todoist ##### \n" + requestBody);

        ObjectMapper mapper = new ObjectMapper();
        Codebeautify code = mapper.readValue(requestBody, Codebeautify.class);
        System.out.println(code.getUser_id());

        // pushing data to SQS for further processing
        String body = "{\"content\":\"" + code.getEvent_data().getContent() + "\"}";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.todoist.com/rest/v2/tasks"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer 2c1c5d9a3196eaab20fc8643e2f93c56dba0fa19")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .headers()
                .build();
        return new ResponseEntity<String>(requestBody, HttpStatus.OK);
    }
}
