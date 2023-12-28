package models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "TodoistEvents")
public class ItemDetails {
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
    private Integer child_order;
    @DynamoDBAttribute
    private boolean collapsed;
    @DynamoDBAttribute
    private String completed_at;
    @DynamoDBAttribute
    private String content;
    @DynamoDBAttribute
    private String description;
    @DynamoDBAttribute
    private Due dueObj;
    @DynamoDBAttribute
    private String duration;

    @DynamoDBAttribute
    @JsonProperty("is_deleted")
    private boolean is_deleted;
//    @DynamoDBAttribute
//    private String[] labels;
    @DynamoDBAttribute
    private String parent_id;
    @DynamoDBAttribute
    private Integer priority;
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
