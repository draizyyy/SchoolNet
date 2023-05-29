package com.draizyyy.myreportcard.actiivities;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.draizyyy.myreportcard.fragments.NewsActivity;
import com.draizyyy.myreportcard.R;
import com.draizyyy.myreportcard.fragments.TimetableActivity;
import com.draizyyy.myreportcard.databinding.ActivityMainBinding;
import com.draizyyy.myreportcard.fragments.AccountActivity;
import com.draizyyy.myreportcard.fragments.GradeActivity;
import com.draizyyy.myreportcard.fragments.HomeworksActivity;
import com.draizyyy.myreportcard.retrofit.NetworkService;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email = getIntent().getExtras().getString("email");
        NewsActivity newsActivity = new NewsActivity();
        newsActivity.initData();
        TimetableActivity timetableActivity = new TimetableActivity();
        timetableActivity.initData();
        HomeworksActivity homeworksActivity = new HomeworksActivity();
        homeworksActivity.initData();
        GradeActivity gradeActivity = new GradeActivity();
        gradeActivity.initData();

        binding = ActivityMainBinding.inflate(getLayoutInflater());

//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View view = binding.getRoot();
        setContentView(view);

        AccountActivity accountActivity = new AccountActivity();
        accountActivity.getNameAndSurname(email);

        swapFragment(newsActivity);

        setAlphaToDefault();
        binding.newsButton.setAlpha(1);

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
            swapFragment(homeworksActivity);
            setAlphaToDefault();
            binding.homeworksButton.setAlpha(1);
        });
        binding.marksButton.setOnClickListener(view1 -> {
            swapFragment(gradeActivity);
            setAlphaToDefault();
            binding.marksButton.setAlpha(1);
        });
        binding.settingsButton.setOnClickListener(view1 -> {
            swapFragment(accountActivity);
            setAlphaToDefault();
            binding.settingsButton.setAlpha(1);
        });
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

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
    }
}