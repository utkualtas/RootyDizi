package me.rootylabs.rootydizi.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import me.rootylabs.rootydizi.ui.activities.main.MainActivityViewModel;
import me.rootylabs.rootydizi.ui.activities.splash.SplashActivityViewModel;
import me.rootylabs.rootydizi.ui.fragments.main.FeedFragmentViewModel;
import me.rootylabs.rootydizi.ui.fragments.main.SearchFragmentViewModel;

@Module
public abstract class ViewModelModule {

    @ViewModelKey(MainActivityViewModel.class)
    @IntoMap
    @Binds
    abstract ViewModel provideMainViewModel(MainActivityViewModel mainActivityViewModel);

    @ViewModelKey(SplashActivityViewModel.class)
    @IntoMap
    @Binds
    abstract ViewModel provideSplashViewModel(SplashActivityViewModel splashActivityViewModel);

    @ViewModelKey(FeedFragmentViewModel.class)
    @IntoMap
    @Binds
    abstract ViewModel provideFeedFragmentViewModel(FeedFragmentViewModel feedFragmentViewModel);

    @ViewModelKey(SearchFragmentViewModel.class)
    @IntoMap
    @Binds
    abstract ViewModel provideSearchFragmentViewModel(SearchFragmentViewModel searchFragmentViewModel);

    @Binds
    abstract ViewModelProvider.Factory provideViewModelFactory(ViewModelFactory viewModelFactory);

}