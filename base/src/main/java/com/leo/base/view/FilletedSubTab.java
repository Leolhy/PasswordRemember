package com.leo.base.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.leo.base.R;
import com.leo.base.entities.FilletedTabItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/17 15:40
 * Desc:
 */
public class FilletedSubTab extends HorizontalScrollView {
    private static final String TAG = "FilletedSubTab";

    private List<FilletedTabItem> tabs = new ArrayList<>();
    private int count;

    private final float borderRadius;
    private final int[] norState = new int[]{-android.R.attr.state_checked};
    private final int[] selState = new int[]{android.R.attr.state_checked};
    private final int colorNor, colorSel, colorBorder;
    private final float borderWidth;
    private final int textSize;
    private final int horizontalPadding, verticalPadding;

    private ColorStateList textColor;
    private RadioGroup group;

    private OnTabSelectChangedListener changedListener;

    public FilletedSubTab(Context context) {
        this(context, null);
    }

    public FilletedSubTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FilletedSubTab);
        colorNor = typedArray.getColor(R.styleable.FilletedSubTab_sub_tab_color_nor, Color.WHITE);
        colorSel = typedArray.getColor(R.styleable.FilletedSubTab_sub_tab_color_sel, Color.BLUE);
        colorBorder = typedArray.getColor(R.styleable.FilletedSubTab_sub_tab_border_color, colorSel);
        borderWidth = typedArray.getDimension(R.styleable.FilletedSubTab_sub_tab_border_width, 2f);
        borderRadius = typedArray.getDimension(R.styleable.FilletedSubTab_sub_tab_border_radius, 6f);
        textSize = typedArray.getDimensionPixelSize(R.styleable.FilletedSubTab_sub_tab_text_size, 18);
        horizontalPadding = typedArray.getDimensionPixelSize(R.styleable.FilletedSubTab_sub_tab_horizontal_padding, 32);
        verticalPadding = typedArray.getDimensionPixelSize(R.styleable.FilletedSubTab_sub_tab_vertical_padding, 8);
        typedArray.recycle();

        initViews();
        setTabColor();

//        tabs.add("Tab1");
//        tabs.add("Tab2");
//        tabs.add("Tab3");
//        count = tabs.size();
//        initTabs();
    }

    private void initViews() {
        setHorizontalScrollBarEnabled(false);
        group = new RadioGroup(getContext());
        FrameLayout.LayoutParams layoutParams = new LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        group.setLayoutParams(layoutParams);
        group.setGravity(Gravity.CENTER_VERTICAL);
        group.setOrientation(LinearLayout.HORIZONTAL);
        addView(group);
        group.setOnCheckedChangeListener((group1, checkedId) -> {
            Log.i(TAG, "initViews: " + checkedId);
            if (changedListener != null) {
                changedListener.onChange(checkedId, tabs.get(checkedId));
            }
        });
    }

    private void setTabColor() {
        int[][] states = {norState, selState};
        int[] colors = {colorSel, colorNor};
        textColor = new ColorStateList(states, colors);
    }

    public void setTabList(List<FilletedTabItem> tabs) {
        this.tabs = tabs;
        count = tabs.size();
        initTabs();
    }

    private void initTabs() {
        if (group.getChildCount() > 0) {
            group.removeAllViews();
        }
        for (int i = 0; i < count; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            String tab = tabs.get(i).getTabName();
            StateListDrawable stateDrawable = buildStateListDrawable(colorNor, colorSel);
            radioButton.setBackground(stateDrawable);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(horizontalPadding / 2, verticalPadding, horizontalPadding / 2, verticalPadding);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
            radioButton.setButtonDrawable(null);
            radioButton.setId(i);
            radioButton.setText(tab);
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            radioButton.setTextColor(textColor);
            radioButton.setChecked(i == 0);
            group.addView(radioButton);
        }
        scrollTo(0, 0);
    }

    private StateListDrawable buildStateListDrawable(int colorNor, int colorSel) {
        GradientDrawable drawableNor = new GradientDrawable();
        drawableNor.setCornerRadius(borderRadius);
        drawableNor.setColor(colorNor);
        drawableNor.setStroke((int) borderWidth, colorBorder);
        GradientDrawable drawableSel = new GradientDrawable();
        drawableSel.setCornerRadius(borderRadius);
        drawableSel.setColor(colorSel);
        drawableSel.setStroke((int) borderWidth, colorBorder);
        StateListDrawable stateDrawable = new StateListDrawable();
        stateDrawable.addState(norState, drawableNor);
        stateDrawable.addState(selState, drawableSel);
        return stateDrawable;
    }

    public void setChangedListener(OnTabSelectChangedListener changedListener) {
        this.changedListener = changedListener;
    }

    public static interface OnTabSelectChangedListener {
        void onChange(int selectIndex, FilletedTabItem selectTab);
    }
}
