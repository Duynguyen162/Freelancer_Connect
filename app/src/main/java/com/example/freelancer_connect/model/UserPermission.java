package com.example.freelancer_connect.model;

import java.util.List;

public class UserPermission {
    private String name;
    private String role;
    private boolean canManageAccount;
    private boolean canManageNotification;
    private boolean canAuthenticate;
    private boolean canStatistic;
    private boolean canManageService;

    public UserPermission(String name, String role,
                          boolean canManageAccount,
                          boolean canManageNotification,
                          boolean canAuthenticate,
                          boolean canStatistic,
                          boolean canManageService) {
        this.name = name;
        this.role = role;
        this.canManageAccount = canManageAccount;
        this.canManageNotification = canManageNotification;
        this.canAuthenticate = canAuthenticate;
        this.canStatistic = canStatistic;
        this.canManageService = canManageService;
    }

    // getter
    public String getName() { return name; }
    public String getRole() { return role; }
    public boolean isCanManageAccount() { return canManageAccount; }
    public boolean isCanManageNotification() { return canManageNotification; }
    public boolean isCanAuthenticate() { return canAuthenticate; }
    public boolean isCanStatistic() { return canStatistic; }
    public boolean isCanManageService() { return canManageService; }
}
