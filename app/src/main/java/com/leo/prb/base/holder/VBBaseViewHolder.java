package com.leo.prb.base.holder;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/3/23 11:53
 * Desc:
 */
public class VBBaseViewHolder<VB extends ViewBinding> extends BaseViewHolder {
    private final VB viewBinding;

    public VBBaseViewHolder(@NonNull VB binding) {
        super(binding.getRoot());
        this.viewBinding = binding;
    }

    public VB getViewBinding() {
        return viewBinding;
    }
}
