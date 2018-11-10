package me.rootylabs.rootydizi.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.rootylabs.rootydizi.ui.activities.main.MainActivity;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract MainActivity contributeFeedActivity();
}
