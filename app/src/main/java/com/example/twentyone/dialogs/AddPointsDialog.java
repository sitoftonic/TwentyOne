package com.example.twentyone.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twentyone.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddPointsDialog extends DialogFragment {

    private TextInputLayout date_input;
    private TextInputEditText date_text;

    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_points, container, false);
        return view;
    }*/

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //iew view2 = getLayoutInflater().inflate(R.layout.dialog_add_points, (ViewGroup) getView());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_add_points, null);
        date_input = view.findViewById(R.id.add_points_date_input);
        date_text = view.findViewById(R.id.add_points_date_text);

        builder.setTitle(R.string.add_points_title)
                .setView(view)
                .setPositiveButton(R.string.add_points_save, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("POINTS", "CLICK");
                        Log.i("POINTS", date_text.getText().toString());
                        
                    }
                })
                .setNegativeButton(R.string.add_points_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it

        return builder.create();
    }
}
