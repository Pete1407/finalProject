package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.testrestapi.Fragment.Type_Information_Fragment_Update;
import com.example.testrestapi.Fragment.Type_PriceAndPromotion_Dorm_Fragment_Update;
import com.example.testrestapi.Fragment.Type_QualityAndPremiwum_Fragment_Update;
import com.google.vr.ndk.base.Frame;

import java.util.ArrayList;

public class UpdateDetail_Style extends AppCompatActivity {
    public ActionBar bar;

    public String dormname;
    public int codeStyle;
    public FrameLayout frame;

    public FragmentTransaction transaction;
    public FragmentManager fragmentManager;
    public String dataPromotion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_detail__style);

        bar = getSupportActionBar();
        bar.setTitle("สไตล์หอพัก");
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));
        bar.setDisplayHomeAsUpEnabled(true);

        frame = (FrameLayout) findViewById(R.id.framelayout);

        dormname = getIntent().getStringExtra("dormname");
        codeStyle = getIntent().getIntExtra("codeStyle",4);

        if(codeStyle == 0)
        {
            String promotionDetail = getIntent().getStringExtra("promotionDetail");
            int oldprice = getIntent().getIntExtra("oldPrice",0);
            int newprice = getIntent().getIntExtra("newPrice",0);
            Bundle b = new Bundle();
            b.putString("dormname",dormname);
            b.putString("promotionDetail",promotionDetail);
            b.putInt("oldprice",oldprice);
            b.putInt("newprice",newprice);
            Type_PriceAndPromotion_Dorm_Fragment_Update fragment = new Type_PriceAndPromotion_Dorm_Fragment_Update();
            fragment.setArguments(b);
            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction().replace(R.id.framelayout,fragment);
            transaction.commit();
        }
        else if(codeStyle == 1)
        {
            String infodetail = getIntent().getStringExtra("infodetail");
            System.out.println("InfoDetail: "+infodetail);
            Bundle b = new Bundle();
            b.putString("dormname",dormname);
            b.putString("infodetail",infodetail);
            Type_Information_Fragment_Update fgr = new Type_Information_Fragment_Update();
            fgr.setArguments(b);
            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction().replace(R.id.framelayout,fgr);
            transaction.commit();
        }
        else if(codeStyle == 2)
        {
            ArrayList<Integer> imagelist = getIntent().getIntegerArrayListExtra("imagelist");
            ArrayList<String> thlist = getIntent().getStringArrayListExtra("thlist");
            ArrayList<String> enlist=  getIntent().getStringArrayListExtra("enlist");
            Bundle b = new Bundle();
            b.putString("dormname",dormname);
            b.putIntegerArrayList("imagelist",imagelist);
            b.putStringArrayList("thlist",thlist);
            b.putStringArrayList("enlist",enlist);
            Type_QualityAndPremiwum_Fragment_Update fgr = new Type_QualityAndPremiwum_Fragment_Update();
            fgr.setArguments(b);
            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction().replace(R.id.framelayout,fgr);
            transaction.commit();
        }
        else if(codeStyle == 3)
        {
            String pathlogo = getIntent().getStringExtra("pathlogo");
            String username = getIntent().getStringExtra("username");
            String email = getIntent().getStringExtra("email");
            //System.out.println("PATH LOGO: "+pathlogo);
            Bundle b = new Bundle();
            b.putString("dormname",dormname);
            b.putString("pathlogo",pathlogo);
            b.putString("username",username);
            b.putString("email",email);
            Type_BrandName_Dorm_Fragment_Update fgr = new Type_BrandName_Dorm_Fragment_Update();
            fgr.setArguments(b);
            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction().replace(R.id.framelayout,fgr);
            transaction.commit();
        }

    }
}
