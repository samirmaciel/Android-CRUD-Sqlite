package com.samirmaciel.crudsqlite.controller;

import android.app.Activity;
import android.content.Intent;

import android.app.Fragment;
import com.samirmaciel.crudsqlite.R;
import com.samirmaciel.crudsqlite.view.HomeFragment;
import com.samirmaciel.crudsqlite.view.SaveFragment;

public class MainController {
    private Activity activity;

    public MainController(Activity activity) {
        this.activity = activity;
    }

    public void getSaveView() {
        activity.getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, new SaveFragment())
                .commit();
    }
}
