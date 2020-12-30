package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ImportantPlace_BrandName_Style extends AppCompatActivity implements OnMapReadyCallback{

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

    public String facultyOfUser;
    public Faculty facofuser;

    public SupportMapFragment mapfgr;
    public GoogleMap mMap;
    public ArrayList<Places> plist;
    public SharedPreferences sharedPreferences;

    public ListView listview;
    public LatLng mydorm;
    public String dormName;

    public String port = ":8080";
    public String ip = "192.168.43.57";
    public String newServerKey = "AIzaSyAIm2bf5ksXaJTiIVoBHFuwyW4ebWOdLLw";

    public ArrayList<Places> list;

    public ActionBar bar;
    public int langcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_place__brand_name__style);
        setFaculty();
        this.list = new ArrayList<>();
        this.plist = new ArrayList<>();

        this.bar = getSupportActionBar();
        String importantPlace = getResources().getString(R.string.important_place);
        this.bar.setTitle(importantPlace);
        this.bar.setDisplayHomeAsUpEnabled(true);
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        String[] array = getResources().getStringArray(R.array.faculty);

        this.listview = (ListView) findViewById(R.id.listview_places);

        SharedPreferences lang = getSharedPreferences("language", Context.MODE_PRIVATE);
        langcode = lang.getInt("languageCode",0);

        sharedPreferences = getSharedPreferences("file_pref", Context.MODE_PRIVATE);
        String fac = sharedPreferences.getString("faculty","no faculty");
        facultyOfUser = fac;
        //System.out.println("Faculty: "+facultyOfUser);

        SharedPreferences var = getSharedPreferences("selectDorm", MODE_PRIVATE);
        dormName = var.getString("dormname","no value");

        if(facultyOfUser.equals(array[1]))
        {
            plist = scienceAndTechnology.getPlacelist();
        }
        else if(facultyOfUser.equals(array[2]))
        {
            plist = law.getPlacelist();
        }
        else if(facultyOfUser.equals(array[3]))
        {
            plist = politicalScience.getPlacelist();
        }
        else if(facultyOfUser.equals(array[4]))
        {
            plist = account.getPlacelist();
        }
        else if(facultyOfUser.equals(array[5]))
        {
            plist = liberalArt.getPlacelist();
        }
        else if(facultyOfUser.equals(array[6]))
        {
            plist = architect.getPlacelist();
        }
        else if(facultyOfUser.equals(array[7]))
        {
            plist = economy.getPlacelist();
        }
        else if(facultyOfUser.equals(array[8]))
        {
            plist = social.getPlacelist();
        }
        else if(facultyOfUser.equals(array[9]))
        {
            plist = jouranlism.getPlacelist();
        }
        else if(facultyOfUser.equals(array[10]))
        {
            plist = humanism.getPlacelist();
        }
        else if(facultyOfUser.equals(array[11]))
        {
            plist = engineer.getPlacelist();
        }
        else if(facultyOfUser.equals(array[12]))
        {
            plist = medicine.getPlacelist();
        }
        else if(facultyOfUser.equals(array[13]))
        {
            plist = alliedHealth.getPlacelist();
        }
        else if(facultyOfUser.equals(array[14]))
        {
            plist = dentistry.getPlacelist();
        }
        else if(facultyOfUser.equals(array[15]))
        {
            plist = nurse.getPlacelist();
        }
        else if(facultyOfUser.equals(array[16]))
        {
            plist = fineArt.getPlacelist();
        }
        else if(facultyOfUser.equals(array[17]))
        {
            plist = publicHealth.getPlacelist();
        }
        else if(facultyOfUser.equals(array[18]))
        {
            plist = pharmacy.getPlacelist();
        }
        else if(facultyOfUser.equals(array[19]))
        {
            plist = studyAndknowledge.getPlacelist();
        }
        else if(facultyOfUser.equals(array[20]))
        {
            plist = siit.getPlacelist();
        }
        else if(facultyOfUser.equals(array[21]))
        {
            plist = puey.getPlacelist();
        }

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
                    BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_dorm_use);
                    Bitmap b = bitmapdraw.getBitmap();
                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                    MarkerOptions dorm = new MarkerOptions().title(dormName).position(mydorm).icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                    mMap.addMarker(dorm);

                    int numberPlace = plist.size();
                    System.out.println("Size: "+numberPlace);
                    final MarkerOptions[] mk = new MarkerOptions[numberPlace];
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

                                    BitmapDrawable bitmapdrawable2 = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_place_modify);
                                    Bitmap b2 = bitmapdrawable2.getBitmap();
                                    Bitmap smallMarker2 = Bitmap.createScaledBitmap(b2, width, height, false);



                                    p[c].setDistance(distance_text);

                                    if(langcode == 0)
                                    {
                                        mk[c] = new MarkerOptions().title(nameTH).position(new LatLng(lat,lng)).icon(BitmapDescriptorFactory.fromBitmap(smallMarker2)).snippet("ระยะทางจากหอ:"+distance_text+" เวลา:"+duration_text);
                                    }
                                    else
                                    {
                                        mk[c] = new MarkerOptions().title(nameEN).position(new LatLng(lat,lng)).icon(BitmapDescriptorFactory.fromBitmap(smallMarker2)).snippet("from dorm:"+distance_text+" time:"+duration_text);

                                    }

                                    mMap.addMarker(mk[c]);

                                    list.add(p[c]);


                                    ListViewAdapter adapter = new ListViewAdapter(getApplicationContext(),list,langcode);
                                    listview.setAdapter(adapter);


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


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);


        // position of university

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
        scCanteen2 = new Places("ศูนย์อาหาร SC2","SC2 Canteen",14.069697, 100.604672);


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
