package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class updateTypeDorm extends AppCompatActivity {
    public ActionBar bar;
    public Spinner spinner;
    public String[] stylename;

    public int stylecode;
    public String styletext;

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public Button button;

    public String username;
    public String password;

    public String port = ":8080";
    public String ip = "192.168.43.57";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_type_dorm);

        spinner = findViewById(R.id.spinner2);
        button = findViewById(R.id.button8);

        sharedPreferences = getSharedPreferences("file_pref",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        username = sharedPreferences.getString("username","no username");



        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        Resources res = getResources();
        stylename = res.getStringArray(R.array.dormtype);
        String title = res.getString(R.string.updatestyle_dorm);
        bar.setTitle(title);
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.dormtype,android.R.layout.simple_expandable_list_item_1);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    styletext = stylename[position];
                    stylecode = position;
                   // Toast.makeText(getApplicationContext(),styletext+" "+stylecode,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void confirmupdateTypeDorm(View v){
        RequestQueue queue = Volley.newRequestQueue(this);

        String path = "http://"+ip+port+"/User/checkUser/"+username;
        JsonObjectRequest updatereq = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                 JSONObject object = response;
                try
                {
                    String json_username = object.getString("username");
                    String json_firstname = object.getString("firstname");
                    String json_lastname = object.getString("lastname");
                    String json_email = object.getString("email");
                    String json_faculty = object.getString("faculty");
                    String json_password = object.getString("password");
                    int json_roleID = object.getInt("roleID");
                    String dormStyle = styletext;
                    int dormStyleInteger = stylecode;

                    JSONObject json = new JSONObject();
                    json.put("username",json_username);
                    json.put("firstname",json_firstname);
                    json.put("lastname",json_lastname);
                    json.put("email",json_email);
                    json.put("faculty",json_faculty);
                    json.put("password",json_password);
                    json.put("roleID",json_roleID);
                    json.put("dormStyle",dormStyle);
                    json.put("dormStyleInteger",dormStyleInteger);

                    editor.putString("password",json_password);
                    editor.putBoolean("statusSearch",false);
                    editor.putString("dormStyle",dormStyle);
                    editor.putString("username",json_username);
                    editor.putString("faculty",json_faculty);
                    editor.putInt("dormStyleCode",dormStyleInteger);
                    editor.commit();

                    String path2 = "http://"+ip+port+"/User/updateStyledorm/"+username;
                    JsonObjectRequest updatereq2 = new JsonObjectRequest(Request.Method.POST, path2, json, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getApplicationContext(),"แก้ไขเรียบร้อย",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(updateTypeDorm.this,MainPage.class);
                            startActivity(intent);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(updatereq2);


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
        queue.add(updatereq);

    }
}
