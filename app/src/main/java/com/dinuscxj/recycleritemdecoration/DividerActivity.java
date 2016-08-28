package com.dinuscxj.recycleritemdecoration;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dinuscxj.itemdecoration.LinearDividerItemDecoration;
import com.dinuscxj.itemdecoration.LinearOffsetsItemDecoration;
import com.dinuscxj.recycleritemdecoration.foundation.RecyclerListAdapter;
import com.dinuscxj.recycleritemdecoration.foundation.RecyclerListFragment;
import com.dinuscxj.recycleritemdecoration.foundation.SingleFragmentActivity;
import com.dinuscxj.recycleritemdecoration.model.ItemAnimal;
import com.dinuscxj.recycleritemdecoration.model.ItemCartoon;
import com.dinuscxj.recycleritemdecoration.model.ItemDrawable;
import com.dinuscxj.recycleritemdecoration.model.ItemScenic;
import com.dinuscxj.recycleritemdecoration.viewholder.AnimalViewHolder;
import com.dinuscxj.recycleritemdecoration.viewholder.CartoonViewHolder;
import com.dinuscxj.recycleritemdecoration.viewholder.ScenicViewHolder;

public class DividerActivity extends SingleFragmentActivity {

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, DividerActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected Fragment createFragment() {
        return LinearDividerFragment.newInstance();
    }

    public static class LinearDividerFragment extends RecyclerListFragment {

        public static LinearDividerFragment newInstance() {
            return new LinearDividerFragment();
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            getAdapter().addAll(createItemDrawableList());
        }

        private List<ItemDrawable> createItemDrawableList() {
            List<ItemDrawable> itemDrawableList = new ArrayList<>();
            // ItemAnimal
            itemDrawableList.add(new ItemAnimal(R.drawable.ic_animal0, getString(R.string.animal0)));
            itemDrawableList.add(new ItemAnimal(R.drawable.ic_animal1, getString(R.string.animal1)));
            itemDrawableList.add(new ItemAnimal(R.drawable.ic_animal2, getString(R.string.animal2)));
            // ItemCartoon
            itemDrawableList.add(new ItemCartoon(R.drawable.ic_cartoon0, getString(R.string.cartoon0)));
            itemDrawableList.add(new ItemCartoon(R.drawable.ic_cartoon1, getString(R.string.cartoon1)));
            itemDrawableList.add(new ItemCartoon(R.drawable.ic_cartoon2, getString(R.string.cartoon2)));
            // ItemScenic
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

                    addViewType(ItemScenic.class, new ViewHolderFactory<RecyclerListAdapter.ViewHolder>() {
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
            return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        }

        @Override
        public RecyclerView.ItemDecoration onCreateItemDecoration() {
            return createDividerItemDecoration();
        }

        @NonNull
        private RecyclerView.ItemDecoration createDividerItemDecoration() {
            LinearDividerItemDecoration dividerItemDecoration = new LinearDividerItemDecoration(
                    getActivity(), LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL);

            dividerItemDecoration.registerTypeDrawable(getItemViewType(ItemAnimal.class),
                    new LinearDividerItemDecoration.DrawableCreator() {
                        @Override
                        public Drawable create(RecyclerView parent, int adapterPosition) {
                            return getResources().getDrawable(R.drawable.bg_animal_divider);
                        }
                    });

            dividerItemDecoration.registerTypeDrawable(getItemViewType(ItemCartoon.class),
                    new LinearDividerItemDecoration.DrawableCreator() {
                        @Override
                        public Drawable create(RecyclerView parent, int adapterPosition) {
                            return getResources().getDrawable(R.drawable.bg_cartoon_divider);
                        }
                    });

            dividerItemDecoration.registerTypeDrawable(getItemViewType(ItemScenic.class),
                    new LinearDividerItemDecoration.DrawableCreator() {
                        @Override
                        public Drawable create(RecyclerView parent, int adapterPosition) {
                            return getResources().getDrawable(R.drawable.bg_scenic_divider);
                        }
                    });

            return dividerItemDecoration;
        }
    }
}
