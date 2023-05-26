package com.draizyyy.myreportcard.adapters;

import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.draizyyy.myreportcard.R;
import com.draizyyy.myreportcard.fragments.LessonInfoActivity;
import com.draizyyy.myreportcard.pojos.Lesson;

import java.util.List;

public class MyLessonAdapter extends RecyclerView.Adapter<MyLessonAdapter.MyViewHolder> {
    private final List<Lesson> list;
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

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Lesson lesson = list.get(position);
        final View view = holder.view;
        String classroom;
        view.setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putString("name", lesson.name);
            bundle.putString("address", lesson.address);
            bundle.putString("schoolName", lesson.schoolName);
            bundle.putString("homework", lesson.homework);
            bundle.putString("classroom", lesson.classroom);
            bundle.putString("teacher", lesson.teacher);
            AppCompatActivity activity = (AppCompatActivity) view1.getContext();
            LessonInfoActivity lessonInfoFragment = new LessonInfoActivity();
            lessonInfoFragment.setArguments(bundle);
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.open_fragment_anim, R.anim.close_fragment_anim, R.anim.open_fragment_anim, R.anim.close_fragment_anim);
            transaction.replace(R.id.timetable_activity, lessonInfoFragment).addToBackStack("timetableFragment").commit();
        });
        holder.name.setText(lesson.getName());
        holder.start_time.setText(String.valueOf(lesson.getStart_time()));
        holder.finish_time.setText(lesson.getFinish_time());
        if (lesson.classroom != null && !lesson.classroom.equals("") && !lesson.classroom.equals(" ")) {
            classroom = "каб. " + lesson.classroom;
        }
        else {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.name.getLayoutParams();
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            classroom = lesson.classroom;
            holder.classroom.setVisibility(View.GONE);
        }
        holder.classroom.setText(classroom);
        holder.grade.setText(lesson.getGrade());
//        Log.v("MY APP", "запрашиваемый номер элемента: " + (bundleList.size() - 1) + ", всего элементов в листе:  " + bundleList.size());
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
        private final TextView classroom;
        private final View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.name_of_lesson);
            start_time = itemView.findViewById(R.id.start_time_of_lesson);
            finish_time = itemView.findViewById(R.id.finish_time_of_lesson);
            classroom = itemView.findViewById(R.id.number_of_class);
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