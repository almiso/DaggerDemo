package org.almiso.daggerdemo.di.recycler;


import org.almiso.daggerdemo.ui.adapter.CustomersAdapter;
import org.almiso.daggerdemo.ui.activity.RecyclerSampleActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RecyclerModule.class})
public interface RecyclerComponent {

    void inject(RecyclerSampleActivity activity);

    void inject(CustomersAdapter adapter);
}
