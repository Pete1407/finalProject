package com.example.testrestapi;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Type_BrandName_Dorm_Fragment extends Fragment {

   public Button button;
   public TextView title;
   public ImageButton uploadlogo;

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
    public String logo;

    public String username;
    public String email;

    public Bitmap composite_logo;

    public String ip = "192.168.43.57:8080";
    public String url = "http://"+ip;


    public Type_BrandName_Dorm_Fragment newInstance(){
        return new Type_BrandName_Dorm_Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.type_brandname_dorm_fragment,container,false);
        title = (TextView) v.findViewById(R.id.title);
        uploadlogo = (ImageButton) v.findViewById(R.id.uploadlogo);
        button = (Button) v.findViewById(R.id.button7);


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
        //Toast.makeText(getContext(),"Email: "+email+"\n"+"Username: "+username,Toast.LENGTH_LONG).show();

        uploadlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,0);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadLogoBrand();


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
                i.putExtra("imageName",logo);
                i.putExtra("username",username);
                i.putExtra("email",email);
                startActivity(i);
            }
        });

        return v;
    }

    public void chooseLogo(View v){
        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == Activity.RESULT_OK && data!=null)
        {
            try {
                Uri uri = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(uri,filePathColumn,null,null,null);
                cursor.moveToFirst();
                int index = cursor.getColumnIndex(filePathColumn[0]);
                String fileImage = cursor.getString(index);
                cursor.close();



                Bitmap image = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
                logo = this.BitMapToString(image); // string send to UploadImageActivity
                composite_logo = image;
                uploadlogo.setImageBitmap(image);
            }
            catch(FileNotFoundException ex)
            {
                ex.printStackTrace();
            }

        }

    }

    public void uploadLogoBrand(){
        String path = url +"/LogoBrandName/uploadLogoBrandName";

        VolleyMultipartRequest request = new VolleyMultipartRequest(Request.Method.POST, path, new Response.Listener<NetworkResponse>() {
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
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                //name_doc = String.valueOf(imagename+".png");
                params.put("file", new DataPart("LogoBrand_"+imagename + ".png", getFileDataFromDrawable(composite_logo)));
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}
