package com.example.twentyone.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.twentyone.R;
import com.example.twentyone.model.data.BloodPressure;
import com.example.twentyone.restapi.callback.BloodAPICallBack;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddBloodDialog extends DialogFragment implements BloodAPICallBack {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //iew view2 = getLayoutInflater().inflate(R.layout.dialog_add_points, (ViewGroup) getView());

        builder.setTitle(R.string.add_blood_title)
                .setView(R.layout.dialog_add_blood)
                .setPositiveButton(R.string.add_blood_save, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton(R.string.add_blood_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it

        return builder.create();
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
