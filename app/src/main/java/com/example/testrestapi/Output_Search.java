package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Output_Search extends AppCompatActivity {

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

    public int count2 = 0;
    public ActionBar bar;
    public int codeStyle;
    public int codeStyleSearch;
    public RecyclerView recycler;
    public String port = ":8080";
    public String ip = "192.168.43.57";
    public String newServerKey = "AIzaSyAX6qj2h0KbaAEUtSp8g-Wt0Vzml68ljA0";

    public ArrayList<Dorm_PricenPromotion_Model> pricelist;
    public ArrayList<Dorm_Information_Model> informationlist;
    public ArrayList<Dorm_Quality_Model> qualitylist;
    public ArrayList<Dorm_BrandName_Model> brandnamelist;

    public String facultyOfUser;
    public Faculty facofuser;
    public String faculty;

    public SharedPreferences selectdorm;
    public SharedPreferences.Editor editor;
    public String confirm;
    public int nub = 0;
    public int user_price = 0;
    public double user_distance = 0;
    public String backsearchpage;
    public int langcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output__search);
        this.setFaculty();

        backsearchpage = getResources().getString(R.string.backtosearchpage);

        pricelist = new ArrayList<>();
        informationlist = new ArrayList<>();
        qualitylist = new ArrayList<>();
        brandnamelist = new ArrayList<>();

        bar = getSupportActionBar();
        String title = getResources().getString(R.string.outputforfinding);
        bar.setTitle(title);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));

        selectdorm = getSharedPreferences("selectDorm",MODE_PRIVATE);
        editor = selectdorm.edit();

        SharedPreferences sharedPreferences = getSharedPreferences("file_pref",MODE_PRIVATE);
        codeStyle = sharedPreferences.getInt("dormStyleCode",4);
        faculty = sharedPreferences.getString("faculty","no faculty");

        confirm = getResources().getString(R.string.gomainpage);

        SharedPreferences lang = getSharedPreferences("language", Context.MODE_PRIVATE);
        langcode = lang.getInt("languageCode",2);

        final String[] array = getResources().getStringArray(R.array.faculty);
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



        if(codeStyle == 3)
        {
            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        }

        this.recycler = (RecyclerView) findViewById(R.id.recyclerview);

        SharedPreferences search = getSharedPreferences("search",MODE_PRIVATE);
        codeStyleSearch = search.getInt("codeStyleSearch",3);

        if(codeStyleSearch == 0)
        {
            String nametext = search.getString("inputfromUser","no value");
            //Toast.makeText(this,codeStyleSearch+" "+nametext,Toast.LENGTH_SHORT).show();
            this.queryForName(nametext,codeStyleSearch);
        }
        else if(codeStyleSearch == 1)
        {
            int pricetext = search.getInt("inputfromUser",0);
            //Toast.makeText(this,codeStyleSearch+" "+pricetext,Toast.LENGTH_SHORT).show();
            this.queryForPrice(pricetext,codeStyleSearch);
        }
        else if(codeStyleSearch == 2)
        {
            double distancefromUser = Double.parseDouble(search.getString("inputfromUser","0"));
            //Toast.makeText(this,codeStyleSearch+" "+distancefromUser,Toast.LENGTH_SHORT).show();
            this.queryForDistance(distancefromUser,codeStyleSearch);
        }
    }

    public void queryForName(final String name,int codeStyleSearch){
       final RequestQueue queue = Volley.newRequestQueue(this);
        if(codeStyle == 0)
        {
           final Dorm_PricenPromotion_Model model = new Dorm_PricenPromotion_Model();
            String queryDorm = "http://"+ip+port+"/DormTypePriceNPromotion/checkDorm/"+name;
            StringRequest stringreq = new StringRequest(Request.Method.GET, queryDorm, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    StringTokenizer t = new StringTokenizer(response,",");
                    String res = t.nextToken();
                    final String nameoutput = t.nextToken();
                    if(res.equals("success"))
                    {
                       String finddorm = "http://"+ip+port+"/DormTypePriceNPromotion/getDormBydormName/"+nameoutput;
                       JsonObjectRequest findname = new JsonObjectRequest(Request.Method.GET, finddorm, null, new Response.Listener<JSONObject>() {
                           @Override
                           public void onResponse(JSONObject response) {
                                JSONObject obj = response;
                            try {
                                final String id = obj.getString("dormID");
                                final String proDetail = obj.getString("promotionDescribe");
                                // System.out.println("Promotion Detail: "+proDetail);
                                int oldprice = obj.getInt("oldPrice");
                                int newprice = obj.getInt("newPrice");

                                model.setDorm_name(id);
                                model.setOldPrice(oldprice);
                                model.setNewPrice(newprice);

                                String getPositionDorm = "http://"+ip+port+"/dorm/getDormByDormName/"+id;
                                JsonObjectRequest positionreq = new JsonObjectRequest(Request.Method.GET, getPositionDorm, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        JSONObject ob = response;
                                        try {
                                            double latitude = ob.getDouble("latitude");
                                            double longtitude = ob.getDouble("longtitude");

                                            String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                            JsonObjectRequest google = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
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

                                                        model.setDistance(distanceText);

                                                        String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+id;
                                                        JsonObjectRequest usernameandemail = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                            @Override
                                                            public void onResponse(JSONObject response) {
                                                                JSONObject dormowner = response;
                                                                try {
                                                                    String username = dormowner.getString("username");
                                                                    String email = dormowner.getString("email");

                                                                    String coverImagePath = "http://"+ip+port+"/CoverImage/getCoverImageByusernameAndEmail/"+username+"/"+email;
                                                                    JsonObjectRequest getcover = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                        @Override
                                                                        public void onResponse(JSONObject response) {
                                                                            JSONObject coverImage = response;
                                                                            try {
                                                                                String coverImagePath = coverImage.getString("coverImage");
                                                                                model.setImage(coverImagePath);

                                                                                String pathGetPriceDorm = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+id;
                                                                                JsonObjectRequest pricenoPromotion = new JsonObjectRequest(Request.Method.GET, pathGetPriceDorm, null, new Response.Listener<JSONObject>() {
                                                                                    @Override
                                                                                    public void onResponse(JSONObject response) {
                                                                                        JSONObject obj = response;
                                                                                        try {
                                                                                            int minPricefromData = obj.getInt("minPrice");
                                                                                            if(!proDetail.equals(""))
                                                                                            {
                                                                                                int statusPromotion = 1;
                                                                                                model.setStatus_promotion(statusPromotion);
                                                                                                model.setFaculty(facofuser);
                                                                                                pricelist.add(model);

                                                                                            }
                                                                                            else
                                                                                            {
                                                                                                int statusPromotion = 0;
                                                                                                model.setStatus_promotion(statusPromotion);
                                                                                                model.setFaculty(facofuser);
                                                                                                model.setOldPrice(minPricefromData);
                                                                                                pricelist.add(model);

                                                                                            }

                                                                                            RecyclerViewAdapter_Type_PriceNPromotion a = new RecyclerViewAdapter_Type_PriceNPromotion(pricelist,getApplicationContext());
                                                                                            recycler.setAdapter(a);
                                                                                            recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                                                                            a.setOnItemClickListener(new OnItemClickListener() {
                                                                                                @Override
                                                                                                public void onItemClick(int position) {
                                                                                                    String dormName = pricelist.get(position).getDorm_name();
                                                                                                    Intent i = new Intent(getApplicationContext(),Detail_Dorm_PriceAndPromotion.class);
                                                                                                    i.putExtra("dormName",dormName);
                                                                                                    editor.putString("dormname",dormName);
                                                                                                    editor.commit();
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
                                                                                queue.add(pricenoPromotion);

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
                                                                    queue.add(getcover);
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
                                                        queue.add(usernameandemail);
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

                                            queue.add(google);
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
                                queue.add(positionreq);

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
                       queue.add(findname);
                    }
                    else
                    {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(Output_Search.this);
                        String title = getResources().getString(R.string.notfound);
                        String subtitle = getResources().getString(R.string.notfoundresult);
                        dialog.setTitle(subtitle);
                        dialog.setIcon(R.drawable.alert);
                        dialog.setMessage(title);
                        dialog.setNegativeButton(backsearchpage, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Output_Search.this,Search_Activity.class);
                                startActivity(intent);
                            }
                        });
                        dialog.show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            queue.add(stringreq);
        }
        else if(codeStyle == 1)
        {
            final Dorm_Information_Model model = new Dorm_Information_Model();
            String pathInformation = "http://"+ip+port+"/DormTypeInformation/checkDorm/"+name;
            StringRequest checkInformation = new StringRequest(Request.Method.GET, pathInformation, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    StringTokenizer t = new StringTokenizer(response,",");
                    String res = t.nextToken();
                    final String nameoutput = t.nextToken();
                    if(res.equals("success"))
                    {
                        String finddorm = "http://"+ip+port+"/DormTypeInformation/getDorm/"+nameoutput;
                        JsonObjectRequest dormrequest = new JsonObjectRequest(Request.Method.GET, finddorm, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONObject obj = response;
                                try
                                {
                                    String nameID = obj.getString("dormID");
                                    String detail = obj.getString("detail");
                                    StringTokenizer tokenizer = new StringTokenizer(detail,",");
                                    String[] hippo = new String[tokenizer.countTokens()];
                                    for(int count2=0;count2<hippo.length;count2++)
                                    {
                                        //System.out.println(tokenizer.nextToken());
                                        hippo[count2] = tokenizer.nextToken();
                                    }

                                    model.setName(nameID);
                                    model.setDetail(hippo);

                                    String pathDorm = "http://"+ip+port+"/dorm/getDormByDormName/"+nameoutput;
                                    JsonObjectRequest dormRequest = new JsonObjectRequest(Request.Method.GET, pathDorm, null, new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            JSONObject obj = response;
                                            try {
                                                String address = obj.getString("address");
                                                double longtitude = obj.getDouble("longtitude");
                                                double latitude = obj.getDouble("latitude");
                                                String addressText = "";
                                                StringTokenizer token = new StringTokenizer(address," ");
                                                while(token.hasMoreTokens())
                                                {
                                                    addressText = addressText+" "+token.nextToken();
                                                }
                                                model.setAddress(addressText);

                                                String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                                JsonObjectRequest distanceReq = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
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
                                                            model.setDistance(distanceText);

                                                            String getInfoDormRequest = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+nameoutput;
                                                            JsonObjectRequest getinfo = new JsonObjectRequest(Request.Method.GET, getInfoDormRequest, null, new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {
                                                                    try {
                                                                        int minPrice = response.getInt("minPrice");
                                                                        model.setPrice(minPrice);

                                                                        String getFacilityInRoom = "http://"+ip+port+"/facilityInRoom/getFacilityInRoom/"+nameoutput;
                                                                        JsonObjectRequest getFac = new JsonObjectRequest(Request.Method.GET, getFacilityInRoom, null, new Response.Listener<JSONObject>() {
                                                                            @Override
                                                                            public void onResponse(JSONObject response) {
                                                                                try {

                                                                                    JSONObject obj = response;
                                                                                    int[] image;
                                                                                    JSONArray array = obj.getJSONArray("image");
                                                                                    image = new int[array.length()];
                                                                                    for (int k = 0; k < image.length; k++)
                                                                                    {
                                                                                        image[k] = array.getInt(k);
                                                                                    }

                                                                                    model.setFacility(image);

                                                                                    String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+nameoutput;
                                                                                    JsonObjectRequest aa = new JsonObjectRequest(getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                                        @Override
                                                                                        public void onResponse(JSONObject response) {
                                                                                            try {
                                                                                                String username = response.getString("username");
                                                                                                String email = response.getString("email");

                                                                                                String getCoverImage = "http://"+ip+port+"/CoverImage/getCoverImageByusernameAndEmail/"+username+"/"+email;
                                                                                                JsonObjectRequest gg = new JsonObjectRequest(Request.Method.GET, getCoverImage, null, new Response.Listener<JSONObject>() {
                                                                                                    @Override
                                                                                                    public void onResponse(JSONObject response) {
                                                                                                        try {
                                                                                                            String pathImage = response.getString("coverImage");
                                                                                                            model.setImage(pathImage);
                                                                                                            model.setFaculty(facofuser);
                                                                                                            informationlist.add(model);

                                                                                                            RecyclerViewAdapter_Type_Information informationAdapter = new RecyclerViewAdapter_Type_Information(informationlist,getApplicationContext(),langcode);
                                                                                                            recycler.setAdapter(informationAdapter);
                                                                                                            recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                                                                                            informationAdapter.setOnItemClickListener(new OnItemClickListener() {
                                                                                                                @Override
                                                                                                                public void onItemClick(int position) {
                                                                                                                    String dormName = informationlist.get(position).getName();
                                                                                                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_Information.class);
                                                                                                                    i.putExtra("dormName",dormName);
                                                                                                                    editor.putString("dormname",dormName);
                                                                                                                    editor.commit();
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
                                                                                                queue.add(gg);


                                                                                            } catch (JSONException e) {
                                                                                                e.printStackTrace();
                                                                                            }

                                                                                        }
                                                                                    }, new Response.ErrorListener() {
                                                                                        @Override
                                                                                        public void onErrorResponse(VolleyError error) {

                                                                                        }
                                                                                    });
                                                                                    queue.add(aa);
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
                                                                        queue.add(getFac);
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
                                                            queue.add(getinfo);

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
                                                queue.add(distanceReq);

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

                                    queue.add(dormRequest);

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
                        queue.add(dormrequest);
                    }
                    else
                    {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(Output_Search.this);
                        String title = getResources().getString(R.string.notfound);
                        String subtitle = getResources().getString(R.string.notfoundresult);
                        dialog.setTitle(subtitle);
                        dialog.setIcon(R.drawable.alert);
                        dialog.setMessage(title);
                        dialog.setNegativeButton(backsearchpage, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Output_Search.this,Search_Activity.class);
                                startActivity(intent);
                            }
                        });
                        dialog.show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(checkInformation);
        }
        else if(codeStyle == 2)
        {
            final Dorm_Quality_Model model = new Dorm_Quality_Model();
            String hippo = name;
            String checkdorm = "http://"+ip+port+"/DormTypeQuality/checkDorm/"+name;
            System.out.println(checkdorm);
            StringRequest stringreq = new StringRequest(Request.Method.GET, checkdorm, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    StringTokenizer t = new StringTokenizer(response,",");
                    String res = t.nextToken();
                    final String nameoutput = t.nextToken();
                    if(res.equals("success"))
                    {
                        String finddorm = "http://"+ip+port+"/DormTypeQuality/getPremiumBydormName/"+nameoutput;
                        JsonObjectRequest findd = new JsonObjectRequest(Request.Method.GET, finddorm, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONObject obj = response;
                                try {
                                    final String id = obj.getString("dormID");
                                    JSONArray image = obj.getJSONArray("imagelist");
                                    int[] imageArray = new int[image.length()];
                                    for(int count2=0;count2<image.length();count2++)
                                    {
                                        imageArray[count2] = image.getInt(count2);
                                        //System.out.println(image.getInt(count2));
                                    }
                                    model.setName(id);
                                    model.setPremieum(imageArray);
                                    String getPositionPath = "http://"+ip+port+"/dorm/getDormByDormName/"+nameoutput;
                                    JsonObjectRequest qq = new JsonObjectRequest(Request.Method.GET, getPositionPath, null, new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            final JSONObject res = response;
                                            //System.out.println(res);
                                            try {
                                                double latitude = res.getDouble("latitude");
                                                double longtitude = res.getDouble("longtitude");

                                                String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                                JsonObjectRequest pikat = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
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
                                                            model.setDistance(distanceText);

                                                            String getPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+nameoutput;
                                                            JsonObjectRequest pricee = new JsonObjectRequest(Request.Method.GET, getPrice, null, new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {
                                                                    JSONObject j = response;
                                                                    try {
                                                                        int minPrice = j.getInt("minPrice");
                                                                        model.setPrice(minPrice);

                                                                        String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+nameoutput;
                                                                        JsonObjectRequest ee = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                            @Override
                                                                            public void onResponse(JSONObject response) {
                                                                                try {
                                                                                    String username = response.getString("username");
                                                                                    String email = response.getString("email");

                                                                                    String coverImagePath = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                                    JsonObjectRequest ttt = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                                        @Override
                                                                                        public void onResponse(JSONObject response) {
                                                                                            try {
                                                                                                String coverImage = response.getString("coverImage");
                                                                                                model.setImage(coverImage);
                                                                                                model.setFaculty(facofuser);
                                                                                                qualitylist.add(model);

                                                                                                RecyclerViewAdapter_Type_Quality adapter = new RecyclerViewAdapter_Type_Quality(qualitylist,getApplicationContext());
                                                                                                recycler.setAdapter(adapter);
                                                                                                recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
                                                                                            } catch (JSONException e) {
                                                                                                e.printStackTrace();
                                                                                            }

                                                                                        }
                                                                                    }, new Response.ErrorListener() {
                                                                                        @Override
                                                                                        public void onErrorResponse(VolleyError error) {

                                                                                        }
                                                                                    });
                                                                                    queue.add(ttt);
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
                                                                        queue.add(ee);


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
                                                            queue.add(pricee);
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
                                                queue.add(pikat);
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
                                    queue.add(qq);



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                        queue.add(findd);
                    }
                    else
                    {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(Output_Search.this);
                        String title = getResources().getString(R.string.notfound);
                        String subtitle = getResources().getString(R.string.notfoundresult);
                        dialog.setTitle(subtitle);
                        dialog.setIcon(R.drawable.alert);
                        dialog.setMessage(title);
                        dialog.setNegativeButton(backsearchpage, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Output_Search.this,Search_Activity.class);
                                startActivity(intent);
                            }
                        });
                        dialog.show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(stringreq);
        }
        else if(codeStyle == 3)
        {
            final Dorm_BrandName_Model model = new Dorm_BrandName_Model();
            String findd = "http://"+ip+port+"/DormTypeBrandName/checkDorm/"+name;
            StringRequest checkdorm = new StringRequest(Request.Method.GET, findd, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    StringTokenizer t = new StringTokenizer(response,",");
                    final String res = t.nextToken();
                    final String nameoutput = t.nextToken();
                    if(res.equals("success"))
                    {
                        String finddorm = "http://"+ip+port+"/DormTypeBrandName/getDorm/"+nameoutput;
                        JsonObjectRequest fd = new JsonObjectRequest(Request.Method.GET, finddorm, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONObject obj = response;
                                try {
                                    final String dormId = obj.getString("dormID");
                                    model.setName(dormId);

                                    String getPosition = "http://"+ip+port+"/dorm/getDormByDormName/"+nameoutput;
                                    JsonObjectRequest ee = new JsonObjectRequest(Request.Method.GET, getPosition, null, new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            JSONObject data = response;
                                            //System.out.println(data);
                                            try {
                                                String address = data.getString("address");
                                                double latitude = data.getDouble("latitude");
                                                double longtitude = data.getDouble("longtitude");
                                                String collecttext = " ";
                                                StringTokenizer token = new StringTokenizer(address, " ");
                                                while (token.hasMoreTokens()) {
                                                    collecttext = collecttext + token.nextToken() + " ";
                                                }
                                                model.setAddress(collecttext);

                                                String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                                JsonObjectRequest rr = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
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

                                                            model.setDistance(distanceText);

                                                            String getMinPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+nameoutput;
                                                            JsonObjectRequest tt = new JsonObjectRequest(Request.Method.GET, getMinPrice, null, new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {
                                                                    try {
                                                                        int minPrice = response.getInt("minPrice");
                                                                        model.setPrice(minPrice);

                                                                        String facilityInDorm = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+nameoutput;
                                                                        JsonObjectRequest uu = new JsonObjectRequest(Request.Method.GET, facilityInDorm, null, new Response.Listener<JSONObject>() {
                                                                            @Override
                                                                            public void onResponse(JSONObject response) {
                                                                                JSONObject obj = response;
                                                                                try {
                                                                                    String[] fac;
                                                                                    JSONArray textTh = obj.getJSONArray("nameTH");
                                                                                    JSONArray array = obj.getJSONArray("image");
                                                                                    fac = new String[array.length()];
                                                                                    for (int j = 0; j < array.length(); j++)
                                                                                    {
                                                                                        fac[j] = textTh.getString(j);
                                                                                        System.out.println(textTh.get(j) + " " + fac[j]);
                                                                                    }

                                                                                    model.setPremieum(fac);
                                                                                    String usernameAndEmailRequest = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+nameoutput;
                                                                                    JsonObjectRequest pp = new JsonObjectRequest(Request.Method.GET, usernameAndEmailRequest, null, new Response.Listener<JSONObject>() {
                                                                                        @Override
                                                                                        public void onResponse(JSONObject response) {
                                                                                            try {
                                                                                                String username = response.getString("username");
                                                                                                String email = response.getString("email");

                                                                                                String logoBrandName = "http://" + ip + port + "/LogoBrandName/getPathLogoBrand/" + username + "/" + email;
                                                                                                JsonObjectRequest qq = new JsonObjectRequest(Request.Method.GET, logoBrandName, null, new Response.Listener<JSONObject>() {
                                                                                                    @Override
                                                                                                    public void onResponse(JSONObject response) {
                                                                                                        try {
                                                                                                            String logo = response.getString("logo");
                                                                                                            model.setLogo(logo);
                                                                                                            model.setFaculty(facofuser);
                                                                                                            brandnamelist.add(model);

                                                                                                            RecyclerViewAdapter_Type_Brandname adapter = new RecyclerViewAdapter_Type_Brandname(brandnamelist,getApplicationContext());
                                                                                                            recycler.setAdapter(adapter);
                                                                                                            recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
                                                                                                        } catch (JSONException e) {
                                                                                                            e.printStackTrace();
                                                                                                        }

                                                                                                    }
                                                                                                }, new Response.ErrorListener() {
                                                                                                    @Override
                                                                                                    public void onErrorResponse(VolleyError error) {

                                                                                                    }
                                                                                                });
                                                                                                queue.add(qq);
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
                                                                                    queue.add(pp);

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
                                                                        queue.add(uu);
                                                                    } catch (JSONException e) {
                                                                        e.printStackTrace();
                                                                    }

                                                                }
                                                            }, new Response.ErrorListener() {
                                                                @Override
                                                                public void onErrorResponse(VolleyError error) {

                                                                }
                                                            });
                                                            queue.add(tt);


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
                                                queue.add(rr);
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
                                    queue.add(ee);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                        queue.add(fd);
                    }
                    else
                    {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(Output_Search.this);
                        String title = getResources().getString(R.string.notfound);
                        String subtitle = getResources().getString(R.string.notfoundresult);
                        dialog.setTitle(subtitle);
                        dialog.setIcon(R.drawable.alert);
                        dialog.setMessage(title);
                        dialog.setNegativeButton(backsearchpage, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Output_Search.this,Search_Activity.class);
                                startActivity(intent);
                            }
                        });
                        dialog.show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(checkdorm);
        }
    }

    public void queryForPrice(final int price,int codeStyleSearch){
       final RequestQueue queue = Volley.newRequestQueue(this);
        if(codeStyle == 0)
        {
            user_price = price;
            setNotification(new Callback() {
                @Override
                public void onSuccess(ArrayList<String> list) {

                }

                @Override
                public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {
                    nub++;
                    if(dorm1.size() > 0)
                    {
                        RecyclerViewAdapter_Type_PriceNPromotion a = new RecyclerViewAdapter_Type_PriceNPromotion(dorm1,getApplicationContext());
                        recycler.setAdapter(a);
                        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        a.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                String dormName = pricelist.get(position).getDorm_name();
                                Intent i = new Intent(getApplicationContext(),Detail_Dorm_PriceAndPromotion.class);
                                i.putExtra("dormName",dormName);
                                editor.putString("dormname",dormName);
                                editor.commit();
                                startActivity(i);
                            }
                        });
                    }
                    else
                    {
                        if(nub == 1)
                        {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(Output_Search.this);
                            String title = getResources().getString(R.string.notfound);
                            String subtitle = getResources().getString(R.string.notfoundresult);
                            dialog.setTitle(subtitle);
                            dialog.setIcon(R.drawable.alert);
                            dialog.setMessage(title);
                            dialog.setNegativeButton(backsearchpage, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Output_Search.this,Search_Activity.class);
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
        else if(codeStyle == 1)
        {
            user_price = price;
            setNotification(new Callback() {
                @Override
                public void onSuccess(ArrayList<String> list) {

                }

                @Override
                public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                }

                @Override
                public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {
                        nub++;
                        if(dorm1.size() > 0)
                        {
                            RecyclerViewAdapter_Type_Information informationAdapter = new RecyclerViewAdapter_Type_Information(dorm1,getApplicationContext(),langcode);
                            recycler.setAdapter(informationAdapter);
                            recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            informationAdapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    String dormName = informationlist.get(position).getName();
                                    Intent i = new Intent(getApplicationContext(), Detail_Dorm_Information.class);
                                    i.putExtra("dormName",dormName);
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
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Output_Search.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(backsearchpage, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Output_Search.this,Search_Activity.class);
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
        else if(codeStyle == 2)
        {
            user_price = price;
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
                        recycler.setAdapter(adapter);
                        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
                            AlertDialog.Builder dialog = new AlertDialog.Builder(Output_Search.this);
                            String title = getResources().getString(R.string.notfound);
                            String subtitle = getResources().getString(R.string.notfoundresult);
                            dialog.setTitle(subtitle);
                            dialog.setIcon(R.drawable.alert);
                            dialog.setMessage(title);
                            dialog.setNegativeButton(backsearchpage, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Output_Search.this,Search_Activity.class);
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
        else if(codeStyle == 3)
        {
            user_price = price;
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
                            recycler.setAdapter(adapter);
                            recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Output_Search.this);
                                String title = getResources().getString(R.string.notfound);
                                String subtitle = getResources().getString(R.string.notfoundresult);
                                dialog.setTitle(subtitle);
                                dialog.setIcon(R.drawable.alert);
                                dialog.setMessage(title);
                                dialog.setNegativeButton(backsearchpage, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Output_Search.this,Search_Activity.class);
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

    public void queryForDistance(final double distanceInput,int codeStyleSearch) {
       final RequestQueue queue = Volley.newRequestQueue(this);
        if (codeStyle == 0)
        {
            user_distance = distanceInput;
            setNotification(new Callback() {
                @Override
                public void onSuccess(ArrayList<String> list) {

                }

                @Override
                public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {
                    nub++;
                    if(dorm1.size() > 0)
                    {
                        RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter forfilter = new RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter(dorm1, getApplicationContext(),langcode);
                        recycler.setAdapter(forfilter);
                        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
                            AlertDialog.Builder dialog = new AlertDialog.Builder(Output_Search.this);
                            String title = getResources().getString(R.string.notfound);
                            String subtitle = getResources().getString(R.string.notfoundresult);
                            dialog.setTitle(title);
                            dialog.setIcon(R.drawable.alert);
                            dialog.setMessage(subtitle);
                            dialog.setNegativeButton(backsearchpage, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Output_Search.this,Search_Activity.class);
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
        else if (codeStyle == 1)
        {
            user_distance = distanceInput;
            setNotification(new Callback() {
                @Override
                public void onSuccess(ArrayList<String> list) {

                }

                @Override
                public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                }

                @Override
                public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {
                    nub++;
                    if(dorm1.size() > 0)
                    {
                        RecyclerViewAdapter_Type_Information forfilter = new RecyclerViewAdapter_Type_Information(dorm1, getApplicationContext(),langcode);
                        recycler.setAdapter(forfilter);
                        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        forfilter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                String dormName = pricelist.get(position).getDorm_name();
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
                            AlertDialog.Builder dialog = new AlertDialog.Builder(Output_Search.this);
                            String title = getResources().getString(R.string.notfound);
                            String subtitle = getResources().getString(R.string.notfoundresult);
                            dialog.setTitle(title);
                            dialog.setIcon(R.drawable.alert);
                            dialog.setMessage(subtitle);
                            dialog.setNegativeButton(backsearchpage, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Output_Search.this,Search_Activity.class);
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
        else if (codeStyle == 2)
        {
            user_distance = distanceInput;
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
                        RecyclerViewAdapter_Type_Quality forfilter = new RecyclerViewAdapter_Type_Quality(dorm1,getApplicationContext());
                        recycler.setAdapter(forfilter);
                        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        forfilter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                String dormName = qualitylist.get(position).getName();
                                Intent i = new Intent(getApplicationContext(), Detail_Dorm_Quality.class);
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
                            AlertDialog.Builder dialog = new AlertDialog.Builder(Output_Search.this);
                        String title = getResources().getString(R.string.notfound);
                        String subtitle = getResources().getString(R.string.notfoundresult);
                        dialog.setTitle(title);
                        dialog.setIcon(R.drawable.alert);
                        dialog.setMessage(subtitle);
                        dialog.setNegativeButton(backsearchpage, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Output_Search.this,Search_Activity.class);
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
        else if (codeStyle == 3)
        {
            //Toast.makeText(this,"Zehahaha",Toast.LENGTH_LONG).show();
            user_distance = distanceInput;
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
                    System.out.println("----  Size of BrandName: "+brandnamelist.size()+"   ---- ");
                    if(dorm1.size() > 0)
                    {
                        RecyclerViewAdapter_Type_Brandname adapter = new RecyclerViewAdapter_Type_Brandname(dorm1,getApplicationContext());
                        recycler.setAdapter(adapter);
                        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
                        System.out.println("Nub : "+nub);
                        if(nub == 1 )
                        {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(Output_Search.this);
                            String title = getResources().getString(R.string.notfound);
                            String subtitle = getResources().getString(R.string.notfoundresult);
                            dialog.setTitle(title);
                            dialog.setIcon(R.drawable.alert);
                            dialog.setMessage(subtitle);
                            dialog.setNegativeButton(backsearchpage, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Output_Search.this,Search_Activity.class);
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
           if(codeStyle == 0)
           {
               if(codeStyleSearch == 1)
               {
                   System.out.println("User Price: "+user_price);
                   String getPath = "http://"+ip+port+"/DormTypePriceNPromotion/getAllDorm";
                   JsonArrayRequest af = new JsonArrayRequest(Request.Method.GET, getPath, null, new Response.Listener<JSONArray>() {
                       @Override
                       public void onResponse(JSONArray response) {
                           final JSONArray data = response;
                           final Dorm_PricenPromotion_Model[] priceModel = new Dorm_PricenPromotion_Model[data.length()];
                           for(int count=0;count<data.length();count++)
                           {
                               final int c = count;
                               System.out.println("C: "+c+"  "+data.length());
                               priceModel[count] = new Dorm_PricenPromotion_Model();
                               JSONObject obj = null;
                               try {
                                   obj = data.getJSONObject(count);
                                   final String id = obj.getString("dormID");
                                   final String proDetail = obj.getString("promotionDescribe");
                                   int oldprice = obj.getInt("oldPrice");
                                   int newprice = obj.getInt("newPrice");
                                   priceModel[count].setDorm_name(id);
                                   priceModel[count].setOldPrice(oldprice);
                                   priceModel[count].setNewPrice(newprice);

                                   String getPositionDorm = "http://"+ip+port+"/dorm/getDormByDormName/"+id;
                                   JsonObjectRequest uu = new JsonObjectRequest(Request.Method.GET, getPositionDorm, null, new Response.Listener<JSONObject>() {
                                       @Override
                                       public void onResponse(JSONObject response) {
                                           JSONObject ob = response;
                                           try {
                                               double latitude = ob.getDouble("latitude");
                                               double longtitude = ob.getDouble("longtitude");

                                               String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                               JsonObjectRequest mm = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
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
                                                           priceModel[c].setDistance(distanceText);

                                                           String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+id;
                                                           JsonObjectRequest mail = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                               @Override
                                                               public void onResponse(JSONObject response) {
                                                                   JSONObject dormowner = response;
                                                                   try {
                                                                       String username = dormowner.getString("username");
                                                                       String email = dormowner.getString("email");
                                                                       //System.out.println(id+"  "+username+"  "+email);

                                                                       String coverImagePath = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                       JsonObjectRequest qq = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                           @Override
                                                                           public void onResponse(JSONObject response) {
                                                                               JSONObject coverImage = response;

                                                                               try {
                                                                                   String coverImagePath = coverImage.getString("coverImage");
                                                                                   //System.out.println(coverImagePath);
                                                                                   priceModel[c].setImage(coverImagePath);
                                                                                   //System.out.println("Promotion Detail: "+proDetail);
                                                                                   String pathGetPriceDorm = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+id;
                                                                                   JsonObjectRequest tt = new JsonObjectRequest(Request.Method.GET, pathGetPriceDorm, null, new Response.Listener<JSONObject>() {
                                                                                       @Override
                                                                                       public void onResponse(JSONObject response) {

                                                                                           JSONObject obj = response;
                                                                                           try
                                                                                           {
                                                                                               int minPricefromData = obj.getInt("minPrice");

                                                                                               if(!proDetail.equals(""))
                                                                                               {
                                                                                                   if(priceModel[c].getNewPrice() == user_price)
                                                                                                   {

                                                                                                       int statusPromotion = 1;
                                                                                                       priceModel[c].setStatus_promotion(statusPromotion);
                                                                                                       priceModel[c].setFaculty(facofuser);
                                                                                                       pricelist.add(priceModel[c]);
                                                                                                   }


                                                                                               }
                                                                                               else
                                                                                               {

                                                                                                   if(minPricefromData == user_price)
                                                                                                   {
                                                                                                       int statusPromotion = 0;
                                                                                                       priceModel[c].setStatus_promotion(statusPromotion);
                                                                                                       priceModel[c].setFaculty(facofuser);
                                                                                                       priceModel[c].setOldPrice(minPricefromData);
                                                                                                       pricelist.add(priceModel[c]);
                                                                                                   }
                                                                                               }

                                                                                               count2++;
                                                                                               if(count2 == data.length())
                                                                                               {
                                                                                                   oncallback.onSuccessType1(pricelist);
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
                                                                                   queue.add(tt);

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
                                                                       queue.add(qq);
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
                                                           queue.add(mail);

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
                                               queue.add(mm);
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
                                   queue.add(uu);
                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }

                           }
                       }
                   }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {

                       }
                   });
                   queue.add(af);
               }
               else if(codeStyleSearch == 2)
               {
                   String pathGetDormInStyle = "http://"+ip+port+"/DormTypePriceNPromotion/getAllDorm";
                   JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, pathGetDormInStyle, null, new Response.Listener<JSONArray>() {
                       @Override
                       public void onResponse(JSONArray response) {
                           try {
                               final JSONArray data = response;
                               final Dorm_PricenPromotion_Model[] priceModel = new Dorm_PricenPromotion_Model[data.length()];
                               for(int count=0;count<data.length();count++)
                               {
                                   final int c = count;
                                   priceModel[count] = new Dorm_PricenPromotion_Model();
                                   JSONObject obj = data.getJSONObject(count);
                                   final String id = obj.getString("dormID");
                                   final String proDetail = obj.getString("promotionDescribe");
                                   // System.out.println("Promotion Detail: "+proDetail);
                                   int oldprice = obj.getInt("oldPrice");
                                   int newprice = obj.getInt("newPrice");

                                   priceModel[count].setDorm_name(id);
                                   priceModel[count].setOldPrice(oldprice);
                                   priceModel[count].setNewPrice(newprice);

                                   String getPositionDorm = "http://"+ip+port+"/dorm/getDormByDormName/"+id;
                                   JsonObjectRequest ee = new JsonObjectRequest(Request.Method.GET, getPositionDorm, null, new Response.Listener<JSONObject>() {
                                       @Override
                                       public void onResponse(JSONObject response) {
                                           JSONObject ob = response;
                                           try {
                                               double latitude = ob.getDouble("latitude");
                                               double longtitude = ob.getDouble("longtitude");
                                               String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                               JsonObjectRequest map = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
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

                                                           priceModel[c].setDistance(disText);
                                                           priceModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                           String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+id;
                                                           JsonObjectRequest yy = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                               @Override
                                                               public void onResponse(JSONObject response) {
                                                                   JSONObject dormowner = response;
                                                                   try {
                                                                       String username = dormowner.getString("username");
                                                                       String email = dormowner.getString("email");

                                                                       String coverImagePath = "http://"+ip+port+"/CoverImage/getCoverImageByusernameAndEmail/"+username+"/"+email;
                                                                       JsonObjectRequest tt = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                           @Override
                                                                           public void onResponse(JSONObject response) {
                                                                               JSONObject coverImage = response;
                                                                               try {
                                                                                   String coverImagePath = coverImage.getString("coverImage");
                                                                                   //System.out.println(coverImagePath);
                                                                                   priceModel[c].setImage(coverImagePath);

                                                                                   String pathGetPriceDorm = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+id;
                                                                                   JsonObjectRequest uu = new JsonObjectRequest(Request.Method.GET, pathGetPriceDorm, null, new Response.Listener<JSONObject>() {
                                                                                       @Override
                                                                                       public void onResponse(JSONObject response) {
                                                                                           JSONObject obj = response;
                                                                                           try {
                                                                                               int minPricefromData = obj.getInt("minPrice");

                                                                                               if(!proDetail.equals(""))
                                                                                               {
                                                                                                   int statusPromotion = 1;
                                                                                                   priceModel[c].setStatus_promotion(statusPromotion);
                                                                                                   priceModel[c].setFaculty(facofuser);
                                                                                                   Double d1 = new Double(priceModel[c].getDistanceDouble());
                                                                                                   Double d2 = new Double(user_distance);
                                                                                                   int distanced1 =(int) Math.floor(d1);
                                                                                                   int distanced2 = (int) Math.floor(d2);
                                                                                                   System.out.println("d1: "+d1+"  d2: "+d2);
                                                                                                   int retval = d1.compareTo(d2);
                                                                                                   if (distanced1 == distanced2)
                                                                                                   {
                                                                                                       pricelist.add(priceModel[c]);
                                                                                                   }
                                                                                               }
                                                                                               else
                                                                                               {
                                                                                                   int statusPromotion = 0;
                                                                                                   priceModel[c].setStatus_promotion(statusPromotion);
                                                                                                   priceModel[c].setFaculty(facofuser);
                                                                                                   priceModel[c].setOldPrice(minPricefromData);
                                                                                                   Double d1 = new Double(priceModel[c].getDistanceDouble());
                                                                                                   Double d2 = new Double(user_distance);
                                                                                                   int distanced1 =(int) Math.floor(d1);
                                                                                                   int distanced2 = (int) Math.floor(d2);

                                                                                                   System.out.println("d1: "+d1+"  d2: "+d2);
                                                                                                   int retval = d1.compareTo(d2);
                                                                                                   if(distanced1 == distanced2)
                                                                                                   {
                                                                                                       pricelist.add(priceModel[c]);
                                                                                                   }


                                                                                               }

                                                                                               count2++;
                                                                                               if(count2 == data.length())
                                                                                               {
                                                                                                   oncallback.onSuccessType1(pricelist);
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
                                                                                   queue.add(uu);

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
                                                                       queue.add(tt);

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

                                                           queue.add(yy);
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
                                               queue.add(map);
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
                                   queue.add(ee);
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
                   queue.add(req);
               }
           }
           else if(codeStyle == 1)
           {
               if(codeStyleSearch == 1)
               {
                   this.informationlist = new ArrayList<>();
                   String getDorm_Infotmation = "http://"+ip+port+"/DormTypeInformation/getAllDorm";
                   JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, getDorm_Infotmation, null, new Response.Listener<JSONArray>() {
                       @Override
                       public void onResponse(JSONArray response) {
                           final JSONArray res = response;
                           try {
                               final Dorm_Information_Model[] infoModel = new Dorm_Information_Model[res.length()];
                               for(int count=0;count<res.length();count++)
                               {
                                   final int c = count;
                                   infoModel[count] = new Dorm_Information_Model();
                                   JSONObject ob = res.getJSONObject(count);
                                   final String id = ob.getString("dormID");
                                   String detail = ob.getString("detail");
                                   StringTokenizer tokenizer = new StringTokenizer(detail,",");
                                   String[] hippo = new String[tokenizer.countTokens()];

                                   for(int count2=0;count2<hippo.length;count2++)
                                   {
                                       //System.out.println(tokenizer.nextToken());
                                       hippo[count2] = tokenizer.nextToken();
                                   }

                                   infoModel[count].setName(id);
                                   infoModel[count].setDetail(hippo);

                                   String pathDorm = "http://"+ip+port+"/dorm/getDormByDormName/"+id;
                                   JsonObjectRequest ww = new JsonObjectRequest(Request.Method.GET, pathDorm, null, new Response.Listener<JSONObject>() {
                                       @Override
                                       public void onResponse(JSONObject response) {
                                           JSONObject o = response;
                                           String addressText = "";
                                           try
                                           {
                                               String address = o.getString("address");
                                               double longtitude = o.getDouble("longtitude");
                                               double latitude = o.getDouble("latitude");
                                               StringTokenizer token = new StringTokenizer(address, " ");
                                               while (token.hasMoreTokens()) {
                                                   addressText = addressText + " " + token.nextToken();
                                               }
                                               System.out.println(id + "  " + addressText + "  " + latitude + "  " + longtitude);
                                               infoModel[c].setAddress(addressText);
                                               addressText = "";

                                               String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                               JsonObjectRequest er = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
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
                                                           System.out.println(id + "  " + distanceText);
                                                           infoModel[c].setDistance(distanceText);

                                                           String getInfoDormRequest = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+id;
                                                           JsonObjectRequest io = new JsonObjectRequest(Request.Method.GET, getInfoDormRequest, null, new Response.Listener<JSONObject>() {
                                                               @Override
                                                               public void onResponse(JSONObject response) {
                                                                   try {
                                                                       final int minPrice = response.getInt("minPrice");
                                                                       // System.out.println(minPrice);
                                                                       infoModel[c].setPrice(minPrice);

                                                                       String getFacilityInRoom = "http://"+ip+port+"/facilityInRoom/getFacilityInRoom/"+id;
                                                                       JsonObjectRequest rr = new JsonObjectRequest(Request.Method.GET, getFacilityInRoom, null, new Response.Listener<JSONObject>() {
                                                                           @Override
                                                                           public void onResponse(JSONObject response) {
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
                                                                                   JsonObjectRequest ed = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                                       @Override
                                                                                       public void onResponse(JSONObject response) {
                                                                                           try {
                                                                                               String username = response.getString("username");
                                                                                               String email = response.getString("email");
                                                                                               System.out.println(username + "   " + email);

                                                                                               String getCoverImage = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                                               JsonObjectRequest photo = new JsonObjectRequest(Request.Method.GET, getCoverImage, null, new Response.Listener<JSONObject>() {
                                                                                                   @Override
                                                                                                   public void onResponse(JSONObject response) {
                                                                                                       try {
                                                                                                           String pathImage = response.getString("coverImage");
                                                                                                           System.out.println(pathImage);
                                                                                                           infoModel[c].setImage(pathImage);

                                                                                                           infoModel[c].setFaculty(facofuser);

                                                                                                           if(minPrice == user_price)
                                                                                                           {
                                                                                                               informationlist.add(infoModel[c]);
                                                                                                           }


                                                                                                           count2++;
                                                                                                           if(count2 == res.length())
                                                                                                           {
                                                                                                               oncallback.onSuccessType2(informationlist);
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
                                                                                               queue.add(photo);
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
                                                                                   queue.add(ed);
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
                                                                       queue.add(rr);

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
                                                           queue.add(io);

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
                                               queue.add(er);

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
                                   queue.add(ww);
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

                   queue.add(req);
               }
               else if(codeStyleSearch == 2)
               {
                   String getDorm_Infotmation = "http://"+ip+port+"/DormTypeInformation/getAllDorm";
                   JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, getDorm_Infotmation, null, new Response.Listener<JSONArray>() {
                       @Override
                       public void onResponse(JSONArray response) {
                           final JSONArray res = response;
                           try {
                               final  Dorm_Information_Model[] infoModel = new Dorm_Information_Model[res.length()];

                               for (int count = 0; count < res.length(); count++)
                               {
                                   final int c = count;
                                   infoModel[count] = new Dorm_Information_Model();
                                   JSONObject ob = res.getJSONObject(count);
                                   final String id = ob.getString("dormID");
                                   String detail = ob.getString("detail");
                                   StringTokenizer tokenizer = new StringTokenizer(detail,",");
                                   String[] hippo = new String[tokenizer.countTokens()];
                                   System.out.println("dorm name: "+id);
                                   for(int count2=0;count2<hippo.length;count2++)
                                   {
                                       //System.out.println(tokenizer.nextToken());
                                       hippo[count2] = tokenizer.nextToken();
                                   }

                                   infoModel[count].setName(id);
                                   infoModel[count].setDetail(hippo);

                                   String pathDorm = "http://"+ip+port+"/dorm/getDormByDormName/"+id;
                                   JsonObjectRequest q = new JsonObjectRequest(Request.Method.GET, pathDorm, null, new Response.Listener<JSONObject>() {
                                       @Override
                                       public void onResponse(JSONObject response) {
                                           JSONObject o = response;
                                           //System.out.println("Response: "+o);
                                           try {
                                               String addressText = "";
                                               String address = o.getString("address");
                                               double longtitude = o.getDouble("longtitude");
                                               double latitude = o.getDouble("latitude");
                                               StringTokenizer token = new StringTokenizer(address, " ");
                                               while (token.hasMoreTokens()) {
                                                   addressText = addressText + " " + token.nextToken();
                                               }
                                               System.out.println(id + "  " + addressText + "  " + latitude + "  " + longtitude);
                                               infoModel[c].setAddress(addressText);
                                               addressText = "";

                                               String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                               JsonObjectRequest tt = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
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
                                                           String disText = "";
                                                           String distanceInteger = "";
                                                           String unit = "";
                                                           StringTokenizer token = new StringTokenizer(distanceText, " ");
                                                           for (int i = 0; i < token.countTokens(); i++) {
                                                               disText = token.nextToken();
                                                               unit = token.nextToken();
                                                           }

                                                           System.out.println(id + "  " + distanceText);
                                                           infoModel[c].setDistance(distanceText);
                                                           infoModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                           String getInfoDormRequest = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+id;
                                                           JsonObjectRequest zz = new JsonObjectRequest(Request.Method.GET, getInfoDormRequest, null, new Response.Listener<JSONObject>() {
                                                               @Override
                                                               public void onResponse(JSONObject response) {
                                                                   try {
                                                                       int minPrice = response.getInt("minPrice");
                                                                       // System.out.println(minPrice);
                                                                       infoModel[c].setPrice(minPrice);

                                                                       String getFacilityInRoom = "http://" + ip + port + "/facilityInRoom/getFacilityInRoom/" + id;
                                                                       JsonObjectRequest ff = new JsonObjectRequest(Request.Method.GET, getFacilityInRoom, null, new Response.Listener<JSONObject>() {
                                                                           @Override
                                                                           public void onResponse(JSONObject response) {
                                                                               try {

                                                                                   JSONObject obj = response;
                                                                                   int[] image;
                                                                                   JSONArray array = obj.getJSONArray("image");
                                                                                   image = new int[array.length()];
                                                                                   for (int k = 0; k < image.length; k++) {
                                                                                       image[k] = array.getInt(k);
                                                                                   }

                                                                                   infoModel[c].setFacility(image);
                                                                                   String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+id;
                                                                                   JsonObjectRequest ll = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                                       @Override
                                                                                       public void onResponse(JSONObject response) {
                                                                                           try {
                                                                                               String username = response.getString("username");
                                                                                               String email = response.getString("email");
                                                                                               System.out.println(username + "   " + email);

                                                                                               String getCoverImage = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                                               JsonObjectRequest jj = new JsonObjectRequest(Request.Method.GET, getCoverImage, null, new Response.Listener<JSONObject>() {
                                                                                                   @Override
                                                                                                   public void onResponse(JSONObject response) {
                                                                                                       try {
                                                                                                           String pathImage = response.getString("coverImage");
                                                                                                           System.out.println(pathImage);
                                                                                                           infoModel[c].setImage(pathImage);

                                                                                                           infoModel[c].setFaculty(facofuser);

                                                                                                           Double d1 = new Double(infoModel[c].getDistanceDouble());
                                                                                                           Double d2 = new Double(user_distance);
                                                                                                           int distanceofd1 = (int) Math.floor(d1);
                                                                                                           int distanceofd2 = (int) Math.floor(d2);
                                                                                                           System.out.println(d1+"  "+d2);
                                                                                                           if(distanceofd1 == distanceofd2)
                                                                                                           {
                                                                                                               informationlist.add(infoModel[c]);
                                                                                                           }
                                                                                                           //System.out.println("Distance1 : "+d1.intValue()+"   Distance2 : "+d2.intValue());
                                                                                                           //int retval = d1.compareTo(d2);
                                                                                                           //if (retval == 0)
                                                                                                           //{
                                                                                                           //    informationlist.add(infoModel[c]);
                                                                                                           //}

                                                                                                           count2++;
                                                                                                           if(count2 == res.length())
                                                                                                           {
                                                                                                               oncallback.onSuccessType2(informationlist);
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
                                                                                               queue.add(jj);
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
                                                                                   queue.add(ll);
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
                                                                       queue.add(ff);
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
                                                           queue.add(zz);

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
                                               queue.add(tt);
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
                                   queue.add(q);
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
                   queue.add(request);
               }
           }
           else if(codeStyle == 2)
           {
               if(codeStyleSearch == 1)
               {
                   qualitylist = new ArrayList<>();
                   String getAllDorm = "http://"+ip+port+"/DormTypeQuality/getAllDorm";
                   JsonArrayRequest we = new JsonArrayRequest(Request.Method.GET, getAllDorm, null, new Response.Listener<JSONArray>() {
                       @Override
                       public void onResponse(JSONArray response) {
                           final JSONArray array = response;
                           final Dorm_Quality_Model[] quaModel;
                           try {
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
                                   JsonObjectRequest aa = new JsonObjectRequest(Request.Method.GET, getPositionPath, null, new Response.Listener<JSONObject>() {
                                       @Override
                                       public void onResponse(JSONObject response) {
                                           final JSONObject res = response;
                                           //System.out.println(res);
                                           try {
                                               double latitude = res.getDouble("latitude");
                                               double longtitude = res.getDouble("longtitude");
                                               String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                               JsonObjectRequest wa = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                                   @Override
                                                   public void onResponse(JSONObject response) {
                                                       JSONObject ob = response;
                                                       System.out.println(ob);
                                                       try {
                                                           JSONArray route_title = ob.getJSONArray("routes");
                                                           JSONObject routes = route_title.getJSONObject(0);
                                                           JSONArray leg_title = routes.getJSONArray("legs");
                                                           JSONObject legs = leg_title.getJSONObject(0);
                                                           JSONObject distance = legs.getJSONObject("distance");
                                                           String distanceText = distance.getString("text");
                                                           System.out.println(id + "  " + distanceText);

                                                           quaModel[c].setDistance(distanceText);

                                                           String getPrice = "http://" + ip + port + "/InfoDorm/getInfoDormByDormName/" + id;
                                                           JsonObjectRequest er = new JsonObjectRequest(Request.Method.GET, getPrice, null, new Response.Listener<JSONObject>() {
                                                               @Override
                                                               public void onResponse(JSONObject response) {
                                                                   JSONObject j = response;
                                                                   try {
                                                                       final int minPrice = j.getInt("minPrice");
                                                                       System.out.println(minPrice);
                                                                       quaModel[c].setPrice(minPrice);

                                                                       String getUsernameAndEmail = "http://" + ip + port + "/dormOwner/getInfoDormOwnerByDormName/" + id;
                                                                       JsonObjectRequest ett = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                           @Override
                                                                           public void onResponse(JSONObject response) {
                                                                               try {
                                                                                   String username = response.getString("username");
                                                                                   String email = response.getString("email");

                                                                                   String coverImagePath = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                                   JsonObjectRequest tt = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                                       @Override
                                                                                       public void onResponse(JSONObject response) {
                                                                                           try {
                                                                                               String coverImage = response.getString("coverImage");
                                                                                               System.out.println("Image: " + coverImage);
                                                                                               quaModel[c].setImage(coverImage);
                                                                                               quaModel[c].setFaculty(facofuser);
                                                                                               if(minPrice == user_price)
                                                                                               {
                                                                                                   qualitylist.add(quaModel[c]);
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
                                                                                   queue.add(tt);
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
                                                                       queue.add(ett);
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
                                                           queue.add(er);
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
                                               queue.add(wa);
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
                                   queue.add(aa);
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
                   queue.add(we);
               }
               else if(codeStyleSearch == 2)
               {
                   final String getAllDorm = "http://"+ip+port+"/DormTypeQuality/getAllDorm";
                   JsonArrayRequest ar = new JsonArrayRequest(Request.Method.GET, getAllDorm, null, new Response.Listener<JSONArray>() {
                       @Override
                       public void onResponse(JSONArray response) {
                           JSONArray array = response;
                           final Dorm_Quality_Model[] quaModel = new Dorm_Quality_Model[array.length()];

                           try {

                               for (int count = 0; count < array.length(); count++) {
                                   final int c = count;
                                   quaModel[count] = new Dorm_Quality_Model();
                                   JSONObject obj = array.getJSONObject(count);
                                   final String id = obj.getString("dormID");
                                   JSONArray image = obj.getJSONArray("imagelist");
                                   int[] imageArray = new int[image.length()];
                                   for (int count2 = 0; count2 < image.length(); count2++) {
                                       imageArray[count2] = image.getInt(count2);
                                       //System.out.println(image.getInt(count2));
                                   }
                                   quaModel[count].setName(id);
                                   quaModel[count].setPremieum(imageArray);

                                   String getPositionPath = "http://"+ip+port+"/dorm/getDormByDormName/"+id;
                                   JsonObjectRequest jsonrequest = new JsonObjectRequest(Request.Method.GET, getPositionPath, null, new Response.Listener<JSONObject>() {
                                       @Override
                                       public void onResponse(JSONObject response) {
                                           final JSONObject res = response;
                                           //System.out.println(res);
                                           try {
                                               double latitude = res.getDouble("latitude");
                                               double longtitude = res.getDouble("longtitude");
                                               String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                               JsonObjectRequest jsonrequest2 = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
                                                   @Override
                                                   public void onResponse(JSONObject response) {
                                                       JSONObject ob = response;
                                                       System.out.println(ob);
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
                                                           quaModel[c].setDistance(distanceText);
                                                           quaModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                           String getPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+id;
                                                           JsonObjectRequest jsonrequest3 = new JsonObjectRequest(Request.Method.GET, getPrice, null, new Response.Listener<JSONObject>() {
                                                               @Override
                                                               public void onResponse(JSONObject response) {
                                                                   JSONObject j = response;
                                                                   try {
                                                                       int minPrice = j.getInt("minPrice");
                                                                       System.out.println(minPrice);
                                                                       quaModel[c].setPrice(minPrice);

                                                                       String getUsernameAndEmail = "http://" + ip + port + "/dormOwner/getInfoDormOwnerByDormName/" + id;
                                                                       JsonObjectRequest jsonrequest4 = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                           @Override
                                                                           public void onResponse(JSONObject response) {
                                                                               try {
                                                                                   String username = response.getString("username");
                                                                                   String email = response.getString("email");

                                                                                   String coverImagePath = "http://" + ip + port + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                                                   JsonObjectRequest jsonrequest4 = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                                                       @Override
                                                                                       public void onResponse(JSONObject response) {
                                                                                           try {
                                                                                               String coverImage = response.getString("coverImage");
                                                                                               System.out.println("Image: " + coverImage);
                                                                                               quaModel[c].setImage(coverImage);
                                                                                               quaModel[c].setFaculty(facofuser);
                                                                                               Double d1 = new Double(user_distance);
                                                                                               Double d2 = new Double(quaModel[c].getDistanceDouble());
                                                                                               int distanced1 = (int) Math.floor(d1);
                                                                                               int distanced2 = (int) Math.floor(d2);
                                                                                               int retval = d1.compareTo(d2);
                                                                                               if(distanced1 == distanced2)
                                                                                               {
                                                                                                   qualitylist.add(quaModel[c]);
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
                                                                                   queue.add(jsonrequest4);

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
                                                                       queue.add(jsonrequest4);
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
                                                           queue.add(jsonrequest3);


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
                                               queue.add(jsonrequest2);
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
                                   queue.add(jsonrequest);
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
                   queue.add(ar);
               }
           }
           else if(codeStyle == 3)
           {
               if(codeStyleSearch == 1)
               {
                   brandnamelist = new ArrayList<>();
                   String getAllDormBrandName = "http://"+ip+port+"/DormTypeBrandName/getAllDorm";
                   JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, getAllDormBrandName, null, new Response.Listener<JSONArray>() {
                       @Override
                       public void onResponse(JSONArray response) {
                           final JSONArray datalist = response;
                           final Dorm_BrandName_Model[] brandModel;
                           try {
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
                                   JsonObjectRequest rr = new JsonObjectRequest(Request.Method.GET, getPosition, null, new Response.Listener<JSONObject>() {
                                       @Override
                                       public void onResponse(JSONObject response) {
                                           JSONObject data = response;
                                           try {
                                               String address = data.getString("address");
                                               //System.out.println("Address: "+address);
                                               double latitude = data.getDouble("latitude");
                                               double longtitude = data.getDouble("longtitude");
                                               String collecttext = " ";
                                               StringTokenizer token = new StringTokenizer(address, " ");
                                               while (token.hasMoreTokens()) {
                                                   collecttext = collecttext + token.nextToken() + " ";
                                               }
                                               //System.out.println(collecttext);
                                               brandModel[c].setAddress(collecttext);

                                               String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                               JsonObjectRequest ii = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
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
                                                           //System.out.println(dormId + "  " + distanceText);
                                                           brandModel[c].setDistance(distanceText);

                                                           String getMinPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dormId;
                                                           JsonObjectRequest oo = new JsonObjectRequest(Request.Method.GET, getMinPrice, null, new Response.Listener<JSONObject>() {
                                                               @Override
                                                               public void onResponse(JSONObject response) {
                                                                   try {
                                                                       final int minPrice = response.getInt("minPrice");
                                                                       System.out.println("Price: " + minPrice);
                                                                       brandModel[c].setPrice(minPrice);

                                                                       String facilityInDorm = "http://" + ip + port + "/facilityInDorm/getFacilityInDorm/" + dormId;
                                                                       JsonObjectRequest fg = new JsonObjectRequest(Request.Method.GET, facilityInDorm, null, new Response.Listener<JSONObject>() {
                                                                           @Override
                                                                           public void onResponse(JSONObject response) {
                                                                               JSONObject obj = response;
                                                                               try {
                                                                                   String[] fac;
                                                                                   JSONArray textTh = obj.getJSONArray("nameTH");
                                                                                   JSONArray array = obj.getJSONArray("image");
                                                                                   fac = new String[array.length()];
                                                                                   for (int j = 0; j < array.length(); j++) {
                                                                                       fac[j] = textTh.getString(j);
                                                                                       System.out.println(textTh.get(j) + " " + fac[j]);
                                                                                   }

                                                                                   brandModel[c].setPremieum(fac);

                                                                                   String usernameAndEmailRequest = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormId;
                                                                                   JsonObjectRequest ff = new JsonObjectRequest(Request.Method.GET, usernameAndEmailRequest, null, new Response.Listener<JSONObject>() {
                                                                                       @Override
                                                                                       public void onResponse(JSONObject response) {
                                                                                           try {
                                                                                               String username = response.getString("username");
                                                                                               String email = response.getString("email");

                                                                                               String logoBrandName = "http://" + ip + port + "/LogoBrandName/getPathLogoBrand/" + username + "/" + email;
                                                                                               JsonObjectRequest ty = new JsonObjectRequest(Request.Method.GET, logoBrandName, null, new Response.Listener<JSONObject>() {
                                                                                                   @Override
                                                                                                   public void onResponse(JSONObject response) {
                                                                                                       try {
                                                                                                           String logo = response.getString("logo");
                                                                                                           brandModel[c].setLogo(logo);
                                                                                                           brandModel[c].setFaculty(facofuser);

                                                                                                           if(minPrice == user_price)
                                                                                                           {
                                                                                                               brandnamelist.add(brandModel[c]);
                                                                                                           }

                                                                                                           count2++;
                                                                                                           if(count2 == datalist.length())
                                                                                                           {
                                                                                                               oncallback.onSuccessType4(brandnamelist);
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
                                                                                               queue.add(ty);
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
                                                                                   queue.add(ff);


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
                                                                       queue.add(fg);
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
                                                           queue.add(oo);
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
                                               queue.add(ii);
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
                                   queue.add(rr);
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
                   queue.add(req);
               }
               else if(codeStyleSearch == 2)
               {
                   brandnamelist = new ArrayList<>();
                   String getAllDormBrandName = "http://"+ip+port+"/DormTypeBrandName/getAllDorm";
                   JsonArrayRequest jsonrequest1 = new JsonArrayRequest(Request.Method.GET, getAllDormBrandName, null, new Response.Listener<JSONArray>() {
                       @Override
                       public void onResponse(JSONArray response) {
                           try {
                               JSONArray array = response;
                               final Dorm_BrandName_Model[] brandModel = new Dorm_BrandName_Model[array.length()];
                               for(int count=0;count<array.length();count++)
                               {
                                   final int c = count;
                                   brandModel[c] = new Dorm_BrandName_Model();
                                   JSONObject obj = array.getJSONObject(count);
                                   final String dormId = obj.getString("dormID");
                                   //System.out.println("DORM NAME: "+dormId);
                                   brandModel[count].setName(dormId);

                                   String getPosition = "http://"+ip+port+"/dorm/getDormByDormName/"+dormId;
                                   JsonObjectRequest jsonrequest2 = new JsonObjectRequest(Request.Method.GET, getPosition, null, new Response.Listener<JSONObject>() {
                                       @Override
                                       public void onResponse(JSONObject response) {
                                           JSONObject data = response;
                                           //System.out.println(data);
                                           try {
                                               String address = data.getString("address");
                                               //System.out.println("Address: "+address);
                                               double latitude = data.getDouble("latitude");
                                               double longtitude = data.getDouble("longtitude");
                                               String collecttext = " ";
                                               StringTokenizer token = new StringTokenizer(address, " ");
                                               while (token.hasMoreTokens()) {
                                                   collecttext = collecttext + token.nextToken() + " ";
                                               }
                                               //System.out.println(collecttext);
                                               brandModel[c].setAddress(collecttext);

                                               String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                               JsonObjectRequest jsonrequest3 = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
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
                                                           brandModel[c].setDistance(distanceText);
                                                           brandModel[c].setDistanceDouble(Double.parseDouble(disText));

                                                           String getMinPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dormId;
                                                           JsonObjectRequest jsonrequest4 = new JsonObjectRequest(Request.Method.GET, getMinPrice, null, new Response.Listener<JSONObject>() {
                                                               @Override
                                                               public void onResponse(JSONObject response) {
                                                                   try {
                                                                       int minPrice = response.getInt("minPrice");
                                                                       //System.out.println("Price: " + minPrice);
                                                                       brandModel[c].setPrice(minPrice);

                                                                       String facilityInDorm = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+dormId;
                                                                       JsonObjectRequest jsonrequest5 = new JsonObjectRequest(Request.Method.GET, facilityInDorm, null, new Response.Listener<JSONObject>() {
                                                                           @Override
                                                                           public void onResponse(JSONObject response) {
                                                                               JSONObject obj = response;
                                                                               try {
                                                                                   String[] fac;
                                                                                   JSONArray textTh = obj.getJSONArray("nameTH");
                                                                                   JSONArray arrayy = obj.getJSONArray("image");
                                                                                   fac = new String[arrayy.length()];
                                                                                   for (int j = 0; j < arrayy.length(); j++) {
                                                                                       fac[j] = textTh.getString(j);
                                                                                       //System.out.println(textTh.get(j) + " " + fac[j]);
                                                                                   }

                                                                                   brandModel[c].setPremieum(fac);

                                                                                   String usernameAndEmailRequest = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormId;
                                                                                   JsonObjectRequest jsonrequest6 = new JsonObjectRequest(Request.Method.GET, usernameAndEmailRequest, null, new Response.Listener<JSONObject>() {
                                                                                       @Override
                                                                                       public void onResponse(JSONObject response) {
                                                                                           try {
                                                                                               String username = response.getString("username");
                                                                                               String email = response.getString("email");

                                                                                               String logoBrandName = "http://" + ip + port + "/LogoBrandName/getPathLogoBrand/" + username + "/" + email;
                                                                                               JsonObjectRequest jsonrequest7 = new JsonObjectRequest(Request.Method.GET, logoBrandName, null, new Response.Listener<JSONObject>() {
                                                                                                   @Override
                                                                                                   public void onResponse(JSONObject response) {
                                                                                                       try {
                                                                                                           String logo = response.getString("logo");
                                                                                                           brandModel[c].setLogo(logo);
                                                                                                           brandModel[c].setFaculty(facofuser);

                                                                                                           Double d1 = new Double(user_distance);
                                                                                                           Double d2 = new Double(brandModel[c].getDistanceDouble());
                                                                                                           int distanced1 = (int) Math.floor(d1);
                                                                                                           int distanced2 = (int) Math.floor(d2);
                                                                                                           System.out.println("distance1: "+distanced1+"    distance2: "+distanced2);
                                                                                                           int retval = d1.compareTo(d2);
                                                                                                           if(distanced1 == distanced2)
                                                                                                           {
                                                                                                               brandnamelist.add(brandModel[c]);
                                                                                                           }
                                                                                                           System.out.println("Size list: "+brandnamelist.size());
                                                                                                           count2++;
                                                                                                           if(count2 == array.length())
                                                                                                           {
                                                                                                               System.out.println("Size of brandname: "+brandnamelist.size());
                                                                                                               oncallback.onSuccessType4(brandnamelist);

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
                                                                                               queue.add(jsonrequest7);
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
                                                                                   queue.add(jsonrequest6);
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
                                                                       queue.add(jsonrequest5);
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
                                                           queue.add(jsonrequest4);
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
                                               queue.add(jsonrequest3);
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
                                   queue.add(jsonrequest2);
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
                   queue.add(jsonrequest1);
               }
           }
        }


    }

