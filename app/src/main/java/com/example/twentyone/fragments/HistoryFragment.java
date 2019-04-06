package com.example.twentyone.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.twentyone.R;
import com.example.twentyone.dialogs.AddPointsDialog;
import com.example.twentyone.dialogs.ResetPassDialog;
import com.example.twentyone.model.Validator;
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HistoryFragment extends Fragment {

    public static final String TAG = HistoryFragment.class.getSimpleName();

    private static final HistoryFragment ourInstance = new HistoryFragment();

    public static HistoryFragment getInstance() {
        return ourInstance;
    }

    public HistoryFragment() {
    }

    private TextView dateText;
    private CalendarView calendarView;
    private int c_day, c_month, c_year;

    private Bundle bundle = null;

    private MaterialButton add_points;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        dateText = view.findViewById(R.id.date_text);

        add_points = view.findViewById(R.id.add_points_button);
        add_points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddPointsDialog().show(getChildFragmentManager(), "dialog");

            }
        });

        calendarView = view.findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                c_day = dayOfMonth;
                c_month = month;
                c_year = year;

                Calendar calendar = Calendar.getInstance();

                calendar.set(c_year, c_month , c_day);

                DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("es", "ES"));

                String s1 = df.format(calendar.getTime());
                String output = s1.substring(0, 1).toUpperCase() + s1.substring(1);

                dateText.setText(output);
                //dateText.setText(df.format(calendar.getTime()));

            }
        });

        Log.i("HISTORY","onCreateView");
        if (bundle != null) {
            Log.i("HISTORY",bundle.toString());

            c_year = bundle.getInt("year");
            c_month = bundle.getInt("month");
            c_day = bundle.getInt("day");
            if (savedInstanceState == null) {
                Log.i("HISTORY", "SAVED NULL, BUNDLE NOT NULL");
            } else {
                Log.i("HISTORY", "SAVED NOT NULL, BUNDLE NOT NULL");
            }
            DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("es", "ES"));
            Calendar calendar = Calendar.getInstance();

            calendar.set(c_year, c_month, c_day);
            String s1 = df.format(calendar.getTime());
            String output = s1.substring(0, 1).toUpperCase() + s1.substring(1);

            dateText.setText(output);
            calendarView.setDate(calendar.getTimeInMillis(), true, true);

        } else {
            Log.i("HISTORY","CREATING SHIIIIIT");
            bundle = new Bundle();

            DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("es", "ES"));
            Calendar calendar = Calendar.getInstance();

            if (savedInstanceState == null) {
                Log.i("HISTORY","SAVED NULL, BUNDLE NULL");
                c_day = calendar.get(Calendar.DAY_OF_MONTH);
                c_month = calendar.get(Calendar.MONTH);
                c_year = calendar.get(Calendar.YEAR);
                String s = df.format(calendarView.getDate());
                String output = s.substring(0, 1).toUpperCase() + s.substring(1);
                dateText.setText(output);
            } else {
                Log.i("HISTORY","SAVED NOT NULL, BUNDLE NULL");

                c_year = savedInstanceState.getInt("year");
                c_month = savedInstanceState.getInt("month");
                c_day = savedInstanceState.getInt("day");

                calendar.set(c_year, c_month, c_day);
                String s1 = df.format(calendar.getTime());
                String output = s1.substring(0, 1).toUpperCase() + s1.substring(1);

                dateText.setText(output);
                calendarView.setDate(calendar.getTimeInMillis(), true, true);
            }
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("HISTORY", "destroyView");

        bundle.putInt("day", c_day);
        bundle.putInt("month", c_month);
        bundle.putInt("year", c_year);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("HISTORY", "pause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("HISTORY", "destroy");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("HISTORY", "SAVE");

        outState.putInt("day", c_day);
        outState.putInt("month", c_month);
        outState.putInt("year", c_year);

        bundle.putInt("day", c_day);
        bundle.putInt("month", c_month);
        bundle.putInt("year", c_year);
    }
}
