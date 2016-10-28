package org.almiso.daggerdemo.di.recycler;


import org.almiso.daggerdemo.core.presenter.CustomersPresenter;
import org.almiso.daggerdemo.core.presenter.PresenterInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RecyclerModule {

    @Provides
    @Singleton
    CustomersPresenter provideController() {
        return new CustomersPresenter();
    }

    @Provides
    @Singleton
    PresenterInfo providePresenterInfo(CustomersPresenter presenter) {
        return new PresenterInfo(presenter);
    }
}