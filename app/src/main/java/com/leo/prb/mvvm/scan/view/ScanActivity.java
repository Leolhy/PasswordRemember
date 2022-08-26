package com.leo.prb.mvvm.scan.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;

import androidx.annotation.NonNull;

import com.leo.prb.base.activity.BaseDaggerMvvmActivity;
import com.leo.prb.dagger.components.AppComponent;
import com.leo.prb.dagger.components.DaggerScanComponent;
import com.leo.prb.dagger.modules.ScanModule;
import com.leo.prb.databinding.ActivityScanBinding;
import com.leo.prb.event.QRCodeEvent;
import com.leo.prb.mvvm.scan.viewModel.ScanViewModel;

import java.util.List;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import pub.devrel.easypermissions.EasyPermissions;

public class ScanActivity extends BaseDaggerMvvmActivity<ActivityScanBinding, ScanViewModel> implements QRCodeView.Delegate, EasyPermissions.PermissionCallbacks {

    private static final String[] permissions = {Manifest.permission.CAMERA};

    public static void open(Context context) {
        Intent openIntent = new Intent(context, ScanActivity.class);
        context.startActivity(openIntent);
    }

    @Override
    protected void componentInject(AppComponent appComponent) {
        DaggerScanComponent.builder()
                .appComponent(appComponent)
                .scanModule(new ScanModule(getLayoutInflater()))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        binding.zbarview.setDelegate(this);
    }

    @Override
    protected void initObserver() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (EasyPermissions.hasPermissions(this, permissions)) {
            binding.zbarview.startSpotAndShowRect();
        } else {
            EasyPermissions.requestPermissions(this, "申请照相机权限", 10001, permissions);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        binding.zbarview.startCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.zbarview.onDestroy();
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode == 10001) {
            binding.zbarview.startSpotAndShowRect();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        finish();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        vibrate();
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            appEventBus.postOrderly(new QRCodeEvent(result));
            finish();
        }, 200);
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }

    @SuppressLint("MissingPermission")
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

}