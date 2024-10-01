package in.roopsai;

import java.util.Date;

public class Task {

    private static int count = 0;

    private int id;
    private String status;
    private String title;
    private Date createdAt;
    private Date updatedAt;

    public Task(String title) {
        this.title = title;
        count += 1;
        id = count;
        status = "todo";
        createdAt = new Date();
        
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String toJson() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"id\": ").append(id).append(",");
        json.append("\"status\": ").append(status).append(",");
        json.append("\"title\": ").append(title).append(",");
        json.append("\"createdAt\": ").append(createdAt).append(",");
        json.append("\"updatedAt\": ").append(updatedAt);
        json.append("}");
        return json.toString();

    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return toJson();
    }





}
