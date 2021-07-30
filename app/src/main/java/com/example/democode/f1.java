package com.example.democode;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link f1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class f1 extends Fragment {

    ApiInterface1 api1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public f1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment f1.
     */
    // TODO: Rename and change types and number of parameters
    public static f1 newInstance(String param1, String param2) {
        f1 fragment = new f1();
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
        View v= inflater.inflate(R.layout.fragment_f1, container, false);

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.recyclerview);

        int numberOfColumns=2;
        rv.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));

        api1 = Retrofit1.getRt().create(ApiInterface1.class);
        api1.getPosts().enqueue(new Callback<List<PostPojo1>>() {
            @Override
            public void onResponse(Call<List<PostPojo1>> call, Response<List<PostPojo1>> response) {

                if(response.body().size()>0){
                    List<PostPojo1> pl1= response.body();

                    F1Adapter ca = new F1Adapter(getActivity(), pl1);
                    rv.setAdapter(ca);
                    rv.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
                }
                else
                    Toast.makeText(getActivity(), "List is empty", Toast.LENGTH_LONG).show();    //so that app cannot crash instead show msg

            }

            @Override
            public void onFailure(Call<List<PostPojo1>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();    //so that app cannot crash instead show msg
            }
        });

        Animation a1 = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        rv.startAnimation(a1);

        return v;
    }
}