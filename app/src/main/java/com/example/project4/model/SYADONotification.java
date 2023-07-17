package com.example.project4.model;

public class SYADONotification {
    private int id;
    private String user_id,content,date,from;

    public SYADONotification() {
    }

    public SYADONotification(int id, String user_id, String content, String date, String from) {
        this.id = id;
        this.user_id = user_id;
        this.content = content;
        this.date = date;
        this.from = from;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "SYADONotification{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
