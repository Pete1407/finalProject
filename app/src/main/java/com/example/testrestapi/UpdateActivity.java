package com.example.testrestapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class UpdateActivity extends AppCompatActivity {
    public ActionBar bar;
    public ImageView CoverImage;
    private TextView update_infodormowner;
    private TextView nameDormowner;
    private TextView contactDormowner;

    private TextView update_facility;

    private TextView update_fac;
    private TextView update_expense;

    public InfoDorm infodorm;
    public String dormID;

    public ImageButton coverImageButton;
    public ImageButton image360Button;

    private static int DOCUMENT_CODE = 2;
    private static int COVERIMAGE_CODE = 0;
    private static int IMAGE360_CODE = 1;
    public static  int UPDATEGALLERY_CODE = 3;

    public String coverImageSelected;
    public String image360ImageSelected;

    public String firstname;
    public String lastname;
    public String nameDorm;
    public String fullname;
    public String contact;
    public String email;
    public String username;
    public String password;
    public String pathCoverImage;
    public String pathImage360;

    public TextView fullname_text;
    public TextView contact_text;
    public TextView email_text;

    public String ip = "192.168.43.57:8080";
    public ArrayList<String> facInRoom;
    public ArrayList<String> facInDorm;

    public TextView facInRoom_Text;
    public TextView facInDorm_Text;

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public String b_facInRoom = "";
    public String c_facInDorm = "";

    public TextView confirmCoverImage;
    public TextView confirmImage360;

    public Bitmap cov;
    public Bitmap img360;
    public Bitmap doc;
    public TextView configdetailStyle;
    public String emailupdate;
    public String statusupdate;
    public TextView updatedoc;
    public ImageButton documentUpdate;
    Bitmap b;

    public Toolbar toolbar;
    public TextView dormownername;
    public TextView contactdormowner;
    public TextView emaildormowner;
    public Button updategallery;
    public ProgressDialog progress;
    int picture_upload;

    public TextView title1;
    public TextView title2;
    public TextView title3;
    public TextView title4;
    public TextView title5;
    public TextView title6;

    public TextView result1;
    public TextView result2;
    public TextView result3;
    public TextView result4;
    public TextView result5;
    public TextView result6;
    public String unit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        unit = getResources().getString(R.string.baht);
        statusupdate = getResources().getString(R.string.updatenotify);
        sharedPreferences = getSharedPreferences("file_for_dormowner",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        this.username = sharedPreferences.getString("username","no var");
        this.password = sharedPreferences.getString("password","no var");
        this.emailupdate = sharedPreferences.getString("email","no email");
        //this.username = getIntent().getStringExtra("username");
        //this.password = getIntent().getStringExtra("password");

        //Toast.makeText(this,username+"  "+password,Toast.LENGTH_SHORT).show();
        System.out.println("SHARED USERNAME: "+username);
        System.out.println("SHARED PASSWORD: "+password);
        getDormOwner();


        this.facInRoom = new ArrayList<>();
        this.facInDorm = new ArrayList<>();

        this.toolbar =  (Toolbar) findViewById(R.id.toolbar);
        this.toolbar.setBackground(new ColorDrawable(Color.parseColor("#DC143C")));
        this.setSupportActionBar(toolbar);
        //this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));
        //this.bar.setTitle("หน้าหลักเจ้าของหอพัก");
        //this.bar.setDisplayHomeAsUpEnabled(true);

        this.updatedoc = (TextView) findViewById(R.id.updatedoc);
        this.updatedoc.setEnabled(false);
        int gray = getResources().getColor(R.color.gray);
        this.updatedoc.setTextColor(gray);
        this.documentUpdate = (ImageButton) findViewById(R.id.documentUpdate);
        this.confirmCoverImage = (TextView) findViewById(R.id.confirm_coverImage);
        this.confirmCoverImage.setEnabled(false);
        this.confirmCoverImage.setTextColor(gray);
        this.confirmImage360 = (TextView) findViewById(R.id.confirm_image360);
        this.confirmImage360.setEnabled(false);
        this.confirmImage360.setTextColor(gray);
        this.facInRoom_Text = (TextView) findViewById(R.id.facInRoom_Text);
        this.facInDorm_Text = (TextView) findViewById(R.id.facInDorm_Text);
        this.CoverImage = (ImageView) findViewById(R.id.coverDisplay);
        this.update_infodormowner = (TextView) findViewById(R.id.update_infodormowner);
        this.nameDormowner = (TextView) findViewById(R.id.name_dormowner);
        this.update_facility = (TextView) findViewById(R.id.update_facility);
        this.contactDormowner = (TextView) findViewById(R.id.contact_dormowner);
        this.update_expense = (TextView) findViewById(R.id.update_expense);
        this.dormownername = (TextView) findViewById(R.id.name_dormowner);
        this.contactdormowner = (TextView) findViewById(R.id.contact_dormowner);
        this.emaildormowner = (TextView) findViewById(R.id.email_dormowner);
        this.updategallery = (Button) findViewById(R.id.updategallery);
        this.configdetailStyle = (TextView) findViewById(R.id.configdetailStyle);

        title1 = (TextView) findViewById(R.id.textView85);
        result1 = (TextView) findViewById(R.id.textView86);
        title2 = (TextView) findViewById(R.id.textView83);
        result2 = (TextView) findViewById(R.id.textView84);
        title3 = (TextView) findViewById(R.id.waterprice);
        result3 = (TextView) findViewById(R.id.waternumber);
        title4 = (TextView) findViewById(R.id.classprice);
        result4 = (TextView) findViewById(R.id.classnumber);
        title5 = (TextView) findViewById(R.id.electroprice);
        result5 = (TextView) findViewById(R.id.electronumber);
        title6 = (TextView) findViewById(R.id.depositprice);
        result6 = (TextView) findViewById(R.id.depositnumber);

        Resources res = getResources();

        String roomprice = res.getString(R.string.class_payment);
        String waterprice = res.getString(R.string.water_payment);
        String electroprice = res.getString(R.string.electro_payment);
        String depositprice = res.getString(R.string.deposit);
        String commonfee = res.getString(R.string.middle_payment);
        String description = res.getString(R.string.description);

        title1.setText(roomprice);
        title2.setText(waterprice);
        title3.setText(electroprice);
        title4.setText(depositprice);
        title5.setText(commonfee);
        title6.setText(description);

        title1.setTypeface(Typeface.DEFAULT_BOLD);
        title2.setTypeface(Typeface.DEFAULT_BOLD);
        title3.setTypeface(Typeface.DEFAULT_BOLD);
        title4.setTypeface(Typeface.DEFAULT_BOLD);
        title5.setTypeface(Typeface.DEFAULT_BOLD);
        title6.setTypeface(Typeface.DEFAULT_BOLD);

        this.update_facility.setPaintFlags(update_facility.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);

        this.update_expense.setPaintFlags(update_expense.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        this.configdetailStyle.setPaintFlags(configdetailStyle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        this.update_infodormowner.setPaintFlags(update_infodormowner.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        this.coverImageButton = (ImageButton) findViewById(R.id.CoverImage);
        this.image360Button = (ImageButton) findViewById(R.id.image360);

        this.fullname_text = (TextView) findViewById(R.id.fullname_text);
        this.contact_text = (TextView) findViewById(R.id.contact_text);
        this.email_text = (TextView) findViewById(R.id.email_text);


        Picasso.with(this).setLoggingEnabled(true);


        int colortext = getResources().getColor(R.color.colortext);
        this.update_infodormowner.setTextColor(colortext);
        this.update_facility.setTextColor(colortext);
        this.update_expense.setTextColor(colortext);
        this.configdetailStyle.setTextColor(colortext);



        this.fullname_text.setText(fullname);
        this.contact_text.setText(contact);
        this.email_text.setText(emailupdate);


        picture_upload = getResources().getColor(R.color.picture_text);
        //this.confirmCoverImage.setTextColor(picture_upload);
        //this.confirmImage360.setTextColor(picture_upload);
        //this.updatedoc.setTextColor(picture_upload);




        this.update_infodormowner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("Update");
                Intent intent = new Intent(UpdateActivity.this,Update_info_Activity.class);
                intent.putExtra("email",emailupdate);
                intent.putExtra("nameDorm",nameDorm);
                startActivity(intent);
            }
        });

        this.update_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this,Update_ExpenseActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("password",password);
                intent.putExtra("nameDorm",nameDorm);
                startActivity(intent);
            }
        });


        this.update_facility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this,Update_Facility_Activity.class);
                intent.putExtra("username",username);
                intent.putExtra("password",password);
                intent.putExtra("dormName",nameDorm);
                startActivity(intent);
            }
        });

        this.documentUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.setType("image/*");
                startActivityForResult(i,DOCUMENT_CODE);
            }
        });

        this.coverImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent,COVERIMAGE_CODE);
            }
        });

        this.image360Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent,IMAGE360_CODE);
            }
        });

        this.updategallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(UpdateActivity.this,UpdateGallery.class);
               startActivity(intent);
            }
        });
    }

    public void getDormOwner(){
        String pathgetDormOwner = "http://"+ip+"/dormOwner/checkdormowner/"+username+"/"+password;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, pathgetDormOwner, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    JSONObject object = response;
                    //System.out.println("OBJECT SEARCH DORM OWNER: "+object);

                try
                {
                    nameDorm = object.getString("nameDorm");
                    toolbar.setTitle(nameDorm);
                    int blacktitle = getResources().getColor(android.R.color.white);
                    toolbar.setTitleTextColor(blacktitle);
                    username = object.getString("username");
                    password = object.getString("password");
                    firstname = object.getString("name");
                    lastname = object.getString("lastname");
                    fullname_text.setText(firstname+" "+lastname);

                    contact = object.getString("contact");
                    contact_text.setText(contact);
                    email = object.getString("email");
                    email_text.setText(email);

                    //System.out.println("NAME DORM: "+nameDorm);
                    //System.out.println("USERNAME: "+username);
                    //System.out.println("PASSWORD: "+password);
                    //System.out.println("FIRSTNAME: "+firstname);
                    //System.out.println("LASTNAME: "+lastname);
                    //System.out.println("CONTACT: "+contact);
                    //System.out.println("EMAIL: "+email);



                    getFacilityInRoom();
                    getFacilityInDorm();
                    getDocument();
                    getCoverImage();
                    getImage360();
                    getExpense(nameDorm);

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

    public void getExpense(String dormname){
            RequestQueue queue = Volley.newRequestQueue(this);
            String path1 = "http://"+ip+"/InfoDorm/getInfoDormByDormName/"+dormname;
            JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, path1, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String minprice = String.valueOf(response.getString("minPrice"));
                        String maxprice = String.valueOf(response.getString("maxPrice"));
                        String typewater = response.getString("typeWater");
                        String waterprice = String.valueOf(response.getInt("priceWater"));
                        String typeelectro = response.getString("typeElectro");
                        String electroprice = String.valueOf(response.getInt("priceElectro"));
                        String typedeposit = response.getString("typeDeposit");
                        String depositprice = String.valueOf(response.getInt("depositPrice"));
                        String typecommmonfee = response.getString("typeCommonFee");
                        String commonfee = String.valueOf(response.getInt("commonFee"));
                        String description = response.getString("description");


                        result1.setText(minprice+" - "+maxprice+" "+unit);
                        result2.setText(waterprice+" "+unit+"("+typewater+")");

                        String[] electroBill = getResources().getStringArray(R.array.electroBill);
                        String[] deposit = getResources().getStringArray(R.array.depositChoice);
                        String[] middle  = getResources().getStringArray(R.array.middle_Choice);

                        // set ค่าไฟฟ้า
                        if(typeelectro.equals(electroBill[1]))
                        {
                            result3.setText(electroprice+" "+unit+"("+typeelectro+")");
                        }
                        else if(typeelectro.equals(electroBill[2]))
                        {
                            result3.setText(typeelectro);
                        }

                        // set ค่ามัดจำ
                        if(typedeposit.equals(deposit[0]))
                        {
                            result4.setText(typedeposit);
                        }
                        else
                        {
                            result4.setText(depositprice+" "+unit+"("+typedeposit+")");
                        }

                        if(typecommmonfee.equals(middle[0]))
                        {
                            result5.setText(typecommmonfee);
                        }
                        else
                        {
                            result5.setText(commonfee+" "+unit+"("+typecommmonfee+")");
                        }

                        result6.setText(description);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(request1);
    }

    public void UpdateDetailStyle(View v){
       final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String gettypeDorm = "http://"+ip+"/DormTypePriceNPromotion/checkDorm/"+nameDorm;
        System.out.println("NAME DORM: "+nameDorm);
        StringRequest request1 = new StringRequest(Request.Method.GET, gettypeDorm, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                final String res = response;
                String restext = "";
                String output = "";
                StringTokenizer token = new StringTokenizer(res,",");
                while(token.hasMoreTokens())
                {
                    restext = token.nextToken();
                    output = token.nextToken();
                }
               final String output2 = output;
                if(restext.equals("success"))
                {
                    String checkPromotion = "http://"+ip+"/DormTypePriceNPromotion/getDormBydormName/"+nameDorm;
                    JsonObjectRequest getpromotion = new JsonObjectRequest(Request.Method.GET, checkPromotion, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONObject object = response;
                            try {
                                String promotionDetail = object.getString("promotionDescribe");
                                int oldPrice = object.getInt("oldPrice");
                                int newPrice = object.getInt("newPrice");

                                Intent intent = new Intent(UpdateActivity.this,UpdateDetail_Style.class);
                                intent.putExtra("dormname",output2);
                                intent.putExtra("codeStyle",0);
                                intent.putExtra("promotionDetail",promotionDetail);
                                intent.putExtra("oldPrice",oldPrice);
                                intent.putExtra("newPrice",newPrice);
                                startActivity(intent);
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
                    queue.add(getpromotion);

                }
                else
                {
                    System.out.println("NOT FOUND IN TYPE PRICE AND PROMOTION");
                    String checkDormInformation = "http://"+ip+"/DormTypeInformation/checkDorm/"+nameDorm;
                    StringRequest checkrequest = new StringRequest(Request.Method.GET, checkDormInformation, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String responsedata = response;
                            String restext = "";
                            String output = "";
                            StringTokenizer token = new StringTokenizer(responsedata,",");
                            while(token.hasMoreTokens())
                            {
                                restext = token.nextToken();
                                output = token.nextToken();
                            }
                           final String aa = output;
                            if(restext.equals("success"))
                            {
                                String getoldDetail = "http://"+ip+"/DormTypeInformation/getDorm/"+nameDorm;
                                JsonObjectRequest olddetailRequest = new JsonObjectRequest(Request.Method.GET, getoldDetail, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                         JSONObject obj = response;
                                        try {
                                            String infodetail = obj.getString("detail");
                                            System.out.println("INFODETAIL: "+infodetail);
                                            Intent intent = new Intent(UpdateActivity.this,UpdateDetail_Style.class);
                                            intent.putExtra("dormname",aa);
                                            intent.putExtra("codeStyle",1);
                                            intent.putExtra("infodetail",infodetail);
                                            startActivity(intent);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                                queue.add(olddetailRequest);


                            }
                            else
                            {
                                System.out.println("NOT FOUND IN TYPE INFORMATION");
                                String checkQuality = "http://"+ip+"/DormTypeQuality/checkDorm/"+nameDorm;
                                StringRequest quarequest = new StringRequest(Request.Method.GET, checkQuality, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        String responsedata = response;
                                        String restext = "";
                                        String output = "";
                                        StringTokenizer token = new StringTokenizer(responsedata,",");
                                        while(token.hasMoreTokens())
                                        {
                                            restext = token.nextToken();
                                            output = token.nextToken();
                                        }
                                       final String uu = output;
                                        if(restext.equals("success"))
                                        {
                                            String getQuality = "http://"+ip+"/DormTypeQuality/getPremiumBydormName/"+nameDorm;
                                            JsonObjectRequest qualityrequest = new JsonObjectRequest(Request.Method.GET, getQuality, null, new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    JSONObject obj = response;
                                                    try {
                                                        JSONArray imagelist = obj.getJSONArray("imagelist");
                                                        JSONArray thlist = obj.getJSONArray("premieum_th");
                                                        JSONArray enlist = obj.getJSONArray("premieum_en");
                                                        ArrayList<Integer> image = new ArrayList<>();
                                                        ArrayList<String> th = new ArrayList<>();
                                                        ArrayList<String> en = new ArrayList<>();

                                                        // ยัดข้อมูลที่ได้จาก database ใส่ไปเพื่อส่งไปให้คลาส UpdateDetail แล้วส่งไปให้ Fragment style ต่อไป
                                                        for(int count=0;count<imagelist.length();count++)
                                                        {
                                                            image.add(imagelist.getInt(count));
                                                        }

                                                        for(int count2=0;count2<thlist.length();count2++)
                                                        {
                                                            th.add(thlist.getString(count2));
                                                        }

                                                        for(int count3=0;count3<enlist.length();count3++)
                                                        {
                                                            en.add(enlist.getString(count3));
                                                        }


                                                        Intent intent = new Intent(UpdateActivity.this,UpdateDetail_Style.class);
                                                        intent.putExtra("dormname",uu);
                                                        intent.putExtra("codeStyle",2);
                                                        intent.putIntegerArrayListExtra("imagelist",image);
                                                        intent.putStringArrayListExtra("thlist",th);
                                                        intent.putStringArrayListExtra("enlist",en);
                                                        startActivity(intent);
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }


                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            });
                                            queue.add(qualityrequest);

                                        }
                                        else
                                        {
                                            System.out.println("NOT FOUND IN TYPE QUALITY");
                                            String checkBrand = "http://"+ip+"/DormTypeBrandName/checkDorm/"+nameDorm;
                                            StringRequest brandrequest = new StringRequest(Request.Method.GET, checkBrand, new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    String responsedata = response;
                                                    String restext = "";
                                                    String output = "";
                                                    StringTokenizer token = new StringTokenizer(responsedata,",");
                                                    while(token.hasMoreTokens())
                                                    {
                                                        restext = token.nextToken();
                                                        output = token.nextToken();
                                                    }
                                                   final String rr = output;
                                                    if(restext.equals("success"))
                                                    {
                                                        System.out.println("FOUND IN TYPE BRANDNAME");

                                                        String getpathlogo = "http://"+ip+"/dormOwner/getInfoDormOwnerByDormName/"+nameDorm;
                                                        JsonObjectRequest logorequest = new JsonObjectRequest(Request.Method.GET, getpathlogo, null, new Response.Listener<JSONObject>() {
                                                            @Override
                                                            public void onResponse(JSONObject response) {
                                                                    JSONObject obj = response;
                                                                try
                                                                {
                                                                   final String id = obj.getString("username");
                                                                   final String email = obj.getString("email");

                                                                    String getlogorequest = "http://"+ip+"/LogoBrandName/getPathLogoBrand/"+id+"/"+email;
                                                                    JsonObjectRequest logoo = new JsonObjectRequest(Request.Method.GET, getlogorequest, null, new Response.Listener<JSONObject>() {
                                                                        @Override
                                                                        public void onResponse(JSONObject response) {
                                                                          try {
                                                                              String pathlogo = response.getString("logo");
                                                                              Intent intent = new Intent(UpdateActivity.this,UpdateDetail_Style.class);
                                                                              intent.putExtra("dormname",rr);
                                                                              intent.putExtra("username",id);
                                                                              intent.putExtra("email",email);
                                                                              intent.putExtra("pathlogo",pathlogo);
                                                                              intent.putExtra("codeStyle",3);
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
                                                                    queue.add(logoo);
                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }
                                                        }, new Response.ErrorListener() {
                                                            @Override
                                                            public void onErrorResponse(VolleyError error) {

                                                            }
                                                        });
                                                        queue.add(logorequest);

                                                    }
                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            });
                                            queue.add(brandrequest);
                                        }

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                                queue.add(quarequest);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(checkrequest);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == COVERIMAGE_CODE && resultCode == Activity.RESULT_OK && data!=null)
        {
            try
            {
                confirmCoverImage.setEnabled(true);
                confirmCoverImage.setTextColor(picture_upload);
                confirmCoverImage.setPaintFlags(confirmCoverImage.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                Uri imageSelect = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(imageSelect,filePathColumn,null,null,null);
                cursor.moveToFirst();
                int position = cursor.getColumnIndex(filePathColumn[0]);
                coverImageSelected = cursor.getString(position);

                cov = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageSelect));
                coverImageButton.setImageBitmap(cov);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(requestCode == IMAGE360_CODE && resultCode == Activity.RESULT_OK && data!=null)
        {
            try
            {
                confirmImage360.setEnabled(true);
                confirmImage360.setTextColor(picture_upload);
                confirmImage360.setPaintFlags(confirmImage360.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                Uri imageSelect = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor =getContentResolver().query(imageSelect,filePathColumn,null,null,null);
                cursor.moveToFirst();
                int position = cursor.getColumnIndex(filePathColumn[0]);
                image360ImageSelected = cursor.getColumnName(position);

                img360 = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageSelect));
                int w = img360.getWidth();//get width
                int h = img360.getHeight();//get height
                int aspRat = w / h;//get aspect ratio
                int W = 256;
                int H = w * aspRat;

                 //b = Bitmap.createScaledBitmap(img360, W, H, false);//scale the bitmap
                Glide.with(getApplicationContext()).load(img360).into(image360Button);
                //image360Button.setImageBitmap(b);//set the image view
                //img360 = null;
                //image360Button.setImageBitmap(img360);

            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        else if(requestCode == DOCUMENT_CODE && resultCode == Activity.RESULT_OK && data!=null)
        {
            updatedoc.setEnabled(true);
            updatedoc.setTextColor(picture_upload);
            updatedoc.setPaintFlags(updatedoc.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            Uri imageSelect = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor =getContentResolver().query(imageSelect,filePathColumn,null,null,null);
            cursor.moveToFirst();
            int position = cursor.getColumnIndex(filePathColumn[0]);

            try
            {
                doc = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageSelect));
                documentUpdate.setImageBitmap(doc);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void getFacilityInRoom(){
        String pathGetFacilityInRoom = "http://"+ip+"/facilityInRoom/getFacilityInRoom/"+nameDorm;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, pathGetFacilityInRoom, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject facility = response;
                System.out.println(facility);
                try
                {
                    JSONArray array_th = facility.getJSONArray("nameTH");
                    for(int count=0;count<array_th.length();count++)
                    {
                        String a = array_th.getString(count);
                        facInRoom.add(a);

                    }

                    for(int count2=0;count2<facInRoom.size();count2++)
                    {
                        b_facInRoom = b_facInRoom + facInRoom.get(count2)+" ";
                    }


                    facInRoom_Text.setText(b_facInRoom);


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

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void getFacilityInDorm(){
        String pathGetFacilityInDorm = "http://"+ip+"/facilityInDorm/getFacilityInDorm/"+nameDorm;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, pathGetFacilityInDorm, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject facility = response;
                //System.out.println(facility);

                try
                {
                    JSONArray array_th = facility.getJSONArray("nameTH");
                    for(int count=0;count<array_th.length();count++)
                    {
                        String a = array_th.getString(count);
                        facInDorm.add(a);

                    }

                    for(int count2=0;count2<facInDorm.size();count2++)
                    {
                        c_facInDorm = c_facInDorm + facInDorm.get(count2)+" ";
                    }


                    facInDorm_Text.setText(c_facInDorm);


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

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void getDocument(){
        String updatedocu = "http://"+ip+"/documentImage/getpathImageByEmailAndUsername/"+email+"/"+username;
        StringRequest docrequest = new StringRequest(Request.Method.GET, updatedocu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Picasso.with(getApplicationContext()).load(response).into(documentUpdate);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(docrequest);
    }

    public void getCoverImage(){
        String pathgetCoverImage = "http://"+ip+"/CoverImage/getCoverImageByusernameAndEmail/"+username+"/"+email;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, pathgetCoverImage, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject object = response;
                try
                {
                    String path = object.getString("coverImage");
                    Picasso.with(getApplicationContext()).load(path).into(coverImageButton);
                    coverImageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Picasso.with(getApplicationContext()).load(path).into(CoverImage);
                    CoverImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //System.out.println(object);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void getImage360(){
        String pathgetImage360 = "http://"+ip+"/Image360/getImage360byUsernameAndEmail/"+username+"/"+email;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, pathgetImage360, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject object = response;
                try
                {
                    String path = object.getString("image360");
                    Glide.with(getApplicationContext()).load(path).into(image360Button);
                    //Picasso.with(getApplicationContext()).load(path).into(image360Button);
                    image360Button.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(object);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void updateDocument(View v){
        String updatedoc = "http://"+ip+"/documentImage/updateDocument";

        VolleyMultipartRequest request = new VolleyMultipartRequest(Request.Method.POST, updatedoc, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                Toast.makeText(getApplicationContext(),statusupdate,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("username", username);
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() throws AuthFailureError {
                Map<String, DataPart> param = new HashMap<>();
                long imagename = System.currentTimeMillis();
                param.put("file", new DataPart("Document_" + imagename + ".png", getFileDataFromDrawable(doc)));

                return param;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void updateCoverImage(View v){
        String pathUpdateCoverImage = "http://"+ip+"/CoverImage/updateCoverImage/"+email;

        VolleyMultipartRequest request = new VolleyMultipartRequest(Request.Method.POST, pathUpdateCoverImage, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                Toast.makeText(getApplicationContext(),statusupdate,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateActivity.this,UpdateActivity.class);
                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("username", username);
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() throws AuthFailureError {
                Map<String, DataPart> param = new HashMap<>();
                long imagename = System.currentTimeMillis();
                param.put("file", new DataPart("CoverImage_" + imagename + ".png", getFileDataFromDrawable(cov)));

                return param;
            }

        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }

    public void updateImage360(View v){
       String pathUpdateImage360 = "http://"+ip+"/Image360/updateImage360/"+email;

       VolleyMultipartRequest request = new VolleyMultipartRequest(Request.Method.POST, pathUpdateImage360, new Response.Listener<NetworkResponse>() {
           @Override
           public void onResponse(NetworkResponse response) {
                Toast.makeText(getApplicationContext(),statusupdate,Toast.LENGTH_SHORT).show();
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       })
       {
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String> param = new HashMap<>();
               param.put("email",email);
               param.put("username",username);
               return param;
           }

           @Override
           protected Map<String, DataPart> getByteData() throws AuthFailureError {
               Map<String,DataPart> param = new HashMap<>();
               long imagename = System.currentTimeMillis();
               param.put("file",new DataPart("Image360_"+imagename+".png",getFileDataFromDrawable(img360)));
               return param;
           }
       };

       RequestQueue queue = Volley.newRequestQueue(this);
       queue.add(request);
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }




}





