package com.example.democode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;

import org.aviran.cookiebar2.CookieBar;

import java.util.Random;

public class PinActivity extends AppCompatActivity {

    PinView pv1;
    Button bt1;

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sp.getBoolean("Muskan", false)) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("Muskan", Boolean.TRUE).apply();
        }
        else {
            Intent i= new Intent(PinActivity.this, HomeActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        Random rand = new Random();
        Integer ix1= rand.nextInt(9);    //choosing a random number from 1 to 9
        Integer ix2= rand.nextInt(9);
        Integer ix3= rand.nextInt(9);
        Integer ix4= rand.nextInt(9);

        CookieBar.build(this)
                .setTitle("OTP")
                .setMessage(ix1+ " "+ ix2+" "+ ix3+" "+ ix4)
                .setIcon(R.drawable.ic_baseline_pending_24)
                .setCookiePosition(CookieBar.TOP)
                .setDuration(20000)
                .show();


        pv1= (PinView)findViewById(R.id.pin_view);
        bt1= (Button)findViewById(R.id.button5);

        ImageView iv= findViewById(R.id.imageView2);
        TextView tv= findViewById(R.id.textView2);
        TextView tv3= findViewById(R.id.textView);
        Animation a2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        iv.startAnimation(a2);
        tv.startAnimation(a2);

        Animation a12 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        tv3.startAnimation(a12);

        Animation a1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        bt1.startAnimation(a1);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation a1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                bt1.startAnimation(a1);

                pv1.clearComposingText();

                Toast.makeText(PinActivity.this, "Welcome !!", Toast.LENGTH_SHORT).show();
                Intent i= new Intent(PinActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }
}