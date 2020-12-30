package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Facility_Activity extends AppCompatActivity{
    public ActionBar bar;
    public ArrayList<Facility> list;
    public ArrayList<Facility> userChoice1;
    public ArrayList<Facility> userChoice2;
    public ListView facilityInternal;
    public ListView facilityExternal;
    public Button button;
    public ArrayAdapter<Facility> adapter1;
    public ArrayAdapter<Facility> adapter2;
    public Button expense;
    public String ip = "192.168.43.57:8080";
    public String url = "http://"+ip;
    public String dormID;

    public String username;
    public String password;
    public String email;
    public HashMap<String,Facility> map1;
    public HashMap<String,Facility> map2;

    public ArrayList<Facility> backup1;
    public ArrayList<Facility> backup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_);

        dormID = getIntent().getStringExtra("dormID");
        this.username = getIntent().getStringExtra("username");
        this.password = getIntent().getStringExtra("password");
        this.email = getIntent().getStringExtra("email");

        //Toast.makeText(this,"Email: "+email+"\n"+"Username: "+username,Toast.LENGTH_LONG).show();

        this.map1 = new HashMap<>();
        this.map2 = new HashMap<>();
        backup1 = new ArrayList<>();
        backup2 = new ArrayList<>();

        this.list = new ArrayList<>();
        this.userChoice1 = new ArrayList<>();
        this.userChoice2 = new ArrayList<>();
        this.bar = getSupportActionBar();
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));
        //this.button = (Button) findViewById(R.id.getData);
        this.expense = (Button) findViewById(R.id.expense);
        int title = R.string.facility_title;
        String fa = getResources().getString(title);
        this.bar.setTitle(fa);
        this.bar.setDisplayHomeAsUpEnabled(true);

        this.facilityInternal = (ListView) findViewById(R.id.listview1);
        this.facilityExternal = (ListView) findViewById(R.id.listview2);

        Facility refrigerator = new Facility("ตู้เย็น","Refrigerator",false,R.drawable.refrigerator_color);
        Facility tv = new Facility("ทีวี","Television",false,R.drawable.tv_color);
        Facility aircondition = new Facility("เครื่องปรับอากาศ","Air condition",false,R.drawable.aircondition_color);
        Facility phone = new Facility("โทรศัพท์","Telephone",false,R.drawable.phone_color);
        Facility fan = new Facility("พัดลม","Fan",false,R.drawable.fan_color);
        Facility heater = new Facility("เครื่องทำน้ำอุ่น","Heater",false,R.drawable.heater_color);
        Facility bed = new Facility("เตียงนอน","Bed",false,R.drawable.bed);
        Facility cableTV = new Facility("เคเบิลทีวี","Cable TV",false,R.drawable.cable);
        Facility closet = new Facility("ตู้เสื้อผ้า","Closet",false,R.drawable.closet_color);
        Facility microwavee = new Facility("ไมโครเวฟ","Microwave",false,R.drawable.microwave_color);
        Facility lamp = new Facility("โคมไฟ","Lamp",false,R.drawable.lamp);
        Facility curtain = new Facility("ผ้าม่าน","Curtain",false,R.drawable.curtain_color);

        Facility[] inner = new Facility[]{refrigerator,tv,aircondition,phone,fan,heater,bed,cableTV,closet,microwavee,lamp,curtain};
        for(int count=0;count<inner.length;count++)
        {
            list.add(inner[count]);
        }

        this.adapter1 = new ArrayAdapter<Facility>(this,android.R.layout.simple_list_item_checked,inner);
        this.facilityInternal.setAdapter(this.adapter1);
        this.facilityInternal.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        this.facilityInternal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView checkedTextView = (CheckedTextView) view;
                boolean c = checkedTextView.isChecked();
                Facility f = (Facility) facilityInternal.getItemAtPosition(position);
                f.setCheck(c);

                if(c == true)
                {
                    f.setCheck(true);
                    userChoice1.add(f);
                }
                else
                {
                    f.setCheck(false);
                    userChoice1.add(f);
                    //userChoice1.remove(position);
                }
            }
        });



        Facility wifi = new Facility("ไวไฟ","Wifi",false,R.drawable.wifi);
        Facility gym = new Facility("ที่ออกกำลังกาย","Gym",false,R.drawable.gym_color);
        Facility parking = new Facility("ที่จอดรถ","Parking",false,R.drawable.parking_color);
        Facility shop = new Facility("ร้านสะดวกซื้อ","Shop",false,R.drawable.shop_color);
        Facility laundry = new Facility("ร้านซักผ้า","Laundry",false,R.drawable.laundryservice_color);
        Facility elevator = new Facility("ลิฟต์","Elevator",false,R.drawable.elevator_color);
        Facility barber = new Facility("ร้านเสริมสวย","Barber",false,R.drawable.hairbarber);
        Facility cctv = new Facility("กล้องวงจรปิด","CCTV",false,R.drawable.cctv_color);
        Facility securityManager = new Facility("เจ้าหน้าที่รักษาความปลอดภัย","Security Guard",false,R.drawable.guard);
        Facility shuttle = new Facility("บริการรถตู้รับส่ง","Shuttle Service",false,R.drawable.bus);
        Facility keycard = new Facility("คีร์การ์ด","Keycard",false,R.drawable.keycard_color);

        Facility[] external = new Facility[]{wifi,gym,parking,shop,laundry,elevator,barber,cctv,securityManager,shuttle,keycard};

        this.adapter2 = new ArrayAdapter<Facility>(this,android.R.layout.simple_list_item_checked,external);
        this.facilityExternal.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        this.facilityExternal.setAdapter(adapter2);
        this.facilityExternal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView ctv = (CheckedTextView) view;
                boolean checkchoice = ctv.isChecked();
                Facility fac = (Facility)facilityExternal.getItemAtPosition(position);
                fac.setCheck(checkchoice);

                if(checkchoice == true)
                {
                    fac.setCheck(true);
                    userChoice2.add(fac);
                }
                else
                {
                    fac.setCheck(false);
                    userChoice2.add(fac);
                }
            }
        });

    }

    public void addExpense(View v){
        url = url + "/facilityInRoom/addFacility";
        String dormID = getIntent().getStringExtra("dormID");
        try {
            JSONObject object = new JSONObject();
            JSONArray nameth = new JSONArray();
            JSONArray nameen = new JSONArray();
            JSONArray image = new JSONArray();
            object.put("dormID",dormID);

            for(int count=0;count<userChoice1.size();count++)
            {
                if(userChoice1.get(count).isCheck() == false)
                {
                    userChoice1.remove(count);
                }
                else
                {
                    backup1.add(userChoice1.get(count));
                }
            }

            for(int count=0;count<backup1.size();count++)
            {
                System.out.println("BACKUP1 : "+backup1.get(count).getTextTH());
            }

            // ใช้ Hashmap มาช่วยคัดแยกตัวซ้ำออก
            for(int count=0;count<backup1.size();count++)
            {
                map1.put(backup1.get(count).getTextTH(),backup1.get(count));
            }
            userChoice1.clear();
            for(Facility f : map1.values())
            {
                userChoice1.add(f);
            }


            for(int i=0;i<userChoice1.size();i++)
            {
                if(userChoice1.get(i).isCheck() == true)
                {
                    nameth.put(i,userChoice1.get(i).getTextTH());
                }
            }

            for(int count2=0;count2<userChoice1.size();count2++)
            {
                if(userChoice1.get(count2).isCheck() == true)
                {
                    nameen.put(count2,userChoice1.get(count2).getTextEN());
                }
            }


            object.put("nameTH",nameth);
            object.put("nameEN",nameen);

            for(int count3=0;count3<userChoice1.size();count3++)
            {
                if(userChoice1.get(count3).isCheck() == true)
                {
                    image.put(count3,userChoice1.get(count3).getImage());
                }
            }



            object.put("image",image);
            //System.out.println("โชว์ object: "+object);
            System.out.println("SHOW1: "+object);




            JSONObject object2 = new JSONObject();
            JSONArray namethObj2 = new JSONArray();
            JSONArray nameenObj2 = new JSONArray();
            JSONArray imageObj2 = new JSONArray();
            object2.put("dormID",dormID);
            for(int count4=0;count4<userChoice2.size();count4++)
            {
                if(userChoice2.get(count4).isCheck() == false)
                {
                    userChoice2.remove(count4);
                }
                else
                {
                    backup2.add(userChoice2.get(count4));
                }

            }

            for(int count=0;count<backup2.size();count++)
            {
                map2.put(backup2.get(count).getTextTH(),backup2.get(count));
            }
            userChoice2.clear();
            for(Facility f : map2.values())
            {
                userChoice2.add(f);
            }

            for(int k=0;k<userChoice2.size();k++)
            {
                if(userChoice2.get(k).isCheck() == true)
                {
                    namethObj2.put(k,userChoice2.get(k).getTextTH());
                }
            }

            for(int count5=0;count5<userChoice2.size();count5++)
            {
                if(userChoice2.get(count5).isCheck() == true)
                {
                    nameenObj2.put(count5,userChoice2.get(count5).getTextEN());
                }

            }

            for(int count6=0;count6<userChoice2.size();count6++)
            {
                if(userChoice2.get(count6).isCheck() == true)
                {
                    imageObj2.put(count6,userChoice2.get(count6).getImage());
                }
            }



            object2.put("nameTH",namethObj2);
            object2.put("nameEN",nameenObj2);
            object2.put("image",imageObj2);



            System.out.println("SHOW2: "+object2);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });


            String url2 = "http://192.168.43.57:8080/facilityInDorm/addFacility";
            JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.POST, url2, object2, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            RequestQueue queue = Volley.newRequestQueue(this);
            // ห้ามลืม เปิด comment ไม่งั้นใส่ลงฐานข้อมูลไม่ได้นะ  ################@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&
            queue.add(request);
            queue.add(request2);

        }
        catch(JSONException ex){
            ex.printStackTrace();
        }



        Intent intent = new Intent(Facility_Activity.this,ExpenseActivity.class);
        intent.putExtra("dormID",dormID);
        intent.putExtra("username",username);
        intent.putExtra("password",password);
        intent.putExtra("email",email);
        startActivity(intent);
    }



}
