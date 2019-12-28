package com.example.hwRoomDB.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hwRoomDB.R;

public class RVAdapterFrag extends RecyclerView.Adapter<RVAdapterFrag.ViewHolder> {

    String[] values;
    FragmentDelegate delegate;
    public interface FragmentDelegate{
        void getPosition(String value, int position);
    }

    public RVAdapterFrag(String[] values, FragmentDelegate adapterFrag) {
        this.values = values;
        this.delegate = adapterFrag;
    }

    @NonNull
    @Override
    public RVAdapterFrag.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_recyclerview,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RVAdapterFrag.ViewHolder holder, final int position) {
        holder.textView.setText(values[position]);
        holder.itemView.setOnClickListener(v -> {
            delegate.getPosition(values[position], position);
            holder.itemView.setBackgroundColor(R.color.purp);
        });
    }

    @Override
    public int getItemCount() {
        return values.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.weeknday_textview);
        }
    }
}
