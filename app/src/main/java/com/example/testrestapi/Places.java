package com.example.testrestapi;

import android.os.Parcel;
import android.os.Parcelable;

public class Places implements Parcelable {

    public String nameTH;
    public String nameEN;
    public String distance;
    public double latitude_place;
    public double longtitude_place;

    public Places(String nameTH,String nameEN, double latitude_place, double longtitude_place) {
        this.nameTH = nameTH;
        this.nameEN = nameEN;
        this.latitude_place = latitude_place;
        this.longtitude_place = longtitude_place;

    }

    protected Places(Parcel in) {
        nameTH = in.readString();
        nameEN = in.readString();
        distance = in.readString();
        latitude_place = in.readDouble();
        longtitude_place = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameTH);
        dest.writeString(nameEN);
        dest.writeString(distance);
        dest.writeDouble(latitude_place);
        dest.writeDouble(longtitude_place);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Places> CREATOR = new Creator<Places>() {
        @Override
        public Places createFromParcel(Parcel in) {
            return new Places(in);
        }

        @Override
        public Places[] newArray(int size) {
            return new Places[size];
        }
    };

    public String getNameEN() {
        return nameEN;
    }

    public String getNameTH() {
        return nameTH;
    }



    public void setDistance(String distance){
        this.distance = distance;
    }

    public String getDistance() {
        return distance;
    }

    public double getLatitude_place() {
        return latitude_place;
    }

    public double getLongtitude_place(){
        return longtitude_place;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public void setNameTH(String nameTH) {
        this.nameTH = nameTH;
    }


    public void setLatitude_place(double latitude_place) {
        this.latitude_place = latitude_place;
    }

    public void setLongtitude_place(double longtitude_place) {
        this.longtitude_place = longtitude_place;
    }
}
