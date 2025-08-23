package com.example.freelancer_connect.notify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.customer_model.Notification;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private List<Notification> listNotification;

    public NotificationAdapter(List<Notification> listNotification) {
        this.listNotification = listNotification;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_customer_notification, parent, false);
        return new NotificationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification noti = listNotification.get(position);
        holder.notifyContent.setText(noti.getContent());
    }

    @Override
    public int getItemCount() {
        if (listNotification != null) {
            return listNotification.size();
        }
        return 0;
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        public TextView notifyContent;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            notifyContent = itemView.findViewById(R.id.txt_notify);
        }
    }
}
