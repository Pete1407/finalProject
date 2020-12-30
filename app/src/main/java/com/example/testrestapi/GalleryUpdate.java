package com.example.testrestapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.BitSet;

public class GalleryUpdate extends BaseAdapter {

    public Context context;
    public ArrayList<String> list;
    public ArrayList<Bitmap> bitmaplist;
    public boolean checkstatus;
    public String blank ;
    public GalleryUpdate(Context c, ArrayList<String> l,boolean checksta){
        context = c;
        list = l;
        checkstatus = checksta;
    }

    public GalleryUpdate(Context c, ArrayList<Bitmap> l,boolean checksta,String b){
        context = c;
        bitmaplist = l;
        checkstatus = checksta;
        blank = b;
    }

    @Override
    public int getCount() {
        if(checkstatus == false)
        {
            return list.size();
        }
        else
        {
            return bitmaplist.size();
        }
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
       if(checkstatus == false)
       {
           Picasso.with(context).load(list.get(position)).skipMemoryCache().into(image);
       }
       else
       {
           image.setImageBitmap(bitmaplist.get(position));
       }

        return v;
    }

    public void updateImageList(ArrayList<String> images) {

        if(list != null)
        {
            list.removeAll(list);
            list.addAll(images);
        }
        else {
            list = list;
        }

        notifyDataSetChanged();
    }
}
