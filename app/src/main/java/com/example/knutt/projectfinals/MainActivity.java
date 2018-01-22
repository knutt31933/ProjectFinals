package com.example.knutt.projectfinals;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private LinearLayout clk1,clk2,clk3,clk4;
    private CallbackManager callbackManager;
    private Button buttonLoginfb;

    //TextView textMatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //textMatter=(TextView)findViewById(R.id.textMatter);

        clk1=(LinearLayout)findViewById(R.id.click1);
        clk2=(LinearLayout)findViewById(R.id.click2);
        clk3=(LinearLayout)findViewById(R.id.click3);
        clk4=(LinearLayout)findViewById(R.id.click4);
        Initializing();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_nine) {
            textMatter.setText("99");
            return true;
        }if (id == R.id.action_two) {
            textMatter.setText("22");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Initializing(){
        Fragment fragment = null;
        Class fragmentClass =  Home.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }// Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
    }

    public void ClickNavigation(View view){
        Fragment fragment = null;
        Class fragmentClass =  Home.class;

        switch (view.getId()){
            case R.id.click1:

                fragmentClass = FragmentA.class;
                clk1.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_clickb, null));
                clk2.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk3.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk4.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                break;
            case R.id.click2:
                fragmentClass = FragmentB.class;
                clk1.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk2.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_clickb, null));
                clk3.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk4.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                break;
            case R.id.click3:
                fragmentClass = FragmentC.class;
                clk1.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk2.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk3.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_clickb, null));
                clk4.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                break;
            case R.id.click4:
                fragmentClass = FragmentD.class;
                clk1.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk2.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk3.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk4.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_clickb, null));
                break;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }// Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
