package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Ouput_from_filter extends AppCompatActivity {

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

    public String port = ":8080";
    public String ip = "192.168.43.57";
    public String newServerKey = "AIzaSyAX6qj2h0KbaAEUtSp8g-Wt0Vzml68ljA0";

    public String facultyOfUser;
    public Faculty facofuser;


    public int minprice;
    public int maxprice;
    public String distanceDescribe;
    public double distanceDouble;
    public ArrayList<Facility> facilities;
    public int codeStyle;
    public String dormStyle;
    public String faculty;

    public RecyclerView recyclerview;


    public ArrayList<Dorm_PricenPromotion_Model> pricelist;
    public ArrayList<Dorm_Information_Model> informationlist;
    public ArrayList<Dorm_Quality_Model> qualitylist;
    public ArrayList<Dorm_BrandName_Model> brandnamelist;

    public Dorm_PricenPromotion_Model[] priceModel;
    public Dorm_Information_Model[] infoModel;
    public Dorm_Quality_Model[] quaModel;
    public Dorm_BrandName_Model[] brandModel;

    public RecyclerView.LayoutManager manager;
    public int ff = 0;
    public boolean cc = false;
    public int minnn;
    public int maxxx;
    public ActionBar bar;

    public String disText;

    public boolean priceCheck = false;
    public boolean distanceCheck = false;
    public boolean facilityCheck = false;

    public SharedPreferences sharedPreferences;
    public String[] distanceArray;
    public String addressText;
    public String checkrange;
    public int zehaha = 0;
    public int checktotalloop = 0;
    public String notfoundresult;
    public String notfound;
    public int langcode;

    public SharedPreferences type1;
    public SharedPreferences.Editor editor1;

    public SharedPreferences type2;
    public SharedPreferences.Editor editor2;

    public SharedPreferences type3;
    public SharedPreferences.Editor editor3;

    public SharedPreferences type4;
    public SharedPreferences.Editor editor4;

    public int count;
    public int count2 = 0;
    public int nub = 0;
    public String confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ouput_from_filter);
        this.pricelist = new ArrayList<>();
        this.informationlist = new ArrayList<>();
        this.qualitylist = new ArrayList<>();
        this.brandnamelist = new ArrayList<>();

        this.confirm = getResources().getString(R.string.gomainpage);

        this.setFaculty();

        SharedPreferences lang = getSharedPreferences("language",Context.MODE_PRIVATE);
        langcode = lang.getInt("languageCode",0);

        if(langcode == 0)
        {
            this.distanceArray = getResources().getStringArray(R.array.distance_title);
        }
        else
        {
            this.distanceArray = getResources().getStringArray(R.array.distance_titleEN);
        }

        notfoundresult = getResources().getString(R.string.notfoundresult);
        notfound = getResources().getString(R.string.notfound);
        sharedPreferences = getSharedPreferences("filter", MODE_PRIVATE);
        String minPrice_Test = sharedPreferences.getString("minPrice", "no minprice value");
        String maxPrice_Test = sharedPreferences.getString("maxPrice", "no maxprice value");
        if (!minPrice_Test.equals("0") && !maxPrice_Test.equals("0"))
        {
            priceCheck = true;
            this.minprice = Integer.parseInt(getIntent().getStringExtra("minPrice"));
            this.maxprice = Integer.parseInt(getIntent().getStringExtra("maxPrice"));
            //Toast.makeText(this,minprice+"  "+maxprice,Toast.LENGTH_LONG).show();
        }
        else
        {
            priceCheck = false;
        }

        Gson gson = new Gson();
        String json = sharedPreferences.getString("facility", "");
        Type type = new TypeToken<ArrayList<Facility>>() {
        }.getType();
        facilities = gson.fromJson(json, type);
        System.out.println("SIZE FAC: " + facilities.size());
        //this.facilities = getIntent().getParcelableArrayListExtra("facilityList");

        for (int count = 0; count < this.facilities.size(); count++) {
            System.out.println("FAC FROM FILTER " + facilities.get(count).getTextTH() + "   MONEY");
        }


        if (facilities.size() > 0) {
            facilityCheck = true;
        } else if (facilities.size() == 0) {
            facilityCheck = false;
        }

        this.distanceDescribe = sharedPreferences.getString("distanceChoice", "no value distance");
        this.distanceDouble = Double.parseDouble(sharedPreferences.getString("distanceDouble", "0"));
        // System.out.println(distanceDescribe+"  DISTANCE");
        Double d1 = new Double(this.distanceDouble);
        Double d2 = new Double(0);
        int retval = d1.compareTo(d2);
        if (distanceDescribe.equals("") && retval == 0) {
            distanceCheck = false;
        } else {
            distanceCheck = true;
        }
        this.codeStyle = sharedPreferences.getInt("codeStyle", 4);
        this.dormStyle = sharedPreferences.getString("dormStyle", "no value dormStyle");
        this.faculty = sharedPreferences.getString("faculty", "no value faculty");

        //System.out.println(minprice+"  "+maxprice+"  "+facilities.size()+"  "+distance+"  "+distanceDouble);
        // System.out.println("Price: "+priceCheck+"  Distance: "+distanceCheck+"  Facility: "+facilityCheck);
        this.bar = getSupportActionBar();
        String search = getResources().getString(R.string.resultofSearch);
        this.bar.setTitle(search);

        final SharedPreferences sharedPreferences = getSharedPreferences("selectDorm", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("dormname", "no var");
        editor.putString("pathImage360", "no path");
        editor.putInt("numberofFavorite", 0);

        System.out.println("PRICE : " + priceCheck + "   DISTANCE: " + distanceCheck + "  FACILITY: " + facilityCheck);

        if (codeStyle == 0 || codeStyle == 1 || codeStyle == 2) {
            this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));
        } else if (codeStyle == 3) {
            this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        }


        this.bar.setDisplayHomeAsUpEnabled(true);
        this.recyclerview = (RecyclerView) findViewById(R.id.recyclerView);

        facultyOfUser = faculty;
        final String[] array = getResources().getStringArray(R.array.faculty);

        if (facultyOfUser.equals(array[1])) {
            facofuser = scienceAndTechnology;
            //plist = scienceAndTechnology.getPlacelist();

        } else if (facultyOfUser.equals(array[2])) {
            facofuser = law;
            //plist = law.getPlacelist();

        } else if (facultyOfUser.equals(array[3])) {
            facofuser = politicalScience;
            //plist = politicalScience.getPlacelist();

        } else if (facultyOfUser.equals(array[4])) {
            facofuser = account;
            //plist = account.getPlacelist();

        } else if (facultyOfUser.equals(array[5])) {
            facofuser = liberalArt;
            //plist = liberalArt.getPlacelist();

        } else if (facultyOfUser.equals(array[6])) {
            facofuser = architect;
            //plist = architect.getPlacelist();

        } else if (facultyOfUser.equals(array[7])) {
            facofuser = economy;
            //plist = economy.getPlacelist();

        } else if (facultyOfUser.equals(array[8])) {
            facofuser = social;
            //plist = social.getPlacelist();

        } else if (facultyOfUser.equals(array[9])) {
            facofuser = jouranlism;
            //plist = jouranlism.getPlacelist();

        } else if (facultyOfUser.equals(array[10])) {
            facofuser = humanism;
            //plist = humanism.getPlacelist();

        } else if (facultyOfUser.equals(array[11])) {
            facofuser = engineer;
            //plist = engineer.getPlacelist();

        } else if (facultyOfUser.equals(array[12])) {
            facofuser = medicine;
            //plist = medicine.getPlacelist();

        } else if (facultyOfUser.equals(array[13])) {
            facofuser = alliedHealth;
            //plist = alliedHealth.getPlacelist();

        } else if (facultyOfUser.equals(array[14])) {
            facofuser = dentistry;
            //plist = dentistry.getPlacelist();

        } else if (facultyOfUser.equals(array[15])) {
            facofuser = nurse;
            //plist = nurse.getPlacelist();

        } else if (facultyOfUser.equals(array[16])) {
            facofuser = fineArt;
            //plist = fineArt.getPlacelist();

        } else if (facultyOfUser.equals(array[17])) {
            facofuser = publicHealth;
            //plist = publicHealth.getPlacelist();

        } else if (facultyOfUser.equals(array[18])) {
            facofuser = pharmacy;
            //plist = pharmacy.getPlacelist();

        } else if (facultyOfUser.equals(array[19])) {
            facofuser = studyAndknowledge;
            //plist = studyAndknowledge.getPlacelist();

        } else if (facultyOfUser.equals(array[20])) {
            facofuser = siit;
            //plist = siit.getPlacelist();

        } else if (facultyOfUser.equals(array[21])) {
            facofuser = puey;
            //plist = puey.getPlacelist();

        }

        final RequestQueue queue = Volley.newRequestQueue(this);
        if (codeStyle == 0)
        {
            type1 = getSharedPreferences("resultfromfilter",MODE_PRIVATE);
            editor1 = type1.edit();
            editor1.putString("result","initial value");
            editor1.commit();
            if (priceCheck == true && distanceCheck == false && facilityCheck == false)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {
                        System.out.println("Size of model: "+dorm1.size());
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter forfilter = new RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter(dorm1, getApplicationContext(),langcode);
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerview.setAdapter(forfilter);
                            forfilter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    String dormName = pricelist.get(position).getDorm_name();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_PriceAndPromotion.class);
                                    i.putExtra("dormName", dormName);
                                    editor.putString("dormname", dormName);
                                    editor.commit();
                                    startActivity(i);
                                }
                            });
                        }
                        else if(dorm1.size() == 0)
                        {
                            if(nub == 1)
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                        startActivity(intent);
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });

            }
            else if (distanceCheck == true && priceCheck == false && facilityCheck == false)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {
                        System.out.println("Size of model: "+dorm1.size());
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter forfilter = new RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter(dorm1, getApplicationContext(),langcode);
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerview.setAdapter(forfilter);
                            forfilter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    String dormName = pricelist.get(position).getDorm_name();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_PriceAndPromotion.class);
                                    i.putExtra("dormName", dormName);
                                    editor.putString("dormname", dormName);
                                    editor.commit();
                                    startActivity(i);
                                }
                            });


                        }
                        else if(dorm1.size() == 0)
                        {
                            if(nub == 1)
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                        startActivity(intent);
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
            else if (facilityCheck == true && priceCheck == false && distanceCheck == false)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {
                        System.out.println("Size of model: "+dorm1.size());
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter forfilter = new RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter(dorm1, getApplicationContext(),langcode);
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerview.setAdapter(forfilter);
                            forfilter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    String dormName = pricelist.get(position).getDorm_name();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_PriceAndPromotion.class);
                                    i.putExtra("dormName", dormName);
                                    editor.putString("dormname", dormName);
                                    editor.commit();
                                    startActivity(i);
                                }
                            });


                        }
                        else if(dorm1.size() == 0)
                        {
                            if(nub == 1)
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                        startActivity(intent);
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
            else if (priceCheck == true && distanceCheck == true && facilityCheck == false)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {
                        System.out.println("Size of model: "+dorm1.size());
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter forfilter = new RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter(dorm1, getApplicationContext(),langcode);
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerview.setAdapter(forfilter);
                            forfilter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    String dormName = pricelist.get(position).getDorm_name();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_PriceAndPromotion.class);
                                    i.putExtra("dormName", dormName);
                                    editor.putString("dormname", dormName);
                                    editor.commit();
                                    startActivity(i);
                                }
                            });


                        }
                        else if(dorm1.size() == 0)
                        {
                            if(nub == 1)
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                        startActivity(intent);
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
            else if (priceCheck == true && facilityCheck == true && distanceCheck == false)
            {
                    setNotification(new Callback() {
                        @Override
                        public void onSuccess(ArrayList<String> list) {

                        }

                        @Override
                        public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {
                            System.out.println("Size of model: "+dorm1.size());
                            nub++;
                            if(dorm1.size() > 0)
                            {
                                RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter forfilter = new RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter(dorm1, getApplicationContext(),langcode);
                                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                recyclerview.setAdapter(forfilter);
                                forfilter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        String dormName = pricelist.get(position).getDorm_name();
                                        Intent i = new Intent(getApplicationContext(), Detail_Dorm_PriceAndPromotion.class);
                                        i.putExtra("dormName", dormName);
                                        editor.putString("dormname", dormName);
                                        editor.commit();
                                        startActivity(i);
                                    }
                                });


                            }
                            else if(dorm1.size() == 0)
                            {
                                if(nub == 1)
                                {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                    String title = getResources().getString(R.string.notfound);
                                    String subtitle = getResources().getString(R.string.notfoundresult);
                                    dialog.setTitle(subtitle);
                                    dialog.setIcon(R.drawable.alert);
                                    dialog.setMessage(title);
                                    dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                            startActivity(intent);
                                        }
                                    });
                                    dialog.show();
                                }
                            }
                        }

                        @Override
                        public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    });
            }
            else if (priceCheck == false && distanceCheck == true && facilityCheck == true)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {
                        System.out.println("Size of model: "+dorm1.size());
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter forfilter = new RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter(dorm1, getApplicationContext(),langcode);
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerview.setAdapter(forfilter);
                            forfilter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    String dormName = pricelist.get(position).getDorm_name();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_PriceAndPromotion.class);
                                    i.putExtra("dormName", dormName);
                                    editor.putString("dormname", dormName);
                                    editor.commit();
                                    startActivity(i);
                                }
                            });


                        }
                        else if(dorm1.size() == 0)
                        {
                            if(nub == 1)
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                        startActivity(intent);
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
            else if (priceCheck == true && distanceCheck == true && facilityCheck == true)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {
                        System.out.println("Size of model: "+dorm1.size());
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter forfilter = new RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter(dorm1, getApplicationContext(),langcode);
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerview.setAdapter(forfilter);
                            forfilter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    String dormName = pricelist.get(position).getDorm_name();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_PriceAndPromotion.class);
                                    i.putExtra("dormName", dormName);
                                    editor.putString("dormname", dormName);
                                    editor.commit();
                                    startActivity(i);
                                }
                            });
                        }
                        else if(dorm1.size() == 0)
                        {
                            if(nub == 1)
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                        startActivity(intent);
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
        }
        // DORM STYLE INFORMATION
        else if (codeStyle == 1)
        {
            final RequestQueue reqqueue = Volley.newRequestQueue(getApplicationContext());
            if (priceCheck == true && distanceCheck == false && facilityCheck == false)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {
                        System.out.println("Size of model: "+dorm1.size());
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_Information informationAdapter = new RecyclerViewAdapter_Type_Information(dorm1,getApplicationContext(),langcode);
                            recyclerview.setAdapter(informationAdapter);
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            informationAdapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    String dormName = informationlist.get(position).getName();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_Information.class);
                                    i.putExtra("dormName", dormName);
                                    editor.putString("dormname", dormName);
                                    editor.commit();
                                    startActivity(i);
                                }
                            });
                        }
                        else if(dorm1.size() == 0)
                        {
                            if(nub == 1)
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                        startActivity(intent);
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
            else if (distanceCheck == true && priceCheck == false && facilityCheck == false)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {
                        System.out.println("Size of model: "+dorm1.size());
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_Information informationAdapter = new RecyclerViewAdapter_Type_Information(dorm1,getApplicationContext(),langcode);
                            recyclerview.setAdapter(informationAdapter);
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            informationAdapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    String dormName = informationlist.get(position).getName();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_Information.class);
                                    i.putExtra("dormName", dormName);
                                    editor.putString("dormname", dormName);
                                    editor.commit();
                                    startActivity(i);
                                }
                            });
                        }
                        else if(dorm1.size() == 0)
                        {
                            if(nub == 1)
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                        startActivity(intent);
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
            else if (facilityCheck == true && priceCheck == false && distanceCheck == false)
            {
                    setNotification(new Callback() {
                        @Override
                        public void onSuccess(ArrayList<String> list) {

                        }

                        @Override
                        public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {
                            System.out.println("Size of model: "+dorm1.size());
                            nub++;
                            if(dorm1.size() > 0)
                            {
                                RecyclerViewAdapter_Type_Information informationAdapter = new RecyclerViewAdapter_Type_Information(dorm1,getApplicationContext(),langcode);
                                recyclerview.setAdapter(informationAdapter);
                                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                informationAdapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        String dormName = informationlist.get(position).getName();
                                        Intent i = new Intent(getApplicationContext(), Detail_Dorm_Information.class);
                                        i.putExtra("dormName", dormName);
                                        editor.putString("dormname", dormName);
                                        editor.commit();
                                        startActivity(i);
                                    }
                                });
                            }
                            else if(dorm1.size() == 0)
                            {
                                if(nub == 1)
                                {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                    String title = getResources().getString(R.string.notfound);
                                    String subtitle = getResources().getString(R.string.notfoundresult);
                                    dialog.setTitle(subtitle);
                                    dialog.setIcon(R.drawable.alert);
                                    dialog.setMessage(title);
                                    dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                            startActivity(intent);
                                        }
                                    });
                                    dialog.show();
                                }
                            }
                        }

                        @Override
                        public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    });
            }
            else if (priceCheck == true && distanceCheck == true && facilityCheck == false)
            {
                    setNotification(new Callback() {
                        @Override
                        public void onSuccess(ArrayList<String> list) {

                        }

                        @Override
                        public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {
                            System.out.println("Size of model: "+dorm1.size());
                            nub++;
                            if(dorm1.size() > 0)
                            {
                                RecyclerViewAdapter_Type_Information informationAdapter = new RecyclerViewAdapter_Type_Information(dorm1,getApplicationContext(),langcode);
                                recyclerview.setAdapter(informationAdapter);
                                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                informationAdapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        String dormName = informationlist.get(position).getName();
                                        Intent i = new Intent(getApplicationContext(), Detail_Dorm_Information.class);
                                        i.putExtra("dormName", dormName);
                                        editor.putString("dormname", dormName);
                                        editor.commit();
                                        startActivity(i);
                                    }
                                });
                            }
                            else if(dorm1.size() == 0)
                            {
                                if(nub == 1)
                                {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                    String title = getResources().getString(R.string.notfound);
                                    String subtitle = getResources().getString(R.string.notfoundresult);
                                    dialog.setTitle(subtitle);
                                    dialog.setIcon(R.drawable.alert);
                                    dialog.setMessage(title);
                                    dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                            startActivity(intent);
                                        }
                                    });
                                    dialog.show();
                                }
                            }
                        }

                        @Override
                        public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    });
            }
            else if (priceCheck == true && facilityCheck == true && distanceCheck == false)
            {
                    setNotification(new Callback() {
                        @Override
                        public void onSuccess(ArrayList<String> list) {

                        }

                        @Override
                        public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {
                            System.out.println("Size of model: "+dorm1.size());
                            nub++;
                            if(dorm1.size() > 0)
                            {
                                RecyclerViewAdapter_Type_Information informationAdapter = new RecyclerViewAdapter_Type_Information(dorm1,getApplicationContext(),langcode);
                                recyclerview.setAdapter(informationAdapter);
                                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                informationAdapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        String dormName = informationlist.get(position).getName();
                                        Intent i = new Intent(getApplicationContext(), Detail_Dorm_Information.class);
                                        i.putExtra("dormName", dormName);
                                        editor.putString("dormname", dormName);
                                        editor.commit();
                                        startActivity(i);
                                    }
                                });
                            }
                            else if(dorm1.size() == 0)
                            {
                                if(nub == 1)
                                {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                    String title = getResources().getString(R.string.notfound);
                                    String subtitle = getResources().getString(R.string.notfoundresult);
                                    dialog.setTitle(subtitle);
                                    dialog.setIcon(R.drawable.alert);
                                    dialog.setMessage(title);
                                    dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                            startActivity(intent);
                                        }
                                    });
                                    dialog.show();
                                }
                            }
                        }

                        @Override
                        public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    });
            }
            else if (priceCheck == false && distanceCheck == true && facilityCheck == true)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {
                        System.out.println("Size of model: "+dorm1.size());
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_Information informationAdapter = new RecyclerViewAdapter_Type_Information(dorm1,getApplicationContext(),langcode);
                            recyclerview.setAdapter(informationAdapter);
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            informationAdapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    String dormName = informationlist.get(position).getName();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_Information.class);
                                    i.putExtra("dormName", dormName);
                                    editor.putString("dormname", dormName);
                                    editor.commit();
                                    startActivity(i);
                                }
                            });
                        }
                        else if(dorm1.size() == 0)
                        {
                            if(nub == 1)
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                        startActivity(intent);
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
            else if (priceCheck == true && distanceCheck == true && facilityCheck == true)
            {
                    setNotification(new Callback() {
                        @Override
                        public void onSuccess(ArrayList<String> list) {

                        }

                        @Override
                        public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {
                            System.out.println("Size of model: "+dorm1.size());
                            nub++;
                            if(dorm1.size() > 0)
                            {
                                RecyclerViewAdapter_Type_Information informationAdapter = new RecyclerViewAdapter_Type_Information(dorm1,getApplicationContext(),langcode);
                                recyclerview.setAdapter(informationAdapter);
                                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                informationAdapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        String dormName = informationlist.get(position).getName();
                                        Intent i = new Intent(getApplicationContext(), Detail_Dorm_Information.class);
                                        i.putExtra("dormName", dormName);
                                        editor.putString("dormname", dormName);
                                        editor.commit();
                                        startActivity(i);
                                    }
                                });
                            }
                            else if(dorm1.size() == 0)
                            {
                                if(nub == 1)
                                {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                    String title = getResources().getString(R.string.notfound);
                                    String subtitle = getResources().getString(R.string.notfoundresult);
                                    dialog.setTitle(subtitle);
                                    dialog.setIcon(R.drawable.alert);
                                    dialog.setMessage(title);
                                    dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                            startActivity(intent);
                                        }
                                    });
                                    dialog.show();
                                }
                            }
                        }

                        @Override
                        public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    });
            }
        }
        else if(codeStyle ==2)
        {
            final RequestQueue requestQueue = Volley.newRequestQueue(this);
            if(priceCheck == true && distanceCheck == false && facilityCheck == false)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {
                            nub++;
                            if(dorm1.size() > 0)
                            {
                                RecyclerViewAdapter_Type_Quality adapter = new RecyclerViewAdapter_Type_Quality(dorm1,getApplicationContext());
                                recyclerview.setAdapter(adapter);
                                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {

                                        String dormName = qualitylist.get(position).getName();
                                        Intent i = new Intent(getApplicationContext(), Detail_Dorm_Quality.class);
                                        i.putExtra("dormname",dormName);
                                        editor.putString("dormname",dormName);
                                        editor.commit();
                                        startActivity(i);
                                    }
                                });
                            }
                            else if(dorm1.size() == 0)
                            {
                                if(nub == 1)
                                {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                    String title = getResources().getString(R.string.notfound);
                                    String subtitle = getResources().getString(R.string.notfoundresult);
                                    dialog.setTitle(subtitle);
                                    dialog.setIcon(R.drawable.alert);
                                    dialog.setMessage(title);
                                    dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                            startActivity(intent);
                                        }
                                    });
                                    dialog.show();
                                }
                            }
                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
            else if(priceCheck == false && distanceCheck == true && facilityCheck == false)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_Quality adapter = new RecyclerViewAdapter_Type_Quality(dorm1,getApplicationContext());
                            recyclerview.setAdapter(adapter);
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            adapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

                                    String dormName = qualitylist.get(position).getName();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_Quality.class);
                                    i.putExtra("dormname",dormName);
                                    editor.putString("dormname",dormName);
                                    editor.commit();
                                    startActivity(i);
                                }
                            });
                        }
                        else if(dorm1.size() == 0)
                        {
                            if(nub == 1)
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                        startActivity(intent);
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
            else if(priceCheck == false && distanceCheck == false && facilityCheck == true)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_Quality adapter = new RecyclerViewAdapter_Type_Quality(dorm1,getApplicationContext());
                            recyclerview.setAdapter(adapter);
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            adapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

                                    String dormName = qualitylist.get(position).getName();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_Quality.class);
                                    i.putExtra("dormname",dormName);
                                    editor.putString("dormname",dormName);
                                    editor.commit();
                                    startActivity(i);
                                }
                            });
                        }
                        else if(dorm1.size() == 0)
                        {
                            if(nub == 1)
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                        startActivity(intent);
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
            else if(priceCheck == true && distanceCheck == true && facilityCheck == false)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_Quality adapter = new RecyclerViewAdapter_Type_Quality(dorm1,getApplicationContext());
                            recyclerview.setAdapter(adapter);
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            adapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

                                    String dormName = qualitylist.get(position).getName();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_Quality.class);
                                    i.putExtra("dormname",dormName);
                                    editor.putString("dormname",dormName);
                                    editor.commit();
                                    startActivity(i);
                                }
                            });
                        }
                        else if(dorm1.size() == 0)
                        {
                            if(nub == 1)
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                        startActivity(intent);
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
            else if(priceCheck == true && distanceCheck == false && facilityCheck == true)
            {
                    setNotification(new Callback() {
                        @Override
                        public void onSuccess(ArrayList<String> list) {

                        }

                        @Override
                        public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {
                            nub++;
                            if(dorm1.size() > 0)
                            {
                                RecyclerViewAdapter_Type_Quality adapter = new RecyclerViewAdapter_Type_Quality(dorm1,getApplicationContext());
                                recyclerview.setAdapter(adapter);
                                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {

                                        String dormName = qualitylist.get(position).getName();
                                        Intent i = new Intent(getApplicationContext(), Detail_Dorm_Quality.class);
                                        i.putExtra("dormname",dormName);
                                        editor.putString("dormname",dormName);
                                        editor.commit();
                                        startActivity(i);
                                    }
                                });
                            }
                            else if(dorm1.size() == 0)
                            {
                                if(nub == 1)
                                {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                    String title = getResources().getString(R.string.notfound);
                                    String subtitle = getResources().getString(R.string.notfoundresult);
                                    dialog.setTitle(subtitle);
                                    dialog.setIcon(R.drawable.alert);
                                    dialog.setMessage(title);
                                    dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                            startActivity(intent);
                                        }
                                    });
                                    dialog.show();
                                }
                            }
                        }

                        @Override
                        public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    });
            }
            else if(priceCheck == false && distanceCheck == true && facilityCheck == true)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_Quality adapter = new RecyclerViewAdapter_Type_Quality(dorm1,getApplicationContext());
                            recyclerview.setAdapter(adapter);
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            adapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

                                    String dormName = qualitylist.get(position).getName();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_Quality.class);
                                    i.putExtra("dormname",dormName);
                                    editor.putString("dormname",dormName);
                                    editor.commit();
                                    startActivity(i);
                                }
                            });
                        }
                        else if(dorm1.size() == 0)
                        {
                            if(nub == 1)
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                        startActivity(intent);
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }
                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
            else if(priceCheck == true && distanceCheck == true && facilityCheck == true)
            {
                    setNotification(new Callback() {
                        @Override
                        public void onSuccess(ArrayList<String> list) {

                        }

                        @Override
                        public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {
                            nub++;
                            if(dorm1.size() > 0)
                            {
                                RecyclerViewAdapter_Type_Quality adapter = new RecyclerViewAdapter_Type_Quality(dorm1,getApplicationContext());
                                recyclerview.setAdapter(adapter);
                                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {

                                        String dormName = qualitylist.get(position).getName();
                                        Intent i = new Intent(getApplicationContext(), Detail_Dorm_Quality.class);
                                        i.putExtra("dormname",dormName);
                                        editor.putString("dormname",dormName);
                                        editor.commit();
                                        startActivity(i);
                                    }
                                });
                            }
                            else if(dorm1.size() == 0)
                            {
                                if(nub == 1)
                                {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                    String title = getResources().getString(R.string.notfound);
                                    String subtitle = getResources().getString(R.string.notfoundresult);
                                    dialog.setTitle(subtitle);
                                    dialog.setIcon(R.drawable.alert);
                                    dialog.setMessage(title);
                                    dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                            startActivity(intent);
                                        }
                                    });
                                    dialog.show();
                                }
                            }
                        }

                        @Override
                        public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    });
            }
        }
        else if(codeStyle == 3)
        {
            final RequestQueue requestqueue = Volley.newRequestQueue(this);
            if(priceCheck == true && distanceCheck == false && facilityCheck == false)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {
                            nub++;
                            if(dorm1.size() > 0)
                            {
                                RecyclerViewAdapter_Type_Brandname adapter = new RecyclerViewAdapter_Type_Brandname(dorm1,getApplicationContext());
                                recyclerview.setAdapter(adapter);
                                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        String dormId = brandnamelist.get(position).getName();
                                        Intent i = new Intent(getApplicationContext(), Detail_Dorm_BrandName.class);
                                        i.putExtra("dormName",dormId);
                                        editor.putString("dormname",dormId);
                                        editor.commit();
                                        startActivity(i);
                                    }
                                });
                            }
                            else if(dorm1.size() == 0)
                            {
                                if(nub == 1)
                                {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                    String title = getResources().getString(R.string.notfound);
                                    String subtitle = getResources().getString(R.string.notfoundresult);
                                    dialog.setTitle(subtitle);
                                    dialog.setIcon(R.drawable.alert);
                                    dialog.setMessage(title);
                                    dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                            startActivity(intent);
                                        }
                                    });
                                    dialog.show();
                                }
                            }
                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });

            }
            else if(priceCheck == false && distanceCheck == true && facilityCheck == false)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_Brandname adapter = new RecyclerViewAdapter_Type_Brandname(dorm1,getApplicationContext());
                            recyclerview.setAdapter(adapter);
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            adapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    String dormId = brandnamelist.get(position).getName();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_BrandName.class);
                                    i.putExtra("dormName",dormId);
                                    editor.putString("dormname",dormId);
                                    editor.commit();
                                    startActivity(i);
                                }
                            });
                        }
                        else if(dorm1.size() == 0)
                        {
                            if(nub == 1)
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                        startActivity(intent);
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
            else if(priceCheck == false && distanceCheck == false && facilityCheck == true)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_Brandname adapter = new RecyclerViewAdapter_Type_Brandname(dorm1,getApplicationContext());
                            recyclerview.setAdapter(adapter);
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            adapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    String dormId = brandnamelist.get(position).getName();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_BrandName.class);
                                    i.putExtra("dormName",dormId);
                                    editor.putString("dormname",dormId);
                                    editor.commit();
                                    startActivity(i);
                                }
                            });
                        }
                        else if(dorm1.size() == 0)
                        {
                            if(nub == 1)
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                        startActivity(intent);
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
            else if(priceCheck == true && distanceCheck == true && facilityCheck == false)
            {
                    setNotification(new Callback() {
                        @Override
                        public void onSuccess(ArrayList<String> list) {

                        }

                        @Override
                        public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {
                            nub++;
                            if(dorm1.size() > 0)
                            {
                                RecyclerViewAdapter_Type_Brandname adapter = new RecyclerViewAdapter_Type_Brandname(dorm1,getApplicationContext());
                                recyclerview.setAdapter(adapter);
                                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        String dormId = brandnamelist.get(position).getName();
                                        Intent i = new Intent(getApplicationContext(), Detail_Dorm_BrandName.class);
                                        i.putExtra("dormName",dormId);
                                        editor.putString("dormname",dormId);
                                        editor.commit();
                                        startActivity(i);
                                    }
                                });
                            }
                            else if(dorm1.size() == 0)
                            {
                                if(nub == 1)
                                {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                    String title = getResources().getString(R.string.notfound);
                                    String subtitle = getResources().getString(R.string.notfoundresult);
                                    dialog.setTitle(subtitle);
                                    dialog.setIcon(R.drawable.alert);
                                    dialog.setMessage(title);
                                    dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                            startActivity(intent);
                                        }
                                    });
                                    dialog.show();
                                }
                            }
                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    });
            }
            else if(priceCheck == true && distanceCheck == false && facilityCheck == true)
            {
                    setNotification(new Callback() {
                        @Override
                        public void onSuccess(ArrayList<String> list) {

                        }

                        @Override
                        public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {
                            nub++;
                            if(dorm1.size() > 0)
                            {
                                RecyclerViewAdapter_Type_Brandname adapter = new RecyclerViewAdapter_Type_Brandname(dorm1,getApplicationContext());
                                recyclerview.setAdapter(adapter);
                                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        String dormId = brandnamelist.get(position).getName();
                                        Intent i = new Intent(getApplicationContext(), Detail_Dorm_BrandName.class);
                                        i.putExtra("dormName",dormId);
                                        editor.putString("dormname",dormId);
                                        editor.commit();
                                        startActivity(i);
                                    }
                                });
                            }
                            else if(dorm1.size() == 0)
                            {
                                if(nub == 1)
                                {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                    String title = getResources().getString(R.string.notfound);
                                    String subtitle = getResources().getString(R.string.notfoundresult);
                                    dialog.setTitle(subtitle);
                                    dialog.setIcon(R.drawable.alert);
                                    dialog.setMessage(title);
                                    dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                            startActivity(intent);
                                        }
                                    });
                                    dialog.show();
                                }
                            }
                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    });
            }
            else if(priceCheck == false && distanceCheck == true && facilityCheck == true)
            {
                    setNotification(new Callback() {
                        @Override
                        public void onSuccess(ArrayList<String> list) {

                        }

                        @Override
                        public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                        }

                        @Override
                        public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {
                            nub++;
                            if(dorm1.size() > 0)
                            {
                                RecyclerViewAdapter_Type_Brandname adapter = new RecyclerViewAdapter_Type_Brandname(dorm1,getApplicationContext());
                                recyclerview.setAdapter(adapter);
                                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        String dormId = brandnamelist.get(position).getName();
                                        Intent i = new Intent(getApplicationContext(), Detail_Dorm_BrandName.class);
                                        i.putExtra("dormName",dormId);
                                        editor.putString("dormname",dormId);
                                        editor.commit();
                                        startActivity(i);
                                    }
                                });
                            }
                            else if(dorm1.size() == 0)
                            {
                                if(nub == 1)
                                {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                    String title = getResources().getString(R.string.notfound);
                                    String subtitle = getResources().getString(R.string.notfoundresult);
                                    dialog.setTitle(subtitle);
                                    dialog.setIcon(R.drawable.alert);
                                    dialog.setMessage(title);
                                    dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                            startActivity(intent);
                                        }
                                    });
                                    dialog.show();
                                }
                            }
                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    });
            }
            else if(priceCheck == true && distanceCheck == true && facilityCheck == true)
            {
                setNotification(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {

                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_Brandname adapter = new RecyclerViewAdapter_Type_Brandname(dorm1,getApplicationContext());
                            recyclerview.setAdapter(adapter);
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            adapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    String dormId = brandnamelist.get(position).getName();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_BrandName.class);
                                    i.putExtra("dormName",dormId);
                                    editor.putString("dormname",dormId);
                                    editor.commit();
                                    startActivity(i);
                                }
                            });
                        }
                        else if(dorm1.size() == 0)
                        {
                            if(nub == 1)
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Ouput_from_filter.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Ouput_from_filter.this,MainPage.class);
                                        startActivity(intent);
                                    }
                                });
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
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

    public void setNotification(Callback oncallback){
        RequestQueue queue = Volley.newRequestQueue(this);
        System.out.println("Price Check: "+priceCheck+"  distance Check: "+distanceCheck+"  facility Check: "+facilityCheck);
   if(codeStyle == 0)
   {
       // price only
       if (priceCheck == true && distanceCheck == false && facilityCheck == false)
       {
           String pathGetDormInStyle = "http://" + ip + port + "/DormTypePriceNPromotion/getAllDorm";
           JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, pathGetDormInStyle, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   try {
                       final JSONArray data = response;
                       priceModel = new Dorm_PricenPromotion_Model[data.length()];
                       for (count = 0; count < data.length(); count++) {
                           final int c = count;
                           //Toast.makeText(getApplicationContext(),String.valueOf(count),Toast.LENGTH_SHORT).show();
                           ff = count;
                           priceModel[count] = new Dorm_PricenPromotion_Model();
                           JSONObject obj = data.getJSONObject(count);
                           final String id = obj.getString("dormID");
                           final String proDetail = obj.getString("promotionDescribe");
                           // System.out.println("Promotion Detail: "+proDetail);
                           final int oldprice = obj.getInt("oldPrice");
                           final int newprice = obj.getInt("newPrice");

                           // priceModel[count].setDorm_name(id);
                           // priceModel[count].setOldPrice(oldprice);
                           // priceModel[count].setNewPrice(newprice);

                           String getPositionDorm = "http://" + ip + port + "/dorm/getDormByDormName/" + id;
                           JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getPositionDorm, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject ob = response;
                                   try {
                                       double latitude = ob.getDouble("latitude");
                                       double longtitude = ob.getDouble("longtitude");

                                       final String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?" + "origin=" + latitude + "," + longtitude + "&destination=" + facofuser.getLatitude() + "," + facofuser.getLongtitude() + "&key=" + newServerKey;
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
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++) {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   //priceModel[c].setDistance(distanceText);
                                                   priceModel[c].setDistance(disText);
                                                   priceModel[c].setDistanceDouble(Double.parseDouble(disText));
                                                   //System.out.println(Double.parseDouble(disText)+"  "+unit);


                                                   String getUsernameAndEmail = "http://" + ip + port + "/dormOwner/getInfoDormOwnerByDormName/" + id;
                                                   JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           JSONObject dormowner = response;
                                                           try {
                                                               final String username = dormowner.getString("username");
                                                               final String email = dormowner.getString("email");

                                                               String getInfoDorm = "http://" + ip + port + "/InfoDorm/getInfoDormByDormName/" + id;
                                                               JsonObjectRequest infodorm = new JsonObjectRequest(Request.Method.GET, getInfoDorm, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       JSONObject obj = response;
                                                                       try {
                                                                           final int minpp = obj.getInt("minPrice");
                                                                           final int maxpp = obj.getInt("maxPrice");

                                                                           String coverImagePath = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                           JsonObjectRequest request5 = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   JSONObject coverImage = response;
                                                                                   try {
                                                                                       final String coverImagePath = coverImage.getString("coverImage");

                                                                                       // priceModel[c].setImage(coverImagePath);

                                                                                       if (!proDetail.equals("")) {
                                                                                           int statusPromotion = 1;
                                                                                           if (newprice >= minprice && newprice <= maxprice) {
                                                                                               System.out.println(id + "  " + disText + " and distance that user define: " + distanceDouble);
                                                                                               priceModel[c].setDorm_name(id);
                                                                                               priceModel[c].setOldPrice(oldprice);
                                                                                               priceModel[c].setNewPrice(newprice);
                                                                                               //priceModel[c].setDistance(disText);
                                                                                               priceModel[c].setImage(coverImagePath);
                                                                                               priceModel[c].setStatus_promotion(statusPromotion);
                                                                                               priceModel[c].setFaculty(facofuser);
                                                                                               pricelist.add(priceModel[c]);
                                                                                           }
                                                                                       } else {
                                                                                           if (minpp >= minprice && minpp <= maxprice) {
                                                                                               priceModel[c].setDorm_name(id);
                                                                                               priceModel[c].setOldPrice(minpp);
                                                                                               //priceModel[c].setDistance(disText);
                                                                                               priceModel[c].setImage(coverImagePath);
                                                                                               priceModel[c].setFaculty(facofuser);
                                                                                               pricelist.add(priceModel[c]);
                                                                                           }
                                                                                       }

                                                                                       count2++;
                                                                                       System.out.println("count2: " + count2);
                                                                                       if (count2 == data.length()) {
                                                                                           oncallback.onSuccessType1(pricelist);
                                                                                       }
                                                                                   } catch (JSONException ex) {
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
                                                               queue.add(infodorm);


                                                           } catch (JSONException ex) {
                                                               ex.printStackTrace();
                                                           }

                                                       }
                                                   }, new Response.ErrorListener() {
                                                       @Override
                                                       public void onErrorResponse(VolleyError error) {

                                                       }
                                                   });

                                                   queue.add(request4);
                                               } catch (JSONException ex) {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });
                                       queue.add(request3);
                                   } catch (JSONException ex) {
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

                   } catch (JSONException ex) {
                       ex.printStackTrace();
                   }

               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }
           });
           queue.add(request);
       }
       // distance only
       else if (priceCheck == false && distanceCheck == true && facilityCheck == false)
       {
           String pathGetDormInStyle = "http://" + ip + port + "/DormTypePriceNPromotion/getAllDorm";
           JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, pathGetDormInStyle, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   try {
                       final JSONArray data = response;
                       priceModel = new Dorm_PricenPromotion_Model[data.length()];
                       for (count = 0; count < data.length(); count++) {
                           final int c = count;
                           priceModel[count] = new Dorm_PricenPromotion_Model();
                           JSONObject obj = data.getJSONObject(count);
                           final String id = obj.getString("dormID");
                           final String proDetail = obj.getString("promotionDescribe");
                           // System.out.println("Promotion Detail: "+proDetail);
                           final int oldprice = obj.getInt("oldPrice");
                           final int newprice = obj.getInt("newPrice");

                           // priceModel[count].setDorm_name(id);
                           // priceModel[count].setOldPrice(oldprice);
                           // priceModel[count].setNewPrice(newprice);

                           String getPositionDorm = "http://" + ip + port + "/dorm/getDormByDormName/" + id;
                           JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getPositionDorm, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject ob = response;
                                   try {
                                       double latitude = ob.getDouble("latitude");
                                       double longtitude = ob.getDouble("longtitude");

                                       final String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?" + "origin=" + latitude + "," + longtitude + "&destination=" + facofuser.getLatitude() + "," + facofuser.getLongtitude() + "&key=" + newServerKey;
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
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++) {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   //priceModel[c].setDistance(distanceText);
                                                   priceModel[c].setDistance(disText);
                                                   priceModel[c].setDistanceDouble(Double.parseDouble(disText));
                                                   //System.out.println(Double.parseDouble(disText)+"  "+unit);


                                                   String getUsernameAndEmail = "http://" + ip + port + "/dormOwner/getInfoDormOwnerByDormName/" + id;
                                                   JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           JSONObject dormowner = response;
                                                           try {
                                                               final String username = dormowner.getString("username");
                                                               final String email = dormowner.getString("email");

                                                               String getInfoDorm = "http://" + ip + port + "/InfoDorm/getInfoDormByDormName/" + id;
                                                               JsonObjectRequest infodorm = new JsonObjectRequest(Request.Method.GET, getInfoDorm, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       JSONObject obj = response;
                                                                       try {
                                                                           final int minpp = obj.getInt("minPrice");
                                                                           final int maxpp = obj.getInt("maxPrice");

                                                                           String coverImagePath = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                           JsonObjectRequest request5 = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   JSONObject coverImage = response;
                                                                                   try {
                                                                                       final String coverImagePath = coverImage.getString("coverImage");

                                                                                       // priceModel[c].setImage(coverImagePath);

                                                                                       if (!proDetail.equals("")) {
                                                                                           int statusPromotion = 1;
                                                                                           System.out.println(id + "  " + disText + " and distance that user define: " + distanceDouble);
                                                                                           priceModel[c].setDorm_name(id);
                                                                                           priceModel[c].setOldPrice(oldprice);
                                                                                           priceModel[c].setNewPrice(newprice);
                                                                                           //priceModel[c].setDistance(disText);
                                                                                           priceModel[c].setImage(coverImagePath);
                                                                                           priceModel[c].setStatus_promotion(statusPromotion);
                                                                                           priceModel[c].setFaculty(facofuser);
                                                                                           //pricelist.add(priceModel[c]);


                                                                                           //    priceModel[c].setStatus_promotion(statusPromotion);
                                                                                           //    priceModel[c].setFaculty(facofuser);
                                                                                           //    pricelist.add(priceModel[c]);

                                                                                       } else {
                                                                                           //System.out.println(id+"   "+minpp+"  "+maxpp);
                                                                                           //int statusPromotion = 0;
                                                                                           priceModel[c].setDorm_name(id);
                                                                                           priceModel[c].setOldPrice(minpp);
                                                                                           //priceModel[c].setDistance(disText);
                                                                                           priceModel[c].setImage(coverImagePath);
                                                                                           priceModel[c].setFaculty(facofuser);
                                                                                           //pricelist.add(priceModel[c]);


                                                                                           //   priceModel[c].setStatus_promotion(statusPromotion);
                                                                                           //   priceModel[c].setFaculty(facofuser);
                                                                                           //   pricelist.add(priceModel[c]);
                                                                                       }
                                                                                       Double d1 = new Double(priceModel[c].getDistanceDouble());
                                                                                       Double d2 = new Double(distanceDouble);
                                                                                       int retval = d1.compareTo(d2);
                                                                                       if (distanceDescribe.equals(distanceArray[0]))
                                                                                       {
                                                                                           if (retval < 0 || retval == 0) {
                                                                                               pricelist.add(priceModel[c]);
                                                                                           }

                                                                                       }
                                                                                       else if (distanceDescribe.equals(distanceArray[1]))
                                                                                       {
                                                                                           if (retval < 0 || retval == 0)
                                                                                           {
                                                                                               pricelist.add(priceModel[c]);
                                                                                           }
                                                                                       }
                                                                                       else if (distanceDescribe.equals(distanceArray[2]))
                                                                                       {
                                                                                           if (retval < 0 || retval == 0)
                                                                                           {
                                                                                               pricelist.add(priceModel[c]);
                                                                                           }
                                                                                       }
                                                                                       else if (distanceDescribe.equals(distanceArray[3]))
                                                                                       {
                                                                                           if (retval < 0 || retval == 0)
                                                                                           {
                                                                                               pricelist.add(priceModel[c]);
                                                                                           }
                                                                                       }

                                                                                       for(int a=0;a<pricelist.size();a++)
                                                                                       {
                                                                                           System.out.println("หอพักที่ตรงตามเกณฑ์: "+pricelist.get(a).getDorm_name());
                                                                                       }

                                                                                       count2++;
                                                                                       if(count2 == data.length())
                                                                                       {
                                                                                           oncallback.onSuccessType1(pricelist);
                                                                                       }


                                                                                   } catch (JSONException ex) {
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
                                                               queue.add(infodorm);


                                                           } catch (JSONException ex) {
                                                               ex.printStackTrace();
                                                           }

                                                       }
                                                   }, new Response.ErrorListener() {
                                                       @Override
                                                       public void onErrorResponse(VolleyError error) {

                                                       }
                                                   });

                                                   queue.add(request4);
                                               } catch (JSONException ex) {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });
                                       queue.add(request3);
                                   } catch (JSONException ex) {
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
                   } catch (JSONException ex) {
                       ex.printStackTrace();
                   }

               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }
           });
           queue.add(request);
       }
       // facility only
       else if (priceCheck == false && distanceCheck == false && facilityCheck == true)
       {
           String pathGetDormInStyle = "http://" + ip + port + "/DormTypePriceNPromotion/getAllDorm";
           JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, pathGetDormInStyle, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   try {
                       final JSONArray data = response;
                       priceModel = new Dorm_PricenPromotion_Model[data.length()];
                       for (count = 0; count < data.length(); count++) {
                           final int c = count;
                           priceModel[count] = new Dorm_PricenPromotion_Model();
                           JSONObject obj = data.getJSONObject(count);
                           final String id = obj.getString("dormID");
                           final String proDetail = obj.getString("promotionDescribe");
                           // System.out.println("Promotion Detail: "+proDetail);
                           final int oldprice = obj.getInt("oldPrice");
                           final int newprice = obj.getInt("newPrice");

                           // priceModel[count].setDorm_name(id);
                           // priceModel[count].setOldPrice(oldprice);
                           // priceModel[count].setNewPrice(newprice);

                           String getPositionDorm = "http://" + ip + port + "/dorm/getDormByDormName/" + id;
                           JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getPositionDorm, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject ob = response;
                                   try {
                                       double latitude = ob.getDouble("latitude");
                                       double longtitude = ob.getDouble("longtitude");

                                       final String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?" + "origin=" + latitude + "," + longtitude + "&destination=" + facofuser.getLatitude() + "," + facofuser.getLongtitude() + "&key=" + newServerKey;
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
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++) {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   //priceModel[c].setDistance(distanceText);
                                                   priceModel[c].setDistance(disText);
                                                   priceModel[c].setDistanceDouble(Double.parseDouble(disText));
                                                   System.out.println(Double.parseDouble(disText) + "  " + unit);


                                                   String getUsernameAndEmail = "http://" + ip + port + "/dormOwner/getInfoDormOwnerByDormName/" + id;
                                                   JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           JSONObject dormowner = response;
                                                           try {
                                                               final String username = dormowner.getString("username");
                                                               final String email = dormowner.getString("email");

                                                               String getInfoDorm = "http://" + ip + port + "/InfoDorm/getInfoDormByDormName/" + id;
                                                               JsonObjectRequest infodorm = new JsonObjectRequest(Request.Method.GET, getInfoDorm, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       JSONObject obj = response;
                                                                       try {
                                                                           final int minpp = obj.getInt("minPrice");
                                                                           final int maxpp = obj.getInt("maxPrice");

                                                                           String coverImagePath = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                           JsonObjectRequest request5 = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   JSONObject coverImage = response;
                                                                                   try {
                                                                                       final String coverImagePath = coverImage.getString("coverImage");

                                                                                       // priceModel[c].setImage(coverImagePath);

                                                                                       if (!proDetail.equals("")) {
                                                                                           int statusPromotion = 1;
                                                                                           System.out.println(id + "  " + disText + " and distance that user define: " + distanceDouble);
                                                                                           priceModel[c].setDorm_name(id);
                                                                                           priceModel[c].setOldPrice(oldprice);
                                                                                           priceModel[c].setNewPrice(newprice);
                                                                                           //priceModel[c].setDistance(disText);
                                                                                           priceModel[c].setImage(coverImagePath);
                                                                                           priceModel[c].setStatus_promotion(statusPromotion);
                                                                                           priceModel[c].setFaculty(facofuser);
                                                                                           //pricelist.add(priceModel[c]);


                                                                                           //    priceModel[c].setStatus_promotion(statusPromotion);
                                                                                           //    priceModel[c].setFaculty(facofuser);
                                                                                           //    pricelist.add(priceModel[c]);

                                                                                       } else {
                                                                                           // System.out.println(id+"   "+minpp+"  "+maxpp);
                                                                                           //int statusPromotion = 0;
                                                                                           priceModel[c].setDorm_name(id);
                                                                                           priceModel[c].setOldPrice(minpp);
                                                                                           //priceModel[c].setDistance(disText);
                                                                                           priceModel[c].setImage(coverImagePath);
                                                                                           priceModel[c].setFaculty(facofuser);
                                                                                           //pricelist.add(priceModel[c]);


                                                                                           //   priceModel[c].setStatus_promotion(statusPromotion);
                                                                                           //   priceModel[c].setFaculty(facofuser);
                                                                                           //   pricelist.add(priceModel[c]);
                                                                                       }

                                                                                       String pathfacility = "http://" + ip + port + "/facilityInDorm/getFacilityInDorm/" + id;
                                                                                       JsonObjectRequest getFacility = new JsonObjectRequest(Request.Method.GET, pathfacility, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               JSONObject obj = response;
                                                                                               try {
                                                                                                   int facRepeat = 0;
                                                                                                   ArrayList<String> datacompare = new ArrayList<>();
                                                                                                   JSONArray nameth = obj.getJSONArray("nameTH");
                                                                                                   JSONArray nameen = obj.getJSONArray("nameEN");
                                                                                                   for (int j = 0; j < nameth.length(); j++) {
                                                                                                       datacompare.add(nameth.getString(j));
                                                                                                   }

                                                                                                   for (int k = 0; k < datacompare.size(); k++) {
                                                                                                       for (int l = 0; l < facilities.size(); l++) {
                                                                                                           if (facilities.get(l).getTextTH().equals(datacompare.get(k))) {
                                                                                                               facRepeat++;
                                                                                                               System.out.println(facRepeat);
                                                                                                               System.out.println(id + "  " + facilities.get(l).getTextTH() + " equal " + datacompare.get(k) + "   999999999999999999999999");
                                                                                                               if (facRepeat == facilities.size()) {

                                                                                                                   pricelist.add(priceModel[c]);
                                                                                                               }


                                                                                                           }
                                                                                                       }
                                                                                                   }

                                                                                                  count2++;
                                                                                                   if(count2 == data.length())
                                                                                                   {
                                                                                                       oncallback.onSuccessType1(pricelist);
                                                                                                   }


                                                                                               } catch (JSONException ex) {
                                                                                                   ex.printStackTrace();
                                                                                               }

                                                                                           }
                                                                                       }, new Response.ErrorListener() {
                                                                                           @Override
                                                                                           public void onErrorResponse(VolleyError error) {

                                                                                           }
                                                                                       });
                                                                                       queue.add(getFacility);
                                                                                   } catch (JSONException ex) {
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
                                                               queue.add(infodorm);


                                                           } catch (JSONException ex) {
                                                               ex.printStackTrace();
                                                           }

                                                       }
                                                   }, new Response.ErrorListener() {
                                                       @Override
                                                       public void onErrorResponse(VolleyError error) {

                                                       }
                                                   });

                                                   queue.add(request4);
                                               } catch (JSONException ex) {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });
                                       queue.add(request3);
                                   } catch (JSONException ex) {
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
                   } catch (JSONException ex) {
                       ex.printStackTrace();
                   }

               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }
           });
           queue.add(request);
       }
       // price , distance
       else if (priceCheck == true && distanceCheck == true && facilityCheck == false)
       {
           String pathGetDormInStyle = "http://" + ip + port + "/DormTypePriceNPromotion/getAllDorm";
           JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, pathGetDormInStyle, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   try {
                       final JSONArray data = response;
                       priceModel = new Dorm_PricenPromotion_Model[data.length()];
                       for (count = 0; count < data.length(); count++) {
                           final int c = count;
                           priceModel[count] = new Dorm_PricenPromotion_Model();
                           JSONObject obj = data.getJSONObject(count);
                           final String id = obj.getString("dormID");
                           final String proDetail = obj.getString("promotionDescribe");
                           // System.out.println("Promotion Detail: "+proDetail);
                           final int oldprice = obj.getInt("oldPrice");
                           final int newprice = obj.getInt("newPrice");

                           // priceModel[count].setDorm_name(id);
                           // priceModel[count].setOldPrice(oldprice);
                           // priceModel[count].setNewPrice(newprice);

                           String getPositionDorm = "http://" + ip + port + "/dorm/getDormByDormName/" + id;
                           JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getPositionDorm, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject ob = response;
                                   try {
                                       double latitude = ob.getDouble("latitude");
                                       double longtitude = ob.getDouble("longtitude");

                                       final String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?" + "origin=" + latitude + "," + longtitude + "&destination=" + facofuser.getLatitude() + "," + facofuser.getLongtitude() + "&key=" + newServerKey;
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
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++) {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   //priceModel[c].setDistance(distanceText);
                                                   priceModel[c].setDistance(disText);
                                                   priceModel[c].setDistanceDouble(Double.parseDouble(disText));
                                                   //System.out.println(Double.parseDouble(disText)+"  "+unit);


                                                   String getUsernameAndEmail = "http://" + ip + port + "/dormOwner/getInfoDormOwnerByDormName/" + id;
                                                   JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           JSONObject dormowner = response;
                                                           try {
                                                               final String username = dormowner.getString("username");
                                                               final String email = dormowner.getString("email");

                                                               String getInfoDorm = "http://" + ip + port + "/InfoDorm/getInfoDormByDormName/" + id;
                                                               JsonObjectRequest infodorm = new JsonObjectRequest(Request.Method.GET, getInfoDorm, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       JSONObject obj = response;
                                                                       try {
                                                                           final int minpp = obj.getInt("minPrice");
                                                                           final int maxpp = obj.getInt("maxPrice");

                                                                           String coverImagePath = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                           JsonObjectRequest request5 = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   JSONObject coverImage = response;
                                                                                   try {
                                                                                       final String coverImagePath = coverImage.getString("coverImage");

                                                                                       // priceModel[c].setImage(coverImagePath);

                                                                                       if (!proDetail.equals(""))
                                                                                       {

                                                                                           int statusPromotion = 1;

                                                                                           if (newprice >= minprice && newprice <= maxprice) {
                                                                                               //System.out.println(id+"  "+disText+" and distance that user define: "+distanceDouble);
                                                                                               priceModel[c].setDorm_name(id);
                                                                                               priceModel[c].setOldPrice(oldprice);
                                                                                               priceModel[c].setNewPrice(newprice);
                                                                                               //priceModel[c].setDistance(disText);
                                                                                               priceModel[c].setImage(coverImagePath);
                                                                                               priceModel[c].setStatus_promotion(statusPromotion);
                                                                                               priceModel[c].setFaculty(facofuser);

                                                                                               Double d1 = new Double(priceModel[c].getDistanceDouble());
                                                                                               Double d2 = new Double(distanceDouble);
                                                                                               int retval = d1.compareTo(d2);
                                                                                               if (distanceDescribe.equals(distanceArray[0])) {
                                                                                                   if (retval < 0 || retval == 0) {
                                                                                                       pricelist.add(priceModel[c]);
                                                                                                   }

                                                                                               } else if (distanceDescribe.equals(distanceArray[1])) {

                                                                                                   if (retval < 0 || retval == 0) {
                                                                                                       pricelist.add(priceModel[c]);
                                                                                                   }
                                                                                               } else if (distanceDescribe.equals(distanceArray[2])) {
                                                                                                   if (retval < 0 || retval == 0) {
                                                                                                       pricelist.add(priceModel[c]);
                                                                                                   }
                                                                                               } else if (distanceDescribe.equals(distanceArray[3])) {
                                                                                                   if (retval < 0 || retval == 0) {
                                                                                                       pricelist.add(priceModel[c]);
                                                                                                   }
                                                                                               }


                                                                                           }
                                                                                       } else {
                                                                                           //System.out.println(id+"   "+minpp+"  "+maxpp);
                                                                                           //int statusPromotion = 0;
                                                                                           if (minpp >= minprice && minpp <= maxprice) {
                                                                                               priceModel[c].setDorm_name(id);
                                                                                               priceModel[c].setOldPrice(minpp);
                                                                                               //priceModel[c].setDistance(disText);
                                                                                               priceModel[c].setImage(coverImagePath);
                                                                                               priceModel[c].setFaculty(facofuser);

                                                                                               Double d1 = new Double(priceModel[c].getDistanceDouble());
                                                                                               Double d2 = new Double(distanceDouble);
                                                                                               int retval = d1.compareTo(d2);
                                                                                               if (distanceDescribe.equals(distanceArray[0])) {
                                                                                                   if (retval < 0 || retval == 0) {
                                                                                                       pricelist.add(priceModel[c]);
                                                                                                   }

                                                                                               } else if (distanceDescribe.equals(distanceArray[1])) {

                                                                                                   if (retval < 0 || retval == 0) {
                                                                                                       pricelist.add(priceModel[c]);
                                                                                                   }
                                                                                               } else if (distanceDescribe.equals(distanceArray[2])) {
                                                                                                   if (retval < 0 || retval == 0) {
                                                                                                       pricelist.add(priceModel[c]);
                                                                                                   }
                                                                                               } else if (distanceDescribe.equals(distanceArray[3])) {
                                                                                                   if (retval < 0 || retval == 0) {
                                                                                                       pricelist.add(priceModel[c]);
                                                                                                   }
                                                                                               }


                                                                                           }
                                                                                           //   priceModel[c].setStatus_promotion(statusPromotion);
                                                                                           //   priceModel[c].setFaculty(facofuser);
                                                                                           //   pricelist.add(priceModel[c]);
                                                                                       }

                                                                                       count2++;
                                                                                       if(count2 == data.length())
                                                                                       {
                                                                                           oncallback.onSuccessType1(pricelist);
                                                                                       }
                                                                                   } catch (JSONException ex) {
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
                                                               queue.add(infodorm);


                                                           } catch (JSONException ex) {
                                                               ex.printStackTrace();
                                                           }

                                                       }
                                                   }, new Response.ErrorListener() {
                                                       @Override
                                                       public void onErrorResponse(VolleyError error) {

                                                       }
                                                   });

                                                   queue.add(request4);
                                               } catch (JSONException ex) {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });
                                       queue.add(request3);
                                   } catch (JSONException ex) {
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
                   } catch (JSONException ex) {
                       ex.printStackTrace();
                   }

               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }
           });
           queue.add(request);
       }
       // price , facility
       else if (priceCheck == true && distanceCheck == false && facilityCheck == true)
       {
           String pathGetDormInStyle = "http://" + ip + port + "/DormTypePriceNPromotion/getAllDorm";
           JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, pathGetDormInStyle, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   try {
                       final JSONArray data = response;
                       priceModel = new Dorm_PricenPromotion_Model[data.length()];
                       for (count = 0; count < data.length(); count++) {
                           final int c = count;
                           priceModel[count] = new Dorm_PricenPromotion_Model();
                           JSONObject obj = data.getJSONObject(count);
                           final String id = obj.getString("dormID");
                           final String proDetail = obj.getString("promotionDescribe");
                           // System.out.println("Promotion Detail: "+proDetail);
                           final int oldprice = obj.getInt("oldPrice");
                           final int newprice = obj.getInt("newPrice");

                           // priceModel[count].setDorm_name(id);
                           // priceModel[count].setOldPrice(oldprice);
                           // priceModel[count].setNewPrice(newprice);

                           String getPositionDorm = "http://" + ip + port + "/dorm/getDormByDormName/" + id;
                           JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getPositionDorm, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject ob = response;
                                   try {
                                       double latitude = ob.getDouble("latitude");
                                       double longtitude = ob.getDouble("longtitude");

                                       final String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?" + "origin=" + latitude + "," + longtitude + "&destination=" + facofuser.getLatitude() + "," + facofuser.getLongtitude() + "&key=" + newServerKey;
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
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++) {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   //priceModel[c].setDistance(distanceText);
                                                   priceModel[c].setDistance(disText);
                                                   priceModel[c].setDistanceDouble(Double.parseDouble(disText));
                                                   System.out.println(Double.parseDouble(disText) + "  " + unit);


                                                   String getUsernameAndEmail = "http://" + ip + port + "/dormOwner/getInfoDormOwnerByDormName/" + id;
                                                   JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           JSONObject dormowner = response;
                                                           try {
                                                               final String username = dormowner.getString("username");
                                                               final String email = dormowner.getString("email");

                                                               String getInfoDorm = "http://" + ip + port + "/InfoDorm/getInfoDormByDormName/" + id;
                                                               JsonObjectRequest infodorm = new JsonObjectRequest(Request.Method.GET, getInfoDorm, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       JSONObject obj = response;
                                                                       try {
                                                                           final int minpp = obj.getInt("minPrice");
                                                                           final int maxpp = obj.getInt("maxPrice");

                                                                           String coverImagePath = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                           JsonObjectRequest request5 = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   JSONObject coverImage = response;
                                                                                   try {
                                                                                       final String coverImagePath = coverImage.getString("coverImage");

                                                                                       String pathfacility = "http://" + ip + port + "/facilityInDorm/getFacilityInDorm/" + id;
                                                                                       JsonObjectRequest facReq = new JsonObjectRequest(Request.Method.GET, pathfacility, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               JSONObject obj = response;
                                                                                               try {
                                                                                                   int facRepeat = 0;
                                                                                                   ArrayList<String> datacompare = new ArrayList<>();
                                                                                                   JSONArray nameth = obj.getJSONArray("nameTH");
                                                                                                   JSONArray nameen = obj.getJSONArray("nameEN");
                                                                                                   for (int j = 0; j < nameth.length(); j++) {
                                                                                                       datacompare.add(nameth.getString(j));
                                                                                                   }

                                                                                                   for (int k = 0; k < facilities.size(); k++)
                                                                                                   {
                                                                                                       for (int l = 0; l < datacompare.size(); l++)
                                                                                                       {
                                                                                                           if (facilities.get(k).getTextTH().equals(datacompare.get(l)))
                                                                                                           {
                                                                                                               facRepeat++;
                                                                                                               System.out.println("DORM NAME: " + id + "   " + facilities.get(k).getTextTH());
                                                                                                               System.out.println("TIMES OF full facility: " + facRepeat);
                                                                                                               if (facRepeat == facilities.size())
                                                                                                               {
                                                                                                                   if (!proDetail.equals("")) {
                                                                                                                       if (newprice >= minprice && newprice <= maxprice) {
                                                                                                                           int statusPromotion = 1;
                                                                                                                           //System.out.println(id + "  " + disText + " and distance that user define: " + distanceDouble);
                                                                                                                           priceModel[c].setDorm_name(id);
                                                                                                                           priceModel[c].setOldPrice(oldprice);
                                                                                                                           priceModel[c].setNewPrice(newprice);
                                                                                                                           priceModel[c].setImage(coverImagePath);
                                                                                                                           priceModel[c].setStatus_promotion(statusPromotion);
                                                                                                                           priceModel[c].setFaculty(facofuser);
                                                                                                                           pricelist.add(priceModel[c]);
                                                                                                                       }
                                                                                                                       System.out.println("PROMOTION DORM: " + id);
                                                                                                                   } else {
                                                                                                                       if (minpp >= minprice && minpp <= maxprice) {
                                                                                                                           System.out.println(id + "   " + minpp + "  " + maxpp);
                                                                                                                           //int statusPromotion = 0;
                                                                                                                           priceModel[c].setDorm_name(id);
                                                                                                                           priceModel[c].setOldPrice(minpp);
                                                                                                                           priceModel[c].setImage(coverImagePath);
                                                                                                                           priceModel[c].setFaculty(facofuser);
                                                                                                                           pricelist.add(priceModel[c]);
                                                                                                                       }
                                                                                                                       System.out.println("NORMAL DORM: " + id);
                                                                                                                   }
                                                                                                               }


                                                                                                           }
                                                                                                       }
                                                                                                   }

                                                                                                 count2++;
                                                                                                 if(count2 == data.length())
                                                                                                 {
                                                                                                     oncallback.onSuccessType1(pricelist);
                                                                                                 }
                                                                                               } catch (JSONException ex) {
                                                                                                   ex.printStackTrace();
                                                                                               }


                                                                                           }
                                                                                       }, new Response.ErrorListener() {
                                                                                           @Override
                                                                                           public void onErrorResponse(VolleyError error) {

                                                                                           }
                                                                                       });
                                                                                       queue.add(facReq);
                                                                                   } catch (JSONException ex) {
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
                                                               queue.add(infodorm);


                                                           } catch (JSONException ex) {
                                                               ex.printStackTrace();
                                                           }

                                                       }
                                                   }, new Response.ErrorListener() {
                                                       @Override
                                                       public void onErrorResponse(VolleyError error) {

                                                       }
                                                   });

                                                   queue.add(request4);
                                               } catch (JSONException ex) {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });
                                       queue.add(request3);
                                   } catch (JSONException ex) {
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
                   } catch (JSONException ex) {
                       ex.printStackTrace();
                   }

               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }
           });
           queue.add(request);
       }
       // distance , facility
       else if (priceCheck == false && distanceCheck == true && facilityCheck == true)
       {
           String pathGetDormInStyle = "http://" + ip + port + "/DormTypePriceNPromotion/getAllDorm";
           JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, pathGetDormInStyle, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   try {
                       final JSONArray data = response;
                       priceModel = new Dorm_PricenPromotion_Model[data.length()];
                       for (count = 0; count < data.length(); count++) {
                           final int c = count;
                           priceModel[count] = new Dorm_PricenPromotion_Model();
                           JSONObject obj = data.getJSONObject(count);
                           final String id = obj.getString("dormID");
                           final String proDetail = obj.getString("promotionDescribe");
                           // System.out.println("Promotion Detail: "+proDetail);
                           final int oldprice = obj.getInt("oldPrice");
                           final int newprice = obj.getInt("newPrice");

                           // priceModel[count].setDorm_name(id);
                           // priceModel[count].setOldPrice(oldprice);
                           // priceModel[count].setNewPrice(newprice);

                           String getPositionDorm = "http://" + ip + port + "/dorm/getDormByDormName/" + id;
                           JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getPositionDorm, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject ob = response;
                                   try {
                                       double latitude = ob.getDouble("latitude");
                                       double longtitude = ob.getDouble("longtitude");

                                       final String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?" + "origin=" + latitude + "," + longtitude + "&destination=" + facofuser.getLatitude() + "," + facofuser.getLongtitude() + "&key=" + newServerKey;
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
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++) {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   //priceModel[c].setDistance(distanceText);
                                                   priceModel[c].setDistance(disText);
                                                   priceModel[c].setDistanceDouble(Double.parseDouble(disText));
                                                   System.out.println(Double.parseDouble(disText) + "  " + unit);


                                                   String getUsernameAndEmail = "http://" + ip + port + "/dormOwner/getInfoDormOwnerByDormName/" + id;
                                                   JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           JSONObject dormowner = response;
                                                           try {
                                                               final String username = dormowner.getString("username");
                                                               final String email = dormowner.getString("email");

                                                               String getInfoDorm = "http://" + ip + port + "/InfoDorm/getInfoDormByDormName/" + id;
                                                               JsonObjectRequest infodorm = new JsonObjectRequest(Request.Method.GET, getInfoDorm, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       JSONObject obj = response;
                                                                       try {
                                                                           final int minpp = obj.getInt("minPrice");
                                                                           final int maxpp = obj.getInt("maxPrice");

                                                                           String coverImagePath = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                           JsonObjectRequest request5 = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   JSONObject coverImage = response;
                                                                                   try {
                                                                                       final String coverImagePath = coverImage.getString("coverImage");

                                                                                       String pathfacility = "http://" + ip + port + "/facilityInDorm/getFacilityInDorm/" + id;
                                                                                       JsonObjectRequest facReq = new JsonObjectRequest(Request.Method.GET, pathfacility, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               JSONObject obj = response;
                                                                                               try {
                                                                                                   int facRepeat = 0;
                                                                                                   ArrayList<String> datacompare = new ArrayList<>();
                                                                                                   JSONArray nameth = obj.getJSONArray("nameTH");
                                                                                                   JSONArray nameen = obj.getJSONArray("nameEN");
                                                                                                   for (int j = 0; j < nameth.length(); j++) {
                                                                                                       datacompare.add(nameth.getString(j));
                                                                                                   }

                                                                                                   for (int k = 0; k < facilities.size(); k++)
                                                                                                   {
                                                                                                       for (int l = 0; l < datacompare.size(); l++)
                                                                                                       {
                                                                                                           if (facilities.get(k).getTextTH().equals(datacompare.get(l)))
                                                                                                           {
                                                                                                               facRepeat++;
                                                                                                               //System.out.println("DORM NAME: " + id + "   " + facilities.get(k).getTextTH());
                                                                                                               //System.out.println("TIMES OF full facility: " + facRepeat);
                                                                                                               if (facRepeat == facilities.size())
                                                                                                               {
                                                                                                                   if (!proDetail.equals(""))
                                                                                                                   {

                                                                                                                           int statusPromotion = 1;
                                                                                                                           //System.out.println(id + "  " + disText + " and distance that user define: " + distanceDouble);
                                                                                                                           priceModel[c].setDorm_name(id);
                                                                                                                           priceModel[c].setOldPrice(oldprice);
                                                                                                                           priceModel[c].setNewPrice(newprice);
                                                                                                                           priceModel[c].setImage(coverImagePath);
                                                                                                                           priceModel[c].setStatus_promotion(statusPromotion);
                                                                                                                           priceModel[c].setFaculty(facofuser);

                                                                                                                           Double d1 = new Double(priceModel[c].getDistanceDouble());
                                                                                                                           Double d2 = new Double(distanceDouble);
                                                                                                                           int retval = d1.compareTo(d2);
                                                                                                                           if (distanceDescribe.equals(distanceArray[0]))
                                                                                                                           {
                                                                                                                               if (retval < 0 || retval == 0)
                                                                                                                               {
                                                                                                                                   pricelist.add(priceModel[c]);
                                                                                                                               }

                                                                                                                           }
                                                                                                                           else if (distanceDescribe.equals(distanceArray[1]))
                                                                                                                           {
                                                                                                                               if (retval < 0 || retval == 0)
                                                                                                                               {
                                                                                                                                   pricelist.add(priceModel[c]);
                                                                                                                               }
                                                                                                                           }
                                                                                                                           else if (distanceDescribe.equals(distanceArray[2]))
                                                                                                                           {
                                                                                                                               if (retval < 0 || retval == 0)
                                                                                                                               {
                                                                                                                                   pricelist.add(priceModel[c]);
                                                                                                                               }
                                                                                                                           }
                                                                                                                           else if (distanceDescribe.equals(distanceArray[3]))
                                                                                                                           {
                                                                                                                               if (retval < 0 || retval == 0)
                                                                                                                               {
                                                                                                                                   pricelist.add(priceModel[c]);
                                                                                                                               }
                                                                                                                           }
                                                                                                                       System.out.println("PROMOTION DORM: " + id);
                                                                                                                   }
                                                                                                                   else {

                                                                                                                           System.out.println(id + "   " + minpp + "  " + maxpp);
                                                                                                                           //int statusPromotion = 0;
                                                                                                                           priceModel[c].setDorm_name(id);
                                                                                                                           priceModel[c].setOldPrice(minpp);
                                                                                                                           priceModel[c].setImage(coverImagePath);
                                                                                                                           priceModel[c].setFaculty(facofuser);



                                                                                                                       Double d1 = new Double(priceModel[c].getDistanceDouble());
                                                                                                                       Double d2 = new Double(distanceDouble);
                                                                                                                       int retval = d1.compareTo(d2);
                                                                                                                       if (distanceDescribe.equals(distanceArray[0])) {
                                                                                                                           if (retval < 0 || retval == 0) {
                                                                                                                               pricelist.add(priceModel[c]);
                                                                                                                           }

                                                                                                                       } else if (distanceDescribe.equals(distanceArray[1])) {

                                                                                                                           if (retval < 0 || retval == 0) {
                                                                                                                               pricelist.add(priceModel[c]);
                                                                                                                           }
                                                                                                                       } else if (distanceDescribe.equals(distanceArray[2])) {
                                                                                                                           if (retval < 0 || retval == 0) {
                                                                                                                               pricelist.add(priceModel[c]);
                                                                                                                           }
                                                                                                                       } else if (distanceDescribe.equals(distanceArray[3])) {
                                                                                                                           if (retval < 0 || retval == 0) {
                                                                                                                               pricelist.add(priceModel[c]);
                                                                                                                           }
                                                                                                                       }

                                                                                                                       System.out.println("NORMAL DORM: " + id);
                                                                                                                   }

                                                                                                                   count2++;
                                                                                                                   if(count2 == data.length())
                                                                                                                   {
                                                                                                                       oncallback.onSuccessType1(pricelist);
                                                                                                                   }
                                                                                                               }


                                                                                                           }
                                                                                                       }
                                                                                                   }

                                                                                                   count2++;
                                                                                                   if(count2 == data.length())
                                                                                                   {
                                                                                                       oncallback.onSuccessType1(pricelist);
                                                                                                   }
                                                                                               } catch (JSONException ex) {
                                                                                                   ex.printStackTrace();
                                                                                               }


                                                                                           }
                                                                                       }, new Response.ErrorListener() {
                                                                                           @Override
                                                                                           public void onErrorResponse(VolleyError error) {

                                                                                           }
                                                                                       });
                                                                                       queue.add(facReq);
                                                                                   } catch (JSONException ex) {
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
                                                               queue.add(infodorm);


                                                           } catch (JSONException ex) {
                                                               ex.printStackTrace();
                                                           }

                                                       }
                                                   }, new Response.ErrorListener() {
                                                       @Override
                                                       public void onErrorResponse(VolleyError error) {

                                                       }
                                                   });

                                                   queue.add(request4);
                                               } catch (JSONException ex) {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });
                                       queue.add(request3);
                                   } catch (JSONException ex) {
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
                   } catch (JSONException ex) {
                       ex.printStackTrace();
                   }

               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }
           });
           queue.add(request);
       }
       // price , distance , facility
       else if (priceCheck == true && distanceCheck == true && facilityCheck == true)
       {
           String pathGetDormInStyle = "http://" + ip + port + "/DormTypePriceNPromotion/getAllDorm";
           JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, pathGetDormInStyle, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   try {
                       final JSONArray data = response;
                       priceModel = new Dorm_PricenPromotion_Model[data.length()];
                       for (count = 0; count < data.length(); count++) {
                           final int c = count;
                           priceModel[count] = new Dorm_PricenPromotion_Model();
                           JSONObject obj = data.getJSONObject(count);
                           final String id = obj.getString("dormID");
                           final String proDetail = obj.getString("promotionDescribe");
                           // System.out.println("Promotion Detail: "+proDetail);
                           final int oldprice = obj.getInt("oldPrice");
                           final int newprice = obj.getInt("newPrice");

                           // priceModel[count].setDorm_name(id);
                           // priceModel[count].setOldPrice(oldprice);
                           // priceModel[count].setNewPrice(newprice);

                           String getPositionDorm = "http://" + ip + port + "/dorm/getDormByDormName/" + id;
                           JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getPositionDorm, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject ob = response;
                                   try {
                                       double latitude = ob.getDouble("latitude");
                                       double longtitude = ob.getDouble("longtitude");

                                       final String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?" + "origin=" + latitude + "," + longtitude + "&destination=" + facofuser.getLatitude() + "," + facofuser.getLongtitude() + "&key=" + newServerKey;
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
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++) {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   //priceModel[c].setDistance(distanceText);
                                                   priceModel[c].setDistance(disText);
                                                   priceModel[c].setDistanceDouble(Double.parseDouble(disText));
                                                   System.out.println(Double.parseDouble(disText) + "  " + unit);


                                                   String getUsernameAndEmail = "http://" + ip + port + "/dormOwner/getInfoDormOwnerByDormName/" + id;
                                                   JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           JSONObject dormowner = response;
                                                           try {
                                                               final String username = dormowner.getString("username");
                                                               final String email = dormowner.getString("email");

                                                               String getInfoDorm = "http://" + ip + port + "/InfoDorm/getInfoDormByDormName/" + id;
                                                               JsonObjectRequest infodorm = new JsonObjectRequest(Request.Method.GET, getInfoDorm, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       JSONObject obj = response;
                                                                       try {
                                                                           final int minpp = obj.getInt("minPrice");
                                                                           final int maxpp = obj.getInt("maxPrice");

                                                                           String coverImagePath = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                           JsonObjectRequest request5 = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   JSONObject coverImage = response;
                                                                                   try {
                                                                                       final String coverImagePath = coverImage.getString("coverImage");

                                                                                       String pathfacility = "http://" + ip + port + "/facilityInDorm/getFacilityInDorm/" + id;
                                                                                       JsonObjectRequest facReq = new JsonObjectRequest(Request.Method.GET, pathfacility, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               JSONObject obj = response;
                                                                                               try {
                                                                                                   int facRepeat = 0;
                                                                                                   ArrayList<String> datacompare = new ArrayList<>();
                                                                                                   JSONArray nameth = obj.getJSONArray("nameTH");
                                                                                                   JSONArray nameen = obj.getJSONArray("nameEN");
                                                                                                   for (int j = 0; j < nameth.length(); j++) {
                                                                                                       datacompare.add(nameth.getString(j));
                                                                                                   }

                                                                                                   for (int k = 0; k < facilities.size(); k++)
                                                                                                   {
                                                                                                       for (int l = 0; l < datacompare.size(); l++)
                                                                                                       {
                                                                                                           if (facilities.get(k).getTextTH().equals(datacompare.get(l)))
                                                                                                           {
                                                                                                               facRepeat++;
                                                                                                               //System.out.println("DORM NAME: " + id + "   " + facilities.get(k).getTextTH());
                                                                                                               //System.out.println("TIMES OF full facility: " + facRepeat);
                                                                                                               if (facRepeat == facilities.size())
                                                                                                               {
                                                                                                                   if (!proDetail.equals(""))
                                                                                                                   {
                                                                                                                       if (newprice >= minprice && newprice <= maxprice)
                                                                                                                       {
                                                                                                                        int statusPromotion = 1;
                                                                                                                        //System.out.println(id + "  " + disText + " and distance that user define: " + distanceDouble);
                                                                                                                        priceModel[c].setDorm_name(id);
                                                                                                                        priceModel[c].setOldPrice(oldprice);
                                                                                                                        priceModel[c].setNewPrice(newprice);
                                                                                                                        priceModel[c].setImage(coverImagePath);
                                                                                                                        priceModel[c].setStatus_promotion(statusPromotion);
                                                                                                                        priceModel[c].setFaculty(facofuser);

                                                                                                                        Double d1 = new Double(priceModel[c].getDistanceDouble());
                                                                                                                        Double d2 = new Double(distanceDouble);
                                                                                                                        int retval = d1.compareTo(d2);
                                                                                                                        if (distanceDescribe.equals(distanceArray[0]))
                                                                                                                        {
                                                                                                                           if (retval < 0 || retval == 0)
                                                                                                                           {
                                                                                                                               pricelist.add(priceModel[c]);
                                                                                                                           }

                                                                                                                        }
                                                                                                                        else if (distanceDescribe.equals(distanceArray[1]))
                                                                                                                        {
                                                                                                                           if (retval < 0 || retval == 0)
                                                                                                                           {
                                                                                                                               pricelist.add(priceModel[c]);
                                                                                                                           }
                                                                                                                        }
                                                                                                                        else if (distanceDescribe.equals(distanceArray[2]))
                                                                                                                        {
                                                                                                                           if (retval < 0 || retval == 0) {
                                                                                                                               pricelist.add(priceModel[c]);
                                                                                                                           }
                                                                                                                        }
                                                                                                                        else if (distanceDescribe.equals(distanceArray[3]))
                                                                                                                        {
                                                                                                                           if (retval < 0 || retval == 0)
                                                                                                                           {
                                                                                                                               pricelist.add(priceModel[c]);
                                                                                                                           }
                                                                                                                        }
                                                                                                                       System.out.println("PROMOTION DORM: " + id);
                                                                                                                   }
                                                                                                                   }
                                                                                                                   else
                                                                                                                       {
                                                                                                                        if (minpp >= minprice && minpp <= maxprice)
                                                                                                                        {
                                                                                                                            System.out.println(id + "   " + minpp + "  " + maxpp);
                                                                                                                            //int statusPromotion = 0;
                                                                                                                            priceModel[c].setDorm_name(id);
                                                                                                                            priceModel[c].setOldPrice(minpp);
                                                                                                                            priceModel[c].setImage(coverImagePath);
                                                                                                                            priceModel[c].setFaculty(facofuser);


                                                                                                                       Double d1 = new Double(priceModel[c].getDistanceDouble());
                                                                                                                       Double d2 = new Double(distanceDouble);
                                                                                                                       int retval = d1.compareTo(d2);
                                                                                                                       if (distanceDescribe.equals(distanceArray[0])) {
                                                                                                                           if (retval < 0 || retval == 0) {
                                                                                                                               pricelist.add(priceModel[c]);
                                                                                                                           }

                                                                                                                       } else if (distanceDescribe.equals(distanceArray[1])) {

                                                                                                                           if (retval < 0 || retval == 0) {
                                                                                                                               pricelist.add(priceModel[c]);
                                                                                                                           }
                                                                                                                       } else if (distanceDescribe.equals(distanceArray[2])) {
                                                                                                                           if (retval < 0 || retval == 0) {
                                                                                                                               pricelist.add(priceModel[c]);
                                                                                                                           }
                                                                                                                       } else if (distanceDescribe.equals(distanceArray[3])) {
                                                                                                                           if (retval < 0 || retval == 0) {
                                                                                                                               pricelist.add(priceModel[c]);
                                                                                                                           }
                                                                                                                       }

                                                                                                                       System.out.println("NORMAL DORM: " + id);
                                                                                                                   }
                                                                                                                   }

                                                                                                                   count2++;
                                                                                                                   if(count2 == data.length())
                                                                                                                   {
                                                                                                                       oncallback.onSuccessType1(pricelist);
                                                                                                                   }
                                                                                                               }
                                                                                                           }
                                                                                                       }
                                                                                                   }

                                                                                                   count2++;
                                                                                                   if(count2 == data.length())
                                                                                                   {
                                                                                                       oncallback.onSuccessType1(pricelist);
                                                                                                   }
                                                                                               } catch (JSONException ex) {
                                                                                                   ex.printStackTrace();
                                                                                               }


                                                                                           }
                                                                                       }, new Response.ErrorListener() {
                                                                                           @Override
                                                                                           public void onErrorResponse(VolleyError error) {

                                                                                           }
                                                                                       });
                                                                                       queue.add(facReq);
                                                                                   } catch (JSONException ex) {
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
                                                               queue.add(infodorm);


                                                           } catch (JSONException ex) {
                                                               ex.printStackTrace();
                                                           }

                                                       }
                                                   }, new Response.ErrorListener() {
                                                       @Override
                                                       public void onErrorResponse(VolleyError error) {

                                                       }
                                                   });

                                                   queue.add(request4);
                                               } catch (JSONException ex) {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });
                                       queue.add(request3);
                                   } catch (JSONException ex) {
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
                   } catch (JSONException ex) {
                       ex.printStackTrace();
                   }

               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }
           });
           queue.add(request);
       }
   }
   else if(codeStyle == 1)
   {
       RequestQueue reqqueue = Volley.newRequestQueue(this);
       if(priceCheck == true && distanceCheck == false && facilityCheck == false)
       {
           String getDorm_Infotmation = "http://" + ip + port + "/DormTypeInformation/getAllDorm";
           JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, getDorm_Infotmation, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   final JSONArray res = response;
                   try {
                       infoModel = new Dorm_Information_Model[res.length()];

                       for (count = 0; count < res.length(); count++)
                       {
                           final int c = count;
                           infoModel[count] = new Dorm_Information_Model();
                           JSONObject ob = res.getJSONObject(count);
                           final String id = ob.getString("dormID");
                           String detail = ob.getString("detail");
                           StringTokenizer tokenizer = new StringTokenizer(detail, ",");
                           String[] hippo = new String[tokenizer.countTokens()];
                           for (int count2 = 0; count2 < hippo.length; count2++)
                           {
                               hippo[count2] = tokenizer.nextToken();
                           }

                           infoModel[count].setName(id);
                           infoModel[count].setDetail(hippo);


                           String pathDorm = "http://" + ip + port + "/dorm/getDormByDormName/"+id;
                           //System.out.println("Path dorm: "+pathDorm);
                           JsonObjectRequest dormRequest = new JsonObjectRequest(Request.Method.GET, pathDorm, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject o = response;
                                   try
                                   {
                                       String address = o.getString("address");
                                       double longtitude = o.getDouble("longtitude");
                                       double latitude = o.getDouble("latitude");
                                       StringTokenizer token = new StringTokenizer(address, " ");
                                       while (token.hasMoreTokens())
                                       {
                                           addressText = addressText + " " + token.nextToken();
                                       }
                                       //System.out.println(id + "  " + addressText + "  " + latitude + "  " + longtitude);
                                       infoModel[c].setAddress(addressText);
                                       addressText = "";

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?" + "origin=" + latitude + "," + longtitude + "&destination=" + facofuser.getLatitude() + "," + facofuser.getLongtitude() + "&key=" + newServerKey;
                                       JsonObjectRequest distanceRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               //System.out.println(id+" "+response);
                                               JSONObject ob = response;
                                               try {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   //System.out.println(id + "  " + distanceText);
                                                   String distanceInteger = "";
                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText," ");
                                                   for(int i=0;i<token.countTokens();i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   infoModel[c].setDistance(distanceText);
                                                   infoModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getInfoDormRequest = "http://" + ip + port + "/InfoDorm/getInfoDormByDormName/" + id;
                                                   JsonObjectRequest infoDormRequest = new JsonObjectRequest(Request.Method.GET, getInfoDormRequest, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           //System.out.println("RESPONSE: "+response);
                                                           try {
                                                               int minPrice = response.getInt("minPrice");
                                                               // System.out.println(minPrice);
                                                               infoModel[c].setPrice(minPrice);

                                                               String getFacilityInRoom = "http://" + ip + port + "/facilityInRoom/getFacilityInRoom/" + id;
                                                               JsonObjectRequest getFacilityInRoomRequest = new JsonObjectRequest(Request.Method.GET, getFacilityInRoom, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       //System.out.println(response);
                                                                       try {

                                                                           JSONObject obj = response;
                                                                           int[] image;
                                                                           JSONArray array = obj.getJSONArray("image");
                                                                           image = new int[array.length()];
                                                                           for (int k = 0; k < image.length; k++) {
                                                                               image[k] = array.getInt(k);
                                                                           }

                                                                           infoModel[c].setFacility(image);

                                                                           String getUsernameAndEmail = "http://" + ip + port + "/dormOwner/getInfoDormOwnerByDormName/" + id;
                                                                           JsonObjectRequest infoDormRequest = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   //System.out.println(response);
                                                                                   try {
                                                                                       String username = response.getString("username");
                                                                                       String email = response.getString("email");
                                                                                       System.out.println(username + "   " + email);

                                                                                       String getCoverImage = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                                       JsonObjectRequest coverImageRequest = new JsonObjectRequest(Request.Method.GET, getCoverImage, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               // System.out.println(response);
                                                                                               try {
                                                                                                   String pathImage = response.getString("coverImage");
                                                                                                   // System.out.println(pathImage);
                                                                                                   infoModel[c].setImage(pathImage);

                                                                                                   infoModel[c].setFaculty(facofuser);

                                                                                                   if (infoModel[c].getPrice() >= minprice && infoModel[c].getPrice() <= maxprice)
                                                                                                   {
                                                                                                       informationlist.add(infoModel[c]);
                                                                                                   }

                                                                                                   count2++;
                                                                                                   if(count2 == res.length())
                                                                                                   {
                                                                                                       oncallback.onSuccessType2(informationlist);
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

                                                                                       reqqueue.add(coverImageRequest);

                                                                                   } catch (JSONException e) {
                                                                                       e.printStackTrace();
                                                                                   }
                                                                               }
                                                                           }, new Response.ErrorListener() {
                                                                               @Override
                                                                               public void onErrorResponse(VolleyError error) {

                                                                               }
                                                                           });

                                                                           reqqueue.add(infoDormRequest);


                                                                       } catch (JSONException e) {
                                                                           e.printStackTrace();
                                                                       }
                                                                   }
                                                               }, new Response.ErrorListener() {
                                                                   @Override
                                                                   public void onErrorResponse(VolleyError error) {

                                                                   }
                                                               });

                                                               reqqueue.add(getFacilityInRoomRequest);
                                                           } catch (JSONException e) {
                                                               e.printStackTrace();
                                                           }
                                                       }
                                                   }, new Response.ErrorListener() {
                                                       @Override
                                                       public void onErrorResponse(VolleyError error) {

                                                       }
                                                   });

                                                   reqqueue.add(infoDormRequest);
                                               } catch (JSONException ex) {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });

                                       reqqueue.add(distanceRequest);

                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               }
                           }, new Response.ErrorListener() {
                               @Override
                               public void onErrorResponse(VolleyError error) {

                               }
                           });
                           reqqueue.add(dormRequest);


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
           reqqueue.add(req);
       }
       // distance only
       else if (priceCheck == false && distanceCheck == true && facilityCheck == false)
       {
           String getDorm_Infotmation = "http://" + ip + port + "/DormTypeInformation/getAllDorm";
           JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, getDorm_Infotmation, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   final JSONArray res = response;
                   try {
                       infoModel = new Dorm_Information_Model[res.length()];

                       for (int count = 0; count < res.length(); count++)
                       {
                           final int c = count;
                           infoModel[count] = new Dorm_Information_Model();
                           JSONObject ob = res.getJSONObject(count);
                           final String id = ob.getString("dormID");
                           String detail = ob.getString("detail");
                           StringTokenizer tokenizer = new StringTokenizer(detail, ",");
                           String[] hippo = new String[tokenizer.countTokens()];
                           //System.out.println("dorm name: " + id);
                           for (int count2 = 0; count2 < hippo.length; count2++) {
                               //System.out.println(tokenizer.nextToken());
                               hippo[count2] = tokenizer.nextToken();
                           }

                           infoModel[count].setName(id);
                           infoModel[count].setDetail(hippo);

                           // System.out.println("Name: "+infoModel.getName()+"   Detail: ");


                           String pathDorm = "http://" + ip + port + "/dorm/getDormByDormName/" + id;
                           //System.out.println("Path dorm: "+pathDorm);
                           JsonObjectRequest dormRequest = new JsonObjectRequest(Request.Method.GET, pathDorm, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject o = response;
                                   //System.out.println("Response: "+o);
                                   try {
                                       String address = o.getString("address");
                                       double longtitude = o.getDouble("longtitude");
                                       double latitude = o.getDouble("latitude");
                                       StringTokenizer token = new StringTokenizer(address, " ");
                                       while (token.hasMoreTokens()) {
                                           addressText = addressText + " " + token.nextToken();
                                       }
                                       //System.out.println(id + "  " + addressText + "  " + latitude + "  " + longtitude);
                                       infoModel[c].setAddress(addressText);
                                       addressText = "";

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?" + "origin=" + latitude + "," + longtitude + "&destination=" + facofuser.getLatitude() + "," + facofuser.getLongtitude() + "&key=" + newServerKey;
                                       JsonObjectRequest distanceRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               //System.out.println(id+" "+response);
                                               JSONObject ob = response;
                                               try {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   //System.out.println(id + "  " + distanceText);
                                                   String distanceInteger = "";
                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText," ");
                                                   for(int i=0;i<token.countTokens();i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   infoModel[c].setDistance(distanceText);
                                                   infoModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getInfoDormRequest = "http://" + ip + port + "/InfoDorm/getInfoDormByDormName/" + id;
                                                   JsonObjectRequest infoDormRequest = new JsonObjectRequest(Request.Method.GET, getInfoDormRequest, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           //System.out.println("RESPONSE: "+response);
                                                           try {
                                                               int minPrice = response.getInt("minPrice");
                                                               // System.out.println(minPrice);
                                                               infoModel[c].setPrice(minPrice);

                                                               String getFacilityInRoom = "http://" + ip + port + "/facilityInRoom/getFacilityInRoom/" + id;
                                                               JsonObjectRequest getFacilityInRoomRequest = new JsonObjectRequest(Request.Method.GET, getFacilityInRoom, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       //System.out.println(response);
                                                                       try {

                                                                           JSONObject obj = response;
                                                                           int[] image;
                                                                           JSONArray array = obj.getJSONArray("image");
                                                                           image = new int[array.length()];
                                                                           for (int k = 0; k < image.length; k++) {
                                                                               image[k] = array.getInt(k);
                                                                           }

                                                                           infoModel[c].setFacility(image);

                                                                           String getUsernameAndEmail = "http://" + ip + port + "/dormOwner/getInfoDormOwnerByDormName/" + id;
                                                                           JsonObjectRequest infoDormRequest = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   //System.out.println(response);
                                                                                   try {
                                                                                       String username = response.getString("username");
                                                                                       String email = response.getString("email");
                                                                                       System.out.println(username + "   " + email);

                                                                                       String getCoverImage = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                                       JsonObjectRequest coverImageRequest = new JsonObjectRequest(Request.Method.GET, getCoverImage, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               // System.out.println(response);
                                                                                               try {
                                                                                                   String pathImage = response.getString("coverImage");
                                                                                                   // System.out.println(pathImage);
                                                                                                   infoModel[c].setImage(pathImage);

                                                                                                   infoModel[c].setFaculty(facofuser);

                                                                                                   Double d1 = new Double(infoModel[c].getDistanceDouble());
                                                                                                   Double d2 = new Double(distanceDouble);
                                                                                                   int retvalInformation = d1.compareTo(d2);
                                                                                                   if(distanceDescribe.equals(distanceArray[0]))
                                                                                                   {
                                                                                                       if(retvalInformation < 0 || retvalInformation == 0)
                                                                                                       {
                                                                                                           informationlist.add(infoModel[c]);
                                                                                                       }

                                                                                                   }
                                                                                                   else if(distanceDescribe.equals(distanceArray[1]))
                                                                                                   {

                                                                                                       if(retvalInformation < 0 || retvalInformation == 0)
                                                                                                       {
                                                                                                           informationlist.add(infoModel[c]);
                                                                                                       }
                                                                                                   }
                                                                                                   else if(distanceDescribe.equals(distanceArray[2]))
                                                                                                   {
                                                                                                       if(retvalInformation < 0 || retvalInformation == 0)
                                                                                                       {
                                                                                                           informationlist.add(infoModel[c]);
                                                                                                       }
                                                                                                   }
                                                                                                   else if(distanceDescribe.equals(distanceArray[3]))
                                                                                                   {
                                                                                                       if(retvalInformation < 0 || retvalInformation == 0)
                                                                                                       {
                                                                                                           informationlist.add(infoModel[c]);
                                                                                                       }
                                                                                                   }

                                                                                                   count2++;
                                                                                                   if(count2 == res.length())
                                                                                                   {
                                                                                                       oncallback.onSuccessType2(informationlist);
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

                                                                                       reqqueue.add(coverImageRequest);

                                                                                   } catch (JSONException e) {
                                                                                       e.printStackTrace();
                                                                                   }
                                                                               }
                                                                           }, new Response.ErrorListener() {
                                                                               @Override
                                                                               public void onErrorResponse(VolleyError error) {

                                                                               }
                                                                           });

                                                                           reqqueue.add(infoDormRequest);


                                                                       } catch (JSONException e) {
                                                                           e.printStackTrace();
                                                                       }
                                                                   }
                                                               }, new Response.ErrorListener() {
                                                                   @Override
                                                                   public void onErrorResponse(VolleyError error) {

                                                                   }
                                                               });

                                                               reqqueue.add(getFacilityInRoomRequest);
                                                           } catch (JSONException e) {
                                                               e.printStackTrace();
                                                           }
                                                       }
                                                   }, new Response.ErrorListener() {
                                                       @Override
                                                       public void onErrorResponse(VolleyError error) {

                                                       }
                                                   });

                                                   reqqueue.add(infoDormRequest);
                                               } catch (JSONException ex) {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });

                                       reqqueue.add(distanceRequest);

                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               }
                           }, new Response.ErrorListener() {
                               @Override
                               public void onErrorResponse(VolleyError error) {

                               }
                           });
                           reqqueue.add(dormRequest);


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
           reqqueue.add(req);
       }
       // facility only
       else if (priceCheck == false && distanceCheck == false && facilityCheck == true)
       {
           String getDorm_Infotmation = "http://" + ip + port + "/DormTypeInformation/getAllDorm";
           JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, getDorm_Infotmation, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   final JSONArray res = response;
                   try {
                       infoModel = new Dorm_Information_Model[res.length()];

                       for (int count = 0; count < res.length(); count++)
                       {
                           final int c = count;
                           infoModel[count] = new Dorm_Information_Model();
                           JSONObject ob = res.getJSONObject(count);
                           final String id = ob.getString("dormID");
                           String detail = ob.getString("detail");
                           StringTokenizer tokenizer = new StringTokenizer(detail, ",");
                           String[] hippo = new String[tokenizer.countTokens()];
                           //System.out.println("dorm name: " + id);
                           for (int count2 = 0; count2 < hippo.length; count2++) {
                               //System.out.println(tokenizer.nextToken());
                               hippo[count2] = tokenizer.nextToken();
                           }

                           infoModel[count].setName(id);
                           infoModel[count].setDetail(hippo);

                           // System.out.println("Name: "+infoModel.getName()+"   Detail: ");


                           String pathDorm = "http://" + ip + port + "/dorm/getDormByDormName/" + id;
                           //System.out.println("Path dorm: "+pathDorm);
                           JsonObjectRequest dormRequest = new JsonObjectRequest(Request.Method.GET, pathDorm, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject o = response;
                                   //System.out.println("Response: "+o);
                                   try {
                                       String address = o.getString("address");
                                       double longtitude = o.getDouble("longtitude");
                                       double latitude = o.getDouble("latitude");
                                       StringTokenizer token = new StringTokenizer(address, " ");
                                       while (token.hasMoreTokens()) {
                                           addressText = addressText + " " + token.nextToken();
                                       }
                                       //System.out.println(id + "  " + addressText + "  " + latitude + "  " + longtitude);
                                       infoModel[c].setAddress(addressText);
                                       addressText = "";

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?" + "origin=" + latitude + "," + longtitude + "&destination=" + facofuser.getLatitude() + "," + facofuser.getLongtitude() + "&key=" + newServerKey;
                                       JsonObjectRequest distanceRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               //System.out.println(id+" "+response);
                                               JSONObject ob = response;
                                               try {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   //System.out.println(id + "  " + distanceText);
                                                   String distanceInteger = "";
                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText," ");
                                                   for(int i=0;i<token.countTokens();i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   infoModel[c].setDistance(distanceText);
                                                   infoModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getInfoDormRequest = "http://" + ip + port + "/InfoDorm/getInfoDormByDormName/" + id;
                                                   JsonObjectRequest infoDormRequest = new JsonObjectRequest(Request.Method.GET, getInfoDormRequest, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           //System.out.println("RESPONSE: "+response);
                                                           try {
                                                               int minPrice = response.getInt("minPrice");
                                                               // System.out.println(minPrice);
                                                               infoModel[c].setPrice(minPrice);

                                                               String getFacilityInRoom = "http://" + ip + port + "/facilityInRoom/getFacilityInRoom/" + id;
                                                               JsonObjectRequest getFacilityInRoomRequest = new JsonObjectRequest(Request.Method.GET, getFacilityInRoom, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       //System.out.println(response);
                                                                       try {

                                                                           JSONObject obj = response;
                                                                           int[] image;
                                                                           JSONArray array = obj.getJSONArray("image");
                                                                           image = new int[array.length()];
                                                                           for (int k = 0; k < image.length; k++) {
                                                                               image[k] = array.getInt(k);
                                                                           }

                                                                           infoModel[c].setFacility(image);

                                                                           String getUsernameAndEmail = "http://" + ip + port + "/dormOwner/getInfoDormOwnerByDormName/" + id;
                                                                           JsonObjectRequest infoDormRequest = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   //System.out.println(response);
                                                                                   try {
                                                                                       String username = response.getString("username");
                                                                                       String email = response.getString("email");
                                                                                       System.out.println(username + "   " + email);

                                                                                       String getCoverImage = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                                       JsonObjectRequest coverImageRequest = new JsonObjectRequest(Request.Method.GET, getCoverImage, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               // System.out.println(response);
                                                                                               try {
                                                                                                   String pathImage = response.getString("coverImage");
                                                                                                   // System.out.println(pathImage);
                                                                                                   infoModel[c].setImage(pathImage);

                                                                                                   infoModel[c].setFaculty(facofuser);

                                                                                                   String pathfacility = "http://" + ip + port + "/facilityInDorm/getFacilityInDorm/" + id;
                                                                                                   JsonObjectRequest facInfoRequest = new JsonObjectRequest(Request.Method.GET, pathfacility, null, new Response.Listener<JSONObject>() {
                                                                                                       @Override
                                                                                                       public void onResponse(JSONObject response) {
                                                                                                           JSONObject obj = response;
                                                                                                           try {
                                                                                                               int facRepeatInformation = 0;
                                                                                                               ArrayList<String> datacompare = new ArrayList<>();
                                                                                                               JSONArray nameth = obj.getJSONArray("nameTH");
                                                                                                               JSONArray nameen = obj.getJSONArray("nameEN");
                                                                                                               for (int j = 0; j < nameth.length(); j++) {
                                                                                                                   datacompare.add(nameth.getString(j));
                                                                                                               }

                                                                                                               for(int count=0;count<datacompare.size();count++)
                                                                                                               {
                                                                                                                   for(int count2=0;count2<facilities.size();count2++)
                                                                                                                   {
                                                                                                                       if (facilities.get(count2).getTextTH().equals(datacompare.get(count)))
                                                                                                                       {
                                                                                                                           facRepeatInformation++;
                                                                                                                           System.out.println(facRepeatInformation);
                                                                                                                           System.out.println(id + "  " + facilities.get(count2).getTextTH() + " equal " + datacompare.get(count) + "   999999999999999999999999");
                                                                                                                           if (facRepeatInformation == facilities.size())
                                                                                                                           {
                                                                                                                               informationlist.add(infoModel[c]);
                                                                                                                           }
                                                                                                                       }
                                                                                                                   }
                                                                                                               }

                                                                                                               count2++;
                                                                                                               if(count2 == res.length())
                                                                                                               {
                                                                                                                   oncallback.onSuccessType2(informationlist);
                                                                                                               }

                                                                                                           }
                                                                                                           catch (JSONException ex)
                                                                                                           {
                                                                                                               ex.printStackTrace();
                                                                                                           }
                                                                                                       }
                                                                                                   }, new Response.ErrorListener() {
                                                                                                       @Override
                                                                                                       public void onErrorResponse(VolleyError error) {

                                                                                                       }
                                                                                                   });
                                                                                                   reqqueue.add(facInfoRequest);





                                                                                               } catch (JSONException e) {
                                                                                                   e.printStackTrace();
                                                                                               }
                                                                                           }
                                                                                       }, new Response.ErrorListener() {
                                                                                           @Override
                                                                                           public void onErrorResponse(VolleyError error) {

                                                                                           }
                                                                                       });

                                                                                       reqqueue.add(coverImageRequest);

                                                                                   } catch (JSONException e) {
                                                                                       e.printStackTrace();
                                                                                   }
                                                                               }
                                                                           }, new Response.ErrorListener() {
                                                                               @Override
                                                                               public void onErrorResponse(VolleyError error) {

                                                                               }
                                                                           });

                                                                           reqqueue.add(infoDormRequest);


                                                                       } catch (JSONException e) {
                                                                           e.printStackTrace();
                                                                       }
                                                                   }
                                                               }, new Response.ErrorListener() {
                                                                   @Override
                                                                   public void onErrorResponse(VolleyError error) {

                                                                   }
                                                               });

                                                               reqqueue.add(getFacilityInRoomRequest);
                                                           } catch (JSONException e) {
                                                               e.printStackTrace();
                                                           }
                                                       }
                                                   }, new Response.ErrorListener() {
                                                       @Override
                                                       public void onErrorResponse(VolleyError error) {

                                                       }
                                                   });

                                                   reqqueue.add(infoDormRequest);
                                               } catch (JSONException ex) {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });

                                       reqqueue.add(distanceRequest);

                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               }
                           }, new Response.ErrorListener() {
                               @Override
                               public void onErrorResponse(VolleyError error) {

                               }
                           });
                           reqqueue.add(dormRequest);


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
           reqqueue.add(req);
       }
       // price , distance
       else if (priceCheck == true && distanceCheck == true && facilityCheck == false)
       {
           String getDorm_Infotmation = "http://" + ip + port + "/DormTypeInformation/getAllDorm";
           JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, getDorm_Infotmation, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   final JSONArray res = response;
                   try {
                       infoModel = new Dorm_Information_Model[res.length()];

                       for (int count = 0; count < res.length(); count++)
                       {
                           final int c = count;
                           infoModel[count] = new Dorm_Information_Model();
                           JSONObject ob = res.getJSONObject(count);
                           final String id = ob.getString("dormID");
                           String detail = ob.getString("detail");
                           StringTokenizer tokenizer = new StringTokenizer(detail, ",");
                           String[] hippo = new String[tokenizer.countTokens()];
                           //System.out.println("dorm name: " + id);
                           for (int count2 = 0; count2 < hippo.length; count2++) {
                               //System.out.println(tokenizer.nextToken());
                               hippo[count2] = tokenizer.nextToken();
                           }

                           infoModel[count].setName(id);
                           infoModel[count].setDetail(hippo);

                           // System.out.println("Name: "+infoModel.getName()+"   Detail: ");


                           String pathDorm = "http://" + ip + port + "/dorm/getDormByDormName/" + id;
                           //System.out.println("Path dorm: "+pathDorm);
                           JsonObjectRequest dormRequest = new JsonObjectRequest(Request.Method.GET, pathDorm, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject o = response;
                                   //System.out.println("Response: "+o);
                                   try {
                                       String address = o.getString("address");
                                       double longtitude = o.getDouble("longtitude");
                                       double latitude = o.getDouble("latitude");
                                       StringTokenizer token = new StringTokenizer(address, " ");
                                       while (token.hasMoreTokens()) {
                                           addressText = addressText + " " + token.nextToken();
                                       }
                                       //System.out.println(id + "  " + addressText + "  " + latitude + "  " + longtitude);
                                       infoModel[c].setAddress(addressText);
                                       addressText = "";

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?" + "origin=" + latitude + "," + longtitude + "&destination=" + facofuser.getLatitude() + "," + facofuser.getLongtitude() + "&key=" + newServerKey;
                                       JsonObjectRequest distanceRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               //System.out.println(id+" "+response);
                                               JSONObject ob = response;
                                               try {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   //System.out.println(id + "  " + distanceText);
                                                   String distanceInteger = "";
                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText," ");
                                                   for(int i=0;i<token.countTokens();i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   infoModel[c].setDistance(distanceText);
                                                   infoModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getInfoDormRequest = "http://" + ip + port + "/InfoDorm/getInfoDormByDormName/" + id;
                                                   JsonObjectRequest infoDormRequest = new JsonObjectRequest(Request.Method.GET, getInfoDormRequest, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           //System.out.println("RESPONSE: "+response);
                                                           try {
                                                               final int minPrice = response.getInt("minPrice");
                                                               // System.out.println(minPrice);
                                                               infoModel[c].setPrice(minPrice);

                                                               String getFacilityInRoom = "http://" + ip + port + "/facilityInRoom/getFacilityInRoom/" + id;
                                                               JsonObjectRequest getFacilityInRoomRequest = new JsonObjectRequest(Request.Method.GET, getFacilityInRoom, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       //System.out.println(response);
                                                                       try {

                                                                           JSONObject obj = response;
                                                                           int[] image;
                                                                           JSONArray array = obj.getJSONArray("image");
                                                                           image = new int[array.length()];
                                                                           for (int k = 0; k < image.length; k++) {
                                                                               image[k] = array.getInt(k);
                                                                           }

                                                                           infoModel[c].setFacility(image);

                                                                           String getUsernameAndEmail = "http://" + ip + port + "/dormOwner/getInfoDormOwnerByDormName/" + id;
                                                                           JsonObjectRequest infoDormRequest = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   //System.out.println(response);
                                                                                   try {
                                                                                       String username = response.getString("username");
                                                                                       String email = response.getString("email");
                                                                                       System.out.println(username + "   " + email);

                                                                                       String getCoverImage = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                                       JsonObjectRequest coverImageRequest = new JsonObjectRequest(Request.Method.GET, getCoverImage, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               // System.out.println(response);
                                                                                               try {
                                                                                                   String pathImage = response.getString("coverImage");
                                                                                                   // System.out.println(pathImage);
                                                                                                   infoModel[c].setImage(pathImage);

                                                                                                   infoModel[c].setFaculty(facofuser);

                                                                                                   Double d1 = new Double(infoModel[c].getDistanceDouble());
                                                                                                   Double d2 = new Double(distanceDouble);
                                                                                                   int retvalInformation = d1.compareTo(d2);
                                                                                                   if(distanceDescribe.equals(distanceArray[0]))
                                                                                                   {
                                                                                                       if(retvalInformation < 0 || retvalInformation == 0)
                                                                                                       {
                                                                                                           if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                           {
                                                                                                               informationlist.add(infoModel[c]);
                                                                                                           }
                                                                                                       }

                                                                                                   }
                                                                                                   else if(distanceDescribe.equals(distanceArray[1]))
                                                                                                   {

                                                                                                       if(retvalInformation < 0 || retvalInformation == 0)
                                                                                                       {
                                                                                                           if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                           {
                                                                                                               informationlist.add(infoModel[c]);
                                                                                                           }
                                                                                                       }
                                                                                                   }
                                                                                                   else if(distanceDescribe.equals(distanceArray[2]))
                                                                                                   {
                                                                                                       if(retvalInformation < 0 || retvalInformation == 0)
                                                                                                       {
                                                                                                           if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                           {
                                                                                                               informationlist.add(infoModel[c]);
                                                                                                           }
                                                                                                       }
                                                                                                   }
                                                                                                   else if(distanceDescribe.equals(distanceArray[3]))
                                                                                                   {
                                                                                                       if(retvalInformation < 0 || retvalInformation == 0)
                                                                                                       {
                                                                                                           if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                           {
                                                                                                               informationlist.add(infoModel[c]);
                                                                                                           }
                                                                                                       }
                                                                                                   }

                                                                                                   count2++;
                                                                                                   if(count2 == res.length())
                                                                                                   {
                                                                                                       oncallback.onSuccessType2(informationlist);
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

                                                                                       reqqueue.add(coverImageRequest);

                                                                                   } catch (JSONException e) {
                                                                                       e.printStackTrace();
                                                                                   }
                                                                               }
                                                                           }, new Response.ErrorListener() {
                                                                               @Override
                                                                               public void onErrorResponse(VolleyError error) {

                                                                               }
                                                                           });

                                                                           reqqueue.add(infoDormRequest);


                                                                       } catch (JSONException e) {
                                                                           e.printStackTrace();
                                                                       }
                                                                   }
                                                               }, new Response.ErrorListener() {
                                                                   @Override
                                                                   public void onErrorResponse(VolleyError error) {

                                                                   }
                                                               });

                                                               reqqueue.add(getFacilityInRoomRequest);
                                                           } catch (JSONException e) {
                                                               e.printStackTrace();
                                                           }
                                                       }
                                                   }, new Response.ErrorListener() {
                                                       @Override
                                                       public void onErrorResponse(VolleyError error) {

                                                       }
                                                   });

                                                   reqqueue.add(infoDormRequest);
                                               } catch (JSONException ex) {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });

                                       reqqueue.add(distanceRequest);

                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               }
                           }, new Response.ErrorListener() {
                               @Override
                               public void onErrorResponse(VolleyError error) {

                               }
                           });
                           reqqueue.add(dormRequest);


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
           reqqueue.add(req);
       }
       // price , facility
       else if (priceCheck == true && distanceCheck == false && facilityCheck == true)
       {
           String getDorm_Infotmation = "http://" + ip + port + "/DormTypeInformation/getAllDorm";
           JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, getDorm_Infotmation, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   final JSONArray res = response;
                   try {
                       infoModel = new Dorm_Information_Model[res.length()];

                       for (int count = 0; count < res.length(); count++)
                       {
                           final int c = count;
                           infoModel[count] = new Dorm_Information_Model();
                           JSONObject ob = res.getJSONObject(count);
                           final String id = ob.getString("dormID");
                           String detail = ob.getString("detail");
                           StringTokenizer tokenizer = new StringTokenizer(detail, ",");
                           String[] hippo = new String[tokenizer.countTokens()];
                           //System.out.println("dorm name: " + id);
                           for (int count2 = 0; count2 < hippo.length; count2++) {
                               //System.out.println(tokenizer.nextToken());
                               hippo[count2] = tokenizer.nextToken();
                           }

                           infoModel[count].setName(id);
                           infoModel[count].setDetail(hippo);

                           // System.out.println("Name: "+infoModel.getName()+"   Detail: ");


                           String pathDorm = "http://" + ip + port + "/dorm/getDormByDormName/" + id;
                           //System.out.println("Path dorm: "+pathDorm);
                           JsonObjectRequest dormRequest = new JsonObjectRequest(Request.Method.GET, pathDorm, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject o = response;
                                   //System.out.println("Response: "+o);
                                   try {
                                       String address = o.getString("address");
                                       double longtitude = o.getDouble("longtitude");
                                       double latitude = o.getDouble("latitude");
                                       StringTokenizer token = new StringTokenizer(address, " ");
                                       while (token.hasMoreTokens()) {
                                           addressText = addressText + " " + token.nextToken();
                                       }
                                       //System.out.println(id + "  " + addressText + "  " + latitude + "  " + longtitude);
                                       infoModel[c].setAddress(addressText);
                                       addressText = "";

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?" + "origin=" + latitude + "," + longtitude + "&destination=" + facofuser.getLatitude() + "," + facofuser.getLongtitude() + "&key=" + newServerKey;
                                       JsonObjectRequest distanceRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               //System.out.println(id+" "+response);
                                               JSONObject ob = response;
                                               try {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   //System.out.println(id + "  " + distanceText);
                                                   String distanceInteger = "";
                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText," ");
                                                   for(int i=0;i<token.countTokens();i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   infoModel[c].setDistance(distanceText);
                                                   infoModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getInfoDormRequest = "http://" + ip + port + "/InfoDorm/getInfoDormByDormName/" + id;
                                                   JsonObjectRequest infoDormRequest = new JsonObjectRequest(Request.Method.GET, getInfoDormRequest, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           //System.out.println("RESPONSE: "+response);
                                                           try {
                                                               final int minPrice = response.getInt("minPrice");
                                                               // System.out.println(minPrice);
                                                               infoModel[c].setPrice(minPrice);

                                                               String getFacilityInRoom = "http://" + ip + port + "/facilityInRoom/getFacilityInRoom/" + id;
                                                               JsonObjectRequest getFacilityInRoomRequest = new JsonObjectRequest(Request.Method.GET, getFacilityInRoom, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       //System.out.println(response);
                                                                       try {

                                                                           JSONObject obj = response;
                                                                           int[] image;
                                                                           JSONArray array = obj.getJSONArray("image");
                                                                           image = new int[array.length()];
                                                                           for (int k = 0; k < image.length; k++) {
                                                                               image[k] = array.getInt(k);
                                                                           }

                                                                           infoModel[c].setFacility(image);

                                                                           String getUsernameAndEmail = "http://" + ip + port + "/dormOwner/getInfoDormOwnerByDormName/" + id;
                                                                           final JsonObjectRequest infoDormRequest = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   //System.out.println(response);
                                                                                   try {
                                                                                       String username = response.getString("username");
                                                                                       String email = response.getString("email");
                                                                                       System.out.println(username + "   " + email);

                                                                                       String getCoverImage = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                                       JsonObjectRequest coverImageRequest = new JsonObjectRequest(Request.Method.GET, getCoverImage, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               // System.out.println(response);
                                                                                               try {
                                                                                                   String pathImage = response.getString("coverImage");
                                                                                                   // System.out.println(pathImage);
                                                                                                   infoModel[c].setImage(pathImage);

                                                                                                   infoModel[c].setFaculty(facofuser);

                                                                                                   String pathfacility = "http://" + ip + port + "/facilityInDorm/getFacilityInDorm/" + id;
                                                                                                   JsonObjectRequest facInfoRequest = new JsonObjectRequest(Request.Method.GET, pathfacility, null, new Response.Listener<JSONObject>() {
                                                                                                       @Override
                                                                                                       public void onResponse(JSONObject response) {
                                                                                                           JSONObject obj = response;
                                                                                                           try {
                                                                                                               int facRepeatInformation = 0;
                                                                                                               ArrayList<String> datacompare = new ArrayList<>();
                                                                                                               JSONArray nameth = obj.getJSONArray("nameTH");
                                                                                                               JSONArray nameen = obj.getJSONArray("nameEN");
                                                                                                               for (int j = 0; j < nameth.length(); j++) {
                                                                                                                   datacompare.add(nameth.getString(j));
                                                                                                               }

                                                                                                               for(int count=0;count<datacompare.size();count++)
                                                                                                               {
                                                                                                                   for(int count2=0;count2<facilities.size();count2++)
                                                                                                                   {
                                                                                                                       if (facilities.get(count2).getTextTH().equals(datacompare.get(count)))
                                                                                                                       {
                                                                                                                           facRepeatInformation++;
                                                                                                                           System.out.println(facRepeatInformation);
                                                                                                                           System.out.println(id + "  " + facilities.get(count2).getTextTH() + " equal " + datacompare.get(count) + "   999999999999999999999999");
                                                                                                                           if (facRepeatInformation == facilities.size())
                                                                                                                           {
                                                                                                                               if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                                               {
                                                                                                                                   informationlist.add(infoModel[c]);
                                                                                                                                   zehaha = informationlist.size();
                                                                                                                               }

                                                                                                                           }


                                                                                                                       }
                                                                                                                   }
                                                                                                               }

                                                                                                               count2++;
                                                                                                               if(count2 == res.length())
                                                                                                               {
                                                                                                                   oncallback.onSuccessType2(informationlist);
                                                                                                               }
                                                                                                           }
                                                                                                           catch (JSONException ex)
                                                                                                           {
                                                                                                               ex.printStackTrace();
                                                                                                           }
                                                                                                       }
                                                                                                   }, new Response.ErrorListener() {
                                                                                                       @Override
                                                                                                       public void onErrorResponse(VolleyError error) {

                                                                                                       }
                                                                                                   });
                                                                                                   reqqueue.add(facInfoRequest);





                                                                                               } catch (JSONException e) {
                                                                                                   e.printStackTrace();
                                                                                               }
                                                                                           }
                                                                                       }, new Response.ErrorListener() {
                                                                                           @Override
                                                                                           public void onErrorResponse(VolleyError error) {

                                                                                           }
                                                                                       });

                                                                                       reqqueue.add(coverImageRequest);

                                                                                   } catch (JSONException e) {
                                                                                       e.printStackTrace();
                                                                                   }
                                                                               }
                                                                           }, new Response.ErrorListener() {
                                                                               @Override
                                                                               public void onErrorResponse(VolleyError error) {

                                                                               }
                                                                           });

                                                                           reqqueue.add(infoDormRequest);


                                                                       } catch (JSONException e) {
                                                                           e.printStackTrace();
                                                                       }
                                                                   }
                                                               }, new Response.ErrorListener() {
                                                                   @Override
                                                                   public void onErrorResponse(VolleyError error) {

                                                                   }
                                                               });

                                                               reqqueue.add(getFacilityInRoomRequest);
                                                           } catch (JSONException e) {
                                                               e.printStackTrace();
                                                           }
                                                       }
                                                   }, new Response.ErrorListener() {
                                                       @Override
                                                       public void onErrorResponse(VolleyError error) {

                                                       }
                                                   });

                                                   reqqueue.add(infoDormRequest);
                                               } catch (JSONException ex) {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });

                                       reqqueue.add(distanceRequest);

                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               }
                           }, new Response.ErrorListener() {
                               @Override
                               public void onErrorResponse(VolleyError error) {

                               }
                           });
                           reqqueue.add(dormRequest);


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
           reqqueue.add(req);
       }
       // distance , facility
       else if (priceCheck == false && distanceCheck == true && facilityCheck == true)
       {
           String getDorm_Infotmation = "http://" + ip + port + "/DormTypeInformation/getAllDorm";
           JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, getDorm_Infotmation, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   final JSONArray res = response;
                   try {
                       infoModel = new Dorm_Information_Model[res.length()];

                       for (int count = 0; count < res.length(); count++)
                       {
                           final int c = count;
                           infoModel[count] = new Dorm_Information_Model();
                           JSONObject ob = res.getJSONObject(count);
                           final String id = ob.getString("dormID");
                           String detail = ob.getString("detail");
                           StringTokenizer tokenizer = new StringTokenizer(detail, ",");
                           String[] hippo = new String[tokenizer.countTokens()];
                           //System.out.println("dorm name: " + id);
                           for (int count2 = 0; count2 < hippo.length; count2++) {
                               //System.out.println(tokenizer.nextToken());
                               hippo[count2] = tokenizer.nextToken();
                           }

                           infoModel[count].setName(id);
                           infoModel[count].setDetail(hippo);

                           // System.out.println("Name: "+infoModel.getName()+"   Detail: ");


                           String pathDorm = "http://" + ip + port + "/dorm/getDormByDormName/" + id;
                           //System.out.println("Path dorm: "+pathDorm);
                           JsonObjectRequest dormRequest = new JsonObjectRequest(Request.Method.GET, pathDorm, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject o = response;
                                   //System.out.println("Response: "+o);
                                   try {
                                       String address = o.getString("address");
                                       double longtitude = o.getDouble("longtitude");
                                       double latitude = o.getDouble("latitude");
                                       StringTokenizer token = new StringTokenizer(address, " ");
                                       while (token.hasMoreTokens()) {
                                           addressText = addressText + " " + token.nextToken();
                                       }
                                       //System.out.println(id + "  " + addressText + "  " + latitude + "  " + longtitude);
                                       infoModel[c].setAddress(addressText);
                                       addressText = "";

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?" + "origin=" + latitude + "," + longtitude + "&destination=" + facofuser.getLatitude() + "," + facofuser.getLongtitude() + "&key=" + newServerKey;
                                       JsonObjectRequest distanceRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               //System.out.println(id+" "+response);
                                               JSONObject ob = response;
                                               try {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   //System.out.println(id + "  " + distanceText);
                                                   String distanceInteger = "";
                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText," ");
                                                   for(int i=0;i<token.countTokens();i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   infoModel[c].setDistance(distanceText);
                                                   infoModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getInfoDormRequest = "http://" + ip + port + "/InfoDorm/getInfoDormByDormName/" + id;
                                                   JsonObjectRequest infoDormRequest = new JsonObjectRequest(Request.Method.GET, getInfoDormRequest, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           //System.out.println("RESPONSE: "+response);
                                                           try {
                                                               final int minPrice = response.getInt("minPrice");
                                                               // System.out.println(minPrice);
                                                               infoModel[c].setPrice(minPrice);

                                                               String getFacilityInRoom = "http://" + ip + port + "/facilityInRoom/getFacilityInRoom/" + id;
                                                               JsonObjectRequest getFacilityInRoomRequest = new JsonObjectRequest(Request.Method.GET, getFacilityInRoom, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       //System.out.println(response);
                                                                       try {

                                                                           JSONObject obj = response;
                                                                           int[] image;
                                                                           JSONArray array = obj.getJSONArray("image");
                                                                           image = new int[array.length()];
                                                                           for (int k = 0; k < image.length; k++) {
                                                                               image[k] = array.getInt(k);
                                                                           }

                                                                           infoModel[c].setFacility(image);

                                                                           String getUsernameAndEmail = "http://" + ip + port + "/dormOwner/getInfoDormOwnerByDormName/" + id;
                                                                           JsonObjectRequest infoDormRequest = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   //System.out.println(response);
                                                                                   try {
                                                                                       String username = response.getString("username");
                                                                                       String email = response.getString("email");
                                                                                       System.out.println(username + "   " + email);

                                                                                       String getCoverImage = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                                       JsonObjectRequest coverImageRequest = new JsonObjectRequest(Request.Method.GET, getCoverImage, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               // System.out.println(response);
                                                                                               try {
                                                                                                   String pathImage = response.getString("coverImage");
                                                                                                   // System.out.println(pathImage);
                                                                                                   infoModel[c].setImage(pathImage);

                                                                                                   infoModel[c].setFaculty(facofuser);

                                                                                                   String pathfacility = "http://" + ip + port + "/facilityInDorm/getFacilityInDorm/" + id;
                                                                                                   JsonObjectRequest facInfoRequest = new JsonObjectRequest(Request.Method.GET, pathfacility, null, new Response.Listener<JSONObject>() {
                                                                                                       @Override
                                                                                                       public void onResponse(JSONObject response) {
                                                                                                           JSONObject obj = response;
                                                                                                           try {
                                                                                                               int facRepeatInformation = 0;
                                                                                                               ArrayList<String> datacompare = new ArrayList<>();
                                                                                                               JSONArray nameth = obj.getJSONArray("nameTH");
                                                                                                               JSONArray nameen = obj.getJSONArray("nameEN");
                                                                                                               for (int j = 0; j < nameth.length(); j++) {
                                                                                                                   datacompare.add(nameth.getString(j));
                                                                                                               }

                                                                                                               for(int count=0;count<datacompare.size();count++)
                                                                                                               {
                                                                                                                   for(int count2=0;count2<facilities.size();count2++)
                                                                                                                   {
                                                                                                                       if (facilities.get(count2).getTextTH().equals(datacompare.get(count)))
                                                                                                                       {
                                                                                                                           facRepeatInformation++;
                                                                                                                           System.out.println(facRepeatInformation);
                                                                                                                           System.out.println(id + "  " + facilities.get(count2).getTextTH() + " equal " + datacompare.get(count) + "   999999999999999999999999");
                                                                                                                           if (facRepeatInformation == facilities.size())
                                                                                                                           {
                                                                                                                               Double d1 = new Double(infoModel[c].getDistanceDouble());
                                                                                                                               Double d2 = new Double(distanceDouble);
                                                                                                                               int retvalinfo = d1.compareTo(d2);
                                                                                                                               if(distanceDescribe.equals(distanceArray[0]))
                                                                                                                               {
                                                                                                                                   if(retvalinfo < 0 || retvalinfo == 0)
                                                                                                                                   {
                                                                                                                                       informationlist.add(infoModel[c]);
                                                                                                                                   }

                                                                                                                               }
                                                                                                                               else if(distanceDescribe.equals(distanceArray[1]))
                                                                                                                               {

                                                                                                                                   if(retvalinfo < 0 || retvalinfo == 0)
                                                                                                                                   {
                                                                                                                                       informationlist.add(infoModel[c]);
                                                                                                                                   }
                                                                                                                               }
                                                                                                                               else if(distanceDescribe.equals(distanceArray[2]))
                                                                                                                               {
                                                                                                                                   if(retvalinfo < 0 || retvalinfo == 0)
                                                                                                                                   {
                                                                                                                                       informationlist.add(infoModel[c]);
                                                                                                                                   }
                                                                                                                               }
                                                                                                                               else if(distanceDescribe.equals(distanceArray[3]))
                                                                                                                               {
                                                                                                                                   if(retvalinfo < 0 || retvalinfo == 0)
                                                                                                                                   {
                                                                                                                                       informationlist.add(infoModel[c]);
                                                                                                                                   }
                                                                                                                               }
                                                                                                                           }
                                                                                                                       }
                                                                                                                   }
                                                                                                               }

                                                                                                               count2++;
                                                                                                               if(count2 == res.length())
                                                                                                               {
                                                                                                                   oncallback.onSuccessType2(informationlist);
                                                                                                               }
                                                                                                           }
                                                                                                           catch (JSONException ex)
                                                                                                           {
                                                                                                               ex.printStackTrace();
                                                                                                           }
                                                                                                       }
                                                                                                   }, new Response.ErrorListener() {
                                                                                                       @Override
                                                                                                       public void onErrorResponse(VolleyError error) {

                                                                                                       }
                                                                                                   });
                                                                                                   reqqueue.add(facInfoRequest);





                                                                                               } catch (JSONException e) {
                                                                                                   e.printStackTrace();
                                                                                               }
                                                                                           }
                                                                                       }, new Response.ErrorListener() {
                                                                                           @Override
                                                                                           public void onErrorResponse(VolleyError error) {

                                                                                           }
                                                                                       });

                                                                                       reqqueue.add(coverImageRequest);

                                                                                   } catch (JSONException e) {
                                                                                       e.printStackTrace();
                                                                                   }
                                                                               }
                                                                           }, new Response.ErrorListener() {
                                                                               @Override
                                                                               public void onErrorResponse(VolleyError error) {

                                                                               }
                                                                           });

                                                                           reqqueue.add(infoDormRequest);


                                                                       } catch (JSONException e) {
                                                                           e.printStackTrace();
                                                                       }
                                                                   }
                                                               }, new Response.ErrorListener() {
                                                                   @Override
                                                                   public void onErrorResponse(VolleyError error) {

                                                                   }
                                                               });

                                                               reqqueue.add(getFacilityInRoomRequest);
                                                           } catch (JSONException e) {
                                                               e.printStackTrace();
                                                           }
                                                       }
                                                   }, new Response.ErrorListener() {
                                                       @Override
                                                       public void onErrorResponse(VolleyError error) {

                                                       }
                                                   });

                                                   reqqueue.add(infoDormRequest);
                                               } catch (JSONException ex) {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });

                                       reqqueue.add(distanceRequest);

                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               }
                           }, new Response.ErrorListener() {
                               @Override
                               public void onErrorResponse(VolleyError error) {

                               }
                           });
                           reqqueue.add(dormRequest);


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
           reqqueue.add(req);
       }
       // price , distance , facility
       else if (priceCheck == true && distanceCheck == true && facilityCheck == true)
       {
           String getDorm_Infotmation = "http://" + ip + port + "/DormTypeInformation/getAllDorm";
           JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, getDorm_Infotmation, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   final JSONArray res = response;
                   try {
                       infoModel = new Dorm_Information_Model[res.length()];

                       for (int count = 0; count < res.length(); count++)
                       {
                           final int c = count;
                           infoModel[count] = new Dorm_Information_Model();
                           JSONObject ob = res.getJSONObject(count);
                           final String id = ob.getString("dormID");
                           String detail = ob.getString("detail");
                           StringTokenizer tokenizer = new StringTokenizer(detail, ",");
                           String[] hippo = new String[tokenizer.countTokens()];
                           //System.out.println("dorm name: " + id);
                           for (int count2 = 0; count2 < hippo.length; count2++) {
                               //System.out.println(tokenizer.nextToken());
                               hippo[count2] = tokenizer.nextToken();
                           }

                           infoModel[count].setName(id);
                           infoModel[count].setDetail(hippo);

                           // System.out.println("Name: "+infoModel.getName()+"   Detail: ");


                           String pathDorm = "http://" + ip + port + "/dorm/getDormByDormName/" + id;
                           //System.out.println("Path dorm: "+pathDorm);
                           JsonObjectRequest dormRequest = new JsonObjectRequest(Request.Method.GET, pathDorm, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject o = response;
                                   //System.out.println("Response: "+o);
                                   try {
                                       String address = o.getString("address");
                                       double longtitude = o.getDouble("longtitude");
                                       double latitude = o.getDouble("latitude");
                                       StringTokenizer token = new StringTokenizer(address, " ");
                                       while (token.hasMoreTokens()) {
                                           addressText = addressText + " " + token.nextToken();
                                       }
                                       //System.out.println(id + "  " + addressText + "  " + latitude + "  " + longtitude);
                                       infoModel[c].setAddress(addressText);
                                       addressText = "";

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?" + "origin=" + latitude + "," + longtitude + "&destination=" + facofuser.getLatitude() + "," + facofuser.getLongtitude() + "&key=" + newServerKey;
                                       JsonObjectRequest distanceRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               //System.out.println(id+" "+response);
                                               JSONObject ob = response;
                                               try {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   //System.out.println(id + "  " + distanceText);
                                                   String distanceInteger = "";
                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText," ");
                                                   for(int i=0;i<token.countTokens();i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   infoModel[c].setDistance(distanceText);
                                                   infoModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getInfoDormRequest = "http://" + ip + port + "/InfoDorm/getInfoDormByDormName/" + id;
                                                   JsonObjectRequest infoDormRequest = new JsonObjectRequest(Request.Method.GET, getInfoDormRequest, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           //System.out.println("RESPONSE: "+response);
                                                           try {
                                                               final int minPrice = response.getInt("minPrice");
                                                               // System.out.println(minPrice);
                                                               infoModel[c].setPrice(minPrice);

                                                               String getFacilityInRoom = "http://" + ip + port + "/facilityInRoom/getFacilityInRoom/" + id;
                                                               JsonObjectRequest getFacilityInRoomRequest = new JsonObjectRequest(Request.Method.GET, getFacilityInRoom, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       //System.out.println(response);
                                                                       try {

                                                                           JSONObject obj = response;
                                                                           int[] image;
                                                                           JSONArray array = obj.getJSONArray("image");
                                                                           image = new int[array.length()];
                                                                           for (int k = 0; k < image.length; k++) {
                                                                               image[k] = array.getInt(k);
                                                                           }

                                                                           infoModel[c].setFacility(image);

                                                                           String getUsernameAndEmail = "http://" + ip + port + "/dormOwner/getInfoDormOwnerByDormName/" + id;
                                                                           JsonObjectRequest infoDormRequest = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   //System.out.println(response);
                                                                                   try {
                                                                                       String username = response.getString("username");
                                                                                       String email = response.getString("email");
                                                                                       System.out.println(username + "   " + email);

                                                                                       String getCoverImage = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                                       JsonObjectRequest coverImageRequest = new JsonObjectRequest(Request.Method.GET, getCoverImage, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               // System.out.println(response);
                                                                                               try {
                                                                                                   String pathImage = response.getString("coverImage");
                                                                                                   // System.out.println(pathImage);
                                                                                                   infoModel[c].setImage(pathImage);

                                                                                                   infoModel[c].setFaculty(facofuser);

                                                                                                   String pathfacility = "http://" + ip + port + "/facilityInDorm/getFacilityInDorm/" + id;
                                                                                                   JsonObjectRequest facInfoRequest = new JsonObjectRequest(Request.Method.GET, pathfacility, null, new Response.Listener<JSONObject>() {
                                                                                                       @Override
                                                                                                       public void onResponse(JSONObject response) {
                                                                                                           JSONObject obj = response;
                                                                                                           try {
                                                                                                               int facRepeatInformation = 0;
                                                                                                               ArrayList<String> datacompare = new ArrayList<>();
                                                                                                               JSONArray nameth = obj.getJSONArray("nameTH");
                                                                                                               JSONArray nameen = obj.getJSONArray("nameEN");
                                                                                                               for (int j = 0; j < nameth.length(); j++) {
                                                                                                                   datacompare.add(nameth.getString(j));
                                                                                                               }

                                                                                                               for(int count=0;count<datacompare.size();count++)
                                                                                                               {
                                                                                                                   for(int count2=0;count2<facilities.size();count2++)
                                                                                                                   {
                                                                                                                       if (facilities.get(count2).getTextTH().equals(datacompare.get(count)))
                                                                                                                       {
                                                                                                                           facRepeatInformation++;
                                                                                                                           System.out.println(facRepeatInformation);
                                                                                                                           System.out.println(id + "  " + facilities.get(count2).getTextTH() + " equal " + datacompare.get(count) + "   999999999999999999999999");
                                                                                                                           if (facRepeatInformation == facilities.size())
                                                                                                                           {
                                                                                                                               Double d1 = new Double(infoModel[c].getDistanceDouble());
                                                                                                                               Double d2 = new Double(distanceDouble);
                                                                                                                               int retvalinfo = d1.compareTo(d2);
                                                                                                                               if(distanceDescribe.equals(distanceArray[0]))
                                                                                                                               {
                                                                                                                                   if(retvalinfo < 0 || retvalinfo == 0)
                                                                                                                                   {
                                                                                                                                       if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                                                       {
                                                                                                                                           informationlist.add(infoModel[c]);
                                                                                                                                       }

                                                                                                                                   }

                                                                                                                               }
                                                                                                                               else if(distanceDescribe.equals(distanceArray[1]))
                                                                                                                               {

                                                                                                                                   if(retvalinfo < 0 || retvalinfo == 0)
                                                                                                                                   {
                                                                                                                                       if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                                                       {
                                                                                                                                           informationlist.add(infoModel[c]);
                                                                                                                                       }
                                                                                                                                   }
                                                                                                                               }
                                                                                                                               else if(distanceDescribe.equals(distanceArray[2]))
                                                                                                                               {
                                                                                                                                   if(retvalinfo < 0 || retvalinfo == 0)
                                                                                                                                   {
                                                                                                                                       if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                                                       {
                                                                                                                                           informationlist.add(infoModel[c]);
                                                                                                                                       }
                                                                                                                                   }
                                                                                                                               }
                                                                                                                               else if(distanceDescribe.equals(distanceArray[3]))
                                                                                                                               {
                                                                                                                                   if(retvalinfo < 0 || retvalinfo == 0)
                                                                                                                                   {
                                                                                                                                       if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                                                       {
                                                                                                                                           informationlist.add(infoModel[c]);
                                                                                                                                       }
                                                                                                                                   }
                                                                                                                               }



                                                                                                                           }


                                                                                                                       }
                                                                                                                   }
                                                                                                               }

                                                                                                               count2++;
                                                                                                               if(count2 == res.length())
                                                                                                               {
                                                                                                                   oncallback.onSuccessType2(informationlist);
                                                                                                               }
                                                                                                           }
                                                                                                           catch (JSONException ex)
                                                                                                           {
                                                                                                               ex.printStackTrace();
                                                                                                           }
                                                                                                       }
                                                                                                   }, new Response.ErrorListener() {
                                                                                                       @Override
                                                                                                       public void onErrorResponse(VolleyError error) {

                                                                                                       }
                                                                                                   });
                                                                                                   reqqueue.add(facInfoRequest);





                                                                                               } catch (JSONException e) {
                                                                                                   e.printStackTrace();
                                                                                               }
                                                                                           }
                                                                                       }, new Response.ErrorListener() {
                                                                                           @Override
                                                                                           public void onErrorResponse(VolleyError error) {

                                                                                           }
                                                                                       });

                                                                                       reqqueue.add(coverImageRequest);

                                                                                   } catch (JSONException e) {
                                                                                       e.printStackTrace();
                                                                                   }
                                                                               }
                                                                           }, new Response.ErrorListener() {
                                                                               @Override
                                                                               public void onErrorResponse(VolleyError error) {

                                                                               }
                                                                           });

                                                                           reqqueue.add(infoDormRequest);


                                                                       } catch (JSONException e) {
                                                                           e.printStackTrace();
                                                                       }
                                                                   }
                                                               }, new Response.ErrorListener() {
                                                                   @Override
                                                                   public void onErrorResponse(VolleyError error) {

                                                                   }
                                                               });

                                                               reqqueue.add(getFacilityInRoomRequest);
                                                           } catch (JSONException e) {
                                                               e.printStackTrace();
                                                           }
                                                       }
                                                   }, new Response.ErrorListener() {
                                                       @Override
                                                       public void onErrorResponse(VolleyError error) {

                                                       }
                                                   });

                                                   reqqueue.add(infoDormRequest);
                                               } catch (JSONException ex) {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });

                                       reqqueue.add(distanceRequest);

                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               }
                           }, new Response.ErrorListener() {
                               @Override
                               public void onErrorResponse(VolleyError error) {

                               }
                           });
                           reqqueue.add(dormRequest);


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
           reqqueue.add(req);
       }
   }
   else if(codeStyle == 2)
   {
       RequestQueue requestQueue = Volley.newRequestQueue(this);
       if(priceCheck == true && distanceCheck == false && facilityCheck == false)
       {
           String getAllDorm = "http://"+ip+port+"/DormTypeQuality/getAllDorm";
           JsonArrayRequest request1 = new JsonArrayRequest(Request.Method.GET, getAllDorm, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   JSONArray array = response;
                   try
                   {
                       quaModel = new Dorm_Quality_Model[array.length()];
                       for(int count=0;count<array.length();count++)
                       {
                           final int c = count;
                           quaModel[count] = new Dorm_Quality_Model();
                           JSONObject obj = array.getJSONObject(count);
                           final String id = obj.getString("dormID");
                           JSONArray image = obj.getJSONArray("imagelist");
                           int[] imageArray = new int[image.length()];
                           for(int count2=0;count2<image.length();count2++)
                           {
                               imageArray[count2] = image.getInt(count2);
                               //System.out.println(image.getInt(count2));
                           }
                           quaModel[count].setName(id);
                           quaModel[count].setPremieum(imageArray);

                           String getPositionPath = "http://"+ip+port+"/dorm/getDormByDormName/"+id;
                           //System.out.println(getPositionPath);
                           JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getPositionPath, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   final JSONObject res = response;
                                   //System.out.println(res);
                                   try
                                   {
                                       double latitude = res.getDouble("latitude");
                                       double longtitude = res.getDouble("longtitude");
                                       //System.out.println(id+"  "+latitude+"  "+longtitude);

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                       JsonObjectRequest positionRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               JSONObject ob = response;
                                               System.out.println(ob);
                                               try
                                               {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   System.out.println(id + "  " + distanceText);

                                                   quaModel[c].setDistance(distanceText);

                                                   String getPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+id;
                                                   System.out.println("Url: "+getPrice);
                                                   JsonObjectRequest priceRequest = new JsonObjectRequest(Request.Method.GET, getPrice, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           JSONObject j = response;
                                                           try
                                                           {
                                                               final int minPrice = j.getInt("minPrice");
                                                               System.out.println(minPrice);
                                                               quaModel[c].setPrice(minPrice);

                                                               String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+id;
                                                               JsonObjectRequest usernameEmailRequest = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       try
                                                                       {
                                                                           String username = response.getString("username");
                                                                           String email = response.getString("email");

                                                                           String coverImagePath = "http://"+ip+port+"/CoverImage/getCoverImageByusernameAndEmail/"+username+"/"+email;
                                                                           JsonObjectRequest coverImageRequest = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   try
                                                                                   {
                                                                                       String coverImage = response.getString("coverImage");
                                                                                       System.out.println("Image: "+coverImage);
                                                                                       quaModel[c].setImage(coverImage);
                                                                                       quaModel[c].setFaculty(facofuser);

                                                                                       if(minPrice >= minprice && minPrice <= maxprice)
                                                                                       {
                                                                                           qualitylist.add(quaModel[c]);
                                                                                       }


                                                                                     count2++;
                                                                                       if(count2 == array.length())
                                                                                       {
                                                                                           oncallback.onSuccessType3(qualitylist);
                                                                                       }
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
                                                                           requestQueue.add(coverImageRequest);
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

                                                               requestQueue.add(usernameEmailRequest);
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

                                                   requestQueue.add(priceRequest);

                                               }
                                               catch (JSONException ex)
                                               {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });

                                       requestQueue.add(positionRequest);

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
                           requestQueue.add(request2);
                       }
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
           requestQueue.add(request1);
       }
       // distance only
       else if (priceCheck == false && distanceCheck == true && facilityCheck == false)
       {
           String getAllDorm = "http://"+ip+port+"/DormTypeQuality/getAllDorm";
           JsonArrayRequest request1 = new JsonArrayRequest(Request.Method.GET, getAllDorm, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   JSONArray array = response;
                   try
                   {
                       quaModel = new Dorm_Quality_Model[array.length()];
                       for(int count=0;count<array.length();count++)
                       {
                           final int c = count;
                           quaModel[count] = new Dorm_Quality_Model();
                           JSONObject obj = array.getJSONObject(count);
                           final String id = obj.getString("dormID");
                           JSONArray image = obj.getJSONArray("imagelist");
                           int[] imageArray = new int[image.length()];
                           for(int count2=0;count2<image.length();count2++)
                           {
                               imageArray[count2] = image.getInt(count2);
                               //System.out.println(image.getInt(count2));
                           }
                           quaModel[count].setName(id);
                           quaModel[count].setPremieum(imageArray);

                           String getPositionPath = "http://"+ip+port+"/dorm/getDormByDormName/"+id;
                           //System.out.println(getPositionPath);
                           JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getPositionPath, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   final JSONObject res = response;
                                   //System.out.println(res);
                                   try
                                   {
                                       double latitude = res.getDouble("latitude");
                                       double longtitude = res.getDouble("longtitude");
                                       //System.out.println(id+"  "+latitude+"  "+longtitude);

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                       JsonObjectRequest positionRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               JSONObject ob = response;
                                               System.out.println(ob);
                                               try
                                               {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   System.out.println(id + "  " + distanceText);

                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   quaModel[c].setDistance(distanceText);
                                                   quaModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+id;
                                                   System.out.println("Url: "+getPrice);
                                                   JsonObjectRequest priceRequest = new JsonObjectRequest(Request.Method.GET, getPrice, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           JSONObject j = response;
                                                           try
                                                           {
                                                               final int minPrice = j.getInt("minPrice");
                                                               System.out.println(minPrice);
                                                               quaModel[c].setPrice(minPrice);

                                                               String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+id;
                                                               JsonObjectRequest usernameEmailRequest = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       try
                                                                       {
                                                                           String username = response.getString("username");
                                                                           String email = response.getString("email");

                                                                           String coverImagePath = "http://"+ip+port+"/CoverImage/getCoverImageByusernameAndEmail/"+username+"/"+email;
                                                                           JsonObjectRequest coverImageRequest = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   try
                                                                                   {
                                                                                       String coverImage = response.getString("coverImage");
                                                                                       System.out.println("Image: "+coverImage);
                                                                                       quaModel[c].setImage(coverImage);
                                                                                       quaModel[c].setFaculty(facofuser);



                                                                                       Double d1 = new Double(quaModel[c].getDistanceDouble());
                                                                                       Double d2 = new Double(distanceDouble);
                                                                                       int retval = d1.compareTo(d2);
                                                                                       if(distanceDescribe.equals(distanceArray[0]))
                                                                                       {
                                                                                           if(retval < 0 || retval == 0)
                                                                                           {
                                                                                               qualitylist.add(quaModel[c]);
                                                                                           }

                                                                                       }
                                                                                       else if(distanceDescribe.equals(distanceArray[1]))
                                                                                       {

                                                                                           if(retval < 0 || retval == 0)
                                                                                           {
                                                                                               qualitylist.add(quaModel[c]);
                                                                                           }
                                                                                       }
                                                                                       else if(distanceDescribe.equals(distanceArray[2]))
                                                                                       {
                                                                                           if(retval < 0 || retval == 0)
                                                                                           {
                                                                                               qualitylist.add(quaModel[c]);
                                                                                           }
                                                                                       }
                                                                                       else if(distanceDescribe.equals(distanceArray[3]))
                                                                                       {
                                                                                           if(retval < 0 || retval == 0)
                                                                                           {
                                                                                               qualitylist.add(quaModel[c]);
                                                                                           }
                                                                                       }

                                                                                       count2++;
                                                                                       if(count2 == array.length())
                                                                                       {
                                                                                           oncallback.onSuccessType3(qualitylist);
                                                                                       }
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
                                                                           requestQueue.add(coverImageRequest);
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

                                                               requestQueue.add(usernameEmailRequest);
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

                                                   requestQueue.add(priceRequest);

                                               }
                                               catch (JSONException ex)
                                               {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });

                                       requestQueue.add(positionRequest);

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

                           requestQueue.add(request2);

                       }

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
           requestQueue.add(request1);
       }
       // facility only
       else if (priceCheck == false && distanceCheck == false && facilityCheck == true)
       {
           String getAllDorm = "http://"+ip+port+"/DormTypeQuality/getAllDorm";
           JsonArrayRequest request1 = new JsonArrayRequest(Request.Method.GET, getAllDorm, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   JSONArray array = response;
                   try
                   {
                       quaModel = new Dorm_Quality_Model[array.length()];
                       for(int count=0;count<array.length();count++)
                       {
                           final int c = count;
                           quaModel[count] = new Dorm_Quality_Model();
                           JSONObject obj = array.getJSONObject(count);
                           final String id = obj.getString("dormID");
                           JSONArray image = obj.getJSONArray("imagelist");
                           int[] imageArray = new int[image.length()];
                           for(int count2=0;count2<image.length();count2++)
                           {
                               imageArray[count2] = image.getInt(count2);
                               //System.out.println(image.getInt(count2));
                           }
                           quaModel[count].setName(id);
                           quaModel[count].setPremieum(imageArray);

                           String getPositionPath = "http://"+ip+port+"/dorm/getDormByDormName/"+id;
                           //System.out.println(getPositionPath);
                           JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getPositionPath, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   final JSONObject res = response;
                                   //System.out.println(res);
                                   try
                                   {
                                       double latitude = res.getDouble("latitude");
                                       double longtitude = res.getDouble("longtitude");
                                       //System.out.println(id+"  "+latitude+"  "+longtitude);

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                       JsonObjectRequest positionRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               JSONObject ob = response;
                                               System.out.println(ob);
                                               try
                                               {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   System.out.println(id + "  " + distanceText);

                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   quaModel[c].setDistance(distanceText);
                                                   quaModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+id;
                                                   System.out.println("Url: "+getPrice);
                                                   JsonObjectRequest priceRequest = new JsonObjectRequest(Request.Method.GET, getPrice, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           JSONObject j = response;
                                                           try
                                                           {
                                                               final int minPrice = j.getInt("minPrice");
                                                               System.out.println(minPrice);
                                                               quaModel[c].setPrice(minPrice);

                                                               String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+id;
                                                               JsonObjectRequest usernameEmailRequest = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       try
                                                                       {
                                                                           String username = response.getString("username");
                                                                           String email = response.getString("email");

                                                                           String coverImagePath = "http://"+ip+port+"/CoverImage/getCoverImageByusernameAndEmail/"+username+"/"+email;
                                                                           JsonObjectRequest coverImageRequest = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   try
                                                                                   {
                                                                                       String coverImage = response.getString("coverImage");
                                                                                       System.out.println("Image: "+coverImage);
                                                                                       quaModel[c].setImage(coverImage);
                                                                                       quaModel[c].setFaculty(facofuser);



                                                                                       String pathfacility = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+id;
                                                                                       JsonObjectRequest facQua = new JsonObjectRequest(Request.Method.GET, pathfacility, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               JSONObject obj = response;
                                                                                               try {
                                                                                                   int facRepeatt = 0;
                                                                                                   ArrayList<String> datacompare = new ArrayList<>();
                                                                                                   JSONArray nameth = obj.getJSONArray("nameTH");
                                                                                                   JSONArray nameen = obj.getJSONArray("nameEN");
                                                                                                   for (int j = 0; j < nameth.length(); j++)
                                                                                                   {
                                                                                                       datacompare.add(nameth.getString(j));
                                                                                                   }
                                                                                                   for(int f=0;f<datacompare.size();f++)
                                                                                                   {
                                                                                                       for(int t=0;t<facilities.size();t++)
                                                                                                       {
                                                                                                           if(facilities.get(t).getTextTH().equals(datacompare.get(f)))
                                                                                                           {
                                                                                                               facRepeatt++;
                                                                                                               if(facRepeatt == facilities.size())
                                                                                                               {
                                                                                                                   qualitylist.add(quaModel[c]);
                                                                                                               }
                                                                                                           }
                                                                                                       }
                                                                                                   }

                                                                                                  count2++;
                                                                                                   if(count2 == array.length())
                                                                                                   {
                                                                                                       oncallback.onSuccessType3(qualitylist);
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
                                                                                       requestQueue.add(facQua);

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
                                                                           requestQueue.add(coverImageRequest);
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

                                                               requestQueue.add(usernameEmailRequest);
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

                                                   requestQueue.add(priceRequest);

                                               }
                                               catch (JSONException ex)
                                               {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });

                                       requestQueue.add(positionRequest);

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

                           requestQueue.add(request2);

                       }

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
           requestQueue.add(request1);
       }
       // price , distance
       else if (priceCheck == true && distanceCheck == true && facilityCheck == false)
       {
           String getAllDorm = "http://"+ip+port+"/DormTypeQuality/getAllDorm";
           JsonArrayRequest request1 = new JsonArrayRequest(Request.Method.GET, getAllDorm, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   JSONArray array = response;
                   try
                   {
                       quaModel = new Dorm_Quality_Model[array.length()];
                       for(int count=0;count<array.length();count++)
                       {
                           final int c = count;
                           quaModel[count] = new Dorm_Quality_Model();
                           JSONObject obj = array.getJSONObject(count);
                           final String id = obj.getString("dormID");
                           JSONArray image = obj.getJSONArray("imagelist");
                           int[] imageArray = new int[image.length()];
                           for(int count2=0;count2<image.length();count2++)
                           {
                               imageArray[count2] = image.getInt(count2);
                               //System.out.println(image.getInt(count2));
                           }
                           quaModel[count].setName(id);
                           quaModel[count].setPremieum(imageArray);

                           String getPositionPath = "http://"+ip+port+"/dorm/getDormByDormName/"+id;
                           //System.out.println(getPositionPath);
                           JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getPositionPath, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   final JSONObject res = response;
                                   //System.out.println(res);
                                   try
                                   {
                                       double latitude = res.getDouble("latitude");
                                       double longtitude = res.getDouble("longtitude");
                                       //System.out.println(id+"  "+latitude+"  "+longtitude);

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                       JsonObjectRequest positionRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               JSONObject ob = response;
                                               System.out.println(ob);
                                               try
                                               {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   System.out.println(id + "  " + distanceText);

                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   quaModel[c].setDistance(distanceText);
                                                   quaModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+id;
                                                   System.out.println("Url: "+getPrice);
                                                   JsonObjectRequest priceRequest = new JsonObjectRequest(Request.Method.GET, getPrice, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           JSONObject j = response;
                                                           try
                                                           {
                                                               final int minPrice = j.getInt("minPrice");
                                                               System.out.println(minPrice);
                                                               quaModel[c].setPrice(minPrice);

                                                               String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+id;
                                                               JsonObjectRequest usernameEmailRequest = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       try
                                                                       {
                                                                           String username = response.getString("username");
                                                                           String email = response.getString("email");

                                                                           String coverImagePath = "http://"+ip+port+"/CoverImage/getCoverImageByusernameAndEmail/"+username+"/"+email;
                                                                           JsonObjectRequest coverImageRequest = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   try
                                                                                   {
                                                                                       String coverImage = response.getString("coverImage");
                                                                                       System.out.println("Image: "+coverImage);
                                                                                       quaModel[c].setImage(coverImage);
                                                                                       quaModel[c].setFaculty(facofuser);



                                                                                       Double d1 = new Double(quaModel[c].getDistanceDouble());
                                                                                       Double d2 = new Double(distanceDouble);
                                                                                       int retval = d1.compareTo(d2);
                                                                                       if(distanceDescribe.equals(distanceArray[0]))
                                                                                       {
                                                                                           if(retval < 0 || retval == 0)
                                                                                           {
                                                                                               if(minPrice >= minprice && minPrice <= maxprice)
                                                                                               {
                                                                                                   qualitylist.add(quaModel[c]);
                                                                                                   zehaha = qualitylist.size();
                                                                                               }
                                                                                           }

                                                                                       }
                                                                                       else if(distanceDescribe.equals(distanceArray[1]))
                                                                                       {

                                                                                           if(retval < 0 || retval == 0)
                                                                                           {
                                                                                               if(minPrice >= minprice && minPrice <= maxprice)
                                                                                               {
                                                                                                   qualitylist.add(quaModel[c]);
                                                                                                   zehaha = qualitylist.size();

                                                                                               }
                                                                                           }
                                                                                       }
                                                                                       else if(distanceDescribe.equals(distanceArray[2]))
                                                                                       {
                                                                                           if(retval < 0 || retval == 0)
                                                                                           {
                                                                                               if(minPrice >= minprice && minPrice <= maxprice)
                                                                                               {
                                                                                                   qualitylist.add(quaModel[c]);
                                                                                                   zehaha = qualitylist.size();

                                                                                               }
                                                                                           }
                                                                                       }
                                                                                       else if(distanceDescribe.equals(distanceArray[3]))
                                                                                       {
                                                                                           if(retval < 0 || retval == 0)
                                                                                           {
                                                                                               if(minPrice >= minprice && minPrice <= maxprice)
                                                                                               {
                                                                                                   qualitylist.add(quaModel[c]);
                                                                                                   zehaha = qualitylist.size();

                                                                                               }
                                                                                           }
                                                                                       }

                                                                                      count2++;
                                                                                      if(count2 == array.length())
                                                                                      {
                                                                                          oncallback.onSuccessType3(qualitylist);
                                                                                      }

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
                                                                           requestQueue.add(coverImageRequest);

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

                                                               requestQueue.add(usernameEmailRequest);
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

                                                   requestQueue.add(priceRequest);

                                               }
                                               catch (JSONException ex)
                                               {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });

                                       requestQueue.add(positionRequest);

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

                           requestQueue.add(request2);
                       }
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
           requestQueue.add(request1);
       }
       // price , facility
       else if (priceCheck == true && distanceCheck == false && facilityCheck == true)
       {
           String getAllDorm = "http://"+ip+port+"/DormTypeQuality/getAllDorm";
           JsonArrayRequest request1 = new JsonArrayRequest(Request.Method.GET, getAllDorm, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   JSONArray array = response;
                   try
                   {
                       quaModel = new Dorm_Quality_Model[array.length()];
                       for(int count=0;count<array.length();count++)
                       {
                           final int c = count;
                           quaModel[count] = new Dorm_Quality_Model();
                           JSONObject obj = array.getJSONObject(count);
                           final String id = obj.getString("dormID");
                           JSONArray image = obj.getJSONArray("imagelist");
                           int[] imageArray = new int[image.length()];
                           for(int count2=0;count2<image.length();count2++)
                           {
                               imageArray[count2] = image.getInt(count2);
                               //System.out.println(image.getInt(count2));
                           }
                           quaModel[count].setName(id);
                           quaModel[count].setPremieum(imageArray);

                           String getPositionPath = "http://"+ip+port+"/dorm/getDormByDormName/"+id;
                           //System.out.println(getPositionPath);
                           JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getPositionPath, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   final JSONObject res = response;
                                   //System.out.println(res);
                                   try
                                   {
                                       double latitude = res.getDouble("latitude");
                                       double longtitude = res.getDouble("longtitude");
                                       //System.out.println(id+"  "+latitude+"  "+longtitude);

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                       JsonObjectRequest positionRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               JSONObject ob = response;
                                               System.out.println(ob);
                                               try
                                               {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   System.out.println(id + "  " + distanceText);

                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   quaModel[c].setDistance(distanceText);
                                                   quaModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+id;
                                                   System.out.println("Url: "+getPrice);
                                                   JsonObjectRequest priceRequest = new JsonObjectRequest(Request.Method.GET, getPrice, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           JSONObject j = response;
                                                           try
                                                           {
                                                               final int minPrice = j.getInt("minPrice");
                                                               System.out.println(minPrice);
                                                               quaModel[c].setPrice(minPrice);

                                                               String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+id;
                                                               JsonObjectRequest usernameEmailRequest = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       try
                                                                       {
                                                                           String username = response.getString("username");
                                                                           String email = response.getString("email");

                                                                           String coverImagePath = "http://"+ip+port+"/CoverImage/getCoverImageByusernameAndEmail/"+username+"/"+email;
                                                                           JsonObjectRequest coverImageRequest = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   try
                                                                                   {
                                                                                       String coverImage = response.getString("coverImage");
                                                                                       System.out.println("Image: "+coverImage);
                                                                                       quaModel[c].setImage(coverImage);
                                                                                       quaModel[c].setFaculty(facofuser);



                                                                                       String pathfacility = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+id;
                                                                                       JsonObjectRequest facQua = new JsonObjectRequest(Request.Method.GET, pathfacility, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               JSONObject obj = response;
                                                                                               try {
                                                                                                   int facRepeatt = 0;
                                                                                                   ArrayList<String> datacompare = new ArrayList<>();
                                                                                                   JSONArray nameth = obj.getJSONArray("nameTH");
                                                                                                   JSONArray nameen = obj.getJSONArray("nameEN");
                                                                                                   for (int j = 0; j < nameth.length(); j++)
                                                                                                   {
                                                                                                       datacompare.add(nameth.getString(j));
                                                                                                   }
                                                                                                   for(int f=0;f<datacompare.size();f++)
                                                                                                   {
                                                                                                       for(int t=0;t<facilities.size();t++)
                                                                                                       {
                                                                                                           if(facilities.get(t).getTextTH().equals(datacompare.get(f)))
                                                                                                           {
                                                                                                               facRepeatt++;
                                                                                                               if(facRepeatt == facilities.size())
                                                                                                               {
                                                                                                                   if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                                   {
                                                                                                                       qualitylist.add(quaModel[c]);
                                                                                                                   }

                                                                                                               }
                                                                                                           }
                                                                                                       }
                                                                                                   }

                                                                                                   count2++;
                                                                                                   if(count2 == array.length())
                                                                                                   {
                                                                                                       oncallback.onSuccessType3(qualitylist);
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
                                                                                       requestQueue.add(facQua);

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
                                                                           requestQueue.add(coverImageRequest);
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

                                                               requestQueue.add(usernameEmailRequest);
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

                                                   requestQueue.add(priceRequest);

                                               }
                                               catch (JSONException ex)
                                               {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });

                                       requestQueue.add(positionRequest);

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

                           requestQueue.add(request2);

                       }

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
           requestQueue.add(request1);
       }
       // distance , facility
       else if (priceCheck == false && distanceCheck == true && facilityCheck == true)
       {
           String getAllDorm = "http://"+ip+port+"/DormTypeQuality/getAllDorm";
           JsonArrayRequest request1 = new JsonArrayRequest(Request.Method.GET, getAllDorm, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   JSONArray array = response;
                   try
                   {
                       quaModel = new Dorm_Quality_Model[array.length()];
                       for(int count=0;count<array.length();count++)
                       {
                           final int c = count;
                           quaModel[count] = new Dorm_Quality_Model();
                           JSONObject obj = array.getJSONObject(count);
                           final String id = obj.getString("dormID");
                           JSONArray image = obj.getJSONArray("imagelist");
                           int[] imageArray = new int[image.length()];
                           for(int count2=0;count2<image.length();count2++)
                           {
                               imageArray[count2] = image.getInt(count2);
                               //System.out.println(image.getInt(count2));
                           }
                           quaModel[count].setName(id);
                           quaModel[count].setPremieum(imageArray);

                           String getPositionPath = "http://"+ip+port+"/dorm/getDormByDormName/"+id;
                           //System.out.println(getPositionPath);
                           JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getPositionPath, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   final JSONObject res = response;
                                   //System.out.println(res);
                                   try
                                   {
                                       double latitude = res.getDouble("latitude");
                                       double longtitude = res.getDouble("longtitude");
                                       //System.out.println(id+"  "+latitude+"  "+longtitude);

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                       JsonObjectRequest positionRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               JSONObject ob = response;
                                               System.out.println(ob);
                                               try
                                               {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   System.out.println(id + "  " + distanceText);

                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   quaModel[c].setDistance(distanceText);
                                                   quaModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+id;
                                                   System.out.println("Url: "+getPrice);
                                                   JsonObjectRequest priceRequest = new JsonObjectRequest(Request.Method.GET, getPrice, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           JSONObject j = response;
                                                           try
                                                           {
                                                               final int minPrice = j.getInt("minPrice");
                                                               System.out.println(minPrice);
                                                               quaModel[c].setPrice(minPrice);

                                                               String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+id;
                                                               JsonObjectRequest usernameEmailRequest = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       try
                                                                       {
                                                                           String username = response.getString("username");
                                                                           String email = response.getString("email");

                                                                           String coverImagePath = "http://"+ip+port+"/CoverImage/getCoverImageByusernameAndEmail/"+username+"/"+email;
                                                                           JsonObjectRequest coverImageRequest = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   try
                                                                                   {
                                                                                       String coverImage = response.getString("coverImage");
                                                                                       System.out.println("Image: "+coverImage);
                                                                                       quaModel[c].setImage(coverImage);
                                                                                       quaModel[c].setFaculty(facofuser);



                                                                                       String pathfacility = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+id;
                                                                                       JsonObjectRequest facQua = new JsonObjectRequest(Request.Method.GET, pathfacility, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               JSONObject obj = response;
                                                                                               try {
                                                                                                   int facRepeatt = 0;
                                                                                                   ArrayList<String> datacompare = new ArrayList<>();
                                                                                                   JSONArray nameth = obj.getJSONArray("nameTH");
                                                                                                   JSONArray nameen = obj.getJSONArray("nameEN");
                                                                                                   for (int j = 0; j < nameth.length(); j++)
                                                                                                   {
                                                                                                       datacompare.add(nameth.getString(j));
                                                                                                   }
                                                                                                   for(int f=0;f<datacompare.size();f++)
                                                                                                   {
                                                                                                       for(int t=0;t<facilities.size();t++)
                                                                                                       {
                                                                                                           if(facilities.get(t).getTextTH().equals(datacompare.get(f)))
                                                                                                           {
                                                                                                               facRepeatt++;
                                                                                                               if(facRepeatt == facilities.size())
                                                                                                               {
                                                                                                                   Double d1 = new Double(quaModel[c].getDistanceDouble());
                                                                                                                   Double d2 = new Double(distanceDouble);
                                                                                                                   int retval = d1.compareTo(d2);
                                                                                                                   if(distanceDescribe.equals(distanceArray[0]))
                                                                                                                   {
                                                                                                                       if(retval < 0 || retval == 0)
                                                                                                                       {
                                                                                                                           qualitylist.add(quaModel[c]);
                                                                                                                       }

                                                                                                                   }
                                                                                                                   else if(distanceDescribe.equals(distanceArray[1]))
                                                                                                                   {

                                                                                                                       if(retval < 0 || retval == 0)
                                                                                                                       {
                                                                                                                           qualitylist.add(quaModel[c]);
                                                                                                                       }
                                                                                                                   }
                                                                                                                   else if(distanceDescribe.equals(distanceArray[2]))
                                                                                                                   {
                                                                                                                       if(retval < 0 || retval == 0)
                                                                                                                       {
                                                                                                                           qualitylist.add(quaModel[c]);
                                                                                                                       }
                                                                                                                   }
                                                                                                                   else if(distanceDescribe.equals(distanceArray[3]))
                                                                                                                   {
                                                                                                                       if(retval < 0 || retval == 0)
                                                                                                                       {
                                                                                                                           qualitylist.add(quaModel[c]);
                                                                                                                       }
                                                                                                                   }

                                                                                                               }
                                                                                                           }
                                                                                                       }
                                                                                                   }

                                                                                                   count2++;
                                                                                                   if(count2 == array.length())
                                                                                                   {
                                                                                                       oncallback.onSuccessType3(qualitylist);
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
                                                                                       requestQueue.add(facQua);

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
                                                                           requestQueue.add(coverImageRequest);
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

                                                               requestQueue.add(usernameEmailRequest);
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

                                                   requestQueue.add(priceRequest);

                                               }
                                               catch (JSONException ex)
                                               {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });

                                       requestQueue.add(positionRequest);

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

                           requestQueue.add(request2);

                       }

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
           requestQueue.add(request1);
       }
       // price , distance , facility
       else if (priceCheck == true && distanceCheck == true && facilityCheck == true)
       {
           String getAllDorm = "http://"+ip+port+"/DormTypeQuality/getAllDorm";
           JsonArrayRequest request1 = new JsonArrayRequest(Request.Method.GET, getAllDorm, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   JSONArray array = response;
                   try
                   {
                       quaModel = new Dorm_Quality_Model[array.length()];
                       for(int count=0;count<array.length();count++)
                       {
                           final int c = count;
                           quaModel[count] = new Dorm_Quality_Model();
                           JSONObject obj = array.getJSONObject(count);
                           final String id = obj.getString("dormID");
                           JSONArray image = obj.getJSONArray("imagelist");
                           int[] imageArray = new int[image.length()];
                           for(int count2=0;count2<image.length();count2++)
                           {
                               imageArray[count2] = image.getInt(count2);
                               //System.out.println(image.getInt(count2));
                           }
                           quaModel[count].setName(id);
                           quaModel[count].setPremieum(imageArray);

                           String getPositionPath = "http://"+ip+port+"/dorm/getDormByDormName/"+id;
                           //System.out.println(getPositionPath);
                           JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getPositionPath, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   final JSONObject res = response;
                                   //System.out.println(res);
                                   try
                                   {
                                       double latitude = res.getDouble("latitude");
                                       double longtitude = res.getDouble("longtitude");
                                       //System.out.println(id+"  "+latitude+"  "+longtitude);

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                       JsonObjectRequest positionRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               JSONObject ob = response;
                                               System.out.println(ob);
                                               try
                                               {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   System.out.println(id + "  " + distanceText);

                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   quaModel[c].setDistance(distanceText);
                                                   quaModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+id;
                                                   System.out.println("Url: "+getPrice);
                                                   JsonObjectRequest priceRequest = new JsonObjectRequest(Request.Method.GET, getPrice, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           JSONObject j = response;
                                                           try
                                                           {
                                                               final int minPrice = j.getInt("minPrice");
                                                               System.out.println(minPrice);
                                                               quaModel[c].setPrice(minPrice);

                                                               String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+id;
                                                               JsonObjectRequest usernameEmailRequest = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       try
                                                                       {
                                                                           String username = response.getString("username");
                                                                           String email = response.getString("email");

                                                                           String coverImagePath = "http://"+ip+port+"/CoverImage/getCoverImageByusernameAndEmail/"+username+"/"+email;
                                                                           JsonObjectRequest coverImageRequest = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   try
                                                                                   {
                                                                                       String coverImage = response.getString("coverImage");
                                                                                       System.out.println("Image: "+coverImage);
                                                                                       quaModel[c].setImage(coverImage);
                                                                                       quaModel[c].setFaculty(facofuser);



                                                                                       String pathfacility = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+id;
                                                                                       JsonObjectRequest facQua = new JsonObjectRequest(Request.Method.GET, pathfacility, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               JSONObject obj = response;
                                                                                               try {
                                                                                                   int facRepeatt = 0;
                                                                                                   ArrayList<String> datacompare = new ArrayList<>();
                                                                                                   JSONArray nameth = obj.getJSONArray("nameTH");
                                                                                                   JSONArray nameen = obj.getJSONArray("nameEN");
                                                                                                   for (int j = 0; j < nameth.length(); j++)
                                                                                                   {
                                                                                                       datacompare.add(nameth.getString(j));
                                                                                                   }
                                                                                                   for(int f=0;f<datacompare.size();f++)
                                                                                                   {
                                                                                                       for(int t=0;t<facilities.size();t++)
                                                                                                       {
                                                                                                           if(facilities.get(t).getTextTH().equals(datacompare.get(f)))
                                                                                                           {
                                                                                                               facRepeatt++;
                                                                                                               if(facRepeatt == facilities.size())
                                                                                                               {
                                                                                                                   Double d1 = new Double(quaModel[c].getDistanceDouble());
                                                                                                                   Double d2 = new Double(distanceDouble);
                                                                                                                   int retval = d1.compareTo(d2);
                                                                                                                   if(distanceDescribe.equals(distanceArray[0]))
                                                                                                                   {
                                                                                                                       if(retval < 0 || retval == 0)
                                                                                                                       {
                                                                                                                           if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                                           {
                                                                                                                               qualitylist.add(quaModel[c]);
                                                                                                                           }
                                                                                                                       }

                                                                                                                   }
                                                                                                                   else if(distanceDescribe.equals(distanceArray[1]))
                                                                                                                   {
                                                                                                                       if(retval < 0 || retval == 0)
                                                                                                                       {
                                                                                                                           if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                                           {
                                                                                                                               qualitylist.add(quaModel[c]);
                                                                                                                           }
                                                                                                                       }
                                                                                                                   }
                                                                                                                   else if(distanceDescribe.equals(distanceArray[2]))
                                                                                                                   {
                                                                                                                       if(retval < 0 || retval == 0)
                                                                                                                       {
                                                                                                                           if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                                           {
                                                                                                                               qualitylist.add(quaModel[c]);
                                                                                                                           }
                                                                                                                       }
                                                                                                                   }
                                                                                                                   else if(distanceDescribe.equals(distanceArray[3]))
                                                                                                                   {
                                                                                                                       if(retval < 0 || retval == 0)
                                                                                                                       {
                                                                                                                           if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                                           {
                                                                                                                               qualitylist.add(quaModel[c]);
                                                                                                                           }
                                                                                                                       }
                                                                                                                   }

                                                                                                               }
                                                                                                           }
                                                                                                       }
                                                                                                   }

                                                                                                   count2++;
                                                                                                   if(count2 == array.length())
                                                                                                   {
                                                                                                       oncallback.onSuccessType3(qualitylist);
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
                                                                                       requestQueue.add(facQua);

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
                                                                           requestQueue.add(coverImageRequest);
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

                                                               requestQueue.add(usernameEmailRequest);
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

                                                   requestQueue.add(priceRequest);

                                               }
                                               catch (JSONException ex)
                                               {
                                                   ex.printStackTrace();
                                               }

                                           }
                                       }, new Response.ErrorListener() {
                                           @Override
                                           public void onErrorResponse(VolleyError error) {

                                           }
                                       });

                                       requestQueue.add(positionRequest);

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

                           requestQueue.add(request2);

                       }

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
           requestQueue.add(request1);
       }
   }
   else if(codeStyle == 3)
   {
       RequestQueue requestqueue = Volley.newRequestQueue(this);
       if(priceCheck == true && distanceCheck == false && facilityCheck == false)
       {
           String getAllDormBrandName = "http://"+ip+port+"/DormTypeBrandName/getAllDorm";
           JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, getAllDormBrandName, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   JSONArray datalist = response;
                   try
                   {
                       brandModel = new Dorm_BrandName_Model[datalist.length()];
                       for(int count=0;count<datalist.length();count++)
                       {
                           final int c = count;
                           brandModel[count] = new Dorm_BrandName_Model();
                           JSONObject obj = datalist.getJSONObject(count);
                           final String dormId = obj.getString("dormID");
                           System.out.println("DORM NAME: "+dormId);
                           brandModel[count].setName(dormId);

                           String getPosition = "http://"+ip+port+"/dorm/getDormByDormName/"+dormId;
                           JsonObjectRequest positionDormRequest = new JsonObjectRequest(Request.Method.GET, getPosition, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject data = response;
                                   //System.out.println(data);
                                   try
                                   {
                                       String address = data.getString("address");
                                       //System.out.println("Address: "+address);
                                       double latitude = data.getDouble("latitude");
                                       double longtitude = data.getDouble("longtitude");
                                       String collecttext = " ";
                                       StringTokenizer token = new StringTokenizer(address," ");
                                       while(token.hasMoreTokens())
                                       {
                                           collecttext = collecttext + token.nextToken()+" ";
                                       }
                                       //System.out.println(collecttext);
                                       brandModel[c].setAddress(collecttext);

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                       JsonObjectRequest distanceRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               JSONObject ob = response;
                                               try
                                               {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   //System.out.println(dormId + "  " + distanceText);
                                                   String distanceInteger = "";
                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   brandModel[c].setDistance(distanceText);
                                                   brandModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getMinPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dormId;
                                                   JsonObjectRequest minpriceRequest = new JsonObjectRequest(Request.Method.GET, getMinPrice, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           try
                                                           {
                                                               final int minPrice = response.getInt("minPrice");
                                                               System.out.println("Price: "+minPrice);
                                                               brandModel[c].setPrice(minPrice);

                                                               String facilityInDorm = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+dormId;
                                                               JsonObjectRequest facilityRequest = new JsonObjectRequest(Request.Method.GET, facilityInDorm, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       JSONObject obj = response;
                                                                       try
                                                                       {
                                                                           String[] fac;
                                                                           JSONArray textTh = obj.getJSONArray("nameTH");
                                                                           JSONArray array = obj.getJSONArray("image");
                                                                           fac = new String[array.length()];
                                                                           for(int j=0;j<array.length();j++)
                                                                           {
                                                                               fac[j] = textTh.getString(j);
                                                                               System.out.println(textTh.get(j)+" "+fac[j]);
                                                                           }

                                                                           brandModel[c].setPremieum(fac);

                                                                           String usernameAndEmailRequest = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormId;
                                                                           JsonObjectRequest usernameAndEmailreq = new JsonObjectRequest(Request.Method.GET, usernameAndEmailRequest, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   try
                                                                                   {
                                                                                       String username = response.getString("username");
                                                                                       String email = response.getString("email");

                                                                                       String logoBrandName = "http://"+ip+port+"/LogoBrandName/getPathLogoBrand/"+username+"/"+email;
                                                                                       JsonObjectRequest logReq = new JsonObjectRequest(Request.Method.GET, logoBrandName, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               try
                                                                                               {
                                                                                                   String logo = response.getString("logo");
                                                                                                   brandModel[c].setLogo(logo);
                                                                                                   brandModel[c].setFaculty(facofuser);

                                                                                                   if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                   {
                                                                                                       brandnamelist.add(brandModel[c]);
                                                                                                   }

                                                                                                   count2++;
                                                                                                   if(count2 == datalist.length())
                                                                                                   {
                                                                                                       oncallback.onSuccessType4(brandnamelist);
                                                                                                   }
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

                                                                                       requestqueue.add(logReq);
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

                                                                           requestqueue.add(usernameAndEmailreq);


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

                                                               requestqueue.add(facilityRequest);


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

                                                   requestqueue.add(minpriceRequest);
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

                                       requestqueue.add(distanceRequest);
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

                           requestqueue.add(positionDormRequest);

                       }
                   }
                   catch(JSONException exception)
                   {
                       exception.printStackTrace();
                   }


               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }
           });

           requestqueue.add(request);
       }
       // distance only
       else if (priceCheck == false && distanceCheck == true && facilityCheck == false)
       {
           String getAllDormBrandName = "http://"+ip+port+"/DormTypeBrandName/getAllDorm";
           JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, getAllDormBrandName, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   JSONArray datalist = response;
                   try
                   {
                       brandModel = new Dorm_BrandName_Model[datalist.length()];
                       for(int count=0;count<datalist.length();count++)
                       {
                           final int c = count;
                           brandModel[count] = new Dorm_BrandName_Model();
                           JSONObject obj = datalist.getJSONObject(count);
                           final String dormId = obj.getString("dormID");
                           System.out.println("DORM NAME: "+dormId);
                           brandModel[count].setName(dormId);

                           String getPosition = "http://"+ip+port+"/dorm/getDormByDormName/"+dormId;
                           JsonObjectRequest positionDormRequest = new JsonObjectRequest(Request.Method.GET, getPosition, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject data = response;
                                   //System.out.println(data);
                                   try
                                   {
                                       String address = data.getString("address");
                                       //System.out.println("Address: "+address);
                                       double latitude = data.getDouble("latitude");
                                       double longtitude = data.getDouble("longtitude");
                                       String collecttext = " ";
                                       StringTokenizer token = new StringTokenizer(address," ");
                                       while(token.hasMoreTokens())
                                       {
                                           collecttext = collecttext + token.nextToken()+" ";
                                       }
                                       //System.out.println(collecttext);
                                       brandModel[c].setAddress(collecttext);

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                       JsonObjectRequest distanceRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               JSONObject ob = response;
                                               try
                                               {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   //System.out.println(dormId + "  " + distanceText);
                                                   String distanceInteger = "";
                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   brandModel[c].setDistance(distanceText);
                                                   brandModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getMinPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dormId;
                                                   JsonObjectRequest minpriceRequest = new JsonObjectRequest(Request.Method.GET, getMinPrice, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           try
                                                           {
                                                               final int minPrice = response.getInt("minPrice");
                                                               System.out.println("Price: "+minPrice);
                                                               brandModel[c].setPrice(minPrice);

                                                               String facilityInDorm = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+dormId;
                                                               JsonObjectRequest facilityRequest = new JsonObjectRequest(Request.Method.GET, facilityInDorm, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       JSONObject obj = response;
                                                                       try
                                                                       {
                                                                           String[] fac;
                                                                           JSONArray textTh = obj.getJSONArray("nameTH");
                                                                           JSONArray array = obj.getJSONArray("image");
                                                                           fac = new String[array.length()];
                                                                           for(int j=0;j<array.length();j++)
                                                                           {
                                                                               fac[j] = textTh.getString(j);
                                                                               System.out.println(textTh.get(j)+" "+fac[j]);
                                                                           }

                                                                           brandModel[c].setPremieum(fac);

                                                                           String usernameAndEmailRequest = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormId;
                                                                           JsonObjectRequest usernameAndEmailreq = new JsonObjectRequest(Request.Method.GET, usernameAndEmailRequest, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   try
                                                                                   {
                                                                                       String username = response.getString("username");
                                                                                       String email = response.getString("email");

                                                                                       String logoBrandName = "http://"+ip+port+"/LogoBrandName/getPathLogoBrand/"+username+"/"+email;
                                                                                       JsonObjectRequest logReq = new JsonObjectRequest(Request.Method.GET, logoBrandName, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               try
                                                                                               {
                                                                                                   String logo = response.getString("logo");
                                                                                                   brandModel[c].setLogo(logo);
                                                                                                   brandModel[c].setFaculty(facofuser);

                                                                                                   Double d1 = new Double(brandModel[c].getDistanceDouble());
                                                                                                   Double d2 = new Double(distanceDouble);
                                                                                                   int retval = d1.compareTo(d2);
                                                                                                   if(distanceDescribe.equals(distanceArray[0]))
                                                                                                   {
                                                                                                       if(retval < 0 || retval == 0)
                                                                                                       {
                                                                                                           brandnamelist.add(brandModel[c]);
                                                                                                       }

                                                                                                   }
                                                                                                   else if(distanceDescribe.equals(distanceArray[1]))
                                                                                                   {

                                                                                                       if(retval < 0 || retval == 0)
                                                                                                       {
                                                                                                           brandnamelist.add(brandModel[c]);
                                                                                                       }
                                                                                                   }
                                                                                                   else if(distanceDescribe.equals(distanceArray[2]))
                                                                                                   {
                                                                                                       if(retval < 0 || retval == 0)
                                                                                                       {
                                                                                                           brandnamelist.add(brandModel[c]);
                                                                                                       }
                                                                                                   }
                                                                                                   else if(distanceDescribe.equals(distanceArray[3]))
                                                                                                   {
                                                                                                       if(retval < 0 || retval == 0)
                                                                                                       {
                                                                                                           brandnamelist.add(brandModel[c]);
                                                                                                       }
                                                                                                   }

                                                                                                   count2++;
                                                                                                   if(count2 == datalist.length())
                                                                                                   {
                                                                                                       oncallback.onSuccessType4(brandnamelist);
                                                                                                   }
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

                                                                                       requestqueue.add(logReq);
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

                                                                           requestqueue.add(usernameAndEmailreq);


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

                                                               requestqueue.add(facilityRequest);


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

                                                   requestqueue.add(minpriceRequest);
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

                                       requestqueue.add(distanceRequest);
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

                           requestqueue.add(positionDormRequest);

                       }
                   }
                   catch(JSONException exception)
                   {
                       exception.printStackTrace();
                   }


               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }
           });

           requestqueue.add(request);
       }
       // facility only
       else if (priceCheck == false && distanceCheck == false && facilityCheck == true)
       {
           String getAllDormBrandName = "http://"+ip+port+"/DormTypeBrandName/getAllDorm";
           JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, getAllDormBrandName, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   JSONArray datalist = response;
                   try
                   {
                       brandModel = new Dorm_BrandName_Model[datalist.length()];
                       for(int count=0;count<datalist.length();count++)
                       {
                           final int c = count;
                           brandModel[count] = new Dorm_BrandName_Model();
                           JSONObject obj = datalist.getJSONObject(count);
                           final String dormId = obj.getString("dormID");
                           System.out.println("DORM NAME: "+dormId);
                           brandModel[count].setName(dormId);

                           String getPosition = "http://"+ip+port+"/dorm/getDormByDormName/"+dormId;
                           JsonObjectRequest positionDormRequest = new JsonObjectRequest(Request.Method.GET, getPosition, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject data = response;
                                   //System.out.println(data);
                                   try
                                   {
                                       String address = data.getString("address");
                                       //System.out.println("Address: "+address);
                                       double latitude = data.getDouble("latitude");
                                       double longtitude = data.getDouble("longtitude");
                                       String collecttext = " ";
                                       StringTokenizer token = new StringTokenizer(address," ");
                                       while(token.hasMoreTokens())
                                       {
                                           collecttext = collecttext + token.nextToken()+" ";
                                       }
                                       //System.out.println(collecttext);
                                       brandModel[c].setAddress(collecttext);

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                       JsonObjectRequest distanceRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               JSONObject ob = response;
                                               try
                                               {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   //System.out.println(dormId + "  " + distanceText);
                                                   String distanceInteger = "";
                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   brandModel[c].setDistance(distanceText);
                                                   brandModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getMinPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dormId;
                                                   JsonObjectRequest minpriceRequest = new JsonObjectRequest(Request.Method.GET, getMinPrice, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           try
                                                           {
                                                               final int minPrice = response.getInt("minPrice");
                                                               System.out.println("Price: "+minPrice);
                                                               brandModel[c].setPrice(minPrice);

                                                               String facilityInDorm = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+dormId;
                                                               JsonObjectRequest facilityRequest = new JsonObjectRequest(Request.Method.GET, facilityInDorm, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       JSONObject obj = response;
                                                                       try
                                                                       {
                                                                           String[] fac;
                                                                           JSONArray textTh = obj.getJSONArray("nameTH");
                                                                           JSONArray array = obj.getJSONArray("image");
                                                                           fac = new String[array.length()];
                                                                           for(int j=0;j<array.length();j++)
                                                                           {
                                                                               fac[j] = textTh.getString(j);
                                                                               System.out.println(textTh.get(j)+" "+fac[j]);
                                                                           }

                                                                           brandModel[c].setPremieum(fac);

                                                                           String usernameAndEmailRequest = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormId;
                                                                           JsonObjectRequest usernameAndEmailreq = new JsonObjectRequest(Request.Method.GET, usernameAndEmailRequest, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   try
                                                                                   {
                                                                                       String username = response.getString("username");
                                                                                       String email = response.getString("email");

                                                                                       String logoBrandName = "http://"+ip+port+"/LogoBrandName/getPathLogoBrand/"+username+"/"+email;
                                                                                       JsonObjectRequest logReq = new JsonObjectRequest(Request.Method.GET, logoBrandName, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               try
                                                                                               {
                                                                                                   String logo = response.getString("logo");
                                                                                                   brandModel[c].setLogo(logo);
                                                                                                   brandModel[c].setFaculty(facofuser);


                                                                                                   String pathfacility = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+dormId;
                                                                                                   JsonObjectRequest facBrandName = new JsonObjectRequest(Request.Method.GET, pathfacility, null, new Response.Listener<JSONObject>() {
                                                                                                       @Override
                                                                                                       public void onResponse(JSONObject response) {
                                                                                                           JSONObject obj = response;
                                                                                                           try {
                                                                                                               int facRepeat = 0;
                                                                                                               ArrayList<String> datacompare = new ArrayList<>();
                                                                                                               JSONArray nameth = obj.getJSONArray("nameTH");
                                                                                                               JSONArray nameen = obj.getJSONArray("nameEN");

                                                                                                               for (int j = 0; j < nameth.length(); j++)
                                                                                                               {
                                                                                                                   datacompare.add(nameth.getString(j));
                                                                                                               }

                                                                                                               for(int y=0;y<datacompare.size();y++)
                                                                                                               {
                                                                                                                   for(int e=0;e<facilities.size();e++)
                                                                                                                   {
                                                                                                                       if(facilities.get(e).getTextTH().equals(datacompare.get(y)))
                                                                                                                       {
                                                                                                                           facRepeat++;
                                                                                                                           if(facRepeat == facilities.size())
                                                                                                                           {
                                                                                                                               brandnamelist.add(brandModel[c]);
                                                                                                                           }
                                                                                                                       }
                                                                                                                   }
                                                                                                               }

                                                                                                             count2++;
                                                                                                               if(count2 == datalist.length())
                                                                                                               {
                                                                                                                   oncallback.onSuccessType4(brandnamelist);
                                                                                                               }

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

                                                                                                   requestqueue.add(facBrandName);

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

                                                                                       requestqueue.add(logReq);
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

                                                                           requestqueue.add(usernameAndEmailreq);


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

                                                               requestqueue.add(facilityRequest);


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

                                                   requestqueue.add(minpriceRequest);
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

                                       requestqueue.add(distanceRequest);
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

                           requestqueue.add(positionDormRequest);

                       }
                   }
                   catch(JSONException exception)
                   {
                       exception.printStackTrace();
                   }


               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }
           });
           requestqueue.add(request);
       }
       // price , distance
       else if (priceCheck == true && distanceCheck == true && facilityCheck == false)
       {
           String getAllDormBrandName = "http://"+ip+port+"/DormTypeBrandName/getAllDorm";
           JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, getAllDormBrandName, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   JSONArray datalist = response;
                   try
                   {
                       brandModel = new Dorm_BrandName_Model[datalist.length()];
                       for(int count=0;count<datalist.length();count++)
                       {
                           final int c = count;
                           brandModel[count] = new Dorm_BrandName_Model();
                           JSONObject obj = datalist.getJSONObject(count);
                           final String dormId = obj.getString("dormID");
                           System.out.println("DORM NAME: "+dormId);
                           brandModel[count].setName(dormId);

                           String getPosition = "http://"+ip+port+"/dorm/getDormByDormName/"+dormId;
                           JsonObjectRequest positionDormRequest = new JsonObjectRequest(Request.Method.GET, getPosition, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject data = response;
                                   //System.out.println(data);
                                   try
                                   {
                                       String address = data.getString("address");
                                       //System.out.println("Address: "+address);
                                       double latitude = data.getDouble("latitude");
                                       double longtitude = data.getDouble("longtitude");
                                       String collecttext = " ";
                                       StringTokenizer token = new StringTokenizer(address," ");
                                       while(token.hasMoreTokens())
                                       {
                                           collecttext = collecttext + token.nextToken()+" ";
                                       }
                                       //System.out.println(collecttext);
                                       brandModel[c].setAddress(collecttext);

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                       JsonObjectRequest distanceRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               JSONObject ob = response;
                                               try
                                               {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   //System.out.println(dormId + "  " + distanceText);
                                                   String distanceInteger = "";
                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   brandModel[c].setDistance(distanceText);
                                                   brandModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getMinPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dormId;
                                                   JsonObjectRequest minpriceRequest = new JsonObjectRequest(Request.Method.GET, getMinPrice, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           try
                                                           {
                                                               final int minPrice = response.getInt("minPrice");
                                                               System.out.println("Price: "+minPrice);
                                                               brandModel[c].setPrice(minPrice);

                                                               String facilityInDorm = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+dormId;
                                                               JsonObjectRequest facilityRequest = new JsonObjectRequest(Request.Method.GET, facilityInDorm, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       JSONObject obj = response;
                                                                       try
                                                                       {
                                                                           String[] fac;
                                                                           JSONArray textTh = obj.getJSONArray("nameTH");
                                                                           JSONArray array = obj.getJSONArray("image");
                                                                           fac = new String[array.length()];
                                                                           for(int j=0;j<array.length();j++)
                                                                           {
                                                                               fac[j] = textTh.getString(j);
                                                                               System.out.println(textTh.get(j)+" "+fac[j]);
                                                                           }

                                                                           brandModel[c].setPremieum(fac);

                                                                           String usernameAndEmailRequest = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormId;
                                                                           JsonObjectRequest usernameAndEmailreq = new JsonObjectRequest(Request.Method.GET, usernameAndEmailRequest, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   try
                                                                                   {
                                                                                       String username = response.getString("username");
                                                                                       String email = response.getString("email");

                                                                                       String logoBrandName = "http://"+ip+port+"/LogoBrandName/getPathLogoBrand/"+username+"/"+email;
                                                                                       JsonObjectRequest logReq = new JsonObjectRequest(Request.Method.GET, logoBrandName, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               try
                                                                                               {
                                                                                                   String logo = response.getString("logo");
                                                                                                   brandModel[c].setLogo(logo);
                                                                                                   brandModel[c].setFaculty(facofuser);

                                                                                                   Double d1 = new Double(brandModel[c].getDistanceDouble());
                                                                                                   Double d2 = new Double(distanceDouble);
                                                                                                   int retval = d1.compareTo(d2);
                                                                                                   if(distanceDescribe.equals(distanceArray[0]))
                                                                                                   {
                                                                                                       if(retval < 0 || retval == 0)
                                                                                                       {
                                                                                                           if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                           {
                                                                                                               brandnamelist.add(brandModel[c]);
                                                                                                           }

                                                                                                       }
                                                                                                   }
                                                                                                   else if(distanceDescribe.equals(distanceArray[1]))
                                                                                                   {

                                                                                                       if(retval < 0 || retval == 0)
                                                                                                       {
                                                                                                           if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                           {
                                                                                                               brandnamelist.add(brandModel[c]);
                                                                                                           }
                                                                                                       }
                                                                                                   }
                                                                                                   else if(distanceDescribe.equals(distanceArray[2]))
                                                                                                   {
                                                                                                       if(retval < 0 || retval == 0)
                                                                                                       {
                                                                                                           if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                           {
                                                                                                               brandnamelist.add(brandModel[c]);
                                                                                                           }
                                                                                                       }
                                                                                                   }
                                                                                                   else if(distanceDescribe.equals(distanceArray[3]))
                                                                                                   {
                                                                                                       if(retval < 0 || retval == 0)
                                                                                                       {
                                                                                                           if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                           {
                                                                                                               brandnamelist.add(brandModel[c]);
                                                                                                           }
                                                                                                       }
                                                                                                   }

                                                                                                 count2++;
                                                                                                   if(count2 == datalist.length())
                                                                                                   {
                                                                                                       oncallback.onSuccessType4(brandnamelist);
                                                                                                   }
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

                                                                                       requestqueue.add(logReq);
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

                                                                           requestqueue.add(usernameAndEmailreq);


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

                                                               requestqueue.add(facilityRequest);


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

                                                   requestqueue.add(minpriceRequest);
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

                                       requestqueue.add(distanceRequest);
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

                           requestqueue.add(positionDormRequest);

                       }
                   }
                   catch(JSONException exception)
                   {
                       exception.printStackTrace();
                   }


               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }
           });

           requestqueue.add(request);
       }
       // price , facility
       else if (priceCheck == true && distanceCheck == false && facilityCheck == true)
       {
           String getAllDormBrandName = "http://"+ip+port+"/DormTypeBrandName/getAllDorm";
           JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, getAllDormBrandName, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   JSONArray datalist = response;
                   try
                   {
                       brandModel = new Dorm_BrandName_Model[datalist.length()];
                       for(int count=0;count<datalist.length();count++)
                       {
                           final int c = count;
                           brandModel[count] = new Dorm_BrandName_Model();
                           JSONObject obj = datalist.getJSONObject(count);
                           final String dormId = obj.getString("dormID");
                           System.out.println("DORM NAME: "+dormId);
                           brandModel[count].setName(dormId);

                           String getPosition = "http://"+ip+port+"/dorm/getDormByDormName/"+dormId;
                           JsonObjectRequest positionDormRequest = new JsonObjectRequest(Request.Method.GET, getPosition, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject data = response;
                                   //System.out.println(data);
                                   try
                                   {
                                       String address = data.getString("address");
                                       //System.out.println("Address: "+address);
                                       double latitude = data.getDouble("latitude");
                                       double longtitude = data.getDouble("longtitude");
                                       String collecttext = " ";
                                       StringTokenizer token = new StringTokenizer(address," ");
                                       while(token.hasMoreTokens())
                                       {
                                           collecttext = collecttext + token.nextToken()+" ";
                                       }
                                       //System.out.println(collecttext);
                                       brandModel[c].setAddress(collecttext);

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                       JsonObjectRequest distanceRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               JSONObject ob = response;
                                               try
                                               {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   //System.out.println(dormId + "  " + distanceText);
                                                   String distanceInteger = "";
                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   brandModel[c].setDistance(distanceText);
                                                   brandModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getMinPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dormId;
                                                   JsonObjectRequest minpriceRequest = new JsonObjectRequest(Request.Method.GET, getMinPrice, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           try
                                                           {
                                                               final int minPrice = response.getInt("minPrice");
                                                               System.out.println("Price: "+minPrice);
                                                               brandModel[c].setPrice(minPrice);

                                                               String facilityInDorm = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+dormId;
                                                               JsonObjectRequest facilityRequest = new JsonObjectRequest(Request.Method.GET, facilityInDorm, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       JSONObject obj = response;
                                                                       try
                                                                       {
                                                                           String[] fac;
                                                                           JSONArray textTh = obj.getJSONArray("nameTH");
                                                                           JSONArray array = obj.getJSONArray("image");
                                                                           fac = new String[array.length()];
                                                                           for(int j=0;j<array.length();j++)
                                                                           {
                                                                               fac[j] = textTh.getString(j);
                                                                               System.out.println(textTh.get(j)+" "+fac[j]);
                                                                           }

                                                                           brandModel[c].setPremieum(fac);

                                                                           String usernameAndEmailRequest = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormId;
                                                                           JsonObjectRequest usernameAndEmailreq = new JsonObjectRequest(Request.Method.GET, usernameAndEmailRequest, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   try
                                                                                   {
                                                                                       String username = response.getString("username");
                                                                                       String email = response.getString("email");

                                                                                       String logoBrandName = "http://"+ip+port+"/LogoBrandName/getPathLogoBrand/"+username+"/"+email;
                                                                                       JsonObjectRequest logReq = new JsonObjectRequest(Request.Method.GET, logoBrandName, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               try
                                                                                               {
                                                                                                   String logo = response.getString("logo");
                                                                                                   brandModel[c].setLogo(logo);
                                                                                                   brandModel[c].setFaculty(facofuser);


                                                                                                   String pathfacility = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+dormId;
                                                                                                   JsonObjectRequest facBrandName = new JsonObjectRequest(Request.Method.GET, pathfacility, null, new Response.Listener<JSONObject>() {
                                                                                                       @Override
                                                                                                       public void onResponse(JSONObject response) {
                                                                                                           JSONObject obj = response;
                                                                                                           try {
                                                                                                               int facRepeat = 0;
                                                                                                               ArrayList<String> datacompare = new ArrayList<>();
                                                                                                               JSONArray nameth = obj.getJSONArray("nameTH");
                                                                                                               JSONArray nameen = obj.getJSONArray("nameEN");

                                                                                                               for (int j = 0; j < nameth.length(); j++)
                                                                                                               {
                                                                                                                   datacompare.add(nameth.getString(j));
                                                                                                               }

                                                                                                               for(int y=0;y<datacompare.size();y++)
                                                                                                               {
                                                                                                                   for(int e=0;e<facilities.size();e++)
                                                                                                                   {
                                                                                                                       if(facilities.get(e).getTextTH().equals(datacompare.get(y)))
                                                                                                                       {
                                                                                                                           facRepeat++;
                                                                                                                           if(facRepeat == facilities.size())
                                                                                                                           {
                                                                                                                               if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                                               {
                                                                                                                                   brandnamelist.add(brandModel[c]);
                                                                                                                               }

                                                                                                                           }
                                                                                                                       }
                                                                                                                   }
                                                                                                               }

                                                                                                             count2++;
                                                                                                               if(count2 == datalist.length())
                                                                                                               {
                                                                                                                   oncallback.onSuccessType4(brandnamelist);
                                                                                                               }
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

                                                                                                   requestqueue.add(facBrandName);

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

                                                                                       requestqueue.add(logReq);
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

                                                                           requestqueue.add(usernameAndEmailreq);


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

                                                               requestqueue.add(facilityRequest);


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

                                                   requestqueue.add(minpriceRequest);
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

                                       requestqueue.add(distanceRequest);
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

                           requestqueue.add(positionDormRequest);

                       }
                   }
                   catch(JSONException exception)
                   {
                       exception.printStackTrace();
                   }


               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }
           });

           requestqueue.add(request);
       }
       // distance , facility
       else if (priceCheck == false && distanceCheck == true && facilityCheck == true)
       {
           String getAllDormBrandName = "http://"+ip+port+"/DormTypeBrandName/getAllDorm";
           JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, getAllDormBrandName, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   JSONArray datalist = response;
                   try
                   {
                       brandModel = new Dorm_BrandName_Model[datalist.length()];
                       for(int count=0;count<datalist.length();count++)
                       {
                           final int c = count;
                           brandModel[count] = new Dorm_BrandName_Model();
                           JSONObject obj = datalist.getJSONObject(count);
                           final String dormId = obj.getString("dormID");
                           System.out.println("DORM NAME: "+dormId);
                           brandModel[count].setName(dormId);

                           String getPosition = "http://"+ip+port+"/dorm/getDormByDormName/"+dormId;
                           JsonObjectRequest positionDormRequest = new JsonObjectRequest(Request.Method.GET, getPosition, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject data = response;
                                   //System.out.println(data);
                                   try
                                   {
                                       String address = data.getString("address");
                                       //System.out.println("Address: "+address);
                                       double latitude = data.getDouble("latitude");
                                       double longtitude = data.getDouble("longtitude");
                                       String collecttext = " ";
                                       StringTokenizer token = new StringTokenizer(address," ");
                                       while(token.hasMoreTokens())
                                       {
                                           collecttext = collecttext + token.nextToken()+" ";
                                       }
                                       //System.out.println(collecttext);
                                       brandModel[c].setAddress(collecttext);

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                       JsonObjectRequest distanceRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               JSONObject ob = response;
                                               try
                                               {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   //System.out.println(dormId + "  " + distanceText);
                                                   String distanceInteger = "";
                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   brandModel[c].setDistance(distanceText);
                                                   brandModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getMinPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dormId;
                                                   JsonObjectRequest minpriceRequest = new JsonObjectRequest(Request.Method.GET, getMinPrice, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           try
                                                           {
                                                               final int minPrice = response.getInt("minPrice");
                                                               System.out.println("Price: "+minPrice);
                                                               brandModel[c].setPrice(minPrice);

                                                               String facilityInDorm = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+dormId;
                                                               JsonObjectRequest facilityRequest = new JsonObjectRequest(Request.Method.GET, facilityInDorm, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       JSONObject obj = response;
                                                                       try
                                                                       {
                                                                           String[] fac;
                                                                           JSONArray textTh = obj.getJSONArray("nameTH");
                                                                           JSONArray array = obj.getJSONArray("image");
                                                                           fac = new String[array.length()];
                                                                           for(int j=0;j<array.length();j++)
                                                                           {
                                                                               fac[j] = textTh.getString(j);
                                                                               System.out.println(textTh.get(j)+" "+fac[j]);
                                                                           }

                                                                           brandModel[c].setPremieum(fac);

                                                                           String usernameAndEmailRequest = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormId;
                                                                           JsonObjectRequest usernameAndEmailreq = new JsonObjectRequest(Request.Method.GET, usernameAndEmailRequest, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   try
                                                                                   {
                                                                                       String username = response.getString("username");
                                                                                       String email = response.getString("email");

                                                                                       String logoBrandName = "http://"+ip+port+"/LogoBrandName/getPathLogoBrand/"+username+"/"+email;
                                                                                       JsonObjectRequest logReq = new JsonObjectRequest(Request.Method.GET, logoBrandName, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               try
                                                                                               {
                                                                                                   String logo = response.getString("logo");
                                                                                                   brandModel[c].setLogo(logo);
                                                                                                   brandModel[c].setFaculty(facofuser);


                                                                                                   String pathfacility = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+dormId;
                                                                                                   JsonObjectRequest facBrandName = new JsonObjectRequest(Request.Method.GET, pathfacility, null, new Response.Listener<JSONObject>() {
                                                                                                       @Override
                                                                                                       public void onResponse(JSONObject response) {
                                                                                                           JSONObject obj = response;
                                                                                                           try {
                                                                                                               int facRepeat = 0;
                                                                                                               ArrayList<String> datacompare = new ArrayList<>();
                                                                                                               JSONArray nameth = obj.getJSONArray("nameTH");
                                                                                                               JSONArray nameen = obj.getJSONArray("nameEN");

                                                                                                               for (int j = 0; j < nameth.length(); j++)
                                                                                                               {
                                                                                                                   datacompare.add(nameth.getString(j));
                                                                                                               }

                                                                                                               for(int y=0;y<datacompare.size();y++)
                                                                                                               {
                                                                                                                   for(int e=0;e<facilities.size();e++)
                                                                                                                   {
                                                                                                                       if(facilities.get(e).getTextTH().equals(datacompare.get(y)))
                                                                                                                       {
                                                                                                                           facRepeat++;
                                                                                                                           if(facRepeat == facilities.size())
                                                                                                                           {
                                                                                                                               Double d1 = new Double(brandModel[c].getDistanceDouble());
                                                                                                                               Double d2 = new Double(distanceDouble);
                                                                                                                               int retval = d1.compareTo(d2);
                                                                                                                               if(distanceDescribe.equals(distanceArray[0]))
                                                                                                                               {
                                                                                                                                   if(retval < 0 || retval == 0)
                                                                                                                                   {
                                                                                                                                       brandnamelist.add(brandModel[c]);
                                                                                                                                   }

                                                                                                                               }
                                                                                                                               else if(distanceDescribe.equals(distanceArray[1]))
                                                                                                                               {
                                                                                                                                   if(retval < 0 || retval == 0)
                                                                                                                                   {
                                                                                                                                       brandnamelist.add(brandModel[c]);
                                                                                                                                   }
                                                                                                                               }
                                                                                                                               else if(distanceDescribe.equals(distanceArray[2]))
                                                                                                                               {
                                                                                                                                   if(retval < 0 || retval == 0)
                                                                                                                                   {
                                                                                                                                       brandnamelist.add(brandModel[c]);
                                                                                                                                   }
                                                                                                                               }
                                                                                                                               else if(distanceDescribe.equals(distanceArray[3]))
                                                                                                                               {
                                                                                                                                   if(retval < 0 || retval == 0)
                                                                                                                                   {
                                                                                                                                       brandnamelist.add(brandModel[c]);
                                                                                                                                   }
                                                                                                                               }



                                                                                                                           }
                                                                                                                       }
                                                                                                                   }
                                                                                                               }

                                                                                                              count2++;
                                                                                                               if(count2 == datalist.length())
                                                                                                               {
                                                                                                                   oncallback.onSuccessType4(brandnamelist);
                                                                                                               }

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

                                                                                                   requestqueue.add(facBrandName);

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

                                                                                       requestqueue.add(logReq);
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

                                                                           requestqueue.add(usernameAndEmailreq);


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

                                                               requestqueue.add(facilityRequest);


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

                                                   requestqueue.add(minpriceRequest);
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

                                       requestqueue.add(distanceRequest);
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

                           requestqueue.add(positionDormRequest);

                       }
                   }
                   catch(JSONException exception)
                   {
                       exception.printStackTrace();
                   }


               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }
           });

           requestqueue.add(request);
       }
       // price , distance , facility
       else if (priceCheck == true && distanceCheck == true && facilityCheck == true)
       {
           String getAllDormBrandName = "http://"+ip+port+"/DormTypeBrandName/getAllDorm";
           JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, getAllDormBrandName, null, new Response.Listener<JSONArray>() {
               @Override
               public void onResponse(JSONArray response) {
                   JSONArray datalist = response;
                   try
                   {
                       brandModel = new Dorm_BrandName_Model[datalist.length()];
                       for(int count=0;count<datalist.length();count++)
                       {
                           final int c = count;
                           brandModel[count] = new Dorm_BrandName_Model();
                           JSONObject obj = datalist.getJSONObject(count);
                           final String dormId = obj.getString("dormID");
                           System.out.println("DORM NAME: "+dormId);
                           brandModel[count].setName(dormId);

                           String getPosition = "http://"+ip+port+"/dorm/getDormByDormName/"+dormId;
                           JsonObjectRequest positionDormRequest = new JsonObjectRequest(Request.Method.GET, getPosition, null, new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   JSONObject data = response;
                                   //System.out.println(data);
                                   try
                                   {
                                       String address = data.getString("address");
                                       //System.out.println("Address: "+address);
                                       double latitude = data.getDouble("latitude");
                                       double longtitude = data.getDouble("longtitude");
                                       String collecttext = " ";
                                       StringTokenizer token = new StringTokenizer(address," ");
                                       while(token.hasMoreTokens())
                                       {
                                           collecttext = collecttext + token.nextToken()+" ";
                                       }
                                       //System.out.println(collecttext);
                                       brandModel[c].setAddress(collecttext);

                                       String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                       JsonObjectRequest distanceRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                           @Override
                                           public void onResponse(JSONObject response) {
                                               JSONObject ob = response;
                                               try
                                               {
                                                   JSONArray route_title = ob.getJSONArray("routes");
                                                   JSONObject routes = route_title.getJSONObject(0);
                                                   JSONArray leg_title = routes.getJSONArray("legs");
                                                   JSONObject legs = leg_title.getJSONObject(0);
                                                   JSONObject distance = legs.getJSONObject("distance");
                                                   String distanceText = distance.getString("text");
                                                   //System.out.println(dormId + "  " + distanceText);
                                                   String distanceInteger = "";
                                                   String unit = "";
                                                   StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                   for (int i = 0; i < token.countTokens(); i++)
                                                   {
                                                       disText = token.nextToken();
                                                       unit = token.nextToken();
                                                   }
                                                   brandModel[c].setDistance(distanceText);
                                                   brandModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                   String getMinPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dormId;
                                                   JsonObjectRequest minpriceRequest = new JsonObjectRequest(Request.Method.GET, getMinPrice, null, new Response.Listener<JSONObject>() {
                                                       @Override
                                                       public void onResponse(JSONObject response) {
                                                           try
                                                           {
                                                               final int minPrice = response.getInt("minPrice");
                                                               System.out.println("Price: "+minPrice);
                                                               brandModel[c].setPrice(minPrice);

                                                               String facilityInDorm = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+dormId;
                                                               JsonObjectRequest facilityRequest = new JsonObjectRequest(Request.Method.GET, facilityInDorm, null, new Response.Listener<JSONObject>() {
                                                                   @Override
                                                                   public void onResponse(JSONObject response) {
                                                                       JSONObject obj = response;
                                                                       try
                                                                       {
                                                                           String[] fac;
                                                                           JSONArray textTh = obj.getJSONArray("nameTH");
                                                                           JSONArray array = obj.getJSONArray("image");
                                                                           fac = new String[array.length()];
                                                                           for(int j=0;j<array.length();j++)
                                                                           {
                                                                               fac[j] = textTh.getString(j);
                                                                               System.out.println(textTh.get(j)+" "+fac[j]);
                                                                           }

                                                                           brandModel[c].setPremieum(fac);

                                                                           String usernameAndEmailRequest = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormId;
                                                                           JsonObjectRequest usernameAndEmailreq = new JsonObjectRequest(Request.Method.GET, usernameAndEmailRequest, null, new Response.Listener<JSONObject>() {
                                                                               @Override
                                                                               public void onResponse(JSONObject response) {
                                                                                   try
                                                                                   {
                                                                                       String username = response.getString("username");
                                                                                       String email = response.getString("email");

                                                                                       String logoBrandName = "http://"+ip+port+"/LogoBrandName/getPathLogoBrand/"+username+"/"+email;
                                                                                       JsonObjectRequest logReq = new JsonObjectRequest(Request.Method.GET, logoBrandName, null, new Response.Listener<JSONObject>() {
                                                                                           @Override
                                                                                           public void onResponse(JSONObject response) {
                                                                                               try
                                                                                               {
                                                                                                   String logo = response.getString("logo");
                                                                                                   brandModel[c].setLogo(logo);
                                                                                                   brandModel[c].setFaculty(facofuser);


                                                                                                   String pathfacility = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+dormId;
                                                                                                   JsonObjectRequest facBrandName = new JsonObjectRequest(Request.Method.GET, pathfacility, null, new Response.Listener<JSONObject>() {
                                                                                                       @Override
                                                                                                       public void onResponse(JSONObject response) {
                                                                                                           JSONObject obj = response;
                                                                                                           try {
                                                                                                               int facRepeat = 0;
                                                                                                               ArrayList<String> datacompare = new ArrayList<>();
                                                                                                               JSONArray nameth = obj.getJSONArray("nameTH");
                                                                                                               JSONArray nameen = obj.getJSONArray("nameEN");

                                                                                                               for (int j = 0; j < nameth.length(); j++)
                                                                                                               {
                                                                                                                   datacompare.add(nameth.getString(j));
                                                                                                               }

                                                                                                               for(int y=0;y<datacompare.size();y++)
                                                                                                               {
                                                                                                                   for(int e=0;e<facilities.size();e++)
                                                                                                                   {
                                                                                                                       if(facilities.get(e).getTextTH().equals(datacompare.get(y)))
                                                                                                                       {
                                                                                                                           facRepeat++;
                                                                                                                           if(facRepeat == facilities.size())
                                                                                                                           {
                                                                                                                               Double d1 = new Double(brandModel[c].getDistanceDouble());
                                                                                                                               Double d2 = new Double(distanceDouble);
                                                                                                                               int retval = d1.compareTo(d2);
                                                                                                                               if(distanceDescribe.equals(distanceArray[0]))
                                                                                                                               {
                                                                                                                                   if(retval < 0 || retval == 0)
                                                                                                                                   {
                                                                                                                                       if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                                                       {
                                                                                                                                           brandnamelist.add(brandModel[c]);
                                                                                                                                       }

                                                                                                                                   }

                                                                                                                               }
                                                                                                                               else if(distanceDescribe.equals(distanceArray[1]))
                                                                                                                               {
                                                                                                                                   if(retval < 0 || retval == 0)
                                                                                                                                   {
                                                                                                                                       if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                                                       {
                                                                                                                                           brandnamelist.add(brandModel[c]);
                                                                                                                                       }
                                                                                                                                   }
                                                                                                                               }
                                                                                                                               else if(distanceDescribe.equals(distanceArray[2]))
                                                                                                                               {
                                                                                                                                   if(retval < 0 || retval == 0)
                                                                                                                                   {
                                                                                                                                       if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                                                       {
                                                                                                                                           brandnamelist.add(brandModel[c]);
                                                                                                                                       }
                                                                                                                                   }
                                                                                                                               }
                                                                                                                               else if(distanceDescribe.equals(distanceArray[3]))
                                                                                                                               {
                                                                                                                                   if(retval < 0 || retval == 0)
                                                                                                                                   {
                                                                                                                                       if(minPrice >= minprice && minPrice <= maxprice)
                                                                                                                                       {
                                                                                                                                           brandnamelist.add(brandModel[c]);
                                                                                                                                       }
                                                                                                                                   }
                                                                                                                               }



                                                                                                                           }
                                                                                                                       }
                                                                                                                   }
                                                                                                               }

                                                                                                              count2++;
                                                                                                               if(count2 == datalist.length())
                                                                                                               {
                                                                                                                   oncallback.onSuccessType4(brandnamelist);
                                                                                                               }

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

                                                                                                   requestqueue.add(facBrandName);

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

                                                                                       requestqueue.add(logReq);
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

                                                                           requestqueue.add(usernameAndEmailreq);


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

                                                               requestqueue.add(facilityRequest);


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

                                                   requestqueue.add(minpriceRequest);
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

                                       requestqueue.add(distanceRequest);
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

                           requestqueue.add(positionDormRequest);

                       }
                   }
                   catch(JSONException exception)
                   {
                       exception.printStackTrace();
                   }


               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }
           });
           requestqueue.add(request);
       }
   }
    }


}
