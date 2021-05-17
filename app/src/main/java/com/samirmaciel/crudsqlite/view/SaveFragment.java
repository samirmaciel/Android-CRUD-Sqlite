package com.samirmaciel.crudsqlite.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samirmaciel.crudsqlite.R;


public class SaveFragment extends Fragment {

    public SaveFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_save, container, false);


        return view;
    }
}