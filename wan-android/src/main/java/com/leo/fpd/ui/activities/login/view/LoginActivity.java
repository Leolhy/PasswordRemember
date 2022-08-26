package com.leo.fpd.ui.activities.login.view;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.leo.base.activity.BaseDaggerVvmActivity;
import com.leo.base.dagger.components.BaseAppComponent;
import com.leo.base.tools.AfterTextWatcher;
import com.leo.fpd.app.FpdApplication;
import com.leo.fpd.databinding.ActivityLoginBinding;
import com.leo.fpd.ui.activities.login.dagger.component.DaggerLoginComponent;
import com.leo.fpd.ui.activities.login.dagger.module.LoginModule;
import com.leo.fpd.ui.activities.login.viewModel.LoginViewModel;

public class LoginActivity extends BaseDaggerVvmActivity<ActivityLoginBinding, LoginViewModel> {

    private String account;
    private String password;

    @Override
    protected void componentInject(BaseAppComponent appComponent) {
        DaggerLoginComponent.builder()
                .appComponent(((FpdApplication) application).getAppComponent(appComponent))
                .loginModule(new LoginModule(getLayoutInflater()))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        binding.loginTvAccount.addTextChangedListener(new AfterTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                account = s.toString();
            }
        });
        binding.loginTvPassword.addTextChangedListener(new AfterTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
            }
        });
        binding.loginBtn.setOnClickListener(v -> doLogin());
    }

    private void doLogin() {
        View currentFocus = getCurrentFocus();
        if (currentFocus instanceof EditText) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
        if (binding.loginPasswordLayout.isErrorEnabled()) {
            showToast("密码长度错误!");
            return;
        }
        viewModel.login(account, password);
        showLoading();
    }

    @Override
    protected void initObserver() {
        viewModel.userEntityData.observe(this, userEntity -> viewModel.openMain(this));
        viewModel.toastData.observe(this, toastEntity -> {
            if (toastEntity.isHideLoading()) {
                hideLoading();
            }
            showToast(toastEntity.getToastContent());
        });
    }

    @Override
    protected void loadData() {

    }

}