package org.almiso.daggerdemo.di.simple;

import java.util.Random;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SimpleModule {

    @Provides
    @Singleton
    Random providesRandom() {
        return new Random();
//        return new MockRandom();
    }
}