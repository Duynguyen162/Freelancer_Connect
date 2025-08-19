package com.example.freelancer_connect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.model.Category;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {
    public CategoryAdapter(Context context, List<Category> categories) {
        super(context, 0, categories);
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

        icon.setImageResource(category.getIconResId());
        name.setText(category.getName());
        // Xử lý sự kiện click nếu cần
        // deleteBtn.setOnClickListener(...)
        // editBtn.setOnClickListener(...)

        return convertView;
    }
}
