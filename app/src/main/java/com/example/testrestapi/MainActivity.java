package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
//import com.gjiazhe.panoramaimageview.GyroscopeObserver;
//import com.gjiazhe.panoramaimageview.PanoramaImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{

    public EditText firstname_input;
    public EditText lastname_input;
    public EditText email_input;
    public EditText username_input;
    public EditText password_input;
    public Spinner spinner;
    public Button sendData;
    public Button getData;
    public TextView text;
    //////////    variable for User  /////////////
    private String firstname = "";
    private String lastname = "";
    private String email = "";
    private String faculty = "";
    private String username = "";
    private String password = "";
    public int choice_user = 0;
    public String choice = "";

    public ImageButton info;
    public String ip ="192.168.43.57:8080";
    public String port = ":8080";
    public String url = "http://"+ip;


    public ArrayList<Member> list;
    public String a = "";

    //public RadioButton button;
    //public RadioGroup group;
    //public String userlike;
    public String[] dormType;

   // private VrPanoramaView mVRPanoramaView;
    // ที่คอมเม้นท์ไว้คือ เป็นการแสดงภาพแบบ panorama ไม่ใช่ 360 องศา
   // public GyroscopeObserver gyroscope;
    public boolean status = false;
   // public PanoramaImageView panoramaImageView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("สมัครสมาชิก");
        bar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));

        this.list = new ArrayList<>();

        this.firstname_input = (EditText)findViewById(R.id.firstname_input);
        this.lastname_input = (EditText) findViewById(R.id.lastname_input);
        this.email_input = (EditText) findViewById(R.id.email_input);
        this.username_input = (EditText)findViewById(R.id.username_user);
        this.password_input = (EditText)findViewById(R.id.password_user);
        this.info = (ImageButton) findViewById(R.id.info);
        this.sendData = (Button) findViewById(R.id.sendData);
        this.text = (TextView) findViewById(R.id.favorite_text);


        this.dormType = getResources().getStringArray(R.array.dormtype);

      //  group = (RadioGroup) findViewById(R.id.radioGroup);
        /*
        for(int count=0;count<4;count++)
        {
            button = new RadioButton(this);
            button.setText(dormType[count]);
            button.setTag(count);
            group.addView(button,count);
        }
        */

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        this.spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.faculty,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        System.out.println(this.url);

        //mVRPanoramaView = (VrPanoramaView) findViewById(R.id.vrPanoramaView);
        //loadPhotoSphere();




       // this.gyroscope = new GyroscopeObserver();
       // this.gyroscope.setMaxRotateRadian(Math.PI/9);

       // this.panoramaImageView = (PanoramaImageView)findViewById(R.id.panorama);
       // this.panoramaImageView.setGyroscopeObserver(this.gyroscope);

        /*
        this.group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int choice = checkedId;
                switch(choice)
                {
                    case R.id.choice1 : userlike = dormType[0];
                        System.out.println(userlike);
                        break;
                    case R.id.choice2 : userlike = dormType[1];
                        System.out.println(userlike);
                        break;
                    case R.id.choice3 : userlike = dormType[2];
                        System.out.println(userlike);
                        break;
                    case R.id.choice4 : userlike = dormType[3];
                        System.out.println(userlike);
                        break;

                    default:
                }
            }
        });
        */
    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        mVRPanoramaView.resumeRendering();
        //this.gyroscope.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVRPanoramaView.pauseRendering();
        //this.gyroscope.unregister();
    }

    @Override
    protected void onDestroy() {
        mVRPanoramaView.shutdown();
        super.onDestroy();

    }
