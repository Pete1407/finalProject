package com.example.testrestapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.*;
import android.widget.Toast;

import com.example.testrestapi.Fragment.Favorite_Fragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;


public class MainPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public ActionBarDrawerToggle toggle;
    public DrawerLayout drawer;
    public Toolbar toolbar;
    public ViewPager pager;
    public TabLayout tab;
    public NavigationView nav;
    public MyAdapter adapter;

    public String faculty;  // get value from LogIn Class
    public String likedorm;  // get value from LogIn Class
    public int codeStyle;

    public SharedPreferences sharedPreferences;

    //public MaterialSearchView searchView;
    public String s;
    public int cs;
    public String f;

    public SharedPreferences changelanguage;
    public SharedPreferences.Editor editor;

    //public int[] logo = new int[]{R.drawable.filter_icon,R.drawable.list_logo,R.drawable.map_logo};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        int id_title = R.string.mainPage;
        String title = getResources().getString(id_title);

        sharedPreferences = getSharedPreferences("file_pref",MODE_PRIVATE);
        String faculty = sharedPreferences.getString("faculty","default faculty");
        String dormStyleText = sharedPreferences.getString("dormStyle","default dormStyle");
        int codeDormStyle = sharedPreferences.getInt("dormStyleCode",0);
        //Toast.makeText(this,faculty+"\n"+dormStyleText+"\n"+codeDormStyle,Toast.LENGTH_SHORT).show();


        SharedPreferences selectDorm = getSharedPreferences("selectDorm",MODE_PRIVATE);
        SharedPreferences.Editor selectdormeditor = selectDorm.edit();
        selectdormeditor.putString("dormname","initial value");
        selectdormeditor.putString("pathImage360","initial value");
        selectdormeditor.putInt("numberofFavorite",0);
        selectdormeditor.commit();

        f = faculty;
        s = dormStyleText;
        cs = codeDormStyle;


        this.faculty = getIntent().getStringExtra("faculty");    // get value from LogIn Class
        this.likedorm = getIntent().getStringExtra("dormStyle");  // get value from LogIn Class
        System.out.println("----------------- ::::: "+getIntent().getIntExtra("codeStyle",0));
        this.codeStyle = getIntent().getIntExtra("codeStyle",0);

        this.drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.nav = (NavigationView) findViewById(R.id.navigationView);
        //this.searchView = (MaterialSearchView) findViewById(R.id.searchview);

        //this.frame = (FrameLayout) findViewById(R.id.frameLayout);
        this.toolbar.setTitle(title);
        this.toolbar.setBackground(new ColorDrawable(Color.parseColor("#DC143C")));
        setSupportActionBar(toolbar);


        this.tab = (TabLayout) findViewById(R.id.tabLayout);
        String filter = getResources().getString(R.string.filter_title);
        String list = getResources().getString(R.string.list_title);
        String map = getResources().getString(R.string.map_title);
        this.tab.addTab(tab.newTab().setText(filter));
        this.tab.addTab(tab.newTab().setText(list));
        this.tab.addTab(tab.newTab().setText(map));



        this.pager = (ViewPager) findViewById(R.id.viewPager);

        if(codeDormStyle == 3)
        {
            this.toolbar.setBackground(new ColorDrawable(Color.parseColor("#000000")));
        }
        this.adapter = new MyAdapter(this,getSupportFragmentManager(),this.tab.getTabCount(),faculty,likedorm,codeStyle);

        //this.tab.setupWithViewPager(pager);

        this.tab.getTabAt(0).setIcon(R.drawable.filter_icon);
        this.tab.getTabAt(1).setIcon(R.drawable.list_logo);
        this.tab.getTabAt(2).setIcon(R.drawable.map_logo);

        this.pager.setAdapter(adapter);



        this.pager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this.tab));
        this.tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        this.pager.setCurrentItem(1);

        this.toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_close,R.string.navigation_open);

        this.toggle.syncState();

        this.nav.setNavigationItemSelectedListener(this);
        //setFragment(new ListFragment());




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.searchview)
        {
            System.out.println("Search Page");
            Intent search = new Intent(MainPage.this, Search_Activity.class);
            search.putExtra("dormStyleCode",cs);
            search.putExtra("faculty",f);
            startActivity(search);

           // Intent search = new Intent(MainPage.this, SmartSearch.class);
           // search.putExtra("dormStyleCode",cs);
           // search.putExtra("faculty",f);
           // startActivity(search);
        }
        else if(id == R.id.thai_Language)
        {
            System.out.println("Thai Language");
            // ใช้ class Locale ในการ set ภาษาไทย
            Configuration config = new Configuration();
            config.locale = new Locale("th");
            getResources().updateConfiguration(config,null);

            changelanguage = getSharedPreferences("language",MODE_PRIVATE);
            editor = changelanguage.edit();
            editor.putString("language","TH");
            editor.putInt("languageCode",0);
            editor.commit();

            Intent intent = new Intent(MainPage.this,MainPage.class);
            finish();
            startActivity(intent);
        }
        else if(id == R.id.en_language)
        {
            System.out.println("English Language");
            Configuration config = new Configuration();
            config.locale = Locale.ENGLISH;
            getResources().updateConfiguration(config,null);

            changelanguage = getSharedPreferences("language",MODE_PRIVATE);
            editor = changelanguage.edit();
            editor.putString("language","EN");
            editor.putInt("languageCode",1);
            editor.commit();

            Intent intent = new Intent(MainPage.this,MainPage.class);
            finish();
            startActivity(intent);

        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(id == R.id.favoritee)
        {
            Intent intent = new Intent(MainPage.this,FavoriteActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.config_user)
        {
            Intent in = new Intent(MainPage.this,updateTypeDorm.class);
            startActivity(in);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
