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

public class AddPreferencesFragment extends Fragment {

    private TextInputLayout goals_input;
    private TextInputEditText goals_text;
    private TextInputLayout units_input;
    private TextInputEditText units_text;
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

        View view = inflater.inflate(R.layout.fragment_add_preferences, container, false);

        initView(view);

        return view;
    }

    private void initView(View view)
    {
        goals_input = view.findViewById(R.id.add_preferences_goals_input);
        goals_text = view.findViewById(R.id.add_preferences_goals_text);
        units_input = view.findViewById(R.id.add_preferences_units_input);
        units_text = view.findViewById(R.id.add_preferences_units_text);
        user_input = view.findViewById(R.id.add_preferences_user_input);
        user_text = view.findViewById(R.id.add_preferences_user_text);
        cancel = view.findViewById(R.id.add_preferences_cancel);
        save = view.findViewById(R.id.add_preferences_save);
    }
}
