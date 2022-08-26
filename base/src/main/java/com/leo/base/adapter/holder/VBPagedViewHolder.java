package com.leo.base.adapter.holder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/4 15:17
 * Desc:
 */
public class VBPagedViewHolder<VB extends ViewBinding> extends RecyclerView.ViewHolder {
    private final VB binding;

    public VBPagedViewHolder(@NonNull VB binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public VB getBinding() {
        return binding;
    }
}
