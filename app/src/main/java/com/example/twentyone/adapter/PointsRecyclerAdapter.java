package com.example.twentyone.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.twentyone.R;
import com.example.twentyone.activities.PointsDetailActivity;
import com.example.twentyone.model.data.Points;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PointsRecyclerAdapter extends RecyclerView.Adapter<PointsRecyclerAdapter.CustomViewHolder> {

    private List<Points> mData;
    private LayoutInflater mInflater;

    public PointsRecyclerAdapter(Context context, List<Points> data) {
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {
        
        Points current = mData.get(position);
        holder.setData(current, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LISTTT", "viewPressed -> " + ((TextView)holder.itemView.findViewById(R.id.item_subTitle)).getText());
                Context context = v.getContext();
                Intent intent = new Intent(context, PointsDetailActivity.class);
                intent.putExtra("note_id", ((TextView)holder.itemView.findViewById(R.id.item_subTitle)).getText());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subTitle;

        int position;
        Points current;

        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title);
            subTitle = itemView.findViewById(R.id.item_subTitle);

        }

        public void setData(Points current, int position) {

            this.title.setText(current.getDate());
            this.subTitle.setText(current.getNotes());

            this.position = position;
            this.current = current;
        }
    }


    public List<Points> getPoints() {
        return mData;
    }

    public void setPoints(List<Points> data) {
        this.mData = data;
    }

}
