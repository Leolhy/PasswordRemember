package com.leo.fpd.ui.fragments.home.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.leo.fpd.ui.fragments.article.view.ArticleFragment;
import com.leo.fpd.ui.fragments.article.view.TopArticleFragment;
import com.leo.fpd.ui.fragments.qaa.view.QaaFragment;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/11 15:08
 * Desc:
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    public HomePagerAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager, BEHAVIOR_SET_USER_VISIBLE_HINT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            default:
            case 0:
                return TopArticleFragment.newInstance();
            case 1:
                return ArticleFragment.newInstance();
            case 2:
                return QaaFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            default:
            case 0:
                return "置顶文章";
            case 1:
                return "热门博文";
            case 2:
                return "每日一问";
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
