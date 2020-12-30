package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ChooseRole extends AppCompatActivity {
    public ActionBar actionbar;
    public int role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);



        this.actionbar = getSupportActionBar();
        this.actionbar.setTitle("เลือกบทบาท");
        this.actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));
        this.actionbar.setDisplayHomeAsUpEnabled(true);
    }

    public void chooseRoleStudent(View v){
        Intent intent = new Intent(ChooseRole.this,MainActivity.class);
        this.role = 0;
        //System.out.println("You choose Role as User "+this.role);
        Log.d("TAG","You choose Role as User "+this.role);
        intent.putExtra("role",this.role);
        startActivity(intent);
    }

    public void chooseRoleDormOwner(View v){
        this.role = 1;
        Intent intent = new Intent(ChooseRole.this,SignUp_Dorm_Owner.class);
        intent.putExtra("role",this.role);
        //System.out.println("You choose Role as Dorm Owner "+this.role);
        Log.d("TAG","You choose Role as Dormowner "+this.role);
        startActivity(intent);


    }

    public void getTest(View v){
        Intent intent = new Intent(ChooseRole.this,SignUp_Dorm_Owner.class);
        startActivity(intent);
    }
}
