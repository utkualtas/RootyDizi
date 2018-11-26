package me.rootylabs.rootydizi.ui.activities.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.rootylabs.rootydizi.data.models.GridSerie;
import me.rootylabs.rootydizi.databinding.RowLastBinding;

import static android.support.constraint.Constraints.TAG;

public class MainLastAdapter extends RecyclerView.Adapter<MainLastAdapter.MainLastItemViewHolder> {


    private List<GridSerie> series;

    @Inject
    public MainLastAdapter() {
       series = new ArrayList<>();
    }

    public void setSeries(List<GridSerie> series) {
        this.series.addAll(series);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MainLastItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return MainLastItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MainLastItemViewHolder holder, int position) {
        holder.bind(series.get(position));
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: "+series.size() );
        return series.size();
    }


    static class MainLastItemViewHolder extends RecyclerView.ViewHolder {

        private final RowLastBinding itemFeedBinding;

        public MainLastItemViewHolder(RowLastBinding itemFeedBinding) {
            super(itemFeedBinding.getRoot());
            this.itemFeedBinding = itemFeedBinding;
        }

        static MainLastItemViewHolder create(ViewGroup parent) {
            RowLastBinding binding = RowLastBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new MainLastItemViewHolder(binding);
        }

        public void bind(GridSerie gridSerie) {
            itemFeedBinding.setData(gridSerie);
            itemFeedBinding.executePendingBindings();
        }

    }
}
