package com.example.dkkbg_000.nytretrofit.presenter;

import com.example.dkkbg_000.nytretrofit.model.Result;

import java.util.List;

/**
 * Created by dkkbg_000 on 09/06/2016.
 */
public interface CallbackLoadNews {
    void onSuccess(List<Result> results);
    void onFaill(List<Result> error);
}
