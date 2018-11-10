package me.rootylabs.rootydizi;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import me.rootylabs.rootydizi.di.DaggerAppComponent;

public class RootyDiziApp extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
