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

public class RecyclerViewAdapter_Type_Brandname extends RecyclerView.Adapter<RecyclerViewAdapter_Type_Brandname.RecyclerViewHolder> {

    public ArrayList<Dorm_BrandName_Model> list;
    public Context context;
    public OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener lis){
            this.listener = lis;
    }

    public RecyclerViewAdapter_Type_Brandname(ArrayList<Dorm_BrandName_Model> l , Context c) {
        this.list = l;
        this.context = c;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardlist_dorm_brandname,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view,listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        SharedPreferences lang = context.getSharedPreferences("language",Context.MODE_PRIVATE);
        int codelang = lang.getInt("languageCode",2);
        holder.logo.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.with(context).load(list.get(position).getLogo()).into(holder.logo);
        //Typeface fontext = Typeface.createFromAsset(context.getAssets(),"fonts/Fanwood.otf");
        //holder.name.setTypeface(fontext);
            holder.name.setText(list.get(position).getName());
            holder.distance.setText(String.valueOf(list.get(position).getDistance()+" จาก"+list.get(position).getFaculty().getName_faculty()));
            holder.distance.setTextColor(Color.YELLOW);
            holder.address.setText(list.get(position).getAddress());
            if(codelang == 0)
            {
                holder.price.setText(String.valueOf(list.get(position).getPrice()+" บาท/เดือน"));
            }
            else
            {
                holder.price.setText(String.valueOf(list.get(position).getPrice()+" baht/month"));
            }

            holder.card.setCardBackgroundColor(Color.parseColor("#140303"));
            holder.pin.setImageResource(R.drawable.pin_promotion);
             final float scale = context.getResources().getDisplayMetrics().density;
        int size30 = (int) (30 * scale + 0.5f);
        //int padding_10dp = (int) (15 * scale + 0.5f);
        int padding_5dp = (int) (2 * scale + 0.5f);


        for(int count=0;count<list.get(position).getPremieum().length;count++)
        {
            final TextView text = new TextView(context);
            text.setText(list.get(position).getPremieum()[count]);
            text.setBackgroundResource(R.drawable.background_for_textview);
            int width = size30;
            int height = size30;
            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            parms.setMargins(0,0,10,0);

            text.setPadding(padding_5dp,padding_5dp,padding_5dp,padding_5dp);
            text.setLayoutParams(parms);
            text.setTextColor(Color.parseColor("#FE730F"));;
            //holder.linear.addView(text);
        }
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public ImageView logo;
        public TextView distance;
        public TextView address;
        //public LinearLayout linear;
        public TextView price;
        public CardView card;
        public ImageView pin;
        public RecyclerViewHolder(@NonNull View v , final OnItemClickListener listener) {
            super(v);
            name = (TextView) v.findViewById(R.id.namedorm);
            logo = (ImageView) v.findViewById(R.id.logo);
            distance = (TextView) v.findViewById(R.id.subtitile);
            address = (TextView) v.findViewById(R.id.address);
            //linear = (LinearLayout) v.findViewById(R.id.linear);
            price = (TextView) v.findViewById(R.id.price);
            card = (CardView) v.findViewById(R.id.cardview);
            pin = (ImageView) v.findViewById(R.id.imageView2);
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
