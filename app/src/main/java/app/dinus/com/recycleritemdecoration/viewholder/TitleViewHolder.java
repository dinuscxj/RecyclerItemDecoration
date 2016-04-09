package app.dinus.com.recycleritemdecoration.viewholder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.dinus.com.recycleritemdecoration.R;
import app.dinus.com.recycleritemdecoration.foundation.RecyclerListAdapter;
import app.dinus.com.recycleritemdecoration.model.ItemTitle;

public class TitleViewHolder extends RecyclerListAdapter.ViewHolder<ItemTitle> {
    private ImageView mIvTitleImage;
    private TextView mTvTitleName;

    public TitleViewHolder(@NonNull ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_title_layout, parent, false));
        mTvTitleName = (TextView) itemView.findViewById(R.id.title_name);
        mIvTitleImage = (ImageView) itemView.findViewById(R.id.title_image);
    }

    @Override
    public void bind(ItemTitle item, int position) {
        mTvTitleName.setText(item.getTitleName());
        mIvTitleImage.setImageResource(item.getDrawableRes());
    }
}
