package com.draizyyy.myreportcard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.draizyyy.myreportcard.databinding.NewSBinding;
import com.draizyyy.myreportcard.databinding.ActivityNewsBinding;

public class NewsActivity extends Fragment {
    private final List<News> newsList = new ArrayList<>();


    private ActivityNewsBinding newsBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsBinding = newsBinding.inflate(inflater, container, false);

        initData();
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
//        newsBinding.addButton.setOnClickListener(view -> {
//            list.add(Finances.getRandom());
//            binding.recyclerView.getAdapter().notifyItemChanged(list.size() - 1);
//        });
    }

    private void initData() {
        newsList.add(new News("Администрация", "20 апреля", "Завтра уроки отменяются по закону Григоренко"));
        newsList.add(new News("Королёв Б.И.", "17 апреля", "Завтра отменяются все уроки физики, потому что я сегодня добрый."));
        newsList.add(new News("Кручинина О.Б.", "19 апреля", "Сегодня уроков не будет, можете идти домой"));
        newsList.add(new News("Supercell", "20 апреля", "Завтра всех забанят в бравл старс"));
    }
}