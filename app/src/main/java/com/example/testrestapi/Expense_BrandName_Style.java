package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Expense_BrandName_Style extends AppCompatActivity {
    public ActionBar bar;
    public String dormName;
    public TextView waterPrice;
    public TextView electroPrice;
    public TextView roomprice;
    public TextView depositPrice;
    public TextView commonfeeprice;
    public String port = ":8080";
    public String ip = "192.168.43.57";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense__brand_name__style);

        this.dormName = getIntent().getStringExtra("dormName");
        System.out.println("dorm Name: "+dormName);

        this.bar = getSupportActionBar();
        String title = getResources().getString(R.string.expenseTitle);
        this.bar.setTitle(title);
        this.bar.setDisplayHomeAsUpEnabled(true);
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        this.waterPrice = (TextView) findViewById(R.id.waterprice);
        this.electroPrice = (TextView) findViewById(R.id.electroprice);
        this.roomprice = (TextView) findViewById(R.id.roomPrice);
        this.depositPrice = (TextView) findViewById(R.id.deposit);
        this.commonfeeprice = (TextView) findViewById(R.id.commonFee);

        this.getExpense(dormName);
    }

    public void getExpense(String dormname){
        String expensePath = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dormname;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, expensePath, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    String baht = getResources().getString(R.string.baht);
                    int minPriceN = response.getInt("minPrice");
                    int waterPriceN = response.getInt("priceWater");
                    int electroPriceN = response.getInt("priceElectro");
                    int depositPriceN = response.getInt("depositPrice");
                    int commonfeeN = response.getInt("commonFee");
                    String typeWater = response.getString("typeWater");
                    String typeElectro = response.getString("typeElectro");

                     waterPrice.setText(String.valueOf(waterPriceN)+" "+baht+"("+typeWater+")");
                     electroPrice.setText(String.valueOf(electroPriceN)+" "+baht+"("+typeElectro+")");
                     roomprice.setText(String.valueOf(minPriceN)+" "+baht);
                     String a = getResources().getString(R.string.free);
                     if(depositPriceN == 0)
                     {
                         depositPrice.setText(a);
                     }
                     else
                     {
                         depositPrice.setText(String.valueOf(depositPriceN)+" "+baht);

                     }

                     if(commonfeeN == 0)
                     {
                         commonfeeprice.setText(a);
                     }
                     else
                     {
                         commonfeeprice.setText(String.valueOf(commonfeeN)+" "+baht);
                     }

                    //commonfeeprice;
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue q = Volley.newRequestQueue(this);
        q.add(request);
    }
}
