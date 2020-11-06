package com.crossreview.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crossreview.Model.PreviewInfoModel;
import com.crossreview.R;

import java.util.List;

public class EmployerDetailsRecyclerAdapter extends RecyclerView.Adapter<EmployerDetailsRecyclerAdapter.EmployerDetailsRecyclerViewHolder> {


    private Context context;
    private List<PreviewInfoModel> previewInfoModelList;


    @NonNull
    @Override
    public EmployerDetailsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.employer_deatils_recycler_item, parent, false);
        return new EmployerDetailsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployerDetailsRecyclerViewHolder holder, int position) {

        holder.company_complete_address.setText(previewInfoModelList.get(position).getData().getEmployer().getOrganizationName()+previewInfoModelList.get(position).getData().getEmployer().getOrganizationName());

    }

    @Override
    public int getItemCount() {
        return previewInfoModelList.size();
    }

    public class EmployerDetailsRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView company_complete_address, txt_designation, txt_work_duration, txt_salary, txt_reporting_person_name, txt_reporting_person_designataion;

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
        }
    }

}
