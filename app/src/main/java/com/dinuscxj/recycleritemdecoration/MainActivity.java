package com.dinuscxj.recycleritemdecoration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnShader;
    private Button mBtnGridDivider;
    private Button mBtnPinnedHeader;
    private Button mBtnLinearDivider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnShader = (Button) findViewById(R.id.shader);
        mBtnGridDivider = (Button) findViewById(R.id.grid_offsets);
        mBtnPinnedHeader = (Button) findViewById(R.id.pinned_header);
        mBtnLinearDivider = (Button) findViewById(R.id.linear_divider);

        mBtnShader.setOnClickListener(this);
        mBtnGridDivider.setOnClickListener(this);
        mBtnPinnedHeader.setOnClickListener(this);
        mBtnLinearDivider.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shader:
                ShaderActivity.startActivity(MainActivity.this);
                break;
            case R.id.pinned_header:
                PinnedHeaderActivity.startActivity(MainActivity.this);
                break;
            case R.id.linear_divider:
                DividerActivity.startActivity(MainActivity.this);
                break;
            case R.id.grid_offsets:
                OffsetsActivity.startActivity(MainActivity.this);
                break;
        }
    }
}
