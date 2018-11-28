package me.rootylabs.rootydizi.di;


import javax.inject.Singleton;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import me.rootylabs.rootydizi.RootyDiziApp;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        NetworkModule.class,
        ActivityBuilderModule.class,
        FragmentBuilderModule.class,
        ViewModelModule.class})
public interface AppComponent extends AndroidInjector<RootyDiziApp> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<RootyDiziApp> {
    }
}
