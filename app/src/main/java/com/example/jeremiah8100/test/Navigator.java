package com.example.jeremiah8100.test;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class Navigator {
    Inapp inapp;
    View target;
    Fragment currentfragment;
    public Navigator(Inapp inapp, View target) {
        this.target = target;
        this.inapp = inapp;
    }

    public boolean ShowPrevious(){
        if(currentfragment instanceof Event || currentfragment instanceof Settings) {
            Show(new Events());
            return true;
        }

        return false;
    }

    public void Show(Fragment fragment){
        if(!inapp.SidebarActive()) {
            currentfragment = fragment;
            FragmentManager manager = inapp.getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(target.getId(), fragment);
            transaction.commit();
        }
    }
}
