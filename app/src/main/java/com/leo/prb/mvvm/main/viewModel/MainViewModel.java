package com.leo.prb.mvvm.main.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.leo.prb.entities.AccountEntity;
import com.leo.prb.entities.AccountEntity_;
import com.leo.prb.tools.BiometricPromptManager;

import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;
import io.objectbox.android.ObjectBoxDataSource;
import io.objectbox.query.Query;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/16 11:36
 * Desc:
 */
public class MainViewModel extends ViewModel {

    public MutableLiveData<List<AccountEntity>> accountLiveData = new MutableLiveData<>();
    public LiveData<PagedList<AccountEntity>> accountPagedLiveData;
    public MutableLiveData<Boolean> loadMoreEnd = new MutableLiveData<>();
    private int page = 0, size = 15;

    @Inject
    BiometricPromptManager biometricPromptManager;

    @Inject
    Box<AccountEntity> accountBox;


    @Inject
    public MainViewModel() {
    }

    public void startFingerprint() {
        biometricPromptManager.startAuthenticate();
    }

    public void loadMoreAccount() {
        page++;
        loadAccount();
    }

    public void refreshAccount() {
        page = 0;
        loadAccount();
    }

    public void loadAccount() {
        loadAccountList(page, size);
    }

    private void loadAccountList(int page, int pageSize) {
        long count = accountBox.count();
        int temp = (int) (count % pageSize);
        int pageCount = (int) (temp == 0 ? count / pageSize : count / pageSize + 1);
        if ((page + 1) > pageCount) {
            loadMoreEnd.setValue(true);
        } else {
            List<AccountEntity> accountEntities = accountBox.query()
                    .orderDesc(AccountEntity_.createTime)
                    .build()
                    .find((long) page * pageSize, pageSize);
            if (page == 0) {
                accountLiveData.setValue(accountEntities);
            } else {
                List<AccountEntity> value = accountLiveData.getValue();
                if (value != null) {
                    value.addAll(accountEntities);
                    accountLiveData.setValue(value);
                }
            }
            loadMoreEnd.setValue(false);
        }
    }

    public LiveData<PagedList<AccountEntity>> getAccountLiveDataPaged() {
        if (accountPagedLiveData == null) {
            Query<AccountEntity> query = accountBox
                    .query()
                    .orderDesc(AccountEntity_.createTime)
                    .build();
            accountPagedLiveData = new LivePagedListBuilder<>(new ObjectBoxDataSource.Factory<>(query), size).build();
        }
        return accountPagedLiveData;
    }

    public void deleteAccountEntities(List<AccountEntity> accountEntities) {
        accountBox.remove(accountEntities);
    }
}
