package app.dinus.com.recycleritemdecoration.foundation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.dinus.com.recycleritemdecoration.R;

public abstract class RecyclerListFragment extends Fragment {
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_recycler_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        initRecyclerView();
    }

    private void initRecyclerView() {
        if (mRecyclerView != null) {
            RecyclerView.LayoutManager layoutManager = onCreateLayoutManager();
            if (layoutManager != null) {
                mRecyclerView.setLayoutManager(layoutManager);
            }

            RecyclerListAdapter adapter = onCreateAdapter();
            if (adapter != null) {
                mRecyclerView.setAdapter(adapter);
            }

            RecyclerView.ItemDecoration itemDecoration = onCreateItemDecoration();
            if (itemDecoration != null) {
                mRecyclerView.addItemDecoration(itemDecoration);
            }
        }
    }

    public RecyclerView.LayoutManager onCreateLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    public abstract RecyclerListAdapter onCreateAdapter();

    public RecyclerView.ItemDecoration onCreateItemDecoration() {
        return null;
    }

    public RecyclerListAdapter getAdapter() {
        return (RecyclerListAdapter) mRecyclerView.getAdapter();
    }

    public int getItemViewType(Class<?> clazz) {
        return getAdapter().getItemViewType(clazz);
    }

    protected RecyclerView findRecyclerView() {
        return mRecyclerView;
    }
}
