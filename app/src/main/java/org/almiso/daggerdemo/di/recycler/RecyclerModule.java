package org.almiso.daggerdemo.di.recycler;


import android.support.annotation.NonNull;

import org.almiso.daggerdemo.core.presenter.CustomersPresenter;
import org.almiso.daggerdemo.core.presenter.PresenterInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RecyclerModule {

    @Provides
    @NonNull
    @Singleton
    CustomersPresenter provideController() {
        return new CustomersPresenter();
    }

    @Provides
    @NonNull
    @Singleton
    PresenterInfo providePresenterInfo(CustomersPresenter presenter) {
        return new PresenterInfo(presenter);
    }
}