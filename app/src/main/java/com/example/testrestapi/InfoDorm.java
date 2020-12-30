package com.example.testrestapi;

import java.util.ArrayList;

public class InfoDorm {

    public String dormID;
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
    public String dormStyle;
    public String coverImage;
    public String image360;

    public ArrayList<String>  imagelist;

    public InfoDorm(String dormID, int minPrice, int maxPrice, String typeWater, int priceWater, String typeElectro, int priceElectro, String typeDeposit, int depositPrice, String typeCommonFee, int commonFeePrice,String dormStyle) {
        this.dormID = dormID;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.typeWater = typeWater;
        this.priceWater = priceWater;
        this.typeElectro = typeElectro;
        this.priceElectro = priceElectro;
        this.typeDeposit = typeDeposit;
        this.depositPrice = depositPrice;
        this.typeCommonFee = typeCommonFee;
        this.commonFeePrice = commonFeePrice;
        this.dormStyle = dormStyle;

    }




    public String getDormID() {
        return dormID;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public String getTypeWater() {
        return typeWater;
    }

    public int getPriceWater() {
        return priceWater;
    }

    public String getTypeElectro() {
        return typeElectro;
    }

    public int getPriceElectro() {
        return priceElectro;
    }

    public String getTypeDeposit() {
        return typeDeposit;
    }

    public int getDepositPrice() {
        return depositPrice;
    }

    public String getTypeCommonFee() {
        return typeCommonFee;
    }

    public int getCommonFeePrice() {
        return commonFeePrice;
    }

    public String getDormStyle() {
        return dormStyle;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getImage360() {
        return image360;
    }

    public void setImage360(String image360) {
        this.image360 = image360;
    }

    public ArrayList<String> getImagelist() {
        return imagelist;
    }

    public void setImagelist(ArrayList<String> imagelist) {
        this.imagelist = imagelist;
    }
}
