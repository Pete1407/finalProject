package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Place_ListView extends AppCompatActivity {
    public ListView listview;
    public PlaceList_Adapter adapter;
    public ArrayList<Places> list;
    public ActionBar bar;
    public Button goToSearchMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place__list_view);
        this.list = new ArrayList<>();

        this.list = getIntent().getParcelableArrayListExtra("placelist");
        System.out.println(list);

        this.listview = (ListView) findViewById(R.id.recycler);
        this.goToSearchMap = (Button) findViewById(R.id.goToSearchMap);

        this.bar = getSupportActionBar();
        this.bar.setTitle("สถานที่ในมหาวิทยาลัย");
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));

        this.adapter = new PlaceList_Adapter(this,list);
        this.listview.setAdapter(this.adapter);

    }

    public void gotoSearchMapPage(View v){
        Intent i = new Intent(Place_ListView.this,MainPage.class);
        startActivity(i);
    }
}
