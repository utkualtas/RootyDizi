package me.rootylabs.rootydizi.ui.fragments.splash;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.thekhaeng.pushdownanim.PushDownAnim;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import me.rootylabs.rootydizi.R;
import me.rootylabs.rootydizi.data.api.ApiService;
import me.rootylabs.rootydizi.data.repository.UserRepository;
import me.rootylabs.rootydizi.databinding.FragmentSplashLoginBinding;
import me.rootylabs.rootydizi.utils.SomeUtils;
import timber.log.Timber;

public class LoginFragment extends DaggerFragment {

    FragmentSplashLoginBinding binding;
    Activity activity;
    View view;
    InputMethodManager inputMethodManager;

    @Inject
    SomeUtils someUtils;

    @Inject
    ApiService apiService;

    @Inject
    UserRepository userRepository;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash_login, container, false);
        view = binding.getRoot();
        Init();
        Observe();
        return view;
    }

    private void Init() {
        activity = getActivity();

        binding.fragmentLoginBg.setOnClickListener(v -> {
            clearEditTextsFocus(getView());
        });

        binding.fragmentLoginSignup.setOnClickListener(v -> {
            someUtils.pushFragment(getActivity().getSupportFragmentManager(), new SignUpFragment(), R.id.activity_splash_root, null);
        });

        binding.fragmentLoginForgot.setOnClickListener(v -> {
            someUtils.pushFragment(getActivity().getSupportFragmentManager(), new ForgotFragment(), R.id.activity_splash_root, null);
        });

        PushDownAnim.setPushDownAnimTo(binding.fragmentLoginFacebook).setOnClickListener(v -> {
            //Do somethings when click the facebook button.
        });

        PushDownAnim.setPushDownAnimTo(binding.fragmentLoginSignIn).setOnClickListener(v -> {
            //Do somethings when click the sign in button.

            //Bu işlem için View Model oluşturmak mı oluşturmamak mı işte bütün mesele bu..
            userRepository.getUserData("utku", "123456")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(userContainer -> {
                        Timber.e(userContainer.getCode());
                        Timber.e(userContainer.getMessage());
                        if(userContainer.getCode() == "200"){
                            Timber.e(userContainer.getData().getName());
                        }
                    });
        });
    }

    private void Observe() {

    }


    public void clearEditTextsFocus(View v) {
        if (activity.getCurrentFocus() != binding.fragmentLoginFocusable) {
            inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            binding.fragmentLoginFocusable.requestFocus();
        }
    }

}