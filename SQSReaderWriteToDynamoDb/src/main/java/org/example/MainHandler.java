package org.example;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Event;
import org.example.models.ItemDetails;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.UUID;

public class MainHandler {

    public static void main(String[] args) {
        String queueUrl = "https://sqs.ap-south-1.amazonaws.com/309305317617/TodoistSqs";
        AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();

        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
        receiveMessageRequest.withMaxNumberOfMessages(10);
        ObjectMapper mapper = new ObjectMapper();
        List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();

        for (Message m : messages) {
            System.out.println(m);
            Event event = null;
            try {
                event = mapper.readValue(m.getBody(), Event.class);
                //DynamoDB Code
                ModelMapper modelMapper = new ModelMapper();
                ItemDetails itemDetails = modelMapper.map(event.getEvent_data(), ItemDetails.class);
                itemDetails.setEvent_name("item:added");
                itemDetails.setItemId(event.getEvent_data() != null ? event.getEvent_data().getId() : UUID.randomUUID().toString());
                insertToDb(itemDetails);
                //SQS DELETE
                //sqs.deleteMessage(queueUrl, m.getReceiptHandle());
                //SNS
                sendNotification("Event with Id: " + itemDetails.getItemId() + " with eventName " + itemDetails.getEvent_name() + " processed Successfully");
                System.out.println("SNS Notification sent successfully");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private static void insertToDb(ItemDetails itemDetails) {
        AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(dynamoDBClient);
        try {
            dynamoDBMapper.save(itemDetails);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void sendNotification(String msg) {
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();
        PublishRequest publishRequest = new PublishRequest().withMessage(msg).withTopicArn("arn:aws:sns:ap-south-1:309305317617:todoist_events_topic");
        PublishResult publishResult = snsClient.publish(publishRequest);
    }
}