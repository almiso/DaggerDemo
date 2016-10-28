package org.almiso.daggerdemo.di.color;


import org.almiso.daggerdemo.ui.fragment.ColorFragment;
import org.almiso.daggerdemo.ui.fragment.SelectorFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ColorModule.class})
public interface ColorComponent {

    void inject(SelectorFragment fragment);

    void inject(ColorFragment fragment);
}