package com.example.freelancer_connect.model;

import com.google.firebase.Timestamp;

public class Notification {
    private String documentId;
    private String title;
    private String content;
    private String receiver;
    private Timestamp timestamp;

    public Notification() {
        // Bắt buộc cho Firestore
    }

    public String getDocumentId() { return documentId; }
    public void setDocumentId(String documentId) { this.documentId = documentId; }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getReceiver() { return receiver; }
    public Timestamp getTimestamp() { return timestamp; }
}
