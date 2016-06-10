package com.example.dkkbg_000.nytretrofit.presenter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dkkbg_000 on 07/06/2016.
 */
public class NYTClientIml {

    private static Retrofit retrofit = null;

    public static Retrofit apiClientBussiness(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.URL_BUSSINESS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void setNullRetrofit(){
        retrofit=null;
    }

    public static Retrofit apiClientArt(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.URL_ART)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
