package com.leo.prb.mvvm.main.adapter;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.leo.prb.base.adapter.BaseBindingPagedAdapter;
import com.leo.prb.base.holder.VBViewHolder;
import com.leo.prb.databinding.ItemAccountBinding;
import com.leo.prb.entities.AccountEntity;
import com.leo.prb.entities.AccountTypeEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/3/23 11:37
 * Desc:
 */
public class AccountPagedAdapter extends BaseBindingPagedAdapter<ItemAccountBinding, AccountEntity> {

    private OnPagedItemClickListener clickListener;
    private OnPagedItemChildClickListener childClickListener;
    private OnSelectedModeChangeListener changeListener;
    private List<AccountEntity> selectedAccounts = new ArrayList<>();
    private Set<Integer> showIndexes = new HashSet<>();
    private boolean isSelectedMode = false;

    @Inject
    protected AccountPagedAdapter() {
        super(DIFF_CALLBACK);
    }

    public void setOnPagedItemClickListener(OnPagedItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setOnPagedItemChildClickListener(OnPagedItemChildClickListener childClickListener) {
        this.childClickListener = childClickListener;
    }

    public void setOnSelectedModeChangeListener(OnSelectedModeChangeListener changeListener) {
        this.changeListener = changeListener;
    }


    public boolean isSelectedMode() {
        return isSelectedMode;
    }

    public List<AccountEntity> getSelectedAccounts() {
        return selectedAccounts;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void openSelectMode() {
        isSelectedMode = true;
        if (changeListener != null) {
            changeListener.onChange(true);
        }
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void closeSelectMode() {
        isSelectedMode = false;
        if (changeListener != null) {
            changeListener.onChange(false);
        }
        selectedAccounts.clear();
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void selectItem(AccountEntity item) {
        if (selectedAccounts.size() == 0) {
            selectedAccounts.add(item);
        } else {
            if (isItemSelected(item)) {
                selectedAccounts.remove(item);
            } else {
                selectedAccounts.add(item);
            }
        }
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void showHidePassword(int position) {
        if (showIndexes.size() == 0) {
            showIndexes.add(position);
        } else {
            if (isShowPassword(position)) {
                showIndexes.remove(position);
            } else {
                showIndexes.add(position);
            }
        }
        notifyDataSetChanged();
    }

    private boolean isItemSelected(AccountEntity item) {
        if (selectedAccounts.size() > 0) {
            for (AccountEntity planItem : selectedAccounts) {
                if (planItem.getId().equals(item.getId()))
                    return true;
            }
        }
        return false;
    }

    private boolean isShowPassword(int position) {
        return showIndexes.contains(position);
    }

    @Override
    public void onBindViewHolder(@NonNull VBViewHolder<ItemAccountBinding> holder, int position) {
        ItemAccountBinding binding = holder.getBinding();
        AccountEntity item = getItem(position);
        if (item != null) {
            binding.itemAccount.setText(item.getAccount());
            binding.itemDesc.setText(item.getDesc());
            binding.itemCheckBox.setVisibility(isSelectedMode ? View.VISIBLE : View.GONE);
            binding.itemCheckBox.setChecked(isItemSelected(item));
            binding.itemShowHide.setSelected(isShowPassword(position));
            binding.itemPassword.setText(isShowPassword(position) ? item.getPassword() : "******");
            AccountTypeEntity typeEntity = item.getAccountType().getTarget();
            binding.itemType.setText(typeEntity == null ? "无" : typeEntity.getSimpleName());
            binding.getRoot().setOnLongClickListener(v -> {
                openSelectMode();
                return true;
            });
            binding.getRoot().setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onClickItem(position);
                }
            });
            binding.itemShowHide.setOnClickListener(v -> showHidePassword(position));
            binding.itemQrcode.setOnClickListener(v -> {
                if (childClickListener != null) {
                    childClickListener.onChickItemChild(v, position);
                }
            });
        }
    }

    private static final DiffUtil.ItemCallback<AccountEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<AccountEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull AccountEntity oldItem, @NonNull AccountEntity newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull AccountEntity oldItem, @NonNull AccountEntity newItem) {
            return isEqualSafe(oldItem.getAccount(), newItem.getAccount())
                    && isEqualSafe(oldItem.getPassword(), newItem.getDesc())
                    && isEqualSafe(oldItem.getDesc(), newItem.getDesc())
                    && isEqualSafe(oldItem.getAccountType().getTarget().getId(), newItem.getAccountType().getTarget().getId());
        }

        private boolean isEqualSafe(Object oldString, Object newString) {
            if (oldString == null) {
                return newString == null;
            } else {
                return oldString.equals(newString);
            }
        }
    };

    public interface OnSelectedModeChangeListener {
        void onChange(boolean isSelectedMode);
    }
}
