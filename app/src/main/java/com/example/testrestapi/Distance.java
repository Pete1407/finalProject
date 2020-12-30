package com.example.testrestapi;


import android.os.Parcel;
import android.os.Parcelable;

public class Distance implements Parcelable{
    public String distanceTextTH;
    public String distanceTextEN;
    public boolean check;
    public double distanceInt;



    public Distance(String distanceText,String distanceTextEN,Boolean check,double distanceInt){
        this.distanceTextTH = distanceText;
        this.distanceTextEN = distanceTextEN;
        this.check = check;
        this.distanceInt = distanceInt;
    }

    protected Distance(Parcel in) {
        distanceTextTH = in.readString();
        distanceTextEN = in.readString();
        distanceInt = in.readDouble();
        check = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Distance> CREATOR = new Parcelable.Creator<Distance>() {
        @Override
        public Distance createFromParcel(Parcel in) {
            return new Distance(in);
        }

        @Override
        public Distance[] newArray(int size) {
            return new Distance[size];
        }
    };



    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public double getDistanceInt() {
        return distanceInt;
    }

    public void setDistanceInt(int distanceInt) {
        this.distanceInt = distanceInt;
    }

    public String getDistanceTextTH() {
        return distanceTextTH;
    }

    public void setDistanceTextTH(String distanceTextTH) {
        this.distanceTextTH = distanceTextTH;
    }

    public String getDistanceTextEN() {
        return distanceTextEN;
    }

    public void setDistanceTextEN(String distanceTextEN) {
        this.distanceTextEN = distanceTextEN;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(distanceTextTH);
        dest.writeString(distanceTextEN);
        dest.writeDouble(distanceInt);
        dest.writeByte((byte) (check ? 1 : 0));
    }
}
