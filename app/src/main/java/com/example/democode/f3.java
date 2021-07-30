package com.example.democode;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link f3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class f3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView lv;
    VideoView vw;
    MediaController mc;
    String[] videos= {"Samosa Recipe", "Chocolate Brownies", "Chocolate Chip Cookies", "Cookies And Cream Churro Ice Cream Bowls", "Easy Poke Cake"};

    public f3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment f3.
     */
    // TODO: Rename and change types and number of parameters
    public static f3 newInstance(String param1, String param2) {
        f3 fragment = new f3();
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
        View v= inflater.inflate(R.layout.fragment_f3, container, false);

        vw= (VideoView)v.findViewById(R.id.videoView3);

        ArrayAdapter<String> ad= new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, videos);
        lv= v.findViewById(R.id.listview);
        lv.setAdapter(ad);

        Animation a1 = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        lv.startAnimation(a1);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Animation a1 = AnimationUtils.loadAnimation(getContext(), R.anim.blink);
                view.startAnimation(a1);

                switch (position){
                    case 0: vw.setVideoURI(Uri.parse("android.resource://"+ getActivity().getPackageName()+ "/"+ R.raw.samosa1));
                        break;
                    case 1: vw.setVideoURI(Uri.parse("android.resource://"+ getActivity().getPackageName()+ "/"+ R.raw.chocolate_brownie1));
                        break;
                    case 2: vw.setVideoURI(Uri.parse("android.resource://"+ getActivity().getPackageName()+ "/"+ R.raw.chocochip_cookie1));
                        break;
                    case 3: vw.setVideoURI(Uri.parse("android.resource://"+ getActivity().getPackageName()+ "/"+ R.raw.icecream1));
                        break;
                    case 4: vw.setVideoURI(Uri.parse("android.resource://"+ getActivity().getPackageName()+ "/"+ R.raw.poke_cake1));
                        break;
                }

                vw.setMediaController(new MediaController(getContext()));
                vw.requestFocus();
                vw.start();
            }
        });

        return v;
    }
}