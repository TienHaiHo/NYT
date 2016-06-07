package com.example.dkkbg_000.nytretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.dkkbg_000.nytretrofit.Pojo.NYT;
import com.example.dkkbg_000.nytretrofit.Pojo.Result;
import com.example.dkkbg_000.nytretrofit.rest.apiInterfaceNYT;
import com.example.dkkbg_000.nytretrofit.rest.apiNYT;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    final String key = "ffd170d7fe4246c69413fb97a1b0ce05";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiInterfaceNYT apiService =
                apiNYT.apiClient().create(apiInterfaceNYT.class);

        Call<NYT> call = apiService.topNews(key);
        call.enqueue(new Callback<NYT>() {
            @Override
            public void onResponse(Call<NYT> call, Response<NYT> response) {
                List<Result> news = response.body().getResults();
                Log.d("checkxyz: ", String.valueOf(news.size()));
                recyclerView.setAdapter(new adapter(news, R.layout.item_news, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<NYT> call, Throwable t) {
                Log.e("fail", t.toString());
            }
        });
    }
}
