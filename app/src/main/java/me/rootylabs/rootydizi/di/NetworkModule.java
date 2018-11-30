package me.rootylabs.rootydizi.di;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.rootylabs.rootydizi.BuildConfig;
import me.rootylabs.rootydizi.data.source.MainDataSource;
import me.rootylabs.rootydizi.data.RequestInterceptor;
import me.rootylabs.rootydizi.data.source.UserDataSource;
import me.rootylabs.rootydizi.data.api.ApiService;
import me.rootylabs.rootydizi.data.remote.RemoteMainDataSource;
import me.rootylabs.rootydizi.data.remote.RemoteUserDataSource;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final long TIMEOUT_IN_SEC = 10;

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLogginInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.readTimeout(TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(new RequestInterceptor());
        okHttpClient.addNetworkInterceptor(new StethoInterceptor());
        if (false) {
            okHttpClient.addInterceptor(loggingInterceptor);
        }
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    ApiService provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(ApiService.class);
    }


    @Provides
    @Singleton
    @Named("mainData")
    MainDataSource provideMainDataSource(ApiService apiService){
        return new RemoteMainDataSource(apiService);
    }

    @Provides
    @Singleton
    @Named("userData")
    UserDataSource provideUserDataSource(ApiService apiService){
        return new RemoteUserDataSource(apiService);
    }

}
