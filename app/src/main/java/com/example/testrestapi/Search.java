package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.EditText;

public class Search extends AppCompatActivity {
    public ActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        int color = getResources().getColor(R.color.background);
        this.actionbar = getSupportActionBar();
        this.actionbar.setBackgroundDrawable(new ColorDrawable(color));
        this.actionbar.setTitle("");


    }
}
