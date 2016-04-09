package app.dinus.com.itemdecoration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;

/**
 * This class can only be used in the RecyclerView which use a LinearLayoutManager or
 * its subclass.
 */
public class LinearDividerItemDecoration extends RecyclerView.ItemDecoration {
  private static final int[] ATTRS = new int[]{
      android.R.attr.listDivider
  };

  public static final Drawable EMPTY_DIVIDER = new ColorDrawable(Color.TRANSPARENT);

  public static final int LINEAR_DIVIDER_HORIZONTAL = LinearLayoutManager.HORIZONTAL;
  public static final int LINEAR_DIVIDER_VERTICAL = LinearLayoutManager.VERTICAL;

  private final SparseIntArray mDividerOffsets = new SparseIntArray();
  private final SparseArray<DrawableCreator> mTypeDrawableFactories = new SparseArray<>();

  @IntDef({
      LINEAR_DIVIDER_HORIZONTAL,
      LINEAR_DIVIDER_VERTICAL
  })
  @Retention(RetentionPolicy.SOURCE)
  private @interface Orientation {}

  @Orientation
  private int mOrientation;
  private Drawable mDivider;

  public LinearDividerItemDecoration(Context context, @Orientation int orientation) {
    resolveDivider(context);
    setOrientation(orientation);
  }

  private void resolveDivider(Context context) {
    final TypedArray a = context.obtainStyledAttributes(ATTRS);
    mDivider = a.getDrawable(0);
    a.recycle();
  }

  public void setOrientation(@Orientation int orientation) {
    mOrientation = orientation;
  }

  public void setDivider(Drawable divider) {
    this.mDivider = divider;
  }

  @Override
  public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    if (mOrientation == LINEAR_DIVIDER_VERTICAL) {
      drawVerticalDividers(c, parent);
    } else {
      drawHorizontalDividers(c, parent);
    }
  }

  public void drawVerticalDividers(Canvas c, RecyclerView parent) {
    final int left = parent.getPaddingLeft();
    final int right = parent.getWidth() - parent.getPaddingRight();

    final int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);
      final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
      final Drawable divider = getDivider(parent, params.getViewAdapterPosition());
      final int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
      final int bottom = top + divider.getIntrinsicHeight();

      mDividerOffsets.put(params.getViewAdapterPosition(), divider.getIntrinsicHeight());

      divider.setBounds(left, top, right, bottom);
      divider.draw(c);
    }
  }

  public void drawHorizontalDividers(Canvas c, RecyclerView parent) {
    final int top = parent.getPaddingTop();
    final int bottom = parent.getHeight() - parent.getPaddingBottom();

    final int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);
      final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
      final Drawable divider = getDivider(parent, params.getViewAdapterPosition());
      final int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
      final int right = left + divider.getIntrinsicHeight();

      mDividerOffsets.put(params.getViewAdapterPosition(), divider.getIntrinsicHeight());

      divider.setBounds(left, top, right, bottom);
      divider.draw(c);
    }
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    final int adapterPosition = parent.getChildAdapterPosition(view);
    if (adapterPosition == parent.getAdapter().getItemCount() - 1) {
      return ;
    }

    if (mDividerOffsets.indexOfKey(adapterPosition) < 0) {
      mDividerOffsets.put(adapterPosition, getDivider(parent, adapterPosition).getIntrinsicHeight());
    }

    if (mOrientation == LINEAR_DIVIDER_VERTICAL) {
      outRect.set(0, 0, 0, mDividerOffsets.get(parent.getChildAdapterPosition(view)));
    } else {
      outRect.set(0, 0, mDividerOffsets.get(parent.getChildAdapterPosition(view)), 0);
    }
  }

  private Drawable getDivider(RecyclerView parent, int adapterPosition) {
    final RecyclerView.Adapter adapter = parent.getAdapter();
    final int itemType = adapter.getItemViewType(adapterPosition);
    final DrawableCreator drawableCreator = mTypeDrawableFactories.get(itemType);

    if (drawableCreator != null) {
      return drawableCreator.create(parent, adapterPosition);
    }

    return mDivider;
  }

  public void registerTypeDrawable(int itemType, DrawableCreator drawableCreator) {
    mTypeDrawableFactories.put(itemType, drawableCreator);
  }

  public interface DrawableCreator {
    Drawable create(RecyclerView parent, int adapterPosition);
  }
}
