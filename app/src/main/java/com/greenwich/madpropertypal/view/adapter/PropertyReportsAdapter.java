package com.greenwich.madpropertypal.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greenwich.madpropertypal.R;
import com.greenwich.madpropertypal.model.Report;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class PropertyReportsAdapter extends RecyclerView.Adapter<PropertyReportsAdapter.PropertyReportsViewHolder> {

    private List<Report> reports = new ArrayList<>();


    public PropertyReportsAdapter(){
    }

    @NonNull
    @Override
    public PropertyReportsViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_report_item,parent,false);

        return new PropertyReportsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyReportsAdapter.PropertyReportsViewHolder holder, int position) {
        Report currentReport = reports.get(position);

        holder.tvInterest.setText(currentReport.getInterest() + " - ");

        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(true);

        holder.tvOfferPrice.setText("£" + numberFormat.format(currentReport.getOfferPrice()).toString());

//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(currentReport.getViewingDate());
        String formattedViewingDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(currentReport.getViewingDate());
        String formattedExpiryDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(currentReport.getOfferExpiryDate());

        holder.tvViewingDate.setText(formattedViewingDate);
        holder.tvConditions.setText(currentReport.getConditionsOfOffer());
        holder.tvComments.setText(currentReport.getViewingComments());
        holder.tvOfferExpiryDate.setText("Expires: " + formattedExpiryDate);
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
        notifyDataSetChanged();
    }

    public class PropertyReportsViewHolder extends RecyclerView.ViewHolder {

        public TextView tvInterest;
        public TextView tvOfferPrice;
        public TextView tvViewingDate;
        public TextView tvConditions;
        public TextView tvComments;
        public TextView tvOfferExpiryDate;

        public PropertyReportsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvInterest = itemView.findViewById(R.id.tvInterest);
            tvOfferPrice = itemView.findViewById(R.id.tvOfferPrice);
            tvViewingDate = itemView.findViewById(R.id.tvViewingDate);
            tvConditions = itemView.findViewById(R.id.tvConditions);
            tvComments = itemView.findViewById(R.id.tvComments);
            tvOfferExpiryDate = itemView.findViewById(R.id.tvOfferExpiryDate);


        }
    }

}
