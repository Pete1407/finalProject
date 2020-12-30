package com.example.testrestapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Gallery_BrandName_Adapter extends BaseAdapter {

    public Context context;
    public ArrayList<String> pathlist;

    public Gallery_BrandName_Adapter(Context c,ArrayList<String> path){
        this.context = c;
        this.pathlist = path;
    }

    @Override
    public int getCount() {
        return pathlist.size();
    }

    @Override
    public Object getItem(int position) {
        return pathlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.gv_item,parent,false);
        ImageView image = convertView.findViewById(R.id.gallery);
        Picasso.with(context).load(pathlist.get(position)).into(image);
        return convertView;
    }


}
