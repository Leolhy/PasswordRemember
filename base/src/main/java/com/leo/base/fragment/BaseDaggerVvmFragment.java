package com.leo.base.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;

import com.jeremyliao.liveeventbus.core.Observable;
import com.leo.base.app.BaseDaggerApplication;
import com.leo.base.dagger.components.BaseAppComponent;
import com.leo.base.entities.event.BaseAppEvent;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 11:39
 * Desc:
 */
public abstract class BaseDaggerVvmFragment<BD extends ViewBinding, VM extends ViewModel> extends Fragment {

    protected final static String TAG = "BaseDaggerVvmFragment";

    @Inject
    protected BD binding;

    @Inject
    protected VM viewModel;

    @Inject
    protected Observable<BaseAppEvent> appEventBus;

    protected BaseDaggerApplication application;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        application = (BaseDaggerApplication) getActivity().getApplication();
        componentInject(application.getBaseAppComponent(), inflater, container);
        preSetContent();
        initArgument();
        initViews();
        initListeners();
        initObserver();
        loadData();
        return binding.getRoot();
    }

    protected abstract void componentInject(BaseAppComponent appComponent, LayoutInflater inflater, ViewGroup container);

    protected void preSetContent() {
    }

    protected void initArgument() {
    }

    protected abstract void initViews();

    protected abstract void initListeners();

    protected abstract void initObserver();

    protected abstract void loadData();

    protected void showToast(String toastStr) {
        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
    }

    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("请稍候...");
        }
        progressDialog.show();
    }

    public void hideLoading() {
        progressDialog.dismiss();
    }

}
