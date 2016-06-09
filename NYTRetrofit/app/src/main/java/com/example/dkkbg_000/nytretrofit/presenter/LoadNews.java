package com.example.dkkbg_000.nytretrofit.presenter;

import android.util.Log;

import com.example.dkkbg_000.nytretrofit.model.NYT;
import com.example.dkkbg_000.nytretrofit.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dkkbg_000 on 08/06/2016.
 */
public class LoadNews {
    private String key;
    private int kind;
    private List<Result> listNews = null;
    public LoadNews(String key, int kind){
        this.key = key;
        this.kind = kind;
    }

    public void getListNews(final CallbackLoadNews cb){
        Log.d ("key", key);
        apiNYT.setNullRetrofit();
        apiInterfaceNYT apiService = kind==1
                ?apiNYT.apiClientBussiness().create(apiInterfaceNYT.class)
                :apiNYT.apiClientArt().create(apiInterfaceNYT.class);


        Call<NYT> call = (kind==1)
                ?apiService.topNewsBussiness(key)
                :apiService.topNewsArt(key);
        call.enqueue(new Callback<NYT>() {
            @Override
            public void onResponse(Call<NYT> call, Response<NYT> response) {
                listNews = response.body().getResults();
                cb.onSuccess(listNews);
            }

            @Override
            public void onFailure(Call<NYT> call, Throwable t) {
                //t.printStackTrace();
            }
        });
    }

}
