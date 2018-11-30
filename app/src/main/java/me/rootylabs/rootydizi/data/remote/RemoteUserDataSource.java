package me.rootylabs.rootydizi.data.remote;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import me.rootylabs.rootydizi.data.source.UserDataSource;
import me.rootylabs.rootydizi.data.api.ApiService;
import me.rootylabs.rootydizi.data.models.UserContainer;

public class RemoteUserDataSource implements UserDataSource {

    ApiService apiService;

    @Inject
    public RemoteUserDataSource(ApiService apiService){
        this.apiService = apiService;
    }



    @Override
    public Flowable<UserContainer> getUserData(String username, String password) {
        return Flowable.create( e -> {
                    apiService.getLoginResponse(username, password)
                            .toFlowable()
                            .subscribeOn(Schedulers.io())
                            .subscribe(e::onNext);
                }, BackpressureStrategy.BUFFER);
    }
}
