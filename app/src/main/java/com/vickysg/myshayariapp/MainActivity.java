package com.vickysg.myshayariapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AdView adView;

    private AppBarConfiguration mAppBarConfiguration;


    private MyAdapter mAdapter;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

//        FloatingActionButton fab = findViewById(R.id.fab);



        // RecyclerView
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
// Set its Properties
//grid view with 2 columns in each row
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
// Adapter
        mAdapter = new MyAdapter(this, getModels());
        mRecyclerView.setAdapter(mAdapter);









//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private ArrayList<Model> getModels() {
        ArrayList<Model> models = new ArrayList<>();
        Model p;

        // Create an items for recycler view below
        p = new Model();
        p.setTitle("Good Morning");
        p.setImage(R.drawable.morning);
        models.add(p);

        p = new Model();
        p.setTitle("Good Night");
        p.setImage(R.drawable.night);
        models.add(p);

        p = new Model();
        p.setTitle("Love");
        p.setImage(R.drawable.love);
        models.add(p);

        p = new Model();
        p.setTitle("Romantics");
        p.setImage(R.drawable.romantic);
        models.add(p);

        p = new Model();
        p.setTitle("Couple");
        p.setImage(R.drawable.couple);
        models.add(p);

        p = new Model();
        p.setTitle("Valentine Day");
        p.setImage(R.drawable.valentine);
        models.add(p);

        p = new Model();
        p.setTitle("Friendship");
        p.setImage(R.drawable.friendship);
        models.add(p);

        p = new Model();
        p.setTitle("Birthday");
        p.setImage(R.drawable.birthday);
        models.add(p);

        p = new Model();
        p.setTitle("Funny");
        p.setImage(R.drawable.couple);
        models.add(p);

        p = new Model();
        p.setTitle("Best Wishes");
        p.setImage(R.drawable.bestwishes);
        models.add(p);

        p = new Model();
        p.setTitle("G O D");
        p.setImage(R.drawable.bhagwan);
        models.add(p);

        p = new Model();
        p.setTitle("Attitude");
        p.setImage(R.drawable.loveicon);
        models.add(p);

        return models;
    }

    private void layoutAnimation(RecyclerView recyclerView) {
        Context context = recyclerView.getContext();
        LayoutAnimationController layoutAnimationController =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_right);

        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//       // getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXIT");
        builder.setMessage("Are You Sure Want to Exit?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                }).show();
    }
}