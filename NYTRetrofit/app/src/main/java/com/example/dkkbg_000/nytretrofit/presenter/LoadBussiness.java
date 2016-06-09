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
public class LoadBussiness {
    private String key;
    private List<Result> listNews = null;
    public LoadBussiness(String key){
        this.key = key;
    }

    public void getListNews(final CallbackLoadNews cb){
        Log.d ("key", key);
        apiInterfaceNYT apiService =
                apiNYT.apiClient().create(apiInterfaceNYT.class);

        Call<NYT> call = apiService.topNews(key);
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
