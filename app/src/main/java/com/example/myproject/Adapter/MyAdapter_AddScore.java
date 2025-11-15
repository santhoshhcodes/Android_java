package com.example.myproject.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myproject.R;
import com.example.myproject.model.StMark;
import com.example.myproject.screen.Exam_mark;

import java.util.List;

public class MyAdapter_AddScore extends RecyclerView.Adapter<MyAdapter_AddScore.ViewHolder> {

    List<StMark> stMarkList;
    Context context;

    public MyAdapter_AddScore(List<StMark> stMarkList,  Context context) {
        this.stMarkList = stMarkList;
        this.context = context ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_score, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StMark stMark = stMarkList.get(position);
        holder.sno.setText(String.valueOf(position + 1));
        holder.name.setText(stMark.getStName());
        holder.tamil.setText(stMark.getTamil());
        holder.eng.setText(stMark.getEnglish());
        holder.math.setText(stMark.getMaths());
        holder.sci.setText(stMark.getScience());
        holder.social.setText(stMark.getSocial());
        holder.total.setText(stMark.getTotal());

        holder.delete.setOnClickListener(v -> {

            new AlertDialog.Builder(context)
                    .setTitle("Delete Student")
                    .setMessage("Are you sure want to delete the student record!!")
                    .setPositiveButton("yes",((dialog, which) -> {
                        stMarkList.remove(position);
                        notifyItemRemoved(position);
//                        notifyItemRangeChanged(position, stMarkList.size());
                    }))
                    .setNegativeButton("No", null).show();
        });

        holder.edit.setOnClickListener(v -> {
            showEditStudentMark(position);
        });

    }

    private void showEditStudentMark(int position) {
        StMark mark = stMarkList.get(position);

        View dialogview = LayoutInflater.from(context).inflate(R.layout.dialog_editview, null);
        EditText name = dialogview.findViewById(R.id.editStName);
        EditText tamil = dialogview.findViewById(R.id.editTamilScore);
        EditText english = dialogview.findViewById(R.id.editEnglishScore);
        EditText maths = dialogview.findViewById(R.id.editMathsScore);
        EditText science = dialogview.findViewById(R.id.editScienceScore);
        EditText social = dialogview.findViewById(R.id.editSocialScore);

        name.setText(mark.getStName());
        tamil.setText(mark.getTamil());
        english.setText(mark.getEnglish());
        maths.setText(mark.getMaths());
        science.setText(mark.getScience());
        social.setText(mark.getSocial());

        new AlertDialog.Builder(context)
                .setTitle("Edit Student Scores")
                .setView(dialogview)
                .setPositiveButton("update", (dialog, which) -> {

                    int total =
                            Integer.parseInt(tamil.getText().toString())
                            +Integer.parseInt(english.getText().toString())
                            +Integer.parseInt(maths.getText().toString())
                            +Integer.parseInt(science.getText().toString())
                            +Integer.parseInt(social.getText().toString());

                    mark.setStName(name.getText().toString());
                    mark.setTamil(tamil.getText().toString());
                    mark.setEnglish(english.getText().toString());
                    mark.setMaths(maths.getText().toString());
                    mark.setScience(science.getText().toString());
                    mark.setSocial(social.getText().toString());
                    mark.setTotal(String.valueOf(total));

                    notifyItemChanged(position);

                })
                .setNegativeButton("cancel", null)
                .show();

    }

    @Override
    public int getItemCount() {
        return stMarkList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sno, name, tamil, eng, math, sci, social, total;
        ImageView edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sno = itemView.findViewById(R.id.snoID);
            name = itemView.findViewById(R.id.nameID);
            tamil = itemView.findViewById(R.id.tamilID);
            eng = itemView.findViewById(R.id.engID);
            math = itemView.findViewById(R.id.mathsId);
            sci = itemView.findViewById(R.id.sciId);
            social = itemView.findViewById(R.id.socialID);
            total = itemView.findViewById(R.id.totalID);
            delete = itemView.findViewById(R.id.deleteImgID);
            edit = itemView.findViewById(R.id.editImgID);
        }
    }
}
