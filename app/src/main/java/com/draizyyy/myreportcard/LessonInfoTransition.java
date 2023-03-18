package com.draizyyy.myreportcard;

import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;

public class LessonInfoTransition extends TransitionSet {
    public LessonInfoTransition() {
        setOrdering(ORDERING_TOGETHER);
        addTransition(new ChangeBounds()).
                addTransition(new ChangeTransform());
    }
}
