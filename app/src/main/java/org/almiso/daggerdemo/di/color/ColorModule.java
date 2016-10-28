package org.almiso.daggerdemo.di.color;

import android.support.annotation.NonNull;

import org.almiso.daggerdemo.core.presenter.ColorPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ColorModule {

    @Provides
    @NonNull
    @Singleton
    ColorPresenter providePresenter() {
        return new ColorPresenter();
    }
}