package org.almiso.daggerdemo.di.base;


import org.almiso.daggerdemo.model.Customer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Customer providesCustomer() {
        return new Customer();
    }
}