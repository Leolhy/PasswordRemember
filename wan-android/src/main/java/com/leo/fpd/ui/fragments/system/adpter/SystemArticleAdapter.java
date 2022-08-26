package com.leo.fpd.ui.fragments.system.adpter;

import android.text.TextUtils;

import com.chad.library.adapter.base.module.LoadMoreModule;
import com.leo.base.adapter.BaseBindingQuickAdapter;
import com.leo.base.tools.DateTools;
import com.leo.fpd.databinding.ItemArticleBinding;
import com.leo.fpd.entities.ArticleEntity;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/12 15:43
 * Desc:
 */
public class SystemArticleAdapter extends BaseBindingQuickAdapter<ArticleEntity, ItemArticleBinding> implements LoadMoreModule {

    @Inject
    public SystemArticleAdapter() {
    }

    @Override
    protected void convert(ItemArticleBinding binding, ArticleEntity articleEntity) {
        binding.itemArticleTitle.setText(articleEntity.getTitle());
        binding.itemArticleAuthor.setText(TextUtils.isEmpty(articleEntity.getAuthor()) ? articleEntity.getShareUser() : articleEntity.getAuthor());
        binding.itemArticleTime.setText(DateTools.getStringDate(articleEntity.getPublishTime()));
    }
}
