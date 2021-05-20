package com.samirmaciel.crudsqlite.controller;

import android.content.Context;

import com.samirmaciel.crudsqlite.R;
import com.samirmaciel.crudsqlite.view.HomeFragment;
import com.samirmaciel.crudsqlite.view.SaveFragment;

public class HomeController {

    private HomeFragment fragment;
    private Context context;

    public HomeController(HomeFragment fragment, Context context) {
        this.fragment = fragment;
        this.context = context;
    }

    public void getSaveView() {
        fragment.getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, new SaveFragment())
                .commit();
    }
}
