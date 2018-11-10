package me.rootylabs.rootydizi.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import me.rootylabs.rootydizi.ui.activities.main.MainActivityViewModel;

@Module
public abstract class ViewModelModule {

    @ViewModelKey(MainActivityViewModel.class)
    @IntoMap
    @Binds
    abstract ViewModel provideMainViewModel(MainActivityViewModel mainActivityViewModel);

    @Binds
    abstract ViewModelProvider.Factory provideViewModelFactory(ViewModelFactory viewModelFactory);

}