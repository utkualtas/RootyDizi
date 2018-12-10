package me.rootylabs.rootydizi.ui.activities.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import dagger.android.support.HasSupportFragmentInjector;
import me.rootylabs.rootydizi.R;
import me.rootylabs.rootydizi.databinding.ActivityMainBinding;
import me.rootylabs.rootydizi.ui.fragments.main.FeedFragment;
import me.rootylabs.rootydizi.ui.fragments.main.FeedLastAdapter;
import me.rootylabs.rootydizi.ui.fragments.main.SearchFragment;
import me.rootylabs.rootydizi.utils.SomeUtils;

public class MainActivity extends DaggerAppCompatActivity implements HasSupportFragmentInjector, BottomNavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;
    MainActivityViewModel feedViewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    FeedLastAdapter feedLastAdapter;
    @Inject
    SomeUtils someUtils;
    private int mMenuId;

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
        binding.bottomBar.setOnNavigationItemSelectedListener(this);
        someUtils.pushFragment(getSupportFragmentManager(), new FeedFragment(), R.id.activity_main_container, null);
    }

    private void Observe() {

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mMenuId = menuItem.getItemId();

        switch (mMenuId) {
            case R.id.action_home: {
                someUtils.pushFragment(getSupportFragmentManager(), new FeedFragment(), R.id.activity_main_container, null);
            }
            break;
            case R.id.action_search: {
                someUtils.pushFragment(getSupportFragmentManager(), new SearchFragment(), R.id.activity_main_container, null);
            }
            break;
            case R.id.action_profile: {

            }
            break;
        }

        return true;
    }
}
