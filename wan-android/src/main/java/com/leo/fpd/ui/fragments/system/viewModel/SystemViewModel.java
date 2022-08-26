package com.leo.fpd.ui.fragments.system.viewModel;

import androidx.lifecycle.MutableLiveData;

import com.leo.base.http.ListResponse;
import com.leo.base.http.PageResponse;
import com.leo.base.viewModel.BaseViewModel;
import com.leo.fpd.entities.ArticleEntity;
import com.leo.fpd.entities.SystemClassEntity;
import com.leo.fpd.repository.SystemRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 14:21
 * Desc:
 */
public class SystemViewModel extends BaseViewModel {

    private final SystemRepository systemRepository;

    public MutableLiveData<PageResponse<ArticleEntity>> systemArticleData = new MutableLiveData<>();

    public MutableLiveData<List<SystemClassEntity>> systemClassData = new MutableLiveData<>();

    @Inject
    public SystemViewModel(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    public void loadSystemArticle(int page, int cid) {
        systemRepository.getSystemApi()
                .loadPageSystemArticle(page, cid)
                .subscribe(new Observer<PageResponse<ArticleEntity>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull PageResponse<ArticleEntity> response) {
                        if (response.getErrorCode() == 0) {
                            systemArticleData.setValue(response);
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

    public void loadSystemClass() {
        systemRepository.getSystemApi()
                .loadSystemClassTree()
                .subscribe(new Observer<ListResponse<SystemClassEntity>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ListResponse<SystemClassEntity> response) {
                        if (response.getErrorCode() == 0) {
                            systemClassData.setValue(response.getData());
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
