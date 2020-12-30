package com.example.testrestapi;

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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class FavoriteActivity extends AppCompatActivity {
    public ActionBar bar;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public int plastic = 0;
    public String faculty;
    public String dormStyle;
    public int codedormStyle;
    public RecyclerView recycler;
    public Spinner sort;
    public LinearLayout linear;

    public String[] sortArray;
    public String userchoose;
    public ArrayList<FavoriteDormModel> modellist;
    public ArrayList<String> stringlist;
    public String username;

    public String port = ":8080";
    public String ip = "192.168.43.57";


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

    Places lc1 ;
    Places lc2 ;
    Places lc3 ;
    Places lc4 ;
    Places lc5 ;
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

    public String checkChoice;
    public String facultyOfUser;
    public Faculty facofuser;
    public String newServerKey = "AIzaSyCGwojidCNNXh9PfVDqCkU2uzH2W8l3Xw4";
    public LinearLayoutManager manager;

    public RecyclerViewAdapter_favorite adapter;
    public ArrayList<FavoriteDormModel> list2;
    public HashMap<String,FavoriteDormModel> map;

    public SharedPreferences changelanguage;
    public int codelanguage;
    public ArrayAdapter<CharSequence> adapterr;

   public String waterp;
   public String electp;
    public String middle;
    public String depop;
    public String unit;
    public String free;
    public String baht;
    public String[] text = new String[7];
    public String lang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        this.setFaculty();
        modellist = new ArrayList<>();
        stringlist = new ArrayList<>();
        map = new HashMap<>();
        list2 = new ArrayList<>();
        final String[] array = getResources().getStringArray(R.array.faculty);
        this.bar = getSupportActionBar();
        this.bar.setDisplayHomeAsUpEnabled(true);

        this.sortArray = getResources().getStringArray(R.array.sort);

        int fav = R.string.favorite;
        String fav_text = getResources().getString(fav);
        this.bar.setTitle(fav_text);

        this.sort = (Spinner) findViewById(R.id.sortChoice);

        sharedPreferences = getSharedPreferences("file_pref",MODE_PRIVATE);
        username = sharedPreferences.getString("username","no value username");
        faculty = sharedPreferences.getString("faculty","default faculty");
        dormStyle = sharedPreferences.getString("dormStyle","default dormStyle");
        codedormStyle = sharedPreferences.getInt("dormStyleCode",4);
        lang = Locale.getDefault().getLanguage();
        //Toast.makeText(this,lang,Toast.LENGTH_LONG).show();
        changelanguage = getSharedPreferences("language",MODE_PRIVATE);
        codelanguage = changelanguage.getInt("languageCode",3);

        SharedPreferences selectDorm = getSharedPreferences("selectDorm",MODE_PRIVATE);
        SharedPreferences.Editor selectDormeditor = selectDorm.edit();
        selectDormeditor.putString("dormname","initial value");
        selectDormeditor.putString("pathImage360","initial value");
        selectDormeditor.putInt("numberofFavorite",0);
        selectDormeditor.commit();

        waterp = getResources().getString(R.string.water_payment);
        electp = getResources().getString(R.string.electro_payment);
        middle = getResources().getString(R.string.middle_payment);
        depop = getResources().getString(R.string.deposit);
        unit = getResources().getString(R.string.unit);
        free = getResources().getString(R.string.free);
        baht = getResources().getString(R.string.baht);
        text[0] = waterp;
        text[1] = electp;
        text[2] = middle;
        text[3] = depop;
        text[4] = unit;
        text[5] = free;
        text[6] = baht;

        if(codedormStyle == 0 || codedormStyle == 1 || codedormStyle == 2)
        {
            this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));
        }
        else if(codedormStyle == 3)
        {
            this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        }

        this.sort = (Spinner) findViewById(R.id.sortChoice);
        this.linear = (LinearLayout) findViewById(R.id.linearLayout);
        this.manager = new LinearLayoutManager(this);
        this.recycler = (RecyclerView) findViewById(R.id.recycler);

        facultyOfUser = faculty;
        if(facultyOfUser.equals(array[1]))
        {
            facofuser = scienceAndTechnology;
            //plist = scienceAndTechnology.getPlacelist();

        }
        else if(facultyOfUser.equals(array[2]))
        {
            facofuser = law;
            //plist = law.getPlacelist();

        }
        else if(facultyOfUser.equals(array[3]))
        {
            facofuser = politicalScience;
            //plist = politicalScience.getPlacelist();

        }
        else if(facultyOfUser.equals(array[4]))
        {
            facofuser = account;
            //plist = account.getPlacelist();

        }
        else if(facultyOfUser.equals(array[5]))
        {
            facofuser = liberalArt;
            //plist = liberalArt.getPlacelist();

        }
        else if(facultyOfUser.equals(array[6]))
        {
            facofuser = architect;
            //plist = architect.getPlacelist();

        }
        else if(facultyOfUser.equals(array[7]))
        {
            facofuser = economy;
            //plist = economy.getPlacelist();

        }
        else if(facultyOfUser.equals(array[8]))
        {
            facofuser = social;
            //plist = social.getPlacelist();

        }
        else if(facultyOfUser.equals(array[9]))
        {
            facofuser = jouranlism;
            //plist = jouranlism.getPlacelist();

        }
        else if(facultyOfUser.equals(array[10]))
        {
            facofuser = humanism;
            //plist = humanism.getPlacelist();

        }
        else if(facultyOfUser.equals(array[11]))
        {
            facofuser = engineer;
            //plist = engineer.getPlacelist();

        }
        else if(facultyOfUser.equals(array[12]))
        {
            facofuser = medicine;
            //plist = medicine.getPlacelist();

        }
        else if(facultyOfUser.equals(array[13]))
        {
            facofuser = alliedHealth;
            //plist = alliedHealth.getPlacelist();

        }
        else if(facultyOfUser.equals(array[14]))
        {
            facofuser = dentistry;
            //plist = dentistry.getPlacelist();

        }
        else if(facultyOfUser.equals(array[15]))
        {
            facofuser = nurse;
            //plist = nurse.getPlacelist();

        }
        else if(facultyOfUser.equals(array[16]))
        {
            facofuser = fineArt;
            //plist = fineArt.getPlacelist();

        }
        else if(facultyOfUser.equals(array[17]))
        {
            facofuser = publicHealth;
            //plist = publicHealth.getPlacelist();

        }
        else if(facultyOfUser.equals(array[18]))
        {
            facofuser = pharmacy;
            //plist = pharmacy.getPlacelist();

        }
        else if(facultyOfUser.equals(array[19]))
        {
            facofuser = studyAndknowledge;
            //plist = studyAndknowledge.getPlacelist();

        }
        else if(facultyOfUser.equals(array[20]))
        {
            facofuser = siit;
            //plist = siit.getPlacelist();

        }
        else if(facultyOfUser.equals(array[21]))
        {
            facofuser = puey;
            //plist = puey.getPlacelist();

        }


        //sortDorm(codedormStyle,username);

        if(codelanguage == 0 || changelanguage.equals("TH"))
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.sort,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sort.setAdapter(adapter);
            //sort.setSelection(2);
            sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    userchoose = sortArray[position];
                    //System.out.println("CHOICE SORT : "+userchoose);
                    checkChoice = userchoose;
                    sortDorm(codedormStyle,userchoose,username,codelanguage);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else if(codelanguage == 1 || changelanguage.equals("EN"))
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.sortEN,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //sort.setSelection(2);
            sort.setAdapter(adapter);

            sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    userchoose = sortArray[position];
                    //System.out.println("CHOICE SORT : "+userchoose);
                    checkChoice = userchoose;
                    sortDorm(codedormStyle,userchoose,username,codelanguage);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }



    public void sortDorm(final int codedormStyle, final String userchoose, String username, final int codelanguage){
       final RequestQueue queue = Volley.newRequestQueue(this);

      if(codedormStyle == 0)
      {

          String getfavorite = "http://"+ip+port+"/favorite/checkNumberofFavoriteByUsername/"+username;
              JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, getfavorite, null, new Response.Listener<JSONObject>() {
                  @Override
                  public void onResponse(JSONObject response) {
                        JSONObject object = response;
                      try {
                          String usernameRes = object.getString("username");
                          final JSONArray favarray = object.getJSONArray("dormname");
                          final FavoriteDormModel[] favmodel = new FavoriteDormModel[favarray.length()];
                          String[] dnamelist = new String[favarray.length()];

                          for(int count=0;count<favarray.length();count++)
                          {
                              final int c = count;
                              plastic = count;
                              final String dname = favarray.getString(count);
                              favmodel[count] = new FavoriteDormModel();
                              favmodel[count].setDormname(dname);
                              dnamelist[count] = dname;
                              String getDistance = "http://"+ip+port+"/dorm/getDormByDormName/"+dname;
                              JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getDistance, null, new Response.Listener<JSONObject>() {
                                  @Override
                                  public void onResponse(JSONObject response) {
                                        final JSONObject res = response;
                                      try {
                                          String address = res.getString("address");
                                          double latitude = res.getDouble("latitude");
                                          double longtitude = res.getDouble("longtitude");
                                          String collecttext = " ";
                                          StringTokenizer token = new StringTokenizer(address," ");
                                          while(token.hasMoreTokens())
                                          {
                                              collecttext = collecttext + token.nextToken()+" ";
                                          }
                                          favmodel[c].setAddress(collecttext);

                                          String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                          JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                              @Override
                                              public void onResponse(JSONObject response) {
                                                  JSONObject ob = response;
                                                  try {

                                                      JSONArray route_title = ob.getJSONArray("routes");
                                                      JSONObject routes = route_title.getJSONObject(0);
                                                      JSONArray leg_title = routes.getJSONArray("legs");
                                                      JSONObject legs = leg_title.getJSONObject(0);
                                                      JSONObject distance = legs.getJSONObject("distance");
                                                      String distanceText = distance.getString("text");
                                                      String distanceInteger = "";
                                                      String unit = "";
                                                      String disText = "";
                                                      StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                      for (int i = 0; i < token.countTokens(); i++)
                                                      {
                                                          disText = token.nextToken();
                                                          unit = token.nextToken();
                                                      }
                                                      favmodel[c].setDistance(distanceText);
                                                      favmodel[c].setDistanceDouble(Double.parseDouble(disText));

                                                      String getInfodorm = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dname;
                                                      JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET, getInfodorm, null, new Response.Listener<JSONObject>() {
                                                          @Override
                                                          public void onResponse(JSONObject response) {
                                                                try {
                                                                    int minPrice = response.getInt("minPrice");
                                                                    //String typewater = response.getString("typeWater");
                                                                    int waterprice = response.getInt("priceWater");
                                                                    int electroprice = response.getInt("priceElectro");
                                                                    int commonfeeprice = response.getInt("commonFee");
                                                                    int depositprice = response.getInt("depositPrice");

                                                                    favmodel[c].setRoomprice(minPrice);
                                                                    favmodel[c].setWaterprice(waterprice);
                                                                    favmodel[c].setElectroprice(electroprice);
                                                                    favmodel[c].setCommonfee(commonfeeprice);
                                                                    favmodel[c].setDepositprice(depositprice);

                                                                    String checkpromotion = "http://"+ip+port+"/DormTypePriceNPromotion/getDormBydormName/"+dname;
                                                                    JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET, checkpromotion, null, new Response.Listener<JSONObject>() {
                                                                        @Override
                                                                        public void onResponse(JSONObject response) {
                                                                            try {
                                                                                String prodetail = response.getString("promotionDescribe");
                                                                                if(!prodetail.equals(""))
                                                                                {
                                                                                    int proprice = response.getInt("newPrice");
                                                                                    favmodel[c].setRoomprice(proprice);
                                                                                }

                                                                               String getUsernameEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dname;
                                                                                JsonObjectRequest request5 = new JsonObjectRequest(Request.Method.GET, getUsernameEmail, null, new Response.Listener<JSONObject>() {
                                                                                    @Override
                                                                                    public void onResponse(JSONObject response) {
                                                                                        try {
                                                                                             String userName = response.getString("username");
                                                                                             String email = response.getString("email");

                                                                                             String getcoverimage = "http://"+ip+port+"/CoverImage/getCoverImageByusernameAndEmail/"+userName+"/"+email;
                                                                                             JsonObjectRequest request6 = new JsonObjectRequest(Request.Method.GET, getcoverimage, null, new Response.Listener<JSONObject>() {
                                                                                                 @Override
                                                                                                 public void onResponse(JSONObject response) {
                                                                                                     try {
                                                                                                         String pathimage = response.getString("coverImage");
                                                                                                         favmodel[c].setCoverimage(pathimage);

                                                                                                         Gson g = new Gson();
                                                                                                         String a = g.toJson(favmodel[c]);

                                                                                                         //FavoriteDormModel d = g.fromJson(a,FavoriteDormModel.class);
                                                                                                        FavoriteDormModel gg = favmodel[c];

                                                                                                        map.put(favmodel[c].getDormname(),favmodel[c]);

                                                                                                         //modellist.add(favmodel[c]);

                                                                                                         RecyclerViewAdapter_favorite adapter = new RecyclerViewAdapter_favorite(map,getApplicationContext(),facofuser,codedormStyle,userchoose,codelanguage,text);
                                                                                                         manager.setOrientation(RecyclerView.HORIZONTAL);
                                                                                                         recycler.setLayoutManager(manager);
                                                                                                         recycler.setAdapter(adapter);

                                                                                                        adapter.setOnItemClickListener(new OnItemClickListener() {
                                                                                                            @Override
                                                                                                            public void onItemClick(int position) {
                                                                                                                String dormName = adapter.getFavlist()[position].getDormname();
                                                                                                                Intent i = new Intent(getApplicationContext(),Detail_PriceNPromotiion_Favorite.class);
                                                                                                                SharedPreferences selectdorm = getSharedPreferences("selectDorm",MODE_PRIVATE);
                                                                                                                SharedPreferences.Editor editor = selectdorm.edit();
                                                                                                                editor.putString("dormname",dormName);
                                                                                                                editor.commit();
                                                                                                                i.putExtra("dormName",dormName);
                                                                                                                startActivity(i);
                                                                                                            }
                                                                                                        });




                                                                                                     } catch (JSONException e) {
                                                                                                         e.printStackTrace();
                                                                                                     }

                                                                                                 }
                                                                                             }, new Response.ErrorListener() {
                                                                                                 @Override
                                                                                                 public void onErrorResponse(VolleyError error) {

                                                                                                 }
                                                                                             });
                                                                                             queue.add(request6);
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
                                                                                queue.add(request5);



                                                                            } catch (JSONException e) {
                                                                                e.printStackTrace();
                                                                            }

                                                                        }
                                                                    }, new Response.ErrorListener() {
                                                                        @Override
                                                                        public void onErrorResponse(VolleyError error) {

                                                                        }
                                                                    });
                                                                    queue.add(request4);
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
                                                      queue.add(request4);
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
                                          queue.add(request3);
                                      } catch (JSONException e) {
                                          e.printStackTrace();
                                      }
                                  }
                              }, new Response.ErrorListener() {
                                  @Override
                                  public void onErrorResponse(VolleyError error) {

                                  }
                              });
                              queue.add(request2);

                          }


                      } catch (JSONException e) {
                          e.printStackTrace();
                      }
                  }
              }, new Response.ErrorListener() {
                  @Override
                  public void onErrorResponse(VolleyError error) {

                  }
              });
              queue.add(request1);

      }
      else if(codedormStyle == 1)
      {
          String getfavorite = "http://"+ip+port+"/favorite/checkNumberofFavoriteByUsername/"+username;
          JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, getfavorite, null, new Response.Listener<JSONObject>() {
              @Override
              public void onResponse(JSONObject response) {
                  JSONObject object = response;
                  try {
                      String usernameRes = object.getString("username");
                      final JSONArray favarray = object.getJSONArray("dormname");
                      final FavoriteDormModel[] favmodel = new FavoriteDormModel[favarray.length()];

                      for(int count=0;count<favarray.length();count++)
                      {
                          final int c = count;
                          final String dname = favarray.getString(count);
                          favmodel[count] = new FavoriteDormModel();
                          favmodel[count].setDormname(dname);

                          String getDistance = "http://"+ip+port+"/dorm/getDormByDormName/"+dname;
                          JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getDistance, null, new Response.Listener<JSONObject>() {
                              @Override
                              public void onResponse(JSONObject response) {
                                  final JSONObject res = response;
                                  try {
                                      String address = res.getString("address");
                                      double latitude = res.getDouble("latitude");
                                      double longtitude = res.getDouble("longtitude");
                                      String collecttext = " ";
                                      StringTokenizer token = new StringTokenizer(address, " ");
                                      while (token.hasMoreTokens()) {
                                          collecttext = collecttext + token.nextToken() + " ";
                                      }
                                      favmodel[c].setAddress(collecttext);
                                      String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                      JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                          @Override
                                          public void onResponse(JSONObject response) {
                                              JSONObject ob = response;
                                              try {
                                                  JSONArray route_title = ob.getJSONArray("routes");
                                                  JSONObject routes = route_title.getJSONObject(0);
                                                  JSONArray leg_title = routes.getJSONArray("legs");
                                                  JSONObject legs = leg_title.getJSONObject(0);
                                                  JSONObject distance = legs.getJSONObject("distance");
                                                  String distanceText = distance.getString("text");
                                                  String distanceInteger = "";
                                                  String unit = "";
                                                  String disText = "";
                                                  StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                  for (int i = 0; i < token.countTokens(); i++) {
                                                      disText = token.nextToken();
                                                      unit = token.nextToken();
                                                  }
                                                  favmodel[c].setDistance(distanceText);
                                                  favmodel[c].setDistanceDouble(Double.parseDouble(disText));

                                                  String getInfodorm = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dname;
                                                  JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET, getInfodorm, null, new Response.Listener<JSONObject>() {
                                                      @Override
                                                      public void onResponse(JSONObject response) {
                                                          try {
                                                              int minPrice = response.getInt("minPrice");
                                                              //String typewater = response.getString("typeWater");
                                                              int waterprice = response.getInt("priceWater");
                                                              int electroprice = response.getInt("priceElectro");
                                                              int commonfeeprice = response.getInt("commonFee");
                                                              int depositprice = response.getInt("depositPrice");

                                                              favmodel[c].setRoomprice(minPrice);
                                                              favmodel[c].setWaterprice(waterprice);
                                                              favmodel[c].setElectroprice(electroprice);
                                                              favmodel[c].setCommonfee(commonfeeprice);
                                                              favmodel[c].setDepositprice(depositprice);

                                                              String getUsernameEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dname;
                                                              JsonObjectRequest request5 = new JsonObjectRequest(Request.Method.GET, getUsernameEmail, null, new Response.Listener<JSONObject>() {
                                                                  @Override
                                                                  public void onResponse(JSONObject response) {
                                                                      try {
                                                                          String userName = response.getString("username");
                                                                          String email = response.getString("email");

                                                                          String getcoverimage = "http://"+ip+port+"/CoverImage/getCoverImageByusernameAndEmail/"+userName+"/"+email;
                                                                          JsonObjectRequest request6 = new JsonObjectRequest(Request.Method.GET, getcoverimage, null, new Response.Listener<JSONObject>() {
                                                                              @Override
                                                                              public void onResponse(JSONObject response) {
                                                                                  try {
                                                                                      String pathimage = response.getString("coverImage");
                                                                                      favmodel[c].setCoverimage(pathimage);

                                                                                      Log.i("test",favmodel[c].getDormname());
                                                                                      map.put(favmodel[c].getDormname(),favmodel[c]);

                                                                                      //modellist.add(favmodel[c]);

                                                                                      RecyclerViewAdapter_favorite adapter = new RecyclerViewAdapter_favorite(map,getApplicationContext(),facofuser,codedormStyle,userchoose,codelanguage,text);
                                                                                      manager.setOrientation(RecyclerView.HORIZONTAL);
                                                                                      recycler.setLayoutManager(manager);
                                                                                      recycler.setAdapter(adapter);

                                                                                      adapter.setOnItemClickListener(new OnItemClickListener() {
                                                                                          @Override
                                                                                          public void onItemClick(int position) {
                                                                                              String dormName = adapter.getFavlist()[position].getDormname();
                                                                                              Intent i = new Intent(getApplicationContext(),Detail_Information_Favorite.class);
                                                                                              SharedPreferences selectdorm = getSharedPreferences("selectDorm",MODE_PRIVATE);
                                                                                              SharedPreferences.Editor editor = selectdorm.edit();
                                                                                              editor.putString("dormname",dormName);
                                                                                              editor.commit();
                                                                                              i.putExtra("dormName",dormName);
                                                                                              startActivity(i);
                                                                                          }
                                                                                      });
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
                                                                          queue.add(request6);
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
                                                              queue.add(request5);
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
                                                  queue.add(request4);

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
                                      queue.add(request3);
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
                          queue.add(request2);
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
          queue.add(request1);
      }
      else if(codedormStyle == 2)
      {
          String getfavorite = "http://"+ip+port+"/favorite/checkNumberofFavoriteByUsername/"+username;
          JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, getfavorite, null, new Response.Listener<JSONObject>() {
              @Override
              public void onResponse(JSONObject response) {
                  JSONObject object = response;
                  try {
                      String usernameRes = object.getString("username");
                      final JSONArray favarray = object.getJSONArray("dormname");
                      final FavoriteDormModel[] favmodel = new FavoriteDormModel[favarray.length()];

                      for(int count=0;count<favarray.length();count++)
                      {
                          final int c = count;
                          final String dname = favarray.getString(count);
                          favmodel[count] = new FavoriteDormModel();
                          favmodel[count].setDormname(dname);

                          String getDistance = "http://"+ip+port+"/dorm/getDormByDormName/"+dname;
                          JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getDistance, null, new Response.Listener<JSONObject>() {
                              @Override
                              public void onResponse(JSONObject response) {
                                  final JSONObject res = response;
                                  try {
                                      String address = res.getString("address");
                                      double latitude = res.getDouble("latitude");
                                      double longtitude = res.getDouble("longtitude");
                                      String collecttext = " ";
                                      StringTokenizer token = new StringTokenizer(address, " ");
                                      while (token.hasMoreTokens()) {
                                          collecttext = collecttext + token.nextToken() + " ";
                                      }
                                      favmodel[c].setAddress(collecttext);
                                      String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                      JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                          @Override
                                          public void onResponse(JSONObject response) {
                                              JSONObject ob = response;
                                              try {
                                                  JSONArray route_title = ob.getJSONArray("routes");
                                                  JSONObject routes = route_title.getJSONObject(0);
                                                  JSONArray leg_title = routes.getJSONArray("legs");
                                                  JSONObject legs = leg_title.getJSONObject(0);
                                                  JSONObject distance = legs.getJSONObject("distance");
                                                  String distanceText = distance.getString("text");
                                                  String distanceInteger = "";
                                                  String unit = "";
                                                  String disText = "";
                                                  StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                  for (int i = 0; i < token.countTokens(); i++) {
                                                      disText = token.nextToken();
                                                      unit = token.nextToken();
                                                  }
                                                  favmodel[c].setDistance(distanceText);
                                                  favmodel[c].setDistanceDouble(Double.parseDouble(disText));

                                                  String getInfodorm = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dname;
                                                  JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET, getInfodorm, null, new Response.Listener<JSONObject>() {
                                                      @Override
                                                      public void onResponse(JSONObject response) {
                                                          try {
                                                              int minPrice = response.getInt("minPrice");
                                                              //String typewater = response.getString("typeWater");
                                                              int waterprice = response.getInt("priceWater");
                                                              int electroprice = response.getInt("priceElectro");
                                                              int commonfeeprice = response.getInt("commonFee");
                                                              int depositprice = response.getInt("depositPrice");

                                                              favmodel[c].setRoomprice(minPrice);
                                                              favmodel[c].setWaterprice(waterprice);
                                                              favmodel[c].setElectroprice(electroprice);
                                                              favmodel[c].setCommonfee(commonfeeprice);
                                                              favmodel[c].setDepositprice(depositprice);

                                                              String getUsernameEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dname;
                                                              JsonObjectRequest request5 = new JsonObjectRequest(Request.Method.GET, getUsernameEmail, null, new Response.Listener<JSONObject>() {
                                                                  @Override
                                                                  public void onResponse(JSONObject response) {
                                                                      try {
                                                                          String userName = response.getString("username");
                                                                          String email = response.getString("email");

                                                                          String getcoverimage = "http://"+ip+port+"/CoverImage/getCoverImageByusernameAndEmail/"+userName+"/"+email;
                                                                          JsonObjectRequest request6 = new JsonObjectRequest(Request.Method.GET, getcoverimage, null, new Response.Listener<JSONObject>() {
                                                                              @Override
                                                                              public void onResponse(JSONObject response) {
                                                                                  try {
                                                                                      String pathimage = response.getString("coverImage");
                                                                                      favmodel[c].setCoverimage(pathimage);

                                                                                      map.put(favmodel[c].getDormname(),favmodel[c]);

                                                                                      //modellist.add(favmodel[c]);

                                                                                      RecyclerViewAdapter_favorite adapter = new RecyclerViewAdapter_favorite(map,getApplicationContext(),facofuser,codedormStyle,userchoose,codelanguage,text);
                                                                                      manager.setOrientation(RecyclerView.HORIZONTAL);
                                                                                      recycler.setLayoutManager(manager);
                                                                                      recycler.setAdapter(adapter);

                                                                                      adapter.setOnItemClickListener(new OnItemClickListener() {
                                                                                          @Override
                                                                                          public void onItemClick(int position) {
                                                                                              String dormName = adapter.getFavlist()[position].getDormname();
                                                                                              Intent i = new Intent(getApplicationContext(),Detail_Quality_Favorite.class);
                                                                                              SharedPreferences selectdorm = getSharedPreferences("selectDorm",MODE_PRIVATE);
                                                                                              SharedPreferences.Editor editor = selectdorm.edit();
                                                                                              editor.putString("dormname",dormName);
                                                                                              editor.commit();
                                                                                              i.putExtra("dormName",dormName);
                                                                                              startActivity(i);
                                                                                          }
                                                                                      });
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
                                                                          queue.add(request6);
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
                                                              queue.add(request5);
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
                                                  queue.add(request4);

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
                                      queue.add(request3);
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
                          queue.add(request2);
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
          queue.add(request1);
      }
      else if(codedormStyle == 3)
      {
          String getfavorite = "http://"+ip+port+"/favorite/checkNumberofFavoriteByUsername/"+username;
          JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, getfavorite, null, new Response.Listener<JSONObject>() {
              @Override
              public void onResponse(JSONObject response) {
                  JSONObject object = response;
                  try {
                      String usernameRes = object.getString("username");
                      final JSONArray favarray = object.getJSONArray("dormname");
                      final FavoriteDormModel[] favmodel = new FavoriteDormModel[favarray.length()];

                      for(int count=0;count<favarray.length();count++)
                      {
                          final int c = count;
                          final String dname = favarray.getString(count);
                          favmodel[count] = new FavoriteDormModel();
                          favmodel[count].setDormname(dname);

                          String getDistance = "http://"+ip+port+"/dorm/getDormByDormName/"+dname;
                          JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getDistance, null, new Response.Listener<JSONObject>() {
                              @Override
                              public void onResponse(JSONObject response) {
                                  final JSONObject res = response;
                                  try {
                                      String address = res.getString("address");
                                      double latitude = res.getDouble("latitude");
                                      double longtitude = res.getDouble("longtitude");
                                      String collecttext = " ";
                                      StringTokenizer token = new StringTokenizer(address, " ");
                                      while (token.hasMoreTokens()) {
                                          collecttext = collecttext + token.nextToken() + " ";
                                      }
                                      favmodel[c].setAddress(collecttext);
                                      String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                      JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                          @Override
                                          public void onResponse(JSONObject response) {
                                              JSONObject ob = response;
                                              try {
                                                  JSONArray route_title = ob.getJSONArray("routes");
                                                  JSONObject routes = route_title.getJSONObject(0);
                                                  JSONArray leg_title = routes.getJSONArray("legs");
                                                  JSONObject legs = leg_title.getJSONObject(0);
                                                  JSONObject distance = legs.getJSONObject("distance");
                                                  String distanceText = distance.getString("text");
                                                  String distanceInteger = "";
                                                  String unit = "";
                                                  String disText = "";
                                                  StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                  for (int i = 0; i < token.countTokens(); i++) {
                                                      disText = token.nextToken();
                                                      unit = token.nextToken();
                                                  }
                                                  favmodel[c].setDistance(distanceText);
                                                  favmodel[c].setDistanceDouble(Double.parseDouble(disText));

                                                  String getInfodorm = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dname;
                                                  JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET, getInfodorm, null, new Response.Listener<JSONObject>() {
                                                      @Override
                                                      public void onResponse(JSONObject response) {
                                                          try {
                                                              int minPrice = response.getInt("minPrice");
                                                              //String typewater = response.getString("typeWater");
                                                              int waterprice = response.getInt("priceWater");
                                                              int electroprice = response.getInt("priceElectro");
                                                              int commonfeeprice = response.getInt("commonFee");
                                                              int depositprice = response.getInt("depositPrice");

                                                              favmodel[c].setRoomprice(minPrice);
                                                              favmodel[c].setWaterprice(waterprice);
                                                              favmodel[c].setElectroprice(electroprice);
                                                              favmodel[c].setCommonfee(commonfeeprice);
                                                              favmodel[c].setDepositprice(depositprice);

                                                              String getUsernameEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dname;
                                                              JsonObjectRequest request5 = new JsonObjectRequest(Request.Method.GET, getUsernameEmail, null, new Response.Listener<JSONObject>() {
                                                                  @Override
                                                                  public void onResponse(JSONObject response) {
                                                                      try {
                                                                          String userName = response.getString("username");
                                                                          String email = response.getString("email");

                                                                          String getcoverimage = "http://"+ip+port+"/CoverImage/getCoverImageByusernameAndEmail/"+userName+"/"+email;
                                                                          JsonObjectRequest request6 = new JsonObjectRequest(Request.Method.GET, getcoverimage, null, new Response.Listener<JSONObject>() {
                                                                              @Override
                                                                              public void onResponse(JSONObject response) {
                                                                                  try {
                                                                                      String pathimage = response.getString("coverImage");
                                                                                      favmodel[c].setCoverimage(pathimage);

                                                                                      map.put(favmodel[c].getDormname(),favmodel[c]);

                                                                                      //modellist.add(favmodel[c]);

                                                                                      RecyclerViewAdapter_favorite adapter = new RecyclerViewAdapter_favorite(map,getApplicationContext(),facofuser,codedormStyle,userchoose,codelanguage,text);
                                                                                      manager.setOrientation(RecyclerView.HORIZONTAL);
                                                                                      recycler.setLayoutManager(manager);
                                                                                      recycler.setAdapter(adapter);

                                                                                      adapter.setOnItemClickListener(new OnItemClickListener() {
                                                                                          @Override
                                                                                          public void onItemClick(int position) {
                                                                                              String dormName = adapter.getFavlist()[position].getDormname();
                                                                                              Intent i = new Intent(getApplicationContext(),Detail_BrandName_Favorite.class);
                                                                                              SharedPreferences selectdorm = getSharedPreferences("selectDorm",MODE_PRIVATE);
                                                                                              SharedPreferences.Editor editor = selectdorm.edit();
                                                                                              editor.putString("dormname",dormName);
                                                                                              editor.commit();
                                                                                              i.putExtra("dormName",dormName);
                                                                                              startActivity(i);
                                                                                          }
                                                                                      });
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
                                                                          queue.add(request6);
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
                                                              queue.add(request5);
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
                                                  queue.add(request4);

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
                                      queue.add(request3);
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
                          queue.add(request2);
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
          queue.add(request1);
      }

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
