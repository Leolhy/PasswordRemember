package com.leo.prb.mvvm.detail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.leo.prb.R;
import com.leo.prb.entities.AccountTypeEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/3/4 17:06
 * Desc:
 */
public class AccountTypeAdapter extends BaseAdapter {

    private final LayoutInflater inflater;

    private List<AccountTypeEntity> accountTypeEntities = new ArrayList<>();

    @Inject
    public AccountTypeAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setAccountTypeEntities(List<AccountTypeEntity> accountTypeEntities) {
        this.accountTypeEntities = accountTypeEntities;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return accountTypeEntities.size();
    }

    @Override
    public AccountTypeEntity getItem(int position) {
        return accountTypeEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root;
        if (convertView != null) {
            root = convertView;
        } else {
            root = inflater.inflate(R.layout.item_account_type, null);
        }
        TextView textView = root.findViewById(R.id.name);
        textView.setText(accountTypeEntities.get(position).getName());
        return root;
    }

}
