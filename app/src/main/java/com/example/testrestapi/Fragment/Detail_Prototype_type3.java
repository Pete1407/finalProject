package com.example.testrestapi.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.testrestapi.Premieum;
import com.example.testrestapi.PremiumAdapter;
import com.example.testrestapi.R;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightGridView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hsalf.smilerating.SmileRating;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class Detail_Prototype_type3 extends Fragment {
    public ImageView image360;
    public TextView dormName;
    public TextView sign_favorite;
    public TextView roomprice;
    public TextView waterprice;
    public TextView electroprice;
    public TextView depositPrice;
    public TextView commonfeeprice;
    public TextView describedetail;
    public TextView address;

    public TextView distance;

    public TextView datanamedormowner;
    public TextView datacontactdormowner;
    public TextView dataemaildormowner;
    public TextView dataaddressdormowner;
    public ImageView docImage;
    public String pathDocument;
    public TextView timeWalking;
    public TextView timeDriving;
    public TextView timeTransit;
    public TextView averageScore;
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
    public TextView scoreCleanning;
    public TextView scoreService;
    public TextView scoreDecorating;
    public TextView scoreSecurity;
    public ExpandableHeightGridView premiumGrid;
    public TextView depositprice;
    public TextView facilityText;
    public TextView text;
    public TextView nearbyp;

    public SmileRating commentcleanning;
    public SmileRating commentService;
    public SmileRating commentDecorating;
    public SmileRating commentSecurity;
    public EditText editText;

    public String email;
    public String username;
    public String port = ":8080";
    public String ip = "192.168.43.57";
    public GoogleMap map;
    public String namedorm;
    public ArrayList<String> faclist;
    public Detail_Prototype_type3(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_prototype_type3,container,false);
        faclist = new ArrayList<>();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("file_for_dormowner", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username","no value");
        email = sharedPreferences.getString("email","no value");

        Bundle b = this.getArguments();
        namedorm = b.getString("dormname");

        image360 = v.findViewById(R.id.image360);
        dormName = v.findViewById(R.id.dormName);
        dormName.setText(namedorm);
        dormName.setTypeface(Typeface.DEFAULT_BOLD);
        String textfav = getActivity().getResources().getString(R.string.pressFavorite);
        sign_favorite = v.findViewById(R.id.sign_favorite);
        int a = 0;
        sign_favorite.setText(a+" "+textfav);
        int red = getActivity().getResources().getColor(R.color.red);
        sign_favorite.setTextColor(red);
        datanamedormowner = v.findViewById(R.id.datanamedormowner);
        datacontactdormowner = v.findViewById(R.id.datacontactdormowner);
        dataemaildormowner = v.findViewById(R.id.dataemaildormowner);
        dataaddressdormowner = v.findViewById(R.id.dataaddressdormowner);
        docImage = v.findViewById(R.id.docImage);
        averageScore = v.findViewById(R.id.averageScore);
        int zero = 0;
        String uu = getActivity().getResources().getString(R.string.scoreUnit);
        String noscore = getActivity().getResources().getString(R.string.nopoint);
        averageScore.setText(String.valueOf(zero)+" "+uu);
        scoreCleanning = v.findViewById(R.id.scoreCleanning);
        scoreService = v.findViewById(R.id.scoreService);
        scoreDecorating = v.findViewById(R.id.scoreDecorating);
        scoreSecurity = v.findViewById(R.id.scoreSecurity);
        scoreCleanning.setText(noscore);
        scoreService.setText(noscore);
        scoreDecorating.setText(noscore);
        scoreSecurity.setText(noscore);

        premiumGrid = v.findViewById(R.id.gridview);
        address = v.findViewById(R.id.address);
        distance = v.findViewById(R.id.distance);
        timeWalking = v.findViewById(R.id.timeWalking);
        timeDriving = v.findViewById(R.id.timeDriving);
        timeTransit = v.findViewById(R.id.timeTransit);
        roomprice = v.findViewById(R.id.roomprice);
        waterprice = v.findViewById(R.id.waterprice);
        electroprice = v.findViewById(R.id.electroprice);
        depositprice = v.findViewById(R.id.depositprice);
        commonfeeprice = v.findViewById(R.id.commonfeeprice);
        facilityText = v.findViewById(R.id.facilityText);
        text = v.findViewById(R.id.text);
        nearbyp = v.findViewById(R.id.textView65);
        describedetail = v.findViewById(R.id.describedetail);
        commentcleanning = v.findViewById(R.id.commentcleanning);
        commentService = v.findViewById(R.id.commentService);
        commentDecorating = v.findViewById(R.id.commentDecorating);
        commentSecurity = v.findViewById(R.id.commentSecurity);
        editText = v.findViewById(R.id.editText);
        button = v.findViewById(R.id.button);
        button.setEnabled(false);
        editText.setFocusable(false);
        editText.setEnabled(false);
        View view3 = v.findViewById(R.id.view3);
        view3.setVisibility(View.GONE);
        text.setText("สถานที่ต่างๆในมหาวิทยาลัย");
        SupportMapFragment mapfgr = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapfgr.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                map.getUiSettings().setAllGesturesEnabled(false);
            }
        });


        getImage360(email,username);
        showContact(namedorm);
        getExpense(namedorm);
        getPremium(namedorm);
        getFacility(namedorm);
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
                    roomprice.setText(minPriceN+" "+baht);
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


    public void getPremium(String dormName){
        final ArrayList<Premieum> list = new ArrayList<>();
        String premiumPath = "http://"+ip+port+"/DormTypeQuality/getPremiumBydormName/"+dormName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, premiumPath, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject obj = response;
                try {
                    JSONArray image = obj.getJSONArray("imagelist");
                    JSONArray textTH = obj.getJSONArray("premieum_th");
                    JSONArray textEN = obj.getJSONArray("premieum_en");
                    Premieum[] premium = new Premieum[image.length()];
                    for(int count=0;count<image.length();count++)
                    {
                        String textth = textTH.getString(count);
                        String texten = textEN.getString(count);
                        int imageCode = image.getInt(count);
                        premium[count] = new Premieum(textth,texten,imageCode);
                        list.add(premium[count]);
                    }

                    PremiumAdapter adapter = new PremiumAdapter(getActivity(),list,0);
                    premiumGrid.setAdapter(adapter);
                    premiumGrid.setExpanded(true);


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

    public void getFacility(String dormName){
        String pathIndorm = "http://"+ip+port+"/facilityInDorm/getFacilityInDorm/"+dormName;
        final String pathInroom = "http://"+ip+port+"/facilityInRoom/getFacilityInRoom/"+dormName;

        final RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest indorm = new JsonObjectRequest(Request.Method.GET, pathIndorm, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    final JSONArray nameTH = response.getJSONArray("nameTH");
                    JSONArray nameEN = response.getJSONArray("nameEN");
                    JSONArray imagecode = response.getJSONArray("image");
                    // facilityList1 = new Facility[imagecode.length()];
                    for(int count=0;count<imagecode.length();count++)
                    {
                        int code = imagecode.getInt(count);
                        String th = nameTH.getString(count);
                        String en = nameEN.getString(count);
                        faclist.add(th);
                        //   facilityList1[count] = new Facility(th,en,code);
                        //   list.add(facilityList1[count]);
                    }

                    //System.out.println("FACILITY IN DORM : "+facilityList1.length);

                    JsonObjectRequest inroom = new JsonObjectRequest(Request.Method.GET, pathInroom, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray nameth = response.getJSONArray("nameTH");
                                JSONArray nameen = response.getJSONArray("nameEN");
                                JSONArray imagecode = response.getJSONArray("image");
                                //facilityList2 = new Facility[imagecode.length()];
                                for(int count2=0;count2<imagecode.length();count2++)
                                {
                                    int codee = imagecode.getInt(count2);
                                    String thtext = nameth.getString(count2);
                                    String entext = nameen.getString(count2);
                                    faclist.add(thtext);
                                    //  facilityList2[count2] = new Facility(thtext,entext,codee);
                                    //  list.add(facilityList2[count2]);
                                }

                                String g = "";
                                for(int count3=0;count3<faclist.size();count3++)
                                {
                                    g = g + faclist.get(count3)+", ";
                                }
                                facilityText.setText(g);







                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    queue.add(inroom);

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(indorm);
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
                    timeWalking.setText(String.valueOf(0)+" mins");
                    timeDriving.setText(String.valueOf(0)+" mins");
                    timeTransit.setText(String.valueOf(0)+" mins");
                    int red = getResources().getColor(R.color.red);
                    timeWalking.setTextColor(red);
                    timeDriving.setTextColor(red);
                    timeTransit.setTextColor(red);

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
