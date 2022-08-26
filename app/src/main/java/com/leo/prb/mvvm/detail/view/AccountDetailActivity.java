package com.leo.prb.mvvm.detail.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.leo.prb.base.activity.BaseDaggerMvvmActivity;
import com.leo.prb.dagger.components.AppComponent;
import com.leo.prb.dagger.components.DaggerDetailComponent;
import com.leo.prb.dagger.modules.DetailModule;
import com.leo.prb.databinding.ActivityAccountDetailBinding;
import com.leo.prb.entities.AccountEntity;
import com.leo.prb.entities.AccountJsonEntity;
import com.leo.prb.entities.AccountTypeEntity;
import com.leo.prb.event.AccountEvent;
import com.leo.prb.event.QRCodeEvent;
import com.leo.prb.event.RefreshEvent;
import com.leo.prb.mvvm.detail.adapter.AccountTypeAdapter;
import com.leo.prb.mvvm.detail.viewModel.DetailViewModel;
import com.leo.prb.mvvm.main.view.MainActivity;
import com.leo.prb.mvvm.scan.view.ScanActivity;
import com.leo.prb.mvvm.type.view.AccountTypeAddActivity;
import com.leo.prb.tools.AfterTextWatcher;

import javax.inject.Inject;

public class AccountDetailActivity extends BaseDaggerMvvmActivity<ActivityAccountDetailBinding, DetailViewModel> {
    private static final String ACCOUNT_ENTITY_KEY = "accountEntity";
    private String account = "", password = "", desc = "";
    private AccountTypeEntity accountType = null;

    @Inject
    AccountTypeAdapter adapter;

    @Inject
    Gson gson;

    private AccountEntity accountEntity;

    public static void open(Context context) {
        Intent openIntent = new Intent(context, AccountDetailActivity.class);
        context.startActivity(openIntent);
    }

    public static void open(Context context, AccountEntity accountEntity) {
        Intent openIntent = new Intent(context, AccountDetailActivity.class);
        openIntent.putExtra(ACCOUNT_ENTITY_KEY, accountEntity);
        context.startActivity(openIntent);
    }

    @Override
    protected void componentInject(AppComponent appComponent) {
        DaggerDetailComponent.builder()
                .appComponent(appComponent)
                .detailModule(new DetailModule(getLayoutInflater()))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("新增账号");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        binding.addTypeSp.setAdapter(adapter);
    }

    private void initViewData(AccountEntity accountEntity) {
        binding.addAccountEt.setText(accountEntity.getAccount());
        binding.addPasswordEt.setText(accountEntity.getPassword());
        binding.addDescEt.setText(accountEntity.getDesc());
        binding.addBtnSave.setText("保存");
        getSupportActionBar().setTitle("修改账号");
        accountType = accountEntity.getAccountType().getTarget();
        initSelection();
    }

    @Override
    protected void initListeners() {
        binding.addAccountEt.addTextChangedListener(new AfterTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                account = s.toString();
            }
        });
        binding.addPasswordEt.addTextChangedListener(new AfterTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
            }
        });
        binding.addDescEt.addTextChangedListener(new AfterTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                desc = s.toString();
            }
        });
        binding.addTypeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                accountType = adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.addBtnSave.setOnClickListener(v -> {
            AccountEntity accountEntity = checkInput();
            if (accountEntity == null) {
                return;
            }
            viewModel.saveAccount(accountEntity);
        });
        binding.itemAccountTypeAdd.setOnClickListener(v -> AccountTypeAddActivity.open(this));
        binding.detailScanFab.setOnClickListener(v -> ScanActivity.open(this));
    }

    private AccountEntity checkInput() {
        if (TextUtils.isEmpty(account)) {
            showToast("请输入账号/用户名");
            return null;
        }
        if (TextUtils.isEmpty(password)) {
            showToast("请输入密码");
            return null;
        }
        AccountEntity accountEntity;
        if (this.accountEntity != null) {
            accountEntity = this.accountEntity;
        } else {
            accountEntity = new AccountEntity();
        }
        accountEntity.setAccount(account);
        accountEntity.setPassword(password);
        accountEntity.setDesc(desc);
        accountEntity.getAccountType().setTarget(accountType);
        return accountEntity;
    }

    @Override
    protected void initObserver() {
        viewModel.accountTypeLiveData.observe(this, accountTypeEntities -> {
            adapter.setAccountTypeEntities(accountTypeEntities);
            initSelection();
        });
        viewModel.saveResponse.observe(this, saveResponse -> {
            if (saveResponse) {
                showToast("保存成功");
                finish();
                appEventBus.postOrderly(new RefreshEvent(MainActivity.class.getSimpleName()));
            }
        });
        appEventBus.observe(this, appEvent -> {
            if (appEvent instanceof RefreshEvent) {
                RefreshEvent refreshEvent = (RefreshEvent) appEvent;
                if (this.getClass().getSimpleName().equals(refreshEvent.getTarget())) {
                    viewModel.loadAccountTypeList();
                }
            } else if (appEvent instanceof AccountEvent) {
                AccountEvent accountEvent = (AccountEvent) appEvent;
                accountEntity = accountEvent.getAccountEntity();
                initViewData(accountEvent.getAccountEntity());
            } else if (appEvent instanceof QRCodeEvent) {
                QRCodeEvent qrCodeEvent = (QRCodeEvent) appEvent;
                try {
                    AccountJsonEntity accountJsonEntity = gson.fromJson(qrCodeEvent.getQrcodeResult(), AccountJsonEntity.class);
                    if (accountJsonEntity != null) {
                        new AlertDialog.Builder(this)
                                .setTitle("账号导入")
                                .setMessage("确定导入账号【" + accountJsonEntity.getAccount() + "】?")
                                .setNegativeButton("取消导入", (dialog, which) -> dialog.dismiss())
                                .setPositiveButton("立即导入", (dialog, which) -> {
                                    AccountEntity accountEntity = new AccountEntity(accountJsonEntity);
                                    AccountTypeEntity typeByGuid = viewModel.getTypeByGuid(accountJsonEntity.getAccountType());
                                    viewModel.loadAccountTypeList();
                                    accountEntity.getAccountType().setTarget(typeByGuid);
                                    initViewData(accountEntity);
                                    showToast("导入成功");
                                    dialog.dismiss();
                                })
                                .create()
                                .show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    showToast("不支持的扫描结果");
                }
            }
        });
    }

    private void initSelection() {
        if (accountType != null) {
            int selectedIndex = -1;
            for (int i = 0; i < adapter.getCount(); i++) {
                if (accountType.getId().equals(adapter.getItem(i).getId())) {
                    selectedIndex = i;
                    break;
                }
            }
            if (selectedIndex != -1) {
                binding.addTypeSp.setSelection(selectedIndex);
            }
        }
    }

    @Override
    protected void loadData() {
        viewModel.loadAccountTypeList();
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