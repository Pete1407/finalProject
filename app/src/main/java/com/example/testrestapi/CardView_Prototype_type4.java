package com.example.testrestapi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class CardView_Prototype_type4 extends Fragment {
    public String dormname;
    public String logopath;
    public double distance = 0;
    public String ip = "192.168.43.57:8080";
    public CardView_Prototype_type4(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basicprototype4,container,false);
        TextView namedorm = view.findViewById(R.id.namedorm);
        ImageView logo = view.findViewById(R.id.logo);
        TextView subtitle = view.findViewById(R.id.subtitile);
        TextView address = view.findViewById(R.id.address);
        TextView price = view.findViewById(R.id.price);

        Bundle b = this.getArguments();
        dormname = b.getString("dormname");
        logopath = b.getString("logopath");

        namedorm.setText(dormname);
        Picasso.with(getActivity()).load(logopath).into(logo);
        subtitle.setText(String.valueOf(distance)+" จากคณะ XXX");
        String unit = getActivity().getResources().getString(R.string.unit);

        String path = "http://"+ip+"/dorm/getDormByDormName/"+dormname;
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject data = response;
                try {
                    String addr = data.getString("address");
                    address.setText(addr);

                    String path2 = "http://"+ip+"/InfoDorm/getInfoDormByDormName/"+dormname;
                    JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, path2, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int minPrice = response.getInt("minPrice");
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
                    queue.add(request2);
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
