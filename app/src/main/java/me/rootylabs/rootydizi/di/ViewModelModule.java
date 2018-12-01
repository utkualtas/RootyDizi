package me.rootylabs.rootydizi.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import me.rootylabs.rootydizi.ui.activities.main.MainActivityViewModel;
import me.rootylabs.rootydizi.ui.activities.splash.SplashActivityViewModel;
import me.rootylabs.rootydizi.ui.fragments.splash.LoginFragmentViewModel;

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

    @ViewModelKey(LoginFragmentViewModel.class)
    @IntoMap
    @Binds
    abstract ViewModel provideLoginViewModel(LoginFragmentViewModel loginFragmentViewModel);

    @Binds
    abstract ViewModelProvider.Factory provideViewModelFactory(ViewModelFactory viewModelFactory);

}