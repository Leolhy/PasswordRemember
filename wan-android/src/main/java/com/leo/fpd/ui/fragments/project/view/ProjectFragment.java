package com.leo.fpd.ui.fragments.project.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leo.base.dagger.components.BaseAppComponent;
import com.leo.base.fragment.BaseDaggerVvmFragment;
import com.leo.fpd.app.FpdApplication;
import com.leo.fpd.databinding.FragmentProjectBinding;
import com.leo.fpd.ui.fragments.project.dagger.component.DaggerProjectComponent;
import com.leo.fpd.ui.fragments.project.dagger.module.ProjectModule;
import com.leo.fpd.ui.fragments.system.viewModel.SystemViewModel;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 14:28
 * Desc:
 */
public class ProjectFragment extends BaseDaggerVvmFragment<FragmentProjectBinding, SystemViewModel> {

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Override
    protected void componentInject(BaseAppComponent appComponent, LayoutInflater inflater, ViewGroup container) {
        DaggerProjectComponent.builder()
                .appComponent(((FpdApplication) application).getAppComponent(appComponent))
                .projectModule(new ProjectModule(inflater, container))
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
