package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Update_info_Activity extends AppCompatActivity {
    public ActionBar bar;
    public Button button;

    public EditText name;
    public EditText lastname;
    public EditText email;
    public EditText contact;

    public String username;
    public String password;
    public String name_text;
    public String lastname_text;
    public String email_text;
    public String contact_text;
    public JSONObject dormowner;
    public String ip = "192.168.43.57:8080";
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public String email_old;
    public String statusupdate;
    public ProgressDialog progress;
    public String namedorm = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info_);
        statusupdate = getResources().getString(R.string.updatenotify);
        sharedPreferences = getSharedPreferences("file_for_dormowner",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        username = sharedPreferences.getString("username","no var");
        password = sharedPreferences.getString("password","no var");
        System.out.println("USERNAME :"+username);

        email_old = getIntent().getStringExtra("email");
        namedorm = getIntent().getStringExtra("nameDorm");
        System.out.println("OLD EMAIL: "+email_old);
        //System.out.println(dormowner);

        this.bar = getSupportActionBar();
        this.bar.setDisplayHomeAsUpEnabled(true);
        this.bar.setTitle("ข้อมูลเจ้าของหอพัก");
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));

        this.name = (EditText) findViewById(R.id.dormowner_name);
        this.lastname = (EditText) findViewById(R.id.lastname);
        this.email = (EditText) findViewById(R.id.dormowner_email);
        this.contact = (EditText) findViewById(R.id.dormowner_contact);
        this.button = (Button) findViewById(R.id.button);

        setDefault(namedorm);

        name_text = this.name.getText().toString();
        lastname_text = this.lastname.getText().toString();
        email_text = this.email.getText().toString();
        contact_text = this.email.getText().toString();


        this.name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String a = s.toString();
                name_text = a;
            }
        });

        this.lastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String a = s.toString();
                lastname_text = a;
            }
        });

        this.email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    String a = s.toString();
                    email_text = a;
            }
        });

        this.contact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    String a = s.toString();
                    contact_text = a;
            }
        });

    }

    public void confirmUpdateInfoDormOwner(View v){
        System.out.println("ชื่อที่เปลี่ยน: "+name_text);
        System.out.println("นามสกุลที่เปลี่ยน "+lastname_text);
        System.out.println("อีเมล์ที่เปลี่ยน: "+email_text);
        System.out.println("การติดต่อที่เปลี่ยน: "+contact_text);
        System.out.println("old email: "+email_old);
        Resources res = getResources();
        progress = new ProgressDialog(Update_info_Activity.this);
        String title = res.getString(R.string.updateing);
        String msg = res.getString(R.string.messageUpdate);
        progress.setTitle(title);
        progress.setMessage(msg);
        progress.show();
       final RequestQueue queue = Volley.newRequestQueue(this);
        String pathgetInfoDormOwner = "http://"+ip+"/dormOwner/getInfoDormOwnerById/"+username;

       final JSONObject object = new JSONObject();
        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, pathgetInfoDormOwner, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dormowner = response;
                System.out.println("ข้อมูลที่ได้จาก server: "+dormowner);

                String namedorm;
                try
                {
                    System.out.println("^^^^^^ "+name_text+"    "+lastname_text+"    "+contact_text+"    "+email_text+" ^^^^^^");
                    namedorm = dormowner.getString("nameDorm");
                    object.put("id",username);
                    object.put("roleID",String.valueOf(1));
                    object.put("nameDorm",namedorm);
                    object.put("name",name_text);
                    object.put("lastname",lastname_text);
                    object.put("contact",contact_text);
                    object.put("email",email_text);
                    object.put("username",username);
                    object.put("password",password);


                    System.out.println("### JSON THAT UPDATE :"+object+" ###");
                    final String pathupdateInfoDormowner = "http://"+ip+"/dormOwner/updateInfoDormOwner/"+email_old;
                    JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.POST, pathupdateInfoDormowner, object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progress.dismiss();
                            try
                            {
                                String newEmail = response.getString("email");
                                editor.putString("email",newEmail);
                                editor.commit();

                                Intent intent = new Intent(Update_info_Activity.this,UpdateActivity.class);
                                finish();
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            //Toast.makeText(getApplicationContext(),statusupdate,Toast.LENGTH_SHORT).show();
                            System.out.println("Update Finished: "+response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Toast.makeText(getApplicationContext(),"In Request 2 floor: "+error.toString(),Toast.LENGTH_LONG).show();
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
                //Toast.makeText(getApplicationContext(),"In Request 1 floor: "+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request1);
    }

    public void setDefault(String namedorm){
            String path = "http://"+ip+"/dormOwner/getInfoDormOwnerByDormName/"+namedorm;
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    JSONObject object = response;
                    try {
                        String firstname = object.getString("name");
                        String lastnamee = object.getString("lastname");
                        String emaill = object.getString("email");
                        String contactt = object.getString("contact");
                        name.setText(firstname);
                        lastname.setText(lastnamee);
                        email.setText(emaill);
                        contact.setText(contactt);

                    } catch (JSONException e) {
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


}
