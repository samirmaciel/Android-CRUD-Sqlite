package com.samirmaciel.crudsqlite.controller;

import android.app.Activity;
import android.content.Intent;

import android.app.Fragment;
import com.samirmaciel.crudsqlite.R;
import com.samirmaciel.crudsqlite.view.HomeFragment;
import com.samirmaciel.crudsqlite.view.MainActivity;
import com.samirmaciel.crudsqlite.view.SaveFragment;

public class MainController {
    private MainActivity activity;

    public MainController(MainActivity activity) {
        this.activity = activity;
    }

    public void getSaveView() {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, new SaveFragment())
                .commit();
    }
}
