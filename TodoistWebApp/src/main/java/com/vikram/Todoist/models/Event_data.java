package com.vikram.Todoist.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Event_data {

 private String itemId;

 private String event_name;


 private String added_at;

 private String added_by_uid;

 private String assigned_by_uid;

 private boolean checked;

 private float child_order;

 private boolean collapsed;

 private String completed_at;

 public String content;

 private String description;
 @JsonIgnore
 private Due due;


 private String duration;


 private String id;
 @JsonProperty("is_deleted")
 private boolean is_deleted;


 @JsonProperty("in_history")
 private boolean in_history;

 String[] labels;

 private String parent_id;

 private float priority;

 private String project_id;

 private String responsible_uid;

 private String section_id;

 private String sync_id;

 private String updated_at;

 private String url;

 private String user_id;

 private String v2_id;

 private String v2_parent_id;

 private String v2_project_id;

 private String v2_section_id;
 private String date_added;
 private String date_completed;

}
