package com.example.twentyone.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.twentyone.R;
import com.example.twentyone.model.data.BloodPressure;
import com.example.twentyone.restapi.callback.BloodAPICallBack;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.DialogFragment;

public class AddBloodDialog extends DialogFragment implements BloodAPICallBack {

    private TextInputLayout date_input;
    private TextInputEditText date_text;

    private TextInputLayout systolic_input;
    private TextInputEditText systolic_text;

    private TextInputLayout diastolic_input;
    private TextInputEditText diastolic_text;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_add_blood, null);
        initView(view);

        builder.setTitle(R.string.add_blood_title)
                .setView(view)
                .setPositiveButton(R.string.add_blood_save, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        saveBlood();
                    }
                })
                .setNegativeButton(R.string.add_blood_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it

        return builder.create();
    }

    private void initView(View view) {

        date_input = view.findViewById(R.id.add_blood_date_input);
        date_text = view.findViewById(R.id.add_blood_date_text);
        systolic_input = view.findViewById(R.id.add_blood_systolic_input);
        systolic_text = view.findViewById(R.id.add_blood_systolic_text);
        diastolic_input = view.findViewById(R.id.add_blood_diastolic_input);
        diastolic_text = view.findViewById(R.id.add_blood_diastolic_text);
    }

    private void saveBlood() {

        final String date = date_text.getText().toString();
        final String systolic = systolic_text.getText().toString();
        final String diastolic = diastolic_text.getText().toString();

        Log.i("ADD- BLOOD", "Date: " + date + " Systolic: " + systolic + " Diastolic: " + diastolic);

    }

    @Override
    public void onGetAllBloodPressure() {

    }

    @Override
    public void onPostBloodPressure(BloodPressure bloodPressure) {

    }

    @Override
    public void onFinishedCallback(List<BloodPressure> bloodList) {

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
