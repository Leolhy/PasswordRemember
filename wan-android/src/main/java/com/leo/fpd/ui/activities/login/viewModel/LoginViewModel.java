package com.leo.fpd.ui.activities.login.viewModel;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;

import com.leo.base.entities.ToastEntity;
import com.leo.base.http.EntityResponse;
import com.leo.base.tools.AppTools;
import com.leo.base.viewModel.BaseViewModel;
import com.leo.fpd.entities.RememberAccountEntity;
import com.leo.fpd.entities.UserEntity;
import com.leo.fpd.repository.RememberAccountRepository;
import com.leo.fpd.repository.UserRepository;
import com.leo.fpd.ui.activities.main.view.MainActivity;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 16:13
 * Desc:
 */
public class LoginViewModel extends BaseViewModel {

    private final UserRepository userRepository;

    private final RememberAccountRepository accountRepository;

    public MutableLiveData<UserEntity> userEntityData = new MutableLiveData<>();

    @Inject
    public LoginViewModel(UserRepository userRepository, RememberAccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public void login(String username, String password) {
        if (checkLoginData(username, password)) return;
        userRepository.getLoginApi()
                .loginIn(username, password)
                .subscribe(new Observer<EntityResponse<UserEntity>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull EntityResponse<UserEntity> response) {
                        int code = response.getErrorCode();
                        if (code == 0) {
                            toastData.setValue(new ToastEntity("登录成功"));
                            RememberAccountEntity rememberAccount = new RememberAccountEntity();
                            rememberAccount.setAccount(username);
                            rememberAccount.setPassword(password);
                            rememberAccount.setLoginTime(System.currentTimeMillis());
                            rememberAccount.getUserEntity().setTarget(response.getData());
                            accountRepository.getRememberAccountBox().removeAll();
                            long put = accountRepository.getRememberAccountBox().put(rememberAccount);
                            if (put != -1) userEntityData.setValue(response.getData());
                        } else {
                            toastResponse(response);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        toastThrowable(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void loginSuccess(UserEntity userEntity) {

    }

    public void openMain(Activity activity) {
        AppTools.openActivity(activity, MainActivity.class);
        activity.finish();
    }

    private boolean checkLoginData(String username, String password) {
        if (username.trim().length() == 0) {
            ToastEntity toastEntity = new ToastEntity("请输入用户名或账号!");
            toastData.setValue(toastEntity);
            return true;
        }
        if (password.trim().length() == 0) {
            ToastEntity toastEntity = new ToastEntity("请输入密码!");
            toastData.setValue(toastEntity);
            return true;
        }
        return false;
    }


}
