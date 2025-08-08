package com.example.freelancer_connect;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileCustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_custom);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_profile_custom), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textName = findViewById(R.id.textName);
        TextView textCode = findViewById(R.id.textCode);
        TextView textEmail = findViewById(R.id.textEmail);
        TextView textPhone = findViewById(R.id.textPhone);
        TextView textRole = findViewById(R.id.textRole);
        TextView textAddress = findViewById(R.id.textAddress);
        ImageView imageAvatar = findViewById(R.id.imageAvatar);

        // Nhận dữ liệu từ Intent
        String name = getIntent().getStringExtra("name");
        String code = getIntent().getStringExtra("code");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");
        String role = getIntent().getStringExtra("role");
        String address = getIntent().getStringExtra("address");
        int avatarResId = getIntent().getIntExtra("avatarResId", R.drawable.ic_logo);

        // Gán dữ liệu vào các view
        textName.setText(name);
        textCode.setText("Mã KH: " + code);
        textEmail.setText(email);
        textPhone.setText(phone);
        textRole.setText(role);
        textAddress.setText(address);
        imageAvatar.setImageResource(avatarResId);


    }
}