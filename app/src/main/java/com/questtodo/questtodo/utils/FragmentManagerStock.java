package com.questtodo.questtodo.utils;

import android.app.FragmentManager;

/**
 * Created by eunjooim on 15. 8. 23..
 */
public class FragmentManagerStock {
    private static FragmentManager fragmentManager;

    public void Stock(){}

    public static void initiateFragmentManager(FragmentManager fm){
        fragmentManager = fm;
    }

    public static FragmentManager getFragmentManager(){
        return fragmentManager;
    }

}
