package com.example.testrestapi;

import java.util.ArrayList;

public interface FacilityCallback {

    void onsuccess(ArrayList<Facility> fac);
    void onfailure(String error);
}
