package com.leo.fpd.ui.fragments.system.view;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.tabs.TabLayout;
import com.leo.base.dagger.components.BaseAppComponent;
import com.leo.base.entities.FilletedTabItem;
import com.leo.base.fragment.BaseDaggerVvmFragment;
import com.leo.base.http.PageResponse;
import com.leo.fpd.R;
import com.leo.fpd.app.FpdApplication;
import com.leo.fpd.databinding.FragmentSystemBinding;
import com.leo.fpd.entities.ArticleEntity;
import com.leo.fpd.entities.SystemClassEntity;
import com.leo.fpd.ui.fragments.system.adpter.SystemArticleAdapter;
import com.leo.fpd.ui.fragments.system.dagger.component.DaggerSystemComponent;
import com.leo.fpd.ui.fragments.system.dagger.module.SystemModule;
import com.leo.fpd.ui.fragments.system.viewModel.SystemViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 14:21
 * Desc:
 */
public class SystemFragment extends BaseDaggerVvmFragment<FragmentSystemBinding, SystemViewModel> {

    @Inject
    SystemArticleAdapter adapter;

    private List<SystemClassEntity> tabs = new ArrayList<>();
    private SystemClassEntity selectedTab;
    private int page = 0;
    private int cid;

    public static SystemFragment newInstance() {
        return new SystemFragment();
    }

    @Override
    protected void componentInject(BaseAppComponent appComponent, LayoutInflater inflater, ViewGroup container) {
        DaggerSystemComponent.builder()
                .appComponent(((FpdApplication) application).getAppComponent(appComponent))
                .systemModule(new SystemModule(inflater, container))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        binding.toolbarLayout.setTitle("体系");
        binding.toolbarLayout.setExpandedTitleColor(Color.WHITE);
        binding.systemSwipe.setColorSchemeColors(getResources().getColor(R.color.colorHomeTab), getResources().getColor(R.color.colorSystemTab),
                getResources().getColor(R.color.colorProjectTab), getResources().getColor(R.color.colorMineTab));
        binding.systemRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.systemRecycler.setAdapter(adapter);
        adapter.getLoadMoreModule().setAutoLoadMore(true);
    }

    @Override
    protected void initListeners() {
        binding.systemTabParent.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedTab = getSelectEntity(tab);
                changeSubTab();
            }

            private SystemClassEntity getSelectEntity(TabLayout.Tab tab) {
                for (SystemClassEntity _tab : tabs) {
                    if (_tab.getName().contentEquals(Objects.requireNonNull(tab.getText()))) {
                        return _tab;
                    }
                }
                return null;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.systemTabChild.setChangedListener((selectIndex, selectTab) -> {
            SystemClassEntity systemClass = (SystemClassEntity) selectTab;
            cid = systemClass.getId();
            page = 0;
            loadSystemData(page, cid);
        });
        binding.systemSwipe.setOnRefreshListener(() -> {
            page = 0;
            loadSystemData(page, cid);
        });
        adapter.getLoadMoreModule().setOnLoadMoreListener(() -> {
            page++;
            loadSystemData(page, cid);
        });
    }

    @Override
    protected void initObserver() {
        viewModel.systemClassData.observe(this, systemClazz -> {
            this.tabs = systemClazz;
            for (SystemClassEntity classEntity : systemClazz) {
                if (classEntity.getChildren() == null || classEntity.getChildren().isEmpty()) {
                    continue;
                }
                TabLayout.Tab newTab = binding.systemTabParent.newTab();
                newTab.setText(classEntity.getName());
                binding.systemTabParent.addTab(newTab);
                if (selectedTab == null) {
                    selectedTab = classEntity;
                    changeSubTab();
                }
            }
        });
        viewModel.systemArticleData.observe(this, articlePage -> {
            PageResponse.DataBean<ArticleEntity> pageData = articlePage.getData();
            if (pageData.getCurPage() == 1) {
                adapter.setList(articlePage.getData().getDatas());
            } else {
                adapter.addData(articlePage.getData().getDatas());
            }
            setRefreshing(false);
            if (pageData.getCurPage() >= pageData.getPageCount()) {
                adapter.getLoadMoreModule().loadMoreEnd();
            } else {
                adapter.getLoadMoreModule().loadMoreComplete();
            }
        });
    }

    private void changeSubTab() {
        if (selectedTab != null) {
            List<FilletedTabItem> subTabs = new ArrayList<>(selectedTab.getChildren());
            binding.systemTabChild.setTabList(subTabs);
            SystemClassEntity systemClass = (SystemClassEntity) subTabs.get(0);
            cid = systemClass.getId();
            page = 0;
            loadSystemData(page, cid);
        }
    }

    @Override
    protected void loadData() {
        viewModel.loadSystemClass();
    }

    private void setRefreshing(boolean refresh) {
        binding.systemSwipe.post(() -> binding.systemSwipe.setRefreshing(refresh));
    }

    private void loadSystemData(int page, int cid) {
        viewModel.loadSystemArticle(page, cid);
        setRefreshing(true);
    }
}
