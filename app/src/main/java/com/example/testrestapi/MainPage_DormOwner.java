package com.example.testrestapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainPage_DormOwner extends AppCompatActivity {

    public RecyclerView recylcerview;
    public MainPage_DormOwner_RecyclerAdapter adapter;
    public ArrayList<Dorm> list;
    public ArrayList<Container> conlist;
    public ArrayList<InfoDorm> infolist;
    public ArrayList<InfoDorm> backlist;
    public ArrayList<String> imageList;
    public String ip = "192.168.43.57:8080";
    public String url = "http://" + ip;
    private static final int PICK_FROM_GALLERY = 1;

    public Dorm dorm = null;
    public InfoDorm infoDorm = null;
    public String username;
    public SharedPreferences sharedPreferences;
    public String[] nameDorm;

    public JsonObjectRequest request;
    public RequestQueue queue;
    public String dormid = "";

    public ActionBar bar;
    public RecyclerView.LayoutManager layoutManager;
    public int count2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy_main_page_dorm_owner);
        this.backlist = new ArrayList<>();
        this.conlist = new ArrayList<>();
        this.list = new ArrayList<>();
        this.imageList = new ArrayList<>();
        this.infolist = new ArrayList<>();

        this.sharedPreferences = getSharedPreferences("file_pref", MODE_PRIVATE);
        this.username = sharedPreferences.getString("username", "Username Default");


        this.bar = getSupportActionBar();
        this.bar.setTitle("หน้าหลัก");
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));


        this.recylcerview = (RecyclerView) findViewById(R.id.recycler);

        url = url + "/Container/getContainerUsername/" + username;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    dorm = null;
                    JSONArray array = response;
                    for (int count = 0; count < array.length(); count++) {

                        JSONObject obj = array.getJSONObject(count);
                        String dormID = obj.getString("dormID");
                        String username = obj.getString("username");
                        Container con = new Container(dormID, username);
                        conlist.add(con);
                    }

                    System.out.println("Size of Conlist : " + conlist.size());

                    for (int count2 = 0; count2 < conlist.size(); count2++) {
                        System.out.println(conlist.get(count2).getDormID() + "  " + conlist.get(count2).getUsername());
                    }

                    String userN = conlist.get(count2).getUsername();
                    String url2 = "http://" + ip + "/dorm/getDormByUsername/" + userN;

                    JsonArrayRequest request2 = new JsonArrayRequest(Request.Method.GET, url2, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            final JSONArray array = response;
                            for (int i = 0; i < array.length(); i++)
                            {
                                try {
                                    JSONObject obj = array.getJSONObject(i);
                                    dormid = obj.getString("dormID");
                                    String dormOwnerid = obj.getString("dormownerID");
                                    String address = obj.getString("address");
                                    double lati = obj.getDouble("latitude");
                                    double longti = obj.getDouble("longtitude");

                                    Dorm d = new Dorm(dormid, dormOwnerid, address, lati, longti);
                                    list.add(d);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                                System.out.println("DORM SIZE :::: " + list.size());

                            }

                            for (int j = 0; j < list.size(); j++)
                            {
                                System.out.println(list.get(j).getDormID() + " ---- " + list.get(j).getAddress());
                                final int a = j;
                                String url3 = "http://192.168.43.57:8080" + "/InfoDorm/getInfoDormByDormName/" + list.get(j).getDormID();
                                System.out.println("URL3 : " + url3);
                                JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.GET, url3, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                       try
                                       {
                                           JSONObject obj = response;
                                           String did = obj.getString("dormID");
                                           int minP = obj.getInt("minPrice");
                                           int maxP = obj.getInt("maxPrice");
                                           String typeWater = obj.getString("typeWater");
                                           int priceWater = obj.getInt("priceWater");
                                           String typeElectro = obj.getString("typeElectro");
                                           int priceElectro = obj.getInt("priceElectro");
                                           String typeDeposit = obj.getString("typeDeposit");
                                           int depositPrice = obj.getInt("depositPrice");
                                           String typeCommonFee = obj.getString("typeCommonFee");
                                           int commonFee = obj.getInt("commonFee");
                                           String dormStyle = obj.getString("dormStyle");
                                           //String coverImage = obj.getString("coverImage");
                                           //String image360 = obj.getString("image360");
                                           //JSONArray pictureList = obj.getJSONArray("imageList");
                                           InfoDorm in =  new InfoDorm(did,minP,maxP,typeWater,priceWater,typeElectro,priceElectro,typeDeposit,
                                                   depositPrice,typeCommonFee,commonFee,dormStyle);
                                           list.get(a).setInfo(in);
                                           infolist.add(in);
                                           backlist = infolist;
                                           System.out.println("Size of Infolist: "+infolist.size());
                                           System.out.println("Size of backlist: "+backlist.size());

                                           if(infolist.size() == list.size())
                                           {


                                           for(int e=0;e<backlist.size();e++)
                                           {
                                               System.out.println("INFO "+e+":"+backlist.get(e).getMinPrice()+"  "+backlist.get(e).getMaxPrice());
                                           }
                                           System.out.println();
                                           System.out.println("##########################");
                                           System.out.println("Summary: List: "+list.size()+"  "+"infolist: "+infolist.size());
                                           for(int f=0;f<list.size();f++)
                                           {
                                               System.out.println("Dorm Owner name: "+list.get(f).getDormOwnerName());
                                               System.out.println("Name dorm: "+list.get(f).getInfo().getDormID()+"  "+"start price: "+list.get(f).getInfo().getMinPrice());
                                           }
                                           System.out.println("##########################");
                                           System.out.println();
                                           System.out.println();
                                           System.out.println("Final Check: "+"Size List: "+list.size()+"  Size infoList: "+infolist.size());
                                           adapter = new MainPage_DormOwner_RecyclerAdapter(MainPage_DormOwner.this,list,infolist);
                                           layoutManager = new LinearLayoutManager(getApplicationContext());
                                           recylcerview.setAdapter(adapter);
                                           recylcerview.setLayoutManager(layoutManager);
                                           }
                                       }
                                       catch(JSONException ex){
                                           ex.printStackTrace();
                                       }

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                       Log.e("TAG",error.toString());
                                    }
                                });

                                queue.add(request3);
                                System.out.println("Size of list: "+list.size());



                                for(int l=0;l<list.size();l++)
                                {
                                    //System.out.println(list.get(l).getDormID()+"  "+list.get(l).getInfo().dormStyle+"  "+list.get(l).getInfo().getMinPrice()+" "+list.get(l).getInfo().getMaxPrice());
                                }
                            }
                            //System.out.println("------------------------------------------------");
                            //System.out.println(dormid);


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("TAG", error.toString());
                        }
                    });


                    queue.add(request2);


                } catch (JSONException ex) {
                    ex.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue = Volley.newRequestQueue(this);
        queue.add(request);


        System.out.println("INFO ::::: "+infolist.size());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adddorm, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.addDorm) {
            Intent intent = new Intent(MainPage_DormOwner.this, AddDorm.class);
            startActivity(intent);
        }
        return true;
    }
