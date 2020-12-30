package com.example.testrestapi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightGridView;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpdateGallery extends AppCompatActivity {

    public Button updateimage;
    public Button confirmEdit;
    public ActionBar bar;
    public SharedPreferences sharedPreferences;
    public String email;
    public String username;
    public String password;
    public GridView gridview;
    public String ip = "192.168.43.57:8080";
    public ArrayList<String> pathlist;
    public ArrayList<Bitmap> imagelist;
    public boolean pressupdate = false;
    GalleryUpdate adapter;
    public ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_gallery);


        this.pathlist = new ArrayList<>();
        this.imagelist = new ArrayList<>();

        this.bar = getSupportActionBar();
        this.bar.setTitle("รูปภาพเพิ่มเติม");
        this.bar.setDisplayHomeAsUpEnabled(true);
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));

        sharedPreferences = getSharedPreferences("file_for_dormowner",MODE_PRIVATE);
        this.username = sharedPreferences.getString("username","no var");
        this.password = sharedPreferences.getString("password","no var");
        this.email = sharedPreferences.getString("email","no email");

        this.gridview = (GridView) findViewById(R.id.gridview);
        updateimage = (Button) findViewById(R.id.updateimages);
        confirmEdit = (Button) findViewById(R.id.confirmimage);
        confirmEdit.setVisibility(View.GONE);

        this.setGallery(email,pressupdate);
    }
    // แสดง images ที่ผู้ใช้เคยกดอัปโหลด
    public void setGallery(String email,boolean pressupdate){
        String getgallerypath = "http://"+ip+"/GalleryImage/getGalleryImage/"+email;
        RequestQueue queue = Volley.newRequestQueue(this);
         JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getgallerypath, null, new Response.Listener<JSONObject>() {
             @Override
             public void onResponse(JSONObject response) {
                 JSONObject data = response;
                 try {
                     JSONArray images = data.getJSONArray("images");
                     for(int count=0;count<images.length();count++)
                     {
                         String path = images.getString(count);
                         pathlist.add(path);
                     }

                     adapter = new GalleryUpdate(getApplicationContext(),pathlist,pressupdate);
                     gridview.setAdapter(adapter);

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
         queue.add(request);
    }

    // see image in gallery
    public void updateImage(View v){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    // user can press to confirm image
    public void confirmEdit(View v){
            updateGallery(imagelist);
            pressupdate = true;
            //adapter = new GalleryUpdate(getApplicationContext(),imagelist,pressupdate,"");
            //gridview.setAdapter(adapter);
            progress = new ProgressDialog(this);
            String title = getResources().getString(R.string.updateing);
            String message = getResources().getString(R.string.messageUpdate);
            progress.setTitle(title);
            progress.setMessage(message);
            progress.show();
            updateimage.setVisibility(View.VISIBLE);
            confirmEdit.setVisibility(View.GONE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == Activity.RESULT_OK && data!=null)
        {
            if(data.getClipData()!=null)
            {
                confirmEdit.setVisibility(View.VISIBLE);
              try {
                  ClipData clipdata = data.getClipData();
                  for(int count=0;count<clipdata.getItemCount();count++)
                  {
                      ClipData.Item item = clipdata.getItemAt(count);
                      Uri imageuri = item.getUri();
                      Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                      imagelist.add(bitmap);
                  }
                  pressupdate = true;
                  adapter = new GalleryUpdate(getApplicationContext(),imagelist,pressupdate,"");
                  gridview.setAdapter(adapter);
                    updateimage.setVisibility(View.GONE);
              }
              catch (IOException ex){
                  ex.printStackTrace();
              }

            }
            else if(data.getData() != null)
            {
                confirmEdit.setVisibility(View.VISIBLE);
                try
                {
                    Uri imageuri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                    imagelist.add(bitmap);
                    adapter = new GalleryUpdate(getApplicationContext(),imagelist,pressupdate,"");
                    gridview.setAdapter(adapter);
                    updateimage.setVisibility(View.GONE);
                   // updateGallery(imagelist);

                }
                catch (IOException ex){
                    ex.printStackTrace();
                }

            }
        }
    }



    public void updateGallery(ArrayList<Bitmap> imagelist){
        String updatepath = "http://"+ip+"/GalleryImage/updateGallery";
        VolleyMultipartRequest_For_Gallery request = new VolleyMultipartRequest_For_Gallery(Request.Method.POST,
                updatepath, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(getApplicationContext(),"แก้ไขสำเร็จ",Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("email",email);
                map.put("username",username);
                return map;
            }

            @Override
            protected Map<String, ArrayList<DataPart>> getByteData() throws AuthFailureError {
                HashMap<String,ArrayList<DataPart>> map = new HashMap<>();
                ArrayList<DataPart> list = new ArrayList<>();
                for(int count=0;count<imagelist.size();count++)
                {
                    VolleyMultipartRequest_For_Gallery.DataPart datapart = new VolleyMultipartRequest_For_Gallery
                            .DataPart("Gallery_"+count+"_"+username+".png",getFileDataFromDrawable(imagelist.get(count)));
                    list.add(datapart);
                }
                map.put("files",list);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

}
