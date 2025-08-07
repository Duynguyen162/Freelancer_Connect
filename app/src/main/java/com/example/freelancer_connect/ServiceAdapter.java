package com.example.freelancer_connect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private List<Service> listService;

    public ServiceAdapter(List<Service> listService) {
        this.listService = listService;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = listService.get(position);
        holder.serviceImage.setImageResource(service.getServiceImage());
        holder.serviceTitle.setText(service.getServiceTitle());
        holder.servicePrice.setText(service.getServicePrice());
        holder.serviceStar.setText(service.getServiceStar());
    }

    @Override
    public int getItemCount() {
        return listService.size();
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        ImageView serviceImage;
        TextView serviceTitle;
        TextView servicePrice;
        TextView serviceStar;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceImage = itemView.findViewById(R.id.service_image);
            serviceTitle = itemView.findViewById(R.id.service_title);
            servicePrice = itemView.findViewById(R.id.service_price);
            serviceStar = itemView.findViewById(R.id.service_star);
        }
    }
}
