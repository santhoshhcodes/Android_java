package com.example.myproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.model.OnDutyModel;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter_onDuty extends RecyclerView.Adapter<MyAdapter_onDuty.ViewHolder_onDuty>{

    List<OnDutyModel> onDutyModelList = new ArrayList<>();

    public MyAdapter_onDuty(List<OnDutyModel> onDutyList) {
        this.onDutyModelList = onDutyList;
    }

    @NonNull
    @Override
    public MyAdapter_onDuty.ViewHolder_onDuty onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myadapter_onduty, parent,false);
        return new ViewHolder_onDuty(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter_onDuty.ViewHolder_onDuty holder, int position) {

        OnDutyModel onDutyModel = onDutyModelList.get(position);
        holder.Name.setText(onDutyModel.getName());
        holder.Time.setText(onDutyModel.getTime());
        holder.Reason.setText(onDutyModel.getReason());
        holder.Status.setText(onDutyModel.getStatus());


    }

    @Override
    public int getItemCount() {
        return onDutyModelList.size();
    }

    public class ViewHolder_onDuty extends RecyclerView.ViewHolder {

        TextView Name, Time, Reason, Status;

        public ViewHolder_onDuty(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.name);
            Time = itemView.findViewById(R.id.time);
            Reason = itemView.findViewById(R.id.reason);
            Status = itemView.findViewById(R.id.status);


        }
    }
}
