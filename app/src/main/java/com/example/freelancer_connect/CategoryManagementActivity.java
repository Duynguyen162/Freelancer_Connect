package com.example.freelancer_connect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.freelancer_connect.adapter.CategoryAdapter;
import com.example.freelancer_connect.model.Category;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CategoryManagementActivity extends AppCompatActivity {
    Button addCategoryButton;
    ListView categoryListView;
    CategoryAdapter adapter;
    ArrayList<Category> categoryList;
    FirebaseFirestore db;

    private static final int REQUEST_ADD_CATEGORY = 100; // requestCode để nhận kết quả

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

        db = FirebaseFirestore.getInstance();

        // Tạo list trống
        categoryList = new ArrayList<>();
        adapter = new CategoryAdapter(this, categoryList);
        categoryListView.setAdapter(adapter);

        // Load dữ liệu từ Firestore
        loadCategoriesFromFirestore();

        addCategoryButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddServiceActivity.class);
            startActivityForResult(intent, REQUEST_ADD_CATEGORY);
        });
    }

    private void loadCategoriesFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("categories")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    categoryList.clear();
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        Category category = doc.toObject(Category.class);
                        if (category != null) {
                            category.setId(doc.getId()); // ⚡ rất quan trọng
                            categoryList.add(category);
                        }
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Lỗi khi lấy danh mục", e);
                });
    }


    // Nhận kết quả từ AddServiceActivity -> load lại danh mục
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_CATEGORY) {
            loadCategoriesFromFirestore();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Khi quay lại màn hình thì reload dữ liệu
        loadCategoriesFromFirestore();
    }
}
