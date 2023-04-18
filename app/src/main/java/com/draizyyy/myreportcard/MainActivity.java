package com.draizyyy.myreportcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import com.draizyyy.myreportcard.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static MainActivity instance;
    DayDatabase db;
    private ActivityMainBinding binding;
    private final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        db = Room.databaseBuilder(getApplicationContext(), DayDatabase.class, "data-database")
                .allowMainThreadQueries()
                .build();
        binding = ActivityMainBinding.inflate(getLayoutInflater());

//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View view = binding.getRoot();
        setContentView(view);

        NewsActivity newsActivity = new NewsActivity();
        TimetableActivity timetableActivity = new TimetableActivity();
        GradeActivity gradeActivity = new GradeActivity();

        swapFragment(timetableActivity);
        setAlphaToDefault();
        binding.timetableButton.setAlpha(1);

        binding.newsButton.setOnClickListener(view1 -> {
            swapFragment(newsActivity);
            setAlphaToDefault();
            binding.newsButton.setAlpha(1);
        });
        binding.timetableButton.setOnClickListener(view1 -> {
            swapFragment(timetableActivity);
            setAlphaToDefault();
            binding.timetableButton.setAlpha(1);
        });
        binding.homeworksButton.setOnClickListener(view1 -> {
//            swapFragment(timetableActivity);
//            setAlphaToDefault();
//            binding.homeworksButton.setAlpha(1);
        });
        binding.marksButton.setOnClickListener(view1 -> {
            swapFragment(gradeActivity);
            setAlphaToDefault();
            binding.marksButton.setAlpha(1);
        });
        binding.settingsButton.setOnClickListener(view1 -> {
//            swapFragment(timetableActivity);
//            setAlphaToDefault();
//            binding.settingsButton.setAlpha(1);
        });

    }
    public DayDatabase getDatabaseInstance() {
        return db;
    }
    private void setAlphaToDefault() {
        binding.settingsButton.setAlpha((float) 0.3);
        binding.newsButton.setAlpha((float) 0.3);
        binding.homeworksButton.setAlpha((float) 0.3);
        binding.marksButton.setAlpha((float) 0.3);
        binding.timetableButton.setAlpha((float) 0.3);
    }
    private void swapFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.placeholder, fragment);

        transaction.commit();
    }
}