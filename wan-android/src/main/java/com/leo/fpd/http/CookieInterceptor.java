package com.leo.fpd.http;

import androidx.annotation.NonNull;

import com.leo.fpd.entities.RememberAccountEntity;
import com.leo.fpd.entities.RememberAccountEntity_;
import com.leo.fpd.entities.UserEntity;
import com.leo.fpd.objectBox.ObjectBoxTools;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/25 16:01
 * Desc:
 */
public class CookieInterceptor implements Interceptor {

    private final Box<RememberAccountEntity> rememberAccount;

    public CookieInterceptor() {
        this.rememberAccount = ObjectBoxTools.get().boxFor(RememberAccountEntity.class);
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        UserEntity userEntity = getRememberAccount();
        if (userEntity != null) {
            builder.addHeader("Cookie", buildCookieStr(userEntity));
        }
        return chain.proceed(builder.build());
    }

    private UserEntity getRememberAccount() {
        List<RememberAccountEntity> accountEntityList = rememberAccount.query()
                .orderDesc(RememberAccountEntity_.loginTime)
                .build()
                .find();
        if (accountEntityList.size() != 0) {
            RememberAccountEntity accountEntity = accountEntityList.get(0);
            Long loginTime = accountEntity.getLoginTime();
            if ((loginTime + 7 * 24 * 60 * 60 * 1000) > System.currentTimeMillis()) {
                return accountEntity.getUserEntity().getTarget();
            }
        }
        return null;
    }

    /**
     * loginUserName=HoyangLau;
     * token_pass=36c8e03662df6fba143c10b2a218fcbc;
     * loginUserName_wanandroid_com=HoyangLau;
     * token_pass_wanandroid_com=36c8e03662df6fba143c10b2a218fcbc;
     *
     * @param userEntity
     * @return
     */
    private String buildCookieStr(UserEntity userEntity) {
        return String.format("loginUserName=%s;token_pass=%s;loginUserName_wanandroid_com=%s;token_pass_wanandroid_com=%s;",
                userEntity.getUsername(), userEntity.getToken(), userEntity.getUsername(), userEntity.getToken());
    }
}
