package com.vickysg.myshayariapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        ImageView imageView = findViewById(R.id.logo);
        TextView appname = findViewById(R.id.appname);

        int splashTimeOut = 3000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        },splashTimeOut);

//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splashscreenanimation);

        Animation topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        Animation bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        imageView.startAnimation(topAnimation);
        appname.startAnimation(bottomAnimation);


        View decorview = getWindow().getDecorView();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            decorview.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            |View.SYSTEM_UI_FLAG_FULLSCREEN
                            |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }
}