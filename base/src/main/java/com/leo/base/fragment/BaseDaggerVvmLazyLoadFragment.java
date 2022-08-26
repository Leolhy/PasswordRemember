package com.leo.base.fragment;

import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 15:41
 * Desc:
 */
public abstract class BaseDaggerVvmLazyLoadFragment<BD extends ViewBinding, VM extends ViewModel> extends BaseDaggerVvmFragment<BD, VM> {

    /**
     * 视图是否已经初初始化
     */
    protected boolean isInit = false;
    protected boolean isLoad = false;
    protected boolean isVisible = false;

    @Override
    protected void loadData() {
        isInit = true;
        isLoad = false;
        //初始化的时候去加载数据
        isCanLoadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            isCanLoadData();
        } else {
            isVisible = false;
        }
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }
        if (isVisible && !isLoad) {
            lazyLoad();
            isLoad = true;
        } else {
            if (isLoad) {
                stopLoad();
            }
        }
    }

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    protected abstract void lazyLoad();

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
    protected void stopLoad() {
    }
}
