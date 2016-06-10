package com.example.dkkbg_000.nytretrofit.presenter;

import com.example.dkkbg_000.nytretrofit.model.NYT;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dkkbg_000 on 07/06/2016.
 */
public interface NYTClientInterface {

    @GET("72.json")
    Call<NYT> topNewsBussiness(@Query("api_key") String apiKey);

    @GET("24.json?limit=10")
    Call<NYT> topNewsArt(@Query("api_key") String apiKey);
}
