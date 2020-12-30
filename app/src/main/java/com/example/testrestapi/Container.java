package com.example.testrestapi;

public class Container {
    public String dormID;
    public String username;

    public Container(String dormID, String username) {
        this.dormID = dormID;
        this.username = username;
    }

    public String getDormID() {
        return dormID;
    }

    public String getUsername() {
        return username;
    }
}
