package com.example.dkkbg_000.nytretrofit.rest;

import com.example.dkkbg_000.nytretrofit.Pojo.NYT;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dkkbg_000 on 07/06/2016.
 */
public interface apiInterfaceNYT {
    //&api-key=
    //@GET("24.json?limit=10")

    @GET("72.json")
    Call<NYT> topNews(@Query("api_key") String apiKey);
//    @GET("/24.json?limit=10")
//    void
}
