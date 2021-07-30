package com.example.democode;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.aviran.cookiebar2.CookieBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class quiz extends AppCompatActivity {

    TextView tv2,tv4;
    RadioButton a,b,c,d;
    Button bt;
    RadioGroup rg;
    List<Integer> list;
    protected static MediaPlayer m1, m2;

    int correct=0, j=0, q=0;
    String optA[]={"Apple", "Corn Chips", "Speeds up the process of boiling water", "To make dough sweeter", "It has been frozen solid and is not safe to eat",
            "To test the tenderness of the meat being cooked", "Add honey and tomatoes", "Apple and avocado slices", "Two",
            "The redder the sauce, the longer the pasta should be", "A type of cake originating in Yorkshire, spiced with cinnamon"
    };

    String optB[]={ "Cilantro", "Chocolate", "It delays the process of water boiling", "To make the dough bake faster", "It has been gift wrapped",
            "Used to check the temperature of a grill", "Add a squeeze of lemon or lime juice or a spoon of apple cider vinegar",
            "Potato and radish slices", "Three", "Long pastas are best with cream sauces or olive oil",
            "A type of biscuit originating in Lancashire, spiced with caraway seeds"
    };

    String optC[]={ "Pickle", "Cherry Pie filling", "Softens the water", "Used as a leavening agent in baked goods",
            "It is only suitable for cooking and not eating", "Used to see how long you can keep your hand by a hot grill", "Add more water",
            "Lemon and cucumber slices", "Four", "Its all good!", "A type of cake originating in Wales, made with almonds"
    };

    String optD[]={ "Butter", "Apples", "Stops the water from boiling at all", "To make the dough look more edible",
            "It is the process that re-establishing the cocoa butters crystals, resulting in much better-quality chocolate",
            "It’s a test to determine the metal strengths of a grill", "Add olive oil", "Banana and pear slices", "Six", "None of these",
            "A type of biscuit originating in Kent, made with poppy seeds"
    };

    String ques[]={"You have bread and cheese for your grilled cheese sandwich, but what else do you need?",
            "Which of these ingredients is needed for the classic campfire treat?", "How does adding salt to boiling water affect it?",
            "What is baking soda used for in baked goods?", "When chocolate has been tempered, what does it mean?",
            "What does the term ‘hand test’ mean in relation to grilling?", "How can you fix a dish that has been over-sweetened?",
            "What are the two most common items placed in water to make it refreshing?",
            "How many teaspoons make one tablespoon?", "When it comes to Italian pasta and sauces:", "What is a Goosnargh cake?"
    };

    String opt[]={"Butter", "Chocolate", "It delays the process of water boiling", "Used as a leavening agent in baked goods",
            "It is the process that re-establishing the cocoa butters crystals, resulting in much better-quality chocolate",
            "Used to check the temperature of a grill", "Add a squeeze of lemon or lime juice or a spoon of apple cider vinegar",
            "Lemon and cucumber slices", "Three", "Long pastas are best with cream sauces or olive oil",
            "A type of biscuit originating in Lancashire, spiced with caraway seeds"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        getSupportActionBar().setTitle("Cooking Quiz");

        m1= MediaPlayer.create(getApplicationContext(), R.raw.quizs);
        m2= MediaPlayer.create(getApplicationContext(), R.raw.tring);
        m1.start();

        list = new ArrayList<>();
        for(int r=0; r<11; r++)
            list.add(r);

        tv2=(TextView)findViewById(R.id.textView2);
        tv4=(TextView)findViewById(R.id.textView4);
        rg=(RadioGroup)findViewById(R.id.radioGroup);
        a=(RadioButton)findViewById(R.id.radioButton);
        b=(RadioButton)findViewById(R.id.radioButton2);
        c=(RadioButton)findViewById(R.id.radioButton3);
        d=(RadioButton)findViewById(R.id.radioButton4);
        bt=(Button)findViewById(R.id.button);

        Animation a1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        tv4.startAnimation(a1);

        Animation a2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        tv2.startAnimation(a2);

        Animation a3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        bt.startAnimation(a3);


        Random rand = new Random();
        q= list.get(rand.nextInt(list.size()));
        tv2.setText(ques[q]);
        a.setText(optA[q]);
        b.setText(optB[q]);
        c.setText(optC[q]);
        d.setText(optD[q]);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rg.getCheckedRadioButtonId()==-1) {
                    CookieBar.build(quiz.this)
                            .setTitle("Please select a choice !!")
                            .setIcon(R.drawable.ic_baseline_flutter_dash_24)
                            .setCookiePosition(CookieBar.BOTTOM).show();
                    return;
                }

                m2.start();
                RadioButton uans = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();

                if (ansText.equals(opt[q])) {
                    correct++;
                    CookieBar.build(quiz.this)
                            .setTitle("Correct Answer").setMessage("Keep it up...")
                            .setIcon(R.drawable.ic_baseline_flutter_dash_24)
                            .setCookiePosition(CookieBar.BOTTOM).show();
                }
                else
                    CookieBar.build(quiz.this)
                            .setTitle("Wrong Answer").setMessage("Correct Answer is: "+ opt[q])
                            .setIcon(R.drawable.ic_baseline_flutter_dash_24)
                            .setCookiePosition(CookieBar.BOTTOM).show();

                tv4.setText("Total Score: " + correct * 10);

                Random rand = new Random();
                q= list.get(rand.nextInt(list.size()));

                j++;
                if (j==5) {
                    bt.setText("Restart");
                    m1.release();
                    m2.release();
                    tv4.setText("Final Score is " + correct * 10);


                    Dialog d= new Dialog(quiz.this);
                    d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                    d.setContentView(R.layout.game_dialog);
                    params.copyFrom(d.getWindow().getAttributes());
                    d.getWindow().setAttributes(params);
                    d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


                    TextView tv1= (TextView)d.findViewById(R.id.textView5);
                    tv1.setText(" "+ correct*10+ "  ");

                    ImageView ok= d.findViewById(R.id.imageButton);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i2= new Intent(quiz.this, quiz.class);
                            startActivity(i2);
                            d.dismiss();
                        }
                    });

                    ImageView cl= d.findViewById(R.id.imageButton2);
                    cl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i3= new Intent(quiz.this, HomeActivity.class);
                            startActivity(i3);
                            d.dismiss();
                        }
                    });

                    Animation a1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                    ok.startAnimation(a1);
                    cl.startAnimation(a1);

                    d.show();


                }
                else {
                    bt.setText("NEXT");
                    tv2.setText(ques[q]);
                    a.setText(optA[q]);
                    b.setText(optB[q]);
                    c.setText(optC[q]);
                    d.setText(optD[q]);

                    rg.clearCheck();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        m1.release();
        m2.release();
        finish();
        return;
    }
}