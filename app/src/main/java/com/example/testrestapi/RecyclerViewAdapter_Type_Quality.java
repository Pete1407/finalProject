package com.example.testrestapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter_Type_Quality extends RecyclerView.Adapter<RecyclerViewAdapter_Type_Quality.RecyclerViewHolder> {

    public ArrayList<Dorm_Quality_Model> list;
    public OnItemClickListener listener;
    public Context context;
    public int codelang;
    public void setOnItemClickListener(OnItemClickListener li){
        this.listener = li;
    }

    public RecyclerViewAdapter_Type_Quality(ArrayList<Dorm_Quality_Model> l , Context c){
        this.list = l;
        this.context = c;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlist_dorm_quality,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(v,listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        SharedPreferences changelanguage = context.getSharedPreferences("language",Context.MODE_PRIVATE);
        codelang = changelanguage.getInt("languageCode",2);

        int black = context.getResources().getColor(R.color.black);
        String certify = context.getResources().getString(R.string.verifybygov);
        holder.name.setText(list.get(position).getName());
        int pigblood = context.getResources().getColor(R.color.pigblood);
        holder.name.setTextColor(pigblood);

        //holder.certify.setText(list.get(position).getCertify());
        holder.certify.setTextColor(Color.BLUE);
        holder.certify.setTypeface(Typeface.DEFAULT_BOLD);
        holder.distance.setTextColor(black);
        holder.distance.setText(String.valueOf(list.get(position).getDistance()+" จาก"+list.get(position).getFaculty().getName_faculty()));
        Picasso.with(context).load(list.get(position).getImage()).into(holder.image);
        holder.pin.setImageResource(R.drawable.pin_promotion);
        final float scale = context.getResources().getDisplayMetrics().density;
        int size30 = (int) (30 * scale + 0.5f);
        int padding_10dp = (int) (15 * scale + 0.5f);

        holder.linear.removeAllViews();

        if(list.get(position).getPremieum().length <5)
        {
            for(int count=0;count<list.get(position).getPremieum().length;count++)
            {
                ImageView icon = new ImageView(context);
                icon.setImageResource(list.get(position).getPremieum()[count]);
                int width = size30;
                int height = size30;
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width,height);
                parms.setMargins(padding_10dp,0,0,0);
                icon.setPadding(5,0,5,0);
                icon.setLayoutParams(parms);
                holder.linear.addView(icon);
            }
        }
        else
        {
            for(int count=0;count<5;count++)
            {
                ImageView icon = new ImageView(context);
                icon.setImageResource(list.get(position).getPremieum()[count]);
                int width = size30;
                int height = size30;
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width,height);
                parms.setMargins(padding_10dp,0,0,0);
                icon.setPadding(5,0,5,0);
                icon.setLayoutParams(parms);
                holder.linear.addView(icon);
            }
        }

        String unit = context.getResources().getString(R.string.unit);
        if(codelang == 0)
        {
            holder.certify.setText("ได้รับการรับรองจากหน่วยงานรัฐ");
            holder.price.setText(String.valueOf(list.get(position).getPrice()+" บาท/เดือน"));
        }
        else
        {
            holder.certify.setText("Certified by government agency");
            holder.price.setText(String.valueOf(list.get(position).getPrice()+" baht/month"));
        }

        holder.price.setTypeface(Typeface.DEFAULT_BOLD);
        //holder.card.setCardBackgroundColor(Color.parseColor("#D8D6D6"));
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView certify;
        public TextView distance;
        public LinearLayout linear;
        public TextView price;
        public CardView card;
        public ImageView image;
        public ImageView pin;
        public RecyclerViewHolder(@NonNull View v , final OnItemClickListener listener) {
            super(v);
            name = (TextView) v.findViewById(R.id.namedorm);
            certify = (TextView) v.findViewById(R.id.certify);
            distance = (TextView) v.findViewById(R.id.subtitile);
            linear = (LinearLayout) v.findViewById(R.id.linearlayout);
            price = (TextView) v.findViewById(R.id.price);
            card = (CardView) v.findViewById(R.id.cardview);
            image = (ImageView) v.findViewById(R.id.image);
            pin = v.findViewById(R.id.imageView8);
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
