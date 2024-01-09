package com.vikram.Todoist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.vikram.Todoist.models.Event;
import com.vikram.Todoist.models.Task;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handler for requests to Lambda function.
 */

@RestController("/")
public class AppController {

    @PostMapping
    public String getCompletedRequest(@RequestBody String reqFromTodoistEvent) {
        ObjectMapper mapper = new ObjectMapper();
        Event event = null;
        try {
            event = mapper.readValue(reqFromTodoistEvent, Event.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Unirest.setTimeouts(0, 0);
        try {
            Task task = new Task();
            task.setContent(event.getEvent_data().content);

            HttpResponse<String> httpResponse = Unirest.post("https://api.todoist.com/rest/v2/tasks")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer 2c1c5d9a3196eaab20fc8643e2f93c56dba0fa19")
                    .header("Cookie",
                            "tduser=v4.public.eyJ1c2VyX2lkIjogNDU5MjUwMTAsICJleHAiOiAiMjAyNC0wMS0yM1QwNDowODowOSswMDowMCJ9-Mr_wMugAniYeW0wTPC_q-2Kf0CvJe2yR8qIoe1O_BmHf4LhmZ0XSv1gMdRRr_jEH8qGEKf_At2g4DzR0FdZDA; csrf=20b947f2e3f64ec4b20fe0a3bbc55a9c")
                    .body(mapper.writeValueAsString(task))
                    .asString();
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return "Success";
    }
}
