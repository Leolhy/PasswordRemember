package com.leo.fpd.ui.fragments.home.view;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leo.base.dagger.components.BaseAppComponent;
import com.leo.base.fragment.BaseDaggerVvmFragment;
import com.leo.fpd.app.FpdApplication;
import com.leo.fpd.databinding.FragmentHomeBinding;
import com.leo.fpd.ui.fragments.home.adapter.BannerEntityAdapter;
import com.leo.fpd.ui.fragments.home.adapter.HomePagerAdapter;
import com.leo.fpd.ui.fragments.home.dagger.component.DaggerHomeComponent;
import com.leo.fpd.ui.fragments.home.dagger.module.HomeModule;
import com.leo.fpd.ui.fragments.home.viewModel.HomeViewModel;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 11:47
 * Desc:
 */
public class HomeFragment extends BaseDaggerVvmFragment<FragmentHomeBinding, HomeViewModel> {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected void componentInject(BaseAppComponent appComponent, LayoutInflater inflater, ViewGroup container) {
        DaggerHomeComponent.builder()
                .appComponent(((FpdApplication) application).getAppComponent(appComponent))
                .homeModule(new HomeModule(inflater, container))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        binding.toolbarLayout.setTitle("首页");
        binding.toolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        HomePagerAdapter pagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        binding.viewPager.setAdapter(pagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initObserver() {
        viewModel.toastData.observe(this, toastEntity -> showToast(toastEntity.getToastContent()));
        viewModel.bannerData.observe(this, bannerEntities -> {
            BannerEntityAdapter adapter = new BannerEntityAdapter(getContext(), bannerEntities);
            binding.toolbarBanner.setBannerAdapter(adapter);
            binding.toolbarBanner.notifiDataHasChanged();
        });
    }

    @Override
    protected void loadData() {
        viewModel.loadBanners();
    }

}
