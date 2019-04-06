package com.example.twentyone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twentyone.R;
import com.example.twentyone.dialogs.AddBloodDialog;
import com.example.twentyone.dialogs.AddPreferencesDialog;
import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EntitiesPreferencesFragment extends Fragment {

    private MaterialButton add_preferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_entities_preferences, container, false);

        add_preferences = view.findViewById(R.id.add_preferences_button);
        add_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddPreferencesDialog().show(getChildFragmentManager(), "dialog");
            }
        });

        return view;
    }
}
