package com.example.twentyone.fragments.home;

import android.util.Log;
import android.widget.RadioGroup;

import com.example.twentyone.R;
import com.github.mikephil.charting.charts.LineChart;

public class PointsRadioButtonsListener implements RadioGroup.OnCheckedChangeListener {
    private LineChart pointsChart;

    public PointsRadioButtonsListener(LineChart lineChart){
        this.pointsChart = lineChart;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.radioButtonWeekly:
                // TODO: Mostrar gráfica por semanas
                Log.d("LOLO", "WEEKLY");
                break;
            case R.id.radioButtonMonthly:
                // TODO: Mostrar gráfica por meses
                Log.d("LOLO", "MOTHLY");
                break;
        }
    }
}
