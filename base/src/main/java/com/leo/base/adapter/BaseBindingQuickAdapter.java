package com.leo.base.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.base.adapter.holder.VBQuickViewHolder;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Objects;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/4 15:23
 * Desc:
 */
public abstract class BaseBindingQuickAdapter<T, VB extends ViewBinding> extends BaseQuickAdapter<T, VBQuickViewHolder<VB>> {
    public BaseBindingQuickAdapter() {
        super(-1);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @NonNull
    @Override
    protected VBQuickViewHolder<VB> onCreateDefViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            Class<VB> vbClass = (Class<VB>) ((ParameterizedType) Objects.requireNonNull(getClass().getGenericSuperclass())).getActualTypeArguments()[1];
            Method inflate = vbClass.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            VB binding = (VB) inflate.invoke(null, LayoutInflater.from(parent.getContext()), parent, false);
            return new VBQuickViewHolder<>(Objects.requireNonNull(binding));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void convert(@NonNull VBQuickViewHolder<VB> holder, T t) {
        convert(holder.getViewBinding(), t);
    }

    protected abstract void convert(VB binding, T t);

}
