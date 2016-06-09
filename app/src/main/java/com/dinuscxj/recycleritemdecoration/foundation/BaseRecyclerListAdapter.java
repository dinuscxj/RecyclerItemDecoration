package com.dinuscxj.recycleritemdecoration.foundation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

public abstract class BaseRecyclerListAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<T> mItemList = new ArrayList<>();

    public BaseRecyclerListAdapter() {

    }

    public BaseRecyclerListAdapter(@NonNull T[] arrays) {
        if (arrays == null) {
            throw new IllegalArgumentException("don't pass null in");
        }
        mItemList.addAll(Arrays.asList(arrays));
    }

    public BaseRecyclerListAdapter(@NonNull Collection<T> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("don't pass null in");
        }
        mItemList.addAll(collection);
    }

    public BaseRecyclerListAdapter(@NonNull List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("don't pass null in");
        }
        mItemList = list;
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public List<T> getItemList() {
        return mItemList;
    }

    public void setItemList(List<T> itemList) {
        mItemList = itemList;
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return mItemList.get(position);
    }

    public BaseRecyclerListAdapter<T, VH> add(@NonNull T item) {
        mItemList.add(item);
        notifyDataSetChanged();
        return this;
    }

    public BaseRecyclerListAdapter<T, VH> addAll(@NonNull T[] items) {
        mItemList.addAll(Arrays.asList(items));
        notifyDataSetChanged();
        return this;
    }

    public BaseRecyclerListAdapter<T, VH> addAll(@NonNull Collection<T> items) {
        mItemList.addAll(items);
        notifyDataSetChanged();
        return this;
    }

    public BaseRecyclerListAdapter<T, VH> add(int position, @NonNull T item) {
        mItemList.add(position, item);
        notifyDataSetChanged();
        return this;
    }

    public BaseRecyclerListAdapter<T, VH> remove(int position) {
        mItemList.remove(position);
        notifyDataSetChanged();
        return this;
    }

    public BaseRecyclerListAdapter<T, VH> remove(@NonNull T item) {
        mItemList.remove(item);
        notifyDataSetChanged();
        return this;
    }

    public BaseRecyclerListAdapter<T, VH> clear() {
        mItemList.clear();
        notifyDataSetChanged();
        return this;
    }

}