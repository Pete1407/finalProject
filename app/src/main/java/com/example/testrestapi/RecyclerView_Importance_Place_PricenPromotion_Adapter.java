package com.example.testrestapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerView_Importance_Place_PricenPromotion_Adapter extends RecyclerView.Adapter<RecyclerView_Importance_Place_PricenPromotion_Adapter.ViewHolder> {

    public Context context;
    public ArrayList<Places> list;
    public int codelang;
    public RecyclerView_Importance_Place_PricenPromotion_Adapter(Context c,ArrayList<Places> l,int langcode){
        this.context = c;
        this.list = l;
        this.codelang = langcode;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_importance_place_priceandpromotion,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String dis = "";
        if(codelang == 0)
        {
            holder.dormTitle.setText(list.get(position).getNameTH());
            dis = "ระยะทาง";
        }
        else
        {
            holder.dormTitle.setText(list.get(position).getNameEN());
            dis = "distance";
        }

           // dis = context.getResources().getString(R.string.distance);
            holder.distance.setText(dis+" "+list.get(position).getDistance());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dormTitle;
        public TextView distance;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dormTitle = itemView.findViewById(R.id.namePlace);
            distance = itemView.findViewById(R.id.distance);
        }
    }
}
