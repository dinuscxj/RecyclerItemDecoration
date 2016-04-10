package app.dinus.com.itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

public class ShaderItemDecoration extends RecyclerView.ItemDecoration {
    private static final int DEFAULT_SHADER_DISTANCE_DP = 88;
    private static final int SHADER_START_COLOR = Color.parseColor("#FF3c3c3c");
    private static final int SHADER_END_COLOR = Color.parseColor("#00000000");

    public static final int SHADER_TOP = 0x01;
    public static final int SHADER_BOTTOM = 0x02;

    @IntDef(value = {SHADER_BOTTOM, SHADER_TOP}, flag = true)
    private @interface ShaderType {}

    @ShaderType
    private int mShaderType;
    private int mShaderTopDistance;
    private int mShaderBottomDistance;

    private Shader mTopShader;
    private Shader mBottomShader;
    private ShaderCreator mTopShaderCreator;
    private ShaderCreator mBottomShaderCreator;

    public ShaderItemDecoration(Context context, @ShaderType int shaderType) {
        initShaderItemDecoration(context);
        this.mShaderType = shaderType;
    }

    private void initShaderItemDecoration(Context context) {
        mShaderTopDistance = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_SHADER_DISTANCE_DP, context.getResources().getDisplayMetrics());
        mShaderBottomDistance = mShaderTopDistance;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if ((mShaderType & SHADER_BOTTOM) != 0) {
            drawBottomShader(c, parent);
        }

        if ((mShaderType & SHADER_TOP) != 0) {
            drawTopShader(c, parent);
        }
    }

    private void drawTopShader(Canvas c, RecyclerView parent) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(getTopShader(parent));

        c.drawRect(0, 0, parent.getWidth(), mShaderTopDistance, paint);
    }

    private Shader getTopShader(RecyclerView parent) {
        if (mTopShader == null) {
            if (mTopShaderCreator != null) {
                mTopShader = mTopShaderCreator.createShader(parent);
            } else {
                mTopShader = new LinearGradient(0, 0, 0, mShaderTopDistance,
                        SHADER_START_COLOR, SHADER_END_COLOR, Shader.TileMode.CLAMP);
            }
        }

        return mTopShader;
    }

    private void drawBottomShader(Canvas c, RecyclerView parent) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(getBottomShader(parent));

        c.drawRect(0, parent.getHeight() - mShaderBottomDistance, parent.getWidth(), parent.getHeight(), paint);
    }

    private Shader getBottomShader(RecyclerView parent) {
        if (mBottomShader == null) {
            if (mBottomShaderCreator != null) {
                mBottomShader = mBottomShaderCreator.createShader(parent);
            } else {
                mBottomShader = new LinearGradient(0, parent.getHeight(), 0, parent.getHeight() - mShaderBottomDistance,
                        SHADER_START_COLOR, SHADER_END_COLOR, Shader.TileMode.CLAMP);
            }
        }

        return mBottomShader;
    }

    public void setShaderBottomDistance(int shaderBottomDistance) {
        this.mShaderBottomDistance = shaderBottomDistance;
    }

    public void setShaderTopDistance(int shaderTopDistance) {
        this.mShaderTopDistance = shaderTopDistance;
    }

    public void registerTopShaderCreator(ShaderCreator mTopShaderCreator) {
        this.mTopShaderCreator = mTopShaderCreator;
    }

    public void registerBottomShaderCreator(ShaderCreator mBottomShaderCreator) {
        this.mBottomShaderCreator = mBottomShaderCreator;
    }

    public interface ShaderCreator {
        Shader createShader(RecyclerView parent);
    }
}
