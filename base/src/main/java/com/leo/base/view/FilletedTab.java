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
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.leo.base.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/17 15:15
 * Desc:
 */
public class FilletedTab extends RadioGroup {
    private static final String TAG = "FilletedTab";
    private static final int WARP = 100;
    private static final int FILL = 101;

    private List<String> tabs = new ArrayList<>();
    private int count;

    private OnTabSelectChangedListener changedListener;
    private int selectIndex = 0;
    private boolean canSwitchTab = true;

    private final float borderRadius;
    private final int[] norState = new int[]{-android.R.attr.state_checked, android.R.attr.state_enabled};
    private final int[] selState = new int[]{android.R.attr.state_checked, android.R.attr.state_enabled};
    private final int[] disableState = new int[]{-android.R.attr.state_enabled};
    private ColorStateList textColor;
    private final int colorNor, colorSel, colorDisable, colorBorder;
    private final float borderWidth;
    private final int textSize;
    private final int horizontalPadding, verticalPadding;
    private final int mode;

    public FilletedTab(Context context) {
        this(context, null);
    }

    public FilletedTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FilletedTab);
        colorNor = typedArray.getColor(R.styleable.FilletedTab_tab_color_nor, Color.WHITE);
        colorSel = typedArray.getColor(R.styleable.FilletedTab_tab_color_sel, Color.BLUE);
        colorDisable = typedArray.getColor(R.styleable.FilletedTab_tab_color_disable, Color.parseColor("#44000000"));
        colorBorder = typedArray.getColor(R.styleable.FilletedTab_tab_border_color, colorSel);
        borderWidth = typedArray.getDimension(R.styleable.FilletedTab_tab_border_width, 0);
        borderRadius = typedArray.getDimension(R.styleable.FilletedTab_tab_border_radius, 0);
        textSize = typedArray.getDimensionPixelSize(R.styleable.FilletedTab_tab_text_size, 18);
        horizontalPadding = typedArray.getDimensionPixelSize(R.styleable.FilletedTab_tab_horizontal_padding, 32);
        verticalPadding = typedArray.getDimensionPixelSize(R.styleable.FilletedTab_tab_vertical_padding, 16);
        mode = typedArray.getInt(R.styleable.FilletedTab_tab_mode, WARP);
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
        setGravity(Gravity.CENTER_VERTICAL);
        setOrientation(LinearLayout.HORIZONTAL);
        setOnCheckedChangeListener((group, checkedId) -> {
            Log.i(TAG, "initViews: " + checkedId);
            if (changedListener != null) {
                changedListener.onChange(checkedId, tabs.get(checkedId));
            }
        });
    }

    private void initTabs() {
        if (getChildCount() > 0) {
            removeAllViews();
        }
        float[] leftRadii = new float[]{borderRadius - borderWidth, borderRadius - borderWidth, 0, 0, 0, 0, borderRadius - borderWidth, borderRadius - borderWidth};
        float[] centerRadii = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
        float[] rightRadii = new float[]{0, 0, borderRadius - borderWidth, borderRadius - borderWidth, borderRadius - borderWidth, borderRadius - borderWidth, 0, 0};

        for (int i = 0; i < count; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            String tab = tabs.get(i);
            if (i == 0) {
                StateListDrawable leftStateDrawable = buildStateListDrawable(colorNor, colorSel, leftRadii);
                radioButton.setBackground(leftStateDrawable);
            } else if (i == count - 1) {
                StateListDrawable rightStateDrawable = buildStateListDrawable(colorNor, colorSel, rightRadii);
                radioButton.setBackground(rightStateDrawable);
            } else {
                StateListDrawable centerStateDrawable = buildStateListDrawable(colorNor, colorSel, centerRadii);
                radioButton.setBackground(centerStateDrawable);
            }
            LayoutParams layoutParams1;
            if (mode == FILL) {
                layoutParams1 = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            } else {
                layoutParams1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            }
            radioButton.setLayoutParams(layoutParams1);
            radioButton.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
            radioButton.setButtonDrawable(null);
            radioButton.setId(i);
            radioButton.setText(tab);
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            radioButton.setTextColor(textColor);
            if (mode == FILL) {
                radioButton.setGravity(Gravity.CENTER);
            }
            addView(radioButton);
            if (i != (count - 1)) {
                View view = new View(getContext());
                LayoutParams layoutParams = new LayoutParams(dip2px(getContext(), 0.8f), dip2px(getContext(), 25));
                view.setLayoutParams(layoutParams);
                view.setBackgroundColor(Color.LTGRAY);
                addView(view);
            }
        }
        RadioButton childAt = (RadioButton) getChildAt(selectIndex);
        childAt.setChecked(true);
    }

    public void setTabList(List<String> tabs) {
        this.tabs = tabs;
        this.count = tabs.size();
        if (count < 2)
            throw new RuntimeException("tab count must be >= 2!");
        initTabs();
    }

    private void setTabColor() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(colorNor);
        gradientDrawable.setCornerRadius(borderRadius);
        gradientDrawable.setStroke((int) borderWidth, colorBorder);

        setBackground(gradientDrawable);
        int padding = (int) borderWidth;
        setPadding(padding, padding, padding, padding);

        int[][] states = {norState, selState, disableState};
        int[] colors = {colorSel, colorNor, colorDisable};
        textColor = new ColorStateList(states, colors);
    }

    private StateListDrawable buildStateListDrawable(int norColor, int selColor, @Nullable float[] radii) {
        GradientDrawable drawableNor = new GradientDrawable();
        drawableNor.setCornerRadii(radii);
        drawableNor.setColor(norColor);
        GradientDrawable drawableSel = new GradientDrawable();
        drawableSel.setCornerRadii(radii);
        drawableSel.setColor(selColor);

        StateListDrawable stateDrawable = new StateListDrawable();
        stateDrawable.addState(norState, drawableNor);
        stateDrawable.addState(selState, drawableSel);

        return stateDrawable;
    }


    public void setOnTabSelectChangedListener(OnTabSelectChangedListener changedListener) {
        this.changedListener = changedListener;
    }

    public void setSelectIndex(int index) {
        RadioButton viewById = findViewById(index);
        viewById.setChecked(true);
        selectIndex = index;
        setCanSwitchType(canSwitchTab);
    }

    /**
     * Dip into pixels
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setCanSwitchType(boolean canSwitchType) {
        canSwitchTab = canSwitchType;
        for (int i = 0; i < count; i++) {
            RadioButton viewById = findViewById(i);
            if (selectIndex != i) {
                viewById.setEnabled(canSwitchType);
            } else {
                viewById.setEnabled(true);
            }
        }
    }

    public interface OnTabSelectChangedListener {
        void onChange(int selectIndex, String selectTab);
    }
}
