package com.leo.base.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leo.base.entities.ToastEntity;
import com.leo.base.http.BaseResponse;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/9 15:03
 * Desc:
 */
public class BaseViewModel extends ViewModel {

    public MutableLiveData<ToastEntity> toastData = new MutableLiveData<>();

    protected void toastResponse(BaseResponse response) {
        toastData.setValue(new ToastEntity(response.getErrorMsg()));
    }

    protected void toastThrowable(Throwable throwable) {
        toastData.setValue(new ToastEntity(throwable.getMessage()));
    }

}
