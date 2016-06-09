package com.dinuscxj.recycleritemdecoration.viewholder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dinuscxj.recycleritemdecoration.R;
import com.dinuscxj.recycleritemdecoration.foundation.RecyclerListAdapter;
import com.dinuscxj.recycleritemdecoration.model.ItemAnimal;

public class AnimalViewHolder extends RecyclerListAdapter.ViewHolder<ItemAnimal> {
    private ImageView mIvAnimalImage;
    private TextView mTvAnimalName;

    public AnimalViewHolder(@NonNull ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_image_layout, parent, false));
        mTvAnimalName = (TextView) itemView.findViewById(R.id.item_name);
        mIvAnimalImage = (ImageView) itemView.findViewById(R.id.item_image);
    }

    @Override
    public void bind(ItemAnimal item, int position) {
        mTvAnimalName.setText(item.getAnimalName());
        mIvAnimalImage.setImageResource(item.getDrawableRes());
    }
}
