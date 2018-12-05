package me.rootylabs.rootydizi.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;

import javax.inject.Inject;

import me.rootylabs.rootydizi.R;

public class SomeUtils {

    @Inject
    public SomeUtils(){

    }

    public void pushFragment(FragmentManager manager, Fragment fragment, int container, String backStack){
        manager.beginTransaction()
                .setCustomAnimations(R.anim.animation_left_to_right, R.anim.animation_left_to_right2, R.anim.animation_right_to_left, R.anim.animation_right_to_left2)
                .addToBackStack(backStack)
                .replace(container, fragment)
                .commit();
    }




    public int convertDpToPx(Context context, int dp){
        return Math.round(dp*(context.getResources().getDisplayMetrics().xdpi/DisplayMetrics.DENSITY_DEFAULT));

    }

    public int convertPxToDp(int px){
        return Math.round(px/(Resources.getSystem().getDisplayMetrics().xdpi/DisplayMetrics.DENSITY_DEFAULT));
    }

}
