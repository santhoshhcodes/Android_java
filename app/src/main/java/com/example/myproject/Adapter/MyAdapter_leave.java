package com.example.myproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.InterfacePosition;
import com.example.myproject.R;

import com.example.myproject.pojo.leave.Datum;
import com.example.myproject.pojo.leave.ResponsePojo;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter_leave extends RecyclerView.Adapter<MyAdapter_leave.ViewHolder> {

    List<Datum> leaveList = new ArrayList<>();

    Context context;

    InterfacePosition interfacePosition;


    public MyAdapter_leave(Context context,List<Datum> leaveList,InterfacePosition interfacePosition) {
        this.context=context;
        this.leaveList = leaveList;
        this.interfacePosition=interfacePosition;
    }

    @NonNull
    @Override
    public MyAdapter_leave.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_adapter,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter_leave.ViewHolder holder, int position) {


        holder.EmpName.setText(leaveList.get(position).getEmpName());
        holder.AppliedDate.setText(leaveList.get(position).getToDate());
        holder.ComCode.setText(leaveList.get(position).getCompCode());
        holder.LeaveDes.setText(leaveList.get(position).getLeaveType());
        holder.CardView.setOnClickListener(v -> {
            interfacePosition.connecting(position);
        });



    }

    @Override
    public int getItemCount() {
        return leaveList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView EmpName,ComCode,AppliedDate, LeaveDes;

        CardView CardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            EmpName=itemView.findViewById(R.id.EmpNameID);
            AppliedDate=itemView.findViewById(R.id.Applied_OnID);
            ComCode=itemView.findViewById(R.id.CompCodeID);
            LeaveDes=itemView.findViewById(R.id.LeaveDescID);
            CardView = itemView.findViewById(R.id.CardId);




        }
    }
}
