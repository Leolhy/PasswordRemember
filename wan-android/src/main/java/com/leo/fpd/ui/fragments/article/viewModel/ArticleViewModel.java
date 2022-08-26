package com.leo.fpd.ui.fragments.article.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.leo.base.http.ListResponse;
import com.leo.base.viewModel.BaseViewModel;
import com.leo.fpd.apiImpl.ArticleApiImpl;
import com.leo.fpd.entities.ArticleEntity;
import com.leo.fpd.repository.ArticleRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import kotlinx.coroutines.CoroutineScope;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 15:52
 * Desc:
 */
public class ArticleViewModel extends BaseViewModel {

    @Inject
    ArticleApiImpl articleApi;

    private final ArticleRepository articleRepository;
    public MutableLiveData<List<ArticleEntity>> topArticleData = new MutableLiveData<>();

    @Inject
    public ArticleViewModel(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Flowable<PagingData<ArticleEntity>> getFlowable() {
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Pager<Integer, ArticleEntity> pager = new Pager<>(new PagingConfig(20), articleRepository::getArticlePagingSource);
        Flowable<PagingData<ArticleEntity>> flowable = PagingRx.getFlowable(pager);
        PagingRx.cachedIn(flowable, viewModelScope);
        return flowable;
    }

    public void loadTopArticle() {
        articleApi.loadTopArticle()
                .subscribe(new Observer<ListResponse<ArticleEntity>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ListResponse<ArticleEntity> response) {
                        if (response.getErrorCode() == 0) {
                            topArticleData.setValue(response.getData());
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
