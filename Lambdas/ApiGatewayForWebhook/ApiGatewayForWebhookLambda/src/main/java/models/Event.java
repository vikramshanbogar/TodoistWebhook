package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Event {
@JsonIgnore
Event_data Event_dataObject;
 private String event_name;
 Initiator InitiatorObject;
 private String user_id;
 private String version;
@JsonIgnore
 Event_data_extra Event_data_extraObject;
 // Getter Methods 

 public Event_data getEvent_data() {
  return Event_dataObject;
 }

 public String getEvent_name() {
  return event_name;
 }

 public Initiator getInitiator() {
  return InitiatorObject;
 }

 public String getUser_id() {
  return user_id;
 }

 public String getVersion() {
  return version;
 }

 // Setter Methods 

 public void setEvent_data(Event_data event_dataObject) {
  this.Event_dataObject = event_dataObject;
 }

 public void setEvent_name(String event_name) {
  this.event_name = event_name;
 }

 public void setInitiator(Initiator initiatorObject) {
  this.InitiatorObject = initiatorObject;
 }

 public void setUser_id(String user_id) {
  this.user_id = user_id;
 }

 public void setVersion(String version) {
  this.version = version;
 }
}

