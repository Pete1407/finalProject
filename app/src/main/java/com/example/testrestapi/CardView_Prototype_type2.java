package com.example.testrestapi;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.StringTokenizer;

public class CardView_Prototype_type2 extends Fragment {
    public String name;
    public String ip = "192.168.43.57:8080";
    public String unit;
    public CardView_Prototype_type2(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basicprototype2,container,false);
        ImageView coverimage = view.findViewById(R.id.imageView);
        TextView dormname = view.findViewById(R.id.dormname);
        TextView likes = view.findViewById(R.id.likes);
        TextView subtitle = view.findViewById(R.id.subtitile);
        TextView address = view.findViewById(R.id.address);
        TextView info = view.findViewById(R.id.infotextview);
        TextView price = view.findViewById(R.id.price);

        unit = getActivity().getResources().getString(R.string.unit);

        Bundle b = this.getArguments();
        name = b.getString("dormname");
        dormname.setText(name);
        dormname.setTypeface(Typeface.DEFAULT_BOLD);
        int black = getActivity().getResources().getColor(R.color.black);
        dormname.setTextColor(black);
        double distance = 0;
        subtitle.setText(String.valueOf(distance)+" จากคณะ XXX");
        info.setTextColor(Color.BLACK);
        info.setBackgroundResource(R.drawable.background_for_textview);
       final RequestQueue queue = Volley.newRequestQueue(getActivity());
        String path1 = "http://"+ip+"/DormTypeInformation/getDorm/"+name;
        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, path1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String detail = response.getString("detail");
                    StringTokenizer tokenizer = new StringTokenizer(detail,",");
                    String[] hippo = new String[tokenizer.countTokens()];
                    for(int count2=0;count2<hippo.length;count2++)
                    {
                        //System.out.println(tokenizer.nextToken());
                        hippo[count2] = tokenizer.nextToken();
                    }
                    info.setText(hippo[0]);
                    String path2 = "http://"+ip+"/InfoDorm/getInfoDormByDormName/"+name;
                    JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, path2, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int minPrice = response.getInt("minPrice");
                                price.setText(String.valueOf(minPrice)+" "+unit);

                               String path3 = "http://"+ip+"/dorm/getDormByDormName/"+name;
                               JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.GET, path3, null, new Response.Listener<JSONObject>() {
                                   @Override
                                   public void onResponse(JSONObject response) {
                                       try {
                                           String addr = response.getString("address");
                                           address.setText(addr);

                                           likes.setText(String.valueOf(0));

                                           String path4 = "http://"+ip+"/dormOwner/getInfoDormOwnerByDormName/"+name;
                                           JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET, path4, null, new Response.Listener<JSONObject>() {
                                               @Override
                                               public void onResponse(JSONObject response) {
                                                   try {
                                                       String username = response.getString("username");
                                                       String email = response.getString("email");

                                                       String getCoverImage = "http://" + ip + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                                       JsonObjectRequest request5 = new JsonObjectRequest(Request.Method.GET, getCoverImage, null, new Response.Listener<JSONObject>() {
                                                           @Override
                                                           public void onResponse(JSONObject response) {
                                                               try {
                                                                   String pathImage = response.getString("coverImage");
                                                                   Picasso.with(getActivity()).load(pathImage).into(coverimage);
                                                               }
                                                               catch (JSONException ex)
                                                               {
                                                                   ex.printStackTrace();
                                                               }
                                                           }
                                                       }, new Response.ErrorListener() {
                                                           @Override
                                                           public void onErrorResponse(VolleyError error) {

                                                           }
                                                       });
                                                       queue.add(request5);
                                                   }
                                                   catch (JSONException ex){
                                                       ex.printStackTrace();
                                                   }
                                               }
                                           }, new Response.ErrorListener() {
                                               @Override
                                               public void onErrorResponse(VolleyError error) {

                                               }
                                           });
                                           queue.add(request4);
                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }
                                   }
                               }, new Response.ErrorListener() {
                                   @Override
                                   public void onErrorResponse(VolleyError error) {

                                   }
                               });
                               queue.add(request3);
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
        return view;
    }
}
