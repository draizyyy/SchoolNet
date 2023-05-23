package com.draizyyy.myreportcard.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import com.draizyyy.myreportcard.App;
import com.draizyyy.myreportcard.adapters.NewsAdapter;
import com.draizyyy.myreportcard.room.NewsDao;
import com.draizyyy.myreportcard.databinding.ActivityNewsBinding;
import com.draizyyy.myreportcard.pojos.News;

public class NewsActivity extends Fragment {
    private final List<News> newsList = new ArrayList<>();


    private ActivityNewsBinding newsBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsBinding = ActivityNewsBinding.inflate(inflater, container, false);

        setupUI();

        return newsBinding.getRoot();
    }

    private void setupUI() {
        newsBinding.news.setLayoutManager(new LinearLayoutManager(
                        getContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                )
        );
        newsBinding.news.setAdapter(new NewsAdapter(newsList));
    }

    public void initData() {
        new Thread(() -> {
            NewsDao newsDao = App.getDatabase().newsDao();
            newsList.addAll(newsDao.getAllNews());
        }).start();
    }
}