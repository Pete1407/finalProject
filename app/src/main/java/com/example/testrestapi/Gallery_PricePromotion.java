package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Gallery_PricePromotion extends AppCompatActivity {
    public Gallery_PricePromotion_Adapter adapter;
    public GridView grid;
    public String port = ":8080";
    public String ip = "192.168.43.57";
    public String dormName;
    public ArrayList<String> pathlist;
    public ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery__price_promotion);
        this.pathlist = new ArrayList<>();
        this.grid = (GridView) findViewById(R.id.gridshowimage);

        this.bar = getSupportActionBar();
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));
        String a = getResources().getString(R.string.etc_photo);
        this.bar.setTitle(a);
        this.bar.setDisplayHomeAsUpEnabled(true);

       SharedPreferences sharedPreferences = getSharedPreferences("selectDorm",MODE_PRIVATE);
        this.dormName = sharedPreferences.getString("dormname","no value");

        final RequestQueue q = Volley.newRequestQueue(this);
        String path = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    String username = response.getString("username");
                    String email = response.getString("email");

                    String path = "http://"+ip+port+"/GalleryImage/getGalleryImage/"+email;
                    JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONObject obj = response;
                            try
                            {
                                JSONArray array = obj.getJSONArray("images");
                                for(int count=0;count<array.length();count++)
                                {
                                    String url = array.getString(count);
                                    pathlist.add(url);
                                }

                                adapter = new Gallery_PricePromotion_Adapter(getApplicationContext(),pathlist);
                                grid.setAdapter(adapter);
                                grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent i = new Intent(Gallery_PricePromotion.this,Fullscreen_Image.class);
                                        i.putExtra("pathImage",pathlist.get(position));
                                        startActivity(i);
                                    }
                                });
                            }
                            catch (JSONException ex){

                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    q.add(request2);
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
        q.add(request);

    }
}
