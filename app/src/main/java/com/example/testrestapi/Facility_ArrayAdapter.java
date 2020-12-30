package com.example.testrestapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Facility_ArrayAdapter extends ArrayAdapter<Facility> {
    public int codelang;
    public ArrayList<Facility> userchoose = new ArrayList<>();

    public Facility_ArrayAdapter(@NonNull Context context, ArrayList<Facility> list,int langcode) {
        super(context,0,list);
        codelang = langcode;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       final Facility dis = getItem(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.distance_layout,parent,false);
        }

        final TextView title = (TextView) convertView.findViewById(R.id.title);
        final CheckBox check = (CheckBox) convertView.findViewById(R.id.checkbox);
        if(codelang == 0)
        {
            title.setText(dis.getTextTH());
        }
        else
        {
            title.setText(dis.getTextEN());
        }

        title.setTextColor(getContext().getResources().getColor(R.color.black));

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    check.setChecked(true);
                    dis.setCheck(true);
                    userchoose.add(dis);
                    title.setTextColor(getContext().getResources().getColor(R.color.background));
                    //Toast.makeText(getContext(),dis.getTextTH()+"  "+isChecked,Toast.LENGTH_SHORT).show();
                }
                else
                {
                    check.setChecked(false);
                    dis.setCheck(false);
                    userchoose.add(dis);
                    title.setTextColor(getContext().getResources().getColor(R.color.black));
                }

            }
        });
        return convertView;
    }

    public ArrayList<Facility> getList(){
        for(int count=0;count<userchoose.size();count++)
        {
            for(int count2=0;count2<userchoose.size();count2++)
            {
                if(userchoose.get(count2).isCheck() == false)
                {
                    userchoose.remove(count2);
                }
            }
        }
        return userchoose;
    }
}
