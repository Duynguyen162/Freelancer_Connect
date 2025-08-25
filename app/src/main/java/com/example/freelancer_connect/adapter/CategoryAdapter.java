package com.example.freelancer_connect.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.freelancer_connect.R;
import com.example.freelancer_connect.model.Category;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private FirebaseFirestore db;

    public CategoryAdapter(Context context, List<Category> categories) {
        super(context, 0, categories);
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Category category = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_category, parent, false);
        }

        ImageView icon = convertView.findViewById(R.id.category_icon);
        TextView name = convertView.findViewById(R.id.category_name);
        ImageButton deleteBtn = convertView.findViewById(R.id.category_delete_button);
        ImageButton editBtn = convertView.findViewById(R.id.category_edit_button);

        // Gán dữ liệu
        name.setText(category.getName());

        Glide.with(getContext())
                .load(category.getIcon())
                .placeholder(R.drawable.ic_avatar_default)
                .error(R.drawable.ic_avatar_default)
                .into(icon);

        // ====== XỬ LÝ NÚT DELETE ======
        deleteBtn.setOnClickListener(v -> {
            new AlertDialog.Builder(getContext())
                    .setTitle("Xóa danh mục")
                    .setMessage("Bạn có chắc muốn xóa \"" + category.getName() + "\" không?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        db.collection("categories").document(category.getId())
                                .delete()
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(getContext(), "Đã xóa", Toast.LENGTH_SHORT).show();
                                    remove(category); // Xóa khỏi ListView
                                    notifyDataSetChanged();
                                })
                                .addOnFailureListener(e -> {
                                    Log.e("CategoryAdapter", "Lỗi xóa", e);
                                    Toast.makeText(getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                });
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        });

        // ====== XỬ LÝ NÚT EDIT ======
        editBtn.setOnClickListener(v -> {
            // TODO: xử lý sửa (ví dụ mở dialog nhập tên mới rồi update Firestore)
            Toast.makeText(getContext(), "Chỉnh sửa: " + category.getName(), Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}
