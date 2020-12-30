package com.example.testrestapi;

import java.util.ArrayList;

public interface Callback{

    void onSuccess(ArrayList<String> list);
    void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1);
    void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1);
    void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1);
    void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1);
    void onFail(String msg);
}
