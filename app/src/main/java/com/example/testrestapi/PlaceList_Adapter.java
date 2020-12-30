package com.example.testrestapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaceList_Adapter extends BaseAdapter {

    public Context context;
    public ArrayList<Places> arraylist;

    public PlaceList_Adapter(Context c, ArrayList<Places> l){
        this.context = c;
        this.arraylist = l;
    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        ViewHolder holder;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.place_list,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.namePlace.setText(arraylist.get(position).getNameTH());

        return convertView;
    }

    public class ViewHolder{
        public ImageView image;
        public TextView namePlace;
        public TextView distance;
        public ViewHolder(View view){
               this.image = (ImageView) view.findViewById(R.id.image_decorate);
               this.namePlace = (TextView) view.findViewById(R.id.title);
               this.distance = (TextView) view.findViewById(R.id.subtitile);
        }
    }



}
