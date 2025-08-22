package com.example.freelancer_connect.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.freelancer_connect.R;
import com.example.freelancer_connect.model.Service;
import com.example.freelancer_connect.utils.ServiceStatus;   // 👈 thêm import

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private List<Service> serviceList;

    public ServiceAdapter(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pending_service, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = serviceList.get(position);
        if (service == null) return;

        holder.tvTitle.setText(service.getTitle());
        holder.tvPrice.setText(service.getPrice());

        // Load avatar nếu có
        Glide.with(holder.itemView.getContext())
                .load(service.getPortfolioImage())
                .placeholder(R.drawable.ic_avatar_default)
                .into(holder.imgAvatar);

        // Kiểm tra trạng thái bằng ServiceStatus
        if (ServiceStatus.PENDING.equals(service.getStatus())) {
            holder.btnDuyet.setVisibility(View.VISIBLE);
            holder.btnTuChoi.setVisibility(View.VISIBLE);
        } else {
            holder.btnDuyet.setVisibility(View.GONE);
            holder.btnTuChoi.setVisibility(View.GONE);
        }

        holder.btnXem.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return serviceList != null ? serviceList.size() : 0;
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvPrice;
        ImageView imgAvatar;
        Button btnDuyet, btnTuChoi, btnXem;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_service_name);
            tvPrice = itemView.findViewById(R.id.tv_service_price);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            btnDuyet = itemView.findViewById(R.id.btn_duyet);
            btnTuChoi = itemView.findViewById(R.id.btn_tu_choi);
            btnXem = itemView.findViewById(R.id.btn_xem);
        }
    }
}
