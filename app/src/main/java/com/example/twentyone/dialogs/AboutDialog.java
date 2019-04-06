package com.example.twentyone.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.twentyone.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AboutDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.dialog_about_title)
                .setMessage(R.string.dialog_about_message)
                .setView(R.layout.dialog_about);
        // Create the AlertDialog object and return it

        return builder.create();
    }

}
