package com.example.testrestapi;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlaceAdapter extends BaseAdapter {

    public String geocoding = "";
    public Context context;
    public ArrayList<Places> list;

    public String newServerKey = "AIzaSyAIm2bf5ksXaJTiIVoBHFuwyW4ebWOdLLw";

    String urlDirection;
    public LatLng dormOrigin;
    //public LatLng thammasatDestination = new LatLng(14.069685, 100.603324);

    public PlaceAdapter(ArrayList<Places> l,Context c, LatLng dormThatUserChoose){
        this.context = c;
        this.list = l;
        this.dormOrigin = dormThatUserChoose;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        final ViewHolder holder;
        if(convertView == null)
        {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.format_place,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

     /*   if(list.get(position).getIdFaculty()==0)
        {
            for(int count=0;count<list.get(position).getPlace().length;count++)
            {
                holder.namePlace.setText(list.get(position).getPlace()[count].getNameTH());
                holder.distance.setText(String.valueOf(list.get(position).getPlace()[count].getDistance()));
            }
        }

      */

            holder.namePlace.setText(list.get(position).getNameTH());
            holder.namePlace.setTypeface(Typeface.DEFAULT_BOLD);

           // double la_destination = thammasatDestination.latitude;
           // double long_destination = thammasatDestination.longitude;

            urlDirection = "https://maps.googleapis.com/maps/api/directions/json?"+"origin="+dormOrigin.latitude+","+dormOrigin.longitude+"&destination="
                    +list.get(position).getLatitude_place()+","+list.get(position).getLongtitude_place()+"&key="+newServerKey;

            System.out.println(list.get(position).getNameTH());
            System.out.println(list.get(position).getLatitude_place()+"   "+list.get(position).getLongtitude_place());

        JsonObjectRequest getDistanceRequest = new JsonObjectRequest(Request.Method.GET, urlDirection, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject json = response;
                System.out.println(json);

                JSONArray route = null;
                try
                {
                    route = json.getJSONArray("routes");
                    JSONObject routes = route.getJSONObject(0);
                    JSONArray legs = routes.getJSONArray("legs");
                    JSONObject legss = legs.getJSONObject(0);
                    JSONObject distance = legss.getJSONObject("distance");
                    String distance_text = distance.getString("text");


                   // System.out.println(distance_text);
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

        RequestQueue queue = Volley.newRequestQueue(context);

        queue.add(getDistanceRequest);
            System.out.println("URL: "+urlDirection);
            //holder.distance.setText(1+" กิโลเมตร");



        return convertView;
    }

    public class ViewHolder{
        public TextView namePlace;
        public TextView distance;

        public ViewHolder(View v){
            namePlace = (TextView) v.findViewById(R.id.title);
            distance = (TextView) v.findViewById(R.id.subtitile);
        }
    }
}
