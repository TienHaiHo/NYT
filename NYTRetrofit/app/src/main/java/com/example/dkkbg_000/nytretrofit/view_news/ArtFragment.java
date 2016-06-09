package com.example.dkkbg_000.nytretrofit.view_news;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dkkbg_000.nytretrofit.R;
import com.example.dkkbg_000.nytretrofit.adapter.Adapter_MainActivity;
import com.example.dkkbg_000.nytretrofit.model.Result;
import com.example.dkkbg_000.nytretrofit.presenter.CallbackLoadNews;
import com.example.dkkbg_000.nytretrofit.presenter.LoadNews;
import com.example.dkkbg_000.nytretrofit.presenter.SolveTimerReload;

import java.util.List;

public class ArtFragment extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    final String key = "ffd170d7fe4246c69413fb97a1b0ce05";

    public ArtFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_art, container, false);

        final Context c = getActivity();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.news_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        LoadNews newsBussiness = new LoadNews(key,2);
        newsBussiness.getListNews(new CallbackLoadNews() {
            @Override
            public void onSuccess(List<Result> results) {
                recyclerView.setAdapter(new Adapter_MainActivity(results, R.layout.item_news, c));
            }

            @Override
            public void onFaill(List<Result> error) {
                Toast.makeText(c, "fail", Toast.LENGTH_SHORT).show();
            }
        });


        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                try {
                    SolveTimerReload reload = new SolveTimerReload(key);
                    reload.addTime(new CallbackLoadNews() {
                        @Override
                        public void onSuccess(List<Result> results) {
                            recyclerView.setAdapter(new Adapter_MainActivity(results, R.layout.item_news, c));
                            if (swipeRefreshLayout.isRefreshing())
                                swipeRefreshLayout.setRefreshing(false);
                        }

                        @Override
                        public void onFaill(List<Result> error) {
                            Toast.makeText(c, "fail", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        swipeRefreshLayout.setColorSchemeResources(
                R.color.blue,       //This method will rotate
                R.color.red,        //colors given to it when
                R.color.yellow,     //loader continues to
                R.color.green);     //refresh.

        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.white);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
