package com.example.freelancer_connect.user_service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.customer_model.Service;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyServiceAdapter extends RecyclerView.Adapter<MyServiceAdapter.MyServiceViewHolder> {
    private List<Service> serviceList;
    private OnServiceDeleteListener deleteListener;
    private OnServiceUpdateListener updateListener;

    public MyServiceAdapter(List<Service> serviceList, OnServiceDeleteListener deleteListener, OnServiceUpdateListener updateListener) {
        this.serviceList = serviceList;
        this.deleteListener = deleteListener;
        this.updateListener = updateListener;
    }

    @NonNull
    @Override
    public MyServiceAdapter.MyServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_service_item, parent, false);
        return new MyServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyServiceAdapter.MyServiceViewHolder holder, int position) {
        Service service = serviceList.get(position);

        holder.serviceImg.setImageResource(R.drawable.img_user);
        holder.serviceTitle.setText(service.getTitle());
        holder.servicePrice.setText(service.getPrice());
        holder.serviceRate.setText(service.getAverageRating());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Service serviceToDelete = serviceList.get(position);
                if (deleteListener != null) {
                    deleteListener.onServiceDelete(serviceToDelete.getId(), position);
                }
            }
        });

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Service serviceToUpdate = serviceList.get(position);
                updateListener.onServiceUpdate(serviceToUpdate.getId(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (serviceList == null) {
            return 0;
        }
        return serviceList.size();
    }

    public static class MyServiceViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView serviceImg;
        public TextView serviceTitle, servicePrice, serviceRate;
        public Button btnDelete, btnUpdate;
        public MyServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceImg = itemView.findViewById(R.id.my_service_image);
            serviceTitle = itemView.findViewById(R.id.my_service_title);
            servicePrice = itemView.findViewById(R.id.my_service_price);
            serviceRate = itemView.findViewById(R.id.my_service_star_text);
            btnDelete = itemView.findViewById(R.id.my_service_button_delete);
            btnUpdate = itemView.findViewById(R.id.my_service_button_update);
        }
    }
}
