package com.example.twentyone.fragments;

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
import com.example.twentyone.adapter.RecyclerAdapter;
import com.example.twentyone.dialogs.AddPointsDialog;
import com.example.twentyone.model.PointsItem;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EntitiesPointsFragment extends Fragment {

    private TextInputLayout search_layout;
    private TextInputEditText search_text;

    private MaterialButton add_points;

    private RecyclerView recycler;
    private RecyclerAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_entities_points, container, false);

        setRecyclerView(view);

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

        return view;
    }

    private void performSearch() {

        search_text.clearFocus();
        InputMethodManager in = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(search_text.getWindowToken(), 0);

        //TODO SEARCH
    }

    private void setRecyclerView(View view) {
        recycler = view.findViewById(R.id.recycler);
        adapter = new RecyclerAdapter(this.getContext(), PointsItem.getData());
        recycler.setAdapter(adapter);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getContext());
        mLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recycler.setLayoutManager(mLinearLayoutManager);

        //recycler.setItemAnimator(new DefaultItemAnimator());

    }


}
