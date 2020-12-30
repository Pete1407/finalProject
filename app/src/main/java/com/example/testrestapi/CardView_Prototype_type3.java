package com.example.testrestapi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CardView_Prototype_type3 extends Fragment {
    public String ip = "192.168.43.57:8080";
    public String dormname;
    public ArrayList<Integer> imagelist;
    public String unit;
    public CardView_Prototype_type3(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basicprototype3,container,false);
        ImageView coverimage = view.findViewById(R.id.image);
        TextView name = view.findViewById(R.id.namedorm);
        TextView certify = view.findViewById(R.id.certify);
        TextView subtitle = view.findViewById(R.id.subtitile);
        TextView price = view.findViewById(R.id.price);
        LinearLayout linear = view.findViewById(R.id.linearlayout);
        double distance = 0;
        subtitle.setText(String.valueOf(distance)+" จากคณะ XXX");
        imagelist = new ArrayList<>();
        Bundle b = this.getArguments();
        dormname = b.getString("dormname");
        imagelist = b.getIntegerArrayList("imagelist");
        String certified = getActivity().getResources().getString(R.string.verifybygov);
        unit = getActivity().getResources().getString(R.string.unit);
        int blue = getActivity().getResources().getColor(R.color.colortext);
        certify.setText(certified);
        certify.setTextColor(blue);
        int pigblood = getActivity().getResources().getColor(R.color.pigblood);
        name.setText(dormname);
        name.setTextColor(pigblood);
        final float scale = getActivity().getResources().getDisplayMetrics().density;
        int size30 = (int) (30 * scale + 0.5f);
        int padding_10dp = (int) (15 * scale + 0.5f);
     if(imagelist.size() >5)
     {
         for(int count=0;count<5;count++)
         {
             final ImageView icon = new ImageView(getActivity());
             icon.setImageResource(imagelist.get(count));
             int width = size30;
             int height = size30;
             LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width,height);
             parms.setMargins(padding_10dp,0,0,0);
             icon.setPadding(5,0,5,0);
             icon.setLayoutParams(parms);
             linear.addView(icon);
         }
     }
     else
     {
         for(int count=0;count<imagelist.size();count++)
         {
             final ImageView icon = new ImageView(getActivity());
             icon.setImageResource(imagelist.get(count));
             int width = size30;
             int height = size30;
             LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width,height);
             parms.setMargins(padding_10dp,0,0,0);
             icon.setPadding(5,0,5,0);
             icon.setLayoutParams(parms);
             linear.addView(icon);
         }
     }

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String path1 = "http://"+ip+"/InfoDorm/getInfoDormByDormName/"+dormname;
        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, path1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject j = response;
                try {
                    int minPrice = j.getInt("minPrice");
                    price.setText(String.valueOf(minPrice)+" "+unit);
                }
                catch (JSONException ex){
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request1);
        return view;
    }
}
