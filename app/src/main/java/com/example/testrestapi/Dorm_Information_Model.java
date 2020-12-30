package com.example.testrestapi;

public class Dorm_Information_Model {
    public String name;
    public String address;
    public String image;
    public int price;
    public String distance;
    public String[] detail;
    public int[] facility;
    public Faculty faculty;
    public double distanceDouble;

    public Dorm_Information_Model(){
        this.name = "";
        this.address = "";
        image = "";
        price = 0;
        distance = "";
        detail = null;
        facility = null;
        faculty = null;
        this.distanceDouble = 0;
    }

    public Dorm_Information_Model(String name, String address, String image, int price,String distance, String[] detail, int[] facility) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.price = price;
        this.distance = distance;
        this.detail = detail;
        this.facility = facility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String[] getDetail() {
        return detail;
    }

    public void setDetail(String[] detail) {
        this.detail = detail;
    }

    public int[] getFacility() {
        return facility;
    }

    public void setFacility(int[] facility) {
        this.facility = facility;
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
