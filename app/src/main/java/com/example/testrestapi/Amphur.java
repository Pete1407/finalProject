package com.example.testrestapi;

public class Amphur {

    public String name;
    public int id;

    public Amphur(String n,int id){
        this.name = n;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
