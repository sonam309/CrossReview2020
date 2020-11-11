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

public class AddressRecyclerAdapter extends RecyclerView.Adapter<AddressRecyclerAdapter.AddressRecyclerViewHolder> {

    private List<PreviewInfoModel.Address> addressList;
    private Context context;

    public AddressRecyclerAdapter(List<PreviewInfoModel.Address> addressList, Context context) {
        this.addressList = addressList;
        this.context = context;
    }

    @NonNull
    @Override
    public AddressRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.address_recycler_item, parent, false);
        return new AddressRecyclerAdapter.AddressRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressRecyclerViewHolder holder, int position) {

        holder.txt_house_no_tv.setText(addressList.get(position).getAddress());
        holder.txt_address_type.setText(addressList.get(position).getAddressType());
        holder.txt_village.setText(addressList.get(position).getVillage());
        holder.txt_post_office.setText(addressList.get(position).getPostOffice());
        holder.txt_police_station.setText(addressList.get(position).getPoliceStation());
        holder.txt_district_tv.setText(addressList.get(position).getDistric());
        holder.txt_state.setText(addressList.get(position).getState());
        holder.txt_city_tv.setText(addressList.get(position).getVillage());
        holder.txt_zipcode.setText(addressList.get(position).getPinCode());


    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class AddressRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView txt_house_no_tv, txt_village, txt_address_type, txt_post_office,
                txt_police_station, txt_district_tv, txt_state, txt_city_tv, txt_zipcode;;


        public AddressRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_address_type = itemView.findViewById(R.id.txt_address_type);

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
