package app.dinus.com.itemdecoration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import android.graphics.Rect;
import android.support.annotation.IntDef;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * This class can only be used in the RecyclerView which use a LinearLayoutManager or
 * its subclass.
 */
public class LinearOffsetsItemDecoration extends RecyclerView.ItemDecoration{
  public static final int LINEAR_OFFSETS_HORIZONTAL = LinearLayoutManager.HORIZONTAL;
  public static final int LINEAR_OFFSETS_VERTICAL = LinearLayoutManager.VERTICAL;

  private final SparseArray<OffsetsCreator> mTypeOffsetsFactories = new SparseArray<>();

  @IntDef({
      LINEAR_OFFSETS_HORIZONTAL,
      LINEAR_OFFSETS_VERTICAL
  })
  @Retention(RetentionPolicy.SOURCE)
  private @interface Orientation {}

  @Orientation
  private int mOrientation;
  private int mItemOffsets;

  public LinearOffsetsItemDecoration(@Orientation int orientation) {
    mOrientation = orientation;
  }

  public void setOrientation(@Orientation int orientation) {
    mOrientation = orientation;
  }

  public void setItemOffsets(int itemOffsets) {
    this.mItemOffsets = itemOffsets;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    int adapterPosition = parent.getChildAdapterPosition(view);
    if (adapterPosition == parent.getAdapter().getItemCount() - 1) {
      return ;
    }

    if (mOrientation == LINEAR_OFFSETS_HORIZONTAL) {
      outRect.right = getDividerOffsets(parent, view);
    } else {
      outRect.bottom = getDividerOffsets(parent, view);
    }
  }

  private int getDividerOffsets(RecyclerView parent, View view) {
    if (mTypeOffsetsFactories.size() == 0) {
      return mItemOffsets;
    }

    final int adapterPosition = parent.getChildAdapterPosition(view);
    final int itemType = parent.getAdapter().getItemViewType(adapterPosition);
    final OffsetsCreator offsetsCreator = mTypeOffsetsFactories.get(itemType);

    if (offsetsCreator != null) {
      return offsetsCreator.create(parent, adapterPosition);
    }

    return 0;
  }

  public void registerTypeOffset(int itemType, OffsetsCreator offsetsCreator) {
    mTypeOffsetsFactories.put(itemType, offsetsCreator);
  }

  public interface OffsetsCreator {
    int create(RecyclerView parent, int adapterPosition);
  }
}
