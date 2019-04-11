package com.example.twentyone.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.twentyone.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.DialogFragment;

public class AddPointsDialog extends DialogFragment {

    private TextInputLayout date_input;
    private TextInputEditText date_text;

    private AppCompatCheckBox exercise;
    private AppCompatCheckBox eat;
    private AppCompatCheckBox drink;

    private TextInputLayout notes_input;
    private TextInputEditText notes_text;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_add_points, null);
        initView(view);

        builder.setTitle(R.string.add_points_title)
                .setView(view)
                .setPositiveButton(R.string.add_points_save, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        savePoints();
                    }
                })
                .setNegativeButton(R.string.add_points_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }

    private void initView(View view) {

        date_input = view.findViewById(R.id.add_points_date_input);
        date_text = view.findViewById(R.id.add_points_date_text);
        setDate();
        exercise = view.findViewById(R.id.add_points_exercise);
        eat = view.findViewById(R.id.add_points_eat);
        drink = view.findViewById(R.id.add_points_drink);
        notes_input = view.findViewById(R.id.add_points_notes_input);
        notes_text = view.findViewById(R.id.add_points_notes_text);
    }

    private void setDate() {

        Calendar calendar = Calendar.getInstance();

        int c_day = calendar.get(Calendar.DAY_OF_MONTH);
        int c_month = calendar.get(Calendar.MONTH);
        int c_year = calendar.get(Calendar.YEAR);

        date_text.setText(c_day + "/" + c_month + "/" + c_year);
    }

    private void savePoints() {

        final String date = date_text.getText().toString();
        final boolean s_exercise = exercise.isChecked();
        final boolean s_eat = eat.isChecked();
        final boolean s_drink = drink.isChecked();
        final String notes = notes_text.getText().toString();

        Log.i("ADD-POINTS", "Date: " + date + " Exercise: " + s_exercise + " Eat: " + s_eat + " Drink: " + s_drink + " Notes: " + notes);

        boolean state = true;

        //TODO ADD POINTS TO BACK-END

        if (state) {
            Snackbar.make(getActivity().findViewById(R.id.main_coordinator) , R.string.add_points_toast_success, Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(getActivity().findViewById(R.id.main_coordinator) , R.string.add_points_toast_error, Snackbar.LENGTH_SHORT).show();
        }
    }
}
