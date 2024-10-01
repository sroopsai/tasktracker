package in.roopsai;

import java.util.Date;

public class Task {

    private static final String[] STATUSES = {"todo", "in-progress", "done"};
    private static int count = 0;
    private int id;
    private int status;
    private String title;
    private Date createdAt;
    private Date updatedAt;

    public Task(String title) {
        this.title = title;
        count += 1;
        id = count;
        status = 0;

        createdAt = new Date();
    }

    public String toJson() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"id\": ").append(id).append(",");
        json.append("\"status\": ").append(status).append(",");
        json.append("\"title\": ").append(title).append(",");
        json.append("\"createdAt\": ").append(createdAt).append(",");
        json.append("}");
        return json.toString();

    }





}
