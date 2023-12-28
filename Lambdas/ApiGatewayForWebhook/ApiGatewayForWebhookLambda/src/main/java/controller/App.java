package controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        // pushing data to SQS for further processing
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        LambdaLogger logger = context.getLogger();
        logger.log("Received the Message from Gateway"+input);
        try {
            AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
            SendMessageRequest send_msg_request = new SendMessageRequest()
                    .withQueueUrl("https://sqs.ap-south-1.amazonaws.com/309305317617/TodoistSqs")
                    .withMessageBody(input.getBody())
                    .withDelaySeconds(5);
            sqs.sendMessage(send_msg_request);
            logger.log("Processed the Message from Gateway " + input.getBody());
            return response
                    .withStatusCode(200)
                    .withBody(input.getBody());

        } catch (Exception e) {
            logger.log("Failed to Process the Message from Gateway " + input.getBody()+"\n\n"+ e.getMessage());
            return response
                    .withBody(e.getMessage())
                    .withStatusCode(500);
        }
    }
}
