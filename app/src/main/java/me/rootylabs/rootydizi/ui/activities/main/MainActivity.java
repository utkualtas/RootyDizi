package me.rootylabs.rootydizi.ui.activities.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import me.rootylabs.rootydizi.R;
import me.rootylabs.rootydizi.data.models.GridSerie;
import me.rootylabs.rootydizi.databinding.ActivityMainBinding;
import me.rootylabs.rootydizi.utils.SomeUtils;
import me.rootylabs.rootydizi.view.HorizontalRecyclerGroup;
import timber.log.Timber;

public class MainActivity extends DaggerAppCompatActivity {

    ActivityMainBinding binding;
    MainActivityViewModel feedViewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
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
        mainLastAdapter.setOnItemClickListener(this::RecyclerOnItemClick);
        binding.bottomBar.inflateMenu(R.menu.rooty_menu);

        for (int i = 0; i < 5; i++) {
            HorizontalRecyclerGroup group = new HorizontalRecyclerGroup(this);
            group.setRecyclerView(mainLastAdapter);
            group.setPadding(0, someUtils.convertDpToPx(this, 32), 0, someUtils.convertDpToPx(this, 16));
            binding.contentContainer.addView(group);


        }

    }

    private void RecyclerOnItemClick(GridSerie gridSerie) {
        Timber.e(gridSerie.getName());
    }

    private void Observe() {
        feedViewModel.getDatas().observe(this, mainLastAdapter::setSeries);
    }

}
