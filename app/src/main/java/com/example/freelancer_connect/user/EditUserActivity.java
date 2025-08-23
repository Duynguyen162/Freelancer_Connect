package com.example.freelancer_connect.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.customer_model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class EditUserActivity extends AppCompatActivity {
    private EditText edtName, edtCCCD , edtPhone, edtDOB;
    private RadioButton rbMale, rbFemale;
    private Button btnUpdate, btnCancel;
    private String emailToEdit;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_service_detail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            emailToEdit = bundle.getString("user_email");
        }

        edtName = findViewById(R.id.edit_user_edt_name);
        edtCCCD = findViewById(R.id.edit_user_edt_cccd);
        edtPhone = findViewById(R.id.edit_user_edt_phone);
        edtDOB = findViewById(R.id.edit_user_edt_dob);
        rbMale = findViewById(R.id.edit_user_rb_male);
        rbFemale = findViewById(R.id.edit_user_rb_female);

        db = FirebaseFirestore.getInstance();


        btnCancel = findViewById(R.id.edit_user_button_cancel);
        btnUpdate = findViewById(R.id.edit_user_button_update);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
                finish();
            }
        });

        fetchUserByEmail();
    }

    private void fetchUserByEmail() {
        db.collection("users").whereEqualTo("email", emailToEdit).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    User user = new User();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        user = document.toObject(User.class);
                    }
                    edtName.setText(user.getName());
                    edtCCCD.setText(user.getCccd());
                    edtPhone.setText(user.getPhone());
                    edtDOB.setText(user.getBirthday());
                    if (user.getGender().equalsIgnoreCase("Nam")) {
                        rbMale.setChecked(true);
                    } else {
                        rbFemale.setChecked(true);
                    }
                } else {
                    Toast.makeText(EditUserActivity.this, "Không tìm thấy người dùng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateUser() {
        String emailToFind = emailToEdit;
        if (emailToFind.isEmpty()) {
            return;
        }
        String name = edtName.getText().toString();
        String cccd = edtCCCD.getText().toString();
        String phone = edtPhone.getText().toString();
        String birthDay = edtDOB.getText().toString();

        if (name.isEmpty() || cccd.isEmpty() || phone.isEmpty() || birthDay.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("users").whereEqualTo("email", emailToFind).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                db.collection("users").whereEqualTo("email", emailToFind).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult() != null && !task.getResult().isEmpty()) {
                                QueryDocumentSnapshot document = (QueryDocumentSnapshot) task.getResult().getDocuments().get(0);

                                // Lấy ID của document
                                String documentId = document.getId();

                                // Bước 3: Cập nhật thông tin
                                DocumentReference docRef = db.collection("users").document(documentId);

                                Map<String, Object> updates = new HashMap<>();
                                updates.put("name", name);
                                updates.put("cccd", cccd);
                                updates.put("phone", phone);
                                updates.put("birthday", birthDay);

                                docRef.update(updates)
                                        .addOnSuccessListener(aVoid -> {
                                            Toast.makeText(EditUserActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();

                                        })
                                        .addOnFailureListener(e -> {
                                            Toast.makeText(EditUserActivity.this, "Lỗi cập nhật: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        });
                            } else  {
                                Toast.makeText(EditUserActivity.this, "Không tìm thấy người dùng với email này." + emailToFind, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(EditUserActivity.this, "Lỗi truy vấn: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}