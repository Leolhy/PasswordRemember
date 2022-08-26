package com.leo.fpd.ui.fragments.article.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.leo.base.dagger.components.BaseAppComponent;
import com.leo.base.fragment.BaseDaggerVvmLazyLoadFragment;
import com.leo.fpd.R;
import com.leo.fpd.app.FpdApplication;
import com.leo.fpd.databinding.FragmentTopArticleBinding;
import com.leo.fpd.ui.fragments.article.adapter.ArticleAdapter;
import com.leo.fpd.ui.fragments.article.dagger.component.DaggerArticleComponent;
import com.leo.fpd.ui.fragments.article.dagger.module.ArticleModule;
import com.leo.fpd.ui.fragments.article.viewModel.ArticleViewModel;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/12 15:19
 * Desc:
 */
public class TopArticleFragment extends BaseDaggerVvmLazyLoadFragment<FragmentTopArticleBinding, ArticleViewModel> {

    @Inject
    ArticleAdapter adapter;

    public static TopArticleFragment newInstance() {
        return new TopArticleFragment();
    }

    @Override
    protected void componentInject(BaseAppComponent appComponent, LayoutInflater inflater, ViewGroup container) {
        DaggerArticleComponent.builder()
                .appComponent(((FpdApplication) application).getAppComponent(appComponent))
                .articleModule(new ArticleModule(inflater, container))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        binding.articleRefresh.setColorSchemeColors(getResources().getColor(R.color.colorHomeTab), getResources().getColor(R.color.colorSystemTab),
                getResources().getColor(R.color.colorProjectTab), getResources().getColor(R.color.colorMineTab));
        binding.articleRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.articleRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListeners() {
        binding.articleRefresh.setOnRefreshListener(this::lazyLoad);

    }

    @Override
    protected void initObserver() {
        viewModel.topArticleData.observe(this, articleEntities -> {
            adapter.setList(articleEntities);
            setRefreshing(false);
        });
        viewModel.toastData.observe(this, toastEntity -> {
            showToast(toastEntity.getToastContent());
            setRefreshing(false);
        });
    }

    private void setRefreshing(boolean refresh) {
        binding.articleRefresh.post(() -> binding.articleRefresh.setRefreshing(refresh));
    }

    @Override
    protected void lazyLoad() {
        viewModel.loadTopArticle();
        setRefreshing(true);
    }
}
