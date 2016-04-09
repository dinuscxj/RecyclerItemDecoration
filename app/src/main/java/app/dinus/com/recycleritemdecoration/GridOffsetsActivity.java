package app.dinus.com.recycleritemdecoration;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import app.dinus.com.itemdecoration.GridDividerItemDecoration;
import app.dinus.com.itemdecoration.GridOffsetsItemDecoration;
import app.dinus.com.recycleritemdecoration.foundation.RecyclerListAdapter;
import app.dinus.com.recycleritemdecoration.foundation.RecyclerListFragment;
import app.dinus.com.recycleritemdecoration.foundation.SingleFragmentActivity;
import app.dinus.com.recycleritemdecoration.model.ItemAnimal;
import app.dinus.com.recycleritemdecoration.model.ItemCartoon;
import app.dinus.com.recycleritemdecoration.model.ItemDrawable;
import app.dinus.com.recycleritemdecoration.model.ItemScenic;
import app.dinus.com.recycleritemdecoration.viewholder.AnimalViewHolder;
import app.dinus.com.recycleritemdecoration.viewholder.CartoonViewHolder;
import app.dinus.com.recycleritemdecoration.viewholder.ScenicViewHolder;

public class GridOffsetsActivity extends SingleFragmentActivity {

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, GridOffsetsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected Fragment createFragment() {
        return GridDividerFragment.newInstance();
    }

    public static class GridDividerFragment extends RecyclerListFragment {

        public static GridDividerFragment newInstance() {
            return new GridDividerFragment();
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            getAdapter().addAll(createItemDrawableList());
        }

        private List<ItemDrawable> createItemDrawableList() {
            List<ItemDrawable> itemDrawableList = new ArrayList<>();
            //ItemAnimal
            itemDrawableList.add(new ItemAnimal(R.drawable.ic_animal0, getString(R.string.animal0)));
            itemDrawableList.add(new ItemAnimal(R.drawable.ic_animal1, getString(R.string.animal1)));
            itemDrawableList.add(new ItemAnimal(R.drawable.ic_animal2, getString(R.string.animal2)));
            //ItemCartoon
            itemDrawableList.add(new ItemCartoon(R.drawable.ic_cartoon0, getString(R.string.cartoon0)));
            itemDrawableList.add(new ItemCartoon(R.drawable.ic_cartoon1, getString(R.string.cartoon1)));
            itemDrawableList.add(new ItemCartoon(R.drawable.ic_cartoon2, getString(R.string.cartoon2)));
            //ItemScenic
            itemDrawableList.add(new ItemScenic(R.drawable.ic_scenic0, getString(R.string.scenic0)));
            itemDrawableList.add(new ItemScenic(R.drawable.ic_scenic1, getString(R.string.scenic1)));
            itemDrawableList.add(new ItemScenic(R.drawable.ic_scenic2, getString(R.string.scenic2)));

            return itemDrawableList;
        }

        @Override
        public RecyclerListAdapter onCreateAdapter() {
            return new RecyclerListAdapter() {
                {
                    addViewType(ItemAnimal.class, new ViewHolderFactory<ViewHolder>() {
                        @Override
                        public ViewHolder onCreateViewHolder(ViewGroup parent) {
                            return new AnimalViewHolder(parent);
                        }
                    });

                    addViewType(ItemCartoon.class, new ViewHolderFactory<ViewHolder>() {
                        @Override
                        public ViewHolder onCreateViewHolder(ViewGroup parent) {
                            return new CartoonViewHolder(parent);
                        }
                    });

                    addViewType(ItemScenic.class, new ViewHolderFactory<ViewHolder>() {
                        @Override
                        public ViewHolder onCreateViewHolder(ViewGroup parent) {
                            return new ScenicViewHolder(parent);
                        }
                    });
                }
            };
        }

        @Override
        public RecyclerView.LayoutManager onCreateLayoutManager() {
            return new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        }

        @Override
        public RecyclerView.ItemDecoration onCreateItemDecoration() {
//            return createDividerItemDecoration();
            return createOffsetsItemDecoration();
        }

        private RecyclerView.ItemDecoration createDividerItemDecoration() {
            GridDividerItemDecoration dividerItemDecoration = new GridDividerItemDecoration(getActivity(),
                    GridDividerItemDecoration.GRID_DIVIDER_HORIZONTAL);

            dividerItemDecoration.registerTypeDrawable(getItemViewType(ItemAnimal.class),
                    new GridDividerItemDecoration.DrawableCreator() {
                        @Override
                        public Drawable createVertical(RecyclerView parent, int adapterPosition) {
                            return getResources().getDrawable(R.drawable.bg_animal_divider);
                        }

                        @Override
                        public Drawable createHorizontal(RecyclerView parent, int adapterPosition) {
                            return getResources().getDrawable(R.drawable.bg_animal_divider);
                        }
                    });

            dividerItemDecoration.registerTypeDrawable(getItemViewType(ItemCartoon.class),
                    new GridDividerItemDecoration.DrawableCreator() {
                        @Override
                        public Drawable createVertical(RecyclerView parent, int adapterPosition) {
                            return getResources().getDrawable(R.drawable.bg_cartoon_divider);
                        }

                        @Override
                        public Drawable createHorizontal(RecyclerView parent, int adapterPosition) {
                            return getResources().getDrawable(R.drawable.bg_cartoon_divider);
                        }
                    });

            dividerItemDecoration.registerTypeDrawable(getItemViewType(ItemScenic.class),
                    new GridDividerItemDecoration.DrawableCreator() {
                        @Override
                        public Drawable createVertical(RecyclerView parent, int adapterPosition) {
                            return getResources().getDrawable(R.drawable.bg_scenic_divider);
                        }

                        @Override
                        public Drawable createHorizontal(RecyclerView parent, int adapterPosition) {
                            return getResources().getDrawable(R.drawable.bg_scenic_divider);
                        }
                    });

            return dividerItemDecoration;
        }

        private RecyclerView.ItemDecoration createOffsetsItemDecoration() {
            GridOffsetsItemDecoration dividerItemDecoration = new GridOffsetsItemDecoration(
                    GridOffsetsItemDecoration.GRID_OFFSETS_HORIZONTAL);

            dividerItemDecoration.registerTypeDrawable(getItemViewType(ItemAnimal.class),
                    new GridOffsetsItemDecoration.OffsetsCreator() {
                        @Override
                        public int createVertical(RecyclerView parent, int adapterPosition) {
                            return 30;
                        }

                        @Override
                        public int createHorizontal(RecyclerView parent, int adapterPosition) {
                            return 30;
                        }
                    });

            dividerItemDecoration.registerTypeDrawable(getItemViewType(ItemCartoon.class),
                    new GridOffsetsItemDecoration.OffsetsCreator() {
                        @Override
                        public int createVertical(RecyclerView parent, int adapterPosition) {
                            return 60;
                        }

                        @Override
                        public int createHorizontal(RecyclerView parent, int adapterPosition) {
                            return 60;
                        }
                    });

            dividerItemDecoration.registerTypeDrawable(getItemViewType(ItemScenic.class),
                    new GridOffsetsItemDecoration.OffsetsCreator() {
                        @Override
                        public int createVertical(RecyclerView parent, int adapterPosition) {
                            return 90;
                        }

                        @Override
                        public int createHorizontal(RecyclerView parent, int adapterPosition) {
                            return 90;
                        }
                    });

            return dividerItemDecoration;
        }
    }
}
