package com.leo.fpd.ui.fragments.home.viewModel;

import androidx.lifecycle.MutableLiveData;

import com.leo.base.http.ListResponse;
import com.leo.base.viewModel.BaseViewModel;
import com.leo.fpd.entities.BannerEntity;
import com.leo.fpd.repository.BannerRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 11:47
 * Desc:
 */
public class HomeViewModel extends BaseViewModel {

    private final BannerRepository bannerRepository;

    public MutableLiveData<List<BannerEntity>> bannerData = new MutableLiveData<>();

    @Inject
    public HomeViewModel(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    public void loadBanners() {
        bannerRepository.getHomeApi()
                .loadBannerList()
                .subscribe(new Observer<ListResponse<BannerEntity>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ListResponse<BannerEntity> response) {
                        if (response.getErrorCode() == 0) {
                            bannerData.setValue(response.getData());
                        } else {
                            toastResponse(response);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        toastThrowable(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
