package com.leo.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/11 17:32
 * Desc:
 */
public class ViewPagerContainer extends RelativeLayout {
    private ViewPager mViewPager;
    private boolean disallowParentInterceptDownEvent = true;
    private int startX = 0;
    private int startY = 0;


    public ViewPagerContainer(Context context) {
        this(context, null);
    }

    public ViewPagerContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (childView instanceof ViewPager) {
                mViewPager = (ViewPager) childView;
                break;
            }
        }
        if (mViewPager == null) {
            throw new IllegalStateException("The root child of ViewPagerContainer must contains a ViewPager");
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean doNotNeedIntercept = !mViewPager.isFakeDragging()
                || (mViewPager.getAdapter() != null
                && mViewPager.getAdapter().getCount() <= 1);
        if (doNotNeedIntercept) {
            return super.onInterceptTouchEvent(ev);
        } else {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) ev.getX();
                    startY = (int) ev.getY();
                    getParent().requestDisallowInterceptTouchEvent(!disallowParentInterceptDownEvent);
                    break;
                case MotionEvent.ACTION_MOVE:
                    float endX = ev.getX();
                    float endY = ev.getY();
                    float disX = Math.abs(endX - startX);
                    float disY = Math.abs(endY - startY);
                    onHorizontalActionMove(endX, disX, disY);
                    break;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    private void onHorizontalActionMove(float endX, float disX, float disY) {
        if (mViewPager.getAdapter() == null) {
            return;
        }
        if (disX > disY) {
            int currentItem = mViewPager.getCurrentItem();
            int itemCount = mViewPager.getAdapter().getCount();
            if (currentItem == 0 && endX - startX > 0) {
                getParent().requestDisallowInterceptTouchEvent(false);
            } else {
                getParent().requestDisallowInterceptTouchEvent(currentItem != itemCount - 1 || endX - startX >= 0);
            }
        } else if (disY > disX) {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
    }

    /**
     * ???????????????????????????View???{@link MotionEvent#ACTION_DOWN}??????????????????View??????????????????????????????
     * ????????????CoordinatorLayout+CollapsingToolbarLayout?????????ViewPager2Container?????????????????????????????????
     * <p>
     * ?????????????????????ViewPager2Container???{@link MotionEvent#ACTION_DOWN}??????????????????View??????????????????????????????
     * ????????????CoordinatorLayout+CollapsingToolbarLayout?????????ViewPager2Container?????????????????????????????????
     *
     * @param disallowParentInterceptDownEvent ????????????ViewPager2Container???{@link MotionEvent#ACTION_DOWN}??????????????????View???????????????????????????false
     *                                         true ?????????ViewPager2Container???{@link MotionEvent#ACTION_DOWN}??????????????????View??????????????????
     *                                         ??????disallowIntercept???true????????????CoordinatorLayout+CollapsingToolbarLayout???????????????
     *                                         false ??????ViewPager2Container???{@link MotionEvent#ACTION_DOWN}??????????????????View??????????????????
     */
    public void disallowParentInterceptDownEvent(boolean disallowParentInterceptDownEvent) {
        this.disallowParentInterceptDownEvent = disallowParentInterceptDownEvent;
    }

}
