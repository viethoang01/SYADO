package com.example.project4.model;

import java.io.Serializable;

public class Comment implements Serializable {
    private String content;
    private String userID;
    private int like;

    public Comment() {
    }

    public Comment(String content, String userID, int like) {
        this.content = content;
        this.userID = userID;
        this.like = like;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                ", userID='" + userID + '\'' +
                ", like=" + like +
                '}';
    }
}
