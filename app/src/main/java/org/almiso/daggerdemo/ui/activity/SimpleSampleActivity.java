package org.almiso.daggerdemo.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import org.almiso.daggerdemo.R;
import org.almiso.daggerdemo.di.simple.DaggerSimpleComponent;
import org.almiso.daggerdemo.di.simple.SimpleComponent;
import org.almiso.daggerdemo.di.simple.SimpleModule;

import java.util.Random;

import javax.inject.Inject;

public class SimpleSampleActivity extends BaseActivity {

    /* Data */

    @Inject
    Random random;

    /* Views */

    private AppCompatTextView tvRandom;

    /* Start methods */

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, SimpleSampleActivity.class);
        activity.startActivity(intent);
    }

    /* Common methods */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_sample);

        initToolbar();
        initViews();
    }

    /* Initialisation methods */

    private void initToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(R.string.st_simple_injection);
        }
    }

    private void initViews() {
        SimpleComponent component = DaggerSimpleComponent.builder()
                .simpleModule(new SimpleModule())
                .build();
        component.inject(this);

        findViewById(R.id.button_next_random).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRandomText();
            }
        });

        tvRandom = (AppCompatTextView) findViewById(R.id.tv_random_number);
    }

    /* Private methods */

    private void updateRandomText() {
        String nextRandom = getString(R.string.st_random_number, random.nextInt());
        tvRandom.setText(nextRandom);
    }
}
