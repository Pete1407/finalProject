package com.example.testrestapi.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.testrestapi.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class Detail_Prototype_type4 extends Fragment {
    public ImageView image;
    public TextView name;
    public TextView numberofFavorite;
    public RecyclerView recyclerview;
    public TextView description;

    public String port = ":8080";
    public String ip = "192.168.43.57";
    public String username;
    public String email;
    public String namedorm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_prototype_type4,container,false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("file_for_dormowner", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username","no value");
        email = sharedPreferences.getString("email","no value");

        Bundle b = this.getArguments();
        namedorm = b.getString("dormname");
        image = v.findViewById(R.id.image);
        name = v.findViewById(R.id.name);
        numberofFavorite = v.findViewById(R.id.numberofFavorite);
        recyclerview = v.findViewById(R.id.recycler);
        description = v.findViewById(R.id.description);
        name.setText(namedorm);
        int black = getActivity().getResources().getColor(R.color.black);
        name.setTextSize(18);
        name.setTextColor(black);
        name.setTypeface(Typeface.DEFAULT_BOLD);
        String textfav = getActivity().getResources().getString(R.string.pressFavorite);
        int zero = 0;
        int red = getActivity().getResources().getColor(R.color.red);
        numberofFavorite.setText(zero+" "+textfav);
        numberofFavorite.setTextColor(red);
        getImage360(email,username);
        getExpense(namedorm);
        return v;
    }

    public void getImage360(String email,String username){
        System.out.println("HHHHHH");
        String path = "http://"+ip+port+"/Image360/getImage360byUsernameAndEmail/"+username+"/"+email;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String imagepath = response.getString("image360");
                    Glide.with(getActivity()).load(imagepath).apply(bitmapTransform(new BlurTransformation(22))).into(image);
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


    public void getExpense(String dormname){
        RequestQueue q = Volley.newRequestQueue(getActivity());
        String expensePath = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dormname;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, expensePath, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject res) {
                try
                {
                    String describe = res.getString("description");
                    description.setText(describe);




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


        q.add(request);
    }
}
