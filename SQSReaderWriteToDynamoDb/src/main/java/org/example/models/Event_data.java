package org.example.models;

public class Event_data {
 private String added_at;
 private String added_by_uid;
 private String assigned_by_uid;
 private boolean checked;
 private float child_order;
 private boolean collapsed;
 private String completed_at;
 private String content;
 private String description;
 private Due dueObj;
 private String duration;

 private String id;
 private boolean is_deleted;
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


 // Getter Methods 

 public String getAdded_at() {
  return added_at;
 }

 public String getAdded_by_uid() {
  return added_by_uid;
 }

 public String getAssigned_by_uid() {
  return assigned_by_uid;
 }

 public boolean getChecked() {
  return checked;
 }

 public float getChild_order() {
  return child_order;
 }

 public boolean getCollapsed() {
  return collapsed;
 }

 public String getCompleted_at() {
  return completed_at;
 }

 public String getContent() {
  return content;
 }

 public String getDescription() {
  return description;
 }

 public Due getDueObj() {
  return dueObj;
 }

 public String getDuration() {
  return duration;
 }

 public String getId() {
  return id;
 }

 public boolean getIs_deleted() {
  return is_deleted;
 }

 public String getParent_id() {
  return parent_id;
 }

 public float getPriority() {
  return priority;
 }

 public String getProject_id() {
  return project_id;
 }

 public String getResponsible_uid() {
  return responsible_uid;
 }

 public String getSection_id() {
  return section_id;
 }

 public String getSync_id() {
  return sync_id;
 }

 public String getUpdated_at() {
  return updated_at;
 }

 public String getUrl() {
  return url;
 }

 public String getUser_id() {
  return user_id;
 }

 public String getV2_id() {
  return v2_id;
 }

 public String getV2_parent_id() {
  return v2_parent_id;
 }

 public String getV2_project_id() {
  return v2_project_id;
 }

 public String getV2_section_id() {
  return v2_section_id;
 }

 // Setter Methods 

 public void setAdded_at(String added_at) {
  this.added_at = added_at;
 }

 public void setAdded_by_uid(String added_by_uid) {
  this.added_by_uid = added_by_uid;
 }

 public void setAssigned_by_uid(String assigned_by_uid) {
  this.assigned_by_uid = assigned_by_uid;
 }

 public void setChecked(boolean checked) {
  this.checked = checked;
 }

 public void setChild_order(float child_order) {
  this.child_order = child_order;
 }

 public void setCollapsed(boolean collapsed) {
  this.collapsed = collapsed;
 }

 public void setCompleted_at(String completed_at) {
  this.completed_at = completed_at;
 }

 public void setContent(String content) {
  this.content = content;
 }

 public void setDescription(String description) {
  this.description = description;
 }

 public void setDue(Due due) {
  this.dueObj = due;
 }

 public void setDuration(String duration) {
  this.duration = duration;
 }

 public void setId(String id) {
  this.id = id;
 }

 public void setIs_deleted(boolean is_deleted) {
  this.is_deleted = is_deleted;
 }

 public void setParent_id(String parent_id) {
  this.parent_id = parent_id;
 }

 public void setPriority(float priority) {
  this.priority = priority;
 }

 public void setProject_id(String project_id) {
  this.project_id = project_id;
 }

 public void setResponsible_uid(String responsible_uid) {
  this.responsible_uid = responsible_uid;
 }

 public void setSection_id(String section_id) {
  this.section_id = section_id;
 }

 public void setSync_id(String sync_id) {
  this.sync_id = sync_id;
 }

 public void setUpdated_at(String updated_at) {
  this.updated_at = updated_at;
 }

 public void setUrl(String url) {
  this.url = url;
 }

 public void setUser_id(String user_id) {
  this.user_id = user_id;
 }

 public void setV2_id(String v2_id) {
  this.v2_id = v2_id;
 }

 public void setV2_parent_id(String v2_parent_id) {
  this.v2_parent_id = v2_parent_id;
 }

 public void setV2_project_id(String v2_project_id) {
  this.v2_project_id = v2_project_id;
 }

 public void setV2_section_id(String v2_section_id) {
  this.v2_section_id = v2_section_id;
 }

public String[] getLabels() {
    return labels;
}

public void setLabels(String[] labels) {
    this.labels = labels;
}

 
}
