package com.example.freelancer_connect;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.freelancer_connect.Fragment.FragmentAddAccountDecentralization;
import com.example.freelancer_connect.Fragment.FragmentListAccountDecentralization;

public class DecentralizationActivity extends AppCompatActivity {
    Button btnAddAccount,btnListAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_decentralization);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnAddAccount =findViewById(R.id.btn_add_account);
        btnListAccount=findViewById(R.id.btn_list_account);

        btnAddAccount.setSelected(true);
        if (savedInstanceState == null) {
            // load Fragment tạo tài khoản khi mở app
            loadFragment(new FragmentAddAccountDecentralization());
        }
        btnAddAccount.setOnClickListener(v -> {
            btnAddAccount.setSelected(true);
            btnListAccount.setSelected(false);
            loadFragment(new FragmentAddAccountDecentralization());
        });
        btnListAccount.setOnClickListener(v -> {
            btnAddAccount.setSelected(false);
            btnListAccount.setSelected(true);
            loadFragment(new FragmentListAccountDecentralization());
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

}