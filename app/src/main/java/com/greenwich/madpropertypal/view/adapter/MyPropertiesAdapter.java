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

public class MyPropertiesAdapter extends RecyclerView.Adapter<MyPropertiesAdapter.MyPropertiesViewHolder>  {


    private List<Property> properties = new ArrayList<>();

    private OnPropertyClickedListener onPropertyClickedListener;




    public MyPropertiesAdapter(){


    }

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
}
