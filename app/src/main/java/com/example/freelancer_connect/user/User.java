package com.example.freelancer_connect.user;

public class User {
    private int userImage;
    private String userName;
    private String userLoginName;
    private String userID;
    private String userEmail;
    private String userPhone;
    private String userDOB;
    private String userGender;

    public User(int userImage, String userName, String userLoginName, String userID, String userEmail, String userPhone, String userDOB, String userGender) {
        this.userImage = userImage;
        this.userName = userName;
        this.userLoginName = userLoginName;
        this.userID = userID;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userDOB = userDOB;
        this.userGender = userGender;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(String userDOB) {
        this.userDOB = userDOB;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }
}
