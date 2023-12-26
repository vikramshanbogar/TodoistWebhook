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

        {
            AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
            SendMessageRequest send_msg_request = new SendMessageRequest()
                    .withQueueUrl("https://sqs.ap-south-1.amazonaws.com/309305317617/TodoistSqs")
                    .withMessageBody(requestBody)
                    .withDelaySeconds(5);
            sqs.sendMessage(send_msg_request);
        }
        return new ResponseEntity<String>(requestBody, HttpStatus.OK);
    }
}
