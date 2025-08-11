package com.example.freelancer_connect;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.freelancer_connect.Fragment.ServiceManagementFragment;
import com.example.freelancer_connect.model.User;

import java.util.ArrayList;
import java.util.List;

public class ServiceManagementActivity extends AppCompatActivity {

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
        List<User> users = new ArrayList<>();
        users.add(new User("Nguyễn Văn A", "001", "a@gmail.com", "150.000 đồng/h", "0123456789", "Hà Nội", R.drawable.ic_avatar_default));
        users.add(new User("Trần Văn B", "002", "b@gmail.com", "200.000 đồng/h", "0987654321", "HCM", R.drawable.ic_avatar_default));
        users.add(new User("Lê Văn C", "003", "c@gmail.com", "120.000 đồng/h", "0111222333", "Đà Nẵng", R.drawable.ic_avatar_default));

        ServiceManagementFragment fragment = new ServiceManagementFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user_list", new ArrayList<>(users));
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}