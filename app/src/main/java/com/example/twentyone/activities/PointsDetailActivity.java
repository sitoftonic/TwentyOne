package com.example.twentyone.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.twentyone.R;
import com.example.twentyone.adapter.PointsRecyclerAdapter;
import com.example.twentyone.dialogs.DeletePointsDialog;
import com.example.twentyone.dialogs.UpdatePointsDialog;
import com.example.twentyone.fragments.entities.EntitiesPointsFragment;
import com.example.twentyone.model.data.Points;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.Toolbar;

public class PointsDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Points points;

    private TextInputLayout date_input;
    private TextInputEditText date_text;

    private AppCompatCheckBox exercise;
    private AppCompatCheckBox eat;
    private AppCompatCheckBox drink;

    private TextInputLayout notes_input;
    private TextInputEditText notes_text;

    private TextInputLayout user_input;
    private TextInputEditText user_text;

    private MaterialButton button_1;
    private MaterialButton button_2;

    private boolean edit = false;

    private Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bundle = getIntent().getExtras();
        initToolbar(bundle);

        points = PointsRecyclerAdapter.getPoints(bundle.getInt("entry_id", 0));

        initView(bundle);
        setFields();
        setFieldsState(false);
    }


    private void initToolbar(Bundle bundle) {

        toolbar = findViewById(R.id.my_toolbar);

        String title = "Points Entry " + (bundle.getInt("entry_id", 0) + 1);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView(final Bundle bundle) {

        final View view = findViewById(R.id.fragment_add);

        date_input = view.findViewById(R.id.add_points_date_input);
        date_text = view.findViewById(R.id.add_points_date_text);

        exercise = view.findViewById(R.id.add_points_exercise);
        eat = view.findViewById(R.id.add_points_eat);
        drink = view.findViewById(R.id.add_points_drink);

        notes_input = view.findViewById(R.id.add_points_notes_input);
        notes_text = view.findViewById(R.id.add_points_notes_text);

        button_1 = view.findViewById(R.id.add_points_button1);
        button_1.setText(R.string.add_points_delete);
        button_1.setIcon(getResources().getDrawable(R.drawable.ic_round_clear_24px));
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DETAILS",((MaterialButton)v).getText().toString() );

                if (((MaterialButton)v).getText().toString().equals("Cancel")) {
                    Log.i("DETAILS",((MaterialButton)v).getText().toString() );
                    edit = false;
                    setFields();
                    setEditState(false);
                } else {
                    new DeletePointsDialog().show(getSupportFragmentManager(), "delete_points");
                }
            }
        });

        button_2 = view.findViewById(R.id.add_points_button2);
        button_2.setText(R.string.add_points_edit);
        button_2.setIcon(getResources().getDrawable(R.drawable.ic_edit_black_24dp));
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((MaterialButton)v).getText().toString().equals("Save")) {
                    new UpdatePointsDialog().show(getSupportFragmentManager(), "update_points");
                } else if (((MaterialButton)v).getText().toString().equals("Edit")) {
                    edit = true;
                    setEditState(true);
                }
            }
        });
    }

    public void setEditState(boolean state) {

        setFieldsState(state);
        if (state) {
            button_1.setText(R.string.add_points_cancel);
            button_1.setIcon(null);
            button_2.setText(R.string.add_points_save);
            button_2.setIcon(null);
        } else {
            button_1.setText(R.string.add_points_delete);
            button_1.setIcon(getResources().getDrawable(R.drawable.ic_round_clear_24px));
            button_2.setText(R.string.add_points_edit);
            button_2.setIcon(getResources().getDrawable(R.drawable.ic_edit_black_24dp));
        }
    }

    private void setFields() {
        date_text.setText(points.getDate());
        exercise.setChecked(points.getExercise() == 1);
        eat.setChecked(points.getMeals() == 1);
        drink.setChecked(points.getAlcohol() == 1);
        notes_text.setText(points.getNotes());

    }

    public void setFieldsState(boolean state) {
        date_text.setEnabled(state);
        exercise.setEnabled(state);
        eat.setEnabled(state);
        drink.setEnabled(state);
        notes_text.setEnabled(state);
    }

    public void updateEntry() {

        Points p = new Points();
        p.setDate(date_text.getText().toString());
        p.setExercise(exercise.isChecked() ? 1 : 0);
        p.setMeals(eat.isChecked() ? 1 : 0);
        p.setAlcohol(drink.isChecked() ? 1 : 0);
        p.setNotes(notes_text.getText().toString());

        EntitiesPointsFragment.updateEntry(bundle.getInt("entry_id", 0), p);
    }

    public void deleteEntry() {

        EntitiesPointsFragment.deleteEntry(bundle.getInt("entry_id", 0));
        onBackPressed();
    }
}
