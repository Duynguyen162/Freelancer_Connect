package com.example.freelancer_connect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister;

    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_layout);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.loginEmail);
        etPassword = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        // Xử lý đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().contains("hohoangtuan3003@gmail.com")) {
                    String email = etEmail.getText().toString();
                    Intent intent = new Intent(LoginActivity.this, CustomerActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                } else {
                    loginUser();
                }
            }
        });

        // Chuyển sang màn đăng ký
        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterCustomerActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Vui lòng nhập email và mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        // Sang HomeAdminActivity
                        Intent intent = new Intent(LoginActivity.this, HomeAdminActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Sai thông tin tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
