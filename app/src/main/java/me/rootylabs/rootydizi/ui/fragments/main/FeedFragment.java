package me.rootylabs.rootydizi.ui.fragments.main;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import me.rootylabs.rootydizi.R;
import me.rootylabs.rootydizi.data.models.GridSerie;
import me.rootylabs.rootydizi.databinding.FragmentFeedBinding;
import me.rootylabs.rootydizi.utils.SomeUtils;
import me.rootylabs.rootydizi.view.HorizontalRecyclerGroup;
import timber.log.Timber;

public class FeedFragment extends DaggerFragment {

    FragmentFeedBinding binding;
    View view;

    FeedFragmentViewModel feedViewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    FeedLastAdapter feedLastAdapter;

    @Inject
    SomeUtils someUtils;

    Activity activity;
    Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        feedViewModel = ViewModelProviders.of(this, viewModelFactory).get(FeedFragmentViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false);
        view = binding.getRoot();
        Init();
        Observe();
        return view;
    }

    private void Init() {
        activity = getActivity();
        context = getContext();
        feedLastAdapter.setOnItemClickListener(this::RecyclerOnItemClick);
        for (int i = 0; i < 5; i++) {
            HorizontalRecyclerGroup group = new HorizontalRecyclerGroup(activity);
            group.setRecyclerView(feedLastAdapter);
            group.setPadding(0, someUtils.convertDpToPx(context,32), 0, someUtils.convertDpToPx(context, 16));
            binding.contentContainer.addView(group);
        }
    }

    public void RecyclerOnItemClick(GridSerie gridSerie) {
        Timber.e(gridSerie.getName());
    }

    private void Observe() {
        feedViewModel.getDatas().observe(this, feedLastAdapter::setSeries);
    }


}
