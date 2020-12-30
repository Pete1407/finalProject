package com.example.testrestapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FacilityAdapter extends BaseAdapter {

    public ArrayList<Facility> faclist;
    public int langcode;
    public FacilityAdapter(ArrayList<Facility> list,int code){
        faclist = list;
        langcode = code;
    }

    @Override
    public int getCount() {
        return faclist.size();
    }

    @Override
    public Object getItem(int position) {
        return faclist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.facility_pricepromotion_layout,parent,false);
        ImageView image = v.findViewById(R.id.facimage);
        TextView text = v.findViewById(R.id.factitle);
        image.setImageResource(faclist.get(position).getImage());
        if(langcode == 0)
        {
            text.setText(faclist.get(position).getTextTH());
        }
        else
        {
            text.setText(faclist.get(position).getTextEN());
        }
        return v;
    }
}
