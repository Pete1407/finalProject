package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SmartSearch extends AppCompatActivity {
    ActionBar bar;
    Spinner spinner;
    String[] choice = null;
    Resources res;
    int codeStyle;
    String faculty;

    int langcode;
    String langname;
    AutoCompleteTextView autocomplete;
    SharedPreferences changelanguage;
    SharedPreferences.Editor editorlang;
    String[] resultlist;
    public String userchoose;

    public String port = ":8080";
    public String ip = "192.168.43.57";
    public ArrayList<String> namedorm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_search);
        namedorm = new ArrayList<>();
        res = getResources();
        bar = getSupportActionBar();
        String searchtitle = getResources().getString(R.string.search_title);
        bar.setTitle(searchtitle);
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));
        bar.setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPreferences = getSharedPreferences("file_pref",MODE_PRIVATE);
        codeStyle = sharedPreferences.getInt("dormStyleCode",4);
        faculty = sharedPreferences.getString("faculty","no faculty");

        changelanguage = getSharedPreferences("language",MODE_PRIVATE);
        langname = changelanguage.getString("language","no language");
        langcode = changelanguage.getInt("languageCode",0);

        if(langcode == 0)
        {
            choice = res.getStringArray(R.array.searchchoiceTH);
        }
        else
        {
            choice = res.getStringArray(R.array.searchchoiceEN);
        }

        spinner = (Spinner) findViewById(R.id.spinner);
        autocomplete = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,choice);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userchoose = choice[position];
                setchoice(userchoose);
                setDefault(new Callback() {
                    @Override
                    public void onSuccess(ArrayList<String> list) {
                            if(list.size() > 0)
                            {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,list);
                                autocomplete.setAdapter(adapter);
                            }
                            else
                            {
                                System.out.println("It not member in list.");
                            }
                    }

                    @Override
                    public void onSuccessType1(ArrayList<Dorm_PricenPromotion_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType2(ArrayList<Dorm_Information_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType3(ArrayList<Dorm_Quality_Model> dorm1) {

                    }

                    @Override
                    public void onSuccessType4(ArrayList<Dorm_BrandName_Model> dorm1) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void setchoice(String userchoose){
        //String fromuser = autocomplete.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(this);
     if(codeStyle == 0)
     {
         if(userchoose.equals(choice[1]))
         {
             String requestpath = "http://"+ip+port+"/DormTypePriceNPromotion/getAllDorm";
             JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, requestpath, null, new Response.Listener<JSONArray>() {
                 @Override
                 public void onResponse(JSONArray response) {
                    JSONArray array = response;
                    System.out.println(array.length());
                    String[] namelist = new String[array.length()];
                 try
                 {
                     for(int count=0;count<array.length();count++)
                     {
                         JSONObject object = array.getJSONObject(count);
                         String ff = object.getString("dormID");
                         namelist[count] = ff;
                         System.out.println("dorm name: "+ff);
                     }


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
             queue.add(request);
         }
         else if(userchoose.equals(choice[2]))
         {

         }
         else if(userchoose.equals(choice[3]))
         {

         }
     }
     else if(codeStyle == 1)
     {
         //Toast.makeText(this,String.valueOf(codeStyle),Toast.LENGTH_SHORT).show();
         if(userchoose.equals(choice[1]))
         {

         }
         else if(userchoose.equals(choice[2]))
         {

         }
         else if(userchoose.equals(choice[3]))
         {

         }
     }
     else if(codeStyle == 2)
     {
         //Toast.makeText(this,String.valueOf(codeStyle),Toast.LENGTH_SHORT).show();
         if(userchoose.equals(choice[1]))
         {

         }
         else if(userchoose.equals(choice[2]))
         {

         }
         else if(userchoose.equals(choice[3]))
         {

         }
     }
     else if(codeStyle == 3)
     {
         //Toast.makeText(this,String.valueOf(codeStyle),Toast.LENGTH_SHORT).show();
         if(userchoose.equals(choice[1]))
         {

         }
         else if(userchoose.equals(choice[2]))
         {

         }
         else if(userchoose.equals(choice[3]))
         {

         }
     }

    }

    public void setDefault(Callback onCallback){
        RequestQueue queue = Volley.newRequestQueue(this);
        String requestpath = "http://"+ip+port+"/DormTypePriceNPromotion/getAllDorm";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, requestpath, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray array = response;
                System.out.println(array.length());
                String[] namelist = new String[array.length()];
                try
                {
                    for(int count=0;count<array.length();count++)
                    {
                        JSONObject object = array.getJSONObject(count);
                        String ff = object.getString("dormID");
                        namelist[count] = ff;
                        System.out.println("dorm name: "+ff);
                    }
                    ArrayList<String> h = new ArrayList<>();
                    for(int count=0;count<namelist.length;count++)
                    {
                        h.add(namelist[count]);
                    }
                    onCallback.onSuccess(h);


                }
                catch (JSONException ex){
                    ex.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onCallback.onFail(error.toString());
            }
        });
        queue.add(request);
    }


}
