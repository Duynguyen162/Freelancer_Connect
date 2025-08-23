package com.example.freelancer_connect.adapter;

import android.content.Context;
import android.util.Log;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private List<Service> serviceList;
    private Context context;

    public ServiceAdapter(Context context, List<Service> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_pending_service, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = serviceList.get(position);
        if (service == null) return;

        holder.tvTitle.setText(service.getTitle());
        holder.tvPrice.setText(service.getPrice());

        String imageName = service.getPortfolioImage();
        int drawableId = 0;
        if (imageName != null && !imageName.isEmpty()) {
            drawableId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        }

        Glide.with(context)
                .load(drawableId > 0 ? drawableId : R.drawable.ic_avatar_default)
                .placeholder(R.drawable.ic_avatar_default)
                .into(holder.imgAvatar);


        // Kiểm tra trạng thái "Đang chờ duyệt"
        boolean isPending = service.getStatus() != null &&
                service.getStatus().trim().equalsIgnoreCase("Đang chờ duyệt");

        holder.btnDuyet.setVisibility(isPending ? View.VISIBLE : View.GONE);
        holder.btnTuChoi.setVisibility(isPending ? View.VISIBLE : View.GONE);

        // Xử lý click Duyệt
        holder.btnDuyet.setOnClickListener(v -> {
            if (service.getDocumentId() == null || service.getDocumentId().isEmpty()) {
                Log.e("ServiceAdapter", "documentId null, không thể update");
                return;
            }
            FirebaseFirestore.getInstance()
                    .collection("services")
                    .document(service.getDocumentId())
                    .update("status", "Đã được duyệt")
                    .addOnSuccessListener(aVoid -> {
                        service.setStatus("Đã được duyệt");
                        notifyItemChanged(position);
                        Log.d("ServiceAdapter", "Duyệt thành công: " + service.getTitle());
                    })
                    .addOnFailureListener(e -> Log.e("ServiceAdapter", "Duyệt thất bại", e));
        });

        // Xử lý click Từ chối
        holder.btnTuChoi.setOnClickListener(v -> {
            if (service.getDocumentId() == null || service.getDocumentId().isEmpty()) {
                Log.e("ServiceAdapter", "documentId null, không thể update");
                return;
            }
            FirebaseFirestore.getInstance()
                    .collection("services")
                    .document(service.getDocumentId())
                    .update("status", "Đã từ chối")
                    .addOnSuccessListener(aVoid -> {
                        service.setStatus("Đã từ chối");
                        notifyItemChanged(position);
                        Log.d("ServiceAdapter", "Từ chối thành công: " + service.getTitle());
                    })
                    .addOnFailureListener(e -> Log.e("ServiceAdapter", "Từ chối thất bại", e));
        });

        // Click xem chi tiết
        holder.btnXem.setOnClickListener(v ->
                Log.d("ServiceAdapter", "Xem chi tiết: " + service.getTitle()));
    }

    @Override
    public int getItemCount() {
        return serviceList != null ? serviceList.size() : 0;
    }

    // --------- Hàm duyệt tất cả dịch vụ pending ---------
    public void approveAllPendingServices() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("services")
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    if (!querySnapshot.isEmpty()) {
                        for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                            String docId = doc.getId();
                            String status = doc.getString("status");
                            if (status != null && status.equalsIgnoreCase("Đang chờ duyệt")) {
                                Map<String, Object> updates = new HashMap<>();
                                updates.put("status", "Đã được duyệt");

                                db.collection("services")
                                        .document(docId)
                                        .update(updates)
                                        .addOnSuccessListener(aVoid ->
                                                Log.d("ServiceAdapter", "Duyệt thành công: " + docId))
                                        .addOnFailureListener(e ->
                                                Log.e("ServiceAdapter", "Duyệt thất bại: " + docId, e));
                            }
                        }
                    } else {
                        Log.d("ServiceAdapter", "Không có document pending nào");
                    }
                })
                .addOnFailureListener(e -> Log.e("ServiceAdapter", "Lỗi lấy document", e));
    }

    // ------------------------------------------------------

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
