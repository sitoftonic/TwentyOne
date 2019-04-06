package com.example.twentyone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twentyone.R;
import com.example.twentyone.adapter.RecyclerAdapter;
import com.example.twentyone.dialogs.AddPointsDialog;
import com.example.twentyone.model.PointsItem;
import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EntitiesPointsFragment extends Fragment {

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

        View view = inflater.inflate(R.layout.fragment_entities_points2, container, false);

        setRecyclerView(view);

        add_points = view.findViewById(R.id.add_points_button);
        add_points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddPointsDialog().show(getChildFragmentManager(), "dialog");
            }
        });

        return view;
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
