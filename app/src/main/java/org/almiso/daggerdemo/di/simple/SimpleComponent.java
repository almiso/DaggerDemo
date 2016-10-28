package org.almiso.daggerdemo.di.simple;


import org.almiso.daggerdemo.ui.activity.SimpleSampleActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SimpleModule.class})
public interface SimpleComponent {

    void inject(SimpleSampleActivity activity);
}