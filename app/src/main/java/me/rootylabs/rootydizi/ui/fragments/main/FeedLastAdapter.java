package me.rootylabs.rootydizi.ui.fragments.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.rootylabs.rootydizi.data.models.GridSerie;
import me.rootylabs.rootydizi.databinding.RowLastBinding;
import timber.log.Timber;

public class FeedLastAdapter extends RecyclerView.Adapter<FeedLastAdapter.MainLastItemViewHolder> {


    private List<GridSerie> series;
    public interface OnItemClickListener {
        void onItemClicked(GridSerie gridSerie);
    }

    private OnItemClickListener onItemClickListener;

    @Inject
    public FeedLastAdapter() {
       series = new ArrayList<>();
    }

    public void setSeries(List<GridSerie> series) {
        Timber.e("setSeries:  eklendi");
        this.series.addAll(series);
        notifyDataSetChanged();

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MainLastItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return MainLastItemViewHolder.create(parent, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainLastItemViewHolder holder, int position) {
        holder.bind(series.get(position));


        holder.itemFeedBinding.rowLastImg.setClickable(false);
        PushDownAnim.setPushDownAnimTo(holder.itemFeedBinding.rowLastRoot);



    }

    @Override
    public int getItemCount() {
        Timber.e("getItemCount: %s", series.size());
        return series.size();
    }


    static class MainLastItemViewHolder extends RecyclerView.ViewHolder {

        private final RowLastBinding itemFeedBinding;

        static MainLastItemViewHolder create(ViewGroup parent, OnItemClickListener onItemClickListener) {
            RowLastBinding binding = RowLastBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new MainLastItemViewHolder(binding, onItemClickListener);
        }

        public MainLastItemViewHolder(RowLastBinding itemFeedBinding, OnItemClickListener onItemClickListener) {
            super(itemFeedBinding.getRoot());
            this.itemFeedBinding = itemFeedBinding;
            this.itemFeedBinding.rowLastImg.setOnClickListener(view -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClicked(itemFeedBinding.getData());
                }
            });
        }

        public void bind(GridSerie gridSerie) {
            itemFeedBinding.setData(gridSerie);
            itemFeedBinding.executePendingBindings();
        }

    }
}
