package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class AddDorm extends AppCompatActivity implements OnMapReadyCallback , GoogleMap.OnMapClickListener ,GoogleMap.OnMarkerDragListener{
    public ActionBar actionbar;

    public ArrayAdapter<String> adapter;

    public ArrayList<Province> provincelist;
    public ArrayList<Amphur> amphurList;



    public ArrayList<Tumbon> tumbonlist;

    public String apiKey = "AIzaSyDvkyNSklVDDi9ZXAaARBcTmpAon01re8g";

    private String type0;
    private String type1;
    private String type2;
    private String type3;
    private String type4;

    public String houenumber_input = "";
    public String roadname_input = "";
    public String soi_input = "";
    public String province_input = "";
    public String amphur_input = "";
    public String tumbon_input = "";
    public String postcode_input = "";



    public EditText housenumber;
    public EditText roadname;
    public EditText soi;

    public Button chooseProvince;
    public Button chooseAmphur;
    public Button chooseTumbon;
    public EditText postcode;
    public Button identifyposition;

    public int province_key;
    public int amphur_key;

    public double longtitude;
    public double latitude;

    public String json = null;

    public GoogleMap mMap;

    public boolean checkMap = false;

    public int numberMarker = 0;


    public String dormOW;
    public String collectString;

    public String username;
    public String password;

    public String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dorm);

        this.dormOW = getIntent().getStringExtra("dormOwnerID");
        this.username = getIntent().getStringExtra("username");
        this.password = getIntent().getStringExtra("password");
        this.email = getIntent().getStringExtra("email");

        System.out.println(dormOW+"  ************************");
        this.readFileJson();

        this.provincelist = new ArrayList<>();
        this.amphurList = new ArrayList<>();
        this.tumbonlist = new ArrayList<>();


        this.chooseProvince = (Button) findViewById(R.id.button2);
        this.chooseAmphur = (Button) findViewById(R.id.button3);
        this.chooseTumbon = (Button) findViewById(R.id.button4);
        this.postcode = (EditText) findViewById(R.id.postcode);
        this.housenumber = (EditText) findViewById(R.id.housenumber);
        this.roadname = (EditText) findViewById(R.id.roadname);
        this.soi = (EditText) findViewById(R.id.soi);
        this.identifyposition = (Button) findViewById(R.id.identify_position);

        this.actionbar = getSupportActionBar();
        this.actionbar.setDisplayHomeAsUpEnabled(true);

        String title = getResources().getString(R.string.addDorm);
        this.actionbar.setTitle(title);

        this.actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));




        String[] array = getResources().getStringArray(R.array.dormtype);

        this.type0 = array[0];
        this.type1 = array[1];
        this.type2 = array[2];
        this.type3 = array[3];


       housenumber.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {


           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {
                houenumber_input = s.toString();
               //System.out.println(houenumber_input);
           }
       });

        roadname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                roadname_input = s.toString();
               // System.out.println(roadname_input);
            }
        });

        soi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                    soi_input = s.toString();
                    //System.out.println(soi_input);

            }
        });

       postcode.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {


           }

           @Override
           public void afterTextChanged(Editable s) {
                postcode_input = s.toString();
               //System.out.println(postcode_input);

           }
       });



    }



    public void readFileJson(){
        Resources res = getResources();
        InputStream stream = res.openRawResource(R.raw.thai_json);
        Scanner scanner = new Scanner(stream);
        StringBuilder stringBuilder = new StringBuilder();
        while(scanner.hasNextLine())
        {
            stringBuilder.append(scanner.nextLine());
        }

        this.json = stringBuilder.toString();
        scanner.close();

    }

   /*
    public void parseJson(String json){
        StringBuilder builder = new StringBuilder();
        try
        {
            JSONObject jsonobject = new JSONObject(json);
            JSONArray jsonarray = jsonobject.getJSONArray("amphur");
            for(int count=0;count<jsonarray.length();count++)
            {
                JSONObject amphure = jsonarray.getJSONObject(count);
                int amphur_id = Integer.parseInt(amphure.getString("AMPHUR_ID"));
                amphurID.add(amphur_id);

                int amphur_code = Integer.parseInt(amphure.getString("AMPHUR_CODE"));


                String amphure_name = amphure.getString("AMPHURE_NAME");
                amphurName.add(amphure_name);

                String postcode = amphure.getString("POSTCODE");
                postcodeList.add(postcode);

                int province_id = Integer.parseInt(amphure.getString("PROVINCE_ID"));
                provinceID.add(province_id);
            }

            System.out.println(builder.toString());
        }
        catch(JSONException ex)
        {
            ex.printStackTrace();
        }

    }
*/
    public void clickProvince(View v){
        getProvince(json);
    }

    public void clickDistrict(View v){
        getDistrict(json);
    }

    public void clickTumbon(View v){
        getTumbon(json);
    }

    public void getProvince(String json){
        this.adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice);

        try
        {
            JSONObject object = new JSONObject(json);
            JSONArray amphur = object.getJSONArray("amphur");
            JSONArray district = object.getJSONArray("district");
            JSONArray geography = object.getJSONArray("geography");
            JSONArray province = object.getJSONArray("province");
            for(int count=0;count<province.length();count++)
            {
                JSONObject obj = province.getJSONObject(count);
                String provincename = obj.getString("PROVINCE_NAME");
                int province_id = Integer.parseInt(obj.getString("PROVINCE_ID"));

                this.adapter.add(provincename);
                this.provincelist.add(new Province(provincename,province_id));

            }


            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("เลือกจังหวัด");

            dialog.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });



            dialog.setAdapter(this.adapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Toast.makeText(getApplicationContext(),"You selected "+which,Toast.LENGTH_LONG).show();
                    chooseProvince.setText(adapter.getItem(which));

                    //System.out.println(provincelist.get(which).id+"  "+provincelist.get(which).getName());
                    province_input = adapter.getItem(which);
                    province_key = provincelist.get(which).getId();


                }
            });

            dialog.show();
        }
        catch(JSONException ex)
        {
            ex.printStackTrace();
        }
    }

    public void getDistrict(String json){
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice);
        try
        {
            JSONObject object = new JSONObject(json);
            JSONArray amphur = object.getJSONArray("amphur");
            for(int count=0;count<amphur.length();count++)
            {
                JSONObject obj = amphur.getJSONObject(count);
                String a = obj.getString("AMPHUR_NAME");
                int id_amphur = Integer.parseInt(obj.getString("AMPHUR_ID"));
                int id_province = Integer.parseInt(obj.getString("PROVINCE_ID"));
                if(id_province == province_key)
                {
                    amphurList.add(new Amphur(a,id_amphur));
                    adapter.add(a);
                }
              //  System.out.println(obj.getString("AMPHUR_NAME"));

            }



            AlertDialog.Builder dialog = new AlertDialog.Builder(this);

            dialog.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

           // adapter.addAll(amphurName);

            dialog.setAdapter(adapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   String amphur =  adapter.getItem(which);

                   amphur_key = amphurList.get(which).getId();
                   chooseAmphur.setText(amphur);
                   amphur_input = adapter.getItem(which);
                }
            });
            dialog.show();

        }
        catch(JSONException jsonex)
        {
            jsonex.printStackTrace();
        }

    }

    public void getTumbon(String json){
        try {
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice);
            JSONObject object = new JSONObject(json);
            JSONArray amphur = object.getJSONArray("amphur");
            JSONArray district = object.getJSONArray("district");
            for(int count=0;count<district.length();count++)
            {
                JSONObject obj = district.getJSONObject(count);
                String a = obj.getString("DISTRICT_NAME");
                int id = Integer.parseInt(obj.getString("AMPHUR_ID"));

                if(amphur_key == id)
                {
                    tumbonlist.add(new Tumbon(a,id));
                    adapter.add(a);

                }

                System.out.println(a);

            }



            AlertDialog.Builder dialog = new AlertDialog.Builder(this);



            dialog.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });


            dialog.setAdapter(adapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    chooseTumbon.setText(adapter.getItem(which));

                   // System.out.println(tumbonlist.get(which).getId()+"  "+tumbonlist.get(which).getName());
                    tumbon_input = adapter.getItem(which);
                }
            });

            dialog.show();
        }
        catch(JSONException ex)
        {
            ex.printStackTrace();
        }
    }

    public void sendRequest(View v){
        checkMap = true;
        // ? ตัวคั่นระหว่าง url กับ address
        // example of address format = address=32+ถนนยิงเป้า+ตำบลสนามจันทร์+อำเภอเมือง+จังหวัดนครปฐม+73000
        // &key= ตามด้วย api key ของ google map
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=";
        url = url + houenumber_input + "+" + roadname_input + "+" + tumbon_input + "+" + amphur_input + "+" + province_input + "+" + postcode_input+"&key="+ apiKey;

        collectString = houenumber_input+" "+soi_input+" "+roadname_input+" "+tumbon_input+" "+amphur_input+" "+province_input+" "+postcode_input;
        System.out.println("Address User : "+collectString);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    System.out.println(" ### Geocoding ###");
                    JSONObject json = response;

                    JSONArray first = json.getJSONArray("results");
                    JSONObject second = first.getJSONObject(0);
                    JSONObject third = second.getJSONObject("geometry");
                    JSONObject forth = third.getJSONObject("location");
                     latitude = Double.parseDouble(forth.get("lat").toString());
                     longtitude = Double.parseDouble(forth.get("lng").toString());
                     System.out.println("LAT:"+latitude+"  "+"LONG:"+longtitude);
                     //updateCamera(latitude,longtitude);

                    System.out.println(latitude+"  "+longtitude);

                    Intent intent = new Intent(AddDorm.this,MapActivity_Full.class);
                    Bundle b = new Bundle();
                    String dormName = getIntent().getStringExtra("dormName");
                    b.putDouble("one",latitude);
                    b.putDouble("two",longtitude);
                    b.putString("address",collectString);
                    b.putString("dormName",dormName);

                    intent.putExtra("hippo",dormOW);
                    intent.putExtra("address",collectString);
                    intent.putExtra("dormName",dormName);
                    intent.putExtra("username",username);
                    intent.putExtra("password",password);
                    intent.putExtra("email",email);
                    // ID for reference DormOwner
                   // String dormOwnerID = getIntent().getStringExtra("dormOwnerID");

                    intent.putExtras(b);
                    startActivity(intent);
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

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;

        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMarkerDragListener(this);

        this.mMap.getUiSettings().setZoomControlsEnabled(true);
        this.mMap.getUiSettings().setAllGesturesEnabled(true);
        this.mMap.getUiSettings().setZoomGesturesEnabled(true);

        final LatLng thammasat = new LatLng(14.069487, 100.603292);

        System.out.println("CHECK MAP :"+checkMap);


        CameraPosition position = CameraPosition.builder().zoom(15).target(thammasat).build();

        MarkerOptions marker1 = new MarkerOptions();

        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.drawable.tulogo_cutbackground);
        int height = 100;
        int width = 100;

        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.tulogo_cutbackground);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        marker1.position(thammasat).title("มหาวิทยาลัยธรรมศาสตร์").icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        mMap.addMarker(marker1);

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position),1000,null);

        mMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                double lat = mMap.getCameraPosition().target.latitude;
                double longti = mMap.getCameraPosition().target.longitude;
            }
        });

        this.mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                double a = mMap.getCameraPosition().target.latitude;
                double b = mMap.getCameraPosition().target.longitude;
               // System.out.println(a+"   "+b);
            }
        });


    }

    public void updateCamera(double lat,double lng){
       // Toast.makeText(this,"INTO updateCamera fn \n"+lat+" "+lng,Toast.LENGTH_LONG).show();
          //  CameraPosition position = CameraPosition.builder().zoom(20).target(new LatLng(lat,lng)).build();
         //   mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
    }




    @Override
    public void onMapClick(LatLng latLng) {

        if(numberMarker <1)
        {

            MarkerOptions marker1 = new MarkerOptions().position(new LatLng(latLng.latitude,latLng.longitude)).title("This is position").snippet("My Dormitory").draggable(true).icon(BitmapDescriptorFactory.fromResource(R.drawable.dorm2));
            mMap.addMarker(marker1);
            numberMarker++;
        }
        else
        {
            Toast.makeText(this,"Can not add marker",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        double latitude = marker.getPosition().latitude;
        double longtitude = marker.getPosition().longitude;
        System.out.println(latitude+"   "+longtitude);
    }


}
