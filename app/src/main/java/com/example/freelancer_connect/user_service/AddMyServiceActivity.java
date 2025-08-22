package com.example.freelancer_connect.user_service;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.freelancer_connect.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddMyServiceActivity extends AppCompatActivity {
   private Spinner spinnerCategory;
   EditText edtTitle, edtDescription, edtPrice, edtTime;
    private Button btnCancel, btnAdd;
    ArrayList<String> listCategory = new ArrayList<>();
    ArrayAdapter<String> categoryAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_my_service);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtTitle = findViewById(R.id.add_my_service_name);
        edtDescription = findViewById(R.id.add_my_service_description);
        edtPrice = findViewById(R.id.add_my_service_price);
        edtTime = findViewById(R.id.add_my_service_time);

        btnCancel = findViewById(R.id.add_my_service_button_cancel);
        btnAdd = findViewById(R.id.add_my_service_button_add);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewService();
            }
        });

        spinnerCategory = findViewById(R.id.add_my_service_category);
        setUpCategorySpinner();
    }

    private void setUpCategorySpinner() {
        categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listCategory);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerCategory.setAdapter(categoryAdapter);

        db.collection("categories").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Lấy field "name" từ mỗi document
                        String categoryName = document.getString("name");
                        if (categoryName != null) {
                            listCategory.add(categoryName);
                        }
                    }
                    categoryAdapter.notifyDataSetChanged();
                } else {

                }
            }
        });
    }

    private void addNewService() {
        String title, description, category, price, time, status;
        title = edtTitle.getText().toString().trim();
        description = edtDescription.getText().toString().trim();
        category = spinnerCategory.getSelectedItem().toString().trim();
        price = edtPrice.getText().toString().trim();
        time = edtTime.getText().toString().trim();
        status = "Đang chờ duyệt";

        if (title.isEmpty() || description.isEmpty() || category.isEmpty() || price.isEmpty() || time.isEmpty()) {
            Toast.makeText(AddMyServiceActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> service = new HashMap<>();
        service.put("title", title);
        service.put("description", description);
        service.put("categoryName", category);
        service.put("price", price);
        service.put("operatingTime", time);
        service.put("status", status);

        db.collection("services").add(service).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(AddMyServiceActivity.this, "Đã thêm dịch vụ thành công!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddMyServiceActivity.this, "Thêm dịch vụ thất bại!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}