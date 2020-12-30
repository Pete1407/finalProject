package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testrestapi.Fragment.Detail_Prototype_type1;
import com.example.testrestapi.Fragment.Detail_Prototype_type2;
import com.example.testrestapi.Fragment.Detail_Prototype_type3;
import com.example.testrestapi.Fragment.Detail_Prototype_type4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class PrototypeActivity extends AppCompatActivity {
    public ActionBar bar;
    public String[] data;
    public FragmentTransaction transaction;
    public FragmentManager manager;
    public FrameLayout frame;
    public Spinner spinner;
    public String userchoose;
    public String ip = "192.168.43.57:8080";
    public String nameDorm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prototype);
        this.data = getResources().getStringArray(R.array.choicePrototype);

        SharedPreferences sharedPreferences = getSharedPreferences("file_for_dormowner",MODE_PRIVATE);
        nameDorm = sharedPreferences.getString("nameDorm","no value dormname");

        this.bar = getSupportActionBar();
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));
        this.bar.setTitle("การแสดงผล");
        this.bar.setDisplayHomeAsUpEnabled(true);

        this.frame = (FrameLayout) findViewById(R.id.framelayout);
        this.spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> choice = ArrayAdapter.createFromResource(this,R.array.choicePrototype,android.R.layout.simple_spinner_item);
        choice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(choice);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userchoose = parent.getSelectedItem().toString();

                if(userchoose.equals(data[0]))
                {
                    final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                    String gettypeDorm = "http://"+ip+"/DormTypePriceNPromotion/checkDorm/"+nameDorm;
                    System.out.println("NAME DORM: "+nameDorm);
                    StringRequest request1 = new StringRequest(Request.Method.GET, gettypeDorm, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            final String res = response;
                            String restext = "";
                            String output = "";
                            StringTokenizer token = new StringTokenizer(res,",");
                            while(token.hasMoreTokens())
                            {
                                restext = token.nextToken();
                                output = token.nextToken();
                            }
                            final String output2 = output;
                            if(restext.equals("success"))
                            {
                                String checkPromotion = "http://"+ip+"/DormTypePriceNPromotion/getDormBydormName/"+nameDorm;
                                JsonObjectRequest getpromotion = new JsonObjectRequest(Request.Method.GET, checkPromotion, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        JSONObject object = response;
                                        try {
                                            String promotionDetail = object.getString("promotionDescribe");
                                            int oldPrice = object.getInt("oldPrice");
                                            int newPrice = object.getInt("newPrice");

                                            Bundle b = new Bundle();
                                            b.putString("dormname",nameDorm);
                                            CardView_Prototype_type1 fgr = new CardView_Prototype_type1();
                                            fgr.setArguments(b);
                                            manager = getSupportFragmentManager();
                                            transaction = manager.beginTransaction().replace(R.id.framelayout,fgr);
                                            transaction.commit();
                                            //Intent intent = new Intent(UpdateActivity.this,UpdateDetail_Style.class);
                                            //intent.putExtra("dormname",output2);
                                            //intent.putExtra("codeStyle",0);
                                            //intent.putExtra("promotionDetail",promotionDetail);
                                            //intent.putExtra("oldPrice",oldPrice);
                                            //intent.putExtra("newPrice",newPrice);
                                            //startActivity(intent);
                                        }
                                        catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                                queue.add(getpromotion);

                            }
                            else
                            {
                                System.out.println("NOT FOUND IN TYPE PRICE AND PROMOTION");
                                String checkDormInformation = "http://"+ip+"/DormTypeInformation/checkDorm/"+nameDorm;
                                StringRequest checkrequest = new StringRequest(Request.Method.GET, checkDormInformation, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        String responsedata = response;
                                        String restext = "";
                                        String output = "";
                                        StringTokenizer token = new StringTokenizer(responsedata,",");
                                        while(token.hasMoreTokens())
                                        {
                                            restext = token.nextToken();
                                            output = token.nextToken();
                                        }
                                        final String aa = output;
                                        if(restext.equals("success"))
                                        {
                                            String getoldDetail = "http://"+ip+"/DormTypeInformation/getDorm/"+nameDorm;
                                            JsonObjectRequest olddetailRequest = new JsonObjectRequest(Request.Method.GET, getoldDetail, null, new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    JSONObject obj = response;
                                                    try {
                                                        String infodetail = obj.getString("detail");
                                                        System.out.println("INFODETAIL: "+infodetail);
                                                        Bundle b = new Bundle();
                                                        b.putString("dormname",aa);
                                                        CardView_Prototype_type2 fgr = new CardView_Prototype_type2();
                                                        fgr.setArguments(b);
                                                        manager = getSupportFragmentManager();
                                                        transaction = manager.beginTransaction().replace(R.id.framelayout,fgr);
                                                        transaction.commit();
                                                        //Intent intent = new Intent(UpdateActivity.this,UpdateDetail_Style.class);
                                                        //intent.putExtra("dormname",aa);
                                                        //intent.putExtra("codeStyle",1);
                                                        //intent.putExtra("infodetail",infodetail);
                                                        //startActivity(intent);
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            });
                                            queue.add(olddetailRequest);


                                        }
                                        else
                                        {
                                            System.out.println("NOT FOUND IN TYPE INFORMATION");
                                            String checkQuality = "http://"+ip+"/DormTypeQuality/checkDorm/"+nameDorm;
                                            StringRequest quarequest = new StringRequest(Request.Method.GET, checkQuality, new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    String responsedata = response;
                                                    String restext = "";
                                                    String output = "";
                                                    StringTokenizer token = new StringTokenizer(responsedata,",");
                                                    while(token.hasMoreTokens())
                                                    {
                                                        restext = token.nextToken();
                                                        output = token.nextToken();
                                                    }
                                                    final String uu = output;
                                                    if(restext.equals("success"))
                                                    {
                                                        String getQuality = "http://"+ip+"/DormTypeQuality/getPremiumBydormName/"+nameDorm;
                                                        JsonObjectRequest qualityrequest = new JsonObjectRequest(Request.Method.GET, getQuality, null, new Response.Listener<JSONObject>() {
                                                            @Override
                                                            public void onResponse(JSONObject response) {
                                                                JSONObject obj = response;
                                                                try {
                                                                    JSONArray imagelist = obj.getJSONArray("imagelist");
                                                                    JSONArray thlist = obj.getJSONArray("premieum_th");
                                                                    JSONArray enlist = obj.getJSONArray("premieum_en");
                                                                    ArrayList<Integer> image = new ArrayList<>();
                                                                    ArrayList<String> th = new ArrayList<>();
                                                                    ArrayList<String> en = new ArrayList<>();

                                                                    // ยัดข้อมูลที่ได้จาก database ใส่ไปเพื่อส่งไปให้คลาส UpdateDetail แล้วส่งไปให้ Fragment style ต่อไป
                                                                    for(int count=0;count<imagelist.length();count++)
                                                                    {
                                                                        image.add(imagelist.getInt(count));
                                                                    }

                                                                    for(int count2=0;count2<thlist.length();count2++)
                                                                    {
                                                                        th.add(thlist.getString(count2));
                                                                    }

                                                                    for(int count3=0;count3<enlist.length();count3++)
                                                                    {
                                                                        en.add(enlist.getString(count3));
                                                                    }

                                                                    Bundle b = new Bundle();
                                                                    b.putString("dormname",uu);
                                                                    b.putIntegerArrayList("imagelist",image);
                                                                    CardView_Prototype_type3 fgr = new CardView_Prototype_type3();
                                                                    fgr.setArguments(b);
                                                                    manager = getSupportFragmentManager();
                                                                    transaction = manager.beginTransaction().replace(R.id.framelayout,fgr);
                                                                    transaction.commit();
                                                                    //Intent intent = new Intent(UpdateActivity.this,UpdateDetail_Style.class);
                                                                    //intent.putExtra("dormname",uu);
                                                                    //intent.putExtra("codeStyle",2);
                                                                    //intent.putIntegerArrayListExtra("imagelist",image);
                                                                    //intent.putStringArrayListExtra("thlist",th);
                                                                    //intent.putStringArrayListExtra("enlist",en);
                                                                    //startActivity(intent);
                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }


                                                            }
                                                        }, new Response.ErrorListener() {
                                                            @Override
                                                            public void onErrorResponse(VolleyError error) {

                                                            }
                                                        });
                                                        queue.add(qualityrequest);

                                                    }
                                                    else
                                                    {
                                                        System.out.println("NOT FOUND IN TYPE QUALITY");
                                                        String checkBrand = "http://"+ip+"/DormTypeBrandName/checkDorm/"+nameDorm;
                                                        StringRequest brandrequest = new StringRequest(Request.Method.GET, checkBrand, new Response.Listener<String>() {
                                                            @Override
                                                            public void onResponse(String response) {
                                                                String responsedata = response;
                                                                String restext = "";
                                                                String output = "";
                                                                StringTokenizer token = new StringTokenizer(responsedata,",");
                                                                while(token.hasMoreTokens())
                                                                {
                                                                    restext = token.nextToken();
                                                                    output = token.nextToken();
                                                                }
                                                                final String rr = output;
                                                                if(restext.equals("success"))
                                                                {
                                                                    System.out.println("FOUND IN TYPE BRANDNAME");

                                                                    String getpathlogo = "http://"+ip+"/dormOwner/getInfoDormOwnerByDormName/"+nameDorm;
                                                                    JsonObjectRequest logorequest = new JsonObjectRequest(Request.Method.GET, getpathlogo, null, new Response.Listener<JSONObject>() {
                                                                        @Override
                                                                        public void onResponse(JSONObject response) {
                                                                            JSONObject obj = response;
                                                                            try
                                                                            {
                                                                                final String id = obj.getString("username");
                                                                                final String email = obj.getString("email");

                                                                                String getlogorequest = "http://"+ip+"/LogoBrandName/getPathLogoBrand/"+id+"/"+email;
                                                                                JsonObjectRequest logoo = new JsonObjectRequest(Request.Method.GET, getlogorequest, null, new Response.Listener<JSONObject>() {
                                                                                    @Override
                                                                                    public void onResponse(JSONObject response) {
                                                                                        try {
                                                                                            String pathlogo = response.getString("logo");
                                                                                            Bundle b = new Bundle();
                                                                                            b.putString("dormname",rr);
                                                                                            b.putString("logopath",pathlogo);
                                                                                            CardView_Prototype_type4 fgr = new CardView_Prototype_type4();
                                                                                            fgr.setArguments(b);
                                                                                            manager = getSupportFragmentManager();
                                                                                            transaction = manager.beginTransaction().replace(R.id.framelayout,fgr);
                                                                                            transaction.commit();
                                                                                            //Intent intent = new Intent(UpdateActivity.this,UpdateDetail_Style.class);
                                                                                            //intent.putExtra("dormname",rr);
                                                                                            //intent.putExtra("username",id);
                                                                                            //intent.putExtra("email",email);
                                                                                            //intent.putExtra("pathlogo",pathlogo);
                                                                                            //intent.putExtra("codeStyle",3);
                                                                                            //startActivity(intent);
                                                                                        }
                                                                                        catch (JSONException ex){
                                                                                            ex.printStackTrace();
                                                                                        }

                                                                                    }
                                                                                }, new Response.ErrorListener() {
                                                                                    @Override
                                                                                    public void onErrorResponse(VolleyError error) {

                                                                                    }
                                                                                });
                                                                                queue.add(logoo);
                                                                            } catch (JSONException e) {
                                                                                e.printStackTrace();
                                                                            }
                                                                        }
                                                                    }, new Response.ErrorListener() {
                                                                        @Override
                                                                        public void onErrorResponse(VolleyError error) {

                                                                        }
                                                                    });
                                                                    queue.add(logorequest);

                                                                }
                                                            }
                                                        }, new Response.ErrorListener() {
                                                            @Override
                                                            public void onErrorResponse(VolleyError error) {

                                                            }
                                                        });
                                                        queue.add(brandrequest);
                                                    }

                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            });
                                            queue.add(quarequest);
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                                queue.add(checkrequest);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(request1);
                }
                else
                {
                    final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String gettypeDorm = "http://"+ip+"/DormTypePriceNPromotion/checkDorm/"+nameDorm;
                    System.out.println("NAME DORM: "+nameDorm);
                    StringRequest request1 = new StringRequest(Request.Method.GET, gettypeDorm, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            final String res = response;
                            String restext = "";
                            String output = "";
                            StringTokenizer token = new StringTokenizer(res,",");
                            while(token.hasMoreTokens())
                            {
                                restext = token.nextToken();
                                output = token.nextToken();
                            }
                            final String output2 = output;
                            if(restext.equals("success"))
                            {
                                String checkPromotion = "http://"+ip+"/DormTypePriceNPromotion/getDormBydormName/"+nameDorm;
                                JsonObjectRequest getpromotion = new JsonObjectRequest(Request.Method.GET, checkPromotion, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        JSONObject object = response;
                                        try {
                                            String promotionDetail = object.getString("promotionDescribe");
                                            int oldPrice = object.getInt("oldPrice");
                                            int newPrice = object.getInt("newPrice");

                                            Bundle b = new Bundle();
                                            b.putString("dormname",nameDorm);
                                            Detail_Prototype_type1 fgr = new Detail_Prototype_type1();
                                            fgr.setArguments(b);
                                            manager = getSupportFragmentManager();
                                            transaction = manager.beginTransaction().replace(R.id.framelayout,fgr);
                                            transaction.commit();
                                            //Intent intent = new Intent(UpdateActivity.this,UpdateDetail_Style.class);
                                            //intent.putExtra("dormname",output2);
                                            //intent.putExtra("codeStyle",0);
                                            //intent.putExtra("promotionDetail",promotionDetail);
                                            //intent.putExtra("oldPrice",oldPrice);
                                            //intent.putExtra("newPrice",newPrice);
                                            //startActivity(intent);
                                        }
                                        catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                                queue.add(getpromotion);

                            }
                            else
                            {
                                System.out.println("NOT FOUND IN TYPE PRICE AND PROMOTION");
                                String checkDormInformation = "http://"+ip+"/DormTypeInformation/checkDorm/"+nameDorm;
                                StringRequest checkrequest = new StringRequest(Request.Method.GET, checkDormInformation, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        String responsedata = response;
                                        String restext = "";
                                        String output = "";
                                        StringTokenizer token = new StringTokenizer(responsedata,",");
                                        while(token.hasMoreTokens())
                                        {
                                            restext = token.nextToken();
                                            output = token.nextToken();
                                        }
                                        final String aa = output;
                                        if(restext.equals("success"))
                                        {
                                            String getoldDetail = "http://"+ip+"/DormTypeInformation/getDorm/"+nameDorm;
                                            JsonObjectRequest olddetailRequest = new JsonObjectRequest(Request.Method.GET, getoldDetail, null, new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    JSONObject obj = response;
                                                    try {
                                                        String infodetail = obj.getString("detail");
                                                        System.out.println("INFODETAIL: "+infodetail);
                                                        Bundle b = new Bundle();
                                                        b.putString("dormname",aa);
                                                        Detail_Prototype_type2 fgr = new Detail_Prototype_type2();
                                                        fgr.setArguments(b);
                                                        manager = getSupportFragmentManager();
                                                        transaction = manager.beginTransaction().replace(R.id.framelayout,fgr);
                                                        transaction.commit();
                                                        //Intent intent = new Intent(UpdateActivity.this,UpdateDetail_Style.class);
                                                        //intent.putExtra("dormname",aa);
                                                        //intent.putExtra("codeStyle",1);
                                                        //intent.putExtra("infodetail",infodetail);
                                                        //startActivity(intent);
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            });
                                            queue.add(olddetailRequest);


                                        }
                                        else
                                        {
                                            System.out.println("NOT FOUND IN TYPE INFORMATION");
                                            String checkQuality = "http://"+ip+"/DormTypeQuality/checkDorm/"+nameDorm;
                                            StringRequest quarequest = new StringRequest(Request.Method.GET, checkQuality, new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    String responsedata = response;
                                                    String restext = "";
                                                    String output = "";
                                                    StringTokenizer token = new StringTokenizer(responsedata,",");
                                                    while(token.hasMoreTokens())
                                                    {
                                                        restext = token.nextToken();
                                                        output = token.nextToken();
                                                    }
                                                    final String uu = output;
                                                    if(restext.equals("success"))
                                                    {
                                                        String getQuality = "http://"+ip+"/DormTypeQuality/getPremiumBydormName/"+nameDorm;
                                                        JsonObjectRequest qualityrequest = new JsonObjectRequest(Request.Method.GET, getQuality, null, new Response.Listener<JSONObject>() {
                                                            @Override
                                                            public void onResponse(JSONObject response) {
                                                                JSONObject obj = response;
                                                                try {
                                                                    JSONArray imagelist = obj.getJSONArray("imagelist");
                                                                    JSONArray thlist = obj.getJSONArray("premieum_th");
                                                                    JSONArray enlist = obj.getJSONArray("premieum_en");
                                                                    ArrayList<Integer> image = new ArrayList<>();
                                                                    ArrayList<String> th = new ArrayList<>();
                                                                    ArrayList<String> en = new ArrayList<>();

                                                                    // ยัดข้อมูลที่ได้จาก database ใส่ไปเพื่อส่งไปให้คลาส UpdateDetail แล้วส่งไปให้ Fragment style ต่อไป
                                                                    for(int count=0;count<imagelist.length();count++)
                                                                    {
                                                                        image.add(imagelist.getInt(count));
                                                                    }

                                                                    for(int count2=0;count2<thlist.length();count2++)
                                                                    {
                                                                        th.add(thlist.getString(count2));
                                                                    }

                                                                    for(int count3=0;count3<enlist.length();count3++)
                                                                    {
                                                                        en.add(enlist.getString(count3));
                                                                    }

                                                                    Bundle b = new Bundle();
                                                                    b.putString("dormname",uu);
                                                                    b.putIntegerArrayList("imagelist",image);
                                                                    Detail_Prototype_type3 fgr = new Detail_Prototype_type3();
                                                                    fgr.setArguments(b);
                                                                    manager = getSupportFragmentManager();
                                                                    transaction = manager.beginTransaction().replace(R.id.framelayout,fgr);
                                                                    transaction.commit();
                                                                    //Intent intent = new Intent(UpdateActivity.this,UpdateDetail_Style.class);
                                                                    //intent.putExtra("dormname",uu);
                                                                    //intent.putExtra("codeStyle",2);
                                                                    //intent.putIntegerArrayListExtra("imagelist",image);
                                                                    //intent.putStringArrayListExtra("thlist",th);
                                                                    //intent.putStringArrayListExtra("enlist",en);
                                                                    //startActivity(intent);
                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }


                                                            }
                                                        }, new Response.ErrorListener() {
                                                            @Override
                                                            public void onErrorResponse(VolleyError error) {

                                                            }
                                                        });
                                                        queue.add(qualityrequest);

                                                    }
                                                    else
                                                    {
                                                        System.out.println("NOT FOUND IN TYPE QUALITY");
                                                        String checkBrand = "http://"+ip+"/DormTypeBrandName/checkDorm/"+nameDorm;
                                                        StringRequest brandrequest = new StringRequest(Request.Method.GET, checkBrand, new Response.Listener<String>() {
                                                            @Override
                                                            public void onResponse(String response) {
                                                                String responsedata = response;
                                                                String restext = "";
                                                                String output = "";
                                                                StringTokenizer token = new StringTokenizer(responsedata,",");
                                                                while(token.hasMoreTokens())
                                                                {
                                                                    restext = token.nextToken();
                                                                    output = token.nextToken();
                                                                }
                                                                final String rr = output;
                                                                if(restext.equals("success"))
                                                                {
                                                                    System.out.println("FOUND IN TYPE BRANDNAME");

                                                                    String getpathlogo = "http://"+ip+"/dormOwner/getInfoDormOwnerByDormName/"+nameDorm;
                                                                    JsonObjectRequest logorequest = new JsonObjectRequest(Request.Method.GET, getpathlogo, null, new Response.Listener<JSONObject>() {
                                                                        @Override
                                                                        public void onResponse(JSONObject response) {
                                                                            JSONObject obj = response;
                                                                            try
                                                                            {
                                                                                final String id = obj.getString("username");
                                                                                final String email = obj.getString("email");

                                                                                String getlogorequest = "http://"+ip+"/LogoBrandName/getPathLogoBrand/"+id+"/"+email;
                                                                                JsonObjectRequest logoo = new JsonObjectRequest(Request.Method.GET, getlogorequest, null, new Response.Listener<JSONObject>() {
                                                                                    @Override
                                                                                    public void onResponse(JSONObject response) {
                                                                                        try {
                                                                                            String pathlogo = response.getString("logo");
                                                                                            Bundle b = new Bundle();
                                                                                            b.putString("dormname",rr);
                                                                                            b.putString("logopath",pathlogo);
                                                                                            Detail_Prototype_type4 fgr = new Detail_Prototype_type4();
                                                                                            fgr.setArguments(b);
                                                                                            manager = getSupportFragmentManager();
                                                                                            transaction = manager.beginTransaction().replace(R.id.framelayout,fgr);
                                                                                            transaction.commit();
                                                                                            //Intent intent = new Intent(UpdateActivity.this,UpdateDetail_Style.class);
                                                                                            //intent.putExtra("dormname",rr);
                                                                                            //intent.putExtra("username",id);
                                                                                            //intent.putExtra("email",email);
                                                                                            //intent.putExtra("pathlogo",pathlogo);
                                                                                            //intent.putExtra("codeStyle",3);
                                                                                            //startActivity(intent);
                                                                                        }
                                                                                        catch (JSONException ex){
                                                                                            ex.printStackTrace();
                                                                                        }

                                                                                    }
                                                                                }, new Response.ErrorListener() {
                                                                                    @Override
                                                                                    public void onErrorResponse(VolleyError error) {

                                                                                    }
                                                                                });
                                                                                queue.add(logoo);
                                                                            } catch (JSONException e) {
                                                                                e.printStackTrace();
                                                                            }
                                                                        }
                                                                    }, new Response.ErrorListener() {
                                                                        @Override
                                                                        public void onErrorResponse(VolleyError error) {

                                                                        }
                                                                    });
                                                                    queue.add(logorequest);

                                                                }
                                                            }
                                                        }, new Response.ErrorListener() {
                                                            @Override
                                                            public void onErrorResponse(VolleyError error) {

                                                            }
                                                        });
                                                        queue.add(brandrequest);
                                                    }

                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            });
                                            queue.add(quarequest);
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                                queue.add(checkrequest);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(request1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
