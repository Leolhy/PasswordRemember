package com.leo.prb.base.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.prb.base.holder.VBBaseViewHolder;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Objects;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/4/19 17:20
 * Desc:
 */
public abstract class BaseBindingQuickAdapter<T, VB extends ViewBinding> extends BaseQuickAdapter<T, VBBaseViewHolder<VB>> {

    public BaseBindingQuickAdapter(int layoutResId) {
        super(layoutResId);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @NonNull
    @Override
    protected VBBaseViewHolder<VB> onCreateDefViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            Class<VB> vbClass = (Class<VB>) ((ParameterizedType) Objects.requireNonNull(getClass().getGenericSuperclass())).getActualTypeArguments()[1];
            Method inflate = vbClass.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            VB binding = (VB) inflate.invoke(null, LayoutInflater.from(parent.getContext()), parent, false);
            return new VBBaseViewHolder<>(Objects.requireNonNull(binding));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
