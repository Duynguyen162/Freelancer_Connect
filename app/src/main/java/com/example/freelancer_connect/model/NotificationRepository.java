package com.example.freelancer_connect.model;
import java.util.ArrayList;
import java.util.List;

public class NotificationRepository {
    private static final List<Notification> notifications = new ArrayList<>();

    public static void addNotification(Notification n) {
        notifications.add(n);
    }

    public static List<Notification> getNotifications() {
        return notifications;
    }
}
