package com.example.testrestapi;

import android.os.Parcel;
import android.os.Parcelable;

public class Premieum implements Parcelable{

   public String name_th;
   public String name_en;
   public int image;
   public boolean check;

    public Premieum(String name_th, String name_en, int image) {
        this.name_th = name_th;
        this.name_en = name_en;
        this.image = image;
        check = false;
    }

    protected Premieum(Parcel in) {
        name_th = in.readString();
        name_en = in.readString();
        image = in.readInt();
        check = in.readByte() != 0;
    }

    public static final Creator<Premieum> CREATOR = new Creator<Premieum>() {
        @Override
        public Premieum createFromParcel(Parcel in) {
            return new Premieum(in);
        }

        @Override
        public Premieum[] newArray(int size) {
            return new Premieum[size];
        }
    };

    public String getName_th() {
        return name_th;
    }

    public void setName_th(String name_th) {
        this.name_th = name_th;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setCheck(boolean c){
        this.check = c;
    }

    public boolean getCheck(){
        return check;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name_th);
        dest.writeString(name_en);
        dest.writeInt(image);
        dest.writeByte((byte) (check ? 1 : 0));
    }
}
