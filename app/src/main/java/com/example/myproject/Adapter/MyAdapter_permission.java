package com.example.myproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.model.PermissionModel;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter_permission extends RecyclerView.Adapter<MyAdapter_permission.ViewHolder_permission> {
    List<PermissionModel> permissionModelList = new ArrayList<>();


    public  MyAdapter_permission(List<PermissionModel> permissionlist){
        this.permissionModelList = permissionlist;
    }

    @NonNull
    @Override
    public MyAdapter_permission.ViewHolder_permission onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_permission, parent, false);
        return new ViewHolder_permission(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter_permission.ViewHolder_permission holder, int position) {

        PermissionModel  permissionModel = permissionModelList.get(position);
        holder.Name.setText(permissionModel.getName());
        holder.Time.setText(permissionModel.getTime());
        holder.Status.setText(permissionModel.getStatus());
        holder.Reason.setText(permissionModel.getReason());


    }

    @Override
    public int getItemCount() {
        return permissionModelList.size();
    }

    public class ViewHolder_permission extends RecyclerView.ViewHolder {

        TextView Name, Time, Reason, Status;
        public ViewHolder_permission(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.name);
            Time = itemView.findViewById(R.id.time);
            Reason = itemView.findViewById(R.id.reason);
            Status = itemView.findViewById(R.id.status);



        }
    }
}
