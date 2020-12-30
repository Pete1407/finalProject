package com.example.testrestapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CommentandScore_PricePromotion_Style extends AppCompatActivity {

    public ActionBar bar;
    public ExpandableHeightListView listview;
    public TextView signcleanScore;
    public TextView signserviceScore;
    public TextView signdecorateScore;
    public TextView signsecurityScore;
    public SmileRating cleaning;
    public SmileRating service;
    public SmileRating decorate;
    public SmileRating security;
    public EditText comment;
    public Button button;

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public int cleanscore;
    public int servicescore;
    public int decoratescore;
    public int securityscore;
    public String commentfromuser;
    public String port = ":8080";
    public String ip = "192.168.43.57";

    public String dormname;
    public ArrayList<Integer> cleanlist;
    public ArrayList<Integer> servicelist;
    public ArrayList<Integer> decoratelist;
    public ArrayList<Integer> securitylist;
    public ArrayList<String>  commentlist;
    public int averagescore = 0;
    public String point;
    public String nopoint;
    public View view2;
    public View view3;
    public TextView totalaveragescore;
    public TextView sign;

    public int cleanScore;
    public int serviceScore;
    public int decorateScore;
    public int securityScore;
    public int[] summary = new int[4];
    public TextView mostofuser;
    //public TextView readmore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentand_score__price_promotion__style);
        String a = getResources().getString(R.string.comment);
        this.bar = getSupportActionBar();
        this.bar.setTitle(a);
        this.bar.setDisplayHomeAsUpEnabled(true);
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));

        cleanlist = new ArrayList<>();
        servicelist = new ArrayList<>();
        decoratelist = new ArrayList<>();
        securitylist = new ArrayList<>();
        commentlist = new ArrayList<>();

        this.point = getResources().getString(R.string.point);
        this.nopoint = getResources().getString(R.string.nopoint);

        this.listview = (ExpandableHeightListView) findViewById(R.id.recycler);
        this.cleaning = (SmileRating) findViewById(R.id.commentcleanning);
        this.service = (SmileRating) findViewById(R.id.commentService);
        this.decorate = (SmileRating) findViewById(R.id.commentDecorating);
        this.security = (SmileRating) findViewById(R.id.commentSecurity);
        this.comment = (EditText) findViewById(R.id.editText);
        this.button = (Button) findViewById(R.id.button);
        this.signcleanScore = (TextView) findViewById(R.id.scoreCleanning);
        this.signserviceScore = (TextView) findViewById(R.id.scoreService);
        this.signdecorateScore = (TextView) findViewById(R.id.scoreDecorating);
        this.signsecurityScore = (TextView) findViewById(R.id.scoreSecurity);
        this.view2 = (View) findViewById(R.id.view2);
        this.view3 = (View) findViewById(R.id.view3);
        this.totalaveragescore = (TextView) findViewById(R.id.averageScore);
        this.sign = (TextView) findViewById(R.id.sign);
        this.mostofuser = (TextView) findViewById(R.id.textView57);
        //this.readmore = (TextView) findViewById(R.id.morecomment);
        //this.readmore.setVisibility(View.GONE);

        this.sharedPreferences = getSharedPreferences("selectDorm",MODE_PRIVATE);
        this.dormname = sharedPreferences.getString("dormname","no value");
        getComment(dormname);
        getScore(dormname);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,)


        //cleaning.setSelectedSmile(BaseRating.GOOD);
        //service.setSelectedSmile(BaseRating.GOOD);
        //decorate.setSelectedSmile(BaseRating.GOOD);
        //security.setSelectedSmile(BaseRating.GOOD);

        // level start 1 to 5 by sequence
       cleaning.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
           @Override
           public void onSmileySelected(int smiley, boolean reselected) {
               cleanscore = cleaning.getRating();
              // Toast.makeText(getApplicationContext(),"clean: "+cleanscore,Toast.LENGTH_SHORT).show();
           }
       });

       service.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
           @Override
           public void onSmileySelected(int smiley, boolean reselected) {
               servicescore = service.getRating();
              // Toast.makeText(getApplicationContext(),"service: "+servicescore,Toast.LENGTH_SHORT).show();

           }
       });

       decorate.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
           @Override
           public void onSmileySelected(int smiley, boolean reselected) {
               decoratescore = decorate.getRating();
              // Toast.makeText(getApplicationContext(),"decorate: "+decoratescore,Toast.LENGTH_SHORT).show();
           }
       });

       security.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
           @Override
           public void onSmileySelected(int smiley, boolean reselected) {
               securityscore = security.getRating();
              // Toast.makeText(getApplicationContext(),"security: "+securityscore,Toast.LENGTH_SHORT).show();

           }
       });

       this.comment.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {
                  commentfromuser = s.toString();
           }
       });


    }

    public void getComment(String dormname){
        String setcommentpath = "http://"+ip+port+"/ScoreAndComment/getCommentByUsername/"+dormname;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, setcommentpath, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject object = response;
                try {
                    JSONArray comment = object.getJSONArray("comment");
                    String[] com = new String[comment.length()];
                    for(int count=0;count<comment.length();count++)
                    {
                        com[count] = comment.getString(count);
                    }
                    if(com.length == 0)
                    {
                        view2.setVisibility(View.GONE);
                        view3.setVisibility(View.GONE);
                    }
                    else
                    {
                        view2.setVisibility(View.VISIBLE);
                        view3.setVisibility(View.VISIBLE);
                    }
                /*
                    if(comment.length() >=4)
                    {
                        readmore.setVisibility(View.VISIBLE);
                    }
                */
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,com){
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);
                            TextView text = (TextView) view.findViewById(android.R.id.text1);
                            text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
                            return view;
                        }
                    };
                    listview.setAdapter(adapter);
                    listview.setExpanded(true);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void getScore(String dormname){
        String scorepath = "http://"+ip+port+"/ScoreAndComment/getCommentByUsername/"+dormname;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, scorepath, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject object = response;
                try {
                    JSONArray cleanscore = object.getJSONArray("cleanScore");
                    JSONArray decoratescore = object.getJSONArray("decorateScore");
                    JSONArray securityscore = object.getJSONArray("securityScore");
                    JSONArray servicescore = object.getJSONArray("serviceScore");

                    int averageCleanscore = 0;
                    int averagedecoratescore = 0;
                    int averagesecurityscore = 0;
                    int averageservicescore = 0;

                    int total = 0;
                    int one = 0;
                    int two = 0;
                    int three = 0;
                    int four = 0;
                    int five = 0;

                    int[] cleans = new int[cleanscore.length()];
                    total = cleanscore.length();
                    int[] deco = new int[decoratescore.length()];
                    int[] secure = new int[securityscore.length()];
                    int[] ser = new int[servicescore.length()];
                    for(int count=0;count<cleanscore.length();count++)
                    {
                        cleans[count] = cleanscore.getInt(count);

                    }

                    for(int count=0;count<cleans.length;count++)
                    {
                        if(cleans[count] == 1)
                        {
                            one++;
                        }
                        else if(cleans[count] == 2)
                        {
                            two++;
                        }
                        else if(cleans[count] == 3)
                        {
                            three++;
                        }
                        else if(cleans[count] == 4)
                        {
                            four++;
                        }
                        else if(cleans[count] == 5)
                        {
                            five++;
                        }
                    }

                    if(total!=0)
                    {
                        averageCleanscore = ((five*5) + (four*4) + (three*3) + (two*2) + (one*1)) / total;
                        cleanScore = averageCleanscore;
                        summary[0] = averageCleanscore;
                        signcleanScore.setText(averageCleanscore+" "+point);

                        one = 0; two = 0; three = 0; four = 0; five = 0; total = 0;

                        for(int count=0;count<decoratescore.length();count++)
                        {
                            deco[count] = decoratescore.getInt(count);
                        }
                        total = decoratescore.length();
                        for(int count=0;count<deco.length;count++)
                        {
                            if(deco[count] == 1)
                            {
                                one++;
                            }
                            else if(deco[count] == 2)
                            {
                                two++;
                            }
                            else if(deco[count] == 3)
                            {
                                three++;
                            }
                            else if(deco[count] == 4)
                            {
                                four++;
                            }
                            else if(deco[count] == 5)
                            {
                                five++;
                            }
                        }

                        averagedecoratescore = ((five*5) + (four*4) + (three*3) + (two*2) + (one*1))/total;
                        decorateScore = averagedecoratescore;
                        summary[1] = averagedecoratescore;
                        signdecorateScore.setText(averagedecoratescore+" "+point);

                        one = 0; two = 0; three = 0; four = 0; five = 0; total = 0;

                        for(int count2=0;count2<securityscore.length();count2++)
                        {
                            secure[count2] = securityscore.getInt(count2);
                        }
                        total = securityscore.length();
                        for(int count3=0;count3<secure.length;count3++)
                        {
                            if(secure[count3] == 1)
                            {
                                one++;
                            }
                            else if(secure[count3] == 2)
                            {
                                two++;
                            }
                            else if(secure[count3] == 3)
                            {
                                three++;
                            }
                            else if(secure[count3] == 4)
                            {
                                four++;
                            }
                            else if(secure[count3] == 5)
                            {
                                five++;
                            }
                        }

                        averagesecurityscore = ((five*5)+(four*4)+(three*3)+(two*2)+(one*1))/ total;
                        securityScore = averagesecurityscore;
                        summary[2] = averagesecurityscore;
                        signsecurityScore.setText(averagesecurityscore+" "+point);

                        one = 0; two = 0; three = 0; four = 0; five = 0; total = 0;

                        for(int count4=0;count4<servicescore.length();count4++)
                        {
                            ser[count4] = servicescore.getInt(count4);
                        }
                        total = servicescore.length();
                        for(int count5=0;count5<ser.length;count5++)
                        {
                            if(ser[count5] == 1)
                            {
                                one++;
                            }
                            else if(ser[count5] == 2)
                            {
                                two++;
                            }
                            else if(ser[count5] == 3)
                            {
                                three++;
                            }
                            else if(ser[count5] == 4)
                            {
                                four++;
                            }
                            else if(ser[count5] == 5)
                            {
                                five++;
                            }
                        }

                        averageservicescore = ((five*5)+ (four*4) + (three*3)+(two*2)+(one*1))/total;
                        serviceScore = averageservicescore;
                        summary[3] = averageservicescore;
                        signserviceScore.setText(averageservicescore+" "+point);

                        System.out.println(cleanScore+" "+decorateScore+" "+securityScore+" "+serviceScore);
                        int sum = 0;
                        for(int nub=0;nub<summary.length;nub++)
                        {
                            sum = sum + summary[nub];
                        }
                        // for(int count=0;count<summary.length;count++)
                       // {
                       //     System.out.println("Summary: "+summary[count]);
                       // }
                        sum = sum / 4;
                        totalaveragescore.setText(String.valueOf(sum)+" "+point);
                    }
                    else
                    {
                        signcleanScore.setText(nopoint);
                        signserviceScore.setText(nopoint);
                        signsecurityScore.setText(nopoint);
                        signdecorateScore.setText(nopoint);
                        totalaveragescore.setVisibility(View.GONE);
                        sign.setVisibility(View.GONE);

                        int sizeInDP = 3;
                        int fiveteeninDp = 15;
                        int marginInDp = (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, sizeInDP, getResources()
                                        .getDisplayMetrics());

                        int marginInDp2 = (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, fiveteeninDp, getResources()
                                        .getDisplayMetrics());

                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(marginInDp2,0,0,0);
                        mostofuser.setLayoutParams(layoutParams);
                        mostofuser.setTypeface(Typeface.DEFAULT_BOLD);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void saveComment(View v){
        RequestQueue queue = Volley.newRequestQueue(this);
        try
        {
            System.out.println(cleanscore+"  clean");
            System.out.println(servicescore+"    service");
            System.out.println(decoratescore+"    decorate");
            System.out.println(securityscore+"   security");
            System.out.println(commentfromuser+"   comment");

            cleanlist.add(cleanscore);
            servicelist.add(servicescore);
            decoratelist.add(decoratescore);
            securitylist.add(securityscore);
            commentlist.add(commentfromuser);

            JSONObject json = new JSONObject();
            JSONArray cleanarray = new JSONArray();
            JSONArray servicearray = new JSONArray();
            JSONArray decoratearray = new JSONArray();
            JSONArray securityarray = new JSONArray();
            JSONArray commentarray = new JSONArray();

            for(int count=0;count<cleanlist.size();count++)
            {
                cleanarray.put(count,cleanlist.get(count));
            }

            for(int count2=0;count2<servicelist.size();count2++)
            {
                servicearray.put(count2,servicelist.get(count2));
            }

            for(int count3=0;count3<decoratelist.size();count3++)
            {
                decoratearray.put(count3,decoratelist.get(count3));
            }

            for(int count4=0;count4<securitylist.size();count4++)
            {
                securityarray.put(count4,securitylist.get(count4));
            }

            for(int count5=0;count5<commentlist.size();count5++)
            {
                commentarray.put(count5,commentlist.get(count5));
            }

            String savepath = "http://"+ip+port+"/ScoreAndComment/updateScoreandCommentbyDormID/"+dormname;

            json.put("dormID",dormname);
            json.put("cleanScore",cleanarray);
            json.put("decorateScore",decoratearray);
            json.put("securityScore",securityarray);
            json.put("serviceScore",servicearray);
            json.put("averageScore",averagescore);
            json.put("comment",commentarray);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, savepath, json, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println("Favorite: "+response);
                    Intent i = new Intent(CommentandScore_PricePromotion_Style.this,CommentandScore_PricePromotion_Style.class);
                    startActivity(i);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(request);
     }
     catch (JSONException ex){
         ex.printStackTrace();
     }
    }
}
