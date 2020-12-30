package com.example.testrestapi;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Facility implements Parcelable {
        public String textTH;
        public String textEN;
        public boolean check;
        public int image;

     public Facility(String textth,String texten,int image){
            this.textTH = textth;
            this.textEN = texten;
            this.image = image;
            this.check = true;
     }



    public Facility(String textth,String texten, boolean c,int image){
        this.textTH = textth;
        this.textEN = texten;
        this.check = c;
        this.image = image;
    }

    protected Facility(Parcel in) {
        textTH = in.readString();
        textEN = in.readString();
        image = in.readInt();
        check = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Facility> CREATOR = new Parcelable.Creator<Facility>() {
        @Override
        public Facility createFromParcel(Parcel in) {
            return new Facility(in);
        }

        @Override
        public Facility[] newArray(int size) {
            return new Facility[size];
        }
    };

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTextTH() {
        return textTH;
    }

    public void setTextTH(String textTH) {
        this.textTH = textTH;
    }

    public String getTextEN() {
        return textEN;
    }

    public void setTextEN(String textEN) {
        this.textEN = textEN;
    }



    @NonNull
    @Override
    public String toString() {
        return this.textTH;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(textTH);
        dest.writeString(textEN);
        dest.writeInt(image);
        dest.writeByte((byte) (check ? 1 : 0));
    }
}
