package com.example.dkkbg_000.nytretrofit.presenter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dkkbg_000 on 07/06/2016.
 */
public class apiNYT {
    //public final static String URL = "http://api.nytimes.com/svc/news/v3/content/all/arts/";
    public final static String URLBussiness = "http://api.nytimes.com/svc/news/v3/content/nyt/business/";
    public final static String URLArt = "http://api.nytimes.com/svc/news/v3/content/all/arts/";



    private static Retrofit retrofit = null;

    public static Retrofit apiClientBussiness(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URLBussiness)
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
                    .baseUrl(URLArt)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
