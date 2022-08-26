package com.leo.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import com.sivin.Banner;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/11 17:32
 * Desc:
 */
public class BannerContainer extends Banner {
    private ViewPager mViewPager;
    private boolean disallowParentInterceptDownEvent = true;
    private int startX = 0;
    private int startY = 0;


    public BannerContainer(Context context) {
        this(context, null);
    }

    public BannerContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerContainer(Context context, AttributeSet attrs, int defStyleAttr) {
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
        boolean doNotNeedIntercept = (mViewPager.getAdapter() != null
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
     * 设置是否允许在当前View的{@link MotionEvent#ACTION_DOWN}事件中禁止父View对事件的拦截，该方法
     * 用于解决CoordinatorLayout+CollapsingToolbarLayout在嵌套ViewPager2Container时引起的滑动冲突问题。
     * <p>
     * 设置是否允许在ViewPager2Container的{@link MotionEvent#ACTION_DOWN}事件中禁止父View对事件的拦截，该方法
     * 用于解决CoordinatorLayout+CollapsingToolbarLayout在嵌套ViewPager2Container时引起的滑动冲突问题。
     *
     * @param disallowParentInterceptDownEvent 是否允许ViewPager2Container在{@link MotionEvent#ACTION_DOWN}事件中禁止父View拦截事件，默认值为false
     *                                         true 不允许ViewPager2Container在{@link MotionEvent#ACTION_DOWN}时间中禁止父View的时间拦截，
     *                                         设置disallowIntercept为true可以解决CoordinatorLayout+CollapsingToolbarLayout的滑动冲突
     *                                         false 允许ViewPager2Container在{@link MotionEvent#ACTION_DOWN}时间中禁止父View的时间拦截，
     */
    public void disallowParentInterceptDownEvent(boolean disallowParentInterceptDownEvent) {
        this.disallowParentInterceptDownEvent = disallowParentInterceptDownEvent;
    }

}
