package com.example.dkkbg_000.nytretrofit.presenter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dkkbg_000 on 07/06/2016.
 */
public class apiNYT {
    //public final static String URL = "http://api.nytimes.com/svc/news/v3/content/all/arts/";
    public final static String URL = "http://api.nytimes.com/svc/news/v3/content/nyt/business/";



    private static Retrofit retrofit = null;

    public static Retrofit apiClient (){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
