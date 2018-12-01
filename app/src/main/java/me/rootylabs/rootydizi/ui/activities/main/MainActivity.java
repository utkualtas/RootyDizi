package me.rootylabs.rootydizi.ui.activities.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import me.rootylabs.rootydizi.R;
import me.rootylabs.rootydizi.data.api.ApiService;
import me.rootylabs.rootydizi.data.models.GridSerie;
import me.rootylabs.rootydizi.databinding.ActivityMainBinding;
import me.rootylabs.rootydizi.utils.SomeUtils;
import me.rootylabs.rootydizi.utils.SpaceItemDecoration;
import timber.log.Timber;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    ActivityMainBinding binding;

    MainActivityViewModel feedViewModel;

    @Inject
    ApiService apiService;

    @Inject
    MainLastAdapter mainLastAdapter;

    @Inject
    SomeUtils someUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        InitUI();
        Observe();

    }


    private void InitUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.mainRecyclerLast.setLayoutManager(layoutManager);
        binding.mainRecyclerLast.addItemDecoration(new SpaceItemDecoration(someUtils.convertDpToPx(this, 16)));
        binding.mainRecyclerLast.setAdapter(mainLastAdapter);
        mainLastAdapter.setOnItemClickListener(this::RecyclerOnItemClick);
    }

    private void RecyclerOnItemClick(GridSerie gridSerie) {
        Timber.e(gridSerie.getName());
    }


    private void Observe() {
        feedViewModel.getDatas().observe(this, mainLastAdapter::setSeries);
    }



}
