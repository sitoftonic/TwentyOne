package com.example.twentyone.fragments.entities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twentyone.R;
import com.example.twentyone.dialogs.AddPointsDialog;
import com.example.twentyone.dialogs.AddWeightDialog;
import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EntitiesWeightFragment extends Fragment {

    private MaterialButton add_weight;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_entities_weight, container, false);

        add_weight = view.findViewById(R.id.add_weight_button);
        add_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddWeightDialog().show(getChildFragmentManager(), "dialog");
            }
        });

        return view;
    }
}
