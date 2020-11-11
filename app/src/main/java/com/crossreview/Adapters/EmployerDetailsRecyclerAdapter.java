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

public class EmployerDetailsRecyclerAdapter extends RecyclerView.Adapter<EmployerDetailsRecyclerAdapter.EmployerDetailsRecyclerViewHolder> {


    private Context context;
    private List<PreviewInfoModel.Experience> previewInfoModelList;


    public EmployerDetailsRecyclerAdapter(Context context, List<PreviewInfoModel.Experience> previewInfoModelList) {
        this.context = context;
        this.previewInfoModelList = previewInfoModelList;

    }

    @NonNull
    @Override
    public EmployerDetailsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.employer_deatils_recycler_item, parent, false);
        return new EmployerDetailsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployerDetailsRecyclerViewHolder holder, int position) {


        if (previewInfoModelList != null) {
            holder.company_complete_address.setText(previewInfoModelList.get(position).getOrganizationId());
            holder.txt_designation.setText(previewInfoModelList.get(position).getEmployeeDesignation());
            holder.txt_work_duration.setText(previewInfoModelList.get(position).getdOJ() + "-" +
                    previewInfoModelList.get(position).getdOR());
            holder.txt_salary.setText(previewInfoModelList.get(position).getLastCTCLac() + " " +
                    previewInfoModelList.get(position).getLastCTCThousand());
            holder.txt_reporting_person_name.setText(previewInfoModelList.get(position).getReportingPersonName());
            holder.txt_reporting_person_designataion.setText(previewInfoModelList.get(position).getReportingPersonDesignation());

            holder.documentRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true));
            holder.documentRecycler.setAdapter(new ExperienceDocumetRecyclerAdapter(previewInfoModelList.get(position).getDocuments(), context));


        }

    }


    @Override
    public int getItemCount() {
        return previewInfoModelList.size();
    }

    public class EmployerDetailsRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView company_complete_address, txt_designation, txt_work_duration, txt_salary, txt_reporting_person_name, txt_reporting_person_designataion;
        RecyclerView documentRecycler;


        public EmployerDetailsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            bindView();
        }

        private void bindView() {


            company_complete_address = itemView.findViewById(R.id.company_complete_address);
            txt_designation = itemView.findViewById(R.id.txt_designation);
            txt_work_duration = itemView.findViewById(R.id.txt_work_duration);
            txt_salary = itemView.findViewById(R.id.txt_salary);
            txt_reporting_person_name = itemView.findViewById(R.id.txt_reporting_person_name);
            txt_reporting_person_designataion = itemView.findViewById(R.id.txt_reporting_person_designataion);
            documentRecycler = itemView.findViewById(R.id.documentRecycler);


        }
    }

}
