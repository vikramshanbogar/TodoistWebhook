package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Event_data event_data;
    private String event_name;
    private Initiator initiator;
    private String user_id;
    private String version;
    private Event_data_extra event_data_extra;

}

