package com.example.freelancer_connect.user_service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditMyServiceActivity extends AppCompatActivity {
    private EditText edtName, edtDescription, edtPrice, edtTime;
    private Spinner spinnerCategory;
    private Button btnUpdate, btnCancel;
    ArrayList<String> listCategory = new ArrayList<>();
    ArrayAdapter<String> categoryAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_my_service);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtName = findViewById(R.id.edit_my_service_name);
        edtDescription = findViewById(R.id.edit_my_service_description);
        edtPrice = findViewById(R.id.edit_my_service_price);
        edtTime = findViewById(R.id.edit_my_service_time);
        spinnerCategory = findViewById(R.id.edit_my_service_category);
        btnUpdate = findViewById(R.id.edit_my_service_button_update);
        btnCancel = findViewById(R.id.edit_my_service_button_cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateService();
            }
        });

        setUpCategorySpinner();
        setUpServiceInfo();
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
                    Toast.makeText(EditMyServiceActivity.this, "Tải dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setUpServiceInfo() {
        Intent intent = getIntent();
        if (intent == null) return;
        String id = intent.getStringExtra("service_id");
        DocumentReference serviceRef = db.collection("services").document(id);
        serviceRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                if (task.isSuccessful()) {
                    edtName.setText(document.getString("title"));
                    edtDescription.setText(document.getString("description"));
                    edtPrice.setText(document.getString("price"));
                    edtTime.setText(document.getString("operatingTime"));
                    int spinnerIndex = getSpinnerPosition(document.getString("categoryName"));
                    if (spinnerIndex != -1) {
                        spinnerCategory.setSelection(spinnerIndex);
                    } else {
                        spinnerCategory.setSelection(0);
                    }
                } else {
                    Toast.makeText(EditMyServiceActivity.this, "Tải dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int getSpinnerPosition(String value) {
        if (spinnerCategory != null && spinnerCategory.getAdapter() != null) {
            for (int i = 0; i < spinnerCategory.getCount(); i++) {
                if (spinnerCategory.getItemAtPosition(i).toString().equalsIgnoreCase(value));
                return i;
            }
        }
        return -1;
    }

    private void updateService() {
        Intent intent = getIntent();
        if (intent == null) return;
        String id = intent.getStringExtra("service_id");
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("title", edtName.getText().toString());
        updatedData.put("description", edtDescription.getText().toString());
        updatedData.put("price", edtPrice.getText().toString());
        updatedData.put("operatingTime", edtTime.getText().toString());
        updatedData.put("categoryName", spinnerCategory.getSelectedItem().toString());
        db.collection("services").document(id).update(updatedData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(EditMyServiceActivity.this, "Cập nhật dịch vụ thành công", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(EditMyServiceActivity.this, "Cập nhật dịch vụ thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}