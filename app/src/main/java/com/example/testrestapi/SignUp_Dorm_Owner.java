package com.example.testrestapi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import java.util.HashMap;
import java.util.Map;



public class SignUp_Dorm_Owner extends AppCompatActivity {
    public ActionBar actionbar;

    // ******  Part of Dorm Owner *****
    public EditText nameDorm;
    public EditText name_dorm_owner;
    public EditText lastname_dorm_owner;
    public EditText phone_dorm_owner;
    public EditText email_dorm_owner;
    public EditText username;
    public EditText password;
    private static final int PICK_FROM_GALLERY = 1;

    public int roleID;
    public String name_dorm;
    public String firstname;
    public String lastname;
    public String contactPhone;
    public String email;
    public String usernamee;
    public String passwordd;
    public String name_doc;

    public String dormOwnerID = "";

    // ***** Document for Dorm Business *****
    public ImageButton uploadImage;

    public Button cancel;
    public Button addDorm;



    public String ip = "192.168.43.57:8080";
    public String url = "http://"+ip;
    public JSONObject object;
    public JSONObject object2;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Bitmap bitmap;
    public String jsonBody;
    public JSONObject oj;
    public Bitmap composite;


    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up__dorm__owner);

        System.out.println("Role ID: "+this.roleID);

        this.actionbar = getSupportActionBar();
        this.actionbar.setTitle("สำหรับเจ้าของหอพัก");
        this.actionbar.setDisplayHomeAsUpEnabled(true);
        this.actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));

        this.nameDorm = (EditText)findViewById(R.id.name_dorm);
        this.name_dorm_owner = (EditText) findViewById(R.id.name_dorm_owner);
        this.lastname_dorm_owner = (EditText) findViewById(R.id.lastname_dorm_owner);
        this.phone_dorm_owner = (EditText) findViewById(R.id.phone_dorm_owner);
        this.email_dorm_owner = (EditText) findViewById(R.id.email_dorm_owner);
        this.username = (EditText) findViewById(R.id.username_user);
        this.password = (EditText) findViewById(R.id.password_user);


        this.uploadImage = (ImageButton) findViewById(R.id.uploadDocument);

        this.cancel = (Button) findViewById(R.id.expense);
        this.addDorm = (Button) findViewById(R.id.addDorm);

        this.uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (ActivityCompat.checkSelfPermission(SignUp_Dorm_Owner.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(SignUp_Dorm_Owner.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    } else {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(intent,0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });

    }

    // Store data DormOwner
    public void addDetailDorm(View v){

        // Upload Document Image


        url = url +"/dormOwner/addDormOwner";
        this.roleID = getIntent().getIntExtra("role",0);
        this.name_dorm = this.nameDorm.getText().toString();
        this.firstname = this.name_dorm_owner.getText().toString();
        this.lastname = this.lastname_dorm_owner.getText().toString();
        this.contactPhone = this.phone_dorm_owner.getText().toString();
        this.email = this.email_dorm_owner.getText().toString();
        this.usernamee = this.username.getText().toString();
        this.passwordd = this.password.getText().toString();


        try
        {
            object = new JSONObject();
            String sendAddDorm = this.firstname+" "+this.lastname;
            dormOwnerID = sendAddDorm;

            object.put("id",this.usernamee);
            object.put("roleID",this.roleID);
            object.put("nameDorm",this.name_dorm);
            object.put("name",this.firstname);
            object.put("lastname",this.lastname);
            object.put("contact",this.contactPhone);
            object.put("email",this.email);
            object.put("username",this.usernamee);
            object.put("password",this.passwordd);
            object.put("documentImage",this.name_doc);

            System.out.println("NAME DOCUMENT : "+name_doc);
            Log.d("tag","Name Doc: "+name_doc);

            sharedPreferences = getSharedPreferences("username_signupList",MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString("username",usernamee);
            editor.putString("password",passwordd);
            editor.commit();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println("Response: "+response);
                    object2 = response;
                    System.out.println("Save Data");
                    System.out.println("Object2: "+object2);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
            };
                RequestQueue queue = Volley.newRequestQueue(this);
                queue.add(request);

                uploadBitmap(composite);


        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        Intent intent = new Intent(SignUp_Dorm_Owner.this,AddDorm.class);
        intent.putExtra("dormName",name_dorm); // ชื่อหอพัก
        intent.putExtra("dormOwnerID",usernamee); // id เข้าของหอพักที่เป็น string
        intent.putExtra("username",usernamee);
        intent.putExtra("password",passwordd);
        intent.putExtra("email",email);
        startActivity(intent);
    }

    public void chooseRole(View v){
        Intent intent = new Intent(SignUp_Dorm_Owner.this,ChooseRole.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            try {
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Uri uriImage = data.getData();
                Cursor cursor = getContentResolver().query(uriImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imagePath = cursor.getString(columnIndex);
                //document_dorm = uriImage.toString();
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uriImage);
                //Toast.makeText(this,"You choose "+bitmap.toString(),Toast.LENGTH_LONG).show();
                cursor.close();


                Bitmap bmpPic = BitmapFactory.decodeStream(getContentResolver().openInputStream(uriImage));
                composite =  bmpPic;
                uploadImage.setImageBitmap(bmpPic);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public void uploadBitmap(final Bitmap bitmap){
        String urlUploadCoverImage = "http://"+ip+"/documentImage/uploadImage";

        final int roleID = this.roleID;
        final String name_dorm = this.name_dorm;
        final String firstname = this.firstname;
        final String lastname = this.lastname;
        final String contactPhone = this.contactPhone;
        final String email = this.email;
        final String usernamee = this.usernamee;
        final String passwordd = this.passwordd;



        VolleyMultipartRequest request = new VolleyMultipartRequest(Request.Method.POST, urlUploadCoverImage, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Log.e("TAG",error.toString());
            }
        })
        {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("email",email);
                param.put("username",usernamee);
                return param;
            }



            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                //name_doc = String.valueOf(imagename+".png");
                params.put("file", new DataPart("Document_"+imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }


        };
        //Volley.newRequestQueue(this).add(request);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }



}
