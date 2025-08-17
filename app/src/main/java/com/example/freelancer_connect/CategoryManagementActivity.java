package com.example.freelancer_connect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.freelancer_connect.adapter.CategoryAdapter;
import com.example.freelancer_connect.model.Category;

import java.util.ArrayList;

public class CategoryManagementActivity extends AppCompatActivity {
    Button addCategoryButton;
    ListView categoryListView;
    CategoryAdapter adapter;
    ArrayList<Category> categoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addCategoryButton = findViewById(R.id.add_category_button);
        categoryListView = findViewById(R.id.category_listview);

        // Tạo mảng dữ liệu
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Gia sư", R.drawable.ic_logo));
        categoryList.add(new Category("Thiết kế", R.drawable.ic_logo));
        categoryList.add(new Category("Làm bánh", R.drawable.ic_logo));
        categoryList.add(new Category("Sửa chữa", R.drawable.ic_logo));
        categoryList.add(new Category("Chụp ảnh", R.drawable.ic_logo));
        categoryList.add(new Category("Làm nail", R.drawable.ic_logo));
        categoryList.add(new Category("Dịch vụ makeup", R.drawable.ic_logo));

        // Gắn adapter
        adapter = new CategoryAdapter(this, categoryList);
        categoryListView.setAdapter(adapter);

        addCategoryButton.setOnClickListener(v -> {
            Intent intent = new Intent(this ,AddServiceActivity.class);
            startActivity(intent);
        });
    }
}