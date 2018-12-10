package me.rootylabs.rootydizi.ui.fragments.main;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import me.rootylabs.rootydizi.R;
import me.rootylabs.rootydizi.databinding.FragmentSearchBinding;
import me.rootylabs.rootydizi.utils.SomeUtils;

public class SearchFragment extends DaggerFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SomeUtils someUtils;

    Activity activity;
    Context context;
    SearchFragmentViewModel searchFragmentViewModel;
    FragmentSearchBinding binding;
    View view;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        searchFragmentViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchFragmentViewModel.class);
        view = binding.getRoot();
        Init();
        Observe();
        return view;
    }

    private void Init() {


        binding.myRoot.getViewTreeObserver().addOnDrawListener(() -> setWidgetsPosition());

        binding.myRoot.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

            }
        });
    }

    private void setWidgetsPosition(){
        float imageHeight = binding.image.getHeight();
        float stickyHeight = binding.stickyView.getHeight();
        binding.scrollView.setPadding(0, (int)imageHeight + (int) stickyHeight, 0, 0);




    }

    private void Observe() {
    }
}
