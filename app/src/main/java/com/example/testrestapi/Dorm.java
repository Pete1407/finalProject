package com.example.testrestapi;

import java.util.ArrayList;

public class Dorm {


    public String dormID;
    public String dormOwnerName;
    public String address;
    public double latitude;
    public double longtitude;
    public InfoDorm info;


    /*
    public String firstname;
    public String lastname;
    public String contact;
    public String email;
    */

    // Facility
    public ArrayList<Facility> faclist;

   /*
    public int minPrice;
    public int maxPrice;
    public String typeWater;
    public int priceWater;
    public String typeElectro;
    public int priceElectro;
    public String typeDeposit;
    public int depositPrice;
    public String typeCommonFee;
    public int commonFeePrice;
    */

    // Image
    public String coverImage;
    public String image360;

    public Dorm(String dormID, String dormOwnerName, String address, double latitude, double longtitude) {
        this.dormID = dormID;
        this.dormOwnerName = dormOwnerName;
        this.address = address;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public String getDormID() {
        return dormID;
    }

    public String getDormOwnerName() {
        return dormOwnerName;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public InfoDorm getInfo() {
        return info;
    }

    public void setInfo(InfoDorm info) {
        this.info = info;
    }
}
