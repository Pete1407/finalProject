package com.example.testrestapi;

import android.graphics.Paint;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class CardView_Prototype_type1 extends Fragment {
    public String ip = "192.168.43.57:8080";
    public String dormname;
    public String unit;
    public CardView_Prototype_type1(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basicprototype1,container,false);
        ImageView coverimage = view.findViewById(R.id.imageView);
        TextView name = view.findViewById(R.id.dormname);
        TextView tagsale = view.findViewById(R.id.tagsale);
        TextView oldprice = view.findViewById(R.id.oldprice);
        TextView subtitle = view.findViewById(R.id.subtitile);
        TextView promotiondetail = view.findViewById(R.id.prodetail);

        unit = getActivity().getResources().getString(R.string.unit);
        double distance = 0;
        subtitle.setText(String.valueOf(distance)+" จากคณะ XXX");
        Bundle b  = this.getArguments();
        this.dormname = b.getString("dormname");
        final RequestQueue queue = Volley.newRequestQueue(getActivity());
        String path1 = "http://"+ip+"/DormTypePriceNPromotion/getDormBydormName/"+dormname;
        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, path1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    String id = response.getString("dormID");
                    String prodetail = response.getString("promotionDescribe");
                    int oldPrice = response.getInt("oldPrice");
                    int newPrice = response.getInt("newPrice");

                    String getUsernameAndEmail = "http://"+ip+"/dormOwner/getInfoDormOwnerByDormName/"+dormname;
                    JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, getUsernameAndEmail, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONObject dormowner = response;
                            try {
                                String username = dormowner.getString("username");
                                String email = dormowner.getString("email");

                                String coverImagePath = "http://" + ip + "/CoverImage/getCoverImageByusernameAndEmail/" + username + "/" + email;
                                JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.GET, coverImagePath, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try
                                        {
                                            JSONObject coverImage = response;
                                            String coverImagePath = coverImage.getString("coverImage");
                                            Picasso.with(getActivity()).load(coverImagePath).into(coverimage);

                                            String pathGetPriceDorm = "http://"+ip+"/InfoDorm/getInfoDormByDormName/"+dormname;
                                            JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET, pathGetPriceDorm, null, new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    try
                                                    {
                                                        int minpricenopromotion = response.getInt("minPrice");

                                                        if(!prodetail.equals(""))
                                                        {
                                                            name.setText(dormname);
                                                            oldprice.setText(String.valueOf(newPrice)+" "+unit);
                                                            promotiondetail.setText(String.valueOf(oldPrice)+" "+unit);
                                                            promotiondetail.setPaintFlags( promotiondetail.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                                            //promotiondetail.setVisibility(View.GONE);
                                                            tagsale.setVisibility(View.VISIBLE);
                                                        }
                                                        else
                                                        {
                                                            name.setText(dormname);
                                                            tagsale.setVisibility(View.GONE);
                                                            oldprice.setText(String.valueOf(minpricenopromotion)+" "+unit);
                                                            promotiondetail.setVisibility(View.GONE);
                                                        }
                                                    }
                                                    catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }


                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            });
                                            queue.add(request4);

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
                                queue.add(request3);
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
                    queue.add(request2);
                }
                catch (JSONException e) {
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
