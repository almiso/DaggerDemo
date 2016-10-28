package org.almiso.daggerdemo.util;


import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    /* Constants */

    public static final String PREFS_NAME = "org.almiso.daggerdemo." + Preferences.class.getSimpleName();
    private static final String EXTRA_VISIBLE = "EXTRA_VISIBLE";

    /* Data */

    private final SharedPreferences prefs;

    /* Constructors */

    public Preferences(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, 0);
    }


    public boolean isVisited() {
        return prefs.getBoolean(EXTRA_VISIBLE, true);
    }

    public void setVisited(boolean value) {
        prefs.edit().putBoolean(EXTRA_VISIBLE, value).apply();
    }
}