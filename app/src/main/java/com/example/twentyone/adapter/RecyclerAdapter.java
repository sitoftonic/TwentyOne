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
import com.example.twentyone.model.PointsItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {

    private List<PointsItem> mData;
    private LayoutInflater mInflater;

    public RecyclerAdapter(Context context, List<PointsItem> data) {
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
        
        PointsItem current = mData.get(position);
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
        PointsItem current;

        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title);
            subTitle = itemView.findViewById(R.id.item_subTitle);

        }

        public void setData(PointsItem current, int position) {

            this.title.setText(current.getDate().toString());
            this.subTitle.setText(current.getNotes());

            this.position = position;
            this.current = current;
        }
    }



}
