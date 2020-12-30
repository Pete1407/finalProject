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

public class Distance_ArrayAdapter extends ArrayAdapter<Distance> {

    public ArrayList<Distance> userchoose;

    public Distance_ArrayAdapter(@NonNull Context context, ArrayList<Distance> list) {
        super(context,0,list);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       final Distance dis = getItem(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.distance_layout,parent,false);
        }
        this.userchoose = new ArrayList<>();
        final TextView title = (TextView) convertView.findViewById(R.id.title);
        CheckBox check = (CheckBox) convertView.findViewById(R.id.checkbox);
        title.setText(dis.getDistanceTextTH());
        title.setTextColor(getContext().getResources().getColor(R.color.black));
        check.setChecked(dis.isCheck());
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    userchoose.add(dis);
                    title.setTextColor(getContext().getResources().getColor(R.color.background));
                    Toast.makeText(getContext(),dis.getDistanceTextTH()+"  "+isChecked,Toast.LENGTH_SHORT).show();
                }
                else
                {
                    userchoose.remove(position);
                    title.setTextColor(getContext().getResources().getColor(R.color.black));
                }

            }
        });
        return convertView;
    }

    public ArrayList<Distance> getList(){
        return userchoose;
    }
}
