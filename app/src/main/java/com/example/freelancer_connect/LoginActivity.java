package com.example.freelancer_connect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    Button btn_login;
    TextView tv_register;
    TextView tv_forgot_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_login = findViewById(R.id.btnLogin);
        tv_register = findViewById(R.id.tvRegister);
        tv_forgot_password=findViewById(R.id.tvForgotPassword);

        tv_forgot_password.setOnClickListener( v -> {
            Intent intent = new Intent(this ,CustomerActivity.class);
            startActivity(intent);
        });
        tv_register.setOnClickListener( v -> {
            Intent intent = new Intent(this ,RegisterActivity.class);
            startActivity(intent);
        });

        btn_login.setOnClickListener(v -> {
            Intent intent = new Intent(this ,HomeAdminActivity.class);
            startActivity(intent);
        });

    }
}