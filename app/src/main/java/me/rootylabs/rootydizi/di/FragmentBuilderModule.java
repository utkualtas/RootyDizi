package me.rootylabs.rootydizi.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.rootylabs.rootydizi.ui.fragments.splash.ForgotFragment;
import me.rootylabs.rootydizi.ui.fragments.splash.SignUpFragment;

@Module
public abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract SignUpFragment contributeSignUpFragment();

    @ContributesAndroidInjector
    abstract ForgotFragment contributeForgotFragment();


}
