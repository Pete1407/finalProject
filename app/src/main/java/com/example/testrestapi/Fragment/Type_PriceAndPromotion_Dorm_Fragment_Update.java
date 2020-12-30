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
import com.example.testrestapi.Type_BrandName_Dorm_Fragment_Update;
import com.example.testrestapi.UpdateActivity;
import com.example.testrestapi.UploadImageActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Type_PriceAndPromotion_Dorm_Fragment_Update extends Fragment {
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
    public String[] dormStyle;

    public String username;
    public String email;

    public String dormname;
    public String promotionDescribe;
    public int oldPrice;
    public int newPrice;

    public String port = ":8080";
    public String ip = "192.168.43.57";
    public Type_PriceAndPromotion_Dorm_Fragment_Update newInstance(){
        return new Type_PriceAndPromotion_Dorm_Fragment_Update();
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




       Bundle b = this.getArguments();
       dormname = b.getString("dormname");
       promotionDescribe = b.getString("promotionDetail");
       oldPrice = b.getInt("oldprice");
       newPrice = b.getInt("newprice");
       System.out.println("DETAIL PROMOTION: "+promotionDescribe);
       System.out.println("OLD PRICE: "+oldPrice);
       System.out.println("NEW PRICE: "+newPrice);

       dormStyle = getResources().getStringArray(R.array.dormtype);

       // เงื่อนไขถ้าหอพักมีโปรโมชัน
       if(!promotionDescribe.equals(""))
       {
           textCheck = true;
           noRadio.setChecked(false);
           yesRadio.setChecked(true);
           multitext.setVisibility(View.VISIBLE);
           before.setVisibility(View.VISIBLE);
           oldprice.setVisibility(View.VISIBLE);
           to.setVisibility(View.VISIBLE);
           newprice.setVisibility(View.VISIBLE);
           multitext.setVisibility(View.VISIBLE);
           multitext.setText(promotionDescribe);
           //oldprice.setText(String.valueOf(oldPrice));
           setRoomPrice(dormname);
           newprice.setText(String.valueOf(newPrice));
       }
       // ถ้าหอพักไม่มีโปรโมชัน
       else
       {
           textCheck = false;
           noRadio.setChecked(true);
           yesRadio.setChecked(false);
           multitext.setVisibility(View.GONE);
           before.setVisibility(View.GONE);
           oldprice.setVisibility(View.GONE);
           to.setVisibility(View.GONE);
           newprice.setVisibility(View.GONE);
           multitext.setVisibility(View.GONE);
       }


        // เซ็ทค่าต่างๆถ้าหอพักไม่มีโปรโมชัน เพื่อเอาไปเก็บในฐานข้อมูล
        if(textCheck == false)
        {
            multitext.setVisibility(View.GONE);
            before.setVisibility(View.GONE);
            oldprice.setVisibility(View.GONE);
            to.setVisibility(View.GONE);
            newprice.setVisibility(View.GONE);
            multitext.setVisibility(View.GONE);
            oldPrice = 0;
            newPrice = 0;
            promotionDescribe = "";
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
                oldPrice = 0;
                newPrice = 0;
                promotionDescribe = "";
            }
        });


        yesRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textCheck = true;
                multitext.setVisibility(View.VISIBLE);
                before.setVisibility(View.VISIBLE);
                oldprice.setVisibility(View.VISIBLE);
                //////// เซ็ทว่าถ้าหอพักที่ไม่มีโปรโมชันต้องการที่จะเซ็ทโปรโมชันก็ให้ใส่ราคาที่เคยเซ็ตใน InfoDorm  ////////
                setRoomPrice(dormname);
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
            String promotionInfo = "";
                String textFromMultitext = multitext.getText().toString();
               if(textCheck == false)
               {
                   oldp = 0;
                   newp = 0;
                   promotionInfo = "";
               }
               else
               {
                   oldp = Integer.parseInt(oldprice.getText().toString());
                   newp = Integer.parseInt(newprice.getText().toString());
               }

                System.out.println("Promotion Detail: "+textFromMultitext);
                System.out.println("OLD PRICE: "+oldp);
                System.out.println("NEW PRICE: "+newp);

                JSONObject input = new JSONObject();
             try {

                 if(textCheck == false)
                 {
                     input.put("dormID",dormname);
                     input.put("dormStyle",dormStyle[0]);
                     input.put("promotionDescribe",promotionInfo);
                     input.put("oldPrice",oldp);
                     input.put("newPrice",newp);
                 }
                 else
                 {
                     input.put("dormID",dormname);
                     input.put("dormStyle",dormStyle[0]);
                     input.put("promotionDescribe",textFromMultitext);
                     input.put("oldPrice",oldp);
                     input.put("newPrice",newp);
                 }


                 String updatepromotion = "http://"+ip+port+"/DormTypePriceNPromotion/updatePromotion/"+dormname;
                 JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, updatepromotion, input, new Response.Listener<JSONObject>() {
                     @Override
                     public void onResponse(JSONObject response) {
                            System.out.println("UPDATED DATA: "+response);
                            Intent i = new Intent(getActivity(), UpdateActivity.class);
                            getActivity().finish();
                            getActivity().startActivity(i);
                     }
                 }, new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {

                     }
                 });
                 RequestQueue queue = Volley.newRequestQueue(getActivity());
                 queue.add(request);
             }
             catch (JSONException ex){
                 ex.printStackTrace();
             }
            }
        });

        return v;
    }

    public void setRoomPrice(String dormname){
        String path = "http://"+ip+port+"/InfoDorm/"+"getInfoDormByDormName/"+dormname;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int minprice = response.getInt("minPrice");
                    oldprice.setText(String.valueOf(minprice));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }






}
