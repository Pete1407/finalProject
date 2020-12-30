package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class Detail_Dorm_BrandName extends AppCompatActivity {
    public ActionBar bar;
    public ImageView imagee;
    public String dormID;
    public String pathImage360 = "";
    public String port = ":8080";
    public String ip = "192.168.43.57";
    public LinearLayout linear;
    public Button contactButton;
    public ImageView heart;
    public TextView nameDorm;
    public boolean statusFavorite = false;
    public int countFavorite = 0;
    public TextView signnumberfavorite;
    public String dormName;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public LinearLayout faclinear;
    public ImageView facimage;
    public TextView factext;
    public Facility[] fac;
    public LinearLayoutManager manager;
    public RecyclerView recylcerview;
    public ArrayList<Facility> list;
    public Button expenseButton;
    public Button placeButton;
    public Button travelButton;
    public Button commentButton;
    public Button galleryButton;
    public TextView description;

    public ArrayList<String> stack;
    public boolean statusPress = false;
    public String username;
    public int codeStyle;
    public int summaryLikeDorm = 0;
    public String pressFavorite;
    public int numberofLike = 0;
    public ArrayList<String> favlist = new ArrayList<>();
    public int langcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__dorm__brand_name);
        this.list = new ArrayList<>();
        String dormname = getIntent().getStringExtra("dormName");
        pressFavorite = getResources().getString(R.string.pressFavorite);
        this.dormID = getIntent().getStringExtra("dormName");
        sharedPreferences = getSharedPreferences("selectDorm",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        dormName = sharedPreferences.getString("dormname","no var");
        this.setcountFavorite(dormName);
        this.heart = (ImageView) findViewById(R.id.heart);

        stack = new ArrayList<>();

        SharedPreferences lang = getSharedPreferences("language", Context.MODE_PRIVATE);
        langcode = lang.getInt("languageCode",0);

        this.bar = getSupportActionBar();
        this.bar.setDisplayHomeAsUpEnabled(true);
        this.bar.setTitle(dormName);
        this.bar.setDisplayHomeAsUpEnabled(true);
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        this.imagee = (ImageView) findViewById(R.id.image);

        this.contactButton = (Button) findViewById(R.id.contact);
        this.linear = (LinearLayout) findViewById(R.id.interest);
        this.nameDorm = (TextView) findViewById(R.id.name);
        this.signnumberfavorite = (TextView) findViewById(R.id.numberofFavorite);
        this.recylcerview = (RecyclerView) findViewById(R.id.recycler);
        this.manager = new LinearLayoutManager(this);

        this.expenseButton = (Button) findViewById(R.id.expense);
        this.placeButton = (Button) findViewById(R.id.place);
        this.travelButton = (Button) findViewById(R.id.travel);
        this.commentButton = (Button) findViewById(R.id.comment);
        this.galleryButton = (Button) findViewById(R.id.gallery);
        this.description = (TextView) findViewById(R.id.description);

        this.getImage360(dormName);


        sharedPreferences = getSharedPreferences("file_pref", Context.MODE_PRIVATE);
        boolean f = sharedPreferences.getBoolean("statusSearch",false);
        username = sharedPreferences.getString("username","no value");
        codeStyle = sharedPreferences.getInt("dormStyleCode",4);
        this.setFavorite(username);
        if(f == true)
        {
            this.dormName = getIntent().getStringExtra("dormName");
            boolean a = false;
            this.nameDorm.setText(dormName);
            this.bar.setTitle(dormName);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("statusSearch",a);
            editor.commit();
        }

        this.getFacility(dormName);
        this.getDescription(dormName);

       // Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Fanwood.otf");
        this.nameDorm.setTypeface(Typeface.DEFAULT_BOLD);
        this.nameDorm.setTextSize(16);
        int black = getResources().getColor(R.color.black);
        this.nameDorm.setTextColor(black);
        this.nameDorm.setText(dormName);
        int red = getResources().getColor(R.color.red);
        this.signnumberfavorite.setTextColor(red);
        String text = summaryLikeDorm +" "+pressFavorite;
        this.signnumberfavorite.setText(text);
        final RequestQueue q = Volley.newRequestQueue(this);
        String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormID;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    String username = response.getString("username");
                    String email = response.getString("email");
                    String getImage360 = "http://"+ip+port+"/Image360/getImage360byUsernameAndEmail/"+username+"/"+email;
                    JsonObjectRequest image360 = new JsonObjectRequest(Request.Method.GET, getImage360, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try
                            {
                                String image = response.getString("image360");
                                System.out.println("Path Image360: "+image);
                                pathImage360 = image;
                                editor.putString("pathImage360",pathImage360);
                                editor.commit();

                                //String image_backup = sharedPreferences.getString("pathImage360",pathImage360);
                                //Picasso.with(getApplicationContext()).load(pathImage360).into(imagee);
                                Glide.with(getApplicationContext()).load(pathImage360).apply(bitmapTransform(new BlurTransformation(22))).into(imagee);

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
                    q.add(image360);

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
        q.add(request);


        this.imagee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String getUsernameAndEmail = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormName;
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try
                        {
                            String username = response.getString("username");
                            String email = response.getString("email");

                            String getImage360 = "http://"+ip+port+"/Image360/getImage360byUsernameAndEmail/"+username+"/"+email;
                            JsonObjectRequest image360 = new JsonObjectRequest(Request.Method.GET, getImage360, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try
                                    {
                                        String image = response.getString("image360");
                                        System.out.println("Path Image360: "+image);
                                        pathImage360 = image;
                                        Intent i = new Intent(Detail_Dorm_BrandName.this, FullScreen_Image360.class);
                                        i.putExtra("imagePath",pathImage360);
                                        startActivity(i);

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
                            queue.add(image360);
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


        });

    }

    public void setFavorite(String username){
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
                            stack.add(array.getString(count));
                            favlist.add(array.getString(count));
                        }

                        for(int count2=0;count2<favlist.size();count2++)
                        {
                            System.out.println("หอพักที่ชื่นชอบ: "+favlist.get(count2));
                        }
                    }
                    int value = 0;
                    for(int i=0;i<favlist.size();i++)
                    {
                        if(favlist.get(i).equals(dormName))
                        {
                            value++;
                            break;
                        }
                    }
                    if(value!=0)
                    {
                        heart.setImageResource(R.drawable.heart_press2);
                        statusPress = true;
                    }
                    else
                    {
                        heart.setImageResource(R.drawable.heart_notpress);
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
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void favorite(View v){
        if(statusFavorite == false)
        {
            //this.heart.setLayoutParams(new ViewGroup.LayoutParams());
            this.heart.setImageResource(R.drawable.heart_press2);
            statusFavorite = true;
        }
        else
        {
            statusFavorite = false;
            this.heart.setImageResource(R.drawable.heart_notpress);

        }

    }

    public void getContactPage(View v){
        Intent i = new Intent(this,Contact_BrandName_Style.class);
        i.putExtra("dormName",dormName);
        startActivity(i);
    }

    public void getFacility(String dormName){
         RequestQueue q = Volley.newRequestQueue(this);
         String getFacility = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+dormName;
         JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, getFacility, null, new Response.Listener<JSONObject>() {
             @Override
             public void onResponse(JSONObject response) {
                JSONObject object = response;
                 try
                 {
                     JSONArray textth = object.getJSONArray("nameTH");
                     //fac = new Facility[textth.length()];
                     JSONArray texten = object.getJSONArray("nameEN");
                     JSONArray image = object.getJSONArray("image");


                     for(int count=0;count<textth.length();count++)
                     {
                        Facility f = new Facility(textth.getString(count),texten.getString(count),image.getInt(count));
                        list.add(f);
                     }

                     String getfacInroom = "http://"+ip+port+"/facilityInRoom/getFacilityInRoom/"+dormName;
                     JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getfacInroom, null, new Response.Listener<JSONObject>() {
                         @Override
                         public void onResponse(JSONObject response) {
                             try
                             {
                                 JSONArray textthai = response.getJSONArray("nameTH");
                                 JSONArray texteng = response.getJSONArray("nameEN");
                                 JSONArray picture = response.getJSONArray("image");
                                 for(int count2=0;count2<textthai.length();count2++)
                                 {
                                     Facility ff = new Facility(textthai.getString(count2),texteng.getString(count2),picture.getInt(count2));
                                     list.add(ff);
                                 }

                                 RecyclerViewAdapter_Horizontal adapter = new RecyclerViewAdapter_Horizontal(getApplicationContext(),list,langcode);
                                 manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                 recylcerview.setLayoutManager(manager);
                                 recylcerview.setAdapter(adapter);
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
                     q.add(request2);




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
    }

    public void getDescription(String dormName){
        String descriptionPath = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dormName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, descriptionPath, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    String descriptionText = response.getString("description");
                    description.setText(descriptionText);

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

    public void getExpense(View v){
        Intent i = new Intent(Detail_Dorm_BrandName.this,Expense_BrandName_Style.class);
        i.putExtra("dormName",dormName);
        startActivity(i);
    }

    public void getPlace(View v){
        Intent i = new Intent(Detail_Dorm_BrandName.this,ImportantPlace_BrandName_Style.class);
        startActivity(i);
    }

    public void getTravel(View v){
        Intent i = new Intent(Detail_Dorm_BrandName.this,Travel_BrandName_Style.class);
        startActivity(i);
    }

    public void getComment(View v){
        Intent i = new Intent(Detail_Dorm_BrandName.this,CommentandScore_BrandName_Style.class);
        startActivity(i);
    }

    public void getGallery(View v){
        Intent i = new Intent(Detail_Dorm_BrandName.this,Gallery_BrandName_Style.class);
        startActivity(i);
    }

    public void pressFavorite(View v){
        // if อันนี้จะเช็คว่าถ้ามีหอพักที่เราไม่เคยกดถูกใจจะสามารถเข้า if นี้ได้ และกดถูกใจหอพักนี้ได้ หัวใจก็จะสามารถกดได้  if(suspenclick == false ) กับ if(statusPress == false)
        if(statusPress == false)
        {
            summaryLikeDorm = summaryLikeDorm + 1;
            statusPress = true;
            this.signnumberfavorite.setText(summaryLikeDorm+" "+pressFavorite);
            int red = getResources().getColor(R.color.red);
            this.signnumberfavorite.setTextColor(red);
            heart.setImageResource(R.drawable.heart_press2);
            // ฟังก์ชันสำหรับการเก็บชื่อหอพักที่ชื่นชอบ

            this.addFavorite(dormName);
        }
        else
        {
            summaryLikeDorm = summaryLikeDorm - 1;
            this.signnumberfavorite.setText(summaryLikeDorm+" "+pressFavorite);
            int red = getResources().getColor(R.color.red);
            this.signnumberfavorite.setTextColor(red);
            statusPress = false;
            heart.setImageResource(R.drawable.heart_notpress);
            this.cancelFavorite(dormName);
        }
    }

    public void setcountFavorite(final String dormName){
        RequestQueue queue = Volley.newRequestQueue(this);
        String path = "http://"+ip+port+"/favorite/getAllFavorite";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, path, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray array = response;
                // เซ็ตค่าเพื่อไปแยกว่าหอไหนมีผู้ใช้แต่ละคนกดถูกใจกี่คน
                displayArray(response,dormName);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

    public void addFavorite(String dormName){
        RequestQueue queue = Volley.newRequestQueue(this);

        stack.add(dormName);
        String saveFavorite = "http://"+ip+port+"/favorite/saveFavoriteDorm";
        JSONObject jsonobject = new JSONObject();
        JSONArray array = new JSONArray();
        try
        {
            jsonobject.put("username",username);
            jsonobject.put("codeStyle",codeStyle);

            // ยัด arraylist ที่เก็บชื่อของหอพักที่ผู้ใช้กดถูกใจใส่เพื่ออัพเดตลงฐานข้อมูล
            for(int count=0;count<stack.size();count++)
            {
                array.put(count,stack.get(count));
            }
            jsonobject.put("dormname",array);
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, saveFavorite, jsonobject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(req);

        }
        catch (JSONException ex){
            ex.printStackTrace();
        }

    }

    public void cancelFavorite(String dormName){
        RequestQueue queue = Volley.newRequestQueue(this);
        for(int count=0;count<stack.size();count++)
        {
            if(dormName.equals(stack.get(count)))
            {
                stack.remove(count);
            }
        }

        String saveFavorite = "http://"+ip+port+"/favorite/saveFavoriteDorm";
        JSONObject object = new JSONObject();
        try {
            object.put("username",username);
            object.put("codeStyle",codeStyle);
            JSONArray array = new JSONArray();
            for(int j=0;j<stack.size();j++)
            {
                array.put(j,stack.get(j));
            }
            object.put("dormname",array);

            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, saveFavorite, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(req);
        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
    }

    public void displayArray(JSONArray array,String dormName){
        JSONArray data = array;
        try
        {
            for(int count=0;count<data.length();count++)
            {
                JSONObject object = data.getJSONObject(count);
                JSONArray dormname = object.getJSONArray("dormname");
                for(int count2=0;count2<dormname.length();count2++)
                {
                    if(dormName.equals(dormname.getString(count2)))
                    {
                        numberofLike++;
                    }
                }
            }
            signnumberfavorite.setText(numberofLike+" "+pressFavorite);
            summaryLikeDorm = numberofLike;
        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
    }

    public void getImage360(String dormName){
        final RequestQueue q = Volley.newRequestQueue(this);

        String path = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String username = response.getString("username");
                    String email = response.getString("email");

                    String path2 = "http://"+ip+port+"/Image360/getImage360byUsernameAndEmail/"+username+"/"+email;

                    JsonObjectRequest req2 = new JsonObjectRequest(Request.Method.GET, path2, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String image360t = response.getString("image360");
                                editor.putString("pathImage360",image360t);
                                editor.commit();

                                Glide.with(getApplicationContext()).load(image360t).apply(bitmapTransform(new BlurTransformation(22))).skipMemoryCache(true).into(imagee);
                            }
                            catch(JSONException ex){
                                ex.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    q.add(req2);
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


}
