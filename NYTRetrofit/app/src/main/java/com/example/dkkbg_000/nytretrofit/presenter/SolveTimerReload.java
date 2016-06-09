package com.example.dkkbg_000.nytretrofit.presenter;

import android.os.Handler;

import com.example.dkkbg_000.nytretrofit.model.NYT;
import com.example.dkkbg_000.nytretrofit.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dkkbg_000 on 09/06/2016.
 */
public class SolveTimerReload {
    private String key;
    public SolveTimerReload(String key){
        this.key = key;
    }
    public void addTime(final CallbackLoadNews cb) throws InterruptedException {
        //Here I am using a Handler to perform the refresh
        // action after some time to show some fake time
        // consuming task is being performed.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                apiInterfaceNYT apiService =
                        apiNYT.apiClient().create(apiInterfaceNYT.class);

                Call<NYT> call = apiService.topNews(key);
                call.enqueue(new Callback<NYT>() {
                    @Override
                    public void onResponse(Call<NYT> call, Response<NYT> response) {
                        List<Result> news = response.body().getResults();
                        cb.onSuccess(news);
                    }

                    @Override
                    public void onFailure(Call<NYT> call, Throwable t) {
                        cb.onFaill(null);
                    }
                });
//                if (swipeRefreshLayout.isRefreshing())
//                    swipeRefreshLayout.setRefreshing(false);
            }
        }, 4000);
    }
}
