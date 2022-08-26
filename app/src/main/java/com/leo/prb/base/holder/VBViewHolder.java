package com.leo.prb.base.holder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/3/23 11:53
 * Desc:
 */
public class VBViewHolder<VB extends ViewBinding> extends RecyclerView.ViewHolder {
    private final VB binding;

    public VBViewHolder(@NonNull VB binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public VB getBinding() {
        return binding;
    }
}
