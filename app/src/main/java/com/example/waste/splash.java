package com.example.waste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
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

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(splash.this, R.color.amazing_yellow));

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
        }, secondsDelayed * 3000);
    }
}