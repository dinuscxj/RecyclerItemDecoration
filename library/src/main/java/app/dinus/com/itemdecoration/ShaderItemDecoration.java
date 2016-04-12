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
    private static final int DEFAULT_SHADER_VERTICAL_DISTANCE_DP = 88;
    private static final int DEFAULT_SHADER_HORIZONTAL_DISTANCE_DP = 66;
    private static final int SHADER_START_COLOR = Color.parseColor("#FF3c3c3c");
    private static final int SHADER_END_COLOR = Color.parseColor("#00000000");

    public static final int SHADER_TOP = 0x01;
    public static final int SHADER_BOTTOM = 0x02;
    public static final int SHADER_LEFT = 0x04;
    public static final int SHADER_RIGHT = 0x08;

    @IntDef(value = {SHADER_BOTTOM, SHADER_TOP, SHADER_LEFT, SHADER_RIGHT}, flag = true)
    private @interface ShaderType {}

    @ShaderType
    private int mShaderType;
    private int mShaderTopDistance;
    private int mShaderBottomDistance;
    private int mShaderLeftDistance;
    private int mShaderRightDistance;

    private Shader mTopShader;
    private Shader mBottomShader;
    private Shader mLeftShader;
    private Shader mRightShader;
    private ShaderCreator mTopShaderCreator;
    private ShaderCreator mBottomShaderCreator;
    private ShaderCreator mLeftShaderCreator;
    private ShaderCreator mRightShaderCreator;

    public ShaderItemDecoration(Context context, @ShaderType int shaderType) {
        initShaderItemDecoration(context);
        this.mShaderType = shaderType;
    }

    private void initShaderItemDecoration(Context context) {
        mShaderTopDistance = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_SHADER_VERTICAL_DISTANCE_DP, context.getResources().getDisplayMetrics());
        mShaderBottomDistance = mShaderTopDistance;

        mShaderLeftDistance   = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_SHADER_HORIZONTAL_DISTANCE_DP, context.getResources().getDisplayMetrics());
        mShaderRightDistance  = mShaderLeftDistance;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if ((mShaderType & SHADER_BOTTOM) != 0) {
            drawBottomShader(c, parent);
        }

        if ((mShaderType & SHADER_TOP) != 0) {
            drawTopShader(c, parent);
        }

        if ((mShaderType & SHADER_LEFT) != 0) {
            drawLeftShader(c, parent);
        }

        if ((mShaderType & SHADER_RIGHT) != 0) {
            drawRightShader(c, parent);
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

    private void drawLeftShader(Canvas c, RecyclerView parent) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(getLeftShader(parent));

        c.drawRect(0, 0, mShaderLeftDistance, parent.getHeight(), paint);
    }

    private Shader getLeftShader(RecyclerView parent) {
        if (mLeftShader == null) {
            if (mLeftShaderCreator != null) {
                mLeftShader = mLeftShaderCreator.createShader(parent);
            } else {
                mLeftShader = new LinearGradient(0, 0, mShaderLeftDistance, 0,
                        SHADER_START_COLOR, SHADER_END_COLOR, Shader.TileMode.CLAMP);
            }
        }

        return mLeftShader;
    }

    private void drawRightShader(Canvas c, RecyclerView parent) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(getRightShader(parent));

        c.drawRect(parent.getWidth() - mShaderRightDistance, 0, parent.getWidth(), parent.getHeight(), paint);
    }

    private Shader getRightShader(RecyclerView parent) {
        if (mRightShader == null) {
            if (mRightShaderCreator != null) {
                mRightShader = mRightShaderCreator.createShader(parent);
            } else {
                mRightShader = new LinearGradient(parent.getWidth(), 0, parent.getWidth() - mShaderRightDistance, 0,
                        SHADER_START_COLOR, SHADER_END_COLOR, Shader.TileMode.CLAMP);
            }
        }

        return mRightShader;
    }

    public void setShaderBottomDistance(int shaderBottomDistance) {
        this.mShaderBottomDistance = shaderBottomDistance;
    }

    public void setShaderTopDistance(int shaderTopDistance) {
        this.mShaderTopDistance = shaderTopDistance;
    }

    public void setShaderLeftDistance(int shaderLeftDistance) {
        this.mShaderLeftDistance = shaderLeftDistance;
    }

    public void setShaderRightDistance(int shaderRightDistance) {
        this.mShaderRightDistance = shaderRightDistance;
    }

    public void registerTopShaderCreator(ShaderCreator mTopShaderCreator) {
        this.mTopShaderCreator = mTopShaderCreator;
    }

    public void registerBottomShaderCreator(ShaderCreator mBottomShaderCreator) {
        this.mBottomShaderCreator = mBottomShaderCreator;
    }

    public void registerLeftShaderCreator(ShaderCreator leftShaderCreator) {
        this.mLeftShaderCreator = leftShaderCreator;
    }

    public void registerRightShaderCreator(ShaderCreator rightShaderCreator) {
        this.mRightShaderCreator = rightShaderCreator;
    }

    public interface ShaderCreator {
        Shader createShader(RecyclerView parent);
    }
}
