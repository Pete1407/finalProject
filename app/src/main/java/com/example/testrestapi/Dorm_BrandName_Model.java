package com.example.testrestapi;

public class Dorm_BrandName_Model  {

    public String logo;
    public String name;
    public String distance;
    public String[] premieum;
    public int price;
    public String address;
    public Faculty faculty;
    public double distanceDouble;

    public Dorm_BrandName_Model(){
        this.logo = "";
        this.name = "";
        this.distance = "";
        this.premieum = null;
        this.price = 0;
        this.address = "";
        faculty = null;
        this.distanceDouble = 0;
    }

    public Dorm_BrandName_Model(String logo, String name, String distance,String add, String[] premieum, int price) {
        this.logo = logo;
        this.name = name;
        this.distance = distance;
        this.address = add;
        this.premieum = premieum;
        this.price = price;
    }

    public String getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

    public String getDistance() {
        return distance;
    }

    public String[] getPremieum() {
        return premieum;
    }

    public int getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setPremieum(String[] premieum) {
        this.premieum = premieum;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public double getDistanceDouble() {
        return distanceDouble;
    }

    public void setDistanceDouble(double distanceDouble) {
        this.distanceDouble = distanceDouble;
    }
}
