package com.example.twentyone.fragments.add;

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

public class AddBloodFragment extends Fragment {

    private TextInputLayout date_input;
    private TextInputEditText date_text;

    private TextInputLayout systolic_input;
    private TextInputEditText systolic_text;

    private TextInputLayout diastolic_input;
    private TextInputEditText diastolic_text;

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

        View view = inflater.inflate(R.layout.fragment_add_blood, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        date_input = view.findViewById(R.id.add_blood_date_input);
        date_text = view.findViewById(R.id.add_blood_date_text);

        systolic_input = view.findViewById(R.id.add_blood_systolic_input);
        systolic_text = view.findViewById(R.id.add_blood_systolic_text);

        diastolic_input = view.findViewById(R.id.add_blood_diastolic_input);
        diastolic_text = view.findViewById(R.id.add_blood_diastolic_text);

        user_input = view.findViewById(R.id.add_blood_user_input);
        user_text = view.findViewById(R.id.add_blood_user_text);

        cancel = view.findViewById(R.id.add_blood_cancel);
        save = view.findViewById(R.id.add_blood_save);

    }
}
