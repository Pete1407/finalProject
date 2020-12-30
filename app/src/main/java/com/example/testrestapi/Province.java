package com.example.testrestapi;

public class Province {

    public String name;
    public int id;

    public Province(String nameProvince, int id){
            this.name = nameProvince;
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
