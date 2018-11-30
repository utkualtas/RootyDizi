package me.rootylabs.rootydizi.data.source;

import io.reactivex.Flowable;
import me.rootylabs.rootydizi.data.models.UserContainer;

public interface UserDataSource {
    Flowable<UserContainer> getUserData(String username, String password);
}
