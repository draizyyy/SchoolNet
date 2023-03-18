package com.draizyyy.myreportcard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.draizyyy.myreportcard.databinding.LessonInfoBinding;

public class LessonInfoActivity extends Fragment {
    private LessonInfoBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Window window = getActivity().getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(getResources().getColor(R.color.lesson_background));

        binding = LessonInfoBinding.inflate(inflater, container, false);

        binding.getRoot().setOnClickListener(view1 -> {});

        return binding.getRoot();
    }
}
