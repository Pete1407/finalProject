package com.example.testrestapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.testrestapi.Fragment.FilterFragment;
import com.example.testrestapi.Fragment.ListFragment;
import com.example.testrestapi.Fragment.SearchMap_Fragment;

public class MyAdapter extends FragmentPagerAdapter {
    public int[] title = new int[]{R.string.filter_title,R.string.list_title,R.string.map_title};

    public Context c;
    public int sizeTab;
    public String faculty;
    public String dormStyle;
    public int codeStyle;
    public Bundle b;

    public SharedPreferences sharedPreferences;

    public MyAdapter(Context c, FragmentManager fragmentManager,int numTab,String f,String dormS,int codeS){
        super(fragmentManager);
        this.c = c;
        this.sizeTab = numTab;
        this.faculty = f;
        //this.dormStyle = dormS;
        //this.codeStyle = codeS;
        System.out.println("-------------------- " +codeStyle );
        sharedPreferences = c.getSharedPreferences("file_pref",Context.MODE_PRIVATE);
        this.dormStyle = sharedPreferences.getString("dormStyle","no var");
        this.codeStyle = sharedPreferences.getInt("dormStyleCode",6);
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return c.getResources().getString(this.title[position]);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            FilterFragment fgr = new FilterFragment();
            Bundle b = new Bundle();
            b.putString("faculty", faculty);
            b.putString("dormStyle", dormStyle);
            b.putInt("codeStyle", codeStyle);
            fgr.setArguments(b);
            return fgr;
        } else if (position == 1) {
            ListFragment fgr = new ListFragment();
            Bundle b = new Bundle();
            b.putString("faculty", faculty);
            b.putString("dormStyle", dormStyle);
            b.putInt("codeStyle", codeStyle);
            fgr.setArguments(b);
            return fgr;
        } else if (position == 2) {
            SearchMap_Fragment fgr = new SearchMap_Fragment();
            Bundle b = new Bundle();
            b.putString("faculty", faculty);
            b.putString("dormStyle", dormStyle);
            b.putInt("codeStyle", codeStyle);
            fgr.setArguments(b);
            return fgr;
        }

       return null;
    }

    @Override
    public int getCount() {
        return this.sizeTab;
    }


}
