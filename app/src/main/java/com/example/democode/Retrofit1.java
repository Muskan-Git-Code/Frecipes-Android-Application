package com.example.democode;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit1 {

    private static Retrofit rt; //retrofit instance
    private static final String url="https://sleepy-gorge-57188.herokuapp.com/";

    public static Retrofit getRt(){

        if (rt==null){      //so that retrofit instance is created only once
            rt = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        else {

        }
        return rt;
    }
}
