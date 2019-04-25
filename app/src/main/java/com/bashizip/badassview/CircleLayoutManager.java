package com.bashizip.badassview;

import android.content.Context;

import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

public class CircleLayoutManager extends TurnLayoutManager {

    public CircleLayoutManager(Context context, int gravity, int orientation, int radius, int peekDistance, boolean rotate) {
        super(context, gravity, orientation, radius, peekDistance, rotate);
    }

    public CircleLayoutManager(Context context, int radius, int peekDistance) {
        super(context, radius, peekDistance);
    }


    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        RecyclerView.SmoothScroller smoothScroller = new CenterSmoothScroller(recyclerView.getContext());
        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }

    private static class CenterSmoothScroller extends LinearSmoothScroller {

        CenterSmoothScroller(Context context) {
            super(context);
        }

        @Override
        public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
            return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
        }
    }
}
