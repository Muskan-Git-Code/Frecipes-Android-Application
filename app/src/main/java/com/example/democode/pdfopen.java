package com.example.democode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class pdfopen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfopen);

        Intent i=getIntent();
        String n=i.getStringExtra("story");

        PDFView pd1= (PDFView)findViewById(R.id.pdfView1);
        pd1.fromAsset(n).load();
    }
}