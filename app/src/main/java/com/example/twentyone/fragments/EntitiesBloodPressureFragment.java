package com.example.twentyone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twentyone.R;
import com.example.twentyone.dialogs.AddBloodDialog;
import com.example.twentyone.dialogs.AddWeightDialog;
import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EntitiesBloodPressureFragment extends Fragment {

    private MaterialButton add_blood;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_entities_blood, container, false);

        add_blood = view.findViewById(R.id.add_blood_button);
        add_blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddBloodDialog().show(getChildFragmentManager(), "dialog");
            }
        });

        return view;
    }
}
