package com.example.pharmacy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.DrugViewHolder> {

    List<Drug> drugList;
    Context context;

    public DrugAdapter(List<Drug> drugList, Context context) {
        this.drugList = drugList;
        this.context = context;
    }

    @NonNull
    @Override
    public DrugViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.drugs_details,parent,false);
        return new DrugViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrugViewHolder holder, int position) {
        Drug drug = drugList.get(position);
        holder.dris.setText(drug.getIsbn());
        holder.drname.setText(drug.getName());
        holder.drdose.setText(drug.getSerialName());

    }

    @Override
    public int getItemCount(){ return drugList.size();}

    public class DrugViewHolder extends RecyclerView.ViewHolder{

        TextView dris,drname,drdose;
        public DrugViewHolder(@NonNull View itemView){
            super(itemView);
            dris = itemView.findViewById(R.id.drug_name);
            drname = itemView.findViewById(R.id.drug_name);
            drdose = itemView.findViewById(R.id.drug_dose);
        }



    }
}
