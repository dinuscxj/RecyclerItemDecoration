package app.dinus.com.itemdecoration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import android.graphics.Rect;
import android.support.annotation.IntDef;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.View;

/**
 * This class can only be used in the RecyclerView which use a GridLayoutManager
 * or StaggeredGridLayoutManager, but it's not always work for the StaggeredGridLayoutManager,
 * because we can't figure out which position should belong to the last column or the last row
 */
public class GridOffsetsItemDecoration extends RecyclerView.ItemDecoration {
  public static final int GRID_OFFSETS_HORIZONTAL = GridLayoutManager.HORIZONTAL;
  public static final int GRID_OFFSETS_VERTICAL = GridLayoutManager.VERTICAL;

  private final SparseArray<OffsetsCreator> mTypeOffsetsFactories = new SparseArray<>();

  @IntDef({
      GRID_OFFSETS_HORIZONTAL,
      GRID_OFFSETS_VERTICAL
  })
  @Retention(RetentionPolicy.SOURCE)
  private @interface Orientation {}

  @Orientation
  private int mOrientation;
  private int mVerticalItemOffsets;
  private int mHorizontalItemOffsets;

  public GridOffsetsItemDecoration(@Orientation int orientation) {
    setOrientation(orientation);
  }

  public void setOrientation(int orientation) {
    this.mOrientation = orientation;
  }

  public void setVerticalItemOffsets(int verticalItemOffsets) {
    this.mVerticalItemOffsets = verticalItemOffsets;
  }

  public void setHorizontalItemOffsets(int horizontalItemOffsets) {
    this.mHorizontalItemOffsets = horizontalItemOffsets;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    int spanCount = getSpanCount(parent);
    int childCount = parent.getAdapter().getItemCount();
    int adapterPosition = parent.getChildAdapterPosition(view);

    outRect.set(0, 0, getHorizontalOffsets(parent, view), getVerticalOffsets(parent, view));

    if (isLastRow(adapterPosition, spanCount, childCount)) {
      outRect.bottom = 0;
    }

    if (isLastColumn(adapterPosition, spanCount, childCount)) {
      outRect.right = 0;
    }
  }

  private int getHorizontalOffsets(RecyclerView parent, View view) {
    if (mTypeOffsetsFactories.size() == 0) {
      return mHorizontalItemOffsets;
    }

    final int adapterPosition = parent.getChildAdapterPosition(view);
    final int itemType = parent.getAdapter().getItemViewType(adapterPosition);
    final OffsetsCreator offsetsCreator = mTypeOffsetsFactories.get(itemType);

    if (offsetsCreator != null) {
      return offsetsCreator.createHorizontal(parent, adapterPosition);
    }

    return 0;
  }

  private int getVerticalOffsets(RecyclerView parent, View view) {
    if (mTypeOffsetsFactories.size() == 0) {
      return mVerticalItemOffsets;
    }

    final int adapterPosition = parent.getChildAdapterPosition(view);
    final int itemType = parent.getAdapter().getItemViewType(adapterPosition);
    final OffsetsCreator offsetsCreator = mTypeOffsetsFactories.get(itemType);

    if (offsetsCreator != null) {
      return offsetsCreator.createVertical(parent, adapterPosition);
    }

    return 0;
  }

  private boolean isLastColumn(int position, int spanCount, int childCount) {
    if (mOrientation == GRID_OFFSETS_VERTICAL) {
      return (position + 1) % spanCount == 0 ;
    } else {
      int lastColumnCount = childCount % spanCount;
      lastColumnCount = lastColumnCount == 0 ? spanCount : lastColumnCount;
      return position >= childCount - lastColumnCount;
    }
  }

  private boolean isLastRow(int position, int spanCount, int childCount) {
    if (mOrientation == GRID_OFFSETS_VERTICAL) {
      int lastColumnCount = childCount % spanCount;
      lastColumnCount = lastColumnCount == 0 ? spanCount : lastColumnCount;
      return position >= childCount - lastColumnCount;
    } else {
      return (position + 1) % spanCount == 0;
    }
  }

  private int getSpanCount(RecyclerView parent) {
    RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

    if (layoutManager instanceof GridLayoutManager) {
      return ((GridLayoutManager) layoutManager).getSpanCount();
    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
      return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
    } else {
      throw new UnsupportedOperationException("the GridDividerItemDecoration can only be used in " +
          "the RecyclerView which use a GridLayoutManager or StaggeredGridLayoutManager");
    }
  }

  public void registerTypeDrawable(int itemType, OffsetsCreator offsetsCreator) {
    mTypeOffsetsFactories.put(itemType, offsetsCreator);
  }

  public interface OffsetsCreator {
    int createVertical(RecyclerView parent, int adapterPosition);
    int createHorizontal(RecyclerView parent, int adapterPosition);
  }

}
