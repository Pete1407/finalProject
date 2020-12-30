package com.example.testrestapi;

public class Dorm_Quality_Model {

    public String name;
    public final String CERTIFY = "ได้รับการรับรองจากหน่วยรัฐ";
    public String distance;
    public int[] premieum;
    public int price;
    public String image;
    public Faculty faculty;
    public double distanceDouble;

    public Dorm_Quality_Model(){
        this.name = "";
        this.distance = "";
        this.premieum = null;
        this.price = 0;
        this.image = "";
        this.faculty = null;
        this.distanceDouble = 0;

    }

    public Dorm_Quality_Model(String name, String distance, int[] premieum,int p) {
        this.name = name;
        this.distance = distance;
        this.premieum = premieum;
        this.price = p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertify() {
        return CERTIFY;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int[] getPremieum() {
        return premieum;
    }

    public void setPremieum(int[] premieum) {
        this.premieum = premieum;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getCERTIFY() {
        return CERTIFY;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
