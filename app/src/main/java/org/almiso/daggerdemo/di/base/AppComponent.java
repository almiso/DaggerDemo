package org.almiso.daggerdemo.di.base;


import android.content.Context;

import org.almiso.daggerdemo.ui.activity.ScopeSampleActivity;
import org.almiso.daggerdemo.ui.fragment.ColorFragment;
import org.almiso.daggerdemo.ui.fragment.SelectorFragment;
import org.almiso.daggerdemo.util.UserConfig;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(ScopeSampleActivity activity);

    void inject(ColorFragment activity);

    void inject(SelectorFragment activity);

    Context context();

    UserConfig userConfig();
}