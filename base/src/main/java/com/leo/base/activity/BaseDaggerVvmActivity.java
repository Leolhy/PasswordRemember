package com.leo.base.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;

import com.jeremyliao.liveeventbus.core.Observable;
import com.leo.base.app.BaseDaggerApplication;
import com.leo.base.dagger.components.BaseAppComponent;
import com.leo.base.entities.event.BaseAppEvent;

import javax.inject.Inject;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/7/29 16:46
 * Desc:
 */
public abstract class BaseDaggerVvmActivity<BD extends ViewBinding, VM extends ViewModel> extends AppCompatActivity {

    protected final static String TAG = "BaseDaggerVvmActivity";

    @Inject
    protected BD binding;

    @Inject
    protected VM viewModel;

    @Inject
    protected Observable<BaseAppEvent> appEventBus;

    protected BaseDaggerApplication application;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        application = (BaseDaggerApplication) getApplication();
        componentInject(application.getBaseAppComponent());
        super.onCreate(savedInstanceState);
        preSetContent();
        setContentView(binding.getRoot());
        initIntentData();
        initViews();
        initListeners();
        initObserver();
        loadData();
    }

    protected abstract void componentInject(BaseAppComponent appComponent);

    protected void preSetContent() {
    }

    protected void initIntentData() {
    }

    protected abstract void initViews();

    protected abstract void initListeners();

    protected abstract void initObserver();

    protected abstract void loadData();

    protected void showToast(String toastStr) {
        Toast.makeText(this, toastStr, Toast.LENGTH_SHORT).show();
    }

    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("请稍候...");
        }
        progressDialog.show();
    }

    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
