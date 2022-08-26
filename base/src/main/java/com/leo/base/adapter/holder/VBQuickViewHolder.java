package com.leo.base.adapter.holder;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/4 15:23
 * Desc:
 */
public class VBQuickViewHolder<VB extends ViewBinding> extends BaseViewHolder {
    private final VB viewBinding;

    public VBQuickViewHolder(@NonNull VB binding) {
        super(binding.getRoot());
        this.viewBinding = binding;
    }

    public VB getViewBinding() {
        return viewBinding;
    }
}
