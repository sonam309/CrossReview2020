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
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;

import java.security.Key;
import java.util.List;

public class PoliceVarificationRecyclerAdapter extends RecyclerView.Adapter<PoliceVarificationRecyclerAdapter.PoliceVarificationRecyclerViewHolder> {

    private List<PreviewInfoModel.PoliceVerification> previewInfoModelList;
    private Context context;

    public PoliceVarificationRecyclerAdapter(List<PreviewInfoModel.PoliceVerification> previewInfoModelList, Context context) {
        this.previewInfoModelList = previewInfoModelList;
        this.context = context;
    }


    @NonNull
    @Override
    public PoliceVarificationRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.police_verification_detail_expand_layout, parent, false);
        return new PoliceVarificationRecyclerAdapter.PoliceVarificationRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PoliceVarificationRecyclerViewHolder holder, int position) {

        //personal detals varifications
        holder.txt_height_tv.setText(previewInfoModelList.get(position).getHeight());
        holder.txt_weight_tv.setText(previewInfoModelList.get(position).getWeight());
        holder.txt_identification_mark.setText(previewInfoModelList.get(position).getIdentificationMarks());
        holder.txt_Complexion.setText(previewInfoModelList.get(position).getComplexion());

        String fatherName=PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.FatherName);
        if(fatherName!=null && fatherName!="") {
           holder.txt_fathers_name_tv.setText(fatherName);
        }

        holder.txt_mothers_name.setText(previewInfoModelList.get(position).getMotherName());
        holder.txt_language_tv.setText(previewInfoModelList.get(position).getLanguageSpoken());
        holder.txt_birth_place.setText(previewInfoModelList.get(position).getBirthPlace());



    }

    @Override
    public int getItemCount() {
        return previewInfoModelList.size();
    }

    public class PoliceVarificationRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView txt_height_tv, txt_weight_tv, txt_identification_mark, txt_Complexion, txt_fathers_name_tv, txt_mothers_name,
                txt_language_tv, txt_birth_place;

        RecyclerView address_RecyclerView;

        public PoliceVarificationRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);


            //police varifications

            txt_height_tv = itemView.findViewById(R.id.txt_height_tv);
            txt_weight_tv = itemView.findViewById(R.id.txt_weight_tv);
            txt_identification_mark = itemView.findViewById(R.id.txt_identification_mark);
            txt_Complexion = itemView.findViewById(R.id.txt_Complexion);
            txt_fathers_name_tv = itemView.findViewById(R.id.txt_fathers_name_tv);
            txt_mothers_name = itemView.findViewById(R.id.txt_mothers_name);
            txt_language_tv = itemView.findViewById(R.id.txt_language_tv);
            txt_birth_place = itemView.findViewById(R.id.txt_birth_place);
            address_RecyclerView = itemView.findViewById(R.id.address_RecyclerView);

        }
    }
}
