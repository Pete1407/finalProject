package com.example.testrestapi.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testrestapi.R;
import com.example.testrestapi.UploadImageActivity;

public class Type_PriceAndPromotion_Dorm_Fragment extends Fragment {
    public TextView title;
    public EditText multitext;
    public TextView before;
    public EditText oldprice;
    public TextView to;
    public EditText newprice;

    public Button button;

    public RadioButton yesRadio;
    public RadioButton noRadio;
    public boolean textCheck = false;

    // variable for store database INFO DORM
    public int minPrice;
    public int maxPrice;
    public String typeWater;
    public int  priceWater;
    public String typeElectro;
    public int priceElectro;
    public String typeDeposit;
    public int depositPrice;
    public String typeCommonFee;
    public int commonFee;
    public String description;
    public String dormID;
    public String dormStyle;

    public String username;
    public String email;

    public Type_PriceAndPromotion_Dorm_Fragment newInstance(){
        return new Type_PriceAndPromotion_Dorm_Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.type_priceandpromotion_dorm_fragment,container,false);
        title = (TextView) v.findViewById(R.id.title);
        multitext = (EditText) v.findViewById(R.id.multitext);
        before = (TextView) v.findViewById(R.id.before);

        oldprice = (EditText) v.findViewById(R.id.oldprice);
        to = (TextView) v.findViewById(R.id.to);
        newprice = (EditText) v.findViewById(R.id.newprice);

        button = (Button)v.findViewById(R.id.button);


        yesRadio = (RadioButton) v.findViewById(R.id.yes);
        noRadio = (RadioButton) v.findViewById(R.id.no);

        noRadio.setChecked(true);

       Bundle b = this.getArguments();

        dormID = b.getString("dormID");
        minPrice = b.getInt("minPrice");
        maxPrice = b.getInt("maxPrice");
        typeWater = b.getString("typeWater");
        priceWater = b.getInt("priceWater");
        typeElectro = b.getString("typeElectro");
        priceElectro = b.getInt("priceElectro");
        typeDeposit = b.getString("typeDeposit");
        depositPrice = b.getInt("depositPrice");
        typeCommonFee = b.getString("typeCommonFee");
        commonFee = b.getInt("commonFee");
        description = b.getString("description");
        dormStyle = b.getString("dormStyle");

        this.username = b.getString("username");
        this.email = b.getString("email");
        //Toast.makeText(getActivity(),"Email: "+email+"\n"+"Username: "+username,Toast.LENGTH_LONG).show();

        oldprice.setText(String.valueOf(minPrice));

        if(textCheck == false)
        {
            multitext.setVisibility(View.GONE);
            before.setVisibility(View.GONE);
            oldprice.setVisibility(View.GONE);
            to.setVisibility(View.GONE);
            newprice.setVisibility(View.GONE);
            multitext.setVisibility(View.GONE);
        }


        noRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textCheck = false;
                multitext.setVisibility(View.GONE);
                before.setVisibility(View.GONE);
                oldprice.setVisibility(View.GONE);
                to.setVisibility(View.GONE);
                newprice.setVisibility(View.GONE);
                multitext.setVisibility(View.GONE);
            }
        });

        yesRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textCheck = true;
                multitext.setVisibility(View.VISIBLE);
                before.setVisibility(View.VISIBLE);
                oldprice.setVisibility(View.VISIBLE);
                to.setVisibility(View.VISIBLE);
                newprice.setVisibility(View.VISIBLE);
                multitext.setVisibility(View.VISIBLE);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int oldp;
            int newp;
            String textFromMultitext = multitext.getText().toString();
               if(textCheck == false)
               {
                   oldp = 0;
                   newp = 0;
               }
               else
               {
                   oldp = Integer.parseInt(oldprice.getText().toString());
                   newp = Integer.parseInt(newprice.getText().toString());
               }

                System.out.println(textFromMultitext);
                    Intent i = new Intent(getActivity(),UploadImageActivity.class);
                    i.putExtra("dormID",dormID);
                    i.putExtra("minPrice",minPrice);
                    i.putExtra("maxPrice",maxPrice);
                    i.putExtra("typeWater",typeWater);
                    i.putExtra("priceWater",priceWater);
                    i.putExtra("typeElectro",typeElectro);
                    i.putExtra("priceElectro",priceElectro);
                    i.putExtra("typeDeposit",typeDeposit);
                    i.putExtra("depositPrice",depositPrice);
                    i.putExtra("typeCommonFee",typeCommonFee);
                    i.putExtra("commonFee",commonFee);
                    i.putExtra("description",description);
                    i.putExtra("dormStyle",dormStyle);
                    i.putExtra("promotionDescribe",textFromMultitext);
                    i.putExtra("OldPrice",oldp);
                    i.putExtra("NewPrice",newp);
                    i.putExtra("username",username);
                    i.putExtra("email",email);
                    startActivity(i);

            }
        });

        return v;
    }






}
