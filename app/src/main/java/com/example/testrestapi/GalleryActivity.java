package com.example.testrestapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.BitSet;

public class GalleryActivity extends AppCompatActivity {
    public GridView grid;
    public GalleryAdapter adapter;
    public ArrayList<Uri> imagelist;
    public ArrayList<String> imagelist2;
    public ActionBar bar;

    public int minPrice;
    public int maxPrice;
    public String typeWater;
    public int  priceWater;
    public String typeElectro;
    public int priceElectro;
    public String typeDeposit;
    public int depositPrice;
    public String typeCommonFee;
    public int commonFee;
    public String description;
    public String dormID;
    public String dormStyle;

    /// Part of type Price and Promotion ///
    public String promotionDescribe;
    public int oldPrice;
    public int newPrice;

    /// Part of type Information ///
    public String information;

    /// Part of type Quality ///
    public ArrayList<Premieum> list = new ArrayList<>();

    /// Part of type BrandName ///
    public String logo;


    /// Part of all type ///
    public String coverImage;
    public String image360;

    String[] array;

    public String ip = "192.168.43.57:8080";
    public String url = "http://"+ip;

    public String username;
    public String password;
    public String email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        array = getResources().getStringArray(R.array.dormtype);
        this.bar = getSupportActionBar();
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));
        this.bar.setTitle("รูปภาพเพิ่มเติม");

        this.username = getIntent().getStringExtra("username");
        this.password = getIntent().getStringExtra("password");
        this.email = getIntent().getStringExtra("email");

        //Toast.makeText(this,"Email: "+email+"\n"+"Username: "+username,Toast.LENGTH_LONG).show();

        imagelist2 = new ArrayList<>();

        this.grid = (GridView) findViewById(R.id.grid);

        // get multiple image from UploadImageActivity.class
        imagelist = getIntent().getParcelableArrayListExtra("imagelist");
        for(int j=0;j<imagelist.size();j++)
        {
            imagelist2.add(imagelist.get(j).toString());
        }

        // set Adapter for Gallery
        this.adapter = new GalleryAdapter(this,imagelist);

        this.dormStyle = getIntent().getStringExtra("dormStyle");

        this.grid.setAdapter(adapter);
        this.grid.setHorizontalSpacing(this.grid.getHorizontalSpacing());
        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) this.grid.getLayoutParams();
        mlp.setMargins(5, this.grid.getHorizontalSpacing(), 5, 0);
        System.out.println("Number of image are "+this.adapter.getCount());

        this.grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //Toast.makeText(getApplicationContext(),"Click "+position,Toast.LENGTH_LONG).show();
            }
        });

        if(dormStyle.equals(array[0]))
        {
            dormID = getIntent().getStringExtra("dormID");
            minPrice = getIntent().getIntExtra("minPrice",0);
            maxPrice = getIntent().getIntExtra("maxPrice",0);
            typeWater = getIntent().getStringExtra("typeWater");
            priceWater = getIntent().getIntExtra("priceWater",0);
            typeElectro = getIntent().getStringExtra("typeElectro");
            priceElectro = getIntent().getIntExtra("priceElectro",0);
            typeDeposit = getIntent().getStringExtra("typeDeposit");
            depositPrice = getIntent().getIntExtra("depositPrice",0);
            typeCommonFee = getIntent().getStringExtra("typeCommonFee");
            commonFee = getIntent().getIntExtra("commonFee",0);
            description = getIntent().getStringExtra("description");
            dormStyle = getIntent().getStringExtra("dormStyle");

            promotionDescribe = getIntent().getStringExtra("promotionDescribe");
            oldPrice = getIntent().getIntExtra("OldPrice",0);
            newPrice = getIntent().getIntExtra("NewPrice",0);


            coverImage = getIntent().getStringExtra("coverImage");
            image360 = getIntent().getStringExtra("image360");
        }
        else if(dormStyle.equals(array[1]))
        {
            dormID = getIntent().getStringExtra("dormID");
            minPrice = getIntent().getIntExtra("minPrice",0);
            maxPrice = getIntent().getIntExtra("maxPrice",0);
            typeWater = getIntent().getStringExtra("typeWater");
            priceWater = getIntent().getIntExtra("priceWater",0);
            typeElectro = getIntent().getStringExtra("typeElectro");
            priceElectro = getIntent().getIntExtra("priceElectro",0);
            typeDeposit = getIntent().getStringExtra("typeDeposit");
            depositPrice = getIntent().getIntExtra("depositPrice",0);
            typeCommonFee = getIntent().getStringExtra("typeCommonFee");
            commonFee = getIntent().getIntExtra("commonFee",0);
            description = getIntent().getStringExtra("description");
            dormStyle = getIntent().getStringExtra("dormStyle");

            information = getIntent().getStringExtra("information");

            coverImage = getIntent().getStringExtra("coverImage");
            image360 = getIntent().getStringExtra("image360");
        }
        else if(dormStyle.equals(array[2]))
        {
            dormID = getIntent().getStringExtra("dormID");
            minPrice = getIntent().getIntExtra("minPrice",0);
            maxPrice = getIntent().getIntExtra("maxPrice",0);
            typeWater = getIntent().getStringExtra("typeWater");
            priceWater = getIntent().getIntExtra("priceWater",0);
            typeElectro = getIntent().getStringExtra("typeElectro");
            priceElectro = getIntent().getIntExtra("priceElectro",0);
            typeDeposit = getIntent().getStringExtra("typeDeposit");
            depositPrice = getIntent().getIntExtra("depositPrice",0);
            typeCommonFee = getIntent().getStringExtra("typeCommonFee");
            commonFee = getIntent().getIntExtra("commonFee",0);
            description = getIntent().getStringExtra("description");
            dormStyle = getIntent().getStringExtra("dormStyle");

            list = getIntent().getParcelableArrayListExtra("premieum");

            coverImage = getIntent().getStringExtra("coverImage");
            image360 = getIntent().getStringExtra("image360");
        }
        else if(dormStyle.equals(array[3]))
        {
            dormID = getIntent().getStringExtra("dormID");
            minPrice = getIntent().getIntExtra("minPrice",0);
            maxPrice = getIntent().getIntExtra("maxPrice",0);
            typeWater = getIntent().getStringExtra("typeWater");
            priceWater = getIntent().getIntExtra("priceWater",0);
            typeElectro = getIntent().getStringExtra("typeElectro");
            priceElectro = getIntent().getIntExtra("priceElectro",0);
            typeDeposit = getIntent().getStringExtra("typeDeposit");
            depositPrice = getIntent().getIntExtra("depositPrice",0);
            typeCommonFee = getIntent().getStringExtra("typeCommonFee");
            commonFee = getIntent().getIntExtra("commonFee",0);
            description = getIntent().getStringExtra("description");

            dormStyle = getIntent().getStringExtra("dormStyle");
            logo = getIntent().getStringExtra("imageName");

            //coverImage = getIntent().getStringExtra("coverImage");
            //image360 = getIntent().getStringExtra("image360");
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int a = item.getItemId();
        if(a == R.id.confirm)
        {
            this.url = this.url + "/InfoDorm/addInfoDorm";
            Intent i = new Intent(GalleryActivity.this,LogIn.class);
            JSONObject object = new JSONObject();
            JSONArray imageList = new JSONArray();
            if(dormStyle.equals(array[0]))  // type Price and Promotion
            {
                try
                {
                    for(int count=0;count<imagelist2.size();count++)
                    {
                        imageList.put(count,imagelist2.get(count));
                    }
                    // basic information for store data dorm
                    object.put("dormID",dormID);
                    object.put("minPrice",minPrice);
                    object.put("maxPrice",maxPrice);
                    object.put("typeWater",typeWater);
                    object.put("priceWater",priceWater);
                    object.put("typeElectro",typeElectro);
                    object.put("priceElectro",priceElectro);
                    object.put("typeDeposit",typeDeposit);
                    object.put("depositPrice",depositPrice);
                    object.put("typeCommonFee",typeCommonFee);
                    object.put("commonFee",commonFee);
                    object.put("description",description);
                    object.put("dormStyle",dormStyle);
                    object.put("coverImage",coverImage);
                    object.put("image360",image360);
                    object.put("imageList",imageList);

                    // ******************  send data about price and promotion too
                    JSONObject object2 = new JSONObject();
                    object2.put("dormID",dormID);
                    object2.put("dormStyle",dormStyle);
                    object2.put("promotionDescribe",promotionDescribe);
                    object2.put("oldPrice",oldPrice);
                    object2.put("newPrice",newPrice);


                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    String url2 = "http://"+ip+"/DormTypePriceNPromotion/addDormPriceNPromotion";
                    JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.POST, url2, object2, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    String url3 = "http://"+ip+"/Container/addContainer";
                    JSONObject o = new JSONObject();

                    SharedPreferences sharedPreferences = getSharedPreferences("username_signupList",MODE_PRIVATE);
                    String userN = sharedPreferences.getString("username","no var");

                    o.put("username",userN);
                    o.put("dormID",dormID);

                    JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.POST, url3, o, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    JSONObject json = new JSONObject();
                    JSONArray cleanarray = new JSONArray();
                    JSONArray servicearray = new JSONArray();
                    JSONArray decoratearray = new JSONArray();
                    JSONArray securityarray = new JSONArray();
                    JSONArray commentarray = new JSONArray();

                    int averagescore = 0;
                    json.put("dormID",dormID);
                    json.put("cleanScore",cleanarray);
                    json.put("decorateScore",decoratearray);
                    json.put("securityScore",securityarray);
                    json.put("serviceScore",servicearray);
                    json.put("averageScore",averagescore);
                    json.put("comment",commentarray);

                    String scorecommentpath = "http://"+ip+"/ScoreAndComment/saveComment";
                    JsonObjectRequest commentrequest = new JsonObjectRequest(Request.Method.POST, scorecommentpath, json, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });

                    RequestQueue queue = Volley.newRequestQueue(this);
                    queue.add(request);
                    queue.add(request2);
                    queue.add(request3);
                    queue.add(commentrequest);


                }
                catch(JSONException ex){
                    ex.printStackTrace();
                }



            }
            else if(dormStyle.equals(array[1]))  // type Information
            {
                try {
                    for(int count=0;count<imagelist2.size();count++)
                    {
                        imageList.put(count,imagelist2.get(count));
                    }

                    object.put("dormID",dormID);
                    object.put("minPrice",minPrice);
                    object.put("maxPrice",maxPrice);
                    object.put("typeWater",typeWater);
                    object.put("priceWater",priceWater);
                    object.put("typeElectro",typeElectro);
                    object.put("priceElectro",priceElectro);
                    object.put("typeDeposit",typeDeposit);
                    object.put("depositPrice",depositPrice);
                    object.put("typeCommonFee",typeCommonFee);
                    object.put("commonFee",commonFee);
                    object.put("description",description);
                    object.put("dormStyle",dormStyle);
                    object.put("coverImage",coverImage);
                    object.put("image360",image360);
                    object.put("imageList",imageList);

                    // ******************  send data about price and promotion too
                    JSONObject object2 = new JSONObject();
                    object2.put("dormID",dormID);
                    object2.put("dormStyle",dormStyle);
                    object2.put("detail",information);


                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    String url2 = "http://"+ip+"/DormTypeInformation/addDormTypeInformation";

                    JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.POST, url2, object2, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    String url3 = "http://"+ip+"/Container/addContainer";
                    JSONObject o = new JSONObject();

                    SharedPreferences sharedPreferences = getSharedPreferences("username_signupList",MODE_PRIVATE);
                    String userN = sharedPreferences.getString("username","no var");

                    o.put("username",userN);
                    o.put("dormID",dormID);

                    JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.POST, url3, o, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    JSONObject json = new JSONObject();
                    JSONArray cleanarray = new JSONArray();
                    JSONArray servicearray = new JSONArray();
                    JSONArray decoratearray = new JSONArray();
                    JSONArray securityarray = new JSONArray();
                    JSONArray commentarray = new JSONArray();

                    int averagescore = 0;
                    json.put("dormID",dormID);
                    json.put("cleanScore",cleanarray);
                    json.put("decorateScore",decoratearray);
                    json.put("securityScore",securityarray);
                    json.put("serviceScore",servicearray);
                    json.put("averageScore",averagescore);
                    json.put("comment",commentarray);

                    String scorecommentpath = "http://"+ip+"/ScoreAndComment/saveComment";
                    JsonObjectRequest commentrequest = new JsonObjectRequest(Request.Method.POST, scorecommentpath, json, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });


                    RequestQueue queue = Volley.newRequestQueue(this);
                    queue.add(request);
                    queue.add(request2);
                    queue.add(request3);
                    queue.add(commentrequest);


                }
                catch(JSONException ex){
                    ex.printStackTrace();
                }
            }
            else if(dormStyle.equals(array[2])) // type Quality
            {
                try {
                    for(int count=0;count<imagelist2.size();count++)
                    {
                        imageList.put(count,imagelist2.get(count));
                    }

                    object.put("dormID",dormID);
                    object.put("minPrice",minPrice);
                    object.put("maxPrice",maxPrice);
                    object.put("typeWater",typeWater);
                    object.put("priceWater",priceWater);
                    object.put("typeElectro",typeElectro);
                    object.put("priceElectro",priceElectro);
                    object.put("typeDeposit",typeDeposit);
                    object.put("depositPrice",depositPrice);
                    object.put("typeCommonFee",typeCommonFee);
                    object.put("commonFee",commonFee);
                    object.put("description",description);
                    object.put("dormStyle",dormStyle);
                    object.put("coverImage",coverImage);
                    object.put("image360",image360);
                    object.put("imageList",imageList);

                    // ******************  send data about Quality too
                    JSONObject object2 = new JSONObject();
                    JSONArray arr = new JSONArray();
                    JSONArray th = new JSONArray();
                    JSONArray en = new JSONArray();
                    ArrayList<Premieum> list = getIntent().getParcelableArrayListExtra("premieum");
                    for(int count=0;count<list.size();count++)
                    {
                        arr.put(count,list.get(count).getImage());
                    }

                    for(int count2=0;count2<list.size();count2++)
                    {
                        th.put(count2,list.get(count2).getName_th());
                        en.put(count2,list.get(count2).getName_en());
                    }

                    object2.put("dormID",dormID);
                    object2.put("dormStyle",dormStyle);
                    object2.put("imagelist",arr);
                    object2.put("premieum_th",th);
                    object2.put("premieum_en",en);

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    String url2 = "http://"+ip+"/DormTypeQuality/addDormTypeQuality";
                    JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.POST, url2, object2, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    String url3 = "http://"+ip+"/Container/addContainer";
                    JSONObject o = new JSONObject();

                    SharedPreferences sharedPreferences = getSharedPreferences("username_signupList",MODE_PRIVATE);
                    String userN = sharedPreferences.getString("username","no var");

                    o.put("username",userN);
                    o.put("dormID",dormID);

                    JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.POST, url3, o, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    JSONObject json = new JSONObject();
                    JSONArray cleanarray = new JSONArray();
                    JSONArray servicearray = new JSONArray();
                    JSONArray decoratearray = new JSONArray();
                    JSONArray securityarray = new JSONArray();
                    JSONArray commentarray = new JSONArray();

                    int averagescore = 0;
                    json.put("dormID",dormID);
                    json.put("cleanScore",cleanarray);
                    json.put("decorateScore",decoratearray);
                    json.put("securityScore",securityarray);
                    json.put("serviceScore",servicearray);
                    json.put("averageScore",averagescore);
                    json.put("comment",commentarray);

                    String scorecommentpath = "http://"+ip+"/ScoreAndComment/saveComment";
                    JsonObjectRequest commentrequest = new JsonObjectRequest(Request.Method.POST, scorecommentpath, json, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });


                    RequestQueue queue = Volley.newRequestQueue(this);
                    queue.add(request);
                    queue.add(request2);
                    queue.add(request3);
                    queue.add(commentrequest);


                }
                catch(JSONException ex){
                    ex.printStackTrace();
                }
            }
            else if(dormStyle.equals(array[3]))   // type BrandName
            {
                try {
                    for(int count=0;count<imagelist2.size();count++)
                    {
                        imageList.put(count,imagelist2.get(count));
                    }


                    object.put("dormID",dormID);
                    object.put("minPrice",minPrice);
                    object.put("maxPrice",maxPrice);
                    object.put("typeWater",typeWater);
                    object.put("priceWater",priceWater);
                    object.put("typeElectro",typeElectro);
                    object.put("priceElectro",priceElectro);
                    object.put("typeDeposit",typeDeposit);
                    object.put("depositPrice",depositPrice);
                    object.put("typeCommonFee",typeCommonFee);
                    object.put("commonFee",commonFee);
                    object.put("description",description);
                    object.put("dormStyle",dormStyle);
                    object.put("coverImage",coverImage);
                    object.put("image360",image360);
                    object.put("imageList",imageList);

                    // ******************  send data about BrandName too
                    JSONObject object2 = new JSONObject();
                    object2.put("dormID",dormID);
                    object2.put("dormStyle",dormStyle);
                    object2.put("logo",logo);

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    String url2 = "http://"+ip+"/DormTypeBrandName/addDormTypeBrandName";
                    JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.POST, url2, object2, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    String url3 = "http://"+ip+"/Container/addContainer";
                    JSONObject o = new JSONObject();

                    SharedPreferences sharedPreferences = getSharedPreferences("username_signupList",MODE_PRIVATE);
                    String userN = sharedPreferences.getString("username","no var");

                    o.put("username",dormID);
                    o.put("dormID",userN);

                    JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.POST, url3, o, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    JSONObject json = new JSONObject();
                    JSONArray cleanarray = new JSONArray();
                    JSONArray servicearray = new JSONArray();
                    JSONArray decoratearray = new JSONArray();
                    JSONArray securityarray = new JSONArray();
                    JSONArray commentarray = new JSONArray();

                    int averagescore = 0;
                    json.put("dormID",dormID);
                    json.put("cleanScore",cleanarray);
                    json.put("decorateScore",decoratearray);
                    json.put("securityScore",securityarray);
                    json.put("serviceScore",servicearray);
                    json.put("averageScore",averagescore);
                    json.put("comment",commentarray);

                    String scorecommentpath = "http://"+ip+"/ScoreAndComment/saveComment";
                    JsonObjectRequest commentrequest = new JsonObjectRequest(Request.Method.POST, scorecommentpath, json, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                                System.out.println(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    RequestQueue queue = Volley.newRequestQueue(this);
                    queue.add(request);
                    queue.add(request2);
                    queue.add(request3);
                    queue.add(commentrequest);


                }
                catch(JSONException ex){
                    ex.printStackTrace();
                }
            }



            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.confirm_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }




}
