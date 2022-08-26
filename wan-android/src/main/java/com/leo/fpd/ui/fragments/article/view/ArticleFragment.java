package com.leo.fpd.ui.fragments.article.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.leo.base.dagger.components.BaseAppComponent;
import com.leo.base.fragment.BaseDaggerVvmLazyLoadFragment;
import com.leo.fpd.R;
import com.leo.fpd.app.FpdApplication;
import com.leo.fpd.databinding.FragmentArticleBinding;
import com.leo.fpd.ui.fragments.article.adapter.ArticlePagedAdapter;
import com.leo.fpd.ui.fragments.article.dagger.component.DaggerArticleComponent;
import com.leo.fpd.ui.fragments.article.dagger.module.ArticleModule;
import com.leo.fpd.ui.fragments.article.viewModel.ArticleViewModel;

import javax.inject.Inject;

import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 15:50
 * Desc:
 */
public class ArticleFragment extends BaseDaggerVvmLazyLoadFragment<FragmentArticleBinding, ArticleViewModel> {

    @Inject
    ArticlePagedAdapter adapter;

    public static ArticleFragment newInstance() {
        return new ArticleFragment();
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
        binding.articleRefresh.setOnRefreshListener(() -> {
            binding.articleRefresh.setRefreshing(false);
        });
//        adapter.addLoadStateListener(states -> {
//            if (states.getPrepend().getEndOfPaginationReached()) {
//                binding.articleRefresh.setRefreshing(false);
//            }
//            return null;
//        });
    }

    @Override
    protected void initObserver() {
        viewModel.toastData.observe(this, toastEntity -> showToast(toastEntity.getToastContent()));
    }

    @Override
    protected void lazyLoad() {
        viewModel.getFlowable()
                .observeOn(AndroidSchedulers.mainThread())
                .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(pagingData -> adapter.submitData(getLifecycle(), pagingData));
    }
}
