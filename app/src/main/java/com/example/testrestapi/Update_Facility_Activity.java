package com.example.testrestapi;

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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Update_Facility_Activity extends AppCompatActivity{
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

    public JSONObject inRoom;
    public JSONObject inDorm;

    public boolean check1;
    public boolean check2;

    public HashMap<String,Facility> map1;
    public HashMap<String,Facility> map2;
    public ArrayList<Facility> backup1;
    public ArrayList<Facility> backup2;

    public Facility[] inner;
    public Facility[] external;
    public ArrayList<Facility> defaultlist;
    public ArrayList<Facility> setlist;
    public FacilityCallback callback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_facility_);
        setlist = new ArrayList<>();
        defaultlist = new ArrayList<>();
        dormID = getIntent().getStringExtra("dormName");
        this.username = getIntent().getStringExtra("username");
        this.password = getIntent().getStringExtra("password");
        this.email = getIntent().getStringExtra("email");

        //Toast.makeText(this,"DORM ID : "+dormID,Toast.LENGTH_LONG).show();

        this.check1 = false;
        this.check2 = false;

        backup1 = new ArrayList<>();
        backup2 = new ArrayList<>();
        this.map1 = new HashMap<>();
        this.map2 = new HashMap<>();
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
        Facility microwavee = new Facility("ไมโครเวฟ","Microwave",false,R.drawable.heater_color);
        Facility lamp = new Facility("โคมไฟ","Lamp",false,R.drawable.lamp);
        Facility curtain = new Facility("ผ้าม่าน","Curtain",false,R.drawable.curtain_color);

        inner = new Facility[]{refrigerator,tv,aircondition,phone,fan,heater,bed,cableTV,closet,microwavee,lamp,curtain};
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

        external = new Facility[]{wifi,gym,parking,shop,laundry,elevator,barber,cctv,securityManager,shuttle,keycard};

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
        setDefault(new FacilityCallback() {
            @Override
            public void onsuccess(ArrayList<Facility> fac) {
                setlist = fac;
                System.out.println("Set List: "+setlist.size());
                for(int count=0;count<setlist.size();count++)
                {
                    System.out.println(setlist.get(count).getTextTH());
                }
            }

            @Override
            public void onfailure(String error) {

            }
        });

    }

    public void updateFacility(View v) {
        String pathUpdateFacilityInRoom = "http://" + ip + "/facilityInRoom/updateFacility/" + dormID;
        RequestQueue queue = Volley.newRequestQueue(this);
        try
        {

            inRoom = new JSONObject();
            JSONArray textTH = new JSONArray();
            JSONArray textEN = new JSONArray();
            JSONArray image = new JSONArray();

            //System.out.println("-- Before remove --");
            for(int count=0;count<userChoice1.size();count++)
            {
                // System.out.println(userChoice1.get(count).getTextTH()+"  "+userChoice1.get(count).isCheck());
            }

            //System.out.println("-- After remove --");
            for(int i=0;i<userChoice1.size();i++)
            {
                for(int j=0;j<userChoice1.size();j++)
                {
                    if(userChoice1.get(j).isCheck() == false)
                    {
                        userChoice1.remove(j);
                    }
                }

            }
            for(int count=0;count<userChoice1.size();count++)
            {
                //System.out.println(userChoice1.get(count).getTextTH()+"  "+userChoice1.get(count).isCheck());
            }


            for(int count=0;count<userChoice1.size();count++)
            {
                map1.put(userChoice1.get(count).getTextTH(),userChoice1.get(count));
            }

            for(Facility a : map1.values())
            {
                System.out.println("answer ::"+a.getTextTH());
            }
            userChoice1.clear();
            for(Facility f : map1.values())
            {
                userChoice1.add(f);
            }

            for (int count = 0; count < userChoice1.size(); count++)
            {
                textTH.put(count, userChoice1.get(count).getTextTH());
                textEN.put(count, userChoice1.get(count).getTextEN());
                image.put(count, userChoice1.get(count).getImage());
            }

            inRoom.put("dormID", dormID);
            inRoom.put("nameTH", textTH);
            inRoom.put("nameEN", textEN);
            inRoom.put("image",image);

            System.out.println("SHOW1: "+inRoom);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, pathUpdateFacilityInRoom,inRoom, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(getApplicationContext(),"อัพเดทสำเร็จ",Toast.LENGTH_SHORT).show();
                //check1 = true;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR: "+error.toString());
            }
        });

        String pathUpdateFacilityInDorm = "http://"+ip+"/facilityInDorm/updateFacilityInDorm/"+dormID;

        try
        {
            inDorm = new JSONObject();
            JSONArray textth_indorm = new JSONArray();
            JSONArray texten_indorm = new JSONArray();
            JSONArray image_indorm = new JSONArray();

            //System.out.println("### Before remove ###");
            for(int count=0;count<userChoice1.size();count++)
            {
                //System.out.println(userChoice2.get(count).getTextTH()+"  "+userChoice2.get(count).isCheck());
            }

            for(int count=0;count<userChoice2.size();count++)
            {
                for(int count2=0;count2<userChoice2.size();count2++)
                {
                    if(userChoice2.get(count2).isCheck() == false)
                    {
                        userChoice2.remove(count2);
                    }
                }
            }

            //System.out.println("### After remove ###");
            for(int count=0;count<userChoice2.size();count++)
            {
                //System.out.println(userChoice2.get(count).getTextTH()+"  "+userChoice2.get(count).isCheck());
            }

            for(int count=0;count<userChoice2.size();count++)
            {
                map2.put(userChoice2.get(count).getTextTH(),userChoice2.get(count));
            }
            userChoice2.clear();
            for(Facility f : map2.values())
            {
                userChoice2.add(f);
            }

            for(int j=0;j<userChoice2.size();j++)
            {
                textth_indorm.put(j,userChoice2.get(j).getTextTH());
                texten_indorm.put(j,userChoice2.get(j).getTextEN());
                image_indorm.put(j,userChoice2.get(j).getImage());
            }

            inDorm.put("dormID",dormID);
            inDorm.put("nameTH",textth_indorm);
            inDorm.put("nameEN",texten_indorm);
            inDorm.put("image",image_indorm);

            System.out.println("SHOW2: "+inDorm);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.POST, pathUpdateFacilityInDorm, inDorm, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //check2 = true;
                Toast.makeText(getApplicationContext(),"อัพเดตสำเร็จ",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);
        queue.add(request2);


        Intent intent = new Intent(Update_Facility_Activity.this,UpdateActivity.class);
        finish();
        startActivity(intent);
    }

    public void setDefault(FacilityCallback callback){

        RequestQueue queue = Volley.newRequestQueue(this);
        String path1 = url+"/facilityInRoom/getFacilityInRoom/"+dormID;
        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, path1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray nameth = response.getJSONArray("nameTH");
                    JSONArray nameEN = response.getJSONArray("nameEN");
                    JSONArray images = response.getJSONArray("image");
                    for(int count=0;count<inner.length;count++)
                    {
                        for(int count2=0;count2<nameth.length();count2++)
                        {
                            if(inner[count].getTextTH().equals(nameth.getString(count2)))
                            {
                                defaultlist.add(new Facility(nameth.getString(count2),nameEN.getString(count2),images.getInt(count2)));
                            }
                        }
                    }

                    String path2 = url+"/facilityInDorm/getFacilityInDorm/"+dormID;
                    JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, path2, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray nameth = response.getJSONArray("nameTH");
                                JSONArray nameEN = response.getJSONArray("nameEN");
                                JSONArray images = response.getJSONArray("image");
                                for(int count=0;count<external.length;count++)
                                {
                                    for(int count2=0;count2<nameth.length();count2++)
                                    {
                                        if(external[count].getTextTH().equals(nameth.getString(count2)))
                                        {
                                            defaultlist.add(new Facility(nameth.getString(count2),nameEN.getString(count2),images.getInt(count2)));
                                        }
                                    }
                                }
                                callback.onsuccess(defaultlist);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(request2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request1);
    }


}
