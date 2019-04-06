package com.example.twentyone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twentyone.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddWeightFragment extends Fragment {

    private TextInputLayout date_input;
    private TextInputEditText date_text;

    private TextInputLayout weight_input;
    private TextInputEditText weight_text;

    private TextInputLayout user_input;
    private TextInputEditText user_text;

    private MaterialButton cancel;
    private MaterialButton save;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_weight, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        date_input = view.findViewById(R.id.add_weight_date_input);
        date_text = view.findViewById(R.id.add_weight_date_text);

        weight_input = view.findViewById(R.id.add_weight_weight_input);
        weight_text = view.findViewById(R.id.add_weight_weight_text);

        user_input = view.findViewById(R.id.add_weight_user_input);
        user_text = view.findViewById(R.id.add_weight_user_text);

        cancel = view.findViewById(R.id.add_weight_cancel);
        save = view.findViewById(R.id.add_weight_save);

    }
}
