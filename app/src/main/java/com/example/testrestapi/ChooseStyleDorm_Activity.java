package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.testrestapi.Fragment.Type_Information_Fragment;
import com.example.testrestapi.Fragment.Type_PriceAndPromotion_Dorm_Fragment;
import com.example.testrestapi.Fragment.Type_QualityAndPremiwum_Fragment;

public class ChooseStyleDorm_Activity extends AppCompatActivity {
    public ActionBar bar;
    public Button button;
    public LinearLayout linear;

    public Spinner spinner;
    public String[] dormType;

    public String textCheck = null;

    public String username;
    public String password;
    public String email;

    public Bundle b;
    public Bundle specific_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_style_dorm_);

        //////////////////////////////////////
        String dormID = getIntent().getStringExtra("dormID");
        int minPrice = getIntent().getIntExtra("minPrice",0);
        int maxPrice = getIntent().getIntExtra("maxPrice",0);
        String typeWater = getIntent().getStringExtra("typeWater");
        int priceWater = getIntent().getIntExtra("priceWater",0);
        String typeElectro = getIntent().getStringExtra("typeElectro");
        int priceElectro = getIntent().getIntExtra("priceElectro",0);
        String typeDeposit = getIntent().getStringExtra("typeDeposit");
        int depositPrice = getIntent().getIntExtra("depositPrice",0);
        System.out.println("INTO CHOOSE STYLEDORM ACTIVITY "+depositPrice);
        String typeCommonFee = getIntent().getStringExtra("typeCommonFee");
        int commonFee = getIntent().getIntExtra("commonFee",0);
        String description = getIntent().getStringExtra("description");


        // KEY FOR SPECIFY IMAGE
        this.username = getIntent().getStringExtra("username");
        this.email = getIntent().getStringExtra("email");
        this.password = getIntent().getStringExtra("password");

        System.out.println("USERNAME: "+username+" -----------------");
        System.out.println("EMAIL: "+email+" --------------");
       // Toast.makeText(this,"Email: "+email+"\n"+"Username: "+username,Toast.LENGTH_LONG).show();

        b = new Bundle();
        b.putString("dormID",dormID);
        b.putString("typeWater",typeWater);
        b.putString("typeElectro",typeElectro);
        b.putString("typeDeposit",typeDeposit);
        b.putString("typeCommonFee",typeCommonFee);

        b.putInt("minPrice",minPrice);
        b.putInt("maxPrice",maxPrice);
        b.putInt("priceWater",priceWater);
        b.putInt("priceElectro",priceElectro);
        b.putInt("depositPrice",depositPrice);
        b.putInt("commonFee",commonFee);
        b.putString("description",description);

        b.putString("email",email);
        b.putString("username",username);


        specific_logo = new Bundle();
        specific_logo.putString("dormID",dormID);
        specific_logo.putString("typeWater",typeWater);
        specific_logo.putString("typeElectro",typeElectro);
        specific_logo.putString("typeDeposit",typeDeposit);
        specific_logo.putString("typeCommonFee",typeCommonFee);

        specific_logo.putInt("minPrice",minPrice);
        specific_logo.putInt("maxPrice",maxPrice);
        specific_logo.putInt("priceWater",priceWater);
        specific_logo.putInt("priceElectro",priceElectro);
        specific_logo.putInt("depositPrice",depositPrice);
        specific_logo.putInt("commonFee",commonFee);
        specific_logo.putString("description",description);
        specific_logo.putString("username",username);
        specific_logo.putString("email",email);


        this.button = (Button) findViewById(R.id.button);

        this.bar = getSupportActionBar();
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));
        this.bar.setTitle("ประเภทหอพัก");

        this.spinner = (Spinner) findViewById(R.id.spinner);

        this.dormType = getResources().getStringArray(R.array.dormtype);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.dormtype,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinner.setAdapter(adapter);

        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String userChoose = parent.getSelectedItem().toString();
                //System.out.println(dormType[0]+"  "+parent.getSelectedItem().toString());

                if(userChoose.equals(dormType[0]))
                {
                    System.out.println(dormType[0]);
                    b.putString("dormStyle",dormType[0]);
                    Type_PriceAndPromotion_Dorm_Fragment fgr = new Type_PriceAndPromotion_Dorm_Fragment();
                    fgr.setArguments(b);
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction().replace(R.id.framelayout,fgr);
                    transaction.commit();
                }
                else if(userChoose.equals(dormType[1]))
                {
                    System.out.println(dormType[1]);
                    b.putString("dormStyle",dormType[1]);
                    Type_Information_Fragment fgr = new Type_Information_Fragment();
                    fgr.setArguments(b);
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction().replace(R.id.framelayout,fgr);
                    transaction.commit();
                }
                else if(userChoose.equals(dormType[2]))
                {
                    System.out.println(dormType[2]);

                    b.putString("dormStyle",dormType[2]);
                    Type_QualityAndPremiwum_Fragment fgr = new Type_QualityAndPremiwum_Fragment();
                    fgr.setArguments(b);
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction().replace(R.id.framelayout,fgr);
                    transaction.commit();
                }
                else if(userChoose.equals(dormType[3]))
                {

                    System.out.println(dormType[3]);
                    specific_logo.putString("dormStyle",dormType[3]);
                    Type_BrandName_Dorm_Fragment fgr = new Type_BrandName_Dorm_Fragment();
                    fgr.setArguments(specific_logo);
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction().replace(R.id.framelayout,fgr);
                    transaction.commit();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void confirmInfo(View v){
        Intent intent = new Intent(ChooseStyleDorm_Activity.this,UploadImageActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("password",password);
        intent.putExtra("email",email);
        startActivity(intent);
    }


}
