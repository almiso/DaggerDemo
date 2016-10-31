package org.almiso.daggerdemo.di.base;


import android.content.Context;

import org.almiso.daggerdemo.util.UserConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    UserConfig providePreferences(Context context) {
        return new UserConfig(context);
    }
}