package com.crossreview.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crossreview.Model.PreviewInfoModel;
import com.crossreview.R;

import java.util.List;

public class EducationDetailsRecyclerAdapter extends RecyclerView.Adapter<EducationDetailsRecyclerAdapter.EducationDetailsRecyclerViewHolder> {

    private Context context;
    private List<PreviewInfoModel.Education> previewInfoModelList;

    public EducationDetailsRecyclerAdapter(Context context, List<PreviewInfoModel.Education> previewInfoModelList) {
        this.context = context;
        this.previewInfoModelList = previewInfoModelList;
    }

    @NonNull
    @Override
    public EducationDetailsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.education_details_recycler_item, parent, false);
        return new EducationDetailsRecyclerAdapter.EducationDetailsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationDetailsRecyclerViewHolder holder, int position) {

        if (previewInfoModelList != null) {

            holder.txt_institute_name.setText(previewInfoModelList.get(position).getUniversityName());
            holder.txt_course.setText(previewInfoModelList.get(position).getCourse());
            holder.txt_passout_year.setText(previewInfoModelList.get(position).getPassOutYear().toString());
            holder.txt_grade.setText(previewInfoModelList.get(position).getGradingSystem());
            holder.txt_board.setText(previewInfoModelList.get(position).getEducationType());


            holder.documentRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true));
            holder.documentRecycler.setAdapter(new EducationDocumentRecyclerAdapter(previewInfoModelList.get(position).getDocuments(), context));


        }
    }

    @Override
    public int getItemCount() {
        return previewInfoModelList.size();
    }

    public class EducationDetailsRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView txt_institute_name, txt_course, txt_passout_year, txt_grade, txt_board;
        RecyclerView documentRecycler;

        public EducationDetailsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);


            txt_institute_name = itemView.findViewById(R.id.txt_institute_name);
            txt_course = itemView.findViewById(R.id.txt_course);
            txt_passout_year = itemView.findViewById(R.id.txt_passout_year);
            txt_grade = itemView.findViewById(R.id.txt_grade);
            txt_board = itemView.findViewById(R.id.txt_board);
            documentRecycler = itemView.findViewById(R.id.documentRecycler);
        }
    }
}
