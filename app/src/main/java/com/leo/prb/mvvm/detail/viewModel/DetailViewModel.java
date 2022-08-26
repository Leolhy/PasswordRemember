package com.leo.prb.mvvm.detail.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leo.prb.entities.AccountEntity;
import com.leo.prb.entities.AccountTypeEntity;
import com.leo.prb.entities.AccountTypeEntity_;

import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/3/4 11:28
 * Desc:
 */
public class DetailViewModel extends ViewModel {

    public MutableLiveData<List<AccountTypeEntity>> accountTypeLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> saveResponse = new MutableLiveData<>();

    @Inject
    Box<AccountEntity> accountBox;

    @Inject
    Box<AccountTypeEntity> accountTypeBox;

    @Inject
    public DetailViewModel() {
    }

    public void loadAccountTypeList() {
        List<AccountTypeEntity> accountTypeEntities = accountTypeBox.query().build().find();
        accountTypeLiveData.setValue(accountTypeEntities);
    }


    public void saveAccount(AccountEntity accountEntity) {
        long put = accountBox.put(accountEntity);
        saveResponse.setValue(put != -1);
    }

    public AccountTypeEntity getTypeByGuid(AccountTypeEntity accountType) {
        List<AccountTypeEntity> typeEntities = accountTypeBox.query().equal(AccountTypeEntity_.guid, accountType.getGuid(), QueryBuilder.StringOrder.CASE_SENSITIVE).build().find();
        if (typeEntities.size() > 0) {
            return typeEntities.get(0);
        } else {
            long put = accountTypeBox.put(accountType);
            return accountTypeBox.get(put);
        }
    }
}
