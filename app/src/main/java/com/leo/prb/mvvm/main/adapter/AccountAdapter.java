package com.leo.prb.mvvm.main.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.leo.prb.R;
import com.leo.prb.entities.AccountEntity;
import com.leo.prb.entities.AccountTypeEntity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/3/3 17:34
 * Desc:
 */
public class AccountAdapter extends BaseQuickAdapter<AccountEntity, BaseViewHolder> implements LoadMoreModule {

    @Inject
    public AccountAdapter(@Named("accountItem") int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, AccountEntity accountEntity) {
        AccountTypeEntity typeEntity = accountEntity.getAccountType().getTarget();
        holder.setText(R.id.item_account, accountEntity.getAccount())
                .setText(R.id.item_desc, accountEntity.getDesc())
                .setText(R.id.item_type, typeEntity == null ? "无" : typeEntity.getSimpleName());
    }
}
