package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Initiator {
 private String email;
 private String full_name;
 private String id;
 private String image_id;
 @JsonProperty("is_premium")
 private boolean is_premium;

}