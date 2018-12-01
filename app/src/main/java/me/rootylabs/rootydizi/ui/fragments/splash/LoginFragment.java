package me.rootylabs.rootydizi.ui.fragments.splash;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
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
import me.rootylabs.rootydizi.R;
import me.rootylabs.rootydizi.data.api.ApiService;
import me.rootylabs.rootydizi.data.models.User;
import me.rootylabs.rootydizi.data.repository.UserRepository;
import me.rootylabs.rootydizi.databinding.FragmentSplashLoginBinding;
import me.rootylabs.rootydizi.ui.activities.main.MainActivity;
import me.rootylabs.rootydizi.utils.SomeUtils;
import timber.log.Timber;

public class LoginFragment extends DaggerFragment {

    Activity activity;
    FragmentSplashLoginBinding binding;
    LoginFragmentViewModel loginFragmentViewModel;
    View view;
    InputMethodManager inputMethodManager;


    @Inject
    ViewModelProvider.Factory viewModelFactory;
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
        loginFragmentViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginFragmentViewModel.class);
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
            loginFragmentViewModel.getLogin(binding.fragmentLoginUsername.getText().toString().trim(), binding.fragmentLoginPassword.getText().toString().trim());
        });
    }

    private void Observe() {
        loginFragmentViewModel.getUser().observe(this, this::login);
        loginFragmentViewModel.getLoginMessage().observe(this, this::loginMessage);
    }

    private void loginMessage(String s) {
        //Maybe we can show a dialog for login message.
    }

    private void login(User user) {
        if(user != null){
            //Do somethings when if available entered user.
            Timber.e("Selam " + user.getName());
            startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
        }
    }

    public void clearEditTextsFocus(View v) {
        if (activity.getCurrentFocus() != binding.fragmentLoginFocusable) {
            inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            binding.fragmentLoginFocusable.requestFocus();
        }
    }

}