package com.example.democode;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface1 {

    @GET("categories")   //name given in api url on internet
    Call<List<PostPojo1>> getPosts();
}