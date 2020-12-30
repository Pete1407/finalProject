package com.example.testrestapi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter_Horizontal extends RecyclerView.Adapter<RecyclerViewAdapter_Horizontal.ViewHolder> {

    public Context context;
    public ArrayList<Facility> list;
    public int codelang;
    public RecyclerViewAdapter_Horizontal(Context c,ArrayList<Facility> l,int langcode){
        this.context = c;
        this.list = l;
        this.codelang = langcode;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
           holder.image.setImageResource(list.get(position).getImage());
         if(codelang == 0)
         {
             holder.title.setText(list.get(position).getTextTH());
         }
         else
         {
             holder.title.setText(list.get(position).getTextEN());
         }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
        }
    }

}
