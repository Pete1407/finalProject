package com.example.testrestapi.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.testrestapi.Detail_Dorm_BrandName;
import com.example.testrestapi.Detail_Dorm_Information;
import com.example.testrestapi.Detail_Dorm_PriceAndPromotion;
import com.example.testrestapi.Detail_Dorm_Quality;
import com.example.testrestapi.Dorm_BrandName_Model;
import com.example.testrestapi.Dorm_Information_Model;
import com.example.testrestapi.Dorm_PricenPromotion_Model;
import com.example.testrestapi.Dorm_Quality_Model;
import com.example.testrestapi.Faculty;
import com.example.testrestapi.OnItemClickListener;
import com.example.testrestapi.Places;
import com.example.testrestapi.R;
import com.example.testrestapi.RecyclerViewAdapter_Type_Brandname;
import com.example.testrestapi.RecyclerViewAdapter_Type_Information;
import com.example.testrestapi.RecyclerViewAdapter_Type_PriceNPromotion;
import com.example.testrestapi.RecyclerViewAdapter_Type_Quality;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.StringTokenizer;

public class ListFragment extends Fragment {

    public RecyclerView recyclerview;
    public String faculty;  // data from MyAdapter Class
    public String dormStyle;  // data from MyAdapter Class
    public int codeStyle;   // data from MyAdapter Class

    public ArrayList<String> titlelist;
    public ArrayList<String> distanceList;
    public ArrayList<Dorm_PricenPromotion_Model> pricelist;
    public ArrayList<Dorm_Information_Model> informationlist;
    public ArrayList<Dorm_Quality_Model> qualitylist;
    public ArrayList<Dorm_BrandName_Model> brandnamelist;

    public Dorm_PricenPromotion_Model[] priceModel;
    public Dorm_Information_Model[] infoModel;
    public Dorm_Quality_Model[] quaModel;
    public Dorm_BrandName_Model[] brandModel;

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


    public int image;
    public int price;
    public int a;

    public String port = ":8080";
    public String ip = "192.168.43.57";

    public String facultyOfUser;
    public Faculty facofuser;

    public String newServerKey = "AIzaSyAX6qj2h0KbaAEUtSp8g-Wt0Vzml68ljA0";
    public String addressText = "";

    ArrayList<String> favlist;
    ArrayList<String> dormlist;

    public String username;
    public int numberof = 0;

    public static ListFragment newInstance(){
        ListFragment fgr = new ListFragment();
        return fgr;
    }

    public void checkFavorite(final String username, final int codeStyle){
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
                            favlist.add(array.getString(count));
                        }

                        for(int count2=0;count2<favlist.size();count2++)
                        {
                            System.out.println("หอพักที่ชื่นชอบ: "+favlist.get(count2));
                        }
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
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setFaculty();
        final String[] array = getResources().getStringArray(R.array.faculty);
        pricelist = new ArrayList<>();
        qualitylist = new ArrayList<>();
        titlelist = new ArrayList<>();
        distanceList = new ArrayList<>();
        favlist = new ArrayList<>();
        dormlist = new ArrayList<>();

        SharedPreferences getusername = getActivity().getSharedPreferences("file_pref",Context.MODE_PRIVATE);
        username = getusername.getString("username","no value username");
        codeStyle = getusername.getInt("dormStyleCode",4);

        checkFavorite(username,codeStyle);

        View v = inflater.inflate(R.layout.activity_list,container,false);
        this.recyclerview = v.findViewById(R.id.recyclerView);
        this.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        this.recyclerview.setFitsSystemWindows(true);


