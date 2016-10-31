package org.almiso.daggerdemo.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.IntDef;

import org.almiso.daggerdemo.model.UiUpdateEvent;
import org.greenrobot.eventbus.EventBus;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class UserConfig {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({RED, YELLOW, GREEN})
    public @interface Color {
    }

    public static final int RED = 0;
    public static final int YELLOW = 1;
    public static final int GREEN = 2;

    /* Constants */

    private static final String EXTRA_SIGN_IN = "EXTRA_SIGN_IN";
    private static final String EXTRA_CURRENT_COLOR = "EXTRA_CURRENT_COLOR";

    /* Data */

    private final SharedPreferences preferences;

    private boolean isSignIn;

    @Color
    private int color;

    /* Constructors */

    public UserConfig(Context context) {
        String PREFS_NAME = context.getPackageName() + UserConfig.class.getSimpleName();
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        isSignIn = preferences.getBoolean(EXTRA_SIGN_IN, false);
        color = preferences.getInt(EXTRA_CURRENT_COLOR, RED);
    }

    /* Sign in */

    public boolean isSignIn() {
        return isSignIn;
    }

    public void setSignIn(boolean value) {
        if (isSignIn == value) {
            return;
        }

        isSignIn = value;
        preferences.edit().putBoolean(EXTRA_SIGN_IN, value).apply();
        clearCurrentColor();
        EventBus.getDefault().post(new UiUpdateEvent(UiUpdateEvent.onSignStateUpdated));
    }

    /* Color */

    @Color
    public int getColor() {
        return color;
    }

    public void setColor(@Color int newColor) {
        if (!isSignIn()) {
            return;
        }

        if (color != newColor) {
            color = newColor;
            preferences.edit().putInt(EXTRA_CURRENT_COLOR, newColor).apply();
            EventBus.getDefault().post(new UiUpdateEvent(UiUpdateEvent.onColorUpdated));
        }
    }

    private void clearCurrentColor() {
        setColor(RED);
    }
}