package com.dinuscxj.recycleritemdecoration.foundation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.dinuscxj.recycleritemdecoration.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected int getLayoutId() {
        return R.layout.activity_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        replaceFragment();
    }

    protected void replaceFragment() {
        Fragment fragment = createFragment();

        if (fragment == null) {
            return;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, createFragment())
                .commit();
    }

    protected abstract Fragment createFragment();
}