package com.example.freelancer_connect.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.model.Notification;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private List<Notification> notifications;
    private Context context;
    private FirebaseFirestore db;

    public NotificationAdapter(Context context, List<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
        this.db = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification n = notifications.get(position);

        holder.tvTitle.setText(n.getTitle());
        holder.tvContent.setText(n.getContent());

        // Hiển thị thời gian nếu có
        if (n.getTimestamp() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            holder.tvTime.setText(sdf.format(n.getTimestamp().toDate()));
        } else {
            holder.tvTime.setText("");
        }


        // Xóa thông báo
        holder.tvDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Xác nhận xóa")
                    .setMessage("Bạn có chắc chắn muốn xóa thông báo này không?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        if (n.getDocumentId() != null) {
                            db.collection("notifications").document(n.getDocumentId())
                                    .delete()
                                    .addOnSuccessListener(aVoid -> {
                                        notifications.remove(position);
                                        notifyItemRemoved(position);
                                        notifyItemRangeChanged(position, notifications.size());
                                        Toast.makeText(context, "Đã xóa thông báo", Toast.LENGTH_SHORT).show();
                                    })
                                    .addOnFailureListener(e ->
                                            Toast.makeText(context, "Xóa thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                                    );
                        }
                    })
                    .setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss())
                    .show();
        });

    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    static class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent, tvEdit, tvDelete, tvTime;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvDelete = itemView.findViewById(R.id.tv_delete);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}
