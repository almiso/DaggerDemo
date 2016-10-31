package org.almiso.daggerdemo.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.almiso.daggerdemo.R;
import org.almiso.daggerdemo.core.App;
import org.almiso.daggerdemo.util.UserConfig;

import javax.inject.Inject;

public class ScopeSampleActivity extends BaseActivity {

    @Inject
    UserConfig userConfig;

    /* Start methods */

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ScopeSampleActivity.class);
        activity.startActivity(intent);
    }

    /* Common methods */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_scope_activity, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (userConfig.isSignIn()) {
            menu.findItem(R.id.menu_sign).setIcon(android.R.drawable.ic_media_pause);
            menu.findItem(R.id.menu_sign).setTitle(R.string.st_sign_out);
        } else {
            menu.findItem(R.id.menu_sign).setIcon(android.R.drawable.ic_media_play);
            menu.findItem(R.id.menu_sign).setTitle(R.string.st_sign_in);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sign:
                userConfig.setSignIn(!userConfig.isSignIn());
                invalidateOptionsMenu();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scope_sample);

        App.getAppComponent().inject(this);

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