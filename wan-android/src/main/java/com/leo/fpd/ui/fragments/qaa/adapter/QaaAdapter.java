package com.leo.fpd.ui.fragments.qaa.adapter;

import com.chad.library.adapter.base.module.LoadMoreModule;
import com.leo.base.adapter.BaseBindingQuickAdapter;
import com.leo.base.tools.DateTools;
import com.leo.fpd.databinding.ItemQaaBinding;
import com.leo.fpd.entities.QaaEntity;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 16:54
 * Desc:
 */
public class QaaAdapter extends BaseBindingQuickAdapter<QaaEntity, ItemQaaBinding> implements LoadMoreModule {

    @Inject
    public QaaAdapter() {
    }

    @Override
    protected void convert(ItemQaaBinding binding, QaaEntity qaaEntity) {
        binding.itemQaaTitle.setText(qaaEntity.getTitle());
        binding.itemQaaAuthor.setText(qaaEntity.getAuthor());
        binding.itemQaaTime.setText(DateTools.getStringDate(qaaEntity.getPublishTime()));
    }
}
