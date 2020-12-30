package com.example.testrestapi.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testrestapi.Premieum;
import com.example.testrestapi.PremieumAdapter;
import com.example.testrestapi.R;
import com.example.testrestapi.UploadImageActivity;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.util.ArrayList;
import java.util.HashMap;

public class Type_QualityAndPremiwum_Fragment extends Fragment {
    public PremieumAdapter adapter;

    public Button button;

    public TextView title;
    public ListView list;

    public String[] namepremium;
    public ArrayList<Premieum> userChoose;
    public ArrayList<Premieum> premieumlist;
    public ArrayList<Premieum> result;
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
    public String dormStyle;

    public String username;
    public String email;

    public ArrayList<String> premieum_th;
    public ArrayList<String> premieum_en;

    public HashMap<String,Premieum> map;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.type_qualityandpremieum_fragment,container,false);
        this.userChoose = new ArrayList<>();
        premieumlist = new ArrayList<>();
        this.maplist = new ArrayList<>();
        this.premieum_th = new ArrayList<>();
        this.result = new ArrayList<>();
        this.premieum_en = new ArrayList<>();
        map = new HashMap<>();


        title = (TextView) v.findViewById(R.id.title);
        list = (ListView) v.findViewById(R.id.recycler);
        button = (Button) v.findViewById(R.id.button9);

        Bundle b = this.getArguments();

         dormID = b.getString("dormID");
         minPrice = b.getInt("minPrice");
         maxPrice = b.getInt("maxPrice");
         typeWater = b.getString("typeWater");
         priceWater = b.getInt("priceWater");
         typeElectro = b.getString("typeElectro");
         priceElectro = b.getInt("priceElectro");
         typeDeposit = b.getString("typeDeposit");
         depositPrice = b.getInt("depositPrice");
         typeCommonFee = b.getString("typeCommonFee");
         commonFee = b.getInt("commonFee");
         description = b.getString("description");
         dormStyle = b.getString("dormStyle");

         this.username = b.getString("username");
         this.email = b.getString("email");
        //Toast.makeText(getActivity(),"Email: "+email+"\n"+"Username: "+username,Toast.LENGTH_LONG).show();

        Premieum one = new Premieum("ทีวีช่องพิเศษ","Special channel for TV",R.drawable.hbo);
        Premieum two = new Premieum("ห้องนั่งเล่น","Living Room",R.drawable.livingroom);
        Premieum three = new Premieum("ห้องอ่านหนังสือ","Library",R.drawable.library);
        Premieum four = new Premieum("เลี้ยงสัตว์เลี้ยง","Pets",R.drawable.pet);
        //Premieum five = new Premieum("คีย์การ์ด","Key card",R.drawable.keycard);
        Premieum fingerprint = new Premieum("แสกนลายนิ้วมือ","Fingerprint Scanner",R.drawable.fingerprint);
        Premieum wifi = new Premieum("อินเทอร์เน็ตความเร็วสูง","Highspeed Internet",R.drawable.speedinternet);
        Premieum fitness = new Premieum("ห้องออกกำลังกาย","Fitness",R.drawable.gym_color);
        Premieum pool = new Premieum("สระว่ายน้ำ","Swimming Pool",R.drawable.pool_color);
        Premieum kitchen = new Premieum("ห้องครัว","Kitchen",R.drawable.kitchen);
        Premieum cleanningService = new Premieum("บริการทำความสะอาด","Cleanning Service",R.drawable.cleanservice_color);

        premieumlist.add(two);
        premieumlist.add(one);
        premieumlist.add(three);
        premieumlist.add(four);
        //premieumlist.add(five);
        premieumlist.add(fingerprint);
        premieumlist.add(wifi);
        premieumlist.add(cleanningService);
        premieumlist.add(fitness);
        premieumlist.add(pool);
        premieumlist.add(kitchen);

        //premieumlist.add(cleanningService);
        //premieumlist.add(cleanningService);

        namepremium = new String[premieumlist.size()];
        for(int count=0;count<premieumlist.size();count++)
        {
            namepremium[count] = premieumlist.get(count).getName_th();
        }


        PremieumAdapter adapter = new PremieumAdapter(getActivity(),premieumlist);
        list.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result = adapter.getUserChoice();
                for(int count=0;count<result.size();count++)
                {
                    map.put(result.get(count).name_th,result.get(count));
                }
                result.clear();
                for(Premieum a : map.values())
                {
                    result.add(a);
                }
                for(Premieum p : map.values())
                {
                    premieum_th.add(p.getName_th());
                    premieum_en.add(p.getName_en());
                }

                for(int count=0;count<result.size();count++)
                {
                    System.out.println("user choose "+result.get(count).getName_th()+"   "+result.get(count).getCheck());
                }


                Intent i = new Intent(getActivity(), UploadImageActivity.class);
                i.putExtra("dormID",dormID);
                i.putExtra("minPrice",minPrice);
                i.putExtra("maxPrice",maxPrice);
                i.putExtra("typeWater",typeWater);
                i.putExtra("priceWater",priceWater);
                i.putExtra("typeElectro",typeElectro);
                i.putExtra("priceElectro",priceElectro);
                i.putExtra("typeDeposit",typeDeposit);
                i.putExtra("depositPrice",depositPrice);
                i.putExtra("typeCommonFee",typeCommonFee);
                i.putExtra("commonFee",commonFee);
                i.putExtra("description",description);
                i.putExtra("dormStyle",dormStyle);
                i.putParcelableArrayListExtra("premieum",result);
                i.putExtra("username",username);
                i.putExtra("email",email);
                startActivity(i);

            }
        });



        return v;
    }


}
