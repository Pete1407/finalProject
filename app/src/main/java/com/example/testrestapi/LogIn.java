package com.example.testrestapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

public class LogIn extends AppCompatActivity {
    public Button signup;
    public Button signin;
    public TextView textview;
    public EditText username_edittext;
    public EditText password_edittext;

    public String userName;
    public String passWord;

    public String port = ":8080";
    public String ip = "192.168.43.57";


    public boolean authen;
    public String use = "";
    public String pass = "";
    public int rol;

    public String faculty;
    public ArrayList<String> userlist;
    public ArrayList<String> passlist;
    public ArrayList<Integer> rolelist;
    public ArrayList<String> stylelist;
    public ArrayList<String> facultylist;
    public ArrayList<Integer> codestylelist;
    public RequestQueue queue;

    public int nub = 0;
    public String[] facString;

    public SharedPreferences sharefile;
    public SharedPreferences.Editor editor;

    public String fac;
    public String ds;
    public String cs;

    public String nameDorm;
    public String firstname;
    public String lastname;
    public String contact;
    public String email;
    public String username;
    public int rolee;
    JsonObjectRequest request3;

    public String username_fromserver;
    public String email_fromserver;
    public String pathCoverImage;
    public String pathImage360;

    public boolean checkRole = false;

    public SharedPreferences forDormOwner;
    public SharedPreferences.Editor editorForDormOwner;
    public int langcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        SharedPreferences lang = getSharedPreferences("language", Context.MODE_PRIVATE);
        SharedPreferences.Editor langeditor = lang.edit();

        SharedPreferences selectDorm = getSharedPreferences("selectDorm",MODE_PRIVATE);
        SharedPreferences.Editor selectdormeditor = selectDorm.edit();
        selectdormeditor.putString("dormname","initial value");
        selectdormeditor.putString("pathImage360","initial value");
        selectdormeditor.putInt("numberofFavorite",0);
        selectdormeditor.commit();

        Locale local = getResources().getConfiguration().locale;
        String codeLang = local.getLanguage().toUpperCase();
        if(codeLang.equals("TH"))
        {
            langeditor.putString("language","TH");
            langeditor.putInt("languageCode",0);
        }
        else
        {
            langeditor.putString("language","EN");
            langeditor.putInt("languageCode",1);
        }
        langeditor.commit();
        //langcode = lang.getInt("languageCode",0);

       this.forDormOwner = getSharedPreferences("file_for_dormowner",MODE_PRIVATE);
       this.editorForDormOwner = forDormOwner.edit();
       this.editorForDormOwner.putString("username","no value");
       this.editorForDormOwner.putString("password","no value");
       this.editorForDormOwner.putString("email","no value");
       this.editorForDormOwner.putString("namedorm", "no value");


       this.sharefile = getSharedPreferences("file_pref",MODE_PRIVATE);
       editor = sharefile.edit();
       editor.putString("username","no var");
       editor.putString("password","no var");
       editor.putString("faculty","no var");
       editor.putBoolean("statusSearch",false);
       editor.commit();

        facString = getResources().getStringArray(R.array.faculty);

        userlist = new ArrayList<>();
        passlist = new ArrayList<>();
        rolelist = new ArrayList<>();
        stylelist = new ArrayList<>();
        facultylist = new ArrayList<>();
        codestylelist = new ArrayList<>();

        //getDataUserandDormowner();

        //WifiManager wifiMan = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        //WifiInfo wifiInf = wifiMan.getConnectionInfo();
        //int ipAddress = wifiInf.getIpAddress();
        //String ip = String.format("%d.%d.%d.%d", (ipAddress & 0xff),(ipAddress >> 8 & 0xff),(ipAddress >> 16 & 0xff),(ipAddress >> 24 & 0xff));
        //System.out.println("IP ADDRESS: "+ip);


        this.signup = (Button) findViewById(R.id.signup);
        this.username_edittext = (EditText) findViewById(R.id.username);
        this.password_edittext = (EditText) findViewById(R.id.password);
        this.signin = (Button) findViewById(R.id.signIn);

