package com.example.democode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nv = findViewById(R.id.bottom_navigation);
        nv.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        Fragment f = null;
        switch (item.getItemId()) {
            case R.id.nf1: f= new f1(); break;
            case R.id.nf2: f= new f2(); break;
            case R.id.nf3: f= new f3(); break;
            case R.id.nf4: f= new f4(); break;
            case R.id.nf5: f= new f5(); break;
        }
        return loadFragment(f);
    }

    private boolean loadFragment(Fragment f) {
        if (f != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}