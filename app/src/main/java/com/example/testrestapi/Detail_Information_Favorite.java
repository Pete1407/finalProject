package com.example.testrestapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightGridView;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hsalf.smilerating.SmileRating;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.StringTokenizer;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class Detail_Information_Favorite extends AppCompatActivity implements OnMapReadyCallback {
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

    public String dormName;
    public ActionBar bar;
    public String port = ":8080";
    public String ip = "192.168.43.57";
    public String newServerKey = "AIzaSyAIm2bf5ksXaJTiIVoBHFuwyW4ebWOdLLw";

    public GoogleMap mMap;
    public SupportMapFragment mapfgr;


    public ImageView heart;
    public boolean statusPress = false;
    public ImageView image360;



    public TextView roomprice;
    public TextView waterprice;
    public TextView electroprice;
    public TextView depositPrice;
    public TextView commonfeeprice;
    public TextView describedetail;
    public TextView address;

    public TextView distance;

    public TextView datanamedormowner;
    public TextView datacontactdormowner;
    public TextView dataemaildormowner;
    public TextView dataaddressdormowner;
    public ImageView docImage;
    public String pathDocument;

    public ArrayList<Places> plist;
    public ArrayList<Places> list;

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public String pathimage360;

    public TextView nameTextView;
    public TextView signFavorite;
    public int numberofFavorite = 0;

    public LatLng mydorm;
    public ImageView arrow;
    public TextView timeWalking;
    public TextView timeDriving;
    public TextView timeTransit;

    public ExpandableHeightListView moreinfo;
    public ExpandableHeightListView nearPlaceList;
    public ExpandableHeightListView listview;
    public ArrayList<Facility> faclist;
    public ExpandableHeightGridView factext;

    public String travel;
    public LinearLayout driving;
    public LinearLayout transit;
    public TextView drivingTitle;
    public TextView transitTitle;

    public ArrayList<String> stack = new ArrayList<>();
    public int codeStyle;
    public String username;
    public int summaryLikeDorm = 0;
    public String ff;
    public int numberofLike = 0;


    public TextView signcleanScore;
    public TextView signserviceScore;
    public TextView signdecorateScore;
    public TextView signsecurityScore;
    public SmileRating cleaning;
    public SmileRating service;
    public SmileRating decorate;
    public SmileRating security;
    public EditText comment;
    public Button button;

    public ArrayList<Integer> cleanlist;
    public ArrayList<Integer> servicelist;
    public ArrayList<Integer> decoratelist;
    public ArrayList<Integer> securitylist;
    public ArrayList<String>  commentlist;
    public int averagescore = 0;
    public String point;
    public String nopoint;
    public View view2;
    public View view3;
    public TextView totalaveragescore;
    public TextView sign;

    public int cleanScore;
    public int serviceScore;
    public int decorateScore;
    public int securityScore;
    public int[] summary = new int[4];

    public int cleanscore;
    public int servicescore;
    public int decoratescore;
    public int securityscore;
    public String commentfromuser;
    public TextView mostofuser;
    public LinearLayout seecomment;
    public ExpandableRelativeLayout expand;
    public TextView commenttitle;
    public ArrayList<String> favlist = new ArrayList<>();
    public int langcode;

    public LinearLayout linear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__dorm__information);
        this.plist = new ArrayList<>();
        this.list = new ArrayList<>();
        this.faclist = new ArrayList<>();

        cleanlist = new ArrayList<>();
        servicelist = new ArrayList<>();
        decoratelist = new ArrayList<>();
        securitylist = new ArrayList<>();
        commentlist = new ArrayList<>();

        this.setFaculty();


        this.linear = (LinearLayout) findViewById(R.id.commentfromuser);
        this.drivingTitle = (TextView) findViewById(R.id.textView73);
        this.transitTitle = (TextView) findViewById(R.id.textView75);
        this.driving = (LinearLayout) findViewById(R.id.driving);
        this.transit = (LinearLayout) findViewById(R.id.transit);
        this.moreinfo = (ExpandableHeightListView) findViewById(R.id.listview2);
        this.address = (TextView) findViewById(R.id.address);
        this.datanamedormowner = (TextView) findViewById(R.id.datanamedormowner);
        this.datacontactdormowner = (TextView) findViewById(R.id.datacontactdormowner);
        this.dataemaildormowner = (TextView) findViewById(R.id.dataemaildormowner);
        this.docImage = (ImageView) findViewById(R.id.docImage);
        this.dataaddressdormowner = (TextView) findViewById(R.id.dataaddressdormowner);
        this.distance = (TextView) findViewById(R.id.distance);
        this.timeWalking = (TextView) findViewById(R.id.timeWalking);
        this.timeDriving = (TextView) findViewById(R.id.timeDriving);
        this.timeTransit = (TextView) findViewById(R.id.timeTransit);

        this.describedetail = (TextView) findViewById(R.id.describedetail);
        this.image360 = (ImageView) findViewById(R.id.image360);
        this.nameTextView = (TextView) findViewById(R.id.dormName);
        this.signFavorite = (TextView) findViewById(R.id.sign_favorite);
        this.heart = (ImageView) findViewById(R.id.heart);

        this.roomprice = (TextView) findViewById(R.id.roomprice);
        this.waterprice = (TextView) findViewById(R.id.waterprice);
        this.electroprice = (TextView) findViewById(R.id.electroprice);
        this.depositPrice = (TextView) findViewById(R.id.depositprice);
        this.commonfeeprice = (TextView) findViewById(R.id.commonfeeprice);
        this.describedetail = (TextView) findViewById(R.id.describedetail);
        this.factext = (ExpandableHeightGridView) findViewById(R.id.facilityText);
        this.listview = (ExpandableHeightListView) findViewById(R.id.listview3);

        this.cleaning = (SmileRating) findViewById(R.id.commentcleanning);
        this.service = (SmileRating) findViewById(R.id.commentService);
        this.decorate = (SmileRating) findViewById(R.id.commentDecorating);
        this.security = (SmileRating) findViewById(R.id.commentSecurity);
        this.comment = (EditText) findViewById(R.id.editText);
        this.seecomment = (LinearLayout) findViewById(R.id.seecomment);
        this.view3 = (View) findViewById(R.id.view3);
        this.signcleanScore = (TextView) findViewById(R.id.scoreCleanning);
        this.signserviceScore = (TextView) findViewById(R.id.scoreService);
        this.signdecorateScore = (TextView) findViewById(R.id.scoreDecorating);
        this.signsecurityScore = (TextView) findViewById(R.id.scoreSecurity);
        this.totalaveragescore = (TextView) findViewById(R.id.averageScore);
        this.sign = (TextView) findViewById(R.id.sign);
        this.mostofuser = (TextView) findViewById(R.id.textView57);
        this.commenttitle = (TextView) findViewById(R.id.commentTitle);


        SharedPreferences lang = getSharedPreferences("language", Context.MODE_PRIVATE);
        langcode = lang.getInt("languageCode",0);

        this.point = getResources().getString(R.string.point);
        this.nopoint = getResources().getString(R.string.nopoint);
        SharedPreferences share = getSharedPreferences("file_pref",MODE_PRIVATE);
        String fac = share.getString("faculty","no faculty");
        facultyOfUser = fac;
        System.out.println("Faculty of User: "+facultyOfUser);

        sharedPreferences = getSharedPreferences("selectDorm",MODE_PRIVATE);
        this.dormName = sharedPreferences.getString("dormname","no value");
        this.setcountFavorite(dormName);
        editor = sharedPreferences.edit();
        stack = new ArrayList<>();


        this.bar = getSupportActionBar();
        this.bar.setTitle(dormName);
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));
        this.bar.setDisplayHomeAsUpEnabled(true);



        this.nameTextView.setTextSize(18);
        this.nameTextView.setText(dormName);
        ff = getResources().getString(R.string.pressFavorite);
        int red = getResources().getColor(R.color.red);
        this.signFavorite.setText(summaryLikeDorm+" "+ff);
        this.signFavorite.setTextColor(red);

        nearPlaceList = (ExpandableHeightListView) findViewById(R.id.listview);

        sharedPreferences = getSharedPreferences("file_pref", Context.MODE_PRIVATE);
        boolean f = sharedPreferences.getBoolean("statusSearch",false);
        codeStyle = sharedPreferences.getInt("dormStyleCode",4);
        username = sharedPreferences.getString("username","no value");
        this.setFavorite(username);
        //System.out.println("FFFFFFFFFFFFFFFFFFF:  "+f);
        if(f == true)
        {
            this.dormName = getIntent().getStringExtra("dormName");
            boolean a = false;
            this.nameTextView.setText(dormName);
            this.bar.setTitle(dormName);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("statusSearch",a);
            editor.commit();

            travel = getIntent().getStringExtra("travel");
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


        this.getImage360(dormName);

        String[] array = getResources().getStringArray(R.array.faculty);

        if(facultyOfUser.equals(array[1]))
        {
            faculty = scienceAndTechnology;
            plist = scienceAndTechnology.getPlacelist();
        }
        else if(facultyOfUser.equals(array[2]))
        {
            faculty = law;
            plist = law.getPlacelist();
        }
        else if(facultyOfUser.equals(array[3]))
        {
            faculty = politicalScience;
            plist = politicalScience.getPlacelist();
        }
        else if(facultyOfUser.equals(array[4]))
        {
            faculty = account;
            plist = account.getPlacelist();
        }
        else if(facultyOfUser.equals(array[5]))
        {
            faculty = liberalArt;
            plist = liberalArt.getPlacelist();
        }
        else if(facultyOfUser.equals(array[6]))
        {
            faculty = architect;
            plist = architect.getPlacelist();
        }
        else if(facultyOfUser.equals(array[7]))
        {
            faculty = economy;
            plist = economy.getPlacelist();
        }
        else if(facultyOfUser.equals(array[8]))
        {
            faculty = social;
            plist = social.getPlacelist();
        }
        else if(facultyOfUser.equals(array[9]))
        {
            faculty = jouranlism;
            plist = jouranlism.getPlacelist();
        }
        else if(facultyOfUser.equals(array[10]))
        {
            faculty = humanism;
            plist = humanism.getPlacelist();
        }
        else if(facultyOfUser.equals(array[11]))
        {
            faculty = engineer;
            plist = engineer.getPlacelist();
        }
        else if(facultyOfUser.equals(array[12]))
        {
            faculty = medicine;
            plist = medicine.getPlacelist();
        }
        else if(facultyOfUser.equals(array[13]))
        {
            faculty = alliedHealth;
            plist = alliedHealth.getPlacelist();
        }
        else if(facultyOfUser.equals(array[14]))
        {
            faculty = dentistry;
            plist = dentistry.getPlacelist();
        }
        else if(facultyOfUser.equals(array[15]))
        {
            faculty = nurse;
            plist = nurse.getPlacelist();
        }
        else if(facultyOfUser.equals(array[16]))
        {
            faculty = fineArt;
            plist = fineArt.getPlacelist();
        }
        else if(facultyOfUser.equals(array[17]))
        {
            faculty = publicHealth;
            plist = publicHealth.getPlacelist();
        }
        else if(facultyOfUser.equals(array[18]))
        {
            faculty = pharmacy;
            plist = pharmacy.getPlacelist();
        }
        else if(facultyOfUser.equals(array[19]))
        {
            faculty = studyAndknowledge;
            plist = studyAndknowledge.getPlacelist();
        }
        else if(facultyOfUser.equals(array[20]))
        {
            faculty = siit;
            plist = siit.getPlacelist();
        }
        else if(facultyOfUser.equals(array[21]))
        {
            faculty = puey;
            plist = puey.getPlacelist();
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
                                        Intent i = new Intent(Detail_Information_Favorite.this, FullScreen_Image360.class);
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

        this.mapfgr = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        this.mapfgr.getMapAsync(this);

        this.getImage360(dormName);
        this.getExpense(dormName);
        this.showContact(dormName);
        this.getPositionDorm(dormName);
        this.getDistancePlaces(dormName);
        this.getFacility(dormName);
        this.getPremieum(dormName);



    }

    public void goComment(View v){
        Intent intent = new Intent(Detail_Information_Favorite.this,CommentandScore_Information_Style.class);
        startActivity(intent);
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

    public void showImage(View v){
        Intent i = new Intent(Detail_Information_Favorite.this,Fullscreen_Image.class);
        i.putExtra("pathImage",pathDocument);
        startActivity(i);

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

    public void getExpense(String dormname){
        String expensePath = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dormname;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, expensePath, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    String baht = getResources().getString(R.string.baht);
                    int minPriceN = response.getInt("minPrice");
                    int waterPriceN = response.getInt("priceWater");
                    int electroPriceN = response.getInt("priceElectro");
                    int depositPriceN = response.getInt("depositPrice");
                    int commonfeeN = response.getInt("commonFee");
                    String typeWater = response.getString("typeWater");
                    String typeElectro = response.getString("typeElectro");
                    String description = response.getString("description");
                    waterprice.setText(String.valueOf(waterPriceN)+" บาท"+"("+typeWater+")");
                    electroprice.setText(String.valueOf(electroPriceN)+" บาท"+"("+typeElectro+")");
                    roomprice.setText(String.valueOf(minPriceN)+" บาท");
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

                    //commonfeeprice;
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

        RequestQueue q = Volley.newRequestQueue(this);
        q.add(request);
    }

    public void getGallery(View v){
        Intent i = new Intent(Detail_Information_Favorite.this,Gallery_Information.class);
        startActivity(i);
    }

    public void getDistancePlaces(final String dormName){
        final RequestQueue q = Volley.newRequestQueue(this);
        String pathPositionMydorm = "http://"+ip+port+"/dorm/getDormByDormName/"+dormName;
        System.out.println("URL: "+pathPositionMydorm);
        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, pathPositionMydorm, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    double latitude = response.getDouble("latitude");
                    double longtitude = response.getDouble("longtitude");
                    System.out.println(dormName+"  "+latitude+"  "+longtitude);
                    mydorm = new LatLng(latitude,longtitude);

                    final int height = 120;
                    final int width = 120;
                    //BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_dorm_use);
                    //Bitmap b = bitmapdraw.getBitmap();
                    //Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                    //MarkerOptions dorm = new MarkerOptions().title(dormName).position(mydorm).icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                    //mMap.addMarker(dorm);

                    int numberPlace = plist.size();
                    System.out.println("Size: "+numberPlace);
                    //final MarkerOptions[] mk = new MarkerOptions[numberPlace];
                    final Places[] p = new Places[numberPlace];
                    for(int count=0;count<numberPlace;count++)
                    {

                        final int c = count;


                        final String nameTH = plist.get(c).nameTH;
                        final String nameEN = plist.get(c).nameEN;
                        final double lat = plist.get(c).latitude_place;
                        final double lng = plist.get(c).longtitude_place;
                        p[count] = new Places(nameTH,nameEN,lat,lng);
                        String urlDirection = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+mydorm.latitude+","+mydorm.longitude+"&destination="+lat+","+lng+"&key="+newServerKey;
                        System.out.println(urlDirection);
                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlDirection, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println(response);
                                JSONObject data = response;
                                JSONArray route = null;


                                try
                                {
                                    route = data.getJSONArray("routes");
                                    JSONObject routes = route.getJSONObject(0);
                                    JSONArray legs = routes.getJSONArray("legs");
                                    JSONObject legss = legs.getJSONObject(0);
                                    JSONObject distance = legss.getJSONObject("distance");
                                    JSONObject duration = legss.getJSONObject("duration");
                                    final String distance_text = distance.getString("text");
                                    final String duration_text = duration.getString("text");

                                    // BitmapDrawable bitmapdrawable2 = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_place_modify);
                                    // Bitmap b2 = bitmapdrawable2.getBitmap();
                                    // Bitmap smallMarker2 = Bitmap.createScaledBitmap(b2, width, height, false);

                                    p[c].setDistance(distance_text);
                                    // mk[c] = new MarkerOptions().title(nameTH).position(new LatLng(lat,lng)).icon(BitmapDescriptorFactory.fromBitmap(smallMarker2)).snippet("ระยะทางจากหอ:"+distance_text+" เวลา:"+duration_text);
                                    // mMap.addMarker(mk[c]);

                                    list.add(p[c]);

                                    System.out.println("Size of List: "+list.size());
                                    Place_ListView_Information_Adapter adapter = new Place_ListView_Information_Adapter(getApplicationContext(),list,langcode);
                                    nearPlaceList.setAdapter(adapter);
                                    nearPlaceList.setExpanded(true);



                                }
                                catch (JSONException e)
                                {
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





                    CameraPosition cameraPosition = CameraPosition.builder()
                            .target(mydorm)
                            .zoom(15)
                            .build();

                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),1000,null);

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

        q.add(req);
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
                                editor.putString("pathImage360",image360t);
                                editor.commit();

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
                    Facility[] fac = new Facility[nameTH.length()];
                    for(int count=0;count<imagecode.length();count++)
                    {
                        fac[count] = new Facility(nameTH.getString(count),nameEN.getString(count),imagecode.getInt(count));
                        int code = imagecode.getInt(count);
                        String th = nameTH.getString(count);
                        String en = nameEN.getString(count);
                        if(langcode == 0)
                        {
                            faclist.add(fac[count]);
                        }
                        else
                        {
                            faclist.add(fac[count]);
                        }

                    }

                    //System.out.println("FACILITY IN DORM : "+facilityList1.length);

                    JsonObjectRequest inroom = new JsonObjectRequest(Request.Method.GET, pathInroom, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray nameth = response.getJSONArray("nameTH");
                                JSONArray nameen = response.getJSONArray("nameEN");
                                JSONArray imagecode = response.getJSONArray("image");
                                Facility[] f = new Facility[nameth.length()];

                                //facilityList2 = new Facility[imagecode.length()];
                                for(int count2=0;count2<imagecode.length();count2++)
                                {
                                    f[count2] = new Facility(nameth.getString(count2),nameen.getString(count2),imagecode.getInt(count2));
                                    int codee = imagecode.getInt(count2);
                                    String thtext = nameth.getString(count2);
                                    String entext = nameen.getString(count2);
                                    if(langcode == 0)
                                    {
                                        faclist.add(f[count2]);
                                    }
                                    else
                                    {
                                        faclist.add(f[count2]);
                                    }
                                    FacilityAdapter adapter = new FacilityAdapter(faclist,langcode);
                                    factext.setAdapter(adapter);
                                    factext.setExpanded(true);
                                    //  facilityList2[count2] = new Facility(thtext,entext,codee);
                                    //  list.add(facilityList2[count2]);
                                }

                                String g = "";
                                //for(int count3=0;count3<faclist.size();count3++)
                                //{
                                    //g = g + faclist.get(count3)+", ";
                                //}








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

    public void getPremieum(String dormName){

        final ArrayList<String> array = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        String pathPremieum = "http://"+ip+port+"/DormTypeInformation/getDorm/"+dormName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, pathPremieum, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String[] text;
                    String detail = response.getString("detail");
                    StringTokenizer token = new StringTokenizer(detail,",");
                    text = new String[token.countTokens()];
                    while(token.hasMoreTokens())
                    {
                        String aa = token.nextToken();
                        System.out.println(aa+"  &&&*&*&*&*");
                        array.add(aa);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,array){
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);
                            TextView tv = (TextView) view.findViewById(android.R.id.text1);
                            tv.setTextColor(Color.BLACK);
                            return tv;
                        }
                    };
                    moreinfo.setAdapter(adapter);
                    moreinfo.setExpanded(true);

                } catch (JSONException e) {
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        this.mMap.getUiSettings().setAllGesturesEnabled(false);
    }


    public void pressFavorite(View v){
        // if อันนี้จะเช็คว่าถ้ามีหอพักที่เราไม่เคยกดถูกใจจะสามารถเข้า if นี้ได้ และกดถูกใจหอพักนี้ได้ หัวใจก็จะสามารถกดได้  if(suspenclick == false ) กับ if(statusPress == false)

        if(statusPress == false)
        {
            summaryLikeDorm = summaryLikeDorm + 1;
            statusPress = true;
            this.signFavorite.setText(summaryLikeDorm+" "+ff);
            int red = getResources().getColor(R.color.red);
            this.signFavorite.setTextColor(red);
            heart.setImageResource(R.drawable.heart_press2);
            // ฟังก์ชันสำหรับการเก็บชื่อหอพักที่ชื่นชอบ
            this.addFavorite(dormName);
        }
        else
        {
            summaryLikeDorm = summaryLikeDorm - 1;
            this.signFavorite.setText(summaryLikeDorm+" "+ff);
            int red = getResources().getColor(R.color.red);
            this.signFavorite.setTextColor(red);
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
            signFavorite.setText(numberofLike+" "+ff);
            summaryLikeDorm = numberofLike;
        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
    }

}
