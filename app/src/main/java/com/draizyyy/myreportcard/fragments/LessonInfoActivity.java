package com.draizyyy.myreportcard.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
        Bundle bundle = getArguments();
        binding = LessonInfoBinding.inflate(inflater, container, false);
        binding.getRoot().setOnClickListener(view1 -> {});

        if (bundle != null) {
            String homework = bundle.getString("homework");
            String name = bundle.getString("name");
            String address = bundle.getString("address");
            String schoolName = bundle.getString("schoolName");
            String classroom = bundle.getString("classroom");
            String teacher = bundle.getString("teacher");
            if (!address.equals("") && !schoolName.equals("")) {
                address += ",";
            }

            if (name.equals("")) {
                binding.nameInv.setVisibility(View.GONE);
                binding.newNameOfLesson.setVisibility(View.GONE);
            }

            if (teacher.equals("")) {
                binding.teacherInv.setVisibility(View.GONE);
                binding.teacher.setVisibility(View.GONE);
            }

            if (address.equals("") && schoolName.equals("")) {
                binding.addressInv.setVisibility(View.GONE);
                binding.addressFull.setVisibility(View.GONE);
            }

            if (classroom.equals(""))  {
                binding.classroomInv.setVisibility(View.GONE);
                binding.classroom.setVisibility(View.GONE);
            }

            if (classroom.equals(""))  {
                binding.classroomInv.setVisibility(View.GONE);
                binding.classroom.setVisibility(View.GONE);
            }

            if (homework.equals(""))  {
                binding.homeworkInv.setVisibility(View.GONE);
                binding.homeworkFull.setVisibility(View.GONE);
            }

            binding.homework.setMovementMethod(new ScrollingMovementMethod());
            binding.homework.setText(homework);
            binding.newNameOfLesson.setText(name);
            binding.teacher.setText(teacher);
            binding.address.setText(address);
            binding.school.setText(schoolName);
            binding.classroom.setText(classroom);
        }
//        Window window = getActivity().getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(getResources().getColor(R.color.lesson_background));

        return binding.getRoot();
    }
}
