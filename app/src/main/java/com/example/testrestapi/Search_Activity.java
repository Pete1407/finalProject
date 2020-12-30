package com.example.testrestapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class Search_Activity extends AppCompatActivity {
    public EditText editext;
    public ImageButton search;
    public Button namebutton;
    public Button pricebutton;
    public Button distancebutton;


    public boolean pricePress = false;
    public boolean namePress = false;
    public boolean distancePress = false;
    public ActionBar bar;

    public SharedPreferences sharedPreferences;

    public String port = ":8080";
    public String ip = "192.168.43.57";


    Faculty scienceAndTechnology;
    Faculty law;
    Faculty politicalScience;
    Faculty account;
    Faculty liberalArt;
    Faculty architect;
    Faculty economy;
    Faculty social;
    Faculty jouranlism;
    Faculty humanism;
    Faculty engineer;
    Faculty medicine;
    Faculty alliedHealth;
    Faculty dentistry;
    Faculty nurse;
    Faculty fineArt;
    Faculty publicHealth;
    Faculty pharmacy;
    Faculty studyAndknowledge;
    Faculty siit;
    Faculty puey;



    Places lc1 ;
    Places lc2 ;
    Places lc3 ;
    Places lc4 ;
    Places lc5 ;
    Places science_Canteen;
    Places pueyLibrary ;
    Places learningBuilding ;
    Places vanPort ;
    Places regtu ;

    // Social Science
    Places sc ;
    Places greenCanteen ;
    Places scCanteen1 ;
    Places scCanteen2;

    public int codeStyle;

    public RadioGroup group;
    public RadioButton chooseRadio;
    public EditText editText;

    public int choice = 3;
    public String choicetext;
    public String inputuser = "";
    public String faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_);
        bar = getSupportActionBar();
        String title = getResources().getString(R.string.finddorm);
        bar.setTitle(title);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));

        sharedPreferences = getSharedPreferences("file_pref",MODE_PRIVATE);
        codeStyle = sharedPreferences.getInt("dormStyleCode",4);
        faculty = sharedPreferences.getString("faculty","no faculty");

        if(codeStyle == 3)
        {
            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        }

        this.editText = (EditText) findViewById(R.id.edittext);
        this.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                inputuser = input;
            }
        });
    }

    public void chooseChoice(View v){
        boolean userchoose = ((RadioButton) v).isChecked();
        switch (v.getId())
        {
            case R.id.name :
                if(userchoose)
                {
                    choice = 0;
                    choicetext = "name";
                    //Toast.makeText(this,String.valueOf(choice),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.price :
                if(userchoose)
                {
                    choice = 1;
                    choicetext = "price";
                    //Toast.makeText(this,String.valueOf(choice),Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.distance:
                if(userchoose)
                {
                    choice = 2;
                    choicetext = "distance";
                    //Toast.makeText(this,String.valueOf(choice),Toast.LENGTH_SHORT).show();
                }
        }
    }

    public void search(View v){
     if(choice==3)
     {
         AlertDialog.Builder notification = new AlertDialog.Builder(Search_Activity.this);
         String title = getResources().getString(R.string.notification_input_search_title);
         String msg = getResources().getString(R.string.notification_input_search_message);
         String confirm = getResources().getString(R.string.confirm);
         notification.setTitle(title);
         notification.setIcon(R.drawable.alert);
         notification.setMessage(msg);
         notification.setNegativeButton(confirm, new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
             }
         });
         notification.show();
     }
     else
     {
         if(inputuser.equals(""))
         {
             android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Search_Activity.this);
             Resources res = getResources();
             String title = res.getString(R.string.inputtext_title);
             String message = res.getString(R.string.inputtext_message);
             dialog.setTitle(title);
             dialog.setMessage(message);
             dialog.setIcon(R.drawable.alert);
             dialog.setNegativeButton("ตกลง", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     dialog.dismiss();
                 }
             });

             dialog.show();
         }
         else
         {
             SharedPreferences search = getSharedPreferences("search",MODE_PRIVATE);
             SharedPreferences.Editor editor = search.edit();
             if(codeStyle == 0)
             {
                 // name
                 if(choice == 0)
                 {
                     String name = inputuser;
                     editor.putInt("codeStyleSearch",choice);
                     editor.putString("optionSearch",choicetext);
                     editor.putString("inputfromUser",name);
                     editor.commit();

                     Intent intent = new Intent(Search_Activity.this,Output_Search.class);
                     startActivity(intent);
                 }
                 // price
                 else if(choice == 1)
                 {
                     int price = Integer.parseInt(inputuser);
                     editor.putInt("codeStyleSearch",choice);
                     editor.putString("optionSearch",choicetext);
                     editor.putInt("inputfromUser",price);
                     editor.commit();
                     Intent intent = new Intent(Search_Activity.this,Output_Search.class);
                     startActivity(intent);
                     //System.out.println("PRICE "+codeStyle);
                 }
                 else if(choice == 2)
                 {
                     double distance = Double.parseDouble(inputuser);
                     editor.putInt("codeStyleSearch",choice);
                     editor.putString("optionSearch",choicetext);
                     editor.putString("inputfromUser",String.valueOf(distance));
                     editor.commit();
                     Intent intent = new Intent(Search_Activity.this,Output_Search.class);
                     startActivity(intent);
                     //System.out.println("DISTANCE "+codeStyle);
                 }
             }
             else if(codeStyle == 1)
             {
                 if(choice == 0)
                 {
                     String name = inputuser;
                     editor.putInt("codeStyleSearch",choice);
                     editor.putString("optionSearch",choicetext);
                     editor.putString("inputfromUser",name);
                     editor.commit();
                     Intent intent = new Intent(Search_Activity.this,Output_Search.class);
                     startActivity(intent);
                 }
                 else if(choice == 1)
                 {
                     int price = Integer.parseInt(inputuser);
                     editor.putInt("codeStyleSearch",choice);
                     editor.putString("optionSearch",choicetext);
                     editor.putInt("inputfromUser",price);
                     editor.commit();
                     Intent intent = new Intent(Search_Activity.this,Output_Search.class);
                     startActivity(intent);
                 }
                 else if(choice == 2)
                 {
                     double distance = Double.parseDouble(inputuser);
                     editor.putInt("codeStyleSearch",choice);
                     editor.putString("optionSearch",choicetext);
                     editor.putString("inputfromUser",String.valueOf(distance));
                     editor.commit();
                     Intent intent = new Intent(Search_Activity.this,Output_Search.class);
                     startActivity(intent);
                     //System.out.println("DISTANCE "+codeStyle);
                 }
             }
             else if(codeStyle == 2)
             {
                 if(choice == 0)
                 {
                     String name = inputuser;
                     editor.putInt("codeStyleSearch",choice);
                     editor.putString("optionSearch",choicetext);
                     editor.putString("inputfromUser",name);
                     editor.commit();
                     Intent intent = new Intent(Search_Activity.this,Output_Search.class);
                     startActivity(intent);
                     //System.out.println("NAME "+codeStyle);
                 }
                 else if(choice == 1)
                 {
                     int price = Integer.parseInt(inputuser);
                     editor.putInt("codeStyleSearch",choice);
                     editor.putString("optionSearch",choicetext);
                     editor.putInt("inputfromUser",price);
                     editor.commit();
                     Intent intent = new Intent(Search_Activity.this,Output_Search.class);
                     startActivity(intent);
                     //System.out.println("PRICE "+codeStyle);
                 }
                 else if(choice == 2)
                 {
                     double distance = Double.parseDouble(inputuser);
                     editor.putInt("codeStyleSearch",choice);
                     editor.putString("optionSearch",choicetext);
                     editor.putString("inputfromUser",String.valueOf(distance));
                     editor.commit();
                     Intent intent = new Intent(Search_Activity.this,Output_Search.class);
                     startActivity(intent);
                     //System.out.println("DISTANCE "+codeStyle);

                 }
             }
             else if(codeStyle == 3)
             {
                 if(choice == 0)
                 {
                     String name = inputuser;
                     editor.putInt("codeStyleSearch",choice);
                     editor.putString("optionSearch",choicetext);
                     editor.putString("inputfromUser",name);
                     editor.commit();
                     Intent intent = new Intent(Search_Activity.this,Output_Search.class);
                     startActivity(intent);
                     //System.out.println("NAME "+codeStyle);

                 }
                 else if(choice == 1)
                 {
                     int price = Integer.parseInt(inputuser);
                     editor.putInt("codeStyleSearch",choice);
                     editor.putString("optionSearch",choicetext);
                     editor.putInt("inputfromUser",price);
                     editor.commit();
                     Intent intent = new Intent(Search_Activity.this,Output_Search.class);
                     startActivity(intent);
                     //System.out.println("PRICE "+codeStyle);
                 }
                 else if(choice == 2)
                 {
                     double distance = Double.parseDouble(inputuser);
                     editor.putInt("codeStyleSearch",choice);
                     editor.putString("optionSearch",choicetext);
                     editor.putString("inputfromUser",String.valueOf(distance));
                     editor.commit();
                     Intent intent = new Intent(Search_Activity.this,Output_Search.class);
                     startActivity(intent);
                     System.out.println("DISTANCE "+codeStyle);

                 }
             }
         }

     }

    }






}
