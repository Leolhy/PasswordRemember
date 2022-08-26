package com.leo.fpd.ui.activities.main.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.leo.base.activity.BaseDaggerVvmActivity;
import com.leo.base.dagger.components.BaseAppComponent;
import com.leo.fpd.R;
import com.leo.fpd.app.FpdApplication;
import com.leo.fpd.databinding.ActivityMainBinding;
import com.leo.fpd.ui.activities.main.dagger.component.DaggerMainComponent;
import com.leo.fpd.ui.activities.main.dagger.module.MainModule;
import com.leo.fpd.ui.activities.main.viewModel.MainViewModel;

import java.util.List;

public class MainActivity extends BaseDaggerVvmActivity<ActivityMainBinding, MainViewModel> {

    private List<Fragment> fragments;

    @Override
    protected void componentInject(BaseAppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(((FpdApplication) application).getAppComponent(appComponent))
                .mainModule(new MainModule(getLayoutInflater()))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        binding.bottomTabBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                addOrShowFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    private void addOrShowFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = fragments.get(position);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (int i = 0; fragments.size() > 0 && i < fragments.size(); i++) {
            transaction.hide(fragments.get(i));
        }
        if (fragments.contains(fragment)) {
            transaction.show(fragment);
        } else {
            transaction.replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName());
        }
        transaction.commitNowAllowingStateLoss();
    }

    @Override
    protected void initObserver() {
        viewModel.bottomItemsData.observe(this, bottomNavigationItems -> {
            for (BottomNavigationItem navigationItem : bottomNavigationItems) {
                binding.bottomTabBar.addItem(navigationItem);
            }
            binding.bottomTabBar.setFirstSelectedPosition(0);
            binding.bottomTabBar.initialise();
        });
        viewModel.fragmentsData.observe(this, fragments -> {
            this.fragments = fragments;
            addOrShowFragment(0);
        });
    }

    @Override
    protected void loadData() {
        viewModel.initTabs();
        viewModel.initFragments();
    }
}