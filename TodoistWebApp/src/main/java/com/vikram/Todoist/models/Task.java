package com.vikram.Todoist.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    String content;


    @Override
    public String toString() {
        return "Task{" +
                "content='" + content + '\'' +
                ", labels=" + Arrays.toString(labels) +
                ", project_id='" + project_id + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    String labels[] = {new SimpleDateFormat("dd-MMM-yyyy").format(new Date())};
    String project_id = "2324810916";

}
