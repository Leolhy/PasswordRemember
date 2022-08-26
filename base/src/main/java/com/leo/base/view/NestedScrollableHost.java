package com.leo.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/12 10:05
 * Desc:
 */
public class NestedScrollableHost extends FrameLayout {
    private int touchSlop = 0;
    private float initialX = 0f;
    private float initialY = 0f;
    private ViewPager2 parentViewPager;
    private View child;

    public NestedScrollableHost(@NonNull Context context) {
        this(context, null);
    }

    public NestedScrollableHost(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedScrollableHost(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        if (parentViewPager == null || child == null) {
            ViewParent viewParent = getParent();
            if (viewParent instanceof View) {
                View parent = (View) viewParent;
                while (!(parent instanceof ViewPager2)) {
                    ViewParent parentParent = parent.getParent();
                    if (parentParent instanceof View) {
                        parent = (View) parentParent;
                    }
                }
                parentViewPager = (ViewPager2) parent;
            }

            if (getChildCount() > 0) {
                child = getChildAt(0);
            } else child = null;
            touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        }
    }

    private boolean canChildScroll(int orientation, float delta) {
        int direction = (int) Math.signum(-delta);
        switch (orientation) {
            case 0:
                return child.canScrollHorizontally(direction);
            case 1:
                return child.canScrollVertically(direction);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        init();
        handleInterceptTouchEvent(e);
        return super.onInterceptTouchEvent(e);
    }

    private void handleInterceptTouchEvent(MotionEvent e) {
        int orientation = parentViewPager.getOrientation();

        // Early return if child can't scroll in same direction as parent
        if (!canChildScroll(orientation, -1f) && !canChildScroll(orientation, 1f)) {
            return;
        }

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            initialX = e.getX();
            initialY = e.getY();
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (e.getAction() == MotionEvent.ACTION_MOVE) {
            float dx = e.getX() - initialX;
            float dy = e.getY() - initialY;
            boolean isVpHorizontal = orientation == ViewPager2.ORIENTATION_HORIZONTAL;

            // assuming ViewPager2 touch-slop is 2x touch-slop of child
            float scaledDx = dx * (isVpHorizontal ? .5f : 1f);
            float scaledDy = dy * (isVpHorizontal ? 1f : .5f);

            if (scaledDx > touchSlop || scaledDy > touchSlop) {
                if (isVpHorizontal == (scaledDy > scaledDx)) {
                    // Gesture is perpendicular, allow all parents to intercept
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    // Gesture is parallel, query child if movement in that direction is possible
                    if (canChildScroll(orientation, isVpHorizontal ? dx : dy)) {
                        // Child can scroll, disallow all parents to intercept
                        getParent().requestDisallowInterceptTouchEvent(true);
                    } else {
                        // Child cannot scroll, allow all parents to intercept
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
            }
        }
    }

}
