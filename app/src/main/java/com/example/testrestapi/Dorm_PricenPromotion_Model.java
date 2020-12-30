package com.example.testrestapi;

public class Dorm_PricenPromotion_Model {

    public int sticker;

    public String dorm_name;
    public String distance;
    public String image;
    public int oldPrice;
    public int newPrice;
    public int status_promotion;
    public Faculty faculty;
    public double distanceDouble;
    public String prodetail;
    public Dorm_PricenPromotion_Model(){
        this.dorm_name = "";
        this.distance = "";
        this.image = "";
        this.oldPrice = 0;
        this.newPrice = 0;
        this.faculty = null;
        this.distanceDouble = 0;
        prodetail = "";
    }

    public Dorm_PricenPromotion_Model(String dorm_name,String distance ,String image, int oldPrice,int newPrice,int status,Faculty faculty) {
        this.dorm_name = dorm_name;
        this.image = image;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.status_promotion = status;
        this.faculty = faculty;
        this.distance = distance;
        if(status_promotion == 1)
        {
            this.sticker = R.drawable.iconpromotion;
        }
    }



    public int getSticker() {
        return sticker;
    }

    public void setSticker(int sticker) {
        this.sticker = sticker;
    }

    public String getDorm_name() {
        return dorm_name;
    }

    public void setDorm_name(String dorm_name) {
        this.dorm_name = dorm_name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus_promotion() {
        return status_promotion;
    }

    public void setStatus_promotion(int status_promotion) {
        this.status_promotion = status_promotion;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(int newPrice) {
        this.newPrice = newPrice;
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

    public String getProdetail() {
        return prodetail;
    }

    public void setProdetail(String prodetail) {
        this.prodetail = prodetail;
    }
}
