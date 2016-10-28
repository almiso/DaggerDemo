package org.almiso.daggerdemo.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import org.almiso.daggerdemo.R;
import org.almiso.daggerdemo.core.App;

public class ScopeSampleActivity extends BaseActivity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.clearColorComponent();
    }

    /* Start methods */

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ScopeSampleActivity.class);
        activity.startActivity(intent);
    }

    /* Common methods */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scope_sample);

        initToolbar();
    }

    /* Initialisation methods */

    private void initToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(R.string.st_sample_scope);
        }
    }
}