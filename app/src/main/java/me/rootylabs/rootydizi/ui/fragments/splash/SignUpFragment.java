package me.rootylabs.rootydizi.ui.fragments.splash;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.android.support.DaggerFragment;
import me.rootylabs.rootydizi.R;
import me.rootylabs.rootydizi.databinding.FragmentSplashSignupBinding;


public class SignUpFragment extends DaggerFragment {


    FragmentSplashSignupBinding binding;
    Activity activity;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash_signup, container, false);
        View view = binding.getRoot();
        Init();
        Observe();
        return view;
    }

    private void Init() {
        activity = getActivity();
    }

    private void Observe() {
    }


}