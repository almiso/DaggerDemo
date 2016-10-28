package org.almiso.daggerdemo.ui.activity;

import android.os.Bundle;
import android.view.View;

import org.almiso.daggerdemo.R;

/**
 * First launch activity.
 * Contains links for other samples.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_sample_simple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleSampleActivity.start(MainActivity.this);
            }
        });

        findViewById(R.id.button_sample_recycler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerSampleActivity.start(MainActivity.this);
            }
        });

        findViewById(R.id.button_sample_scope).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScopeSampleActivity.start(MainActivity.this);
            }
        });
    }
}