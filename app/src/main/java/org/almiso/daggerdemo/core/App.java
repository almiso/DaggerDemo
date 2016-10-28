package org.almiso.daggerdemo.core;

import android.app.Application;

import org.almiso.daggerdemo.di.base.AppComponent;
import org.almiso.daggerdemo.di.base.AppModule;
import org.almiso.daggerdemo.di.base.DaggerAppComponent;
import org.almiso.daggerdemo.di.color.ColorComponent;
import org.almiso.daggerdemo.di.color.ColorModule;
import org.almiso.daggerdemo.di.color.DaggerColorComponent;
import org.almiso.daggerdemo.di.recycler.DaggerRecyclerComponent;
import org.almiso.daggerdemo.di.recycler.RecyclerComponent;
import org.almiso.daggerdemo.di.recycler.RecyclerModule;

public class App extends Application {

    /* Data */

    private static AppComponent appComponent;
    private static RecyclerComponent recyclerComponent;
    private static ColorComponent colorComponent;

    /* Common methods */

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /* Public methods */

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule())
                    .build();
        }
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

    public static ColorComponent getColorComponent() {
        if (colorComponent == null) {
            colorComponent = DaggerColorComponent.builder()
                    .colorModule(new ColorModule())
                    .build();
        }

        return colorComponent;
    }

    public static void clearColorComponent() {
        colorComponent = null;
    }
}