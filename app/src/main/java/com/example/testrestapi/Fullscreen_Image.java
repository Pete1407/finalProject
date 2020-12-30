package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Fullscreen_Image extends AppCompatActivity {
    public ImageView image;
    public String pathimage;
    public ActionBar bar;

    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen__image);
        this.bar = getSupportActionBar();
        String etcgallery = getResources().getString(R.string.etc_photo);
        this.bar.setTitle(etcgallery);
        sharedPreferences = getSharedPreferences("file_pref",MODE_PRIVATE);
        int a = sharedPreferences.getInt("dormStyleCode",4);
        if(a == 0 || a == 1 || a == 2)
        {
            this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));

        }
        else if(a == 3)
        {
            this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        }

        this.bar.setDisplayHomeAsUpEnabled(true);

        this.image = (ImageView) findViewById(R.id.image);
        pathimage = getIntent().getStringExtra("pathImage");

        Picasso.with(this).load(pathimage).into(image);


    }
}
