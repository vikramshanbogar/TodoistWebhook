package org.example.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class Due {
    @DynamoDBAttribute
    private String date;
    @DynamoDBAttribute
    private boolean is_recurring;
    @DynamoDBAttribute
    private String lang;
    @DynamoDBAttribute
    private String string;
    @DynamoDBAttribute
    private String timezone = null;


}