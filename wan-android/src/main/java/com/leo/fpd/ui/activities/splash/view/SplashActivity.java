package com.leo.fpd.ui.activities.splash.view;

import android.annotation.SuppressLint;

import com.leo.base.activity.BaseDaggerVvmActivity;
import com.leo.base.dagger.components.BaseAppComponent;
import com.leo.fpd.R;
import com.leo.fpd.app.FpdApplication;
import com.leo.fpd.databinding.ActivitySplashBinding;
import com.leo.fpd.ui.activities.splash.dagger.component.DaggerSplashComponent;
import com.leo.fpd.ui.activities.splash.dagger.module.SplashModule;
import com.leo.fpd.ui.activities.splash.viewModel.SplashViewModel;

import site.gemus.openingstartanimation.OpeningStartAnimation;
import site.gemus.openingstartanimation.RotationDrawStrategy;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends BaseDaggerVvmActivity<ActivitySplashBinding, SplashViewModel> {

    @Override
    protected void componentInject(BaseAppComponent appComponent) {
        DaggerSplashComponent.builder()
                .appComponent(((FpdApplication) application).getAppComponent(appComponent))
                .splashModule(new SplashModule(getLayoutInflater()))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        OpeningStartAnimation openingStartAnimation = new OpeningStartAnimation.Builder(this)
                .setDrawStategy(new RotationDrawStrategy())
                .setColorOfAppIcon(getColor(R.color.purple_700))
                .setAppName("忘记密码")
                .setColorOfAppName(getColor(R.color.purple_500))
                .setAppStatement("让我们彻底忘记密码")
                .setColorOfAppStatement(getColor(R.color.purple_200))
                .setAnimationInterval(2 * 1000)
                .setAnimationFinishTime(500)
                .create();
        openingStartAnimation.show(this);
    }

    @Override
    protected void initListeners() {
        viewModel.openNext(this);
    }

    @Override
    protected void initObserver() {

    }

    @Override
    protected void loadData() {

    }
}