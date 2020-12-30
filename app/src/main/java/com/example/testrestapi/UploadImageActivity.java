package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UploadImageActivity extends AppCompatActivity {
    public ActionBar bar;
    public TextView uploadCover;
    public ImageButton cover;
    public TextView uploadPano;
    public ImageView imagePano;
    public ImageButton info;

    public Button showimage;
    public Button addImage;
    public ImageView test;

    public Thread thread;
    public GridView grid;


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

    /// Part of type Price and Promotion ///
    public String promotionDescribe;
    public int oldPrice;
    public int newPrice;

    /// Part of type Information ///
    public String information;

    /// Part of type Quality ///
    public ArrayList<Premieum> list = new ArrayList<>();

    /// Part of type BrandName ///
    public String logo;


    /// Part of all type ///
    public String coverImage;
    public String image360;

    public String[] array;

    public Bitmap composite_coverimage;
    public Bitmap composite_picture360;
    public Bitmap composite_pictures;
    public ArrayList<Uri> mArrayUri = new ArrayList<>();

    public String username;
    public String password;
    public String email;

    public String coverImageText;
    public String image360Text;
    public String galleryText;
    public ArrayList<Bitmap> gallist;
    public Bitmap b;
    public String ip = "192.168.43.57:8080";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        this.gallist = new ArrayList<>();
        this.bar = getSupportActionBar();
        array = getResources().getStringArray(R.array.dormtype);
        dormStyle = getIntent().getStringExtra("dormStyle");

        // KEY FOR SPECIFY IMAGE
        this.username = getIntent().getStringExtra("username");
        this.password = getIntent().getStringExtra("password");
        this.email = getIntent().getStringExtra("email");

        //Toast.makeText(this,"Email: "+email+"\n"+"Username: "+username,Toast.LENGTH_LONG).show();

        //System.out.println("USERNAME : "+username+" -----------------------------");
        //System.out.println("EMAIL : "+email+" -----------------------------");

        int id = R.string.uploadImage;
        String title = getResources().getString(id);
        this.bar.setTitle(title);
        this.bar.setDisplayHomeAsUpEnabled(true);
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));


        this.uploadCover = (TextView) findViewById(R.id.uploadCover);
        this.uploadCover.setText("อัปโหลดรูปภาพหน้าปก");
        this.uploadPano = (TextView) findViewById(R.id.uploadImagePanoTitle);
        this.uploadPano.setText("อัปโหลดรูปภาพ 360 องศา");

        this.imagePano = (ImageView) findViewById(R.id.imageButton);
        this.info = (ImageButton) findViewById(R.id.info);
        this.addImage = (Button) findViewById(R.id.images);

        this.showimage = (Button) findViewById(R.id.showimage);
        this.showimage.setVisibility(View.GONE);

        this.grid = (GridView) findViewById(R.id.grid);

        if(dormStyle.equals(array[0]))
        {
            //Toast.makeText(this,"User is Price N Promotion Style",Toast.LENGTH_LONG).show();
            dormID = getIntent().getStringExtra("dormID");
            minPrice = getIntent().getIntExtra("minPrice",0);
            maxPrice = getIntent().getIntExtra("maxPrice",0);
            typeWater = getIntent().getStringExtra("typeWater");
            priceWater = getIntent().getIntExtra("priceWater",0);
            typeElectro = getIntent().getStringExtra("typeElectro");
            priceElectro = getIntent().getIntExtra("priceElectro",0);
            typeDeposit = getIntent().getStringExtra("typeDeposit");
            depositPrice = getIntent().getIntExtra("depositPrice",0);
            //System.out.println("SEE DEPOSIT PRICE IN PRICE N PROMOTION : "+depositPrice);
            typeCommonFee = getIntent().getStringExtra("typeCommonFee");
            commonFee = getIntent().getIntExtra("commonFee",0);
            description = getIntent().getStringExtra("description");
            dormStyle = getIntent().getStringExtra("dormStyle");
            promotionDescribe = getIntent().getStringExtra("promotionDescribe");
            oldPrice = getIntent().getIntExtra("OldPrice",0);
            newPrice = getIntent().getIntExtra("NewPrice",0);

            System.out.println("DORM STYLE: "+dormStyle);
            System.out.println("DORM ID: "+dormID);
            System.out.println("MIN PRICE: "+minPrice);
            System.out.println("MAX PRICE: "+maxPrice);
            System.out.println("TYPE WATER: "+typeWater);
            System.out.println("PRICE WATER: "+priceWater);
            System.out.println("TYPE ELECTRO: "+typeElectro);
            System.out.println("PRICE ELECTRO: "+priceElectro);
            System.out.println("TYPE DEPOSIT: "+typeDeposit);
            System.out.println("PRICE DEPOSIT: "+depositPrice);
            System.out.println("TYPE COMMONFEE: "+typeCommonFee);
            System.out.println("PRICE COMMONFEE: "+commonFee);
            System.out.println("Description: "+description);
            System.out.println("PROMOTION DESCRIBE: "+promotionDescribe);
            System.out.println("OLD PRICE: "+oldPrice);
            System.out.println("NEW PRICE: "+newPrice);
        }
        else if(dormStyle.equals(array[1]))
        {
           // Toast.makeText(this,"User is information and Detail Style",Toast.LENGTH_LONG).show();
            dormID = getIntent().getStringExtra("dormID");
            minPrice = getIntent().getIntExtra("minPrice",0);
            maxPrice = getIntent().getIntExtra("maxPrice",0);
            typeWater = getIntent().getStringExtra("typeWater");
            priceWater = getIntent().getIntExtra("priceWater",0);
            typeElectro = getIntent().getStringExtra("typeElectro");
            priceElectro = getIntent().getIntExtra("priceElectro",0);
            typeDeposit = getIntent().getStringExtra("typeDeposit");
            depositPrice = getIntent().getIntExtra("depositPrice",0);
            //System.out.println("SEE DEPOSIT INFORMATION : "+depositPrice);
            typeCommonFee = getIntent().getStringExtra("typeCommonFee");
            commonFee = getIntent().getIntExtra("commonFee",0);
            description = getIntent().getStringExtra("description");
            dormStyle = getIntent().getStringExtra("dormStyle");
            information = getIntent().getStringExtra("information");

            System.out.println("DORM STYLE: "+dormStyle);
            System.out.println("DORM ID: "+dormID);
            System.out.println("MIN PRICE: "+minPrice);
            System.out.println("MAX PRICE: "+maxPrice);
            System.out.println("TYPE WATER: "+typeWater);
            System.out.println("PRICE WATER: "+priceWater);
            System.out.println("TYPE ELECTRO: "+typeElectro);
            System.out.println("PRICE ELECTRO: "+priceElectro);
            System.out.println("TYPE DEPOSIT: "+typeDeposit);
            System.out.println("PRICE DEPOSIT: "+depositPrice);
            System.out.println("TYPE COMMONFEE: "+typeCommonFee);
            System.out.println("PRICE COMMONFEE: "+commonFee);
            System.out.println("Description: "+description);
            System.out.println("INFORMATION: "+information);
        }
        else if(dormStyle.equals(array[2]))
        {
            //Toast.makeText(this,"User is Quality Style",Toast.LENGTH_LONG).show();
            dormID = getIntent().getStringExtra("dormID");
            minPrice = getIntent().getIntExtra("minPrice",0);
            maxPrice = getIntent().getIntExtra("maxPrice",0);
            typeWater = getIntent().getStringExtra("typeWater");
            priceWater = getIntent().getIntExtra("priceWater",0);
            typeElectro = getIntent().getStringExtra("typeElectro");
            priceElectro = getIntent().getIntExtra("priceElectro",0);
            typeDeposit = getIntent().getStringExtra("typeDeposit");
            depositPrice = getIntent().getIntExtra("depositPrice",0);
            //System.out.println("SEE DEPOSIT QUALITY : "+depositPrice);
            typeCommonFee = getIntent().getStringExtra("typeCommonFee");
            commonFee = getIntent().getIntExtra("commonFee",0);
            description = getIntent().getStringExtra("description");
            dormStyle = getIntent().getStringExtra("dormStyle");
            list = getIntent().getParcelableArrayListExtra("premieum");

            System.out.println("DORM STYLE: "+dormStyle);
            System.out.println("DORM ID: "+dormID);
            System.out.println("MIN PRICE: "+minPrice);
            System.out.println("MAX PRICE: "+maxPrice);
            System.out.println("TYPE WATER: "+typeWater);
            System.out.println("PRICE WATER: "+priceWater);
            System.out.println("TYPE ELECTRO: "+typeElectro);
            System.out.println("PRICE ELECTRO: "+priceElectro);
            System.out.println("TYPE DEPOSIT: "+typeDeposit);
            System.out.println("PRICE DEPOSIT: "+depositPrice);
            System.out.println("TYPE COMMONFEE: "+typeCommonFee);
            System.out.println("PRICE COMMONFEE: "+commonFee);
            System.out.println("Description: "+description);
            for(int count=0;count<list.size();count++)
            {
                System.out.println("PREMIEUM: "+list.get(count));
            }

        }
        else if(dormStyle.equals(array[3]))
        {
           // Toast.makeText(this,"User is BrandName Style",Toast.LENGTH_LONG).show();
            dormID = getIntent().getStringExtra("dormID");
            minPrice = getIntent().getIntExtra("minPrice",0);
            maxPrice = getIntent().getIntExtra("maxPrice",0);
            typeWater = getIntent().getStringExtra("typeWater");
            priceWater = getIntent().getIntExtra("priceWater",0);
            typeElectro = getIntent().getStringExtra("typeElectro");
            priceElectro = getIntent().getIntExtra("priceElectro",0);
            typeDeposit = getIntent().getStringExtra("typeDeposit");
            depositPrice = getIntent().getIntExtra("depositPrice",0);
            //System.out.println("SEE DEPOSIT BRANDNAME : "+depositPrice);
            typeCommonFee = getIntent().getStringExtra("typeCommonFee");
            commonFee = getIntent().getIntExtra("commonFee",0);
            description = getIntent().getStringExtra("description");
            dormStyle = getIntent().getStringExtra("dormStyle");
            logo = getIntent().getStringExtra("imageName");

            System.out.println("DORM STYLE: "+dormStyle);
            System.out.println("DORM ID: "+dormID);
            System.out.println("MIN PRICE: "+minPrice);
            System.out.println("MAX PRICE: "+maxPrice);
            System.out.println("TYPE WATER: "+typeWater);
            System.out.println("PRICE WATER: "+priceWater);
            System.out.println("TYPE ELECTRO: "+typeElectro);
            System.out.println("PRICE ELECTRO: "+priceElectro);
            System.out.println("TYPE DEPOSIT: "+typeDeposit);
            System.out.println("PRICE DEPOSIT: "+depositPrice);
            System.out.println("TYPE COMMONFEE: "+typeCommonFee);
            System.out.println("PRICE COMMONFEE: "+commonFee);
            System.out.println("Description: "+description);
        }


    }


    public void uploadCoverImage(View v){
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,0);
    }

    public void uploadImagePanorama(View v){
            Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,1);
    }

    public void addImage(View v){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setType("image/*");
        startActivityForResult(intent,2);
    }

    public void showInfo(View v){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("ข้อมูลแนะนำในการถ่ายภาพ 360 องศา");
        dialog.setMessage("ใช้ Google Street View ในการถ่ายรูปโดยสามารถโหลดจาก Google Play Store");
        //String linkk = "<a href=\"https://play.google.com/store/apps/details?id=com.google.android.street\">Google Street View</a>";
        //String msg = "Link download: "+linkk;
        //Spanned mymsg = Html.fromHtml(msg);
        //dialog.setMessage(mymsg);
        dialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showListImage(View v) {

        System.out.println("INTO SHOWLISTIMAGE FUNCTION");
        Intent i = new Intent(UploadImageActivity.this,GalleryActivity.class);
        i.putExtra("username",username);
        i.putExtra("password",password);
        i.putExtra("email",email);

        this.uploadCoverpicture();
        this.uploadpicture360();
        this.uploadPictures();

        if(dormStyle.equals(array[0]))
        {
           // Toast.makeText(this,"User is Price N Promotion Style",Toast.LENGTH_LONG).show();
            System.out.println("DORM STYLE: "+dormStyle);
            System.out.println("DORM ID: "+dormID);
            System.out.println("MIN PRICE: "+minPrice);
            System.out.println("MAX PRICE: "+maxPrice);
            System.out.println("TYPE WATER: "+typeWater);
            System.out.println("PRICE WATER: "+priceWater);
            System.out.println("TYPE ELECTRO: "+typeElectro);
            System.out.println("PRICE ELECTRO: "+priceElectro);
            System.out.println("TYPE DEPOSIT: "+typeDeposit);
            System.out.println("PRICE DEPOSIT: "+depositPrice);
            System.out.println("TYPE COMMONFEE: "+typeCommonFee);
            System.out.println("PRICE COMMONFEE: "+commonFee);
            System.out.println("Description: "+description);
            System.out.println("PROMOTION DESCRIBE: "+promotionDescribe);
            System.out.println("OLD PRICE: "+oldPrice);
            System.out.println("NEW PRICE: "+newPrice);

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
            i.putParcelableArrayListExtra("imagelist",mArrayUri);

            // specific information for Price and Promotion Dorm
            i.putExtra("promotionDescribe",promotionDescribe);
            i.putExtra("OldPrice",oldPrice);
            i.putExtra("NewPrice",newPrice);
            startActivity(i);

        }
        else if(dormStyle.equals(array[1]))
        {
            //Toast.makeText(this,"User is information and Detail Style",Toast.LENGTH_LONG).show();
            System.out.println("DORM STYLE: "+dormStyle);
            System.out.println("DORM ID: "+dormID);
            System.out.println("MIN PRICE: "+minPrice);
            System.out.println("MAX PRICE: "+maxPrice);
            System.out.println("TYPE WATER: "+typeWater);
            System.out.println("PRICE WATER: "+priceWater);
            System.out.println("TYPE ELECTRO: "+typeElectro);
            System.out.println("PRICE ELECTRO: "+priceElectro);
            System.out.println("TYPE DEPOSIT: "+typeDeposit);
            System.out.println("PRICE DEPOSIT: "+depositPrice);
            System.out.println("TYPE COMMONFEE: "+typeCommonFee);
            System.out.println("PRICE COMMONFEE: "+commonFee);
            System.out.println("Description: "+description);
            System.out.println("INFORMATION: "+information);


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

            i.putParcelableArrayListExtra("imagelist",mArrayUri);

            // specific information for Information Dorm
            i.putExtra("information",information);
            startActivity(i);
        }
        else if(dormStyle.equals(array[2]))
        {
            //Toast.makeText(this,"User is Quality Style",Toast.LENGTH_LONG).show();
            System.out.println("DORM STYLE: "+dormStyle);
            System.out.println("DORM ID: "+dormID);
            System.out.println("MIN PRICE: "+minPrice);
            System.out.println("MAX PRICE: "+maxPrice);
            System.out.println("TYPE WATER: "+typeWater);
            System.out.println("PRICE WATER: "+priceWater);
            System.out.println("TYPE ELECTRO: "+typeElectro);
            System.out.println("PRICE ELECTRO: "+priceElectro);
            System.out.println("TYPE DEPOSIT: "+typeDeposit);
            System.out.println("PRICE DEPOSIT: "+depositPrice);
            System.out.println("TYPE COMMONFEE: "+typeCommonFee);
            System.out.println("PRICE COMMONFEE: "+commonFee);
            System.out.println("Description: "+description);
            for(int count=0;count<list.size();count++)
            {
                System.out.println("PREMIEUM: "+list.get(count));
            }


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
            i.putParcelableArrayListExtra("imagelist",mArrayUri);

            // specific information for Quality Dorm
            i.putParcelableArrayListExtra("premieum",list);
            startActivity(i);
        }
        else if(dormStyle.equals(array[3]))
        {
            //Toast.makeText(this,"User is BrandName Style",Toast.LENGTH_LONG).show();
            System.out.println("DORM STYLE: "+dormStyle);
            System.out.println("DORM ID: "+dormID);
            System.out.println("MIN PRICE: "+minPrice);
            System.out.println("MAX PRICE: "+maxPrice);
            System.out.println("TYPE WATER: "+typeWater);
            System.out.println("PRICE WATER: "+priceWater);
            System.out.println("TYPE ELECTRO: "+typeElectro);
            System.out.println("PRICE ELECTRO: "+priceElectro);
            System.out.println("TYPE DEPOSIT: "+typeDeposit);
            System.out.println("PRICE DEPOSIT: "+depositPrice);
            System.out.println("TYPE COMMONFEE: "+typeCommonFee);
            System.out.println("PRICE COMMONFEE: "+commonFee);
            System.out.println("Description: "+description);


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
            i.putParcelableArrayListExtra("imagelist",mArrayUri);
            // specific information for BrandName Dorm
            //i.putExtra("imageName",logo);
            startActivity(i);


        }



        //GalleryAdapter adapter = new GalleryAdapter(this,mArrayUri);
        //this.grid.setAdapter(adapter);
        //this.grid.setHorizontalSpacing(this.grid.getHorizontalSpacing());
        //ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) this.grid.getLayoutParams();
        //mlp.setMargins(5, this.grid.getHorizontalSpacing(), 5, 0);


    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    // clear
    public void uploadCoverpicture(){
        String uploadPathCoverImage = "http://"+ip+"/CoverImage/uploadCoverImage";
        VolleyMultipartRequest request = new VolleyMultipartRequest(Request.Method.POST, uploadPathCoverImage, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("email",email);
                param.put("username",username);

                return param;
            }

            @Override
            protected Map<String, DataPart> getByteData() throws AuthFailureError {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                //name_doc = String.valueOf(imagename+".png");
                params.put("file", new DataPart("CoverImage_"+imagename + ".png", getFileDataFromDrawable(composite_coverimage)));
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }


    public void uploadpicture360(){
        String uploadImage360 = "http://"+ip+"/Image360/uploadImage360";
        VolleyMultipartRequest request = new VolleyMultipartRequest(Request.Method.POST, uploadImage360, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("email",email);
                param.put("username",username);
                return param;
            }

            @Override
            protected Map<String, DataPart> getByteData() throws AuthFailureError {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("file",new DataPart("Image360_"+imagename+".jpg",getFileDataFromDrawable(composite_picture360)));
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void uploadPictures(){
        String uploadPicturesPath = "http://"+ip+"/GalleryImage/uploadGalleryImage";

        VolleyMultipartRequest_For_Gallery request = new VolleyMultipartRequest_For_Gallery(Request.Method.POST,uploadPicturesPath, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                params.put("username",username);
                return params;
            }

            @Override
            protected Map<String,ArrayList<DataPart>> getByteData() throws AuthFailureError {
                Map<String, DataPart> params = new HashMap<>();
                Map<String,ArrayList<DataPart>> map = new HashMap<>();
                ArrayList<DataPart> list = new ArrayList<>();
                for(int count=0;count<gallist.size();count++)
                {
                    VolleyMultipartRequest_For_Gallery.DataPart dp = new VolleyMultipartRequest_For_Gallery.DataPart("Gallery_"+count+"_"+username+".png",getFileDataFromDrawable(gallist.get(count)));
                    list.add(dp);
                }
                map.put("files",list);
                return map;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode== 0 && resultCode == Activity.RESULT_OK && data!=null) {
            try
            {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imageString = cursor.getString(columnIndex);

                composite_coverimage = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
                //coverImageText = this.BitMapToString(composite_coverimage);
                cursor.close();

                Bitmap bmpPic = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
                ImageButton img = (ImageButton) findViewById(R.id.CoverImage);
                img.setImageBitmap(bmpPic);

            }
            catch (Exception e) {
                Log.e("Log", "Error from Gallery Activity");
            }
        }
        else if(requestCode == 1 && resultCode == Activity.RESULT_OK && data!=null){
            try
            {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();

                composite_picture360 = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
                //image360Text = this.BitMapToString(composite_picture360);



                Bitmap bmpPic = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
                //imagePano.setImageBitmap(bmpPic);
                Glide.with(getApplicationContext()).load(bmpPic).into(imagePano);
            /*
                composite_picture360 = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
                int w = composite_picture360.getWidth();//get width
                int h = composite_picture360.getHeight();//get height
                int aspRat = w / h;//get aspect ratio
                int W = 1000;
                int H = w * aspRat;
                //image360Text = this.BitMapToString(composite_picture360);

                b = Bitmap.createScaledBitmap(composite_picture360, W, H, false);//scale the bitmap

                //Bitmap bmpPic = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
                imagePano.setImageBitmap(b);
                composite_picture360 = null;
            */
            } catch (Exception e) {
                Log.e("Log", "Error from Gallery Activity");
            }
        }
        else if(requestCode == 2 && resultCode == Activity.RESULT_OK && data !=null)
        {
            if(data.getClipData()!=null)
            {
               // System.out.println("Hello World");
                ClipData clipdata = data.getClipData();
                System.out.println("CLIP DATA: "+clipdata.getItemCount());
                 //mArrayUri = new ArrayList<Uri>();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                for(int count=0;count<clipdata.getItemCount();count++)
                {
                    try {
                        ClipData.Item item = clipdata.getItemAt(count);
                        Uri uri = item.getUri();
                       // Bitmap bmPic = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        Bitmap gallery = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                        //galleryText = this.BitMapToString(gallery);
                        mArrayUri.add(uri);
                        gallist.add(gallery);
                        Cursor cursor = getContentResolver().query(uri,filePathColumn,null,null,null);
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String imageEncode = cursor.getColumnName(columnIndex);

                        cursor.close();
                    }
                    catch(FileNotFoundException ex)
                    {
                        ex.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }

            }
            else if(data.getData()!=null)
                {
                    try
                    {
                        Uri imagedata = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(imagedata, filePathColumn, null, null, null);
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String imgDecodableString = cursor.getString(columnIndex);

                        Bitmap gallerybitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagedata);
                        gallist.add(gallerybitmap);
                        //galleryText = this.BitMapToString(gallerybitmap);
                        mArrayUri.add(imagedata);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }


                      /*
                        File f = new File(imgDecodableString);
                        imagelist.add(f.getAbsolutePath());
                       */



                       // Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagedata);

                }
            /*
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(final Bitmap maps : list)
                        {
                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   System.out.println(maps);
                                   //test.setImageBitmap(maps);
                               }
                           });


                           try
                           {
                               Thread.sleep(3000);

                           }
                           catch(InterruptedException interrupt)
                           {
                                interrupt.printStackTrace();
                           }


                        }
                    }
                }).start();
                */

            this.showimage.setVisibility(View.VISIBLE);




        }

    }//end onActivityResult






}
