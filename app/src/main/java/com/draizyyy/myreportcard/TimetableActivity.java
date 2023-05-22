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

import com.draizyyy.myreportcard.DayDao;

import com.draizyyy.myreportcard.databinding.ActivityTimetableBinding;
import com.draizyyy.myreportcard.databinding.TimetableDayBinding;

public class TimetableActivity extends Fragment {
//    private final List<Lesson> lessonList = new ArrayList<>();
    private final List<Day> dayList = new ArrayList<>();

    private ActivityTimetableBinding weekBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weekBinding = ActivityTimetableBinding.inflate(inflater, container, false);
        setupUI();

        Log.i("MY APP", "creating done");
        return weekBinding.getRoot();
    }
    private void setupUI() {
//        RecyclerView DayRecyclerViewItem
//                = weekBinding.timetable;
//
//        // Initialise the Linear layout manager
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//
//        // Pass the arguments
//        // to the parentItemAdapter.
//        // These arguments are passed
//        // using a method ParentItemList()
//        MyDayAdapter myDayAdapter = new MyDayAdapter(dayList);
//
//
//        // Set the layout manager
//        // and adapter for items
//        // of the parent recyclerview
//        DayRecyclerViewItem.setAdapter(myDayAdapter);
//        DayRecyclerViewItem.setLayoutManager(layoutManager);

        weekBinding.timetable.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx > 0) {
//                    Log.i("MY APP", "dx > 0");
//                    Log.v("MY APP", "POSITION: " + getCurrentItem());
                    if (getCurrentItem() == dayList.size() - 2) {
                        Log.v("MY APP", "Day added to end");
                        Log.i("MY APP", String.valueOf(dayList.size()));
                        dayList.add(getDay());
                        weekBinding.timetable.getAdapter().notifyItemChanged(dayList.size() - 1);
                    }
                } else if (dx < 0) {
//                    Log.i("MY APP", "dx < 0");
//                    Log.v("MY APP", "POSITION: " + getCurrentItem());
                    if (getCurrentItem() == 1 || getCurrentItem() == 0) {
                        Log.v("MY APP", "Day added to start");
                        Log.i("MY APP", String.valueOf(dayList.size()));
                        dayList.add(0, getDay());
                        weekBinding.timetable.getAdapter().notifyItemInserted(0);
                    }
                }
            }
        });

        weekBinding.timetable.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        weekBinding.timetable.setAdapter(new MyDayAdapter(dayList));
        PagerSnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(weekBinding.timetable);
        Objects.requireNonNull(weekBinding.timetable.getLayoutManager()).scrollToPosition(Date.getTodayDateNumber());
    }
    private Day getDay() {
        //нужно добавить вот эту штуку во все остальные классы где это нужно
        ArrayList<Lesson> arrayList = new ArrayList<>();
        ArrayList<Bundle> bundleList = new ArrayList<>();
        for (int i = 0; i < 2; ++i) {
            arrayList.add(new Lesson("0", "0", "test"));
            bundleList.add(new Bundle());
        }
        return new Day("Тестовый день", arrayList);
    }
    private int getCurrentItem(){
        return ((LinearLayoutManager)weekBinding.timetable.getLayoutManager())
                .findFirstVisibleItemPosition();
    }

    private void setCurrentItem(int position, boolean smooth){
        if (smooth)
            weekBinding.timetable.smoothScrollToPosition(position);
        else
            weekBinding.timetable.scrollToPosition(position);
    }
    public void initData() {
        Log.i("MY APP", "initing data");
        new Thread(() -> {
            DayDao dayDao = App.getDatabase().dayDao();
            Log.i("MY APP", "got dao");
            dayList.addAll(dayDao.getAll());
            Day day = dayList.get(2);
            Log.i("MY APP", "timetable dayList 2 day lessonList size: " + day.LessonsList.size());
        }).start();
        Log.i("MY APP", "done1");
    }
}