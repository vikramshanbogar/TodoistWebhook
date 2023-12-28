package models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event_data {
    @DynamoDBHashKey
    @DynamoDBAttribute
    private String itemId;
    @DynamoDBRangeKey
    @DynamoDBAttribute
    private String event_name;

    @DynamoDBAttribute
    private String added_at;
    @DynamoDBAttribute
    private String added_by_uid;
    @DynamoDBAttribute
    private String assigned_by_uid;
    @DynamoDBAttribute
    private boolean checked;
    @DynamoDBAttribute
    private float child_order;
    @DynamoDBAttribute
    private boolean collapsed;
    @DynamoDBAttribute
    private String completed_at;
    @DynamoDBAttribute
    private String content;
    @DynamoDBAttribute
    private String description;
    @JsonIgnore
    @DynamoDBAttribute
    private Due due;

    @DynamoDBAttribute
    private String duration;

    @DynamoDBAttribute
    private String id;
    @JsonProperty("is_deleted")
    @DynamoDBAttribute
    private boolean is_deleted;
    String[] labels;
    @DynamoDBAttribute
    private String parent_id;
    @DynamoDBAttribute
    private float priority;
    @DynamoDBAttribute
    private String project_id;
    @DynamoDBAttribute
    private String responsible_uid;
    @DynamoDBAttribute
    private String section_id;
    @DynamoDBAttribute
    private String sync_id;
    @DynamoDBAttribute
    private String updated_at;
    @DynamoDBAttribute
    private String url;
    @DynamoDBAttribute
    private String user_id;
    @DynamoDBAttribute
    private String v2_id;
    @DynamoDBAttribute
    private String v2_parent_id;
    @DynamoDBAttribute
    private String v2_project_id;
    @DynamoDBAttribute
    private String v2_section_id;

}
