package com.leo.prb.mvvm.main.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.leo.prb.R;
import com.leo.prb.base.activity.BaseDaggerMvvmActivity;
import com.leo.prb.dagger.components.AppComponent;
import com.leo.prb.dagger.components.DaggerMainComponent;
import com.leo.prb.dagger.modules.MainModule;
import com.leo.prb.databinding.ActivityMainBinding;
import com.leo.prb.entities.AccountEntity;
import com.leo.prb.entities.AccountJsonEntity;
import com.leo.prb.event.AccountEvent;
import com.leo.prb.mvvm.detail.view.AccountDetailActivity;
import com.leo.prb.mvvm.main.adapter.AccountPagedAdapter;
import com.leo.prb.mvvm.main.viewModel.MainViewModel;
import com.leo.prb.tools.QRCodeTools;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseDaggerMvvmActivity<ActivityMainBinding, MainViewModel> {

    @Inject
    AccountPagedAdapter pagedAdapter;

    @Inject
    Gson gson;

    @Inject
    QRCodeTools qrCodeTools;
    private int windowWidth;

    @Override
    protected void componentInject(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(getLayoutInflater()))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        binding.mainAddFab.setImageResource(R.mipmap.icon_add);
        binding.mainSwipe.setColorSchemeColors(
                getColor(R.color.purple_200),
                getColor(R.color.purple_500),
                getColor(R.color.purple_700));
        binding.mainSwipe.setEnabled(false);
        binding.mainRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.mainRv.setAdapter(pagedAdapter);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowWidth = wm.getDefaultDisplay().getWidth();
    }

    @Override
    protected void initListeners() {
        binding.mainAddFab.setOnClickListener(v -> AccountDetailActivity.open(this));
        pagedAdapter.setOnPagedItemClickListener(position -> {
            AccountEntity accountEntity = pagedAdapter.getCurrentList().get(position);
            if (pagedAdapter.isSelectedMode()) {
                pagedAdapter.selectItem(accountEntity);
            } else {
                appEventBus.postDelay(new AccountEvent(accountEntity), 200);
                AccountDetailActivity.open(this);
            }
        });
        pagedAdapter.setOnSelectedModeChangeListener(isSelectedMode -> binding.mainBottomBtn.setVisibility(isSelectedMode ? View.VISIBLE : View.GONE));
        pagedAdapter.setOnPagedItemChildClickListener((child, position) -> {
            AccountEntity accountEntity = pagedAdapter.getCurrentList().get(position);
            if (child.getId() == R.id.item_qrcode) {
                AsyncTask.execute(() -> {
                    String json = gson.toJson(new AccountJsonEntity(accountEntity), AccountJsonEntity.class);
                    Bitmap qrCodeBitmap = qrCodeTools.createQRCodeBitmap(json, (int) (windowWidth * 0.8), (int) (windowWidth * 0.8));
                    runOnUiThread(() -> {
                        ImageView imageView = new ImageView(this);
                        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        imageView.setLayoutParams(layoutParams);
                        imageView.setImageBitmap(qrCodeBitmap);
                        Dialog dialog = new Dialog(this);
                        dialog.addContentView(imageView, layoutParams);
                        dialog.create();
                        dialog.show();
                    });
                });
            }
        });
        binding.mainBtnCancel.setOnClickListener(v -> pagedAdapter.closeSelectMode());
        binding.mainBtnDelete.setOnClickListener(v -> {
            List<AccountEntity> selectedAccounts = pagedAdapter.getSelectedAccounts();
            if (selectedAccounts.size() > 0) {
                viewModel.deleteAccountEntities(selectedAccounts);
                pagedAdapter.closeSelectMode();
            }
        });
    }

    @Override
    protected void initObserver() {
        viewModel.getAccountLiveDataPaged().observe(this, accountEntities -> pagedAdapter.submitList(accountEntities));
    }

    @Override
    protected void loadData() {
//        viewModel.loadAccount();
    }

    private void setRefresh(boolean isRefresh) {
        binding.mainSwipe.post(() -> binding.mainSwipe.setRefreshing(isRefresh));
    }

}