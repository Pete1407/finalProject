package com.example.testrestapi.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testrestapi.R;
import com.example.testrestapi.UploadImageActivity;

public class Type_Information_Fragment extends Fragment {

    public TextView title;
    public TextView example;
    public EditText multitext;
    public Button button;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.type_information_fragment,container,false);

        title = (TextView) v.findViewById(R.id.title);
        example = (TextView) v.findViewById(R.id.example);
        multitext =(EditText) v.findViewById(R.id.multitext);
        button = (Button) v.findViewById(R.id.button);

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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String textFromMultitext = multitext.getText().toString();

                Intent i = new Intent(getActivity(), UploadImageActivity.class);
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
                i.putExtra("information",textFromMultitext);
                i.putExtra("username",username);
                i.putExtra("email",email);
                startActivity(i);
            }
        });
        return v;
    }
}
