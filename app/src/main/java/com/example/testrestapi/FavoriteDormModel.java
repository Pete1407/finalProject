package com.example.testrestapi;

public class FavoriteDormModel {

    public String coverimage;
    public String dormname;
    public String distance;
    public double distanceDouble;
    public String address;
    public int electroprice;
    public int waterprice;
    public int commonfee;
    public int depositprice;
    public int roomprice;

    public FavoriteDormModel() {
        this.coverimage = "";
        this.dormname = "";
        this.distance = "";
        this.distanceDouble = 0;
        this.address = "";
        this.electroprice = 0;
        this.waterprice = 0;
        this.commonfee = 0;
        this.depositprice = 0;
        this.roomprice = 0;
    }

    public FavoriteDormModel(String dormname, String distance, double distanceDouble, String address, int electroprice, int waterprice, int commonfee, int depositprice, int roomprice) {
        this.dormname = dormname;
        this.distance = distance;
        this.distanceDouble = distanceDouble;
        this.address = address;
        this.electroprice = electroprice;
        this.waterprice = waterprice;
        this.commonfee = commonfee;
        this.depositprice = depositprice;
        this.roomprice = roomprice;
    }

    public String getDormname() {
        return dormname;
    }

    public void setDormname(String dormname) {
        this.dormname = dormname;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public double getDistanceDouble() {
        return distanceDouble;
    }

    public void setDistanceDouble(double distanceDouble) {
        this.distanceDouble = distanceDouble;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getElectroprice() {
        return electroprice;
    }

    public void setElectroprice(int electroprice) {
        this.electroprice = electroprice;
    }

    public int getWaterprice() {
        return waterprice;
    }

    public void setWaterprice(int waterprice) {
        this.waterprice = waterprice;
    }

    public int getCommonfee() {
        return commonfee;
    }

    public void setCommonfee(int commonfee) {
        this.commonfee = commonfee;
    }

    public int getDepositprice() {
        return depositprice;
    }

    public void setDepositprice(int depositprice) {
        this.depositprice = depositprice;
    }

    public int getRoomprice() {
        return roomprice;
    }

    public void setRoomprice(int roomprice) {
        this.roomprice = roomprice;
    }

    public String getCoverimage() {
        return coverimage;
    }

    public void setCoverimage(String coverimage) {
        this.coverimage = coverimage;
    }
}
