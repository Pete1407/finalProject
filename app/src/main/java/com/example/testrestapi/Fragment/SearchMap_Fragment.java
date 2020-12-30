package com.example.testrestapi.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Leg;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.model.Step;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.testrestapi.Detail_Dorm_BrandName;
import com.example.testrestapi.Detail_Dorm_Information;
import com.example.testrestapi.Detail_Dorm_PriceAndPromotion;
import com.example.testrestapi.Detail_Dorm_Quality;
import com.example.testrestapi.Faculty;
import com.example.testrestapi.ListViewAdapter;
import com.example.testrestapi.Places;
import com.example.testrestapi.R;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SearchMap_Fragment extends Fragment {
    public int facultyid = 0;
    public String apikey = "AIzaSyDvkyNSklVDDi9ZXAaARBcTmpAon01re8g";
    public String urlDirection;
    public LatLng dorm_origin;

    public Button selectDorm;
    public Button getposition;
    public ImageView driving;
    public ImageView transit;
    public ImageView motorcycle;
    public String chooseTraveling;
    public Button search;
    public ListView list;
    public Places place;

    public int paddingDP = 95;

    public String facultyOfUser;

    public boolean statusSearch;

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

    public AlertDialog.Builder placeDialog;
    public String port = ":8080";
    public String ip = "192.168.43.57"+port;

    public String dormStyle;
    public int codeStyle;

    public LinearLayout partoftravel;

    public String[] arrayStyle;
    public ArrayList<String> nameD;
    public ArrayList<Places> plist;

    public String namedormThatUserchoose;

    public GoogleMap mMap;
    public LatLng dormPos;
    public LatLng thammasat = new LatLng(14.069685, 100.603324);

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public String newServerKey = "AIzaSyAIm2bf5ksXaJTiIVoBHFuwyW4ebWOdLLw";
    public LatLng dormOrigin;
    //public LatLng thammasatDestination = new LatLng(14.069685, 100.603324);

    public ImageView image_place;
    public TextView title;
    public TextView subtitle;

    public TextView detailPlace;
    public LinearLayout linear;

    public ExpandableHeightListView listview;

    public ArrayList<Places> placelist;

    public LinearLayout partDriving;
    public LinearLayout partTransit;
    public TextView textDriving;
    public TextView textTransit;
    public TextView sign;
    public int black;

    public int checkchoosedorm = 4;
    public int checkchoosetravel = 4;
    public int langcode;
    PolylineOptions polylineOptionsDriving;
    PolylineOptions polylineOptionsTransit;
    public boolean checkpressposition = false;
    ListViewAdapter adapter;
    public String searchbutton;
    public SearchMap_Fragment newInstance(){
        return new SearchMap_Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.setFaculty();
        this.nameD = new ArrayList<>();
        this.plist = new ArrayList<>();
        this.placelist = new ArrayList<>();
        this.arrayStyle = getResources().getStringArray(R.array.dormtype);
        searchbutton = getResources().getString(R.string.search_title);
        View v = inflater.inflate(R.layout.searchmap_fragment,container,false);
        partoftravel = (LinearLayout) v.findViewById(R.id.partoftravel);
        this.listview = (ExpandableHeightListView) v.findViewById(R.id.expand_nearPlace);
        this.selectDorm = (Button) v.findViewById(R.id.button5);
        this.getposition = (Button) v.findViewById(R.id.findPosition);
        this.getposition.setEnabled(false);
        this.driving = (ImageView) v.findViewById(R.id.driving);
        this.transit = (ImageView) v.findViewById(R.id.transit);
        this.search = (Button) v.findViewById(R.id.searchview);
        this.linear = (LinearLayout) v.findViewById(R.id.border_searchdorm);
        this.partDriving = (LinearLayout) v.findViewById(R.id.PartDriving);
        this.partTransit = (LinearLayout) v.findViewById(R.id.PartTransit);
        this.textDriving = (TextView) v.findViewById(R.id.textDriving);
        this.textTransit = (TextView) v.findViewById(R.id.textTransit);
        this.sign = (TextView) v.findViewById(R.id.textView10);

        this.driving.setEnabled(false);
        this.transit.setEnabled(false);
        search.setText(searchbutton);
        this.placeDialog = new AlertDialog.Builder(getActivity());
        final String[] array = getResources().getStringArray(R.array.faculty);
        Bundle b = this.getArguments();



        SharedPreferences lang = getActivity().getSharedPreferences("language", Context.MODE_PRIVATE);
        langcode = lang.getInt("languageCode",0);

        sharedPreferences = getActivity().getSharedPreferences("file_pref", MODE_PRIVATE);
        String fac = sharedPreferences.getString("faculty","no faculty");
        String dormStyleText = sharedPreferences.getString("dormStyle","no dormStyle");
        int codeDormStyle = sharedPreferences.getInt("dormStyleCode",0);

        black = getActivity().getResources().getColor(R.color.black);
        if(codeDormStyle == 3)
        {

            this.linear.setBackground(new ColorDrawable(Color.parseColor("#F2F2F2")));
            this.sign.setTextColor(black);
        }

        facultyOfUser = fac;
        this.dormStyle = dormStyleText;
        this.codeStyle = codeDormStyle;



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


        float density = getResources().getDisplayMetrics().density;
        final int paddingPixel = (int)(paddingDP * density);



        //this.list = (ListView) v.findViewById(R.id.p_listview);

        SupportMapFragment fgr = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.searchbyMap);
        fgr.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap.clear();
                mMap.getUiSettings().setZoomGesturesEnabled(true);
                mMap.getUiSettings().setZoomControlsEnabled(true);


                int height = 100;
                int width = 100;

                BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.tulogo_cutbackground);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                MarkerOptions tham = new MarkerOptions().position(thammasat).title("Thammasat University").icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                mMap.addMarker(tham);
                LatLng latLng = new LatLng(14.069487, 100.603292);
                CameraPosition cameraPosition = CameraPosition.builder()
                        .target(latLng)
                        .zoom(15)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),1000,null);
            }
        });

        this.selectDorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.select_dialog_singlechoice);
                final AlertDialog.Builder dormDialog = new AlertDialog.Builder(getActivity());
                String pathGetDormPriceNPromotion = "http://"+ip+"/DormTypePriceNPromotion/getAllDorm";
                String pathGetDormInformation = "http://"+ip+"/DormTypeInformation/getAllDorm";
                String pathGetDormQuality = "http://"+ip+"/DormTypeQuality/getAllDorm";
                String pathGetDormBrandname = "http://"+ip+"/DormTypeBrandName/getAllDorm";
                if(dormStyle.equals(arrayStyle[0]))
                {
                    checkchoosedorm = 0;
                    final RequestQueue queue = Volley.newRequestQueue(getActivity());
                    JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, pathGetDormPriceNPromotion, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                JSONArray array = response;
                                System.out.println(array);

                                for(int count=0;count<array.length();count++)
                                {
                                    JSONObject obj = array.getJSONObject(count);
                                    String name = obj.getString("dormID");
                                    nameD.add(name);
                                    adapter.add(name);
                                }

                                dormDialog.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                                dormDialog.setAdapter(adapter, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mMap.clear();

                                        int height = 100;
                                        int width = 100;

                                        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.tulogo_cutbackground);
                                        Bitmap b = bitmapdraw.getBitmap();
                                        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                                        MarkerOptions tham = new MarkerOptions().position(thammasat).title("Thammasat University").icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                                        mMap.addMarker(tham);


                                        String n = adapter.getItem(which);
                                        namedormThatUserchoose = n;
                                        driving.setEnabled(true);
                                        transit.setEnabled(true);
                                        checkpressposition = false;
                                        if(checkpressposition == true || checkpressposition == false)
                                        {
                                            placelist.clear();
                                        }
                                        selectDorm.setText(namedormThatUserchoose);
                                        getposition.setEnabled(true);
                                       // Toast.makeText(getActivity(),n,Toast.LENGTH_SHORT).show();

                                        String pathgetPositionDorm = "http://"+ip+"/dorm/getDormByDormName/"+namedormThatUserchoose;
                                        System.out.println(pathgetPositionDorm);

                                        JsonObjectRequest getPositionDorm = new JsonObjectRequest(Request.Method.GET, pathgetPositionDorm, null, new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                System.out.println(response+"  ***********************8");
                                                JSONObject object = response;
                                                try
                                                {
                                                    double latitude = object.getDouble("latitude");
                                                    double longtitude = object.getDouble("longtitude");

                                                    dormPos = new LatLng(latitude,longtitude);

                                                    BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_dorm_use);
                                                    Bitmap b = bitmapdraw.getBitmap();
                                                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, 150, 150, false);

                                                    MarkerOptions marker = new MarkerOptions().position(dormPos).title(namedormThatUserchoose).icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                                                    mMap.addMarker(marker);

                                                    CameraPosition came = CameraPosition.builder().zoom(15).target(dormPos).build();
                                                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(came),1000,null);
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
                                        queue.add(getPositionDorm);

                                    }
                                });

                                dormDialog.show();

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
                else if(dormStyle.equals(arrayStyle[1]))
                {
                    checkchoosedorm = 1;
                    final RequestQueue queue = Volley.newRequestQueue(getActivity());
                    JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, pathGetDormInformation, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                JSONArray array = response;
                                System.out.println(array);

                                for(int count=0;count<array.length();count++)
                                {
                                    JSONObject obj = array.getJSONObject(count);
                                    String name = obj.getString("dormID");
                                    nameD.add(name);
                                    adapter.add(name);
                                }

                                dormDialog.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                                dormDialog.setAdapter(adapter, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mMap.clear();

                                        int height = 100;
                                        int width = 100;
                                        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.tulogo_cutbackground);
                                        Bitmap b = bitmapdraw.getBitmap();
                                        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                                        MarkerOptions tham = new MarkerOptions().position(thammasat).title("Thammasat University").icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                                        mMap.addMarker(tham);

                                        String n = adapter.getItem(which);
                                        namedormThatUserchoose = n;
                                        driving.setEnabled(true);
                                        transit.setEnabled(true);
                                        checkpressposition = false;
                                        if(checkpressposition == true || checkpressposition == false)
                                        {
                                            placelist.clear();
                                        }
                                        selectDorm.setText(namedormThatUserchoose);
                                        getposition.setEnabled(true);
                                        //Toast.makeText(getActivity(),n,Toast.LENGTH_SHORT).show();

                                        String pathgetPositionDorm = "http://"+ip+"/dorm/getDormByDormName/"+namedormThatUserchoose;
                                        System.out.println(pathgetPositionDorm);

                                        JsonObjectRequest getPositionDorm = new JsonObjectRequest(Request.Method.GET, pathgetPositionDorm, null, new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                System.out.println(response+"  ***********************8");
                                                JSONObject object = response;
                                                try
                                                {
                                                    double latitude = object.getDouble("latitude");
                                                    double longtitude = object.getDouble("longtitude");

                                                    dormPos = new LatLng(latitude,longtitude);

                                                    BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_dorm_use);
                                                    Bitmap b = bitmapdraw.getBitmap();
                                                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, 150, 150, false);

                                                    MarkerOptions marker = new MarkerOptions().position(dormPos).title(namedormThatUserchoose).icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                                                    mMap.addMarker(marker);


                                                    CameraPosition came = CameraPosition.builder().zoom(15).target(dormPos).build();
                                                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(came),1000,null);
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
                                        queue.add(getPositionDorm);

                                    }
                                });

                                dormDialog.show();

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
                else if(dormStyle.equals(arrayStyle[2]))
                {
                    checkchoosedorm = 2;
                    final RequestQueue queue = Volley.newRequestQueue(getActivity());
                    JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, pathGetDormQuality, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                JSONArray array = response;
                                System.out.println(array);

                                for(int count=0;count<array.length();count++)
                                {
                                    JSONObject obj = array.getJSONObject(count);
                                    String name = obj.getString("dormID");
                                    nameD.add(name);
                                    adapter.add(name);
                                }

                                dormDialog.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                                dormDialog.setAdapter(adapter, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mMap.clear();

                                        int height = 100;
                                        int width = 100;
                                        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.tulogo_cutbackground);
                                        Bitmap b = bitmapdraw.getBitmap();
                                        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                                        MarkerOptions tham = new MarkerOptions().position(thammasat).title("Thammasat University").icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                                        mMap.addMarker(tham);


                                        String n = adapter.getItem(which);
                                        namedormThatUserchoose = n;
                                        driving.setEnabled(true);
                                        transit.setEnabled(true);
                                        checkpressposition = false;
                                        if(checkpressposition == true || checkpressposition == false)
                                        {
                                            placelist.clear();
                                        }
                                        selectDorm.setText(namedormThatUserchoose);
                                        getposition.setEnabled(true);
                                        //Toast.makeText(getActivity(),n,Toast.LENGTH_SHORT).show();

                                        String pathgetPositionDorm = "http://"+ip+"/dorm/getDormByDormName/"+namedormThatUserchoose;
                                        System.out.println(pathgetPositionDorm);

                                        JsonObjectRequest getPositionDorm = new JsonObjectRequest(Request.Method.GET, pathgetPositionDorm, null, new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                System.out.println(response+"  ***********************8");
                                                JSONObject object = response;
                                                try
                                                {
                                                    double latitude = object.getDouble("latitude");
                                                    double longtitude = object.getDouble("longtitude");

                                                    dormPos = new LatLng(latitude,longtitude);

                                                    BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_dorm_use);
                                                    Bitmap b = bitmapdraw.getBitmap();
                                                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, 150, 150, false);

                                                    MarkerOptions marker = new MarkerOptions().position(dormPos).title(namedormThatUserchoose).icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                                                    mMap.addMarker(marker);

                                                    CameraPosition came = CameraPosition.builder().zoom(15).target(dormPos).build();
                                                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(came),1000,null);
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
                                        queue.add(getPositionDorm);
                                    }
                                });

                                dormDialog.show();

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
                else if(dormStyle.equals(arrayStyle[3]))
                {
                    checkchoosedorm = 3;
                    final RequestQueue queue = Volley.newRequestQueue(getActivity());
                    JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, pathGetDormBrandname, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                JSONArray array = response;
                                System.out.println(array);

                                for(int count=0;count<array.length();count++)
                                {
                                    JSONObject obj = array.getJSONObject(count);
                                    String name = obj.getString("dormID");
                                    nameD.add(name);
                                    adapter.add(name);
                                }

                                dormDialog.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                                dormDialog.setAdapter(adapter, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mMap.clear();

                                        int height = 100;
                                        int width = 100;
                                        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.tulogo_cutbackground);
                                        Bitmap b = bitmapdraw.getBitmap();
                                        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                                        MarkerOptions tham = new MarkerOptions().position(thammasat).title("Thammasat University").icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                                        mMap.addMarker(tham);

                                        String n = adapter.getItem(which);
                                        namedormThatUserchoose = n;
                                        driving.setEnabled(true);
                                        transit.setEnabled(true);
                                        checkpressposition = false;
                                        if(checkpressposition == true || checkpressposition == false)
                                        {
                                            placelist.clear();
                                        }
                                        selectDorm.setText(namedormThatUserchoose);
                                        getposition.setEnabled(true);
                                        //Toast.makeText(getActivity(),n,Toast.LENGTH_SHORT).show();


                                        String pathgetPositionDorm = "http://"+ip+"/dorm/getDormByDormName/"+namedormThatUserchoose;
                                        System.out.println(pathgetPositionDorm);

                                        JsonObjectRequest getPositionDorm = new JsonObjectRequest(Request.Method.GET, pathgetPositionDorm, null, new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                System.out.println(response+"  ***********************8");
                                                JSONObject object = response;
                                                try
                                                {
                                                    double latitude = object.getDouble("latitude");
                                                    double longtitude = object.getDouble("longtitude");

                                                    dormPos = new LatLng(latitude,longtitude);

                                                    BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_dorm_use);
                                                    Bitmap b = bitmapdraw.getBitmap();
                                                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, 150, 150, false);

                                                    MarkerOptions marker = new MarkerOptions().position(dormPos).title(namedormThatUserchoose).icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                                                    mMap.addMarker(marker);

                                                    CameraPosition came = CameraPosition.builder().zoom(15).target(dormPos).build();
                                                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(came),1000,null);
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
                                        queue.add(getPositionDorm);
                                    }
                                });

                                dormDialog.show();

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



            }
        });



        this.driving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                placelist.clear();
                mMap.clear();
                checkchoosetravel = 0;
                chooseTraveling = getResources().getString(R.string.driving);
                partDriving.setBackground(getResources().getDrawable(R.drawable.background_driving));
                partTransit.setBackground(getResources().getDrawable(R.drawable.image_reset_background));
                int white = getResources().getColor(R.color.white_Background);
                int black = getResources().getColor(R.color.black);
                textDriving.setTextColor(white);
                textTransit.setTextColor(black);
                textDriving.setTypeface(Typeface.DEFAULT_BOLD);
                textTransit.setTypeface(Typeface.DEFAULT_BOLD);


                String pathgetPositionDorm = "http://"+ip+"/dorm/getDormByDormName/"+namedormThatUserchoose;
                System.out.println(pathgetPositionDorm);

                JsonObjectRequest getPositionDorm = new JsonObjectRequest(Request.Method.GET, pathgetPositionDorm, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response+"  ***********************8");
                        JSONObject object = response;
                        try
                        {
                            double latitude = object.getDouble("latitude");
                            double longtitude = object.getDouble("longtitude");

                            dormPos = new LatLng(latitude,longtitude);

                            BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_dorm_use);
                            Bitmap b = bitmapdraw.getBitmap();
                            Bitmap smallMarker = Bitmap.createScaledBitmap(b, 150, 150, false);

                            MarkerOptions marker = new MarkerOptions().position(dormPos).title(namedormThatUserchoose).icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                            mMap.addMarker(marker);

                            CameraPosition came = CameraPosition.builder().zoom(15).target(dormPos).build();
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(came),1000,null);
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
                queue.add(getPositionDorm);

                final Places[] p = new Places[plist.size()];
                for(int count=0;count<plist.size();count++)
                {
                    final int var = count;
                    urlDirection = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+dormPos.latitude+","+dormPos.longitude+"&destination="+plist.get(count).getLatitude_place()+","+plist.get(count).getLongtitude_place()+"&key="+newServerKey;
                    System.out.println("URL :"+urlDirection);
                    GoogleDirection.withServerKey(newServerKey)
                            .from(new LatLng(dormPos.latitude,dormPos.longitude))
                            .to(new LatLng(plist.get(count).getLatitude_place(),plist.get(count).getLongtitude_place()))
                            .transportMode(TransportMode.DRIVING)
                            .execute(new DirectionCallback() {
                                @Override
                                public void onDirectionSuccess(Direction direction) {
                                    if(direction.isOK())
                                    {
                                        Route rr = direction.getRouteList().get(0);
                                        Leg legg = rr.getLegList().get(0);
                                        List<Step> step = legg.getStepList();
                                        ArrayList<LatLng> directionPositionList = legg.getDirectionPoint();
                                        polylineOptionsDriving = DirectionConverter.createPolyline(getActivity(), directionPositionList, 5, Color.RED);
                                        mMap.addPolyline(polylineOptionsDriving);
                                    }
                                }

                                @Override
                                public void onDirectionFailure(Throwable t) {

                                }
                            });
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlDirection, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONObject data = response;
                            //System.out.println(data);


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
                                System.out.println(plist.get(var).getNameTH()+"  "+distance_text+"  "+duration_text);
                                p[var] = new Places(plist.get(var).getNameTH(),plist.get(var).getNameEN(),plist.get(var).getLatitude_place(),plist.get(var).getLongtitude_place());
                                p[var].setDistance(distance_text);
                                LatLng dorm = new LatLng(plist.get(var).getLatitude_place(),plist.get(var).getLongtitude_place());



                                BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_place_modify);
                                Bitmap b = bitmapdraw.getBitmap();
                                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 150, 150, false);
                                MarkerOptions marker = null;
                                if(langcode == 0)
                                {
                                    marker = new MarkerOptions().position(dorm).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).snippet("ระยะทางจากหอ:"+distance_text+" เวลา:"+duration_text).title(plist.get(var).getNameTH());
                                }
                                else
                                {
                                    marker = new MarkerOptions().position(dorm).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).snippet("from dorm :"+distance_text+" time:"+duration_text).title(plist.get(var).getNameEN());

                                }

                                mMap.addMarker(marker);

                                placelist.add(p[var]);


                                CameraPosition posi = CameraPosition.builder().zoom(15).target(thammasat).build();
                                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(posi),1000,null);

                                adapter = new ListViewAdapter(getActivity(),placelist,langcode);
                                listview.setAdapter(adapter);
                                listview.setExpanded(true);


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

                //driving.setBackground(getResources().getDrawable(R.drawable.background_driving));
                //transit.setBackground(getResources().getDrawable(R.drawable.image_reset_background));
                //Toast.makeText(getActivity(),chooseTraveling,Toast.LENGTH_SHORT).show();
                //walk.setBackground(getResources().getDrawable(R.drawable.image_reset_background));
                //car.setBackground(getResources().getDrawable(R.drawable.border_car));
                //car.setScaleType(ImageView.ScaleType.FIT_CENTER);
                //car.getLayoutParams().width =(int) getResources().getDimension(R.dimen.width_image);
                //car.getLayoutParams().height =(int) getResources().getDimension(R.dimen.height_image);
                //car.setPadding(paddingPixel,paddingPixel,paddingPixel,paddingPixel);
                //motorcycle.setBackground(getResources().getDrawable(R.drawable.image_reset_background));

            }
        });

        this.transit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                placelist.clear();
                mMap.clear();
                chooseTraveling = getResources().getString(R.string.transit);
                checkchoosetravel = 1;
                partTransit.setBackground(getResources().getDrawable(R.drawable.background_driving));
                partDriving.setBackground(getResources().getDrawable(R.drawable.image_reset_background));
                int white = getResources().getColor(R.color.white_Background);
                int black = getResources().getColor(R.color.black);
                textDriving.setTextColor(black);
                textTransit.setTextColor(white);
                textDriving.setTypeface(Typeface.DEFAULT_BOLD);
                textTransit.setTypeface(Typeface.DEFAULT_BOLD);

                String pathgetPositionDorm = "http://"+ip+"/dorm/getDormByDormName/"+namedormThatUserchoose;
                System.out.println(pathgetPositionDorm);

                JsonObjectRequest getPositionDorm = new JsonObjectRequest(Request.Method.GET, pathgetPositionDorm, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response+"  ***********************8");
                        JSONObject object = response;
                        try
                        {
                            double latitude = object.getDouble("latitude");
                            double longtitude = object.getDouble("longtitude");

                            dormPos = new LatLng(latitude,longtitude);

                            BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_dorm_use);
                            Bitmap b = bitmapdraw.getBitmap();
                            Bitmap smallMarker = Bitmap.createScaledBitmap(b, 150, 150, false);

                            MarkerOptions marker = new MarkerOptions().position(dormPos).title(namedormThatUserchoose).icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                            mMap.addMarker(marker);

                            CameraPosition came = CameraPosition.builder().zoom(15).target(dormPos).build();
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(came),1000,null);
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
                queue.add(getPositionDorm);


                final Places[] p = new Places[plist.size()];
                for(int count=0;count<plist.size();count++)
                {
                    final int var = count;
                    urlDirection = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+dormPos.latitude+","+dormPos.longitude+"&destination="+plist.get(count).getLatitude_place()+","+plist.get(count).getLongtitude_place()+"&key="+newServerKey;
                    System.out.println("URL :"+urlDirection);
                    GoogleDirection.withServerKey(newServerKey)
                            .from(new LatLng(dormPos.latitude,dormPos.longitude))
                            .to(new LatLng(plist.get(count).getLatitude_place(),plist.get(count).getLongtitude_place()))
                            .transportMode(TransportMode.TRANSIT)
                            .execute(new DirectionCallback() {
                                @Override
                                public void onDirectionSuccess(Direction direction) {
                                    if(direction.isOK())
                                    {
                                        Route rr = direction.getRouteList().get(0);
                                        Leg legg = rr.getLegList().get(0);
                                        List<Step> step = legg.getStepList();
                                        ArrayList<LatLng> directionPositionList = legg.getDirectionPoint();
                                        polylineOptionsTransit = DirectionConverter.createPolyline(getActivity(), directionPositionList, 5, Color.RED);
                                        mMap.addPolyline(polylineOptionsTransit);
                                    }
                                }

                                @Override
                                public void onDirectionFailure(Throwable t) {

                                }
                            });
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlDirection, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONObject data = response;
                            //System.out.println(data);


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
                                System.out.println(plist.get(var).getNameTH()+"  "+distance_text+"  "+duration_text);
                                p[var] = new Places(plist.get(var).getNameTH(),plist.get(var).getNameEN(),plist.get(var).getLatitude_place(),plist.get(var).getLongtitude_place());
                                p[var].setDistance(distance_text);
                                LatLng dorm = new LatLng(plist.get(var).getLatitude_place(),plist.get(var).getLongtitude_place());



                                BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_place_modify);
                                Bitmap b = bitmapdraw.getBitmap();
                                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 150, 150, false);
                                MarkerOptions marker = null;
                                if(langcode == 0)
                                {
                                    marker = new MarkerOptions().position(dorm).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).snippet("ระยะทางจากหอ:"+distance_text+" เวลา:"+duration_text).title(plist.get(var).getNameTH());
                                }
                                else
                                {
                                    marker = new MarkerOptions().position(dorm).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).snippet("from dorm :"+distance_text+" time:"+duration_text).title(plist.get(var).getNameEN());

                                }

                                mMap.addMarker(marker);

                                placelist.add(p[var]);


                                CameraPosition posi = CameraPosition.builder().zoom(15).target(thammasat).build();
                                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(posi),1000,null);

                                adapter = new ListViewAdapter(getActivity(),placelist,langcode);
                                listview.setAdapter(adapter);
                                listview.setExpanded(true);

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

                //transit.setBackground(getResources().getDrawable(R.drawable.background_driving));
                //driving.setBackground(getResources().getDrawable(R.drawable.image_reset_background));
                //Toast.makeText(getActivity(),chooseTraveling,Toast.LENGTH_SHORT).show();

                //motorcycle.getLayoutParams().width =(int) getResources().getDimension(R.dimen.width_image);
               // motorcycle.getLayoutParams().height =(int) getResources().getDimension(R.dimen.height_image);
                //motorcycle.setBackground(getResources().getDrawable(R.drawable.border_motorcycle));
                //walk.setBackground(getResources().getDrawable(R.drawable.image_reset_background));
                //car.setBackground(getResources().getDrawable(R.drawable.image_reset_background));

            }
        });

        this.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ViewPager pager = (ViewPager) getActivity().findViewById(R.id.viewPager);
                //pager.setCurrentItem(1);
             if(checkchoosedorm == 0 || checkchoosedorm == 1 || checkchoosedorm == 2 || checkchoosedorm == 3)
             {
                 if(checkchoosetravel == 0 || checkchoosetravel == 1)
                 {
                     System.out.println("USER CHOOSE: "+namedormThatUserchoose);
                     sharedPreferences = getActivity().getSharedPreferences("file_pref", MODE_PRIVATE);
                     statusSearch = true;
                     editor = sharedPreferences.edit();
                     editor.putBoolean("statusSearch",statusSearch);
                     editor.commit();
                     Intent i;

                     // ถ้าผู้ใช้อยู่ style 1
                     if(dormStyle.equals(arrayStyle[0]))
                     {
                         i = new Intent(getActivity(),Detail_Dorm_PriceAndPromotion.class);
                         i.putExtra("dormName",namedormThatUserchoose);
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("selectDorm",MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("dormname",namedormThatUserchoose);
                        edit.commit();
                         i.putExtra("travel",chooseTraveling);
                         getActivity().startActivity(i);
                     }
                     // ถ้าผู้ใช้อยู่ style 2
                     else if(dormStyle.equals(arrayStyle[1]))
                     {
                         i = new Intent(getActivity(), Detail_Dorm_Information.class);
                         i.putExtra("dormName",namedormThatUserchoose);
                         SharedPreferences sharedPreferences = getActivity().getSharedPreferences("selectDorm",MODE_PRIVATE);
                         SharedPreferences.Editor edit = sharedPreferences.edit();
                         edit.putString("dormname",namedormThatUserchoose);
                         edit.commit();
                         i.putExtra("travel",chooseTraveling);
                         getActivity().startActivity(i);
                     }
                     // ถ้าผู้ใช้อยู่ style 3
                     else if(dormStyle.equals(arrayStyle[2]))
                     {
                         i = new Intent(getActivity(), Detail_Dorm_Quality.class);
                         i.putExtra("dormName",namedormThatUserchoose);
                         SharedPreferences sharedPreferences = getActivity().getSharedPreferences("selectDorm",MODE_PRIVATE);
                         SharedPreferences.Editor edit = sharedPreferences.edit();
                         edit.putString("dormname",namedormThatUserchoose);
                         edit.commit();
                         i.putExtra("travel",chooseTraveling);
                         getActivity().startActivity(i);
                     }
                     // ถ้าผู้ใช้อยู่ style 4
                     else if(dormStyle.equals(arrayStyle[3]))
                     {
                         i = new Intent(getActivity(), Detail_Dorm_BrandName.class);
                         i.putExtra("dormName",namedormThatUserchoose);
                         SharedPreferences sharedPreferences = getActivity().getSharedPreferences("selectDorm",MODE_PRIVATE);
                         SharedPreferences.Editor edit = sharedPreferences.edit();
                         edit.putString("dormname",namedormThatUserchoose);
                         edit.commit();
                         i.putExtra("travel",chooseTraveling);
                         getActivity().startActivity(i);
                     }
                 }
                 else
                 {
                     AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                     String title = getActivity().getResources().getString(R.string.notifytravel);
                     dialog.setTitle(title);
                     String mes = getActivity().getResources().getString(R.string.messageNotifyTravel);
                     String ok = getActivity().getString(R.string.confirm);
                     dialog.setMessage(mes);
                     dialog.setIcon(R.drawable.alert);
                     dialog.setNegativeButton(ok, new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             dialog.dismiss();
                         }
                     });
                     dialog.show();
                 }
             }
             else
             {
                 AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                 String title = getActivity().getResources().getString(R.string.notifyDorm);
                 dialog.setTitle(title);
                 String mes = getActivity().getResources().getString(R.string.messageNotifyDorm);
                 String ok = getActivity().getString(R.string.confirm);
                 dialog.setMessage(mes);
                 dialog.setIcon(R.drawable.alert);
                 dialog.setNegativeButton(ok, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                           dialog.dismiss();
                     }
                 });
                 dialog.show();
             }


            }
        });

        this.getposition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkpressposition = true;
                if(placelist.size() == 0)
                {

                }
                else
                {
                    placelist.clear();
                }
               //Toast.makeText(getActivity(),"I do",Toast.LENGTH_SHORT).show();
               final Places[] p = new Places[plist.size()];
                for(int count=0;count<plist.size();count++)
                {
                 final int var = count;
                 urlDirection = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+dormPos.latitude+","+dormPos.longitude+"&destination="+plist.get(count).getLatitude_place()+","+plist.get(count).getLongtitude_place()+"&key="+newServerKey;
                 System.out.println("URL :"+urlDirection);

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlDirection, null, new Response.Listener<JSONObject>() {
                     @Override
                     public void onResponse(JSONObject response) {
                            JSONObject data = response;
                            //System.out.println(data);

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
                             System.out.println(plist.get(var).getNameTH()+"  "+distance_text+"  "+duration_text);
                             p[var] = new Places(plist.get(var).getNameTH(),plist.get(var).getNameEN(),plist.get(var).getLatitude_place(),plist.get(var).getLongtitude_place());
                             p[var].setDistance(distance_text);
                             LatLng dorm = new LatLng(plist.get(var).getLatitude_place(),plist.get(var).getLongtitude_place());



                             BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_place_modify);
                             Bitmap b = bitmapdraw.getBitmap();
                             Bitmap smallMarker = Bitmap.createScaledBitmap(b, 150, 150, false);
                             MarkerOptions marker = null;
                             if(langcode == 0)
                             {
                                 marker = new MarkerOptions().position(dorm).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).snippet("ระยะทางจากหอ:"+distance_text+" เวลา:"+duration_text).title(plist.get(var).getNameTH());
                             }
                             else
                             {
                                 marker = new MarkerOptions().position(dorm).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).snippet("from dorm :"+distance_text+" time:"+duration_text).title(plist.get(var).getNameEN());

                             }

                             mMap.addMarker(marker);

                             placelist.add(p[var]);


                             CameraPosition posi = CameraPosition.builder().zoom(15).target(thammasat).build();
                             mMap.animateCamera(CameraUpdateFactory.newCameraPosition(posi),1000,null);

                             ListViewAdapter adapter = new ListViewAdapter(getActivity(),placelist,langcode);
                             listview.setAdapter(adapter);
                             listview.setExpanded(true);


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

                 RequestQueue queue = Volley.newRequestQueue(getActivity());
                 queue.add(request);

                }

            }
        });

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
        scCanteen1 = new Places("ศูนย์อาหาร SC1/JC","SC1/JC Canteen",14.069249, 100.604760);
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
