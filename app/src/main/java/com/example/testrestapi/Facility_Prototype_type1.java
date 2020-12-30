package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class Facility_Prototype_type1 extends AppCompatActivity {
    public RecyclerView recycler;
    public ActionBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility__prototype_type1);
        String facility = getResources().getString(R.string.facility_title);
        this.bar = getSupportActionBar();
        this.bar.setTitle(facility);
        this.bar.setDisplayHomeAsUpEnabled(true);
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));
        this.recycler = (RecyclerView) findViewById(R.id.recyclerview);


    }
}
