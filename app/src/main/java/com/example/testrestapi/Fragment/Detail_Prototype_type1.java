package com.example.testrestapi.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.testrestapi.Detail_Dorm_PriceAndPromotion;
import com.example.testrestapi.Facility_PricePromotion_Style;
import com.example.testrestapi.Facility_Prototype_type1;
import com.example.testrestapi.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class Detail_Prototype_type1 extends Fragment {
    public GoogleMap map;
    public String namedorm;
    public String port = ":8080";
    public String ip = "192.168.43.57";
    public ImageView image360;
    public String username;
    public String email;
    public LinearLayout offerpro;
    public ImageView offer;
    TextView detailoffer;
    LinearLayout sale;
    ImageView piggy;
    TextView saveMoney;
    TextView roomprice;
    TextView waterprice;
    TextView electroprice;
    TextView depositprice;
    TextView commonfeeprice;
    TextView describedetail;
    TextView address;
    TextView distance;
    TextView timewalking;
    TextView timedriving;
    TextView timetransit;
    TextView datacontactdormowner;
    TextView dataemaildormowner;
    TextView dataaddressdormowner;
    ImageView docImage;
    public LinearLayout offerPromotion;
    public LinearLayout sale_saveMoney;
    public LinearLayout sale_piggy;
    public TextView datanamedormowner;
    public String pathDocument;
    public LinearLayout facility;
    public Detail_Prototype_type1(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_prototype_type1,container,false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("file_for_dormowner", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username","no value");
        email = sharedPreferences.getString("email","no value");

        Bundle b = this.getArguments();
        namedorm = b.getString("dormname");
        int black = getActivity().getResources().getColor(R.color.black);
        image360 = v.findViewById(R.id.image360);
        TextView dornname = v.findViewById(R.id.dormName);
        dornname.setText(namedorm);
        dornname.setTextColor(black);
        dornname.setTextSize(18);
        dornname.setTypeface(Typeface.DEFAULT_BOLD);
        TextView signfav = v.findViewById(R.id.sign_favorite);
        String textfav = getActivity().getResources().getString(R.string.pressFavorite);
        signfav.setText(String.valueOf(0)+" "+textfav);
        int red = getActivity().getResources().getColor(R.color.red);
        signfav.setTextColor(red);
        ImageView heart = v.findViewById(R.id.heart);
        offerpro = v.findViewById(R.id.offer_promotion);
        offer = v.findViewById(R.id.offer);
        detailoffer = v.findViewById(R.id.detail_offer);
        sale = v.findViewById(R.id.sale);
        piggy = v.findViewById(R.id.piggy);
        saveMoney = v.findViewById(R.id.saveMoney);
        roomprice = v.findViewById(R.id.roomprice);
        waterprice = v.findViewById(R.id.waterprice);
        electroprice = v.findViewById(R.id.electroprice);
        depositprice = v.findViewById(R.id.depositprice);
        commonfeeprice = v.findViewById(R.id.commonfeeprice);
        describedetail = v.findViewById(R.id.describedetail);
        address = v.findViewById(R.id.address);
        distance = v.findViewById(R.id.distance);
        timewalking = v.findViewById(R.id.timeWalking);
        timedriving = v.findViewById(R.id.timeDriving);
        timetransit = v.findViewById(R.id.timeTransit);
        datacontactdormowner = v.findViewById(R.id.datacontactdormowner);
        dataemaildormowner = v.findViewById(R.id.dataemaildormowner);
        dataaddressdormowner = v.findViewById(R.id.dataaddressdormowner);
        docImage = v.findViewById(R.id.docImage);
        offerPromotion = v.findViewById(R.id.offer_promotion);
        sale_saveMoney = v.findViewById(R.id.sale_saveMoney);
        sale_piggy = v.findViewById(R.id.sale_piggy);
        datanamedormowner = v.findViewById(R.id.datanamedormowner);
        facility = v.findViewById(R.id.facility);
        getImage360(email,username);
        getExpense(namedorm);
        showContact(namedorm);

        SupportMapFragment mapfgr = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapfgr.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                googleMap.getUiSettings().setZoomGesturesEnabled(false);
                googleMap.getUiSettings().setZoomControlsEnabled(false);
            }
        });
        getPositionDorm(namedorm);




        return v;
    }

    public void getImage360(String email,String username){
        System.out.println("HHHHHH");
        String path = "http://"+ip+port+"/Image360/getImage360byUsernameAndEmail/"+username+"/"+email;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String imagepath = response.getString("image360");
                    Glide.with(getActivity()).load(imagepath).apply(bitmapTransform(new BlurTransformation(22))).into(image360);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }

    public void getExpense(String dormname){
        RequestQueue q = Volley.newRequestQueue(getActivity());
        String expensePath = "http://"+ip+port+"/InfoDorm/getInfoDormByDormName/"+dormname;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, expensePath, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject res) {
                try
                {
                    String baht = getResources().getString(R.string.baht);
                    int minPriceN = res.getInt("minPrice");
                    int waterPriceN = res.getInt("priceWater");
                    int electroPriceN = res.getInt("priceElectro");
                    int depositPriceN = res.getInt("depositPrice");
                    int commonfeeN = res.getInt("commonFee");
                    String typeWater = res.getString("typeWater");
                    String typeElectro = res.getString("typeElectro");
                    String description = res.getString("description");
                    waterprice.setText(String.valueOf(waterPriceN)+" "+baht+"("+typeWater+")");
                    electroprice.setText(String.valueOf(electroPriceN)+" "+baht+"("+typeElectro+")");
                    describedetail.setText(description);
                    String a = getResources().getString(R.string.free);
                    if(depositPriceN == 0)
                    {
                        depositprice.setText(a);
                    }
                    else
                    {
                        depositprice.setText(String.valueOf(depositPriceN)+" บาท");

                    }

                    if(commonfeeN == 0)
                    {
                        commonfeeprice.setText(a);
                    }
                    else
                    {
                        commonfeeprice.setText(String.valueOf(commonfeeN)+" บาท");
                    }
                    String path = "http://"+ip+port+"/DormTypePriceNPromotion/getDormBydormName/"+dormname;
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String promotionDetail = response.getString("promotionDescribe");
                                if(!promotionDetail.equals(""))
                                {
                                    int oldP = response.getInt("oldPrice");
                                    int newP = response.getInt("newPrice");
                                    int savemoney = oldP - newP;
                                    detailoffer.setText(promotionDetail);
                                    detailoffer.setTextSize(17);

                                    saveMoney.setText("ประหยัดไป "+String.valueOf(savemoney)+" "+baht);
                                    saveMoney.setTextSize(17);
                                    roomprice.setText(String.valueOf(newP)+" "+baht);
                                    System.out.println(savemoney);
                                }
                                else if(promotionDetail.equals(""))
                                {
                                    roomprice.setText(String.valueOf(minPriceN)+" "+baht);
                                    offerPromotion.setVisibility(View.GONE);
                                    sale.setVisibility(View.GONE);
                                    sale_piggy.setVisibility(View.GONE);
                                    sale_saveMoney.setVisibility(View.GONE);
                                    piggy.setVisibility(View.GONE);
                                }

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
                    q.add(req);




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


        q.add(request);
    }

    public void  showContact(final String dormName){
        final RequestQueue q = Volley.newRequestQueue(getActivity());
        String path = "http://"+ip+port+"/dormOwner/getInfoDormOwnerByDormName/"+dormName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String firstname = response.getString("name");
                    String lastname = response.getString("lastname");
                    String fullanme = firstname +" "+lastname;
                    String contact = response.getString("contact");
                    String email = response.getString("email");
                    String username = response.getString("username");
                    datanamedormowner.setText(fullanme);
                    datacontactdormowner.setText(contact);
                    dataemaildormowner.setText(email);

                    String pathaddress = "http://"+ip+port+"/dorm/getDormByDormName/"+dormName;
                    JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, pathaddress, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String address = response.getString("address");
                                dataaddressdormowner.setText(address);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    q.add(request2);

                    String getDocument = "http://"+ip+port+"/documentImage/getpathImageByEmailAndUsername/"+email+"/"+username;
                    StringRequest docrequest = new StringRequest(Request.Method.GET, getDocument, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            pathDocument = response;
                            Picasso.with(getActivity()).load(pathDocument).into(docImage);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    q.add(docrequest);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        q.add(request);
    }

    public void getPositionDorm(String dormName){
        String path = "http://"+ip+port+"/dorm/getDormByDormName/"+dormName;
        final RequestQueue q = Volley.newRequestQueue(getActivity());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String addressText = response.getString("address");
                    final double latitude = response.getDouble("latitude");
                    final double longtitude = response.getDouble("longtitude");
                    address.setText(addressText);

                    final int height = 120;
                    final int width = 120;
                    BitmapDrawable bitmapdrawable2 = (BitmapDrawable)getResources().getDrawable(R.drawable.pin_place_modify);
                    Bitmap b2 = bitmapdrawable2.getBitmap();
                    Bitmap smallMarker2 = Bitmap.createScaledBitmap(b2, width, height, false);

                    MarkerOptions dorm = new MarkerOptions().position(new LatLng(latitude,longtitude)).icon(BitmapDescriptorFactory.fromBitmap(smallMarker2));
                    map.addMarker(dorm);

                    CameraPosition position = CameraPosition.builder()
                            .target(new LatLng(latitude,longtitude))
                            .zoom(15)
                            .build();

                    map.animateCamera(CameraUpdateFactory.newCameraPosition(position),1000,null);
                    distance.setVisibility(View.GONE);
                    timewalking.setText(String.valueOf(0)+" mins");
                    timedriving.setText(String.valueOf(0)+" mins");
                    timetransit.setText(String.valueOf(0)+" mins");
                    int red = getResources().getColor(R.color.red);
                    timewalking.setTextColor(red);
                    timedriving.setTextColor(red);
                    timetransit.setTextColor(red);

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

        q.add(request);
    }





}
