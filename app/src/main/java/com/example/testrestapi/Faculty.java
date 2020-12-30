package com.example.testrestapi;

import java.util.ArrayList;

public class Faculty {
    public int idFaculty;
    public String name_faculty;
    public double latitude;
    public double longtitude;
    public ArrayList<Places> placelist;
    public int count = 0;
    public Faculty(int idFaculty, String name_faculty, double latitude, double longtitude) {
        this.placelist = new ArrayList<>();
        this.idFaculty = idFaculty;
        this.name_faculty = name_faculty;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public void addPlaces(Places p){
        count++;
        placelist.add(p);
   }

   public ArrayList<Places> getPlacelist(){
       return placelist;
   }

    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }

    public String getName_faculty() {
        return name_faculty;
    }

    public void setName_faculty(String name_faculty) {
        this.name_faculty = name_faculty;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
