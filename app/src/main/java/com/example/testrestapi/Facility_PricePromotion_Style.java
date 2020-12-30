package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Facility_PricePromotion_Style extends AppCompatActivity {

    public Facility_PricePromotion_Adapter adapter;
    public GridView recyclerview;
    public RecyclerView.LayoutManager manager;
    public String nameDorm;
    public SharedPreferences sharedPreferences;
    public String port = ":8080";
    public String ip = "192.168.43.57";
    public Facility[] facilityList1;
    public Facility[] facilityList2;
    public ArrayList<Facility> list;

    public ActionBar bar;
    public int langcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility__price_promotion__style);
        this.list = new ArrayList<>();

        this.bar = getSupportActionBar();
        String title = getResources().getString(R.string.facility_title);
        this.bar.setTitle(title);
        this.bar.setDisplayHomeAsUpEnabled(true);

        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));

        SharedPreferences lang = getSharedPreferences("language", Context.MODE_PRIVATE);
        langcode = lang.getInt("languageCode",0);

        this.sharedPreferences = getSharedPreferences("selectDorm",MODE_PRIVATE);
        this.nameDorm = sharedPreferences.getString("dormname","no value");
        System.out.println("NAME DORM: "+nameDorm);
        this.recyclerview = (GridView) findViewById(R.id.recycler);

        this.getFacility(nameDorm);
    }

    public void getFacility(String dormName){
        String pathIndorm = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+dormName;
       final String pathInroom = "http://"+ip+port+"/facilityInRoom/getFacilityInRoom/"+dormName;

      final RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest indorm = new JsonObjectRequest(Request.Method.GET, pathIndorm, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    final JSONArray nameTH = response.getJSONArray("nameTH");
                    JSONArray nameEN = response.getJSONArray("nameEN");
                    JSONArray imagecode = response.getJSONArray("image");
                    facilityList1 = new Facility[imagecode.length()];
                    for(int count=0;count<imagecode.length();count++)
                    {
                        int code = imagecode.getInt(count);
                        String th = nameTH.getString(count);
                        String en = nameEN.getString(count);
                       facilityList1[count] = new Facility(th,en,code);
                       list.add(facilityList1[count]);
                    }

                    System.out.println("FACILITY IN DORM : "+facilityList1.length);

                    JsonObjectRequest inroom = new JsonObjectRequest(Request.Method.GET, pathInroom, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray nameth = response.getJSONArray("nameTH");
                                JSONArray nameen = response.getJSONArray("nameEN");
                                JSONArray imagecode = response.getJSONArray("image");
                                facilityList2 = new Facility[imagecode.length()];
                                for(int count2=0;count2<imagecode.length();count2++)
                                {
                                    int codee = imagecode.getInt(count2);
                                    String thtext = nameth.getString(count2);
                                    String entext = nameen.getString(count2);
                                    facilityList2[count2] = new Facility(thtext,entext,codee);
                                    list.add(facilityList2[count2]);
                                }

                                for(int i=0;i<list.size();i++)
                                {
                                    System.out.println(list.get(i).getTextTH());
                                }

                                adapter = new Facility_PricePromotion_Adapter(list,langcode);
                                manager = new LinearLayoutManager(getApplicationContext());
                                recyclerview.setAdapter(adapter);
                            //System.out.println("FACILITY IN ROOM: "+facilityList2.length);
                            int summary = facilityList1.length + facilityList2.length;
                            //System.out.println("SUM: "+summary);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    queue.add(inroom);

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(indorm);


    }
}
