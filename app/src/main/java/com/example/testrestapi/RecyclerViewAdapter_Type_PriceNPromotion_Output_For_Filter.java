package com.example.testrestapi;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter extends RecyclerView.Adapter<RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter.RecyclerViewHolder> {

    public String port = ":8080";
    public String ip = "192.168.43.57";
    public ArrayList<Dorm_PricenPromotion_Model> list;
    public OnItemClickListener listener;
    public Context context;

    public int minPrice;
    public int maxPrice;
    public int codelang;
    public RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter(ArrayList<Dorm_PricenPromotion_Model> l, Context c,int codel){
        this.context = c;
        this.list = l;
        this.codelang = codel;

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlist_dorm_pricepromotion,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view,listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder hold,final int position) {
        final RecyclerViewAdapter_Type_PriceNPromotion_Output_For_Filter.RecyclerViewHolder holder = hold;
        String unit = context.getResources().getString(R.string.unit);
        int reddark = context.getResources().getColor(android.R.color.holo_red_dark);
        int c = context.getResources().getColor(android.R.color.black);
        if(list.get(position).getStatus_promotion() == 1)
        {
            Picasso.with(context).load(list.get(position).getImage()).into(holder.image);
            holder.nameDorm.setText(list.get(position).getDorm_name());
            holder.nameDorm.setTextColor(reddark);
            holder.distance.setText(list.get(position).getDistance()+" จาก "+list.get(position).getFaculty().getName_faculty());
            //holder.card.setCardBackgroundColor(Color.parseColor("#092D46"));
            if(codelang == 0)
            {
                holder.oldprice.setText(String.valueOf(list.get(position).getNewPrice())+" บาท/เดือน");
            }
            else
            {
                holder.oldprice.setText(String.valueOf(list.get(position).getNewPrice())+" baht/month");
            }

            holder.oldprice.setTypeface(Typeface.DEFAULT_BOLD);

            holder.nameDorm.setTypeface(Typeface.DEFAULT_BOLD);
            holder.distance.setTextColor(c);
            holder.tagsale.setVisibility(View.VISIBLE);
            //holder.prodetail.setVisibility(View.GONE);
            holder.prodetail.setText(String.valueOf(list.get(position).getOldPrice())+" "+unit);
            holder.prodetail.setPaintFlags(holder.prodetail.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            int r = context.getResources().getColor(R.color.greyShade);
            int color_sign_text = context.getResources().getColor(android.R.color.black);

        }

        else
        {
            Picasso.with(context).load(list.get(position).getImage()).into(holder.image);
            holder.nameDorm.setText(list.get(position).getDorm_name());
            holder.distance.setText(list.get(position).getDistance()+" จาก "+list.get(position).getFaculty().getName_faculty());
            holder.distance.setTextColor(c);
            holder.tagsale.setVisibility(View.GONE);
            if(codelang == 0)
            {
                holder.oldprice.setText(String.valueOf(list.get(position).getOldPrice())+" บาท/เดือน");
            }
            else
            {
                holder.oldprice.setText(String.valueOf(list.get(position).getOldPrice())+" baht/month");
            }
            holder.oldprice.setTypeface(Typeface.DEFAULT_BOLD);
            holder.prodetail.setVisibility(View.GONE);

        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public CardView card;
        public ImageView image;
        public TextView nameDorm;
        public TextView distance;
        public TextView price;
        public TextView oldprice;
        //public LinearLayout linear;
        public ImageView pin;
        public TextView prodetail;
        public TextView tagsale;
        public RecyclerViewHolder(@NonNull View v , final OnItemClickListener listener) {
            super(v);
            card = v.findViewById(R.id.cardview);
            image = v.findViewById(R.id.imageView);
            nameDorm = v.findViewById(R.id.dormname);
            distance = v.findViewById(R.id.subtitile);
            price = v.findViewById(R.id.price);
            oldprice = v.findViewById(R.id.oldprice);
            //linear = v.findViewById(R.id.price_promotion);
            pin = v.findViewById(R.id.imageView6);
            prodetail = v.findViewById(R.id.prodetail);
            tagsale = v.findViewById(R.id.tagsale);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
