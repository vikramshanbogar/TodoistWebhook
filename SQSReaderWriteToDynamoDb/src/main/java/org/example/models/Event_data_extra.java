package org.example.models;

public class Event_data_extra {
 Old_item Old_itemObject;
 private String update_intent;


 // Getter Methods 

 public Old_item getOld_item() {
  return Old_itemObject;
 }

 public String getUpdate_intent() {
  return update_intent;
 }

 // Setter Methods 

 public void setOld_item(Old_item old_itemObject) {
  this.Old_itemObject = old_itemObject;
 }

 public void setUpdate_intent(String update_intent) {
  this.update_intent = update_intent;
 }
}