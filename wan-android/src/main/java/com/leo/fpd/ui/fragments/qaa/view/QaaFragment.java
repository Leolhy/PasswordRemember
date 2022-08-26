package com.leo.fpd.ui.fragments.qaa.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.leo.base.dagger.components.BaseAppComponent;
import com.leo.base.fragment.BaseDaggerVvmLazyLoadFragment;
import com.leo.base.http.PageResponse;
import com.leo.fpd.R;
import com.leo.fpd.app.FpdApplication;
import com.leo.fpd.databinding.FragmentQaaBinding;
import com.leo.fpd.entities.QaaEntity;
import com.leo.fpd.ui.fragments.qaa.adapter.QaaAdapter;
import com.leo.fpd.ui.fragments.qaa.dagger.component.DaggerQaaComponent;
import com.leo.fpd.ui.fragments.qaa.dagger.module.QaaModule;
import com.leo.fpd.ui.fragments.qaa.viewModel.QaaViewModel;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 16:02
 * Desc:
 */
public class QaaFragment extends BaseDaggerVvmLazyLoadFragment<FragmentQaaBinding, QaaViewModel> {

    private int page = 0;

    @Inject
    QaaAdapter adapter;

    public static QaaFragment newInstance() {
        return new QaaFragment();
    }

    @Override
    protected void componentInject(BaseAppComponent appComponent, LayoutInflater inflater, ViewGroup container) {
        DaggerQaaComponent.builder()
                .appComponent(((FpdApplication) application).getAppComponent(appComponent))
                .qaaModule(new QaaModule(inflater, container))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        binding.qaaRefresh.setColorSchemeColors(getResources().getColor(R.color.colorHomeTab), getResources().getColor(R.color.colorSystemTab),
                getResources().getColor(R.color.colorProjectTab), getResources().getColor(R.color.colorMineTab));
        binding.qaaRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.qaaRecycler.setAdapter(adapter);
        adapter.getLoadMoreModule().setAutoLoadMore(true);
    }

    @Override
    protected void initListeners() {
        binding.qaaRefresh.setOnRefreshListener(() -> {
            page = 0;
            loadQaaData(page);
            setRefreshing(true);
        });
        adapter.getLoadMoreModule().setOnLoadMoreListener(() -> {
            page++;
            loadQaaData(page);
        });
    }

    @Override
    protected void initObserver() {
        viewModel.qaaPageData.observe(this, response -> {
            PageResponse.DataBean<QaaEntity> pageData = response.getData();
            if (pageData.getCurPage() == 1) {
                adapter.setList(response.getData().getDatas());
            } else {
                adapter.addData(response.getData().getDatas());
            }
            setRefreshing(false);
            if (pageData.getCurPage() >= pageData.getPageCount()) {
                adapter.getLoadMoreModule().loadMoreEnd();
            } else {
                adapter.getLoadMoreModule().loadMoreComplete();
            }
        });
    }

    @Override
    protected void lazyLoad() {
        loadQaaData(page);
    }

    private void setRefreshing(boolean refresh) {
        binding.qaaRefresh.post(() -> binding.qaaRefresh.setRefreshing(refresh));
    }

    private void loadQaaData(int page) {
        viewModel.loadQaaPageData(page);
        setRefreshing(true);
    }
}
