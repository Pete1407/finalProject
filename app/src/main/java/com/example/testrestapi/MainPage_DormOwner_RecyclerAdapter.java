package com.example.testrestapi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.StringTokenizer;

public class MainPage_DormOwner_RecyclerAdapter extends RecyclerView.Adapter<MainPage_DormOwner_RecyclerAdapter.RecyclerViewHolder>{


    public String serverKey = "AIzaSyDvkyNSklVDDi9ZXAaARBcTmpAon01re8g";
    public ArrayList<InfoDorm> infodorm;
    public ArrayList<Dorm> list;
    public ArrayList<InfoDorm> infolist;
    public Context context;
    public LatLng thammasat = new LatLng(14.069550, 100.603292);
    public String collectDistanceAndDuration;

    public SharedPreferences sharedPreferences;

    public MainPage_DormOwner_RecyclerAdapter(Context c,ArrayList<Dorm> l , ArrayList<InfoDorm> infol){
        this.context = c;
        this.list = l;
        this.infolist = infol;

    }



    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mainpage_dormowner,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {
       final RecyclerViewHolder hold = holder;
       final int card_position = position;
        String urlDirectionAPI = "https://maps.googleapis.com/maps/api/directions/json?";

        LatLng thammasat = new LatLng(14.069567, 100.603288);
        LatLng mydorm = new LatLng(list.get(position).getLatitude(),list.get(position).getLongtitude());
        String part2 = "origin="+mydorm.latitude+","+mydorm.longitude+"&destination="+thammasat.latitude+","+thammasat.longitude+"&key="+serverKey;
        String distance;
        String duration;

        urlDirectionAPI = urlDirectionAPI + part2;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlDirectionAPI, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(context,"Get Data",Toast.LENGTH_LONG).show();
                JSONObject jsonObject = response;
                System.out.println(jsonObject);
                try
                {
                    JSONArray route = jsonObject.getJSONArray("routes");
                    JSONObject routes = route.getJSONObject(0);
                    JSONArray legs = routes.getJSONArray("legs");
                    JSONObject legss = legs.getJSONObject(0);
                    JSONObject distance = legss.getJSONObject("distance");
                    String distance_text = distance.getString("text");
                    JSONObject duration = legss.getJSONObject("duration");
                    String duration_text = duration.getString("text");
                    String unit_thai;
                    StringTokenizer token = new StringTokenizer(distance_text," ");
                    String distance_number_text = token.nextToken().trim();
                    String unit_text = token.nextToken().trim();
                    if(unit_text.equals("km"))
                    {
                        unit_text = "กม.";
                    }
                    String collect2 = distance_number_text+" "+ unit_text;
                    hold.distance.setText(collect2+" จากหอพักไปมหาวิทยาลัย");
                    hold.namedorm.setText(list.get(card_position).getDormID());
                    hold.address.setText(list.get(card_position).getAddress());
                    hold.price.setText("เริ่มต้นที่ "+String.valueOf(list.get(card_position).getInfo().getMinPrice()+" บาท/เดือน"));


                    System.out.println(" -----------------------------------------------------");
                    System.out.println(list.get(position).getDormID());
                    System.out.println(list.get(position).getInfo().getMinPrice());
                    System.out.println(list.get(position).getInfo().getMaxPrice());
                    System.out.println(list.get(position).getInfo().getTypeWater());
                    System.out.println(list.get(position).getInfo().getPriceWater());
                    System.out.println(list.get(position).getInfo().getTypeElectro());
                    System.out.println(list.get(position).getInfo().getPriceElectro());
                    System.out.println(list.get(position).getInfo().getTypeDeposit());
                    System.out.println(list.get(position).getInfo().getDepositPrice());
                    System.out.println(list.get(position).getInfo().getTypeCommonFee());
                    System.out.println(list.get(position).getInfo().getCommonFeePrice());
                    System.out.println(list.get(position).getInfo().getDormStyle());
                    System.out.println(list.get(position).getInfo().getCoverImage()+" *************");
                    System.out.println(list.get(position).getInfo().getImage360()+" ###############");
                    System.out.println(list.get(position).getInfo().getImagelist()+" %%%%%%%%%%%%%%");
                    System.out.println(" -----------------------------------------------------");


                    hold.delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context,UpdateActivity.class);
                            intent.putExtra("dormID",list.get(position).getDormID());
                            intent.putExtra("minPrice",list.get(position).getInfo().getMinPrice());
                            intent.putExtra("maxPrice",list.get(position).getInfo().getMaxPrice());
                            intent.putExtra("typeWater",list.get(position).getInfo().getTypeWater());
                            intent.putExtra("priceWater",list.get(position).getInfo().getPriceWater());
                            intent.putExtra("typeElectro",list.get(position).getInfo().getTypeElectro());
                            intent.putExtra("priceElectro",list.get(position).getInfo().getPriceElectro());
                            intent.putExtra("typeDeposit",list.get(position).getInfo().getTypeDeposit());
                            intent.putExtra("depositPrice",list.get(position).getInfo().getDepositPrice());
                            intent.putExtra("typeCommonFee",list.get(position).getInfo().getTypeCommonFee());
                            intent.putExtra("commonFeePrice",list.get(position).getInfo().getCommonFeePrice());
                            intent.putExtra("dormStyle",list.get(position).getInfo().getDormStyle());
                            intent.putExtra("coverImage",list.get(position).getInfo().getCoverImage());
                            intent.putExtra("image360",list.get(position).getInfo().getImage360());
                            intent.putStringArrayListExtra("imagelist",list.get(position).getInfo().getImagelist());
                            context.startActivity(intent);
                        }
                    });

                    hold.modify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context,UpdateActivity.class);

                        context.startActivity(i);

                    }
                });
                }
                catch(JSONException ex)
                {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);


            //holder.price.setText(String.valueOf("เริ่มต้นที่ "+infolist.get(position).getMinPrice()+" บาท/เดือน"));





    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public String calculateDistance(double Lat,double Longti){

        return collectDistanceAndDuration;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView namedorm;
        public TextView distance;
        public TextView address;
        public TextView price;
        public Button modify;
        public Button delete;

        public RecyclerViewHolder(@NonNull View itemView) {

            super(itemView);
            image = itemView.findViewById(R.id.image);
            namedorm =  itemView.findViewById(R.id.nameDorm);
            distance =  itemView.findViewById(R.id.subtitile);
            address =  itemView.findViewById(R.id.address);
            price =  itemView.findViewById(R.id.price);
            modify =  itemView.findViewById(R.id.modify);
            delete = itemView.findViewById(R.id.delete);



        }
    }
}
