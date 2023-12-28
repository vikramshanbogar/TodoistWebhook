package models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("is_recurring")
    private boolean is_recurring;
    @DynamoDBAttribute
    private String lang;
    @DynamoDBAttribute
    private String string;
    @DynamoDBAttribute
    private String timezone = null;


}