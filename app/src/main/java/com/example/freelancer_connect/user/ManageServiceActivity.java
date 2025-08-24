package com.example.freelancer_connect.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.user_service.AddMyServiceActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ManageServiceActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ImageView btnBack;
    private String userEmail;
    private String userPhone;
    private Button btnAdd;
    private SharedViewModel sharedViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_service);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            userPhone = bundle.getString("user_phone");
            userEmail = bundle.getString("user_email");
        }

        btnBack = findViewById(R.id.manage_service_btn_back);
        btnAdd = findViewById(R.id.manage_service_button_add);
        tabLayout = findViewById(R.id.manage_service_tab_layout);
        viewPager = findViewById(R.id.manage_service_view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        sharedViewModel.setData(userEmail);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {
                switch (i) {
                    case 0:
                        tab.setText("Đang hiển thị");
                        break;
                    case 1:
                        tab.setText("Chờ duyệt");
                        break;
                    case 2:
                        tab.setText("Bị từ chối");
                        break;
                }
            }
        }).attach();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageServiceActivity.this, AddMyServiceActivity.class);
                intent.putExtra("user_phone", userPhone);
                intent.putExtra("user_email", userEmail);
                startActivity(intent);
            }
        });
    }
}