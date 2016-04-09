package app.dinus.com.recycleritemdecoration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnGridDivider;
    private Button mBtnPinnedHeader;
    private Button mBtnLinearDivider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnGridDivider = (Button) findViewById(R.id.grid_offsets);
        mBtnPinnedHeader = (Button) findViewById(R.id.pinned_header);
        mBtnLinearDivider = (Button) findViewById(R.id.linear_divider);

        mBtnGridDivider.setOnClickListener(this);
        mBtnPinnedHeader.setOnClickListener(this);
        mBtnLinearDivider.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pinned_header:
                PinnedHeaderActivity.startActivity(MainActivity.this);
                break;
            case R.id.linear_divider:
                LinearDividerActivity.startActivity(MainActivity.this);
                break;
            case R.id.grid_offsets:
                GridOffsetsActivity.startActivity(MainActivity.this);
                break;
        }
    }
}
