package com.example.testrestapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class MapActivity_Full extends AppCompatActivity implements OnMapReadyCallback , GoogleMap.OnMapClickListener, GoogleMap.OnMarkerDragListener {

    public ActionBar bar;

    public GoogleMap mMap;
    public int numberMarker = 0;

    public double latitude = 0;
    public double longtitude = 0;
    public String addressFromAddDorm;
    public boolean checkMap = false;

    public String ip = "192.168.43.57:8080/dorm";
    public String url = "http://"+ip;

    public String dormOW;
    public LatLng dorm;

    public String username;
    public String password;
    public String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map__full);

        this.dormOW = getIntent().getStringExtra("hippo");
        this.username = getIntent().getStringExtra("username");
        this.password = getIntent().getStringExtra("password");
        this.email = getIntent().getStringExtra("email");

        System.out.println(this.dormOW+" ))))))))))))))))))))))))))))))))))))");

        this.bar = getSupportActionBar();
        this.bar.setTitle("ระบุตำแหน่งหอพัก");
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        mapFragment.getMapAsync(this);

       Bundle b = getIntent().getExtras();
       this.latitude = b.getDouble("one");
       this.longtitude = b.getDouble("two");
       this.addressFromAddDorm = b.getString("address");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;

        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMarkerDragListener(this);

        this.mMap.getUiSettings().setZoomControlsEnabled(true);
        this.mMap.getUiSettings().setAllGesturesEnabled(true);
        this.mMap.getUiSettings().setZoomGesturesEnabled(true);


         dorm = new LatLng(latitude,longtitude);
        System.out.println("CHECK MAP :"+checkMap);

        CameraPosition position = CameraPosition.builder().target(dorm).zoom(15).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));

    /*
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
    */
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


    @Override
    public void onMapClick(LatLng latLng) {

        if(numberMarker <1)
        {

            MarkerOptions marker1 = new MarkerOptions().position(new LatLng(latLng.latitude,latLng.longitude)).title("This is position").snippet("My Dormitory").draggable(true).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            mMap.addMarker(marker1);
            this.latitude = marker1.getPosition().latitude;
            this.longtitude = marker1.getPosition().longitude;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.confirm_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.confirm :
                url = url + "/addDorm";
                // ข้อมูลที่รวมไว้เพื่อเก็บลง collection ของ Dorm

               double latitude = this.latitude;
               double longtitude = this.longtitude;
               String address = getIntent().getStringExtra("address");
               String dormOwnerID = getIntent().getStringExtra("hippo");
               String dormName = getIntent().getStringExtra("dormName");

                Toast.makeText(this,"บันทึกสำเร็จ",Toast.LENGTH_LONG);
               try {
                   JSONObject object = new JSONObject();

                   object.put("dormID",dormName);
                   object.put("dormownerID",dormOW);
                   object.put("address",address);
                   object.put("latitude",latitude);
                   object.put("longtitude",longtitude);

                   JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                       @Override
                       public void onResponse(JSONObject response) {

                       }
                   }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {

                       }
                   });

                   RequestQueue queue = Volley.newRequestQueue(this);
                   queue.add(request);
               }
               catch(JSONException ex){
                   ex.printStackTrace();
               }



               // JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,);
                // ส่ง ชื่อหอพักที่เป็น DOrmID ไปให้เพื่อระบุว่าหอไหนมี facility อะไรบ้าง
                Intent intent = new Intent(MapActivity_Full.this,Facility_Activity.class);
                intent.putExtra("dormID",dormName);
                intent.putExtra("username",username);
                intent.putExtra("password",password);
                intent.putExtra("email",email);
                startActivity(intent);
        }
        return true;
    }
}
