package me.rootylabs.rootydizi.ui.fragments.splash;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thekhaeng.pushdownanim.PushDownAnim;

import dagger.android.support.DaggerFragment;
import me.rootylabs.rootydizi.R;
import me.rootylabs.rootydizi.databinding.FragmentSplashForgotBinding;

public class ForgotFragment extends DaggerFragment {


    FragmentSplashForgotBinding binding;
    View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash_forgot, container, false);
        view = binding.getRoot();
        Init();
        Observe();
        return view;
    }

    private void Init() {
        PushDownAnim.setPushDownAnimTo(binding.fragmentForgotBtnSend).setOnClickListener(v -> {
            //Do somethings when click the send confirm button.
        });
    }

    private void Observe() {
    }
}
