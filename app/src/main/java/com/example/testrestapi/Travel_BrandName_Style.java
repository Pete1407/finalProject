package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Travel_BrandName_Style extends AppCompatActivity {
    public String dormName;
    public ActionBar bar;

    public TextView walkingDistance;
    public TextView walkingDuration;

    public TextView drivingDistance;
    public TextView drivingDuration;

    public TextView bikeDistance;
    public TextView bikeDuration;

    public String port = ":8080";
    public String ip = "192.168.43.57";
    public String newServerKey = "AIzaSyAIm2bf5ksXaJTiIVoBHFuwyW4ebWOdLLw";


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
    public ArrayList<Places> plist;
    public Faculty faculty;
    public int langcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel__brand_name__style);
        this.setFaculty();
        SharedPreferences sharedPreferences = getSharedPreferences("selectDorm",MODE_PRIVATE);
        this.dormName = sharedPreferences.getString("dormname","no value");

        this.plist = new ArrayList<>();

        SharedPreferences lang = getSharedPreferences("language", Context.MODE_PRIVATE);
        langcode = lang.getInt("languageCode",0);

        SharedPreferences share = getSharedPreferences("file_pref", Context.MODE_PRIVATE);
        String fac = share.getString("faculty","no faculty");
        facultyOfUser = fac;

        this.walkingDistance = (TextView) findViewById(R.id.driving);
        this.walkingDuration = (TextView) findViewById(R.id.walking_duration);

        this.drivingDistance = (TextView) findViewById(R.id.driving_distance);
        this.drivingDuration = (TextView) findViewById(R.id.driving_duration);

        this.bikeDistance = (TextView) findViewById(R.id.bike_distance);
        this.bikeDuration = (TextView) findViewById(R.id.bike_duration);

        this.bar = getSupportActionBar();
        String traveltitle = getResources().getString(R.string.travelTitle);
        this.bar.setTitle(traveltitle);
        this.bar.setDisplayHomeAsUpEnabled(true);
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        String[] array = getResources().getStringArray(R.array.faculty);

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

        this.getDistanceAndDuration(dormName);
    }

    public void getDistanceAndDuration(String dormName){
        final RequestQueue q = Volley.newRequestQueue(this);
        String pathdata = "http://"+ip+port+"/dorm/getDormByDormName/"+dormName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, pathdata, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    double latitude = response.getDouble("latitude");
                    double longtitude = response.getDouble("longtitude");

                    LatLng mydorm = new LatLng(latitude,longtitude);

                    String urlDirection = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+mydorm.latitude+","+mydorm.longitude+"&destination="+faculty.getLatitude()+","+faculty.getLongtitude()+"&mode=walking&key="+newServerKey;
                    JsonObjectRequest getd = new JsonObjectRequest(Request.Method.GET, urlDirection, null, new Response.Listener<JSONObject>() {
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
                              String distext = getResources().getString(R.string.distance);
                             //walkingDistance.setTypeface(Typeface.DEFAULT_BOLD);
                             walkingDistance.setText(distext+" "+distance_text);
                             //walkingDuration.setTypeface(Typeface.DEFAULT_BOLD);

                             String time = "";
                             if(langcode == 0)
                             {
                                 time = "ใช้เวลา ";
                             }
                             else
                             {
                                 time = "time ";
                             }

                             walkingDuration.setText(time+duration_text);
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

                    String urlDirection2 = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+mydorm.latitude+","+mydorm.longitude+"&destination="+faculty.getLatitude()+","+faculty.getLongtitude()+"&mode=driving&key="+newServerKey;
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

                                //walkingDistance.setTypeface(Typeface.DEFAULT_BOLD);
                                String distext = getResources().getString(R.string.distance);
                                drivingDistance.setText(distext+" "+distance_text);
                                //walkingDuration.setTypeface(Typeface.DEFAULT_BOLD);
                                String time = "";
                                if(langcode == 0)
                                {
                                    time = "ใช้เวลา ";
                                }
                                else
                                {
                                    time = "time ";
                                }

                                drivingDuration.setText( time+duration_text);
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

                    String urlDirection3 = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+mydorm.latitude+","+mydorm.longitude+"&destination="+faculty.getLatitude()+","+faculty.getLongtitude()+"&mode=transit&key="+newServerKey;
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

                                //walkingDistance.setTypeface(Typeface.DEFAULT_BOLD);
                                String distext = getResources().getString(R.string.distance);
                                bikeDistance.setText(distext+" "+distance_text);
                                //walkingDuration.setTypeface(Typeface.DEFAULT_BOLD);
                                String time = "";
                                if(langcode == 0)
                                {
                                    time = "ใช้เวลา ";
                                }
                                else
                                {
                                    time = "time ";
                                }
                                bikeDuration.setText(time+duration_text);
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
