package org.example;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Event;
import org.example.models.ItemDetails;
import org.modelmapper.ModelMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MainHandler {

    public static void main(String[] args) {
        String queueUrl = "https://sqs.ap-south-1.amazonaws.com/309305317617/TodoistSqs";
        AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();
        ObjectMapper mapper = new ObjectMapper();
        List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
        for (Message m : messages) {
            System.out.println(m);

            Event code = null;
            try {
                code = mapper.readValue(m.getBody(), Event.class);

                {//DynamoDB Code

                    ModelMapper modelMapper = new ModelMapper();
                    ItemDetails itemDetails = modelMapper.map(code.getEvent_data(), ItemDetails.class);
                    itemDetails.setEvent_name("item:added");
                    itemDetails.setItemId(code.getEvent_data()!=null?code.getEvent_data().getId(): UUID.randomUUID().toString());
                    AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();
                    DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(dynamoDBClient);
                    try {
                        dynamoDBMapper.save(itemDetails);
                        sqs.deleteMessage(queueUrl,m.getReceiptHandle());
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}