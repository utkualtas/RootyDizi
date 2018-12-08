package me.rootylabs.rootydizi.ui.activities.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import dagger.android.support.HasSupportFragmentInjector;
import me.rootylabs.rootydizi.R;
import me.rootylabs.rootydizi.databinding.ActivityMainBinding;
import me.rootylabs.rootydizi.ui.fragments.main.FeedFragment;
import me.rootylabs.rootydizi.utils.SomeUtils;

public class MainActivity extends DaggerAppCompatActivity implements HasSupportFragmentInjector {

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
        binding.bottomBar.inflateMenu(R.menu.rooty_menu);
        someUtils.pushFragment(getSupportFragmentManager(), new FeedFragment(), R.id.activity_main_container, null);
    }

    private void Observe() {

    }

}
