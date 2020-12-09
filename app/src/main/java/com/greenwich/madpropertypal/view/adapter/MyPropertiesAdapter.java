package com.greenwich.madpropertypal.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greenwich.madpropertypal.R;
import com.greenwich.madpropertypal.model.Property;

import java.util.ArrayList;
import java.util.List;

public class MyPropertiesAdapter extends RecyclerView.Adapter<MyPropertiesAdapter.MyPropertiesViewHolder> implements Filterable {


    private List<Property> properties = new ArrayList<>();
    private List<Property> propertiesFullList;


    private OnPropertyClickedListener onPropertyClickedListener;

    public MyPropertiesAdapter(){ }

    public void setOnPropertyClickedListener(OnPropertyClickedListener onPropertyClickedListener) {
        this.onPropertyClickedListener = onPropertyClickedListener;
    }

    @NonNull
    @Override
    public MyPropertiesAdapter.MyPropertiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_property_item,parent,false);
        return new MyPropertiesViewHolder(itemView, onPropertyClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPropertiesAdapter.MyPropertiesViewHolder holder, int position) {

        Property currentProperty = properties.get(position);

        holder.tvPropertyName.setText(currentProperty.getNumber() + " " + currentProperty.getName());
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    public void setProperties(List<Property> properties){
        this.properties = properties;
        propertiesFullList = new ArrayList<>(properties);
        notifyDataSetChanged();
    }


    public class MyPropertiesViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPropertyName;

        public MyPropertiesViewHolder(@NonNull View itemView, final OnPropertyClickedListener onPropertyClickedListener) {
            super(itemView);
            tvPropertyName = itemView.findViewById(R.id.propertyName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(onPropertyClickedListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            onPropertyClickedListener.onPropertyClicked(position);
                        }
                    }
                }
            });

        }
    }

    public interface OnPropertyClickedListener {

        void onPropertyClicked(int position);
    }

    @Override
    public Filter getFilter() {
        return propertyFilter;
    }

    private Filter propertyFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Property> filteredPropertiesList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredPropertiesList.addAll(propertiesFullList);

            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Property property : propertiesFullList){
                    if(property.getName().toLowerCase().contains(filterPattern) || property.getNumber().toLowerCase().contains(filterPattern)){
                        filteredPropertiesList.add(property);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();

            filterResults.values = filteredPropertiesList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            properties.clear();
            properties.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
