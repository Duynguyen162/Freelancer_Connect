package com.example.freelancer_connect.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.model.User;

import java.util.List;

public class AuthenticatedServiceAdapter extends RecyclerView.Adapter<AuthenticatedServiceAdapter.UserViewHolder> {
    private List<User> userList;

    public AuthenticatedServiceAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pending_service, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.imgAvatar.setImageResource(user.getAvatarResId());
        holder.tvName.setText(user.getName());


        // Xử lý nút
        holder.btnDuyet.setOnClickListener(v ->
                System.out.println("Duyệt: " + user.getName()));
        holder.btnTuChoi.setOnClickListener(v ->
                System.out.println("Từ chối: " + user.getName()));
        holder.btnXem.setOnClickListener(v ->
                System.out.println("Xem: " + user.getName()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView tvName, tvPrice;
        Button btnDuyet, btnTuChoi, btnXem;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            tvName = itemView.findViewById(R.id.tv_service_name);
            tvPrice = itemView.findViewById(R.id.tv_service_price);
            btnDuyet = itemView.findViewById(R.id.btn_duyet);
            btnTuChoi = itemView.findViewById(R.id.btn_tu_choi);
            btnXem = itemView.findViewById(R.id.btn_xem);
        }
    }
}
