package com.example.democode;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link f2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class f2 extends Fragment {


    ArrayList<String> l1 = new ArrayList<>(Arrays.asList("Baking Recipes", "Cheesecake Recipes", "Chocolate Recipes", "Fruit Dessert Recipes", "French Recipes", "Indian Recipes", "Italian Recipes", "Healthy Snacks"));
    ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(R.drawable.bake, R.drawable.cheese, R.drawable.choco1, R.drawable.dessert, R.drawable.french1, R.drawable.indian2, R.drawable.italian2, R.drawable.snacks));

    F2Adapter ca;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public f2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment f2.
     */
    // TODO: Rename and change types and number of parameters
    public static f2 newInstance(String param1, String param2) {
        f2 fragment = new f2();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_f2, container, false);

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.recyclerview);

        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);

        ca = new F2Adapter(getContext(), l1,l2);
        rv.setAdapter(ca);

        SearchView sv = (SearchView) v.findViewById(R.id.searchView);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override    public boolean onQueryTextSubmit(String query) {
                if(l1.contains(query))
                    filter(query);
                else
                    Toast.makeText(getContext(), "No Match found",Toast.LENGTH_LONG).show();
                return false;
            }
            @Override    public boolean onQueryTextChange(String newText) {
                filter(newText);    return false;
            }
        });

        Animation a1 = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        rv.startAnimation(a1);


        return v;
    }

    private void filter(String text) {
        ArrayList<String> filterdNames = new ArrayList<>();
        for (String s : l1)
            if (s.toLowerCase().contains(text.toLowerCase()))
                filterdNames.add(s);

        ca.filterList(filterdNames);
    }
}