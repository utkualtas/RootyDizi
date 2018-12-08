package me.rootylabs.rootydizi.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.rootylabs.rootydizi.ui.activities.main.MainActivity;
import me.rootylabs.rootydizi.ui.activities.splash.SplashActivity;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();


    @ContributesAndroidInjector
    abstract SplashActivity contributeSplashActivity();
}
