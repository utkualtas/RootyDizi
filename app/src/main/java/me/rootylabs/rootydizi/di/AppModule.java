package me.rootylabs.rootydizi.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.rootylabs.rootydizi.RootyDiziApp;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideApplicationContext(RootyDiziApp app) {
        return app;
    }
}