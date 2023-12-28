package customHandler;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Event;
import models.Event_data;
import models.ItemDetails;
import org.modelmapper.ModelMapper;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<SQSEvent, String> {


    @Override
    public String handleRequest(SQSEvent sqsEvent, Context context) {
        LambdaLogger logger = context.getLogger();
        ObjectMapper mapper = new ObjectMapper();
        for (SQSEvent.SQSMessage sqsMessage : sqsEvent.getRecords()) {
            logger.log("Received SQSMessage:- " + sqsMessage);
            Event event = null;
            try {
                event = mapper.readValue(sqsMessage.getBody(), Event.class);
                //DynamoDB Code
                ModelMapper modelMapper = new ModelMapper();

                event.getEvent_data().setItemId(event.getEvent_data().getId());
                event.getEvent_data().setEvent_name(event.getEvent_name());
                ItemDetails itemDetails = modelMapper.map(event.getEvent_data(),ItemDetails.class);
                insertToDb(itemDetails, logger);
                //SNS
                sendNotification("Event with Id: " + event.getEvent_data().getId() + " with eventName " + event.getEvent_name() + " processed Successfully");
                logger.log("SNS Notification sent successfully");
            } catch (JsonProcessingException e) {
                sendNotification("Exception occured");
                throw new RuntimeException(e);
            }
        }
        return "Process Completed Successfully";
    }

    private static void insertToDb(ItemDetails ItemDetails, LambdaLogger logger) {
        AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(dynamoDBClient);
        try {
            dynamoDBMapper.save(ItemDetails);
            System.out.println("Saved");
        } catch (Exception e) {
            logger.log("Error while Db Insertion " + e.getMessage());
        }
    }

    private static void sendNotification(String msg) {
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();
        PublishRequest publishRequest = new PublishRequest().withMessage(msg).withTopicArn("arn:aws:sns:ap-south-1:309305317617:todoist_events_topic");
        PublishResult publishResult = snsClient.publish(publishRequest);
    }
}