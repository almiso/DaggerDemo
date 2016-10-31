package org.almiso.daggerdemo.core;

import android.app.Application;
import android.content.Context;

import org.almiso.daggerdemo.di.base.AppComponent;
import org.almiso.daggerdemo.di.base.AppModule;
import org.almiso.daggerdemo.di.base.DaggerAppComponent;
import org.almiso.daggerdemo.di.recycler.DaggerRecyclerComponent;
import org.almiso.daggerdemo.di.recycler.RecyclerComponent;
import org.almiso.daggerdemo.di.recycler.RecyclerModule;

public class App extends Application {

    /* Data */

    private static AppComponent appComponent;
    private static RecyclerComponent recyclerComponent;

    /* Common methods */

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = createAppComponent(this);
    }

    /* Public methods */

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static RecyclerComponent getRecyclerComponent() {
        if (recyclerComponent == null) {
            recyclerComponent = DaggerRecyclerComponent.builder()
                    .recyclerModule(new RecyclerModule())
                    .build();
        }
        return recyclerComponent;
    }

    /* Private methods */

    private AppComponent createAppComponent(Context context) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(context))
                .build();
    }
}