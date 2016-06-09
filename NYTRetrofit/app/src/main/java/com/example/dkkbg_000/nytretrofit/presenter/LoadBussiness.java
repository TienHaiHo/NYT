package com.example.dkkbg_000.nytretrofit.presenter;

import android.util.Log;

import com.example.dkkbg_000.nytretrofit.Pojo.NYT;
import com.example.dkkbg_000.nytretrofit.Pojo.Result;
import com.example.dkkbg_000.nytretrofit.rest.apiInterfaceNYT;
import com.example.dkkbg_000.nytretrofit.rest.apiNYT;

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

    public List<Result> getListNews(){
        Log.d ("key", key);
        apiInterfaceNYT apiService =
                apiNYT.apiClient().create(apiInterfaceNYT.class);

        Call<NYT> call = apiService.topNews(key);
        call.enqueue(new Callback<NYT>() {
            @Override
            public void onResponse(Call<NYT> call, Response<NYT> response) {
                listNews = response.body().getResults();
//                return news;
//                Log.d("checkxyz: ", String.valueOf(listNews.size()));
//                recyclerView.setAdapter(new adapter(news, R.layout.item_news, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<NYT> call, Throwable t) {
                Log.e("fail", t.toString());
            }
        });
        Log.d("check list", String.valueOf(listNews.size()));
        return listNews;
    }
    //        apiInterfaceNYT apiService =
//                apiNYT.apiClient().create(apiInterfaceNYT.class);
//
//        Call<NYT> call = apiService.topNews(key);
//        call.enqueue(new Callback<NYT>() {
//            @Override
//            public void onResponse(Call<NYT> call, Response<NYT> response) {
//                List<Result> news = response.body().getResults();
//                Log.d("checkxyz: ", String.valueOf(news.size()));
//                recyclerView.setAdapter(new adapter(news, R.layout.item_news, getApplicationContext()));
//            }
//
//            @Override
//            public void onFailure(Call<NYT> call, Throwable t) {
//                Log.e("fail", t.toString());
//            }
//        });

}
