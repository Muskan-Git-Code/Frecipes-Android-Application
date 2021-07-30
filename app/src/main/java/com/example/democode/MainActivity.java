package com.example.democode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        LottieAnimationView lv= findViewById(R.id.animationView);
        Animation a1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        lv.startAnimation(a1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);

                finish();
            }
        },7000);

    }
}