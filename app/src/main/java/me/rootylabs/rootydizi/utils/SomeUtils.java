package me.rootylabs.rootydizi.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

public class SomeUtils {

    @Inject
    public SomeUtils(){

    }

    public void pushFragment(FragmentManager manager, Fragment fragment, int container, String backStack){
        manager.beginTransaction()
                .addToBackStack(backStack)
                .replace(container, fragment)
                .commit();
    }

}
