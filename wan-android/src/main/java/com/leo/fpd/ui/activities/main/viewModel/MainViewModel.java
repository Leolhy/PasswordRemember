package com.leo.fpd.ui.activities.main.viewModel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.leo.fpd.R;
import com.leo.fpd.ui.fragments.home.view.HomeFragment;
import com.leo.fpd.ui.fragments.mine.view.MineFragment;
import com.leo.fpd.ui.fragments.project.view.ProjectFragment;
import com.leo.fpd.ui.fragments.system.view.SystemFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 15:44
 * Desc:
 */
public class MainViewModel extends ViewModel {

    public MutableLiveData<List<BottomNavigationItem>> bottomItemsData = new MutableLiveData<>();
    public MutableLiveData<List<Fragment>> fragmentsData = new MutableLiveData<>();

    @Inject
    public MainViewModel() {
    }

    public void initTabs() {
        List<BottomNavigationItem> items = new ArrayList<>();
        items.add(new BottomNavigationItem(R.drawable.ic_home_sel, "首页")
                .setActiveColorResource(R.color.colorHomeTab)
                .setInactiveIconResource(R.drawable.ic_home_nor)
                .setInActiveColorResource(R.color.colorTabSel));
        items.add(new BottomNavigationItem(R.drawable.ic_system_sel, "体系")
                .setActiveColorResource(R.color.colorSystemTab)
                .setInactiveIconResource(R.drawable.ic_system_nor)
                .setInActiveColorResource(R.color.colorTabSel));
        items.add(new BottomNavigationItem(R.drawable.ic_project_sel, "项目")
                .setActiveColorResource(R.color.colorProjectTab)
                .setInactiveIconResource(R.drawable.ic_project_nor)
                .setInActiveColorResource(R.color.colorTabSel));
        items.add(new BottomNavigationItem(R.drawable.ic_mine_sel, "我的")
                .setActiveColorResource(R.color.colorMineTab)
                .setInactiveIconResource(R.drawable.ic_mine_nor)
                .setInActiveColorResource(R.color.colorTabSel));
        bottomItemsData.setValue(items);
    }

    public void initFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(SystemFragment.newInstance());
        fragments.add(ProjectFragment.newInstance());
        fragments.add(MineFragment.newInstance());
        fragmentsData.setValue(fragments);
    }

}
