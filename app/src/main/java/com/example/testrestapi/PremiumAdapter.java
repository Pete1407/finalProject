package com.example.testrestapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PremiumAdapter extends BaseAdapter {

    public ArrayList<Premieum> list;
    public Context context;
    public int codelanguage;

    public PremiumAdapter(Context c,ArrayList<Premieum> l,int langcode){
        this.context = c;
        this.list = l;
        this.codelanguage = langcode;
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
        View v = inflater.inflate(R.layout.premium_layout,parent,false);
        ImageView image = v.findViewById(R.id.image);
        TextView name = v.findViewById(R.id.name);
        image.setImageResource(list.get(position).getImage());

        if(codelanguage == 0)
        {
            name.setText(list.get(position).getName_th());
        }
        else if(codelanguage == 1)
        {
            name.setText(list.get(position).getName_en());
        }

        return v;
    }
}
