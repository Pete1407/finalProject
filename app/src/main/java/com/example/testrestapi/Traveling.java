package com.example.testrestapi;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Traveling implements Serializable {
    public String name_travel;
    public boolean active;


    public Traveling(String travel){
        this.name_travel = travel;
        this.active = true;
    }

    public Traveling(String travel , boolean ac) {
        this.name_travel = travel;
        this.active = ac;
    }

    public String getName_travel() {
        return name_travel;
    }

    public void setName_travel(String name_travel) {
        this.name_travel = name_travel;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name_travel;
    }
}
