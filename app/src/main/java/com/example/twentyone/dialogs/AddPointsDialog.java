package com.example.twentyone.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.twentyone.R;
import com.example.twentyone.fragments.entities.EntitiesPointsFragment;
import com.example.twentyone.model.data.Points;
import com.example.twentyone.model.data.PointsWeek;
import com.example.twentyone.restapi.RestAPIManager;
import com.example.twentyone.restapi.callback.PointsAPICallBack;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

public class AddPointsDialog extends DialogFragment implements PointsAPICallBack {

    private TextInputLayout date_input;
    private TextInputEditText date_text;

    private AppCompatCheckBox exercise;
    private AppCompatCheckBox eat;
    private AppCompatCheckBox drink;

    private TextInputLayout notes_input;
    private TextInputEditText notes_text;

    private FragmentActivity activity;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_add_points, null);
        initView(view);

        activity = getActivity();

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
        int s_exercise = 0, s_eat = 0, s_drink = 0;

        if (exercise.isChecked()) s_exercise = 1;
        if (eat.isChecked()) s_eat = 1;
        if (drink.isChecked()) s_drink = 1;

        final String notes = notes_text.getText().toString();

        Log.i("ADD-POINTS", "Date: " + date + " Exercise: " + s_exercise + " Eat: " + s_eat + " Drink: " + s_drink + " Notes: " + notes);

        RestAPIManager.getInstance().postPoints(new Points(date, s_exercise, s_eat, s_drink, notes),this);
    }

    @Override
    public void onPostPoints(Points points) {
        Snackbar.make(activity.findViewById(R.id.main_coordinator) , R.string.add_points_toast_success, Snackbar.LENGTH_SHORT).show();
        EntitiesPointsFragment.updatePoints();
    }

    @Override
    public void onGetPoints(Points points) {

    }

    @Override
    public void onGetPointsWeek(PointsWeek pointsWeek) {

    }

    @Override
    public void onBadRequest() {

    }

    @Override
    public void onFinishedCallback(ArrayList<Points> pointsList) {
        // TODO: Call fragment - onCreate - to update points data
    }

    @Override
    public void onFinishedGraphCallback(int value) {

    }

    @Override
    public void onFinished7LastMonths(ArrayList<Integer> values) {

    }

    @Override
    public void onFinished7LastDays(ArrayList<Integer> values) {

    }

    @Override
    public void onFailure(Throwable t) {
        Snackbar.make(activity.findViewById(R.id.main_coordinator) , R.string.add_points_toast_error, Snackbar.LENGTH_SHORT).show();
    }
}
