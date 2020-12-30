package com.example.testrestapi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PremieumAdapter extends ArrayAdapter<Premieum> {
    public ArrayList<Premieum> list;
    public Context context;
    public ArrayList<Premieum> userchoose = new ArrayList<>();

    public PremieumAdapter(Context c , ArrayList<Premieum> l){
        super(c,0,l);
        list = l;
        context = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Premieum premium = getItem(position);
        if(convertView == null)
        {

            convertView = LayoutInflater.from(context).inflate(R.layout.lisview_premieum,parent,false);
             //holder = new ViewHolder(convertView);
            //convertView.setTag(holder);
        }
        ImageView image = convertView.findViewById(R.id.image);
        TextView title = convertView.findViewById(R.id.title);
        CheckBox checkbox = convertView.findViewById(R.id.checkBox);

        image.setImageResource(list.get(position).getImage());
        title.setText(list.get(position).getName_th());
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked)
               {
                    checkbox.setChecked(true);
                    premium.setCheck(true);
                    userchoose.add(premium);
               }
               else
               {
                   checkbox.setChecked(false);
                   premium.setCheck(false);
                   userchoose.add(premium);
               }
            }
        });
        //holder.checkBox.setTag(position);




        //holder.title.setText(list.get(position).getName_th());
        //holder.image.setImageResource(list.get(position).getImage());
        return convertView;
    }

    public ArrayList<Premieum> getUserChoice(){
        for(int count=0;count<userchoose.size();count++)
        {
            for(int count2=0;count2<userchoose.size();count2++)
            {
                if(userchoose.get(count2).getCheck() == false)
                {
                    userchoose.remove(count2);
                }
            }

        }
        return userchoose;
    }


}
