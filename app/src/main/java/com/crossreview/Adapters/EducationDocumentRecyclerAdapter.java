package com.crossreview.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.crossreview.Model.PreviewInfoModel;
import com.crossreview.R;
import com.crossreview.Utilites.Utility;

import java.util.List;

import okhttp3.internal.Util;

public class EducationDocumentRecyclerAdapter extends RecyclerView.Adapter<EducationDocumentRecyclerAdapter.EducationDocumentRecyclerViewHolder>{


    private List<PreviewInfoModel.Document> previewInfoModelList;
    private Context context;

    public EducationDocumentRecyclerAdapter(List<PreviewInfoModel.Document> previewInfoModelList, Context context) {
        this.previewInfoModelList = previewInfoModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public EducationDocumentRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.upload_documet_recycler_item, parent, false);
        return new EducationDocumentRecyclerAdapter.EducationDocumentRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationDocumentRecyclerViewHolder holder, int position) {

        Glide.with(context)
                .load(previewInfoModelList.get(position).getDocumentURL())
                .placeholder(Utility.getCircleProgress())
                .into(holder.doc_file);

        holder.doc_name.setText(previewInfoModelList.get(position).getDocumentName());


    }

    @Override
    public int getItemCount() {
        return previewInfoModelList.size();
    }

    public class EducationDocumentRecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView doc_file;
        TextView doc_name;

        public EducationDocumentRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);


            doc_file = itemView.findViewById(R.id.doc_file);
            doc_name = itemView.findViewById(R.id.doc_name);
        }
    }
}
