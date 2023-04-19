package com.example.waste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    ImageView imageView,imageCloud;
    TextView appName,slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        imageView = findViewById(R.id.image);
        imageCloud = findViewById(R.id.imageCloud);

        appName = findViewById(R.id.appName);
        slogan = findViewById(R.id.slogan);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_three);
        imageView.setAnimation(animation);
        imageCloud.setAnimation(animation);

        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.animation_two);
        appName.setAnimation(animation2);
        slogan.setAnimation(animation2);




        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(splash.this, MainActivity.class));
                finish();
            }
        }, secondsDelayed * 5000);
    }
}