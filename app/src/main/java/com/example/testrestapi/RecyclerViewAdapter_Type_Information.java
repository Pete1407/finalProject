package com.example.testrestapi;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerViewAdapter_Type_Information extends RecyclerView.Adapter<RecyclerViewAdapter_Type_Information.RecyclerViewHolder> {
   public ArrayList<Dorm_Information_Model> list;
   public OnItemClickListener listener;
    public Context context;
    public int numberofLikes;
    public String port = ":8080";
    public String ip = "192.168.43.57";
    public int languagecode;
    public RecyclerViewAdapter_Type_Information(ArrayList<Dorm_Information_Model> l, Context c,int codelang){
        this.list = l;
        this.context = c;
        languagecode = codelang;
    }

    public void setOnItemClickListener(OnItemClickListener l){
        this.listener = l;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlist_dorm_information,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(v,listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
        String unit = context.getResources().getString(R.string.unit);
            String favorite = "http://"+ip+port+"/favorite/getAllFavorite";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, favorite, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray data = response;
                numberofLikes = 0;
                String dormnamebyPosition = list.get(position).getName();
             try {
                 for(int count=0;count<data.length();count++)
                 {
                     JSONObject obj = data.getJSONObject(count);
                     int codestyle = obj.getInt("codeStyle");
                     if(codestyle == 1)
                     {
                        JSONArray array = obj.getJSONArray("dormname");

                        for(int count2=0;count2<array.length();count2++)
                        {
                            //Toast.makeText(context,array.getString(count2),Toast.LENGTH_LONG).show();
                            if(array.getString(count2).equals(dormnamebyPosition))
                            {
                                numberofLikes++;
                            }
                        }
                        holder.likes.setText(String.valueOf(numberofLikes));

                     } //numberofLikes = 0;
                 }
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
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);



            holder.name.setText(list.get(position).getName());
            int black = context.getResources().getColor(R.color.black);
            //holder.name.setTextColor(black);
            holder.name.setTypeface(Typeface.DEFAULT_BOLD);
            Picasso.with(context).load(list.get(position).getImage()).into(holder.image);
            holder.address.setText(list.get(position).getAddress());
            holder.address.setTextColor(black);
            holder.distance.setTextColor(black);
            holder.distance.setText(String.valueOf(list.get(position).getDistance())+" จาก "+list.get(position).getFaculty().getName_faculty());
            holder.pin.setImageResource(R.drawable.pin_promotion);

            final float scale = context.getResources().getDisplayMetrics().density;
            int padding_5dp = (int) (5 * scale + 0.5f);
            int padding_15dp = (int) (15 * scale + 0.5f);
            int padding_10dp = (int) (10 * scale + 0.5f);
            int size30 = (int) (30 * scale + 0.5f);
            int margin_10dp = (int) (10 * scale + 0.5f);
            holder.linearFac.removeAllViews();

                 for(int count2=0;count2<2;count2++)
                 {
                     int width = size30;
                     int height = size30;
                     TextView infotext = new TextView(context);
                     infotext.setText(list.get(position).getDetail()[count2]);
                     infotext.setTextColor(Color.BLACK);
                     infotext.setGravity(Gravity.CENTER_HORIZONTAL);
                     infotext.setBackgroundResource(R.drawable.background_for_textview);
                     infotext.setPadding(padding_5dp,padding_5dp,padding_5dp,padding_5dp);
                     LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                     param.setMargins(padding_5dp,padding_5dp,padding_5dp,padding_5dp);
                     infotext.setLayoutParams(param);
                     holder.linearFac.addView(infotext);
                 }

               //holder.infotextview.setText(list.get(position).getDetail()[0]);



            if(languagecode == 0)
            {
                holder.price.setText(String.valueOf(list.get(position).getPrice()+" "+"บาท/เดือน"));
            }
            else if(languagecode == 1)
            {
                holder.price.setText(String.valueOf(list.get(position).getPrice()+" "+"baht/month"));
            }

                int greenPrice = context.getResources().getColor(R.color.colorPrice);
                //holder.price.setTextColor(greenPrice);
                holder.price.setTypeface(Typeface.DEFAULT_BOLD);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public ImageView image;
        public TextView address;
        public TextView distance;
        public TextView detail;
        public ImageView facility;
        public TextView price;
        public LinearLayout linear;
        public LinearLayout linearFac;
        public ImageView pin;
        public TextView infotextview;
        public TextView likes;
        public RecyclerViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.dormname);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            address = (TextView) itemView.findViewById(R.id.address);
            distance = (TextView) itemView.findViewById(R.id.subtitile);
            price = (TextView) itemView.findViewById(R.id.price);
            linear = (LinearLayout) itemView.findViewById(R.id.FacilityTitle);
            pin = itemView.findViewById(R.id.imageView7);
            //infotextview = itemView.findViewById(R.id.infotextview);
            likes = itemView.findViewById(R.id.likes);
            linearFac = itemView.findViewById(R.id.FacilityTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
