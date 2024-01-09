package models;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Task {

    String content;

    public Task(String content) {
        this.content = content;
    }

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
