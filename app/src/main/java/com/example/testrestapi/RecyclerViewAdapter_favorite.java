package com.example.testrestapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecyclerViewAdapter_favorite extends RecyclerView.Adapter<RecyclerViewAdapter_favorite.ViewHolder> {

    public Context context;
    public HashMap<String,FavoriteDormModel> list;
    public OnItemClickListener listener;
    public int codeStyle;
    public Faculty f;
    public String userchoose;
    public String[] array;
    public FavoriteDormModel[] list2;

    String waterp;
    String electp ;
    String middle;
    String depop;
    String unit;
    String free;
    String baht;
    String from;
    public int codelanguage;
    public String[] t;

    public void setOnItemClickListener(OnItemClickListener lis){
        this.listener = lis;
    }

    public RecyclerViewAdapter_favorite(HashMap<String,FavoriteDormModel> list, Context c, Faculty f, int codeStyle, String userchoose,int codelang,String[] text){
        this.context = c;
        this.list = list;
        this.codeStyle = codeStyle;
        this.f = f;
        this.userchoose = userchoose;
        list2 = new FavoriteDormModel[list.size()];
        this.array = context.getResources().getStringArray(R.array.sort);
        this.codelanguage = codelang;
        t = text;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.favorite_recyclerview_layout,parent,false);
        ViewHolder holder =  new ViewHolder(v,listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int black = context.getResources().getColor(R.color.black);
        waterp = t[0];
        electp = t[1];
        middle = t[2];
        depop = t[3];
        unit = t[4];
        free = t[5];
        baht = t[6];
    /*
        if(codelanguage == 0)
        {
            waterp = "ค่าน้ำ";
            electp = "ค่าไฟ";
            middle = "ค่าส่วนกลาง";
            depop = "ค่ามัดจำ";
            unit = "บาท/เดือน";
            free = "ไม่มีค่าใช้จ่าย";
            baht = "บาท";
        }
        else
        {
            waterp = "Water price";
            electp = "Electricity price";
            middle = "Common fee";
            depop = "Deposit price";
            unit = "baht/month";
            free = "no extra charge";
            baht = "baht";
        }
    */

        if(codeStyle == 3)
        {
            //holder.namedormborder.setBackground(new ColorDrawable(Color.parseColor("#000000")));
            holder.border.setBackground(new ColorDrawable(Color.parseColor("#000000")));
            holder.borderinfo.setBackground(new ColorDrawable(Color.parseColor("#ECE5E5")));
        }

        if(userchoose.equals(array[0]))
        {
            int countt = 0;
            int min = 0;
            FavoriteDormModel minobject;
            for(Map.Entry me : list.entrySet())
            {
                FavoriteDormModel f = list.get(me.getKey());
                System.out.println(f.getDormname());
                list2[countt] = f;
                countt++;
            }

            for(int count=0;count<list2.length;count++)
            {
               for(int count2=0;count2<list2.length-1;count2++)
               {
                   min = list2[count2].getRoomprice();
                   minobject = list2[count2];
                   if(min > list2[count2+1].getRoomprice())
                   {
                       list2[count2] = list2[count2+1];
                       list2[count2+1] = minobject;
                   }
               }
            }
        }
        else if(userchoose.equals(array[1]))
        {
            int countt = 0;
            int max = 0;
            FavoriteDormModel minobject;
            for(Map.Entry me : list.entrySet())
            {
                FavoriteDormModel f = list.get(me.getKey());
                System.out.println(f.getDormname());
                list2[countt] = f;
                countt++;
            }

            for(int count=0;count<list2.length;count++)
            {
                for(int count2=0;count2<list2.length-1;count2++)
                {
                    max = list2[count2].getRoomprice();
                    minobject = list2[count2];
                    if(max < list2[count2+1].getRoomprice())
                    {
                        list2[count2] = list2[count2+1];
                        list2[count2+1] = minobject;
                    }
                }
            }
        }
        else if(userchoose.equals(array[2]))
        {
            int countt = 0;
            for(Map.Entry me : list.entrySet())
            {
                FavoriteDormModel f = list.get(me.getKey());
                System.out.println(f.getDormname());
                list2[countt] = f;
                countt++;
            }

            System.out.println("SIZE LIST2: "+list2.length);
            for(int i=0;i<list2.length;i++)
            {
                for(int count=0;count<list2.length-1;count++)
                {
                   FavoriteDormModel minn = list2[count];
                    Double d1 = new Double(list2[count].getDistanceDouble());
                    Double d2 = new Double(list2[count+1].getDistanceDouble());
                    int retval = d1.compareTo(d2);
                    if(retval > 0)
                    {
                        list2[count] = list2[count+1];
                        list2[count+1] = minn;
                    }
                }
            }

        }
        else if(userchoose.equals(array[3]))
        {
            int countt = 0;
            for(Map.Entry me : list.entrySet())
            {
                FavoriteDormModel f = list.get(me.getKey());
                System.out.println(f.getDormname());
                list2[countt] = f;
                countt++;
            }

            System.out.println("SIZE LIST2: "+list2.length);
            for(int i=0;i<list2.length;i++)
            {
                for(int count=0;count<list2.length-1;count++)
                {
                    FavoriteDormModel minn = list2[count];
                    Double d1 = new Double(list2[count].getDistanceDouble());
                    Double d2 = new Double(list2[count+1].getDistanceDouble());
                    int retval = d1.compareTo(d2);
                    if(retval < 0)
                    {
                        list2[count] = list2[count+1];
                        list2[count+1] = minn;
                    }
                }
            }
        }


        Picasso.with(context).load(list2[position].getCoverimage()).into(holder.coverimage);
        holder.namedorm.setText(list2[position].getDormname());
        from = context.getResources().getString(R.string.from);
        holder.distance.setText(list2[position].getDistance()+" "+from+f.getName_faculty());
        holder.distance.setTextColor(black);
        //holder.distance.setTextSize(16);
        holder.address.setText(list2[position].getAddress());
        holder.address.setTextColor(black);
        holder.electroprice.setText(electp+": "+String.valueOf(list2[position].getElectroprice())+" "+baht);
        holder.electroprice.setTextColor(black);
        holder.waterprice.setText(waterp+": "+String.valueOf(list2[position].getWaterprice())+" "+baht);
        holder.waterprice.setTextColor(black);
        if(list2[position].getCommonfee() == 0)
        {
            holder.commonfee.setText(middle+": "+free);
        }
        else
        {
            holder.commonfee.setText(middle+": "+String.valueOf(list2[position].getCommonfee())+" "+baht);
        }

        if(list2[position].getDepositprice() == 0)
        {
            holder.depositprice.setText(depop+": "+free);
        }
        else
        {
            holder.depositprice.setText(depop+": "+String.valueOf(list2[position].getDepositprice())+" "+baht);
        }
        holder.commonfee.setTextColor(black);

        holder.depositprice.setTextColor(black);
        holder.roomprice.setText(String.valueOf(list2[position].getRoomprice())+" "+unit);
        holder.pin.setImageResource(R.drawable.pin_normal);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void reset(){

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView coverimage;
        public TextView distance;
        public TextView namedorm;
        public TextView address;
        public TextView electroprice;
        public TextView waterprice;
        public TextView commonfee;
        public TextView depositprice;
        public TextView roomprice;
        public ImageView pin;
        public LinearLayout border;
        public LinearLayout namedormborder;
        public LinearLayout borderinfo;
        public ViewHolder(@NonNull View itemView , final OnItemClickListener listener) {
            super(itemView);
            coverimage = itemView.findViewById(R.id.coverimage);
            namedorm = itemView.findViewById(R.id.namedorm);
            distance = itemView.findViewById(R.id.distance);
            address = itemView.findViewById(R.id.address);
            electroprice = itemView.findViewById(R.id.electroprice);
            waterprice = itemView.findViewById(R.id.waterprice);
            commonfee = itemView.findViewById(R.id.commonfee);
            depositprice = itemView.findViewById(R.id.depositprice);
            roomprice = itemView.findViewById(R.id.roomprice);
            pin = itemView.findViewById(R.id.imageView12);
            border = itemView.findViewById(R.id.title);
            borderinfo = itemView.findViewById(R.id.borderinfo);
            namedormborder = itemView.findViewById(R.id.namedormborder);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public FavoriteDormModel[] getFavlist(){
        return list2;
    }
}
