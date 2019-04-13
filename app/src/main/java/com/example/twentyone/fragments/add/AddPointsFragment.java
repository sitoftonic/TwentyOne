package com.example.twentyone.fragments.add;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.twentyone.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.Fragment;

public class AddPointsFragment extends Fragment {

    private int c_day, c_month, c_year;


    private TextInputLayout date_input;
    private TextInputEditText date_text;

    private AppCompatCheckBox exercise;
    private AppCompatCheckBox eat;
    private AppCompatCheckBox drink;

    private TextInputLayout notes_input;
    private TextInputEditText notes_text;

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

        View view = inflater.inflate(R.layout.fragment_add_points, container, false);

        initView(view);
        /*date_text = view.findViewById(R.id.date_text_edit);

        Calendar calendar = Calendar.getInstance();
        c_day = calendar.get(Calendar.DAY_OF_MONTH);
        c_month = calendar.get(Calendar.MONTH);
        c_year = calendar.get(Calendar.YEAR);

        DatePickerDialog picker = picker = new DatePickerDialog(this.getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date_text.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, c_year, c_month, c_day);

        picker.show();*/


        return view;
    }

    private void initView(View view) {

        date_input = view.findViewById(R.id.add_points_date_input);
        date_text = view.findViewById(R.id.add_points_date_text);
        exercise = view.findViewById(R.id.add_points_exercise);
        eat = view.findViewById(R.id.add_points_eat);
        drink = view.findViewById(R.id.add_points_drink);
        notes_input = view.findViewById(R.id.add_points_notes_input);
        notes_text = view.findViewById(R.id.add_points_notes_text);
        //user_input = view.findViewById(R.id.add_points_user_input);
        //user_text = view.findViewById(R.id.add_points_user_text);
        //cancel = view.findViewById(R.id.add_points_cancel);
        //save = view.findViewById(R.id.add_points_save);
    }
}
