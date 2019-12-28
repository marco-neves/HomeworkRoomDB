package com.example.hwRoomDB.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hwRoomDB.R;
import com.example.hwRoomDB.database.HWEntity;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    List<HWEntity> homeworkEntities;
    HomeworkDelegate homeworkDelegate;

    public interface HomeworkDelegate{
        void doSomething(HWEntity homework);
        Context getContext();
    }

    public RVAdapter(List<HWEntity> homeworkEntities, HomeworkDelegate homeworkDelegate) {
        this.homeworkEntities = homeworkEntities;
        this.homeworkDelegate = homeworkDelegate;
    }

    @NonNull
    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homework_recycerview_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, final int position) {
        final HWEntity HWEntity = homeworkEntities.get(position);
        holder.title_textView.setText(homeworkEntities.get(position).getHwDescription());
        holder.date_textView.setText(homeworkEntities.get(position).dateToString());
        holder.done_textView.setText(homeworkEntities.get(position).completedToString());
        boolean completed = HWEntity.isHwDone();
        if(completed) {
            holder.done_textView.setTextColor(Color.rgb(0,180,160));
        }else{
            holder.done_textView.setTextColor(Color.RED);
        }
        holder.itemView.setOnClickListener(v -> {
            homeworkDelegate.doSomething(homeworkEntities.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return homeworkEntities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title_textView, date_textView, done_textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_textView = itemView.findViewById(R.id.tv_title_rvl);
            done_textView = itemView.findViewById(R.id.tv_price_rvl);
            date_textView = itemView.findViewById(R.id.tv_date_rvl);
        }
    }
}
