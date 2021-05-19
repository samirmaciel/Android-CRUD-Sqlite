package com.samirmaciel.crudsqlite.view;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.samirmaciel.crudsqlite.R;
import com.samirmaciel.crudsqlite.controller.SaveController;


public class SaveFragment extends Fragment {

    public EditText inputName, inputTelefone;
    private Button btnSave, btnVoltar;
    private SaveController controller;

    public SaveFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_save, container, false);
        inputName = (EditText) view.findViewById(R.id.inputName);
        inputTelefone = (EditText) view.findViewById(R.id.inputNumber);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnVoltar = (Button) view.findViewById(R.id.btnBack);

        controller = new SaveController(this, getActivity().getApplicationContext());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                controller.salvarContato();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.getHomeFragment();
            }
        });


        return view;
    }
}