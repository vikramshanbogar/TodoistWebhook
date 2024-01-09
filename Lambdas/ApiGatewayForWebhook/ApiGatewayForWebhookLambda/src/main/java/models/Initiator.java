package models;

public class Initiator {
 private String email;
 private String full_name;
 private String id;
 private String image_id;
 private boolean is_premium;


 // Getter Methods 

 public String getEmail() {
  return email;
 }

 public String getFull_name() {
  return full_name;
 }

 public String getId() {
  return id;
 }

 public String getImage_id() {
  return image_id;
 }

 public boolean getIs_premium() {
  return is_premium;
 }

 // Setter Methods 

 public void setEmail(String email) {
  this.email = email;
 }

 public void setFull_name(String full_name) {
  this.full_name = full_name;
 }

 public void setId(String id) {
  this.id = id;
 }

 public void setImage_id(String image_id) {
  this.image_id = image_id;
 }

 public void setIs_premium(boolean is_premium) {
  this.is_premium = is_premium;
 }
}