*/



    public void selectChocie(View v) {
        boolean check = ((RadioButton)v).isChecked();
        int choice = v.getId();
        switch(choice)
        {
            case R.id.choice1 :
                if(check)
                {
                    this.choice_user = 0;
                    this.choice = dormType[0];
                }
                break;

            case R.id.choice2 :
                if(check)
                {
                    this.choice_user = 1;
                    this.choice = dormType[1];
                }
                break;

            case R.id.choice3 :
                if(check)
                {
                    this.choice_user = 2;
                    this.choice = dormType[2];
                }
                break;

            case R.id.choice4 :
                if(check)
                {
                    this.choice_user = 3;
                    this.choice = dormType[3];
                }
                break;

             default:
        }
    }

    public void showInfo(View v){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        int a = R.string.detail;
        int b = R.string.info_detail;
        String text = getResources().getString(a);
        String detail = getResources().getString(b);
        dialog.setTitle(text);
        dialog.setMessage(b);

        dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.show();
    }

    public void addUser(View v){
        RequestQueue queue = Volley.newRequestQueue(this);
        System.out.println(" *** POST METHOD ***");
        System.out.println(this.firstname_input.getText());
        System.out.println(this.lastname_input.getText());
        System.out.println(this.email_input.getText());
        System.out.println(this.spinner.getSelectedItem().toString());

        System.out.println(this.username_input.getText());
        System.out.println(this.password_input.getText());
        System.out.println(this.choice_user);

        url = url +"/User/addUser";

        try
        {
            // Gson is converter from object to Json and Json to object
            Gson gson = new Gson();
            String id = this.firstname_input.getText().toString();
            String firstname = this.firstname_input.getText().toString();
            String lastname = this.lastname_input.getText().toString();
            String email = this.email_input.getText().toString();
            String faculty = this.spinner.getSelectedItem().toString();
            String username = this.username_input.getText().toString();
            String password = this.password_input.getText().toString();
            int choicecode = this.choice_user;
            String choiceString = choice;
            String role = getIntent().getStringExtra("value");

           // Member member = new Member(id,firstname,lastname,email,faculty,username,password,choice);

            //String json = gson.toJson(member);
            //System.out.println("Saved for :"+json);

            JSONObject object = new JSONObject();
            object.put("username",username);
            object.put("firstname",firstname);
            object.put("lastname",lastname);
            object.put("email",email);
            object.put("faculty",faculty);
            object.put("password",password);
            object.put("roleID",role);
            object.put("dormStyle",choiceString);
            object.put("dormStyleInteger",choicecode);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });


            queue.add(request);

            String createUserFavorite = "http://"+ip+"/favorite/saveFavoriteDorm";
            JSONObject favUser = new JSONObject();
            favUser.put("username",username);
            favUser.put("codeStyle",choicecode);
            JSONArray array = new JSONArray();
            favUser.put("dormname",array);
            JsonObjectRequest createFavorite = new JsonObjectRequest(Request.Method.POST, createUserFavorite, favUser, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(createFavorite);

            Intent intent = new Intent(MainActivity.this,LogIn.class);
            startActivity(intent);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }





    }


    public void getService(View v) {
        System.out.println("Get Service Method");

        final StringRequest request = new StringRequest(Request.Method.POST,this.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

               // Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        // 2 บรรทัดนี้จำเป็นต้องมี ถ้าไม่มีจะไท่เกิดการเชื่อมต่อกับ Server และ database ได้
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void getData(View v){

        //String id = response.getString("id");
        //String firstname = response.getString("firstname");
        //String lastname = response.getString("lastname");
        //String email = response.getString("email");
        //String faculty = response.getString("faculty");
        //String username = response.getString("username");
        //String password = response.getString("password");
        //int choice = response.getInt("choice");
        //String role = response.getString("role");

        //Member m = new Member(id,firstname,lastname,email,faculty,username,password,choice,role);

        String url = "http://192.168.1.103:8080/Member/findMemberByChoice/4";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try
                {
                    System.out.println(response.length());
                    for(int count=0;count<response.length();count++)
                    {
                        System.out.println(response.get(count));
                    }
                }
                catch(JSONException ex)
                {
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

    /*
    public void loadPhotoSphere() {
        System.out.println("Function is working.");
        VrPanoramaView.Options options = new VrPanoramaView.Options();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.image_3604);
        mVRPanoramaView.loadImageFromBitmap(bitmap,options);
    }
    */
/*
    public  String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }
*/
}
