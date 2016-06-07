package com.example.dkkbg_000.nytretrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dkkbg_000.nytretrofit.Pojo.Result;

import java.util.List;

/**
 * Created by dkkbg_000 on 07/06/2016.
 */
public class adapter extends RecyclerView.Adapter<adapter.MovieViewHolder>{
    private List<Result> news;
    private int rowLayout;
    private Context context;


    public adapter(List<Result> news, int rowLayout, Context context) {
        this.news = news;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public adapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        Log.d("title",news.get(position).getTitle());
        holder.movieTitle.setText(news.get(position).getTitle());
        holder.abstractNews.setText(news.get(position).getAbstract());
//        holder.movieDescription.setText(news.get(position).getOverview());
//        holder.rating.setText(news.get(position).getVoteAverage().toString());
    }

    @Override
    public int getItemCount() {
        Log.d("size", String.valueOf(news.size()));
        return news.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView abstractNews;
//        TextView movieDescription;
//        TextView rating;


        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.temp);
            movieTitle = (TextView) v.findViewById(R.id.title);
            abstractNews = (TextView) v.findViewById(R.id.abstract_news);
//            data = (TextView) v.findViewById(R.id.subtitle);
//            movieDescription = (TextView) v.findViewById(R.id.description);
//            rating = (TextView) v.findViewById(R.id.rating);
        }
    }
}
