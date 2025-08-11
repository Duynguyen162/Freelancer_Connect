package com.example.freelancer_connect;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.freelancer_connect.Fragment.AddNotificationFragment;
import com.example.freelancer_connect.Fragment.ViewNotificationFragment;

public class NotificationManagementActivity extends AppCompatActivity {
    Button btnSendNotification , btnViewNotification, btnAddNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_notification_management), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //thêm thôn báo
        btnAddNotification = findViewById(R.id.btn_add_notification);
        //danh sách thông báo
        btnViewNotification = findViewById(R.id.btn_view_notification);

        // Mặc định hiển thị fragment đầu tiên
        btnAddNotification.setSelected(true);
        loadFragment(new AddNotificationFragment());

        btnAddNotification.setOnClickListener(v -> {
            loadFragment(new AddNotificationFragment());
            btnAddNotification.setSelected(true);
            btnViewNotification.setSelected(false);
        });

        btnViewNotification.setOnClickListener(v -> {
            loadFragment(new ViewNotificationFragment());
            btnAddNotification.setSelected(false);
            btnViewNotification.setSelected(true);
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}