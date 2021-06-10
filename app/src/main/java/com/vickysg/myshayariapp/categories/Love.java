package com.vickysg.myshayariapp.categories;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vickysg.myshayariapp.Model;
import com.vickysg.myshayariapp.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class Love extends AppCompatActivity implements View.OnClickListener {

    private AdView adView;
    TextView count_txt;
    EditText quotes_txt;
    CardView back_btn, copy_btn, share_btn, next_btn;

    List<String> quotes_list;
    DatabaseReference databaseReference;
    Model myShayari;
    int position =0;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Love");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AdRequest adRequest1 = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3912259549278001/1985825233", adRequest1, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;

            }

        });

        adView= (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        count_txt = findViewById(R.id.countTEXT);
        quotes_txt = findViewById(R.id.quotesTEXT);
        back_btn = findViewById(R.id.backBtn);
        copy_btn = findViewById(R.id.copyBtn);
        share_btn = findViewById(R.id.shareBtn);
        next_btn = findViewById(R.id.nextBtn);

        back_btn.setOnClickListener(this);
        copy_btn.setOnClickListener(this);
        share_btn.setOnClickListener(this);
        next_btn.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("love");
        myShayari = new Model();
        quotes_list = new ArrayList<>();

        // Event Value Methodes

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    myShayari = dataSnapshot1.getValue(Model.class);
                    if(myShayari != null) {
                        quotes_list.add(0,myShayari.getTitle().replace("\\n", "\n"));
                    }
                }

                quotes_txt.setText(quotes_list.get(position));
                count_txt.setText((position+1)+"/" + quotes_list.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

            }
        });




    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backBtn:
                back();
                break;

            case R.id.copyBtn:
                copy();
                break;

            case R.id.shareBtn:
                share();
                break;

            case R.id.nextBtn:
                next();
                break;

        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }



    private void back(){
        if (position>0){
            position = (position -1) % quotes_list.size();
            quotes_txt.setText(quotes_list.get(position));
            count_txt.setText((position+1) + "/" + quotes_list.size());
        }
    }

    private void next(){
        if (mInterstitialAd != null){
            mInterstitialAd.show(Love.this);
            position = (position +1) % quotes_list.size();
            quotes_txt.setText(quotes_list.get(position));
            count_txt.setText( (position+1) + "/" + quotes_list.size());
        }else{
            position = (position +1) % quotes_list.size();
            quotes_txt.setText(quotes_list.get(position));
            count_txt.setText( (position+1) + "/" + quotes_list.size());
        }



    }

    private void copy(){

        if (mInterstitialAd != null){
            mInterstitialAd.show(Love.this);
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("text", quotes_txt.getText());

            if (clipboardManager != null){
                clipboardManager.setPrimaryClip(clipData);
            }
            Toast.makeText(getApplicationContext(), "copied", Toast.LENGTH_SHORT).show();

        }else{
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("text", quotes_txt.getText());

            if (clipboardManager != null){
                clipboardManager.setPrimaryClip(clipData);
            }
            Toast.makeText(getApplicationContext(), "copied", Toast.LENGTH_SHORT).show();

        }


    }

    private void share(){
        if (mInterstitialAd != null){
            mInterstitialAd.show(Love.this);
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,quotes_txt.getText());
            startActivity(Intent.createChooser(intent,"share via"));
        }else {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, quotes_txt.getText());
            startActivity(Intent.createChooser(intent, "share via"));
        }
    }
}
