package com.example.twentyone.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.twentyone.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddWeightDialog extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //iew view2 = getLayoutInflater().inflate(R.layout.dialog_add_points, (ViewGroup) getView());

        builder.setTitle(R.string.add_weight_title)
                .setView(R.layout.dialog_add_weight)
                .setPositiveButton(R.string.add_weight_save, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton(R.string.add_weight_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it

        return builder.create();
    }
}
