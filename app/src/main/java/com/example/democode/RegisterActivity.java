package com.example.democode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    EditText ed2, ed6;
    TextView ed3;
    Button bt1;
    Spinner sp;
    DatePickerDialog dp;
    String str=""; int strOldlen=0;
    ImageView iv1, iv2;
    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean("Muskan", false) ) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean("Muskan", Boolean.TRUE).apply();
        }
        else {
            SharedPreferences sp = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
            if (!sp.getBoolean("Muskan1", false) ) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("Muskan1", Boolean.TRUE).apply();
            }
            else {
                Intent i= new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(i);
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        iv1= (ImageView)findViewById(R.id.imageView3);

        Animation a2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        iv1.startAnimation(a2);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        ed2= (EditText)findViewById(R.id.editText2);
        ed3= (TextView) findViewById(R.id.editText3);
        ed6= (EditText)findViewById(R.id.editText6);
        bt1= (Button)findViewById(R.id.button4);


        Animation a1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        bt1.startAnimation(a1);

        ed6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                str = ed6.getText().toString();
                int strLen = str.length();

                if(strOldlen<strLen) {
                    if (strLen > 0) {
                        if (strLen == 5) {
                            str=str+" ";
                            ed6.setText(str);
                            ed6.setSelection(ed6.getText().length());
                        }
                        else{
                            if(strLen==6){
                                if(!str.contains(" ")){
                                    String tempStr=str.substring(0,strLen-1);
                                    tempStr +=" "+str.substring(strLen-1,strLen);
                                    ed6.setText(tempStr);
                                    ed6.setSelection(ed6.getText().length());
                                }
                            }
                            strOldlen = strLen;
                        }
                    }
                    else    return;
                }
                else   strOldlen = strLen;
            }
        });


        setDateTimeField();
        ed3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dp.show();
                return false;
            }
        });


        String s[]= {"Select your Gender","Male", "Female", "Others"};
        sp= (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> aa= new ArrayAdapter(this, android.R.layout.simple_spinner_item, s){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)   return false;
                else    return true;
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextColor(Color.parseColor("#7F8C8D"));
                return v;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0)   tv.setTextColor(Color.GRAY);
                else    tv.setTextColor(Color.BLACK);
                return view;
            }
        };

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setPrompt("");
        sp.setAdapter(aa);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation a1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                bt1.startAnimation(a1);

                int flag=0;
                if(ed6.getText().toString().isEmpty() || ed6.length()!=11){ ed6.setError("Enter Valid Phone Number of 10 digits!"); flag=1;}
                if(ed2.getText().toString().isEmpty()){ ed2.setError("Enter Valid Name!"); flag=1;}
                if(ed3.getText().toString().isEmpty()){ ed3.setError("Enter Valid Data!"); flag=1;}
                if(sp.getId()==0 || sp.equals("Select your Gender")){ ((TextView)sp.getSelectedView()).setError("Enter Valid Data!"); flag=1;}

                if(flag==0){
                    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_LONG).show();

                    Animation a11 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                    bt1.startAnimation(a11);

                    Intent i4= new Intent(RegisterActivity.this, PinActivity.class);
                    startActivity(i4);
                }
            }
        });
    }



    private void setDateTimeField() {
        Calendar c = Calendar.getInstance();
        dp = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar cd = Calendar.getInstance();
                cd.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
                final Date s2 = cd.getTime();
                String fdate = sd.format(s2);
                ed3.setText(fdate);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dp.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            iv1.setImageBitmap(photo);
        }
    }
}