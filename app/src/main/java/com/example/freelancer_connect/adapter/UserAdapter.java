package com.example.freelancer_connect.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;

    // Constructor nhận dữ liệu danh sách
    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    // ViewHolder lưu tham chiếu đến View thành phần trong item
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView imageAvatar;
        TextView textName, textCode;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAvatar = itemView.findViewById(R.id.imageAvatar);
            textName = itemView.findViewById(R.id.textName);
            textCode = itemView.findViewById(R.id.textCode);
        }
    }

    // Tạo ViewHolder mới từ layout item_user.xml
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    // Gán dữ liệu vào ViewHolder cho vị trí position
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.textName.setText(user.getName());
        holder.textCode.setText("Mã: " + user.getCode());
        holder.imageAvatar.setImageResource(user.getAvatarResId());
    }

    // Trả về số lượng item trong danh sách
    @Override
    public int getItemCount() {
        return userList.size();
    }
}