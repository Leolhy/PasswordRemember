package com.leo.fpd.ui.fragments.article.adapter;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.leo.base.adapter.BaseBindingPagedAdapter;
import com.leo.base.tools.DateTools;
import com.leo.fpd.databinding.ItemArticleBinding;
import com.leo.fpd.entities.ArticleEntity;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/11 11:53
 * Desc:
 */
public class ArticlePagedAdapter extends BaseBindingPagedAdapter<ItemArticleBinding, ArticleEntity> {

    @Inject
    public ArticlePagedAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    protected void convert(ItemArticleBinding binding, ArticleEntity articleEntity) {
        binding.itemArticleTitle.setText(articleEntity.getTitle());
        binding.itemArticleAuthor.setText(TextUtils.isEmpty(articleEntity.getAuthor()) ? articleEntity.getShareUser() : articleEntity.getAuthor());
        binding.itemArticleTime.setText(DateTools.getStringDate(articleEntity.getPublishTime()));
    }

    private static final DiffUtil.ItemCallback<ArticleEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<ArticleEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull ArticleEntity oldItem, @NonNull ArticleEntity newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ArticleEntity oldItem, @NonNull ArticleEntity newItem) {
            return isEqualSafe(oldItem, newItem);
        }

        private boolean isEqualSafe(Object oldO, Object newO) {
            if (oldO == null) {
                return newO == null;
            } else {
                return oldO.equals(newO);
            }
        }
    };

}
