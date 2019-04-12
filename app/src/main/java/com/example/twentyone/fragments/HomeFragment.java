package com.example.twentyone.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.twentyone.R;
import com.example.twentyone.dialogs.AddBloodDialog;
import com.example.twentyone.dialogs.AddPointsDialog;
import com.example.twentyone.dialogs.AddWeightDialog;
import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    public static final String TAG = HomeFragment.class.getSimpleName();

    private TextView user_text;

    private MaterialButton add_points;
    private MaterialButton add_blood;
    private MaterialButton add_weight;

    private static final HomeFragment ourInstance = new HomeFragment();

    public static HomeFragment getInstance() {
        return ourInstance;
    }

    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        user_text = view.findViewById(R.id.user_text);

        String user = getArguments().getString("user");

        if (user != null) {
            user_text.setText(user);
        }


        add_points = view.findViewById(R.id.add_points_button);
        add_points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddPointsDialog().show(getChildFragmentManager(), "points_dialog");
            }
        });

        add_blood = view.findViewById(R.id.add_blood_button);
        add_blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddBloodDialog().show(getChildFragmentManager(), "blood_dialog");
            }
        });

        add_weight = view.findViewById(R.id.add_weight_button);
        add_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddWeightDialog().show(getChildFragmentManager(), "weight_dialog");
            }
        });
    }
}
