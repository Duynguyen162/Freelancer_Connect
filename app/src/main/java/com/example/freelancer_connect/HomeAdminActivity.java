package com.example.freelancer_connect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeAdminActivity extends AppCompatActivity {
    LinearLayout accountManagement , notificationNanagement ,
            authenticateRegistration,
    serviceManagement,decentralization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_service_detail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        accountManagement = findViewById(R.id.account_management);
        notificationNanagement = findViewById(R.id.notification_management);
        authenticateRegistration = findViewById(R.id.authenticate_registration);
        serviceManagement=findViewById(R.id.service_directory);

        //xử lí sk click

        accountManagement.setOnClickListener(v -> {
            Intent intent = new Intent(this ,AccountManagementActivity.class);
            startActivity(intent);
        });
        notificationNanagement.setOnClickListener(v -> {
            Intent intent = new Intent(this ,NotificationManagementActivity.class);
            startActivity(intent);
        });

        authenticateRegistration.setOnClickListener(v -> {
            Intent intent = new Intent(this , ServiceAuthenticationManagementActivity.class);
            startActivity(intent);
        });
        serviceManagement.setOnClickListener(v -> {
            Intent intent = new Intent(this , CategoryManagementActivity.class);
            startActivity(intent);
        });


    }
}