package com.example.testrestapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Place_ListView_Information_Adapter extends BaseAdapter {

    public ArrayList<Places> list;
    public Context context;
    public int langcode;

    public Place_ListView_Information_Adapter(Context c,ArrayList<Places> list,int codelang){
        this.context = c;
        this.list = list;
        this.langcode = codelang;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.place_list_view_layout,parent,false);
        TextView text = convertView.findViewById(R.id.namePlace);
        TextView distance = convertView.findViewById(R.id.distance);
     if(langcode == 0)
     {
         text.setText(list.get(position).getNameTH());
     }
     else
     {
         text.setText(list.get(position).getNameEN());
     }

        distance.setText(list.get(position).getDistance());
        return convertView;
    }
}
