package com.leo.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.viewbinding.ViewBinding;

import com.leo.base.adapter.holder.VBPagedViewHolder;
import com.leo.base.adapter.holder.VBQuickViewHolder;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Objects;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/4 15:15
 * Desc:
 */
public abstract class BaseBindingPagedAdapter<VB extends ViewBinding, T> extends PagingDataAdapter<T, VBPagedViewHolder<VB>> {

    protected BaseBindingPagedAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(diffCallback);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @NonNull
    @Override
    public VBPagedViewHolder<VB> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            Class<VB> vbClass = (Class<VB>) ((ParameterizedType) Objects.requireNonNull(getClass().getGenericSuperclass())).getActualTypeArguments()[0];
            Method inflate = vbClass.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            VB binding = (VB) inflate.invoke(null, LayoutInflater.from(parent.getContext()), parent, false);
            return new VBPagedViewHolder<>(Objects.requireNonNull(binding));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull VBPagedViewHolder<VB> holder, int position) {
        VB binding = holder.getBinding();
        T item = getItem(position);
        convert(binding, item);
    }

    protected abstract void convert(VB binding, T t);

    public interface OnPagedItemClickListener {
        void onClickItem(int position);
    }

    public interface OnPagedItemChildClickListener {
        void onChickItemChild(View child, int position);
    }
}
