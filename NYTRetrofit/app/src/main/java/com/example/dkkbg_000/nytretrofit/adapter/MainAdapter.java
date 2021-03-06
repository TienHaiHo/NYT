package com.example.dkkbg_000.nytretrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dkkbg_000.nytretrofit.R;
import com.example.dkkbg_000.nytretrofit.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dkkbg_000 on 07/06/2016.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MovieViewHolder>{
    private List<Result> news;
    private int rowLayout;
    private Context context;


    public MainAdapter(List<Result> news, int rowLayout, Context context) {
        this.news = news;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MainAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(news.get(position).getTitle());
        holder.abstractNews.setText(news.get(position).getAbstract());
        String thumbnail = news.get(position).getThumbnailStandard();
        if (thumbnail.length()>1){
            holder.image.setMinimumHeight(200);
            holder.image.setMinimumWidth(200);
            Picasso.with(context)
                    .load(thumbnail.replace("\\",""))
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView abstractNews;
        ImageView image;


        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.temp);
            movieTitle = (TextView) v.findViewById(R.id.title);
            abstractNews = (TextView) v.findViewById(R.id.abstract_news);
            image = (ImageView) v.findViewById(R.id.image_news);
        }
    }
}
