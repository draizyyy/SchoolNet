package com.draizyyy.myreportcard;

import android.annotation.SuppressLint;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLessonAdapter extends RecyclerView.Adapter<MyLessonAdapter.MyViewHolder> {
    private final List<Lesson> list;
    private final ArrayList<Bundle> bundleList = new ArrayList<>();
    public MyLessonAdapter(List<Lesson> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.day_lesson,
                parent,
                false
        );
        bundleList.add(new Bundle());
        Bundle bundle = bundleList.get(bundleList.size() - 1);
        view.setOnClickListener(view1 -> {
            AppCompatActivity activity = (AppCompatActivity) view1.getContext();
            LessonInfoActivity lessonInfoFragment = new LessonInfoActivity();
            lessonInfoFragment.setArguments(bundle);
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.open_fragment_anim, R.anim.close_fragment_anim, R.anim.open_fragment_anim, R.anim.close_fragment_anim);
            transaction.replace(R.id.timetable_activity, lessonInfoFragment).addToBackStack("timetableFragment").commit();
        });

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Lesson lesson = list.get(position);
        holder.name.setText(lesson.getName());
        holder.start_time.setText(String.valueOf(lesson.getStart_time()));
        holder.finish_time.setText(lesson.getFinish_time());
        holder.grade.setText(lesson.getGrade());
        Log.v("MY APP", "запрашиваемый номер элемента: " + (bundleList.size() - 1) + ", всего элементов в листе:  " + bundleList.size());
        if (bundleList.size() > 0) {
            bundleList.get(bundleList.size() - 1).putString("name", lesson.name);
            bundleList.get(bundleList.size() - 1).putString("start_time", lesson.start_time);
            bundleList.get(bundleList.size() - 1).putString("finish_time", lesson.finish_time);
            bundleList.get(bundleList.size() - 1).putString("grade", lesson.grade);
            bundleList.get(bundleList.size() - 1).putString("classroom", lesson.classroom);
            bundleList.get(bundleList.size() - 1).putString("address", lesson.address);
            bundleList.get(bundleList.size() - 1).putString("schoolName", lesson.schoolName);
            bundleList.get(bundleList.size() - 1).putString("teacher", lesson.teacher);
            bundleList.get(bundleList.size() - 1).putString("homework", lesson.homework);
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView start_time;
        private final TextView finish_time;
        private final TextView grade;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_of_lesson);
            start_time = itemView.findViewById(R.id.start_time_of_lesson);
            finish_time = itemView.findViewById(R.id.finish_time_of_lesson);
            grade = itemView.findViewById(R.id.grade);
//            itemView.setOnTouchListener((v, event) -> {
//                X = event.getX();
//                Y = event.getY();
//                return true;
//            });
//            itemView.setOnClickListener(view -> {
////                String name1;
//                Bundle bundle = new Bundle();
////                float height = (float) Resources.getSystem().getDisplayMetrics().widthPixels;
////                float width = (float) Resources.getSystem().getDisplayMetrics().heightPixels;
//                System.out.println("Click on lesson");
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//
////                try {
////                    Field fld =  itemView.getClass().getField("name");
////                    name1 = fld.toString();
////                } catch (NoSuchFieldException e) {
////                    name1 = "";
////                    throw new RuntimeException(e);
////                }
////                bundle.putString("name", name1);
////                AnimationSet set = new AnimationSet(false);
////                ScaleAnimation scaleAnimation = new ScaleAnimation(width/X, 1, height/Y, 1);
////                scaleAnimation.setDuration(250);
////                scaleAnimation.setFillAfter(true);
////                set.addAnimation(scaleAnimation);
////                View view1 = itemView.findViewById(R.id.timetable_activity);
////                FlingAnimation flingAnimation = new FlingAnimation(view1, DynamicAnimation.SCALE_X);
////                Animation animation = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.open_fragment_anim);
////                Animation animation = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.open_fragment_anim);
////                ScaleAnimation scale = (ScaleAnimation) animation;
////                scale
////                ObjectAnimator moveAnimatorX = ObjectAnimator.ofFloat(itemView, View.X, X);
////                ObjectAnimator moveAnimatorY = ObjectAnimator.ofFloat(itemView, View.Y, Y);
////                ObjectAnimator scaleAnimatorX = ObjectAnimator.ofFloat(itemView, View.SCALE_X, 0.5f);
////                ObjectAnimator scaleAnimatorY = ObjectAnimator.ofFloat(itemView, View.SCALE_Y, 0.5f);
////
////                AnimatorSet animatorSet = new AnimatorSet();
////                animatorSet.playTogether(moveAnimatorX, moveAnimatorY, scaleAnimatorX, scaleAnimatorY);
////                animatorSet.setDuration(500);
////                animatorSet.start();
//                LessonInfoActivity lessonInfoFragment = new LessonInfoActivity();
////
////                LessonInfoTransition transition = new LessonInfoTransition();
////                Fade fade = new Fade();
////
////                lessonInfoFragment.setSharedElementEnterTransition(transition);
////                lessonInfoFragment.setSharedElementReturnTransition(transition);
////
////                lessonInfoFragment.setExitTransition(fade);
////                lessonInfoFragment.setEnterTransition(fade);
////
//                //                transaction.setTransitionStyle(R.anim.open_fragment_anim);
//                // , "timetableFragment"
//                //.addSharedElement(name, "SharedItemView")
//                lessonInfoFragment.setArguments(bundle);
//                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.open_fragment_anim, R.anim.close_fragment_anim, R.anim.open_fragment_anim, R.anim.close_fragment_anim);
//                transaction.replace(R.id.timetable_activity, lessonInfoFragment).addToBackStack("timetableFragment").commit();
//            });
        }
    }
}