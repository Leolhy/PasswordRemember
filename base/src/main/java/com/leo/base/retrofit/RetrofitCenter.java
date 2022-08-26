package com.leo.base.retrofit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 15:02
 * Desc:
 */
public abstract class RetrofitCenter {

    protected static final String TAG = "RetrofitCenter";

    protected static long CONNECT_TIMEOUT = 60L;
    protected static long READ_TIMEOUT = 30L;
    protected static long WRITE_TIMEOUT = 30L;

    private Retrofit retrofit;

    protected void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    protected abstract String getBaseUrl();

    protected OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        List<Interceptor> interceptors = addInterceptors();
        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }

    public <S> S createServer(Class<S> cls) {
        return retrofit.create(cls);
    }

    public <T> ObservableTransformer<T, T> ioMain() {
        return upStream -> upStream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    protected List<Interceptor> addInterceptors() {
        return new ArrayList<>();
    }

}
