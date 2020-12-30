package com.example.testrestapi.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import com.example.testrestapi.R;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightGridView;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
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
import java.util.StringTokenizer;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class Detail_Prototype_type2 extends Fragment {
    public String dormname;
    public ImageView image360;
    public TextView dormName;
    public TextView sign_favorite;
    public TextView describedetail;
    public GoogleMap map;
    public TextView address;
    public TextView distance;
    public TextView timewalking;
    public TextView timedriving;
    public TextView timetransit;
    public ExpandableHeightListView listview;

    public TextView roomprice;
    public TextView waterprice;
    public TextView electroprice;
    public TextView depositprice;
    public TextView commonfeeprice;
    public TextView facilityTex;

    public TextView datanamedormowner;
    public TextView datacontactdormowner;
    public TextView dataemaildormowner;
    public TextView dataaddressdormowner;

    public ImageView docImage;
    public ExpandableHeightListView listview2;
    public ExpandableHeightListView listview3;
    public TextView averageScore;

    public TextView scoreCleanning;
    public TextView scoreService;
    public TextView scoreDecorating;
    public TextView scoreSecurity;
    public Button button;
    public SmileRating commentcleanning;
    public SmileRating commentService;
    public SmileRating commentDecorating;
    public SmileRating commentSecurity;
    public EditText editText;

    public ArrayList<String> faclist;

    public String port = ":8080";
    public String ip = "192.168.43.57";

    public String username;
    public String email;
    public TextView text;
    public String pathDocument;
    public Detail_Prototype_type2(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_prototype_type2,container,false);
        faclist = new ArrayList<>();
        Bundle b = this.getArguments();
        dormname = b.getString("dormname");

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("file_for_dormowner", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username","no value");
        email = sharedPreferences.getString("email","no value");

        image360 = view.findViewById(R.id.image360);
        dormName = view.findViewById(R.id.dormName);
        dormName.setText(dormname);
        dormName.setTypeface(Typeface.DEFAULT_BOLD);
        sign_favorite = view.findViewById(R.id.sign_favorite);
        String textfav = getActivity().getResources().getString(R.string.pressFavorite);
        int g = 0;
        int red = getActivity().getResources().getColor(R.color.red);
        sign_favorite.setTextColor(red);
        sign_favorite.setText(String.valueOf(g)+" "+textfav);
        describedetail = view.findViewById(R.id.describedetail);
        address = view.findViewById(R.id.address);
        distance = view.findViewById(R.id.distance);
        timewalking = view.findViewById(R.id.timeWalking);
        timedriving = view.findViewById(R.id.timeDriving);
        timetransit = view.findViewById(R.id.timeTransit);
        roomprice = view.findViewById(R.id.roomprice);
        waterprice = view.findViewById(R.id.waterprice);
        electroprice = view.findViewById(R.id.electroprice);
        depositprice = view.findViewById(R.id.depositprice);
        commonfeeprice = view.findViewById(R.id.commonfeeprice);
        facilityTex = view.findViewById(R.id.facilityText);
        datanamedormowner = view.findViewById(R.id.datanamedormowner);
        datacontactdormowner = view.findViewById(R.id.datacontactdormowner);
        dataemaildormowner = view.findViewById(R.id.dataemaildormowner);
        dataaddressdormowner = view.findViewById(R.id.dataaddressdormowner);
        docImage = view.findViewById(R.id.docImage);
        listview2 = view.findViewById(R.id.listview2);
        averageScore = view.findViewById(R.id.averageScore);
        scoreCleanning = view.findViewById(R.id.scoreCleanning);
        scoreService = view.findViewById(R.id.scoreService);
        scoreDecorating = view.findViewById(R.id.scoreDecorating);
        scoreSecurity = view.findViewById(R.id.scoreSecurity);
        commentcleanning = view.findViewById(R.id.commentcleanning);
        commentService = view.findViewById(R.id.commentService);
        commentDecorating = view.findViewById(R.id.commentDecorating);
        commentSecurity = view.findViewById(R.id.commentSecurity);
        button = view.findViewById(R.id.button);
        editText = view.findViewById(R.id.editText);
        text = view.findViewById(R.id.text);
        text.setText("สถานที่ต่างๆในมหาวิทยาลัย");
        button.setClickable(false);
        editText.setEnabled(false);
        editText.setFocusable(false);

        int d1 = 0;
        String noscore = getActivity().getResources().getString(R.string.nopoint);
        String score = getActivity().getResources().getString(R.string.scoreUnit);
        averageScore.setText(String.valueOf(d1)+" "+score);
        scoreCleanning.setText(noscore);
        scoreDecorating.setText(noscore);
        scoreSecurity.setText(noscore);
        scoreService.setText(noscore);

        SupportMapFragment mapfgr = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapfgr.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                map.getUiSettings().setAllGesturesEnabled(false);
            }
        });

        getImage360(email,username);
        getExpense(dormname);
        showContact(dormname);
        getPositionDorm(dormname);
        getFacility(dormname);
        getPremieum(dormname);

        return view;
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
                                facilityTex.setText(g);







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

    public void getPremieum(String dormName){

        final ArrayList<String> array = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String pathPremieum = "http://"+ip+port+"/DormTypeInformation/getDorm/"+dormName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, pathPremieum, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String[] text;
                    String detail = response.getString("detail");
                    StringTokenizer token = new StringTokenizer(detail,",");
                    text = new String[token.countTokens()];
                    while(token.hasMoreTokens())
                    {
                        String aa = token.nextToken();
                        System.out.println(aa+"  &&&*&*&*&*");
                        array.add(aa);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array){
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);
                            TextView tv = (TextView) view.findViewById(android.R.id.text1);
                            tv.setTextColor(Color.WHITE);
                            return tv;
                        }
                    };
                    listview2.setAdapter(adapter);
                    listview2.setExpanded(true);

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
