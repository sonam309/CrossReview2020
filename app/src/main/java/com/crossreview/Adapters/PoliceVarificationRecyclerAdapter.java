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

public class PoliceVarificationRecyclerAdapter extends RecyclerView.Adapter<PoliceVarificationRecyclerAdapter.PoliceVarificationRecyclerViewHolder> {

    private List<PreviewInfoModel> previewInfoModelList;
    private Context context;

    public PoliceVarificationRecyclerAdapter(List<PreviewInfoModel> previewInfoModelList, Context context) {
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
       holder.txt_height_tv.setText(previewInfoModelList.get(position).getData().getPoliceVerifications().get(position).getHeight());
        holder.txt_weight_tv.setText(previewInfoModelList.get(position).getData().getPoliceVerifications().get(0).getWeight());
        holder.txt_identification_mark.setText(previewInfoModelList.get(position).getData().getPoliceVerifications().get(0).getIdentificationMarks());
        holder.txt_Complexion.setText(previewInfoModelList.get(position).getData().getPoliceVerifications().get(0).getComplexion());
//           holder.txt_fathers_name_tv.setText(previewInfoModelList.getData().getPoliceVerifications().get(0).get);
        holder.txt_mothers_name.setText(previewInfoModelList.get(position).getData().getPoliceVerifications().get(0).getMotherName());
        holder.txt_language_tv.setText(previewInfoModelList.get(position).getData().getPoliceVerifications().get(0).getLanguageSpoken());
        holder.txt_birth_place.setText(previewInfoModelList.get(position).getData().getPoliceVerifications().get(0).getBirthPlace());

        holder.txt_house_no_tv.setText(previewInfoModelList.get(position).getData().getAddresses().get(position).getAddress().toString());
        holder.txt_address_type.setText(previewInfoModelList.get(position).getData().getAddresses().get(position).getAddressType());
        holder.txt_village.setText(previewInfoModelList.get(position).getData().getAddresses().get(position).getVillage());
        holder.txt_post_office.setText(previewInfoModelList.get(position).getData().getAddresses().get(position).getPostOffice());
        holder.txt_police_station.setText(previewInfoModelList.get(position).getData().getAddresses().get(position).getPoliceStation());
        holder.txt_district_tv.setText(previewInfoModelList.get(position).getData().getAddresses().get(position).getDistric());
        holder.txt_state.setText(previewInfoModelList.get(position).getData().getAddresses().get(position).getState());
        holder.txt_city_tv.setText(previewInfoModelList.get(position).getData().getAddresses().get(position).getVillage());
        holder.txt_zipcode.setText(previewInfoModelList.get(position).getData().getAddresses().get(position).getPinCode());

    }

    @Override
    public int getItemCount() {
        return previewInfoModelList.size();
    }

    public class PoliceVarificationRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView txt_height_tv, txt_weight_tv,txt_identification_mark,txt_Complexion,txt_fathers_name_tv,txt_mothers_name,
                txt_language_tv,txt_birth_place,txt_house_no_tv,txt_village,txt_personal_details,txt_address_type,txt_post_office,
                txt_police_station,txt_district_tv,txt_state,txt_city_tv,txt_zipcode;

        public PoliceVarificationRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);


            //police varifications
            txt_personal_details = itemView.findViewById(R.id.txt_personal_details);
            txt_address_type = itemView.findViewById(R.id.txt_address_type);
            txt_height_tv = itemView.findViewById(R.id.txt_height_tv);
            txt_weight_tv = itemView.findViewById(R.id.txt_weight_tv);
            txt_identification_mark = itemView.findViewById(R.id.txt_identification_mark);
            txt_Complexion = itemView.findViewById(R.id.txt_Complexion);
            txt_fathers_name_tv = itemView.findViewById(R.id.txt_fathers_name_tv);
            txt_mothers_name = itemView.findViewById(R.id.txt_mothers_name);
            txt_language_tv = itemView.findViewById(R.id.txt_language_tv);
            txt_birth_place = itemView.findViewById(R.id.txt_birth_place);
            txt_house_no_tv = itemView.findViewById(R.id.txt_house_no_tv);
            txt_village = itemView.findViewById(R.id.txt_village);
            txt_post_office = itemView.findViewById(R.id.txt_post_office);
            txt_police_station = itemView.findViewById(R.id.txt_police_station);
            txt_district_tv = itemView.findViewById(R.id.txt_district_tv);
            txt_state = itemView.findViewById(R.id.txt_state);
            txt_city_tv = itemView.findViewById(R.id.txt_city_tv);
            txt_zipcode = itemView.findViewById(R.id.txt_zipcode);
        }
    }
}
