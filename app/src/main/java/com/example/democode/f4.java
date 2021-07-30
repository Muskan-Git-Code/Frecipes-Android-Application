package com.example.democode;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.aviran.cookiebar2.CookieBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link f4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class f4 extends Fragment {

    NotesDb helper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public f4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment f4.
     */
    // TODO: Rename and change types and number of parameters
    public static f4 newInstance(String param1, String param2) {
        f4 fragment = new f4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_f4, container, false);

        helper = new NotesDb(getActivity());


        view(v);


        FloatingActionButton fb= (FloatingActionButton)v.findViewById(R.id.floatingActionButton4);

        Animation a1 = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        fb.startAnimation(a1);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation a1 = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                fb.startAnimation(a1);

                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                dialog.setContentView(R.layout.note_dialog);
                params.copyFrom(dialog.getWindow().getAttributes());
                dialog.getWindow().setAttributes(params);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText ed1= (EditText)dialog.findViewById(R.id.title1);
                EditText ed2 = (EditText) dialog.findViewById(R.id.description1);
                TextView tv1= (TextView)dialog.findViewById(R.id.date1);

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String cd = sdf.format(new Date());
                tv1.setText(cd);

                Button submit = (Button) dialog.findViewById(R.id.submit1);
                Animation a12 = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                submit.startAnimation(a12);

                submit.setOnClickListener(new View.OnClickListener() {;
                    @Override
                    public void onClick(View v) {
                        ;
                        long ix = helper.insert(ed1.getText().toString(), ed2.getText().toString(), tv1.getText().toString());
                        if(ix>0)
                            CookieBar.build(getActivity())
                                    .setTitle("Insertion Successful")
                                    .setIcon(R.drawable.ic_baseline_sentiment_very_satisfied_24)
                                    .setCookiePosition(CookieBar.BOTTOM).show();
//                            Toast.makeText(getActivity(), "Insertion Successful", Toast.LENGTH_LONG).show();

                        view(v);

                        dialog.cancel();
                    }
                });


            }
        });


        view(v);

        return v;

    }

    private void view(View v) {
        ArrayList<String> l1 = new ArrayList<>(), l2=new ArrayList<>(), l3=new ArrayList<>(), l4=new ArrayList<>();
        Cursor c= helper.viewData();
        while(c.moveToNext()){
            l1.add(String.valueOf(c.getInt(0)));
            l2.add(c.getString(1));
            l3.add(c.getString(2));
            l4.add(c.getString(3));
        }

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.recyclerView3);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);

        F4Adapter ca = new F4Adapter(getActivity(), l1,l2, l3, l4);
        rv.setAdapter(ca);

        Animation a1 = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        rv.startAnimation(a1);
    }
}