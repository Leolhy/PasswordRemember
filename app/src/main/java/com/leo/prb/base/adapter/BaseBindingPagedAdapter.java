package com.leo.prb.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.viewbinding.ViewBinding;

import com.leo.prb.base.holder.VBViewHolder;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Objects;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/3/23 11:52
 * Desc:
 */
public abstract class BaseBindingPagedAdapter<VB extends ViewBinding, T> extends PagedListAdapter<T, VBViewHolder<VB>> {

    protected BaseBindingPagedAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(diffCallback);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @NonNull
    @Override
    public VBViewHolder<VB> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            Class<VB> vbClass = (Class<VB>) ((ParameterizedType) Objects.requireNonNull(getClass().getGenericSuperclass())).getActualTypeArguments()[0];
            Method inflate = vbClass.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            VB binding = (VB) inflate.invoke(null, LayoutInflater.from(parent.getContext()), parent, false);
            return new VBViewHolder<>(Objects.requireNonNull(binding));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public interface OnPagedItemClickListener {
        void onClickItem(int position);
    }

    public interface OnPagedItemChildClickListener {
        void onChickItemChild(View child, int position);
    }
}
