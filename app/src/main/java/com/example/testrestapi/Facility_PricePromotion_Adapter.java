package com.example.testrestapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Facility_PricePromotion_Adapter extends BaseAdapter {

    public Context c;
    public ArrayList<Facility> list;
    public int codelang;
    public Facility_PricePromotion_Adapter(ArrayList<Facility> l,int langcode){
        this.list = l;
        this.codelang = langcode;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.facility_pricepromotion_layout,parent,false);
        ImageView facimage = v.findViewById(R.id.facimage);
        TextView factitle = v.findViewById(R.id.factitle);

        facimage.setImageResource(list.get(position).getImage());
        if(codelang == 0)
        {
            factitle.setText(list.get(position).getTextTH());
        }
        else
        {
            factitle.setText(list.get(position).getTextEN());
        }


        return v;
    }
}
