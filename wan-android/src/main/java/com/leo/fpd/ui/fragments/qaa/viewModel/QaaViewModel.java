package com.leo.fpd.ui.fragments.qaa.viewModel;

import androidx.lifecycle.MutableLiveData;

import com.leo.base.http.PageResponse;
import com.leo.base.viewModel.BaseViewModel;
import com.leo.fpd.entities.QaaEntity;
import com.leo.fpd.repository.QaaRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 16:03
 * Desc:
 */
public class QaaViewModel extends BaseViewModel {

    private final QaaRepository qaaRepository;

    public MutableLiveData<PageResponse<QaaEntity>> qaaPageData = new MutableLiveData<>();

    @Inject
    public QaaViewModel(QaaRepository qaaRepository) {
        this.qaaRepository = qaaRepository;
    }

    public void loadQaaPageData(int page) {
        qaaRepository.getQaaApi()
                .getQaaPage(page)
                .subscribe(new Observer<PageResponse<QaaEntity>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull PageResponse<QaaEntity> response) {
                        if (response.getErrorCode() == 0) {
                            qaaPageData.setValue(response);
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
