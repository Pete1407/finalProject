package com.example.testrestapi.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.testrestapi.Distance;
import com.example.testrestapi.Distance_ArrayAdapter;
import com.example.testrestapi.Facility;
import com.example.testrestapi.Facility_ArrayAdapter;
import com.example.testrestapi.Ouput_from_filter;
import com.example.testrestapi.R;
import com.example.testrestapi.Traveling;
import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.google.gson.Gson;

import java.text.ChoiceFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FilterFragment  extends Fragment  {
    public TextView title;

    public String faculty;  // data from MyAdapter Class
    public String dormStyle;  // data from MyAdapter Class
    public int codeStyle;   // data from MyAdapter Class
    public boolean[] checkworking;
    public EditText min;
    public EditText max;
    public String minm = "";
    public String maxm = "";
    public String minPrice = "";
    public String maxPrice = "";
    public int minInteger;
    public int maxInteger;
    public ArrayList<Distance> distanceList;
    public ArrayList<Facility> facilityList;

    public ArrayAdapter<String> adapter;
    public ArrayAdapter<String> adapter2;

    public ExpandableHeightListView listview1;
    public ExpandableHeightListView listview2;
    public CheckBox checkBox;
    public Button button;
    public String choice_distance;
    public int choice_distance_number;
    public ArrayList<Facility> faclist;
    public ArrayList<String> disList;
    public ExpandableRelativeLayout expand;
    public LinearLayout linear;
    public ImageView arrow;
    public Button confirm;
    public String distanceChoice = "";
    public double distanceDouble = 0;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public int langcode;
    public ArrayList<String> faclang;
    public static FilterFragment newInstance(){
        return new FilterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SharedPreferences lang = getActivity().getSharedPreferences("language",Context.MODE_PRIVATE);
        langcode = lang.getInt("languageCode",0);

        View v = inflater.inflate(R.layout.filter_activity,container,false);
        arrow = (ImageView) v.findViewById(R.id.arrow);
        linear = (LinearLayout) v.findViewById(R.id.FacilityTitle);
        expand = (ExpandableRelativeLayout) v.findViewById(R.id.facility);
        title = (TextView) v.findViewById(R.id.title);
        min = (EditText) v.findViewById(R.id.min);
        max = (EditText) v.findViewById(R.id.max);
        listview1 = (ExpandableHeightListView) v.findViewById(R.id.listview1);
        listview2 =(ExpandableHeightListView) v.findViewById(R.id.listview2);

        button = (Button) v.findViewById(R.id.confirm);

        checkworking = new boolean[3];



        this.disList = new ArrayList<>();
        this.faclist = new ArrayList<>();
        this.distanceList = new ArrayList<>();
        this.facilityList = new ArrayList<>();
        this.faclang = new ArrayList<>();


        faculty = getArguments().getString("faculty");
        dormStyle = getArguments().getString("dormStyle");
        codeStyle = getArguments().getInt("codeStyle");



        // ใส่สี actionbar  แยกตาม Style
        if(codeStyle == 0 || codeStyle ==1 || codeStyle ==2)
        {
            button.setBackground(getActivity().getResources().getDrawable(R.color.green_button));
        }
        else if(codeStyle == 3)
        {
            button.setBackground(getActivity().getResources().getDrawable(R.color.black));
        }

        // set ตัวเลือกการค้นหาของ facility กับ distance
        this.setFacility();
        this.setDistance();

        if(minPrice.equals("0") && maxPrice.equals("0"))
        {
            minPrice = "0";
            maxPrice = "0";
            checkworking[0] = false;
        }
        else
        {
            checkworking[0] = true;
        }

        System.out.println("------------    MIN PRICE: "+minPrice+"     MAX PRICE: "+maxPrice+"    ----------");


        // ถ้า langcode เป็น 0 set TH

         ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_single_choice,disList);
         listview1.setAdapter(adapter);
         listview1.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
         listview1.setExpanded(true);
         listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 distanceChoice = disList.get(position);
                 distanceDouble = distanceList.get(position).getDistanceInt();
                 checkworking[1] = true;
                 //Toast.makeText(getActivity(),distanceChoice,Toast.LENGTH_SHORT).show();
             }
         });



        // ถ้า langcode เป็น 1 set EN


        final Facility_ArrayAdapter adapter2 = new Facility_ArrayAdapter(getActivity(),faclist,langcode);
        listview2.setAdapter(adapter2);
        listview2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listview2.setExpanded(true);

        this.expand.setListener(new ExpandableLayoutListener() {
            @Override
            public void onAnimationStart() {
                arrow.setImageResource(R.drawable.rightarrow);
            }

            @Override
            public void onAnimationEnd() {

            }

            @Override
            public void onPreOpen() {
                arrow.setImageResource(R.drawable.rightarrow);
            }

            @Override
            public void onPreClose() {
                arrow.setImageResource(R.drawable.down_arrow);
            }

            @Override
            public void onOpened() {
                arrow.setImageResource(R.drawable.down_arrow);
            }

            @Override
            public void onClosed() {
                arrow.setImageResource(R.drawable.rightarrow);
            }
        });

        this.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               expand.toggle();
            }
        });


        // กรอกราคาน้อยสุด
        this.min.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                minPrice = s.toString();
                    minm = minPrice;
            }
        });

        // กรอกราคามากที่สุด
        max.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                maxPrice = s.toString();
                    maxm = maxPrice;
            }
        });

        // กดยืนยันการค้นหา
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int k = 0;
                System.out.println("Min Price that user choose : "+minm+"   and max price that user choose: "+maxm);
                System.out.println("Distance that user choose: "+distanceChoice+"  and value is "+distanceDouble);
                HashMap<String,Facility> map = new HashMap<>();
              // ArrayList<Distance> dislist = adapter.getList();
               ArrayList<Facility> facilist = adapter2.getList();
               for(int count=0;count<facilist.size();count++)
               {
                   map.put(facilist.get(count).getTextTH(),facilist.get(count));
               }
               System.out.println("user choose facility : "+facilist.size());
               facilist.clear();
               for(Facility f : map.values())
               {
                   System.out.println("user choose : "+f.getTextTH()+"  "+f.isCheck());
               }

               for(Facility e : map.values())
               {
                   facilist.add(e);
               }
               if(minm.equals("") && maxm.equals(""))
               {
                   checkworking[0] = false;
               }
               else
               {
                   checkworking[0] = true;
               }

               if(facilist.size() == 0)
               {
                   checkworking[2] = false;
               }
               else
               {
                   checkworking[2] = true;
               }

                   // เงื่อนไข check ดักถ้าผู้ใข้ไม่ได้กรอกข้อมูลในการค้นหาเลยก็จะขึ้นแจ้งเตือน คือ ถ้า k = 0 จะขึ้นแจ้งเตือน แต่ถ้าผู้ใช้เกรอกอะไรบางอย่างหรือทั้งสอง หรือทั้งสามอย่างก็จะทำดารค้นหาได้
                   if(checkworking[0] == true || checkworking[1] == true || checkworking[2] == true)
                   {
                       k++;
                   }


               if(k>0)
               {

                   //System.out.println("Min Price: "+minInteger+"  Max Price: "+maxInteger);

                   Intent i = new Intent(getActivity(), Ouput_from_filter.class);
                   sharedPreferences = getActivity().getSharedPreferences("filter", Context.MODE_PRIVATE);
                   editor = sharedPreferences.edit();

                   // ตรวจเช็คว่าการกรอกข้อมูลถูกต้องตามที่ต้งอการหรือเปล่า
                   if(minm.equals("") && maxm.equals(""))
                   {
                       i.putExtra("minPrice","0");
                       i.putExtra("maxPrice","0");
                       editor.putString("minPrice","0");
                       editor.putString("maxPrice","0");
                   }
                   else
                   {
                       i.putExtra("minPrice",minm);
                       i.putExtra("maxPrice",maxm);
                       editor.putString("minPrice",minm);
                       editor.putString("maxPrice",maxm);
                   }

                   i.putExtra("codeStyle",codeStyle);
                   i.putExtra("faculty",faculty);
                   i.putExtra("dormStyle",dormStyle);
                   editor.putInt("codeStyle",codeStyle);
                   editor.putString("faculty",faculty);

                   if(distanceChoice.equals(""))
                   {
                       i.putExtra("distanceChoice","");
                       i.putExtra("distanceDouble",0);
                       editor.putString("distanceChoice","");
                       editor.putString("distanceDouble","0");
                   }
                   else
                   {
                       i.putExtra("distanceChoice",distanceChoice);
                       i.putExtra("distanceDouble",distanceDouble);
                       editor.putString("distanceChoice",distanceChoice);
                       editor.putString("distanceDouble",String.valueOf(distanceDouble));
                   }

                   System.out.println("SIZE FAC FILTER : "+facilist.size());
                   for(int count=0;count<facilist.size();count++)
                   {
                       System.out.println(facilist.get(count).getTextTH());
                   }


                   i.putParcelableArrayListExtra("facilityList",facilist);
                   Gson gson = new Gson();
                   String json = gson.toJson(facilist);
                   editor.putString("facility",json);
                   editor.commit();
                    // &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7    ห้ามลืมมมมมมมมมม &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&77&&
                   startActivity(i);
               }
               else if(k==0)
               {
                   AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                   Resources res = getResources();
                   String title = res.getString(R.string.noinput_title);
                   String message = res.getString(R.string.noinput_message);
                   dialog.setTitle(title);
                   dialog.setMessage(message);
                   dialog.setIcon(R.drawable.alert);
                   dialog.setNegativeButton("ตกลง", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                       }
                   });

                   dialog.show();
               }
            }
        });

        return v;
    }

    public void setFacility(){

        //Facility refrigerator = new Facility("ตู้เย็น","Refrigerator",false,R.drawable.refrigerator_image);
        //Facility tv = new Facility("ทีวี","Television",false,R.drawable.television);
        //Facility aircondition = new Facility("เครื่องปรับอากาศ","Air condition",false,R.drawable.airconditioner);
        //Facility phone = new Facility("โทรศัพท์","Telephone",false,R.drawable.phone);
        //Facility fan = new Facility("พัดลม","Fan",false,R.drawable.fan);
        //Facility heater = new Facility("เครื่องทำน้ำอุ่น","Heater",false,R.drawable.heater);
        //Facility bed = new Facility("เตียงนอน","Bed",false,R.drawable.bed);
        //Facility cableTV = new Facility("เคเบิลทีวี","Cable TV",false,R.drawable.cable);
        //Facility closet = new Facility("ตู้เสื้อผ้า","Closet",false,R.drawable.closet);
        //Facility microwavee = new Facility("ไมโครเวฟ","Microwave",false,R.drawable.heater);
        //Facility lamp = new Facility("โคมไฟ","Lamp",false,R.drawable.lamp);
        //Facility curtain = new Facility("ผ้าม่าน","Curtain",false,R.drawable.curtain);

        Facility wifi = new Facility("ไวไฟ","Wifi",false,R.drawable.wifi);
        Facility gym = new Facility("ที่ออกกำลังกาย","Gym",false,R.drawable.gym);
        Facility parking = new Facility("ที่จอดรถ","Parking",false,R.drawable.parking);
        Facility shop = new Facility("ร้านสะดวกซื้อ","Shop",false,R.drawable.shop);
        Facility laundry = new Facility("ร้านซักผ้า","Laundry",false,R.drawable.laundry);
        Facility elevator = new Facility("ลิฟต์","Elevator",false,R.drawable.elevator);
        Facility barber = new Facility("ร้านเสริมสวย","Barber",false,R.drawable.hairbarber);
        Facility cctv = new Facility("กล้องวงจรปิด","CCTV",false,R.drawable.cctv);
        Facility securityManager = new Facility("เจ้าหน้าที่รักษาความปลอดภัย","Security Guard",false,R.drawable.guard);
        Facility shuttle = new Facility("บริการรถตู้รับส่ง","Shuttle Service",false,R.drawable.bus);
        Facility keycard = new Facility("คีร์การ์ด","Keycard",false,R.drawable.keycard);

        //faclist.add(refrigerator);
        //faclist.add(tv);
        //faclist.add(aircondition);
        //faclist.add(phone);
        //faclist.add(fan);
        //faclist.add(heater);
        //faclist.add(bed);
        //faclist.add(cableTV);
        //faclist.add(closet);
        //faclist.add(microwavee);
        //faclist.add(lamp);
        //faclist.add(curtain);
        faclist.add(wifi);
        faclist.add(gym);
        faclist.add(parking);
        faclist.add(shop);
        faclist.add(laundry);
        faclist.add(elevator);
        faclist.add(barber);
        faclist.add(cctv);
        faclist.add(securityManager);
        faclist.add(shuttle);
        faclist.add(keycard);

        if(langcode == 0)
        {

        }
        else
        {

        }

    }

    public void setDistance(){
        final String[] array = getResources().getStringArray(R.array.distance_title);
        Distance one = new Distance(array[0],"less than 1 km",false,1);
        Distance three = new Distance(array[1],"less than 3 km",false,3);
        Distance five = new Distance(array[2],"less than 5 km",false,5);
        Distance seven = new Distance(array[3],"less than 7 km",false,7);
        if(langcode == 0)
        {
            disList.add(one.getDistanceTextTH());
            disList.add(three.getDistanceTextTH());
            disList.add(five.getDistanceTextTH());
            disList.add(seven.getDistanceTextTH());

            distanceList.add(one);
            distanceList.add(three);
            distanceList.add(five);
            distanceList.add(seven);
        }
        else
        {
            disList.add(one.getDistanceTextEN());
            disList.add(three.getDistanceTextEN());
            disList.add(five.getDistanceTextEN());
            disList.add(seven.getDistanceTextEN());

            distanceList.add(one);
            distanceList.add(three);
            distanceList.add(five);
            distanceList.add(seven);
        }





    }




}
