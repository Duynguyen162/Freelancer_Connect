package com.example.freelancer_connect;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddServiceActivity extends AppCompatActivity {

    private static final String TAG = "AddServiceActivity";

    private ImageView categoryPreview;
    private Button addImageButton, addNewButton;
    private EditText categoryName;

    private Uri selectedImageUri; // chỉ dùng preview, không upload

    private final ActivityResultLauncher<String> pickImage =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    categoryPreview.setImageURI(uri);
                    Log.d(TAG, "Ảnh đã chọn: " + uri);
                } else {
                    Log.d(TAG, "Không chọn ảnh nào");
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_service);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        categoryPreview = findViewById(R.id.category_preview);
        addImageButton = findViewById(R.id.add_image_button);
        addNewButton   = findViewById(R.id.add_new_button);
        categoryName   = findViewById(R.id.category_name);

        // Chọn ảnh
        addImageButton.setOnClickListener(v -> pickImage.launch("image/*"));

        // Lưu Firestore
        addNewButton.setOnClickListener(v -> {
            String name = categoryName.getText().toString().trim();
            if (name.isEmpty()) {
                categoryName.setError("Vui lòng nhập tên danh mục");
                return;
            }

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Map<String, Object> data = new HashMap<>();
            data.put("name", name);

            db.collection("categories")
                    .add(data)
                    .addOnSuccessListener(documentReference -> {
                        Log.d(TAG, "Thêm thành công với ID: " + documentReference.getId());
                        Toast.makeText(this, "Đã thêm danh mục", Toast.LENGTH_SHORT).show();
                        categoryName.setText("");
                        categoryPreview.setImageResource(R.drawable.services); // reset về mặc định
                        selectedImageUri = null;
                    })
                    .addOnFailureListener(e -> {
                        Log.e(TAG, "Lỗi khi thêm", e);
                        Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    });
        });
    }
}
