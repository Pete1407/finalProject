package com.example.testrestapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PremieumAdapter_Update extends ArrayAdapter<Premieum> {
    public ArrayList<Premieum> list;
    public Context context;
    public ArrayList<Premieum> userchoose;

    public PremieumAdapter_Update(Context c,ArrayList<Premieum> list){
        super(c,0,list);
        context = c;
        userchoose = list;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        Premieum pre = getItem(position);
        if(convertView == null)
        {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lisview_premieum,parent,false);
        }


        ImageView image = convertView.findViewById(R.id.image);
        TextView title = convertView.findViewById(R.id.title);
        CheckBox checkbox = convertView.findViewById(R.id.checkBox);

        image.setImageResource(userchoose.get(position).getImage());
        title.setText(userchoose.get(position).getName_th());


        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    checkbox.setChecked(true);
                    pre.setCheck(true);
                    userchoose.add(pre);
                }
                else
                {
                    checkbox.setChecked(false);
                    pre.setCheck(false);
                    userchoose.add(pre);
                }
            }
        });



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
