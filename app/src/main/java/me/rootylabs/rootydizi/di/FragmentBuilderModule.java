package me.rootylabs.rootydizi.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.rootylabs.rootydizi.ui.fragments.main.FeedFragment;
import me.rootylabs.rootydizi.ui.fragments.main.SearchFragment;
import me.rootylabs.rootydizi.ui.fragments.splash.ForgotFragment;
import me.rootylabs.rootydizi.ui.fragments.splash.SignUpFragment;

@Module
public abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract SignUpFragment contributeSignUpFragment();

    @ContributesAndroidInjector
    abstract ForgotFragment contributeForgotFragment();

    @ContributesAndroidInjector
    abstract FeedFragment contributeFeedFragment();

    @ContributesAndroidInjector
    abstract SearchFragment contributeSearchFragment();


}
