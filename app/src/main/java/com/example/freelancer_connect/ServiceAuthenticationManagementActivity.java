package com.example.freelancer_connect;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.freelancer_connect.Fragment.ApprovedServiceFragment;
import com.example.freelancer_connect.Fragment.ServiceManagementFragment;
import com.example.freelancer_connect.Fragment.ServiceStatisticsFragment;
import com.example.freelancer_connect.model.User;

import java.util.ArrayList;
import java.util.List;

public class ServiceAuthenticationManagementActivity extends AppCompatActivity {

    private Button btnWaitingApprove, btnApproved, btnStatistical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_service_management);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_service_management), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnWaitingApprove = findViewById(R.id.btn_waiting_approve);
        btnApproved = findViewById(R.id.btn_approved);
        btnStatistical = findViewById(R.id.btn_statistical);

        // Mặc định hiển thị danh sách chờ duyệt
        btnWaitingApprove.setOnClickListener(v -> {
            ServiceManagementFragment fragment = new ServiceManagementFragment();
            fragment.setArguments(createBundle(getWaitingApproveUsers()));
            btnWaitingApprove.setSelected(true);
            btnApproved.setSelected(false);
            btnStatistical.setSelected(false);
            loadFragment(fragment);
        });

        // Hiển thị danh sách đã duyệt
        btnApproved.setOnClickListener(v -> {
            ApprovedServiceFragment fragment = new ApprovedServiceFragment();
            fragment.setArguments(createBundle(getApprovedUsers()));
            btnWaitingApprove.setSelected(false);
            btnApproved.setSelected(true);
            btnStatistical.setSelected(false);
            loadFragment(fragment);
        });

        btnStatistical.setOnClickListener(v -> {
            ServiceStatisticsFragment statisticsFragment = new ServiceStatisticsFragment();
            btnWaitingApprove.setSelected(false);
            btnApproved.setSelected(false);
            btnStatistical.setSelected(true);
            loadFragment(statisticsFragment);
        });

        // Load mặc định fragment đầu tiên khi vào màn hình
        btnWaitingApprove.performClick();
    }

    // Hàm tạo dữ liệu chờ duyệt
    private List<User> getWaitingApproveUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("Nguyễn Văn A", "001", "a@gmail.com", "Freelancer",
                "01234654789", "Hà Nội", R.drawable.ic_avatar_default, null));

        users.add(new User("Nguyễn Văn B", "002", "a@gmail.com", "Freelancer",
                "0123456789", "Hà Nội", R.drawable.ic_avatar_default, null));

        users.add(new User("Nguyễn Văn C", "003", "a@gmail.com", "Freelancer",
                "01234512389", "Hà Nội", R.drawable.ic_avatar_default, null));

        return users;
    }

    // Hàm tạo dữ liệu đã duyệt
    private List<User> getApprovedUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("Nguyễn Văn D", "004", "a@gmail.com", "Freelancer",
                "01234454789", "Hà Nội", R.drawable.ic_avatar_default, null));

        return users;
    }

    // Tạo Bundle truyền danh sách User vào Fragment
    private Bundle createBundle(List<User> users) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("user_list", new ArrayList<>(users));
        return bundle;
    }

    // Load Fragment
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
