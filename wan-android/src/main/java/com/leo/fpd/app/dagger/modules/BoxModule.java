package com.leo.fpd.app.dagger.modules;

import com.leo.base.dagger.annotation.AppScope;
import com.leo.fpd.entities.RememberAccountEntity;
import com.leo.fpd.entities.UserEntity;
import com.leo.fpd.objectBox.ObjectBoxTools;

import dagger.Module;
import dagger.Provides;
import io.objectbox.Box;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 17:35
 * Desc:
 */
@Module
public class BoxModule {

    @AppScope
    @Provides
    public Box<UserEntity> provideUserEntityBox() {
        return ObjectBoxTools.get().boxFor(UserEntity.class);
    }

    @AppScope
    @Provides
    public Box<RememberAccountEntity> providerRememberAccountEntityBox() {
        return ObjectBoxTools.get().boxFor(RememberAccountEntity.class);
    }


}
