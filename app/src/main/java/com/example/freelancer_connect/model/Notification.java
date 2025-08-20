package com.example.freelancer_connect.model;
public class Notification {
    private String title;
    private String content;
    private String receiver; // người nhận

    public Notification(String title, String content, String receiver) {
        this.title = title;
        this.content = content;
        this.receiver = receiver;
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getReceiver() { return receiver; }
}
