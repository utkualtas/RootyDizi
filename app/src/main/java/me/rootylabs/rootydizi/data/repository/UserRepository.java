package me.rootylabs.rootydizi.data.repository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import me.rootylabs.rootydizi.data.source.UserDataSource;
import me.rootylabs.rootydizi.data.models.UserContainer;

public class UserRepository implements UserDataSource {

    private final UserDataSource userDataSource;

    @Inject
    public UserRepository(@Named("userData") UserDataSource userDataSource){
        this.userDataSource = userDataSource;
    }

    @Override
    public Flowable<UserContainer> getUserData(String username, String password) {
        return userDataSource.getUserData(username, password).subscribeOn(Schedulers.io());
    }
}
