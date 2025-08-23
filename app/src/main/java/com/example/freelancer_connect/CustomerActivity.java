package com.example.freelancer_connect;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.freelancer_connect.customer_model.User;
import com.example.freelancer_connect.databinding.ActivityCustomerBinding;
import com.example.freelancer_connect.home_page.HomeFragment;
import com.example.freelancer_connect.notify.NotifyFragment;
import com.example.freelancer_connect.user.UserFragment;

public class CustomerActivity extends AppCompatActivity {
    ActivityCustomerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("userEmail");

        binding = ActivityCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        changeFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            if (id == R.id.action_home) {
                changeFragment(new HomeFragment());
            } else if (id == R.id.action_notify) {
                changeFragment(new NotifyFragment());
            } else if (id == R.id.action_user) {
                UserFragment userFragment = new UserFragment();
                Bundle bundle = new Bundle();
                bundle.putString("user_email", userEmail);
                userFragment.setArguments(bundle);
                changeFragment(userFragment);
            }
            return true;
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_customer), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}