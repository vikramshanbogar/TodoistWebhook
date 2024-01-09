package com.vikram.Todoist.models;

public class Due {
 private String date;
 private boolean is_recurring;
 private String lang;
 private String string;
 private String timezone = null;


 // Getter Methods 

 public String getDate() {
  return date;
 }

 public boolean getIs_recurring() {
  return is_recurring;
 }

 public String getLang() {
  return lang;
 }

 public String getString() {
  return string;
 }

 public String getTimezone() {
  return timezone;
 }

 // Setter Methods 

 public void setDate(String date) {
  this.date = date;
 }

 public void setIs_recurring(boolean is_recurring) {
  this.is_recurring = is_recurring;
 }

 public void setLang(String lang) {
  this.lang = lang;
 }

 public void setString(String string) {
  this.string = string;
 }

 public void setTimezone(String timezone) {
  this.timezone = timezone;
 }
}