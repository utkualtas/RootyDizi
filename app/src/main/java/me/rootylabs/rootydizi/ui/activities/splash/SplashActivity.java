package me.rootylabs.rootydizi.ui.activities.splash;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.thekhaeng.pushdownanim.PushDownAnim;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import dagger.android.support.HasSupportFragmentInjector;
import me.rootylabs.rootydizi.R;
import me.rootylabs.rootydizi.data.models.User;
import me.rootylabs.rootydizi.databinding.ActivitySplashBinding;
import me.rootylabs.rootydizi.ui.activities.main.MainActivity;
import me.rootylabs.rootydizi.ui.fragments.splash.ForgotFragment;
import me.rootylabs.rootydizi.ui.fragments.splash.SignUpFragment;
import me.rootylabs.rootydizi.utils.SomeUtils;
import timber.log.Timber;


public class SplashActivity extends DaggerAppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    SomeUtils someUtils;

    ActivitySplashBinding binding;
    SplashActivityViewModel splashActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashActivityViewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        InitUI();
        Observe();
    }

    private void InitUI() {



        binding.fragmentLoginSignup.setOnClickListener(v -> {
            someUtils.pushFragment(getSupportFragmentManager(), new SignUpFragment(), R.id.fragment_login_root, null);
        });

        binding.fragmentLoginForgot.setOnClickListener(v -> {
            someUtils.pushFragment(getSupportFragmentManager(), new ForgotFragment(), R.id.fragment_login_root, null);
        });

        PushDownAnim.setPushDownAnimTo(binding.fragmentLoginFacebook).setOnClickListener(v -> {
            //Do somethings when click the facebook button.
        });

        PushDownAnim.setPushDownAnimTo(binding.fragmentLoginSignIn).setOnClickListener(v -> {
            //Do somethings when click the sign in button.
            splashActivityViewModel.getLogin(binding.fragmentLoginUsername.getText().toString().trim(), binding.fragmentLoginPassword.getText().toString().trim());

        });
    }



    private void Observe() {
        splashActivityViewModel.getUser().observe(this, this::login);
        splashActivityViewModel.getLoginMessage().observe(this, this::loginMessage);
    }

    private void loginMessage(String s) {
        //Maybe we can show a dialog for login message.
    }

    private void login(User user) {
        if(user != null){
            //Do somethings when if available entered user.
            Timber.e("Selam " + user.getName());
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }




}
