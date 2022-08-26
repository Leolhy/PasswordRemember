package com.leo.prb.mvvm.type.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leo.prb.entities.AccountTypeEntity;

import javax.inject.Inject;

import io.objectbox.Box;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/3/4 16:27
 * Desc:
 */
public class TypeViewModel extends ViewModel {

    public MutableLiveData<Boolean> saveResponse = new MutableLiveData<>();

    @Inject
    Box<AccountTypeEntity> accountTypeBox;

    @Inject
    public TypeViewModel() {
    }

    public void saveAccountType(AccountTypeEntity typeEntity) {
        long put = accountTypeBox.put(typeEntity);
        saveResponse.setValue(put != -1);
    }

}
