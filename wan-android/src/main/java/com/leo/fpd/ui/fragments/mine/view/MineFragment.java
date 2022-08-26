package com.leo.fpd.ui.fragments.mine.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leo.base.dagger.components.BaseAppComponent;
import com.leo.base.fragment.BaseDaggerVvmFragment;
import com.leo.fpd.app.FpdApplication;
import com.leo.fpd.databinding.FragmentMineBinding;
import com.leo.fpd.ui.fragments.mine.dagger.component.DaggerMineComponent;
import com.leo.fpd.ui.fragments.mine.dagger.module.MineModule;
import com.leo.fpd.ui.fragments.mine.viewModel.MineViewModel;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 14:04
 * Desc:
 */
public class MineFragment extends BaseDaggerVvmFragment<FragmentMineBinding, MineViewModel> {

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected void componentInject(BaseAppComponent appComponent, LayoutInflater inflater, ViewGroup container) {
        DaggerMineComponent.builder()
                .appComponent(((FpdApplication) application).getAppComponent(appComponent))
                .mineModule(new MineModule(inflater, container))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initObserver() {

    }

    @Override
    protected void loadData() {

    }
}
