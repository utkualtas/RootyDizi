package me.rootylabs.rootydizi.ui.fragments.splash;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import me.rootylabs.rootydizi.data.models.User;
import me.rootylabs.rootydizi.data.models.UserContainer;
import me.rootylabs.rootydizi.data.repository.UserRepository;
import me.rootylabs.rootydizi.utils.RxViewModel;
import timber.log.Timber;

public class LoginFragmentViewModel extends RxViewModel {


    private final UserRepository userRepository;
    private MutableLiveData<User> user = new MutableLiveData();
    private MutableLiveData<String> mLoginMessage = new MutableLiveData();

    @Inject
    public LoginFragmentViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public void getLogin(String username, String password) {
        disposable.add(userRepository
                .getUserData(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loadUser));
    }


    public void loadUser(UserContainer userContainer) {
        Timber.e("Code: " + userContainer.getCode());
        Timber.e("Message: " + userContainer.getMessage());
        mLoginMessage.setValue(userContainer.getMessage());
        if(userContainer.getCode().equals("200")){
            //If we change the user and set it to our MutablaLiveData which is user on this case,
            //it will notify automaticly and update the LoginFragment because we observing the livedata from LoginFragment.
            user.setValue(userContainer.getData());
        }
    }

    public LiveData<User> getUser(){
        return user;
    }

    public  LiveData<String> getLoginMessage(){
        return mLoginMessage;
    }


}
