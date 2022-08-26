package com.leo.fpd.ui.activities.splash.viewModel;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import com.leo.base.entities.ToastEntity;
import com.leo.base.tools.AppTools;
import com.leo.base.viewModel.BaseViewModel;
import com.leo.fpd.entities.RememberAccountEntity;
import com.leo.fpd.entities.RememberAccountEntity_;
import com.leo.fpd.repository.RememberAccountRepository;
import com.leo.fpd.ui.activities.login.view.LoginActivity;
import com.leo.fpd.ui.activities.main.view.MainActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/8 11:24
 * Desc:
 */
public class SplashViewModel extends BaseViewModel {

    private final RememberAccountRepository rememberAccount;

    @Inject
    public SplashViewModel(RememberAccountRepository rememberAccount) {
        this.rememberAccount = rememberAccount;
    }

    public void openNext(Activity activity) {
        List<RememberAccountEntity> accountEntityList = rememberAccount.getRememberAccountBox().query()
                .orderDesc(RememberAccountEntity_.loginTime)
                .build()
                .find();
        if (accountEntityList.size() != 0) {
            RememberAccountEntity accountEntity = accountEntityList.get(0);
            Long loginTime = accountEntity.getLoginTime();
            if ((loginTime + 7 * 24 * 60 * 60 * 1000) > System.currentTimeMillis()) {
                openMain(activity);
            } else {
                toastData.setValue(new ToastEntity("登录信息已过期,请重新登录!"));
                openLogin(activity);
            }
        } else {
            openLogin(activity);
        }
    }

    public void openMain(Activity activity) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            AppTools.openActivity(activity, MainActivity.class);
            activity.finish();
        }, (long) (2.5 * 1000));
    }

    public void openLogin(Activity activity) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            AppTools.openActivity(activity, LoginActivity.class);
            activity.finish();
        }, (long) (2.5 * 1000));
    }


}
