package com.example.twentyone.fragments.home;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.twentyone.R;
import com.example.twentyone.dialogs.AddBloodDialog;
import com.example.twentyone.dialogs.AddPointsDialog;
import com.example.twentyone.dialogs.AddWeightDialog;
import com.example.twentyone.model.data.Points;
import com.example.twentyone.model.data.PointsWeek;
import com.example.twentyone.restapi.RestAPIManager;
import com.example.twentyone.restapi.callback.PointsAPICallBack;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment implements PointsAPICallBack {

    public static final String TAG = HomeFragment.class.getSimpleName();

    private View view;
    private TextView user_text;

    private MaterialButton add_points;
    private MaterialButton add_blood;
    private MaterialButton add_weight;

    private Chip chipPoints;
    private LineChart chartPoints;

    private static final HomeFragment ourInstance = new HomeFragment();

    public static HomeFragment getInstance() {
        return ourInstance;
    }

    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        initView();

        return view;
    }

    private void initView() {

        user_text = view.findViewById(R.id.user_text);

        chartPoints = view.findViewById(R.id.chartPoints);
        chipPoints = view.findViewById(R.id.chipPoints);

        String user = getArguments().getString("user");

        if (user != null) {
            user_text.setText(user);
        }


        add_points = view.findViewById(R.id.add_points_button);
        add_points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddPointsDialog().show(getChildFragmentManager(), "points_dialog");
            }
        });

        add_blood = view.findViewById(R.id.add_blood_button);
        add_blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddBloodDialog().show(getChildFragmentManager(), "blood_dialog");
            }
        });

        add_weight = view.findViewById(R.id.add_weight_button);
        add_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddWeightDialog().show(getChildFragmentManager(), "weight_dialog");
            }
        });

        getPoints();
        //getBlood();
        //getWeight();

    }

    private void getWeight() {

    }

    private void getBlood() {

    }

    private void getPoints() {
        ArrayList<Integer> points = new ArrayList<>(7);
        for (int i = 0; i < 7; i++){
            points.add(0);
        }

        RestAPIManager.getInstance().getPointsLast7Days(this, 0, points);
    }

    private void initChartPoints(ArrayList<Integer> points) {

        if (noValues(points)){
            // Ocultar gráfica
            chartPoints.setVisibility(View.GONE);
            // Mostrar chip
            chipPoints.setVisibility(View.VISIBLE);
        }else{
            // Ocultar chip
            chipPoints.setVisibility(View.GONE);

            {   // // Chart Style // //
                // background color
                chartPoints.setBackgroundColor(Color.WHITE);

                // disable description text
                chartPoints.getDescription().setEnabled(false);

                // enable touch gestures
                chartPoints.setTouchEnabled(true);

                // enable scaling and dragging
                chartPoints.setDragEnabled(true);
                chartPoints.setScaleEnabled(true);

                // force pinch zoom along both axis
                chartPoints.setPinchZoom(true);
            }

            XAxis xAxis;
            {   // // X-Axis Style // //
                xAxis = chartPoints.getXAxis();

                // vertical grid lines
                xAxis.enableGridDashedLine(10f, 10f, 0f);
            }

            YAxis yAxis;
            {   // // Y-Axis Style // //
                yAxis = chartPoints.getAxisLeft();

                // disable dual axis (only use LEFT axis)
                chartPoints.getAxisRight().setEnabled(false);

                // horizontal grid lines
                yAxis.enableGridDashedLine(10f, 10f, 0f);

                // axis range
                yAxis.setAxisMaximum(3);
                yAxis.setAxisMinimum(0);
            }


            // add data
            setDataPoints(points);


            // get the legend (only possible after setting data)
            Legend l = chartPoints.getLegend();

            // draw legend entries as lines
            l.setForm(Legend.LegendForm.LINE);

            LegendEntry[] entries = new LegendEntry[1];
            entries[0] = new LegendEntry();
            entries[0].label = "Points for last 7 days";
            l.setCustom(entries);

            // Mostrar gráfica
            chartPoints.setVisibility(View.VISIBLE);
        }
    }

    private boolean noValues(ArrayList<Integer> points) {
        for (Integer n : points) {
            if (n != 0)
                return false;
        }
        return true;
    }

    private void setDataPoints(ArrayList<Integer> points) {
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = points.size() - 1; i >= 0; i--) {
            values.add(new Entry(i, points.get(i)));
        }

        LineDataSet set1;

        if (chartPoints.getData() != null &&
                chartPoints.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chartPoints.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            chartPoints.getData().notifyDataChanged();
            chartPoints.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setDrawIcons(false);

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chartPoints.getAxisLeft().getAxisMinimum();
                }
            });

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this.getContext(), R.drawable.fade_blue);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            chartPoints.setData(data);
        }
    }

    private void initChartBlood() {
        chartPoints = view.findViewById(R.id.chartPoints);


    }

    private void initChartWeight() {
        chartPoints = view.findViewById(R.id.chartPoints);

    }

    @Deprecated
    @Override
    public void onPostPoints(Points points) {

    }
    @Deprecated
    @Override
    public void onGetPoints(Points points) {

    }
    @Deprecated
    @Override
    public void onGetPointsWeek(PointsWeek pointsWeek) {

    }
    @Deprecated
    @Override
    public void onBadRequest() {

    }
    @Deprecated
    @Override
    public void onFinishedCallback(ArrayList<Points> pointsList) {

    }
    @Deprecated
    @Override
    public void onFinishedGraphCallback(int value) {

    }
    @Deprecated
    @Override
    public void onFinished7LastMonths(ArrayList<Integer> values) {

    }
    @Deprecated
    @Override
    public void onFinished7LastDays(ArrayList<Integer> values) {
        // Hemos obtenido los puntos de los últimos 7 días
        // puestos de [0] hoy a [6] día más antiguo
        initChartPoints(values);
    }

    @Override
    public void onFinished7LastWeeks(ArrayList<Integer> values) {

    }

    @Deprecated
    @Override
    public void onFailure(Throwable t) {
        // Ocultar todos los gráficos
        chartPoints.setVisibility(View.GONE);
        // Mostrar todos los chips
        chipPoints.setText(getString(R.string.str_connectionError));
        chipPoints.setVisibility(View.VISIBLE);
    }

    // TODO: OnDataRequiredFailed para mostrar los chips y no los gráficos
    // TODO: OnPointsRequiredSuccess para mostrar chart de puntos y esconder chip
    // TODO: OnBloodRequiredSuccess para mostrar chart de blood y esconder chip
    // TODO: OnWeightRequiredSuccess para mostrar chart de weight y esconder chip
}
