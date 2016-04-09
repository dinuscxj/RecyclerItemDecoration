package app.dinus.com.recycleritemdecoration.viewholder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.dinus.com.recycleritemdecoration.R;
import app.dinus.com.recycleritemdecoration.foundation.RecyclerListAdapter;
import app.dinus.com.recycleritemdecoration.model.ItemScenic;

public class ScenicViewHolder extends RecyclerListAdapter.ViewHolder<ItemScenic> {
    private ImageView mIvScenicImage;
    private TextView mTvScenicName;

    public ScenicViewHolder(@NonNull ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_image_layout, parent, false));
        mTvScenicName = (TextView) itemView.findViewById(R.id.item_name);
        mIvScenicImage = (ImageView) itemView.findViewById(R.id.item_image);
    }

    @Override
    public void bind(ItemScenic item, int position) {
        mTvScenicName.setText(item.getScenicName());
        mIvScenicImage.setImageResource(item.getDrawableRes());
    }
}
