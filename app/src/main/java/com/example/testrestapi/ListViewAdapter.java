package com.example.testrestapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<Places> list;
    public int codelang;

    public ListViewAdapter(Context c, ArrayList<Places> l,int langcode){
        this.context = c;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.place_list_view_layout,parent,false);
        ViewHolder holder = new ViewHolder(convertView);
     if(codelang == 0)
     {
         holder.namePlace.setText(list.get(position).getNameTH());
     }
     else
     {
         holder.namePlace.setText(list.get(position).getNameEN());
     }

        holder.distance.setText(list.get(position).getDistance());

        return convertView;
    }

    public class ViewHolder{
        public TextView namePlace;
        public TextView distance;
        public ViewHolder(View itemView){
            this.namePlace = (TextView) itemView.findViewById(R.id.namePlace);
            this.distance = (TextView) itemView.findViewById(R.id.distance);
        }
    }
}
