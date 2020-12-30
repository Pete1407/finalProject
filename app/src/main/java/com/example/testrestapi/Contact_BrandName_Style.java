package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Contact_BrandName_Style extends AppCompatActivity {
    public ActionBar bar;
    public TextView fullname;
    public TextView phoneNo;
    public TextView email;
    public String port = ":8080";
    public String ip = "192.168.43.57";
    public String dormID;
    public TextView address;
    public ImageView doc;
    public String pathDocument;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__brand_name__style);

        this.dormID = getIntent().getStringExtra("dormName");

        this.bar = getSupportActionBar();
        this.bar.setTitle(dormID);
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        this.fullname = (TextView) findViewById(R.id.fullname);
        this.phoneNo = (TextView) findViewById(R.id.phonenumber);
        this.email = (TextView) findViewById(R.id.email);
        this.address = (TextView) findViewById(R.id.address);
        this.doc = (ImageView) findViewById(R.id.doc);
        this.getInfoDormowner(dormID);
        this.getAddress(dormID);
        this.getDocumentImage(dormID);

        this.doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RequestQueue q = Volley.newRequestQueue(getApplicationContext());
                String path = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormID;
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String firstname = response.getString("name");
                            String lastname = response.getString("lastname");
                            String fullanme = firstname +" "+lastname;
                            String contact = response.getString("contact");
                            String email = response.getString("email");
                            String username = response.getString("username");


                            String pathaddress = "http://"+ip+port+"/dorm/getDormByDormName/"+dormID;
                            JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, pathaddress, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        String address = response.getString("address");

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                            q.add(request2);

                            String getDocument = "http://"+ip+port+"/documentImage/getpathImageByEmailAndUsername/"+email+"/"+username;
                            StringRequest docrequest = new StringRequest(Request.Method.GET, getDocument, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    pathDocument = response;
                                    Intent i = new Intent(Contact_BrandName_Style.this,Fullscreen_Image.class);
                                    i.putExtra("pathImage",pathDocument);
                                    startActivity(i);
                                    //Picasso.with(getApplicationContext()).load(pathDocument).into(doc);

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                            q.add(docrequest);

                        } catch (JSONException e) {
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
        });
    }

    public void getInfoDormowner(String dormName){
        String getInfo = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getInfo, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject obj = response;
                try
                {
                    String name = obj.getString("name");
                    String lastname = obj.getString("lastname");
                    String fullname_text = name+" "+lastname;
                    String contact = obj.getString("contact");
                    String email_text = obj.getString("email");
                    fullname.setText(fullname_text);
                    fullname.setTextSize(15);
                    phoneNo.setText(contact);
                    phoneNo.setTextSize(15);
                    email.setTextSize(15);
                    email.setText(email_text);
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

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void getAddress(String dormName){
        String getaddress = "http://"+ip+port+"/dorm/getDormByDormName/"+dormName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getaddress, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    String address_text = response.getString("address");
                    address.setText(address_text);
                    address.setTextSize(15);

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

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void getDocumentImage(final String dormName){
        final RequestQueue queue = Volley.newRequestQueue(this);
        String getdoc = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getdoc, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String username = response.getString("username");
                    String email = response.getString("email");
                    System.out.println(username+"  "+email);
                    String getDocumentImage = "http://"+ip+port+"/documentImage/getpathImageByEmailAndUsername/"+email+"/"+username;
                    StringRequest request2 = new StringRequest(Request.Method.GET, getDocumentImage, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String doctext = response;
                            Picasso.with(getApplicationContext()).load(doctext).into(doc);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    queue.add(request2);

                } catch (JSONException e) {
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
