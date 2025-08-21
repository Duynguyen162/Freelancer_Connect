package com.example.freelancer_connect.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.model.User;

import java.util.List;

public class ApprovedServiceAdapter extends RecyclerView.Adapter<ApprovedServiceAdapter.UserViewHolder> {

    private List<User> userList;

    public ApprovedServiceAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Dùng layout riêng cho "Đã xác thực"
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_verified_service, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.imgAvatar.setImageResource(user.getAvatarResId());
        holder.tvName.setText(user.getName());

         //Nếu muốn nút khác, có thể thêm ở đây
        holder.btnXem.setOnClickListener(v ->
                System.out.println("Xem chi tiết (Đã xác thực): " + user.getName()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView tvName, tvEmail;
        Button btnXem;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            tvName = itemView.findViewById(R.id.tv_service_name);
            btnXem = itemView.findViewById(R.id.btn_xem);
        }
    }
}
