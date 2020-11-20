package com.greenwich.madpropertypal.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greenwich.madpropertypal.R;
import com.greenwich.madpropertypal.model.Property;

import java.util.ArrayList;
import java.util.List;

public class MyPropertiesAdapter extends RecyclerView.Adapter<MyPropertiesAdapter.MyPropertiesViewHolder> {


//    Context context;
    private List<Property> properties = new ArrayList<>();

    public MyPropertiesAdapter(){


    }
    @NonNull
    @Override
    public MyPropertiesAdapter.MyPropertiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_property_item,parent,false);
        return new MyPropertiesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPropertiesAdapter.MyPropertiesViewHolder holder, int position) {

        Property currentProperty = properties.get(position);

        holder.tvPropertyName.setText(currentProperty.getName());
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    public void setProperties(List<Property> properties){
        this.properties = properties;
        notifyDataSetChanged();
    }



    public class MyPropertiesViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPropertyName;

        public MyPropertiesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPropertyName = itemView.findViewById(R.id.propertyName);

        }
    }
}
