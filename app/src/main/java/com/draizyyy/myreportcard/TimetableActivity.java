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

import com.draizyyy.myreportcard.databinding.ActivityTimetableBinding;
import com.draizyyy.myreportcard.databinding.TimetableDayBinding;

public class TimetableActivity extends Fragment {
    private final List<Lesson> lessonList = new ArrayList<>();
    private final List<Day> dayList = new ArrayList<>();
    Lesson lesson = new Lesson("Литература", "08:45", "09:30", "5", "215", "Пролетарский проспект", "Лицей 1511", "Андреев М.Ф.", "Прочитать \"Бэлу\", подготовить ответы с опорой на текст на вопросы из ДЗ: закладки/выписанные на листок с заданием номера страниц. Учим и сдаём второе из пяти стихотворений Лермонтова. И ещё много много много много много много много много много много много много много много много много много много много много текста");
    Lesson lesson1 = new Lesson("Английский язык", "09:50", "10:35");
    Lesson lesson2 = new Lesson("Информатика", "10:50", "11:35","4", "123", "", "", "Янков В.Ю.");
    Lesson lesson3 = new Lesson("Алгебра", "11:45", "12:30");
    Lesson lesson4 = new Lesson("Алгебра", "12:45", "13:30", "5", "21А", "Кленовый Бульвар", "Лицей 1523", "Алексеева Н.И.", "А-9, &22, № 22.6, 22.8, 22.14, 22.16, 22.20");
    Lesson lesson5 = new Lesson("Химия", "13:45", "14:30", "4", "", "", "", "", "Знать определения.");
    Lesson lesson6 = new Lesson("Обществознание", "14:35", "15:35", "3","37", "Аоаоаоа", "Лицей 1123", "Никитин А.П.", "Прочитать §16, ответить на вопросы на стр. 64-65.");
    Lesson lesson7 = new Lesson("Информатика", "15:45", "16:30", "5", "52", "", "Лицей 1523");
    Lesson lesson8 = new Lesson("Основы безопаснос...", "16:45", "17:30");

    Day day0 = new Day("Воскресенье", new ArrayList<>(), "12 марта");
    Day day1 = new Day("Понедельник", lessonList, "13 марта");
    Day day2 = new Day("Вторник", lessonList, "14 марта");
    Day day3 = new Day("Среда", lessonList, "15 марта");
    Day day4 = new Day("Четверг", lessonList, "16 марта");
    Day day5 = new Day("Пятница", lessonList, "17 марта");
    Day day6 = new Day("Суббота", lessonList, "18 марта");
    Day day7 = new Day("Воскресенье", lessonList, "19 марта");
    Day day8 = new Day("Понедельник", lessonList, "20 марта");

    private ActivityTimetableBinding weekBinding;
    private TimetableDayBinding dayBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weekBinding = ActivityTimetableBinding.inflate(inflater, container, false);

        initData();
        setupUI();

        return weekBinding.getRoot();
    }
    private void setupUI() {
        RecyclerView DayRecyclerViewItem
                = weekBinding.timetable;

        // Initialise the Linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        // Pass the arguments
        // to the parentItemAdapter.
        // These arguments are passed
        // using a method ParentItemList()
        MyDayAdapter myDayAdapter = new MyDayAdapter(dayList);

        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview
        DayRecyclerViewItem.setAdapter(myDayAdapter);
        DayRecyclerViewItem.setLayoutManager(layoutManager);

        DayRecyclerViewItem.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx > 0) {
                    Log.i("MY APP", "dx > 0");
                    Log.v("MY APP", "POSITION: " + getCurrentItem());
                    if (getCurrentItem() == dayList.size() - 2) {
                        Log.v("MY APP", "Day added to end");
                        Log.i("MY APP", String.valueOf(dayList.size()));
                        dayList.add(getDay());
                        weekBinding.timetable.getAdapter().notifyItemChanged(dayList.size() - 1);
                    }
                } else if (dx < 0) {
                    Log.i("MY APP", "dx < 0");
                    Log.v("MY APP", "POSITION: " + getCurrentItem());
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
        helper.attachToRecyclerView(DayRecyclerViewItem);
        Objects.requireNonNull(DayRecyclerViewItem.getLayoutManager()).scrollToPosition(Date.getTodayDateNumber());
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
    private void initData() {
        lessonList.add(lesson);
        lessonList.add(lesson1);
        lessonList.add(lesson2);
        lessonList.add(lesson3);
        lessonList.add(lesson4);
        lessonList.add(lesson5);
        lessonList.add(lesson6);
        lessonList.add(lesson7);
        lessonList.add(lesson8);

        dayList.add(day0);
        dayList.add(day1);
        dayList.add(day2);
        dayList.add(day3);
        dayList.add(day4);
        dayList.add(day5);
        dayList.add(day6);
        dayList.add(day7);
        dayList.add(day8);
    }
}