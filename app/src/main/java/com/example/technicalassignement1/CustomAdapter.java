package com.example.technicalassignement1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context context;
    private ArrayList<String> counterList;
    public CustomAdapter(Context context, ArrayList<String> counterList) {
        this.context = context;
        this.counterList = counterList;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView counterName;
        public ViewHolder(View view) {
            super(view);
            counterName = view.findViewById(R.id.counter_name);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.counter_list, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.counterName.setText(counterList.get(position));
    }
    @Override
    public int getItemCount() {
        return counterList.size();
    }
}

