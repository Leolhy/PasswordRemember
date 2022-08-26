package com.leo.prb.mvvm.setting.view;

import com.leo.prb.base.activity.BaseDaggerMvvmActivity;
import com.leo.prb.dagger.components.AppComponent;
import com.leo.prb.dagger.components.DaggerSettingComponent;
import com.leo.prb.dagger.modules.SettingModule;
import com.leo.prb.databinding.ActivitySettingBinding;
import com.leo.prb.mvvm.setting.viewModel.SettingViewModel;

public class SettingActivity extends BaseDaggerMvvmActivity<ActivitySettingBinding, SettingViewModel> {

    @Override
    protected void componentInject(AppComponent appComponent) {
        DaggerSettingComponent.builder()
                .appComponent(appComponent)
                .settingModule(new SettingModule(getLayoutInflater()))
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