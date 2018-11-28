package me.rootylabs.rootydizi.ui.activities.splash;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.HasFragmentInjector;
import dagger.android.support.DaggerAppCompatActivity;
import me.rootylabs.rootydizi.R;
import me.rootylabs.rootydizi.data.api.ApiService;
import me.rootylabs.rootydizi.databinding.ActivitySplashBinding;
import me.rootylabs.rootydizi.ui.fragments.splash.LoginFragment;
import me.rootylabs.rootydizi.utils.SomeUtils;


public class SplashActivity extends DaggerAppCompatActivity implements HasFragmentInjector {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    ApiService apiService;
    @Inject
    SomeUtils someUtils;

    ActivitySplashBinding binding;
    SplashActivityViewModel splashActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashActivityViewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        InitUI();
        Observe();
    }

    private void InitUI() {
        //binding.activitySplashProgress.setVisibility(View.GONE);
        someUtils.pushFragment(getSupportFragmentManager(), new LoginFragment(), R.id.activity_splash_root, null);
    }

    private void Observe() {

    }




}
