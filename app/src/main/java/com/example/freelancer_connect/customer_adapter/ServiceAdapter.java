package com.example.freelancer_connect.customer_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.home_page.OnClickItemService;
import com.example.freelancer_connect.customer_model.Service;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {
    private List<Service> serviceList;
    private OnClickItemService listener;

    public ServiceAdapter(List<Service> serviceList, OnClickItemService listener) {
        this.serviceList = serviceList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServiceAdapter.ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.ServiceViewHolder holder, int position) {
        final Service service = serviceList.get(position);

        holder.serviceImg.setImageResource(R.drawable.img_user);
        holder.serviceTitle.setText(service.getTitle());
        holder.servicePrice.setText(service.getPrice());
        holder.serviceNumContact.setText("Đã liên lạc: " + service.getNumContact());
        holder.serviceRate.setText(service.getAverageRating());
        holder.serviceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onServiceClickListener(service);
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

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout serviceLayout;
        CircleImageView serviceImg;
        TextView serviceTitle, servicePrice, serviceNumContact, serviceRate;
        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceLayout = itemView.findViewById(R.id.item_service_layout);
            serviceImg = itemView.findViewById(R.id.service_image_view);
            serviceTitle = itemView.findViewById(R.id.text_view_service_title);
            servicePrice = itemView.findViewById(R.id.text_view_service_price);
            serviceNumContact = itemView.findViewById(R.id.text_view_service_num_contact);
            serviceRate = itemView.findViewById(R.id.text_view_service_average_rate);
        }
    }
}