        this.username_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String a = s.toString();
                userName = a;
                System.out.println(userName);
            }
        });

        this.password_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String b = s.toString();
                passWord = b;
                System.out.println(passWord);


            }
        });


    }

    public void signUpActivity(View v){
        Intent intent = new Intent(LogIn.this,ChooseRole.class);
        startActivity(intent);
    }


    public void accessMainPage(View v){
        //response =new String(response.getBytes("ISO-8859-1"), "UTF-8");
        final String usernameFromUser = userName;
        final String passwordFromUser = passWord;

        sharefile = getSharedPreferences("file_pref",MODE_PRIVATE);
        editor = sharefile.edit();
        editor.putString("username",usernameFromUser);
        editor.putString("password",passwordFromUser);


        boolean u = false;
        boolean p = false;
        int positionU = 0;
        int positionP = 0;
        final String faculty;
        String dormStyle;
        final int codeStyle;

        String loginpathforUser = "http://"+ip+port+"/User/detectUserByUsernameandPassword/"+usernameFromUser+"/"+passwordFromUser;

        final  RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request1 = new StringRequest(Request.Method.GET, loginpathforUser, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                StringTokenizer token = new StringTokenizer(response,",");
                String output = "";
                String userid = "";
                String pass = "";
                while(token.hasMoreTokens())
                {
                    output = token.nextToken();
                    userid = token.nextToken();
                    pass = token.nextToken();
                }

                if(output.equals("success"))
                {
                    String url3 = "http://"+ip+port+"/User/checkuser/"+userid+"/"+pass;
                    JsonObjectRequest getinfouser = new JsonObjectRequest(Request.Method.GET, url3, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getApplicationContext(),"เข้าสู่ระบบสำเร็จ",Toast.LENGTH_SHORT).show();
                            JSONObject obj = response;
                            try {


                                rolee = obj.getInt("roleID");
                                System.out.println("ROLE ID IN USER :" + rolee);
                                fac = obj.getString("faculty");
                                System.out.println("FACULTY :" + fac);
                                ds = obj.getString("dormStyle");
                                System.out.println("Style DORM : " + ds);
                                cs = obj.getString("dormStyleInteger");
                                System.out.println("Code Style : " + cs);

                                editor.putString("faculty", fac);
                                editor.putString("dormStyle", ds);
                                editor.putInt("dormStyleCode", Integer.parseInt(cs));
                                editor.commit();

                                Intent intent = new Intent(LogIn.this, MainPage.class);
                                intent.putExtra("faculty", fac);
                                intent.putExtra("dormStyle", ds);
                                intent.putExtra("codeStyle", Integer.parseInt(cs));
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
                    queue.add(getinfouser);
                }
                else
                {
                    String detectdormowner = "http://"+ip+port+"/dormOwner/detectdormownerByUsernameandPassword/"+usernameFromUser+"/"+passwordFromUser;
                    StringRequest checkdormowner = new StringRequest(Request.Method.GET, detectdormowner, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            StringTokenizer token = new StringTokenizer(response,",");
                            String output = "";
                            String userid = "";
                            String password = "";
                            while(token.hasMoreTokens())
                            {
                                output = token.nextToken();
                                userid = token.nextToken();
                                password = token.nextToken();
                            }
                            if(output.equals("success"))
                            {
                                 String url4 = "http://"+ip+port+"/dormOwner/checkdormowner/"+usernameFromUser+"/"+passwordFromUser;
                                 JsonObjectRequest getinfoowner = new JsonObjectRequest(Request.Method.GET, url4, null, new Response.Listener<JSONObject>() {
                                     @Override
                                     public void onResponse(JSONObject response) {
                                         Toast.makeText(getApplicationContext(),"เข้าสู่ระบบสำเร็จ",Toast.LENGTH_SHORT).show();
                                         final JSONObject obj = response;
                                         try
                                         {

                                             String userN = obj.getString("username");
                                             String passW = obj.getString("password");
                                             String email_dormOwner = obj.getString("email");
                                             String nameD = obj.getString("nameDorm");
                                             System.out.println("EMAIL: "+email+"  **************************");
                                             Intent intent = new Intent(LogIn.this,UpdateActivity.class);
                                             intent.putExtra("username",userN);
                                             intent.putExtra("password",passW);
                                             intent.putExtra("nameDorm",nameDorm);

                                             editorForDormOwner.putString("username",userN);
                                             editorForDormOwner.putString("password",passW);
                                             editorForDormOwner.putString("email",email_dormOwner);
                                             editorForDormOwner.putString("nameDorm",nameD);
                                             editorForDormOwner.commit();

                                             //intent.putExtra("fullname",firstname+" "+lastname);
                                             //intent.putExtra("contact",contact);
                                             //intent.putExtra("pathCoverImage",pathCoverImage);
                                             //intent.putExtra("image360",pathImage360);
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
                                 queue.add(getinfoowner);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูลให้ถูกต้อง",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(checkdormowner);



                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request1);
    }



}
