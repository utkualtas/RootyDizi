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

import dagger.android.support.DaggerFragment;
import me.rootylabs.rootydizi.R;
import me.rootylabs.rootydizi.databinding.FragmentSplashSignupBinding;


public class SignUpFragment extends DaggerFragment {


    FragmentSplashSignupBinding binding;
    Activity activity;
    View view;
    InputMethodManager inputMethodManager;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash_signup, container, false);
        view = binding.getRoot();
        Init();
        Observe();
        return view;
    }

    private void Init() {
        activity = getActivity();
        binding.fragmentSignupBg.setOnClickListener( v -> { clearEditTextsFocus(v);});
        PushDownAnim.setPushDownAnimTo(binding.fragmentSignupBtnSignUp).setOnClickListener( v -> {
            //Do somethings when click sign up button.
        });
    }

    private void Observe() {
    }


    public void clearEditTextsFocus(View v){
        if(activity.getCurrentFocus() != binding.fragmentSignupFocusable){
            inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            binding.fragmentSignupFocusable.requestFocus();
        }
    }


}