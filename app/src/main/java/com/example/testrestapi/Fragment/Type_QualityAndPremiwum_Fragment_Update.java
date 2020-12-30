package com.example.testrestapi.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.testrestapi.Premieum;
import com.example.testrestapi.PremieumAdapter;
import com.example.testrestapi.PremieumAdapter_Update;
import com.example.testrestapi.R;
import com.example.testrestapi.UpdateActivity;
import com.example.testrestapi.UploadImageActivity;
import com.example.testrestapi.VolleyMultipartRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Type_QualityAndPremiwum_Fragment_Update extends Fragment {
    public PremieumAdapter_Update adapter;

    public Button button;

    public TextView title;
    public ListView list;

    public ArrayList<Premieum> userChoose;
    public ArrayList<Premieum> premieumlist;
    public ArrayList<HashMap<String,String>> maplist;
    public String[] from = {"image","title"};
    public int[] to = {R.id.image,R.id.title};

    public int minPrice;
    public int maxPrice;
    public String typeWater;
    public int  priceWater;
    public String typeElectro;
    public int priceElectro;
    public String typeDeposit;
    public int depositPrice;
    public String typeCommonFee;
    public int commonFee;
    public String description;
    public String dormID;
    public String[] dormStyle;

    public String username;
    public String email;

    public ArrayList<String> premieum_th;
    public ArrayList<String> premieum_en;
    public ArrayList<Premieum> result;
    public String ip = "192.168.43.57";
    public String port = ":8080";

    public String dormname;
    ArrayList<Integer> imagelist;
    ArrayList<String> thlist;
    ArrayList<String> enlist;
    public HashMap<String,Premieum> map;
    public TextView userchosen;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.type_qualityandpremium_fragment_update,container,false);
        this.userChoose = new ArrayList<>();
        premieumlist = new ArrayList<>();
        this.maplist = new ArrayList<>();
        this.premieum_th = new ArrayList<>();
        this.premieum_en = new ArrayList<>();
        result = new ArrayList<>();
        map = new HashMap<>();

        title = (TextView) v.findViewById(R.id.title);
        list = (ListView) v.findViewById(R.id.recycler);
        button = (Button) v.findViewById(R.id.button9);
        userchosen = (TextView) v.findViewById(R.id.userchosen);

        Bundle b = this.getArguments();
        dormname = b.getString("dormname");
        imagelist = b.getIntegerArrayList("imagelist");
        thlist = b.getStringArrayList("thlist");
        enlist = b.getStringArrayList("enlist");


        dormStyle = getActivity().getResources().getStringArray(R.array.dormtype);


        Premieum one = new Premieum("ทีวีช่องพิเศษ","Special channel for TV",R.drawable.hbo);
        Premieum two = new Premieum("ห้องนั่งเล่น","Living Room",R.drawable.livingroom);
        Premieum three = new Premieum("ห้องอ่านหนังสือ","Library",R.drawable.library);
        Premieum four = new Premieum("เลี้ยงสัตว์เลี้ยง","Pets",R.drawable.pet);
        Premieum five = new Premieum("คีย์การ์ด","Key card",R.drawable.keycard_color);
        Premieum fingerprint = new Premieum("แสกนลายนิ้วมือ","Fingerprint Scanner",R.drawable.fingerprint);
        Premieum wifi = new Premieum("อินเทอร์เน็ตความเร็วสูง","Highspeed Internet",R.drawable.speedinternet);
        Premieum fitness = new Premieum("ห้องออกกำลังกาย","Fitness",R.drawable.gym_color);
        Premieum pool = new Premieum("สระว่ายน้ำ","Swimming Pool",R.drawable.pool_color);
        Premieum kitchen = new Premieum("ห้องครัว","Kitchen",R.drawable.kitchen);
        Premieum cleanningService = new Premieum("บริการทำความสะอาด","Cleanning Service",R.drawable.cleanservice_color);

        premieumlist.add(one);
        premieumlist.add(two);
        premieumlist.add(three);
        premieumlist.add(four);
        premieumlist.add(five);
        premieumlist.add(fingerprint);
        premieumlist.add(wifi);
        premieumlist.add(fitness);
        premieumlist.add(pool);
        premieumlist.add(kitchen);
        premieumlist.add(cleanningService);
        //premieumlist.add(cleanningService);
        //premieumlist.add(cleanningService);

        setDefaultPremium(dormname);
        PremieumAdapter_Update adapter = new PremieumAdapter_Update(getActivity(),premieumlist);
        list.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              userChoose =  adapter.getUserChoice();
              for(int count=0;count<userChoose.size();count++)
              {
                  map.put(userChoose.get(count).getName_th(),userChoose.get(count));
              }
              userChoose.clear();

              for(Premieum p : map.values())
              {
                  System.out.println("user choose: "+p.getName_th()+"  "+p.getCheck());
              }

              for(Premieum e : map.values())
              {
                  userChoose.add(e);
              }
                JSONArray imagelist = new JSONArray();
                JSONArray thlist = new JSONArray();
                JSONArray enlist = new JSONArray();
             try
             {
                 for(int i=0;i<userChoose.size();i++)
                 {
                     imagelist.put(i,userChoose.get(i).getImage());
                 }

                 for(int j=0;j<userChoose.size();j++)
                 {
                     thlist.put(j,userChoose.get(j).getName_th());
                     enlist.put(j,userChoose.get(j).getName_en());
                 }

             }
             catch (JSONException ex){
                 ex.printStackTrace();
             }

                String updatePremiumpath ="http://"+ip+port+"/DormTypeQuality/updatePremium/"+dormname;
                JSONObject dataupdate = new JSONObject();
             try
             {
                 dataupdate.put("dormID",dormname);
                 dataupdate.put("dormStyle",dormStyle[2]);
                 dataupdate.put("imagelist",imagelist);
                 dataupdate.put("premieum_th",thlist);
                 dataupdate.put("premieum_en",enlist);

                 System.out.println("SHOW :: "+dataupdate);
                 JsonObjectRequest updaterequest = new JsonObjectRequest(Request.Method.POST, updatePremiumpath, dataupdate, new Response.Listener<JSONObject>() {
                     @Override
                     public void onResponse(JSONObject response) {
                        System.out.println("UPDATED DATA : "+response);
                        Intent i = new Intent(getActivity(), UpdateActivity.class);
                        getActivity().finish();
                        getActivity().startActivity(i);
                     }
                 }, new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {

                     }
                 });
                 RequestQueue queue = Volley.newRequestQueue(getActivity());
                 queue.add(updaterequest);
             }
             catch (JSONException ex)
             {
                 ex.printStackTrace();
             }



            }
        });
        return v;
    }

    public void setDefaultPremium(String dormname){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String path = "http://"+ip+port+"/DormTypeQuality/getPremiumBydormName/"+dormname;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("premieum_th");
                    ArrayList<String> l = new ArrayList<>();
                    String thing = "";
                    for(int count=0;count<array.length();count++)
                    {
                        l.add(array.getString(count));
                    }
                    for(int count2=0;count2<l.size();count2++)
                    {
                        thing = thing + l.get(count2)+" ";
                    }

                    userchosen.setText("คุณเลือก: "+thing);
                    int black = getActivity().getResources().getColor(R.color.black);
                    userchosen.setTextColor(black);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }


}
