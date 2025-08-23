package com.example.freelancer_connect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.freelancer_connect.R;
import com.example.freelancer_connect.model.Service;

import java.util.List;

public class ApprovedServiceAdapter extends RecyclerView.Adapter<ApprovedServiceAdapter.ServiceViewHolder> {

    private List<Service> serviceList;
    private Context context;

    public ApprovedServiceAdapter(Context context, List<Service> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_verified_service, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = serviceList.get(position);
        if (service == null) return;

        holder.tvTitle.setText(service.getTitle());
        holder.tvPrice.setText(service.getPrice());

        // Load ảnh portfolio nếu có
        if (service.getPortfolioImage() != null && !service.getPortfolioImage().isEmpty()) {
            int drawableId = context.getResources().getIdentifier(
                    service.getPortfolioImage(), "drawable", context.getPackageName());
            Glide.with(context)
                    .load(drawableId > 0 ? drawableId : R.drawable.ic_avatar_default)
                    .placeholder(R.drawable.ic_avatar_default)
                    .into(holder.imgAvatar);
        } else {
            holder.imgAvatar.setImageResource(R.drawable.ic_avatar_default);
        }
    }

    @Override
    public int getItemCount() {
        return serviceList != null ? serviceList.size() : 0;
    }

    static class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvPrice;
        ImageView imgAvatar;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_service_name);
            tvPrice = itemView.findViewById(R.id.tv_service_price);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
        }
    }
}
