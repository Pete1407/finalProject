package com.example.testrestapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.transition.Transition;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FullScreen_Image360 extends AppCompatActivity {
    public VrPanoramaView panoview;
    public ImageView image;
    public String pathImage360 = "";
    public ActionBar bar;

    public SharedPreferences sharedPreferences;
    public String dormStyle = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen__image);
        sharedPreferences = getSharedPreferences("file_pref",MODE_PRIVATE);
        dormStyle = sharedPreferences.getString("dormStyle","no value");
        this.bar = getSupportActionBar();
        String[] array = getResources().getStringArray(R.array.dormtype);
        if(dormStyle.equals(array[0]) || dormStyle.equals(array[1]) || dormStyle.equals(array[2]))
        {
            this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));
        }
        else if(dormStyle.equals(array[3]))
        {
            this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        }

        String panoram = getResources().getString(R.string.panorama);
        this.bar.setTitle(panoram);

        this.bar.setDisplayHomeAsUpEnabled(true);

        this.panoview = (VrPanoramaView) findViewById(R.id.image360);
        pathImage360 = getIntent().getStringExtra("imagePath");
        System.out.println("IMAGE 360 :: "+pathImage360);
        final VrPanoramaView.Options options = new VrPanoramaView.Options();
        ImageRequest imageRequest = new ImageRequest(pathImage360, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                options.inputType = VrPanoramaView.Options.TYPE_MONO;
                panoview.loadImageFromBitmap(response,options);
            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue q = Volley.newRequestQueue(this);
        q.add(imageRequest);






    }

    @Override
    protected void onResume() {
        super.onResume();
        panoview.resumeRendering();
    }

    @Override
    protected void onPause() {
        super.onPause();
        panoview.pauseRendering();
    }

    @Override
    protected void onDestroy() {
        panoview.shutdown();
        super.onDestroy();

    }
}
