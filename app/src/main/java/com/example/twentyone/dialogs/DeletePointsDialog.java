package com.example.twentyone.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.twentyone.R;
import com.example.twentyone.activities.PointsDetailActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DeletePointsDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.dialog_delete_points_title)
                .setMessage(R.string.dialog_delete_points_msg)
                .setPositiveButton(R.string.dialog_delete_points_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PointsDetailActivity activity = (PointsDetailActivity)getActivity();
                        activity.deleteEntry();
                    }
                })
                .setNegativeButton(R.string.dialog_delete_points_negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it

        return builder.create();
    }

}
