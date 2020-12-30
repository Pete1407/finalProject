package com.example.testrestapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Gallery_PricePromotion_Adapter extends BaseAdapter {

    public Context context;
    public ArrayList<String> list;
    public ArrayList<Bitmap> bitmaplist;

    public Gallery_PricePromotion_Adapter(Context c,ArrayList<String> l){
        this.list = l;
        this.context = c;
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
        View v = inflater.inflate(R.layout.gv_item,parent,false);
        ImageView image = (ImageView) v.findViewById(R.id.gallery);
        image.setPadding(3,3,3,3);
          Picasso.with(context).load(list.get(position)).into(image);
        return v;
    }
}
