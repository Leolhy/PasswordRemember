package com.leo.fpd.ui.fragments.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leo.fpd.entities.BannerEntity;
import com.sivin.BannerAdapter;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/11 16:31
 * Desc:
 */
public class BannerEntityAdapter extends BannerAdapter<BannerEntity> {

    private final WeakReference<Context> context;

    public BannerEntityAdapter(Context context, List<BannerEntity> datas) {
        super(datas);
        this.context = new WeakReference<>(context);
    }

    @Override
    protected void bindTips(TextView tv, BannerEntity bannerEntity) {
        tv.setText(bannerEntity.getTitle());
    }

    @Override
    public void bindImage(ImageView imageView, BannerEntity bannerEntity) {
        Glide.with(context.get())
                .load(bannerEntity.getImagePath())
                .optionalCenterInside()
                .into(imageView);
    }
}
