package com.example.twentyone.fragments.entities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.twentyone.R;
import com.example.twentyone.adapter.PointsRecyclerAdapter;
import com.example.twentyone.dialogs.AddPointsDialog;
import com.example.twentyone.model.data.Points;
import com.example.twentyone.model.data.PointsWeek;
import com.example.twentyone.restapi.RestAPIManager;
import com.example.twentyone.restapi.callback.PointsAPICallBack;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EntitiesPointsFragment extends Fragment implements PointsAPICallBack {

    private static final EntitiesPointsFragment ourInstance = new EntitiesPointsFragment();

    public static EntitiesPointsFragment getInstance() {
        return ourInstance;
    }

    public EntitiesPointsFragment() { }

    private TextInputLayout search_layout;
    private TextInputEditText search_text;

    private MaterialButton add_points;

    private RecyclerView recycler;
    public static PointsRecyclerAdapter adapter;

    private View view;
    private Bundle bundle = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        Log.i("ENTITIES-POINTS", "onCreate");

        view = getLayoutInflater().inflate(R.layout.fragment_entities_points, null);
        initView();
        setRecyclerView(view, new ArrayList<Points>());
        RestAPIManager.getInstance().getAllPointsByUser(this,0,new ArrayList<Points>());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //view = inflater.inflate(R.layout.fragment_entities_points, container, false);
        //initView();
        //setRecyclerView(view, new ArrayList<Points>());
        //RestAPIManager.getInstance().getAllPointsByUser(this,0,new ArrayList<Points>());

        Log.i("ENTITIES-POINTS", "onCreateView");
        return view;
    }

    private void initView() {

        search_layout = view.findViewById(R.id.points_search_text_input);
        search_text = view.findViewById(R.id.points_search_edit_text);
        search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });


        search_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (search_text.getRight() - search_text.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Log.i("ENTITIES-SEARCH", "REFRESH");
                        return true;
                    }

                    if(event.getRawX() <= (search_text.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())){
                        Log.i("ENTITIES-SEARCH", "SEARCH");
                        return true;
                    }

                    if(event.getRawX() <= search_text.getTotalPaddingLeft()) {
                        // your action for drawable click event
                        Log.i("ENTITIES-SEARCH", "SEARCH");
                        performSearch();
                        return true;
                    }

                }

                return false;
            }
        });


        add_points = view.findViewById(R.id.add_points_button);
        add_points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddPointsDialog().show(getChildFragmentManager(), "dialog");
            }
        });

        recycler = view.findViewById(R.id.recycler);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("ENTITIES-POINTS", "onPause");

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("ENTITIES-POINTS", "onSave");

    }

    private void performSearch() {

        search_text.clearFocus();
        InputMethodManager in = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(search_text.getWindowToken(), 0);

        adapter.getPointsList().clear();

        String search = search_text.getText().toString();

        RestAPIManager.getInstance().getAllPointsByUserSearch(this,0,search,new ArrayList<Points>());

        adapter.notifyDataSetChanged();
    }

    private void setRecyclerView(View view, ArrayList<Points> points) {
        Log.i("ENTITIES-POINTS", "RECYCLE");

        adapter = new PointsRecyclerAdapter(this.getContext(), points);
        recycler.setAdapter(adapter);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getContext());
        mLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recycler.setLayoutManager(mLinearLayoutManager);

        //recycler.setItemAnimator(new DefaultItemAnimator());
    }

    public static void updateAdapter(ArrayList<Points> points) {
        adapter.getPointsList().clear();
        adapter.setPointsList(points);
        adapter.notifyDataSetChanged();
    }

    public static void updateEntry(int index, Points points) {
        adapter.getPointsList().set(index, points);
        adapter.notifyItemChanged(index);

        //TODO UPDATE ENTRY IN BACKEND

    }

    public static void deleteEntry(int index) {
        adapter.getPointsList().remove(index);
        adapter.notifyItemChanged(index);

        //TODO DELETE ENTRY IN BACKEND

    }


    public static void updatePoints() {

        if (adapter != null) {

            RestAPIManager.getInstance().getAllPointsByUser(new PointsAPICallBack() {
                @Override
                public void onPostPoints(Points points) {

                }

                @Override
                public void onGetPoints(Points points) {

                }

                @Override
                public void onGetPointsWeek(PointsWeek pointsWeek) {

                }

                @Override
                public void onBadRequest() {

                }

                @Override
                public void onFinishedCallback(ArrayList<Points> pointsList) {
                    updateAdapter(pointsList);
                }

                @Override
                public void onFinishedGraphCallback(int value) {
                }

                @Override
                public void onFailure(Throwable t) {

                }
            }, 0, new ArrayList<Points>());
        }

    }

    @Override
    public void onFinishedCallback(ArrayList<Points> points) {
        updateAdapter(points);
    }

    @Override
    public void onFinishedGraphCallback(int value) {

    }

    @Override
    public void onPostPoints(Points points) {

    }

    @Override
    public void onGetPoints(Points points) {

    }

    @Override
    public void onGetPointsWeek(PointsWeek pointsWeek) {

    }

    @Override
    public void onBadRequest() {

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