        faculty = getArguments().getString("faculty");
        dormStyle = getArguments().getString("dormStyle");
        codeStyle = getArguments().getInt("codeStyle");

        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("selectDorm",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("dormname","no var");
        editor.putString("pathImage360","no path");
        editor.putInt("numberofFavorite",0);

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

        SharedPreferences lang = getActivity().getSharedPreferences("language",Context.MODE_PRIVATE);
        int langcode = lang.getInt("languageCode",2);

        final RequestQueue queue = Volley.newRequestQueue(getActivity());
        if(codeStyle == 0)
        {

            String pathGetDormInStyle = "http://"+ip+port+"/DormTypePriceNPromotion/getAllDorm";
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, pathGetDormInStyle, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                try
                {
                    JSONArray data = response;
                    priceModel = new Dorm_PricenPromotion_Model[data.length()];
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
                        //System.out.println(id+"  "+oldprice+"  "+newprice);
                        priceModel[count].setDorm_name(id);
                        priceModel[count].setOldPrice(oldprice);
                        priceModel[count].setNewPrice(newprice);
                        priceModel[count].setProdetail(proDetail);

                       // System.out.println("Dorm name: "+priceModel[count].getDorm_name());
                        String getPositionDorm = "http://"+ip+port+"/dorm/getDormByDormName/"+id;
                        JsonObjectRequest positionRequest = new JsonObjectRequest(Request.Method.GET, getPositionDorm, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONObject ob = response;
                                try
                                {
                                    double latitude = ob.getDouble("latitude");
                                    double longtitude = ob.getDouble("longtitude");
                                    //System.out.println(id+"  "+latitude+"  "+longtitude);

                                    String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
                                    JsonObjectRequest distanceRequest = new JsonObjectRequest(Request.Method.GET, getDistanceRequest, null, new Response.Listener<JSONObject>() {
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

                                            //System.out.println(id+"  "+distanceText);
                                            priceModel[c].setDistance(distanceText);

                                            String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+id;
                                            //System.out.println("Url: "+getUsernameAndEmail);
                                            JsonObjectRequest usernameAndEmailRequest = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    JSONObject dormowner = response;
                                                    try
                                                    {
                                                        String username = dormowner.getString("username");
                                                        String email = dormowner.getString("email");
                                                        //System.out.println(id+"  "+username+"  "+email);

                                                        String coverImagePath = "http://"+ip+port+"/CoverImage/getCoverImageByusernameAndEmail/"+username+"/"+email;
                                                        //System.out.println(coverImagePath);
                                                        JsonObjectRequest coverImageRequest = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                                            @Override
                                                            public void onResponse(JSONObject response) {
                                                             try
                                                             {
                                                                 JSONObject coverImage = response;
                                                                 String coverImagePath = coverImage.getString("coverImage");
                                                                 //System.out.println(coverImagePath);
                                                                 priceModel[c].setImage(coverImagePath);

                                                                 String pathGetPriceDorm = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+id;
                                                                 JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, pathGetPriceDorm, null, new Response.Listener<JSONObject>() {
                                                                     @Override
                                                                     public void onResponse(JSONObject response) {
                                                                         JSONObject obj = response;
                                                                         try
                                                                         {
                                                                             int minPricefromData = obj.getInt("minPrice");

                                                                             if(!proDetail.equals(""))
                                                                             {
                                                                                 priceModel[c].setStatus_promotion(1);
                                                                                 priceModel[c].setFaculty(facofuser);
                                                                                 pricelist.add(priceModel[c]);
                                                                             }
                                                                             else
                                                                             {
                                                                                 priceModel[c].setStatus_promotion(0);
                                                                                 priceModel[c].setOldPrice(minPricefromData);
                                                                                 priceModel[c].setFaculty(facofuser);
                                                                                 pricelist.add(priceModel[c]);
                                                                             }

                                                                             numberof = c + 1;
                                                                             //Toast.makeText(getActivity(),String.valueOf(numberof),Toast.LENGTH_SHORT).show();

                                                                                 //Toast.makeText(getActivity(),"finished",Toast.LENGTH_SHORT).show();

                                                                                 RecyclerViewAdapter_Type_PriceNPromotion a = new RecyclerViewAdapter_Type_PriceNPromotion(pricelist,getActivity());
                                                                                 recyclerview.setAdapter(a);
                                                                                 a.setOnItemClickListener(new OnItemClickListener() {
                                                                                     @Override
                                                                                     public void onItemClick(int position) {
                                                                                         String dormName = pricelist.get(position).getDorm_name();
                                                                                         Intent i = new Intent(getActivity(),Detail_Dorm_PriceAndPromotion.class);
                                                                                         i.putExtra("dormName",dormName);
                                                                                         //i.putExtra("dormnameList",favlist);
                                                                                         editor.putString("dormname",dormName);
                                                                                         editor.commit();
                                                                                         getActivity().startActivity(i);
                                                                                     }
                                                                                 });

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
                                                             catch (JSONException ex){
                                                                 ex.printStackTrace();
                                                             }

                                                            }
                                                        }, new Response.ErrorListener() {
                                                            @Override
                                                            public void onErrorResponse(VolleyError error) {

                                                            }
                                                        });
                                                        queue.add(coverImageRequest);
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

                                            queue.add(usernameAndEmailRequest);
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

                                    queue.add(distanceRequest);
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

                        queue.add(positionRequest);

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
        else if(codeStyle == 1)
        {
            final RequestQueue reqqueue = Volley.newRequestQueue(getActivity());
            this.informationlist = new ArrayList<>();
            String getDorm_Infotmation = "http://"+ip+port+"/DormTypeInformation/getAllDorm";

            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, getDorm_Infotmation, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    final JSONArray res = response;
                    try
                    {
                        infoModel = new Dorm_Information_Model[res.length()];

                        for(int count=0;count<res.length();count++)
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

                           // System.out.println("Name: "+infoModel.getName()+"   Detail: ");


                            String pathDorm = "http://"+ip+port+"/dorm/getDormByDormName/"+id;
                            //System.out.println("Path dorm: "+pathDorm);
                            JsonObjectRequest dormRequest = new JsonObjectRequest(Request.Method.GET, pathDorm, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                        JSONObject o = response;
                                        //System.out.println("Response: "+o);
                                    try
                                    {
                                        String address = o.getString("address");
                                        double longtitude = o.getDouble("longtitude");
                                        double latitude = o.getDouble("latitude");
                                        StringTokenizer token = new StringTokenizer(address," ");
                                        while(token.hasMoreTokens())
                                        {
                                            addressText = addressText+" "+token.nextToken();
                                        }
                                        System.out.println(id+"  "+addressText+"  "+latitude+"  "+longtitude);
                                        infoModel[c].setAddress(addressText);
                                        addressText = "";

                                        String getDistanceRequest = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+latitude+","+longtitude+"&destination="+facofuser.getLatitude()+","+facofuser.getLongtitude()+"&key="+newServerKey;
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
                                                 System.out.println(id+"  "+distanceText);
                                                 infoModel[c].setDistance(distanceText);

                                                 String getInfoDormRequest = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+id;
                                                 JsonObjectRequest infoDormRequest = new JsonObjectRequest(Request.Method.GET, getInfoDormRequest, null, new Response.Listener<JSONObject>() {
                                                     @Override
                                                     public void onResponse(JSONObject response) {
                                                            //System.out.println("RESPONSE: "+response);
                                                         try
                                                         {
                                                             int minPrice = response.getInt("minPrice");
                                                            // System.out.println(minPrice);
                                                             infoModel[c].setPrice(minPrice);

                                                             String getFacilityInRoom = "http://"+ip+port+"/facilityInRoom/getFacilityInRoom/"+id;
                                                             JsonObjectRequest getFacilityInRoomRequest = new JsonObjectRequest(Request.Method.GET, getFacilityInRoom, null, new Response.Listener<JSONObject>() {
                                                                 @Override
                                                                 public void onResponse(JSONObject response) {
                                                                        //System.out.println(response);
                                                                     try
                                                                     {

                                                                        JSONObject obj = response;
                                                                        int[] image;
                                                                        JSONArray array = obj.getJSONArray("image");
                                                                        image = new int[array.length()];
                                                                        for(int k=0;k<image.length;k++)
                                                                        {
                                                                            image[k] = array.getInt(k);
                                                                        }

                                                                        infoModel[c].setFacility(image);

                                                                        String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+id;
                                                                        JsonObjectRequest infoDormRequest = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                                                                            @Override
                                                                            public void onResponse(JSONObject response) {
                                                                                   //System.out.println(response);
                                                                                try
                                                                                {
                                                                                   String username = response.getString("username");
                                                                                   String email = response.getString("email");
                                                                                   System.out.println(username+"   "+email);

                                                                                   String getCoverImage = "http://"+ip+port+"/CoverImage/getCoverImageByusernameAndEmail/"+username+"/"+email;
                                                                                   JsonObjectRequest coverImageRequest = new JsonObjectRequest(Request.Method.GET, getCoverImage, null, new Response.Listener<JSONObject>() {
                                                                                       @Override
                                                                                       public void onResponse(JSONObject response) {
                                                                                               // System.out.println(response);
                                                                                           try
                                                                                           {
                                                                                               String pathImage = response.getString("coverImage");
                                                                                               System.out.println(pathImage);
                                                                                               infoModel[c].setImage(pathImage);

                                                                                               infoModel[c].setFaculty(facofuser);

                                                                                               informationlist.add(infoModel[c]);

                                                                                                RecyclerViewAdapter_Type_Information informationAdapter = new RecyclerViewAdapter_Type_Information(informationlist,getActivity().getApplicationContext(),langcode);
                                                                                                recyclerview.setAdapter(informationAdapter);
                                                                                                informationAdapter.setOnItemClickListener(new OnItemClickListener() {
                                                                                                @Override
                                                                                                public void onItemClick(int position) {
                                                                                                      String dormName = informationlist.get(position).getName();
                                                                                                      Intent i = new Intent(getActivity(), Detail_Dorm_Information.class);
                                                                                                      i.putExtra("dormName",dormName);
                                                                                                      //i.putExtra("dormnameList",favlist);
                                                                                                    editor.putString("dormname",dormName);
                                                                                                    editor.commit();
                                                                                                      getActivity().startActivity(i);
                                                                                                }
                                                                                                });




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

                                                                                   reqqueue.add(coverImageRequest);

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

                                                                        reqqueue.add(infoDormRequest);


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

                                                             reqqueue.add(getFacilityInRoomRequest);
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

                                                 reqqueue.add(infoDormRequest);
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

                                        reqqueue.add(distanceRequest);

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
                            reqqueue.add(dormRequest);



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
            reqqueue.add(req);
        }
        else if(codeStyle ==2)
        {
            final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
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
                            }
                            quaModel[count].setName(id);
                            quaModel[count].setPremieum(imageArray);

                            String getPositionPath = "http://"+ip+port+"/dorm/getDormByDormName/"+id;
                            JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getPositionPath, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    final JSONObject res = response;
                                    try
                                    {
                                        double latitude = res.getDouble("latitude");
                                        double longtitude = res.getDouble("longtitude");


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
                                                                int minPrice = j.getInt("minPrice");
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
                                                                                        qualitylist.add(quaModel[c]);

                                                                                        RecyclerViewAdapter_Type_Quality adapter = new RecyclerViewAdapter_Type_Quality(qualitylist,getActivity());
                                                                                        recyclerview.setAdapter(adapter);
                                                                                        adapter.setOnItemClickListener(new OnItemClickListener() {
                                                                                            @Override
                                                                                            public void onItemClick(int position) {

                                                                                                String dormName = qualitylist.get(position).getName();
                                                                                                Intent i = new Intent(getActivity(), Detail_Dorm_Quality.class);
                                                                                                i.putExtra("dormname",dormName);
                                                                                                //i.putExtra("dormnameList",favlist);
                                                                                                editor.putString("dormname",dormName);
                                                                                                editor.commit();
                                                                                                getActivity().startActivity(i);
                                                                                            }
                                                                                        });
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
        else if(codeStyle == 3)
        {
            brandnamelist = new ArrayList<>();
            final RequestQueue requestqueue = Volley.newRequestQueue(getActivity());
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
                                 try
                                 {
                                     String address = data.getString("address");
                                     double latitude = data.getDouble("latitude");
                                     double longtitude = data.getDouble("longtitude");
                                     String collecttext = " ";
                                     StringTokenizer token = new StringTokenizer(address," ");
                                     while(token.hasMoreTokens())
                                     {
                                         collecttext = collecttext + token.nextToken()+" ";
                                     }
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
                                              brandModel[c].setDistance(distanceText);

                                              String getMinPrice = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dormId;
                                              JsonObjectRequest minpriceRequest = new JsonObjectRequest(Request.Method.GET, getMinPrice, null, new Response.Listener<JSONObject>() {
                                                  @Override
                                                  public void onResponse(JSONObject response) {
                                                      try
                                                      {
                                                          int minPrice = response.getInt("minPrice");
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
                                                                                              brandnamelist.add(brandModel[c]);

                                                                                              RecyclerViewAdapter_Type_Brandname adapter = new RecyclerViewAdapter_Type_Brandname(brandnamelist,getActivity());
                                                                                              recyclerview.setAdapter(adapter);
                                                                                              adapter.setOnItemClickListener(new OnItemClickListener() {
                                                                                                  @Override
                                                                                                  public void onItemClick(int position) {
                                                                                                      String dormId = brandnamelist.get(position).getName();
                                                                                                      Intent i = new Intent(getActivity(), Detail_Dorm_BrandName.class);
                                                                                                      i.putExtra("dormName",dormId);
                                                                                                      //i.putExtra("dormnameList",favlist);
                                                                                                      editor.putString("dormname",dormId);
                                                                                                      editor.commit();
                                                                                                      getActivity().startActivity(i);
                                                                                                  }
                                                                                              });
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

            RecyclerViewAdapter_Type_Brandname adapter = new RecyclerViewAdapter_Type_Brandname(brandnamelist,getActivity());
            this.recyclerview.setAdapter(adapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    System.out.println(brandnamelist.get(position).getName()+"  "+position);
                }
            });
        }
        return v;
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
