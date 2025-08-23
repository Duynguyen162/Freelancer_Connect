package com.example.freelancer_connect;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.freelancer_connect.Fragment.ServiceStatisticsFragment;
import com.example.freelancer_connect.Fragment.ApprovedServiceFragment;
import com.example.freelancer_connect.Fragment.ServiceManagementFragment;
import com.example.freelancer_connect.model.Service;
import com.example.freelancer_connect.utils.ServiceStatus;   // 👈 thêm import

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ServiceAuthenticationManagementActivity extends AppCompatActivity {

    private Button btnWaitingApprove, btnApproved, btnStatistical;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_authenticate_registration);

        btnWaitingApprove = findViewById(R.id.btn_waiting_approve);
        btnApproved = findViewById(R.id.btn_approved);
        btnStatistical = findViewById(R.id.btn_statistical);

        db = FirebaseFirestore.getInstance();

        // --- Button "Đang chờ duyệt"
        btnWaitingApprove.setOnClickListener(v -> {
            loadServices(ServiceStatus.PENDING, fragment -> {
                btnWaitingApprove.setSelected(true);
                btnApproved.setSelected(false);
                btnStatistical.setSelected(false);
                loadFragment(fragment);
            });
        });

        // --- Button "Đã được duyệt"
        btnApproved.setOnClickListener(v -> {
            loadServices(ServiceStatus.APPROVED, fragment -> {
                btnWaitingApprove.setSelected(false);
                btnApproved.setSelected(true);
                btnStatistical.setSelected(false);
                loadFragment(fragment);
            });
        });

        // --- Button "Thống kê"
        btnStatistical.setOnClickListener(v -> {
            ServiceStatisticsFragment statisticsFragment = new ServiceStatisticsFragment();
            btnWaitingApprove.setSelected(false);
            btnApproved.setSelected(false);
            btnStatistical.setSelected(true);
            loadFragment(statisticsFragment);
        });

        // --- Load mặc định
        btnWaitingApprove.performClick();
    }

    private interface FragmentCallback {
        void onCallback(Fragment fragment);
    }

    private void loadServices(String status, FragmentCallback callback) {
        db.collection("services")
                .whereEqualTo("status", status)
                .get()
                .addOnSuccessListener(query -> {
                    List<Service> services = new ArrayList<>();
                    for (QueryDocumentSnapshot doc : query) {
                        Service service = doc.toObject(Service.class);
                        services.add(service);
                    }

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("service_list", new ArrayList<>(services));

                    Fragment fragment;
                    if (ServiceStatus.APPROVED.equals(status)) {
                        fragment = new ApprovedServiceFragment();
                    } else {
                        fragment = new ServiceManagementFragment();
                    }
                    fragment.setArguments(bundle);

                    callback.onCallback(fragment);
                });
    }

    // Load Fragment
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