/*
   for(int j=0;j<list.size();j++)
    {
        System.out.println(list.get(j).getDormID()+" ---- "+list.get(j).getAddress());

        String url3 = "http://192.168.43.57:8080"+"/InfoDorm/getInfoDormByDormName/"+list.get(j).getDormID() ;
        System.out.println("URL3 : "+url3);
        JsonArrayRequest request3 = new JsonArrayRequest(Request.Method.GET, url3, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray array2 = response;
                System.out.println("Data: "+array2);
                for(int k=0;k<array2.length();k++)
                {
                    try
                    {

                        JSONObject obj2 = array2.getJSONObject(k);
                        String did = obj2.getString("dormID");
                        int minP = obj2.getInt("minPrice");
                        int maxP = obj2.getInt("maxPrice");
                        String typeWater = obj2.getString("typeWater");
                        int priceWater = obj2.getInt("priceWater");
                        String typeElectro = obj2.getString("typeElectro");
                        int priceElectro = obj2.getInt("priceElectro");
                        String typeDeposit = obj2.getString("typeDeposit");
                        int depositPrice = obj2.getInt("depositPrice");
                        String typeCommonFee = obj2.getString("typeCommonFee");
                        int commonFee = obj2.getInt("commonFee");
                        String dormStyle = obj2.getString("dormStyle");
                        String coverImage = obj2.getString("coverImage");
                        String image360 = obj2.getString("image360");
                        JSONArray pictureList = obj2.getJSONArray("imageList");

                        for(int l=0;l<pictureList.length();l++)
                        {
                            imageList.add(pictureList.get(l).toString());
                        }
                        infoDorm = new InfoDorm(did,minP,maxP,typeWater,priceWater,typeElectro,priceElectro,typeDeposit,
                                depositPrice,typeCommonFee,commonFee,dormStyle);

                        System.out.println("MIN PRICE::: "+infoDorm.getMinPrice()+"   "+"MAX PRICE::: "+infoDorm.getMaxPrice());
                        infoDorm.setCoverImage(coverImage);
                        infoDorm.setImage360(image360);
                        infoDorm.setImagelist(imageList);
                        infolist.add(infoDorm);

                        list.get(k).setInfo(infoDorm);

                        System.out.println("INFO ::::: "+infolist.size());
                        adapter = new MainPage_DormOwner_RecyclerAdapter(MainPage_DormOwner.this,list,infolist);
                        layoutManager = new LinearLayoutManager(getApplicationContext());
                        recylcerview.setAdapter(adapter);
                        recylcerview.setLayoutManager(layoutManager);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Tag",error.toString());
            }
        });
        queue.add(request3);




    }
    */
}


