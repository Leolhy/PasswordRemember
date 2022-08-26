package com.leo.prb.dagger.modules;

import com.leo.prb.entities.AccountEntity;
import com.leo.prb.entities.AccountTypeEntity;
import com.leo.prb.objectBox.ObjectBoxTools;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.objectbox.Box;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/15 17:38
 * Desc:
 */
@Module
public class BoxModule {

    @Singleton
    @Provides
    public Box<AccountTypeEntity> provideAccountTypeBox() {
        return ObjectBoxTools.get().boxFor(AccountTypeEntity.class);
    }

    @Singleton
    @Provides
    public Box<AccountEntity> provideAccountBox() {
        return ObjectBoxTools.get().boxFor(AccountEntity.class);
    }

}
