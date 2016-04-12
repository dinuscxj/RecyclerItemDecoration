package app.dinus.com.recycleritemdecoration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.dinus.com.itemdecoration.ShaderItemDecoration;
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

public class ShaderActivity extends SingleFragmentActivity {

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, ShaderActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected Fragment createFragment() {
        return ShaderFragment.newInstance();
    }

    public static class ShaderFragment extends RecyclerListFragment {

        public static ShaderFragment newInstance() {
            return new ShaderFragment();
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
            return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        }

        @Override
        public RecyclerView.ItemDecoration onCreateItemDecoration() {
            return createShaderItemDecoration();
        }

        @NonNull
        private RecyclerView.ItemDecoration createShaderItemDecoration() {
            ShaderItemDecoration shaderItemDecoration = new ShaderItemDecoration(getActivity(),
                    ShaderItemDecoration.SHADER_LEFT | ShaderItemDecoration.SHADER_RIGHT);

            return shaderItemDecoration;
        }
    }
}
