package com.leo.prb.base.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;

import com.jeremyliao.liveeventbus.core.Observable;
import com.leo.prb.app.PrbApplication;
import com.leo.prb.dagger.components.AppComponent;
import com.leo.prb.event.AppEvent;

import javax.inject.Inject;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/16 11:31
 * Desc:
 */
public abstract class BaseDaggerMvvmActivity<BD extends ViewBinding, VM extends ViewModel> extends AppCompatActivity {

    @Inject
    protected BD binding;

    @Inject
    protected VM viewModel;

    @Inject
    protected Observable<AppEvent> appEventBus;

    private PrbApplication application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        application = (PrbApplication) getApplication();
        componentInject(application.getAppComponent());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        initIntentData();
        initViews();
        initListeners();
        initObserver();
        loadData();
    }

    protected abstract void componentInject(AppComponent appComponent);

    protected void initIntentData() {
    }

    protected abstract void initViews();

    protected abstract void initListeners();

    protected abstract void initObserver();

    protected abstract void loadData();

    protected void showToast(String toastStr) {
        Toast.makeText(this, toastStr, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}