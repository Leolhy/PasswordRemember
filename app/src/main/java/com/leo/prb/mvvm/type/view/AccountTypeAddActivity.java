package com.leo.prb.mvvm.type.view;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.leo.prb.base.activity.BaseDaggerMvvmActivity;
import com.leo.prb.dagger.components.AppComponent;
import com.leo.prb.dagger.components.DaggerTypeComponent;
import com.leo.prb.dagger.modules.TypeModule;
import com.leo.prb.databinding.ActivityAccountTypeAddBinding;
import com.leo.prb.entities.AccountTypeEntity;
import com.leo.prb.event.RefreshEvent;
import com.leo.prb.mvvm.detail.view.AccountDetailActivity;
import com.leo.prb.mvvm.type.viewModel.TypeViewModel;
import com.leo.prb.tools.AfterTextWatcher;

import java.util.UUID;

public class AccountTypeAddActivity extends BaseDaggerMvvmActivity<ActivityAccountTypeAddBinding, TypeViewModel> {

    private String name = "", simpleName = "";

    public static void open(Context context) {
        Intent openIntent = new Intent(context, AccountTypeAddActivity.class);
        context.startActivity(openIntent);
    }

    @Override
    protected void componentInject(AppComponent appComponent) {
        DaggerTypeComponent.builder()
                .appComponent(appComponent)
                .typeModule(new TypeModule(getLayoutInflater()))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("新增账号类型");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void initListeners() {
        binding.addTypeNameEt.addTextChangedListener(new AfterTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                name = s.toString();
                if (name.length() < 3) {
                    binding.addTypeSimpleNameEt.setText(name);
                } else {
                    binding.addTypeSimpleNameEt.setText(name.substring(0, 3));
                }
            }
        });
        binding.addTypeSimpleNameEt.addTextChangedListener(new AfterTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                simpleName = s.toString();
            }
        });
        binding.addBtnSave.setOnClickListener(v -> {
            AccountTypeEntity typeEntity = checkInput();
            if (typeEntity == null) {
                return;
            }
            viewModel.saveAccountType(typeEntity);
        });
    }

    private AccountTypeEntity checkInput() {
        if (TextUtils.isEmpty(name)) {
            showToast("请输入类型名称");
            return null;
        }
        if (TextUtils.isEmpty(simpleName)) {
            showToast("请输入类型简称");
            return null;
        }
        AccountTypeEntity typeEntity = new AccountTypeEntity();
        typeEntity.setGuid(UUID.randomUUID().toString());
        typeEntity.setName(name);
        typeEntity.setSimpleName(simpleName);
        return typeEntity;
    }

    @Override
    protected void initObserver() {
        viewModel.saveResponse.observe(this, saveResponse -> {
            if (saveResponse) {
                showToast("添加成功");
                finish();
                appEventBus.postOrderly(new RefreshEvent(AccountDetailActivity.class.getSimpleName()));
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}