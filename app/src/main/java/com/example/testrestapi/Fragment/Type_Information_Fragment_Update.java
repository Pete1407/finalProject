package com.example.testrestapi.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.testrestapi.R;
import com.example.testrestapi.UpdateActivity;
import com.example.testrestapi.UploadImageActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class Type_Information_Fragment_Update extends Fragment {

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
    public String[] dormStyle;

    public String username;
    public String email;

    public String ip = "192.168.43.57";
    public String port = ":8080";

    public String dormname;
    public String infodetail;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.type_information_fragment,container,false);

        title = (TextView) v.findViewById(R.id.title);
        example = (TextView) v.findViewById(R.id.example);
        multitext =(EditText) v.findViewById(R.id.multitext);
        button = (Button) v.findViewById(R.id.button);

        dormStyle = getActivity().getResources().getStringArray(R.array.dormtype);

        Bundle b = this.getArguments();
        dormname = b.getString("dormname");
        System.out.println("NAME DORM: "+dormname);
        infodetail = b.getString("infodetail");
        System.out.println("INFO DETAIL IN FRAGMENT: "+infodetail);
        multitext.setText(infodetail);
     /*
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
      */


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String textFromMultitext = multitext.getText().toString();

               String updateInformation = "http://"+ip+port+"/DormTypeInformation/updateDetail/"+dormname;
                JSONObject json = new JSONObject();
             try {
                 json.put("dormID",dormname);
                 json.put("dormStyle",dormStyle[1]);
                 json.put("detail",textFromMultitext);
                 JsonObjectRequest updaterequest = new JsonObjectRequest(Request.Method.POST, updateInformation, json, new Response.Listener<JSONObject>() {
                     @Override
                     public void onResponse(JSONObject response) {
                        System.out.println("UPDATED DATA : "+response);
                        Intent intent = new Intent(getActivity(), UpdateActivity.class);
                        getActivity().finish();
                        getActivity().startActivity(intent);
                     }
                 }, new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {

                     }
                 });
                 RequestQueue queue = Volley.newRequestQueue(getActivity());
                 queue.add(updaterequest);
             }
             catch (JSONException ex){
                 ex.printStackTrace();
             }

            }
        });
        return v;
    }
}
