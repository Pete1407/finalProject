package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class Detail_PriceNPromotiion_Favorite extends AppCompatActivity implements OnMapReadyCallback {
    public ActionBar bar;
    public String dormName;

    public ImageView image360;

    public ListView listview;

    public LinearLayout nearPlace;
    public TextView text;
    public ListViewAdapter adapter;


    public String port = ":8080";
    public String ip = "192.168.43.57";
    public String newServerKey = "AIzaSyAIm2bf5ksXaJTiIVoBHFuwyW4ebWOdLLw";
    public ArrayList<Places> plist;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public ArrayList<Places> list;

    public String pathimage360 = "";
    public TextView dormtitle;
    public TextView sign_favorite;
    public int numberofFavorite = 0;

    public LinearLayout offerPromotion;
    public LinearLayout sale;

    public ImageView heart;
    public boolean statusPress = false;

    public TextView roomprice;
    public TextView waterprice;
    public TextView electroprice;
    public TextView depositPrice;
    public TextView commonfeeprice;
    public TextView describedetail;
    public TextView address;
    public TextView detail_offer;
    public TextView saveMoney;
    public TextView distance;

    public LinearLayout sale_saveMoney;
    public ImageView piggy;
    public LinearLayout sale_piggy;

    public GoogleMap mMap;
    public SupportMapFragment mapfgr;


    Faculty scienceAndTechnology;
    Faculty law;
    Faculty politicalScience;
    Faculty account;
    Faculty liberalArt;
    Faculty architect;
    Faculty economy;
    Faculty social;
    Faculty jouranlism;
    Faculty humanism;
    Faculty engineer;
    Faculty medicine;
    Faculty alliedHealth;
    Faculty dentistry;
    Faculty nurse;
    Faculty fineArt;
    Faculty publicHealth;
    Faculty pharmacy;
    Faculty studyAndknowledge;
    Faculty siit;
    Faculty puey;

    Places lc1;
    Places lc2;
    Places lc3;
    Places lc4;
    Places lc5;
    Places science_Canteen;
    Places pueyLibrary ;
    Places learningBuilding ;
    Places vanPort ;
    Places regtu ;

    // Social Science
    Places sc ;
    Places greenCanteen ;
    Places scCanteen1 ;
    Places scCanteen2;
    Faculty faculty;
    public String facultyOfUser;
    public Faculty facofuser;

    public TextView timeWalking;
    public TextView timeDriving;
    public TextView timeTransit;


    public TextView datanamedormowner;
    public TextView datacontactdormowner;
    public TextView dataemaildormowner;
    public TextView dataaddressdormowner;
    public TextView descriptionDetail;
    public ImageView docImage;
    public String pathDocument;

    public LinearLayout galleryLinear;
    public  String pressFavorite;
    public String username;
    public String travel;
    public SharedPreferences storeFavoriteDorm;
    public SharedPreferences.Editor storeFavoriteDorm_editor;

    public LinearLayout driving;
    public LinearLayout transit;

    public TextView drivingTitle;
    public TextView transitTitle;

    public Favorite favorite;
    public ArrayList<String> likelist;
    public int codeStyle;
    public int numberofLike = 0;
    public ArrayList<String> stack = new ArrayList<>();
    public int summaryLikeDorm = 0;
    public ArrayList<String> favlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__dorm__price_and_promotion);

        this.likelist = new ArrayList<>();
        this.plist = new ArrayList<>();
        this.list = new ArrayList<>();
        this.setFaculty();


        this.drivingTitle = (TextView) findViewById(R.id.textView73);
        this.transitTitle = (TextView) findViewById(R.id.textView75);
        this.driving = (LinearLayout) findViewById(R.id.driving);
        this.transit = (LinearLayout) findViewById(R.id.transit);
        this.galleryLinear = (LinearLayout) findViewById(R.id.gallery);
        this.datanamedormowner = (TextView) findViewById(R.id.datanamedormowner);
        this.datacontactdormowner = (TextView) findViewById(R.id.datacontactdormowner);
        this.dataemaildormowner = (TextView) findViewById(R.id.dataemaildormowner);
        this.dataaddressdormowner = (TextView) findViewById(R.id.dataaddressdormowner);
        this.docImage = (ImageView) findViewById(R.id.docImage);

        this.image360 = (ImageView) findViewById(R.id.image360);
        this.nearPlace = (LinearLayout) findViewById(R.id.nearPalce);
        this.dormtitle = (TextView) findViewById(R.id.dormName);
        this.sign_favorite = (TextView) findViewById(R.id.sign_favorite);
        this.offerPromotion = (LinearLayout) findViewById(R.id.offer_promotion);
        this.sale = (LinearLayout) findViewById(R.id.sale);
        this.heart = (ImageView) findViewById(R.id.heart);
        this.roomprice = (TextView) findViewById(R.id.roomprice);
        this.waterprice = (TextView) findViewById(R.id.waterprice);
        this.electroprice = (TextView) findViewById(R.id.electroprice);
        this.depositPrice = (TextView) findViewById(R.id.depositprice);
        this.commonfeeprice = (TextView) findViewById(R.id.commonfeeprice);
        this.describedetail = (TextView) findViewById(R.id.describedetail);
        this.detail_offer = (TextView) findViewById(R.id.detail_offer);
        this.saveMoney = (TextView) findViewById(R.id.saveMoney);
        this.sale_piggy = (LinearLayout) findViewById(R.id.sale_piggy);
        this.piggy = (ImageView) findViewById(R.id.piggy);
        this.sale_saveMoney = (LinearLayout) findViewById(R.id.sale_saveMoney);
        this.address = (TextView) findViewById(R.id.address);
        this.distance = (TextView) findViewById(R.id.distance);
        this.timeWalking = (TextView) findViewById(R.id.timeWalking);
        this.timeDriving = (TextView) findViewById(R.id.timeDriving);
        this.timeTransit = (TextView) findViewById(R.id.timeTransit);


        // can get name dorm
        //dormName = getIntent().getStringExtra("dormName");
        sharedPreferences = getSharedPreferences("selectDorm",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        this.dormName = sharedPreferences.getString("dormname","no value");

        // เซ็ตค่าจำนวนคนที่กดถูกใจหอพักนี้
        this.setcountFavorite(dormName);


        stack = new ArrayList<>();



        this.bar = getSupportActionBar();
        this.bar.setTitle(dormName);
        this.bar.setDisplayHomeAsUpEnabled(true);
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));

        this.dormtitle.setText(dormName);
        int black = getResources().getColor(R.color.black);
        int red = getResources().getColor(R.color.red);
        pressFavorite = getResources().getString(R.string.pressFavorite);
        this.dormtitle.setTextColor(black);
        this.dormtitle.setTypeface(Typeface.DEFAULT_BOLD);
        this.dormtitle.setTextSize(18);
        this.sign_favorite.setText(summaryLikeDorm+" "+pressFavorite);
        this.sign_favorite.setTextColor(red);

        this.getImage360(dormName);

        String[] array = getResources().getStringArray(R.array.faculty);

        // can get username
        sharedPreferences = getSharedPreferences("file_pref", Context.MODE_PRIVATE);
        boolean f = sharedPreferences.getBoolean("statusSearch",false);
        codeStyle = sharedPreferences.getInt("dormStyleCode",4);

        if(f == true)
        {
            // รับมาจาก SearchMap_Fragment ในการค้นหาส่วนของ map
            this.dormName = getIntent().getStringExtra("dormName");
            boolean a = false;
            this.dormtitle.setText(dormName);
            this.bar.setTitle(dormName);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("statusSearch",a);
            editor.commit();

            travel = getIntent().getStringExtra("travel");
            // รับมาจาก SearchMap_Fragment ในการค้นหาส่วนของ map
            if(travel.equals(getResources().getString(R.string.driving)))
            {
                this.driving.setBackground(getResources().getDrawable(R.color.gray));
                this.drivingTitle.setTextColor(getResources().getColor(android.R.color.white));
                this.drivingTitle.setTypeface(Typeface.DEFAULT_BOLD);

            }
            else if(travel.equals(getResources().getString(R.string.transit)))
            {
                this.transit.setBackground(getResources().getDrawable(R.color.gray));
                this.transitTitle.setTextColor(getResources().getColor(android.R.color.white));
                this.transitTitle.setTypeface(Typeface.DEFAULT_BOLD);

            }
        }
        String fac = sharedPreferences.getString("faculty","no faculty");
        username = sharedPreferences.getString("username","no value");
        this.setFavorite(username);
        facultyOfUser = fac;

        if(facultyOfUser.equals(array[1]))
        {
            faculty = scienceAndTechnology;
        }
        else if(facultyOfUser.equals(array[2]))
        {
            faculty = law;
        }
        else if(facultyOfUser.equals(array[3]))
        {
            faculty = politicalScience;
        }
        else if(facultyOfUser.equals(array[4]))
        {
            faculty = account;
        }
        else if(facultyOfUser.equals(array[5]))
        {
            faculty = liberalArt;
        }
        else if(facultyOfUser.equals(array[6]))
        {
            faculty = architect;
        }
        else if(facultyOfUser.equals(array[7]))
        {
            faculty = economy;
        }
        else if(facultyOfUser.equals(array[8]))
        {
            faculty = social;
        }
        else if(facultyOfUser.equals(array[9]))
        {
            faculty = jouranlism;
        }
        else if(facultyOfUser.equals(array[10]))
        {
            faculty = humanism;
        }
        else if(facultyOfUser.equals(array[11]))
        {
            faculty = engineer;
        }
        else if(facultyOfUser.equals(array[12]))
        {
            faculty = medicine;
        }
        else if(facultyOfUser.equals(array[13]))
        {
            faculty = alliedHealth;
        }
        else if(facultyOfUser.equals(array[14]))
        {
            faculty = dentistry;
        }
        else if(facultyOfUser.equals(array[15]))
        {
            faculty = nurse;
        }
        else if(facultyOfUser.equals(array[16]))
        {
            faculty = fineArt;
        }
        else if(facultyOfUser.equals(array[17]))
        {
            faculty = publicHealth;
        }
        else if(facultyOfUser.equals(array[18]))
        {
            faculty = pharmacy;
        }
        else if(facultyOfUser.equals(array[19]))
        {
            faculty = studyAndknowledge;
        }
        else if(facultyOfUser.equals(array[20]))
        {
            faculty = siit;
        }
        else if(facultyOfUser.equals(array[21]))
        {
            faculty = puey;
        }


        this.image360.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormName;
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try
                        {
                            String username = response.getString("username");
                            String email = response.getString("email");

                            String getImage360 = "http://"+ip+port+"/Image360/getImage360byUsernameAndEmail/"+username+"/"+email;
                            JsonObjectRequest image360 = new JsonObjectRequest(Request.Method.GET, getImage360, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try
                                    {
                                        String image = response.getString("image360");
                                        System.out.println("Path Image360: "+image);
                                        pathimage360 = image;
                                        Intent i = new Intent(Detail_PriceNPromotiion_Favorite.this, FullScreen_Image360.class);
                                        i.putExtra("imagePath",pathimage360);
                                        startActivity(i);

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
                            queue.add(image360);
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


                queue.add(request);
            }

        });

        this.getExpense(dormName);
        this.getPositionDorm(dormName);
        this.showContact(dormName);

        this.mapfgr = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        this.mapfgr.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        this.mMap.getUiSettings().setZoomGesturesEnabled(false);
        this.mMap.getUiSettings().setZoomControlsEnabled(false);
        this.mMap.getUiSettings().setAllGesturesEnabled(false);
    }

    public void setFavorite(String username){
        String path = "http://"+ip+port+"/favorite/checkNumberofFavoriteByUsername/"+username;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject jsonObject = response;
                try
                {
                    JSONArray array = jsonObject.getJSONArray("dormname");
                    // สำหรับผู้ใช้ที่ไม่ได้กดถูกใจหอพักเลยสักที่
                    if(array.length() == 0)
                    {
                        System.out.println("คุณยังไม่ได้กดถูกใจหอพักเลยสักหอเดียว");

                    }
                    // สำหรับผู้ใช้ที่กดถูกใจหอพักแล้วมากกว่าเท่ากับ 1 หอ
                    else
                    {
                        for(int count=0;count<array.length();count++)
                        {
                            stack.add(array.getString(count));
                            favlist.add(array.getString(count));
                        }

                        for(int count2=0;count2<favlist.size();count2++)
                        {
                            System.out.println("หอพักที่ชื่นชอบ: "+favlist.get(count2));
                        }
                    }
                    int value = 0;
                    for(int i=0;i<favlist.size();i++)
                    {
                        if(favlist.get(i).equals(dormName))
                        {
                            value++;
                            break;
                        }
                    }
                    if(value!=0)
                    {
                        heart.setImageResource(R.drawable.heart_press2);
                        statusPress = true;
                    }
                    else
                    {
                        heart.setImageResource(R.drawable.heart_notpress);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(jsonObject);

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void getPositionDorm(String dormName){
        String path = "http://"+ip+port+"/dorm/getDormByDormName/"+dormName;
        final RequestQueue q = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String addressText = response.getString("address");
                    final double latitude = response.getDouble("latitude");
                    final double longtitude = response.getDouble("longtitude");
                    address.setText(addressText);

                    final int height = 120;
                    final int width = 120;
                    BitmapDrawable bitmapdrawable2 = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_place_modify);
                    Bitmap b2 = bitmapdrawable2.getBitmap();
                    Bitmap smallMarker2 = Bitmap.createScaledBitmap(b2, width, height, false);

                    MarkerOptions dorm = new MarkerOptions().position(new LatLng(latitude,longtitude)).icon(BitmapDescriptorFactory.fromBitmap(smallMarker2));
                    mMap.addMarker(dorm);

                    CameraPosition position = CameraPosition.builder()
                            .target(new LatLng(latitude,longtitude))
                            .zoom(15)
                            .build();

                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position),1000,null);


                    String urlDirection = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+faculty.getLatitude()+","+faculty.getLongtitude()+"&key="+newServerKey;
                    JsonObjectRequest req2 = new JsonObjectRequest(Request.Method.GET, urlDirection, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONObject data = response;
                            JSONArray route = null;
                            try
                            {
                                route = data.getJSONArray("routes");
                                JSONObject routes = route.getJSONObject(0);
                                JSONArray legs = routes.getJSONArray("legs");
                                JSONObject legss = legs.getJSONObject(0);
                                JSONObject distancee = legss.getJSONObject("distance");
                                JSONObject duration = legss.getJSONObject("duration");
                                final String distance_text = distancee.getString("text");
                                final String duration_text = duration.getString("text");
                                distance.setText(distance_text+" จากหอพักไป"+faculty.getName_faculty()+"\n"+"ใช้เวลา "+duration_text);

                                String walkingPath = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+faculty.getLatitude()+","+faculty.getLongtitude()+"&mode=walking&key="+newServerKey;
                                JsonObjectRequest getd = new JsonObjectRequest(Request.Method.GET,walkingPath, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        System.out.println(response);
                                        try {
                                            JSONArray array = response.getJSONArray("routes");
                                            JSONObject routes = array.getJSONObject(0);
                                            JSONArray legs = routes.getJSONArray("legs");
                                            JSONObject legss = legs.getJSONObject(0);
                                            JSONObject distance = legss.getJSONObject("distance");
                                            JSONObject duration = legss.getJSONObject("duration");
                                            final String distance_text = distance.getString("text");
                                            final String duration_text = duration.getString("text");
                                            timeWalking.setText(duration_text);
                                            int red = getResources().getColor(R.color.red);
                                            timeWalking.setTextColor(red);

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
                                q.add(getd);

                                String urlDirection2 = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+faculty.getLatitude()+","+faculty.getLongtitude()+"&mode=driving&key="+newServerKey;
                                JsonObjectRequest getd2 = new JsonObjectRequest(Request.Method.GET, urlDirection2, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        System.out.println(response);
                                        try {
                                            JSONArray array = response.getJSONArray("routes");
                                            JSONObject routes = array.getJSONObject(0);
                                            JSONArray legs = routes.getJSONArray("legs");
                                            JSONObject legss = legs.getJSONObject(0);
                                            JSONObject distance = legss.getJSONObject("distance");
                                            JSONObject duration = legss.getJSONObject("duration");
                                            final String distance_text = distance.getString("text");
                                            final String duration_text = duration.getString("text");
                                            timeDriving.setText(duration_text);
                                            int red = getResources().getColor(R.color.red);
                                            timeDriving.setTextColor(red);
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

                                q.add(getd2);

                                String urlDirection3 = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+faculty.getLatitude()+","+faculty.getLongtitude()+"&mode=transit&key="+newServerKey;
                                JsonObjectRequest getd3 = new JsonObjectRequest(Request.Method.GET, urlDirection3, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        System.out.println(response);
                                        try {
                                            JSONArray array = response.getJSONArray("routes");
                                            JSONObject routes = array.getJSONObject(0);
                                            JSONArray legs = routes.getJSONArray("legs");
                                            JSONObject legss = legs.getJSONObject(0);
                                            JSONObject distance = legss.getJSONObject("distance");
                                            JSONObject duration = legss.getJSONObject("duration");
                                            final String distance_text = distance.getString("text");
                                            final String duration_text = duration.getString("text");

                                            timeTransit.setText(duration_text);
                                            int red = getResources().getColor(R.color.red);
                                            timeTransit.setTextColor(red);
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

                                q.add(getd3);

                            }catch (JSONException ex){
                                ex.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    q.add(req2);


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

        q.add(request);
    }

    public void showPlace(View v){
        Intent i = new Intent(Detail_PriceNPromotiion_Favorite.this,ImportancePlace_PriceandPromotion_Style.class);
        startActivity(i);
    }

    public void showFacility(View v){
        Intent i = new Intent(Detail_PriceNPromotiion_Favorite.this,Facility_PricePromotion_Style.class);
        startActivity(i);
    }

    public void getImage360(String dormName){
        final RequestQueue q = Volley.newRequestQueue(this);

        String path = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String username = response.getString("username");
                    String email = response.getString("email");

                    String path2 = "http://"+ip+port+"/Image360/getImage360byUsernameAndEmail/"+username+"/"+email;

                    JsonObjectRequest req2 = new JsonObjectRequest(Request.Method.GET, path2, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String image360t = response.getString("image360");
                                //editor.putString("pathImage360",image360t);
                                //editor.commit();
                                //Picasso.with(getApplicationContext()).load(image360t).into(image360);
                                Glide.with(getApplicationContext()).load(image360t).apply(bitmapTransform(new BlurTransformation(22))).skipMemoryCache(true).into(image360);
                            }
                            catch(JSONException ex){
                                ex.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    q.add(req2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        q.add(request);
    }

    public void pressFavorite(View v){
        // if อันนี้จะเช็คว่าถ้ามีหอพักที่เราไม่เคยกดถูกใจจะสามารถเข้า if นี้ได้ และกดถูกใจหอพักนี้ได้ หัวใจก็จะสามารถกดได้  if(suspenclick == false ) กับ if(statusPress == false)
        if(statusPress == false)
        {
            summaryLikeDorm = summaryLikeDorm + 1;
            statusPress = true;
            this.sign_favorite.setText(summaryLikeDorm+" "+pressFavorite);
            int red = getResources().getColor(R.color.red);
            this.sign_favorite.setTextColor(red);
            heart.setImageResource(R.drawable.heart_press2);
            // ฟังก์ชันสำหรับการเก็บชื่อหอพักที่ชื่นชอบ

            this.addFavorite(dormName);
        }
        else
        {
            summaryLikeDorm = summaryLikeDorm - 1;
            this.sign_favorite.setText(summaryLikeDorm+" "+pressFavorite);
            int red = getResources().getColor(R.color.red);
            this.sign_favorite.setTextColor(red);
            statusPress = false;
            heart.setImageResource(R.drawable.heart_notpress);
            this.cancelFavorite(dormName);
        }
    }

    public void setcountFavorite(final String dormName){
        RequestQueue queue = Volley.newRequestQueue(this);
        String path = "http://"+ip+port+"/favorite/getAllFavorite";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, path, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray array = response;
                // เซ็ตค่าเพื่อไปแยกว่าหอไหนมีผู้ใช้แต่ละคนกดถูกใจกี่คน
                displayArray(response,dormName);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

    public void addFavorite(String dormName){
        RequestQueue queue = Volley.newRequestQueue(this);

        stack.add(dormName);
        String saveFavorite = "http://"+ip+port+"/favorite/saveFavoriteDorm";
        JSONObject jsonobject = new JSONObject();
        JSONArray array = new JSONArray();
        try
        {
            jsonobject.put("username",username);
            jsonobject.put("codeStyle",codeStyle);

            // ยัด arraylist ที่เก็บชื่อของหอพักที่ผู้ใช้กดถูกใจใส่เพื่ออัพเดตลงฐานข้อมูล
            for(int count=0;count<stack.size();count++)
            {
                array.put(count,stack.get(count));
            }
            jsonobject.put("dormname",array);
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, saveFavorite, jsonobject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(req);

        }
        catch (JSONException ex){
            ex.printStackTrace();
        }

    }

    public void cancelFavorite(String dormName){
        RequestQueue queue = Volley.newRequestQueue(this);
        for(int count=0;count<stack.size();count++)
        {
            if(dormName.equals(stack.get(count)))
            {
                stack.remove(count);
            }
        }

        String saveFavorite = "http://"+ip+port+"/favorite/saveFavoriteDorm";
        JSONObject object = new JSONObject();
        try {
            object.put("username",username);
            object.put("codeStyle",codeStyle);
            JSONArray array = new JSONArray();
            for(int j=0;j<stack.size();j++)
            {
                array.put(j,stack.get(j));
            }
            object.put("dormname",array);

            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, saveFavorite, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(req);
        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
    }

    public void displayArray(JSONArray array,String dormName){
        JSONArray data = array;
        try
        {
            for(int count=0;count<data.length();count++)
            {
                JSONObject object = data.getJSONObject(count);
                JSONArray dormname = object.getJSONArray("dormname");
                for(int count2=0;count2<dormname.length();count2++)
                {
                    if(dormName.equals(dormname.getString(count2)))
                    {
                        numberofLike++;
                    }
                }
            }
            sign_favorite.setText(numberofLike+" "+pressFavorite);
            summaryLikeDorm = numberofLike;
        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
    }



    public void getGallery(View v){
        Intent i = new Intent(Detail_PriceNPromotiion_Favorite.this,Gallery_PricePromotion.class);
        startActivity(i);
    }

    public void getExpense(String dormname){
        RequestQueue q = Volley.newRequestQueue(this);
        String expensePath = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dormname;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, expensePath, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject res) {
                try
                {
                    String baht = getResources().getString(R.string.baht);
                    int minPriceN = res.getInt("minPrice");
                    int waterPriceN = res.getInt("priceWater");
                    int electroPriceN = res.getInt("priceElectro");
                    int depositPriceN = res.getInt("depositPrice");
                    int commonfeeN = res.getInt("commonFee");
                    String typeWater = res.getString("typeWater");
                    String typeElectro = res.getString("typeElectro");
                    String description = res.getString("description");
                    waterprice.setText(String.valueOf(waterPriceN)+" "+baht+"("+typeWater+")");
                    electroprice.setText(String.valueOf(electroPriceN)+" "+baht+"("+typeElectro+")");
                    describedetail.setText(description);
                    String a = getResources().getString(R.string.free);
                    if(depositPriceN == 0)
                    {
                        depositPrice.setText(a);
                    }
                    else
                    {
                        depositPrice.setText(String.valueOf(depositPriceN)+" "+baht);

                    }

                    if(commonfeeN == 0)
                    {
                        commonfeeprice.setText(a);
                    }
                    else
                    {
                        commonfeeprice.setText(String.valueOf(commonfeeN)+" "+baht);
                    }
                    String path = "http://"+ip+port+"/DormTypePriceNPromotion/getDormBydormName/"+dormName;
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String promotionDetail = response.getString("promotionDescribe");
                                if(!promotionDetail.equals(""))
                                {
                                    int oldP = response.getInt("oldPrice");
                                    int newP = response.getInt("newPrice");
                                    int savemoney = oldP - newP;
                                    detail_offer.setText(promotionDetail);
                                    detail_offer.setTextSize(17);

                                    saveMoney.setText("ประหยัด "+String.valueOf(savemoney)+" บาท");
                                    saveMoney.setTextSize(17);
                                    roomprice.setText(String.valueOf(newP)+" "+baht);
                                    System.out.println(savemoney);
                                }
                                else if(promotionDetail.equals(""))
                                {
                                    roomprice.setText(String.valueOf(minPriceN)+" "+baht);
                                    offerPromotion.setVisibility(View.GONE);
                                    sale.setVisibility(View.GONE);
                                    sale_piggy.setVisibility(View.GONE);
                                    sale_saveMoney.setVisibility(View.GONE);
                                    piggy.setVisibility(View.GONE);
                                }

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
                    q.add(req);




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


        q.add(request);
    }

    public void  showContact(final String dormName){
        final RequestQueue q = Volley.newRequestQueue(this);
        String path = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String firstname = response.getString("name");
                    String lastname = response.getString("lastname");
                    String fullanme = firstname +" "+lastname;
                    String contact = response.getString("contact");
                    String email = response.getString("email");
                    String username = response.getString("username");
                    datanamedormowner.setText(fullanme);
                    datacontactdormowner.setText(contact);
                    dataemaildormowner.setText(email);

                    String pathaddress = "http://"+ip+port+"/dorm/getDormByDormName/"+dormName;
                    JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, pathaddress, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String address = response.getString("address");
                                dataaddressdormowner.setText(address);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    q.add(request2);

                    String getDocument = "http://"+ip+port+"/documentImage/getpathImageByEmailAndUsername/"+email+"/"+username;
                    StringRequest docrequest = new StringRequest(Request.Method.GET, getDocument, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            pathDocument = response;
                            Picasso.with(getApplicationContext()).load(pathDocument).into(docImage);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    q.add(docrequest);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        q.add(request);
    }

    public void commentAndScoreActivity(View v){
        Intent i = new Intent(Detail_PriceNPromotiion_Favorite.this,CommentandScore_PricePromotion_Favorite.class);
        startActivity(i);
    }

    public void showImage(View v){
        Intent i = new Intent(Detail_PriceNPromotiion_Favorite.this,Fullscreen_Image.class);
        i.putExtra("pathImage",pathDocument);
        startActivity(i);

    }



    public void setFaculty(){
        scienceAndTechnology = new Faculty(0,"คณะวิทยาศาสตร์และเทคโนโลยี",14.073699, 100.607895);
        law = new Faculty(1,"คณะนิติศาสตร์",14.068288, 100.603120);
        politicalScience = new Faculty(2,"คณะรัฐศาสตร์",14.068114, 100.602777);
        account = new Faculty(3,"คณะพาณิชยศาสตร์และการบัญชี",14.068130, 100.603528);
        liberalArt = new Faculty(4,"คณะศิลปศาสตร์",14.069126, 100.602117);
        architect = new Faculty(5,"คณะสถาปัตยกรรมศาสตร์และการผังเมือง",14.068139, 100.608762);
        economy = new Faculty(6,"คณะเศรษฐศาสตร์",14.067999, 100.603956);
        social = new Faculty(7,"คณะสังคมสงเคราะห์ศาสตร์",14.068467, 100.602751);
        jouranlism = new Faculty(8,"คณะวารสารศาสตร์และสื่อสารมวลชน",14.068779, 100.604339);
        humanism = new Faculty(9,"คณะสังคมวิทยาและมานุษยวิทยา",14.068909, 100.602429);
        engineer = new Faculty(10,"คณะวิศวกรรมศาสตร์",14.069040, 100.605938);
        medicine = new Faculty(11,"คณะแพทยศาสตร์",14.072729, 100.613775);
        alliedHealth = new Faculty(12,"คณะสหเวชศาสตร์",14.072862, 100.612964);
        dentistry = new Faculty(13,"คณะทันตแพทยศาสตร์",14.072827, 100.612697);
        nurse = new Faculty(14,"คณะพยาบาลศาสตร์",14.072674, 100.612600);
        fineArt = new Faculty(15,"คณะศิลปกรรมศาสตร์",14.068832, 100.604663);
        publicHealth = new Faculty(16,"คณะสาธารณสุขศาสตร์",14.072812, 100.612667);
        pharmacy = new Faculty(17,"คณะเภสัชศาสตร์",14.072467, 100.611625);
        studyAndknowledge = new Faculty(18,"คณะวิทยาการเรียนรู้และศึกษาศาสตร์",14.071860, 100.604163);
        siit = new Faculty(19,"สถาบันเทคโนโลยีนานาชาติสิรินธร",14.068973, 100.607441);
        puey = new Faculty(20,"วิทยาลัยพัฒนศาสตร์ ป๋วยอึ้งภากรณ์",14.077784, 100.599844);

        lc1 = new Places("อาคารบรรยายรวม 1","Lecture Classroom 1",14.072651, 100.602225);
        lc2 = new Places("อาคารบรรยายรวม 2","Lecture Classroom 2",14.073599, 100.606278);
        lc3=  new Places("อาคารบรรยายรวม 3","Lecture Classroom 3",14.072652, 100.606259);
        lc4 = new Places("อาคารบรรยายรวม 4","Lecture Classroom 4",14.072634, 100.608040);
        lc5 = new Places("อาคารบรรยายรวม 5","Lecture Classroom 5",14.074293, 100.607891);
        science_Canteen = new Places("โรงอาหารคณะวิทยาศาสตร์","Food Centre",14.072657, 100.608617);


        pueyLibrary = new Places("หอสมุดป๋วย อึ้งภากรณ์","Puey Ungpakorn Library",14.071449, 100.602296);
        learningBuilding = new Places("อาคารศูนย์การเรียนรู้","Learning Centre",14.071514, 100.602318);
        vanPort = new Places("ท่ารถตู้","Van Station",14.067388, 100.608116);
        regtu = new Places("สำนักทะเบียน","Office of the registra",14.070756, 100.602336);

        // Social Science
        sc = new Places("อาคารเรียนรวมสังคมศาสตร์","SC Building",14.069580, 100.603108);
        greenCanteen = new Places("โรงอาหารกรีน","Green Canteen",14.073456, 100.601129);
        scCanteen1 = new Places("ศุนย์อาหาร SC1/JC","SC1/JC Canteen",14.069249, 100.604760);
        scCanteen2 = new Places("ศูนย์อหาร SC2","SC2 Canteen",14.069697, 100.604672);


        // SCIENCE AND TECHNOLOGY
        scienceAndTechnology.addPlaces(lc1);
        scienceAndTechnology.addPlaces(lc2);
        scienceAndTechnology.addPlaces(lc3);
        scienceAndTechnology.addPlaces(lc4);
        scienceAndTechnology.addPlaces(lc5);
        scienceAndTechnology.addPlaces(pueyLibrary);
        scienceAndTechnology.addPlaces(learningBuilding);
        scienceAndTechnology.addPlaces(science_Canteen);


        // LAW
        law.addPlaces(pueyLibrary);
        law.addPlaces(learningBuilding);
        law.addPlaces(vanPort);
        law.addPlaces(sc);
        law.addPlaces(greenCanteen);
        law.addPlaces(scCanteen1);
        law.addPlaces(scCanteen2);

        // POLITICAL
        politicalScience.addPlaces(pueyLibrary);
        politicalScience.addPlaces(learningBuilding);
        politicalScience.addPlaces(vanPort);
        politicalScience.addPlaces(sc);
        politicalScience.addPlaces(greenCanteen);
        politicalScience.addPlaces(scCanteen1);
        politicalScience.addPlaces(scCanteen2);

        // ACCOUNT
        account.addPlaces(pueyLibrary);
        account.addPlaces(learningBuilding);
        account.addPlaces(vanPort);
        account.addPlaces(sc);
        account.addPlaces(greenCanteen);
        account.addPlaces(scCanteen1);
        account.addPlaces(scCanteen2);

        // LIBERRAL ART
        liberalArt.addPlaces(pueyLibrary);
        liberalArt.addPlaces(learningBuilding);
        liberalArt.addPlaces(vanPort);
        liberalArt.addPlaces(sc);
        liberalArt.addPlaces(greenCanteen);
        liberalArt.addPlaces(scCanteen1);
        liberalArt.addPlaces(scCanteen2);

        // ARCHITECT
        architect.addPlaces(pueyLibrary);
        architect.addPlaces(learningBuilding);
        architect.addPlaces(vanPort);



        //ECONOMY
        economy.addPlaces(pueyLibrary);
        economy.addPlaces(learningBuilding);
        economy.addPlaces(vanPort);
        economy.addPlaces(sc);
        economy.addPlaces(greenCanteen);
        economy.addPlaces(scCanteen1);
        economy.addPlaces(scCanteen2);

        // SOCIAL
        social.addPlaces(pueyLibrary);
        social.addPlaces(learningBuilding);
        social.addPlaces(vanPort);
        social.addPlaces(sc);
        social.addPlaces(greenCanteen);
        social.addPlaces(scCanteen1);
        social.addPlaces(scCanteen2);


        // JOURNALISM
        jouranlism.addPlaces(pueyLibrary);
        jouranlism.addPlaces(learningBuilding);
        jouranlism.addPlaces(vanPort);
        jouranlism.addPlaces(sc);
        jouranlism.addPlaces(greenCanteen);
        jouranlism.addPlaces(scCanteen1);
        jouranlism.addPlaces(scCanteen2);

        // HUMANISM
        humanism.addPlaces(pueyLibrary);
        humanism.addPlaces(learningBuilding);
        humanism.addPlaces(vanPort);
        humanism.addPlaces(sc);
        humanism.addPlaces(greenCanteen);
        humanism.addPlaces(scCanteen1);
        humanism.addPlaces(scCanteen2);

        // ENGINEER
        engineer.addPlaces(pueyLibrary);
        engineer.addPlaces(learningBuilding);
        engineer.addPlaces(vanPort);
        engineer.addPlaces(greenCanteen);
        engineer.addPlaces(scCanteen1);
        engineer.addPlaces(scCanteen2);

        // MEDICINE
        medicine.addPlaces(pueyLibrary);
        medicine.addPlaces(learningBuilding);
        medicine.addPlaces(vanPort);
        medicine.addPlaces(greenCanteen);


        // ALLIED HEALTH
        alliedHealth.addPlaces(pueyLibrary);
        alliedHealth.addPlaces(learningBuilding);
        alliedHealth.addPlaces(vanPort);
        alliedHealth.addPlaces(greenCanteen);

        // DENTISTRY
        dentistry.addPlaces(pueyLibrary);
        dentistry.addPlaces(learningBuilding);
        dentistry.addPlaces(vanPort);
        dentistry.addPlaces(greenCanteen);
        dentistry.addPlaces(scCanteen1);
        dentistry.addPlaces(scCanteen2);

        // NURSE
        nurse.addPlaces(pueyLibrary);
        nurse.addPlaces(learningBuilding);
        nurse.addPlaces(vanPort);


        // FINE ART
        fineArt.addPlaces(pueyLibrary);
        fineArt.addPlaces(learningBuilding);
        fineArt.addPlaces(vanPort);
        fineArt.addPlaces(sc);
        fineArt.addPlaces(greenCanteen);
        fineArt.addPlaces(scCanteen1);
        fineArt.addPlaces(scCanteen2);

        // PUBLIC HEALTH
        publicHealth.addPlaces(pueyLibrary);
        publicHealth.addPlaces(learningBuilding);
        publicHealth.addPlaces(vanPort);


        // PHARMACY
        pharmacy.addPlaces(pueyLibrary);
        pharmacy.addPlaces(learningBuilding);
        pharmacy.addPlaces(vanPort);


        // STUDY AND KNOWLEDGE
        studyAndknowledge.addPlaces(pueyLibrary);
        studyAndknowledge.addPlaces(learningBuilding);
        studyAndknowledge.addPlaces(vanPort);
        studyAndknowledge.addPlaces(sc);
        studyAndknowledge.addPlaces(greenCanteen);
        studyAndknowledge.addPlaces(scCanteen1);
        studyAndknowledge.addPlaces(scCanteen2);

        // SIIT
        siit.addPlaces(pueyLibrary);
        siit.addPlaces(learningBuilding);
        siit.addPlaces(vanPort);
        siit.addPlaces(sc);
        siit.addPlaces(greenCanteen);


        // PUEY
        puey.addPlaces(pueyLibrary);
        puey.addPlaces(learningBuilding);
        puey.addPlaces(vanPort);

    }
}
