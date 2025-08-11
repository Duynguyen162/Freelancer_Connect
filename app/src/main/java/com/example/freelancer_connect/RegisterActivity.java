package com.example.freelancer_connect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    RadioButton raidioCustomer , raidioProvider;
    Button btnBack ,btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_edit_user), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        raidioCustomer = findViewById(R.id.radio_customer);
        raidioProvider = findViewById(R.id.radio_provider);
        btnBack = findViewById(R.id.btn_back);
        btnNext= findViewById(R.id.btn_next);

        raidioCustomer.setOnClickListener(v -> raidioProvider.setChecked(false));
        raidioProvider.setOnClickListener(v -> raidioCustomer.setChecked(false));


        btnNext.setOnClickListener(v -> {
            if(raidioCustomer.isChecked()){
                Intent intent = new Intent(RegisterActivity.this,RegisterCustomerActivity.class);
                startActivity(intent);
            } else {
                    Intent intent = new Intent(RegisterActivity.this,RegisterProviderActivity.class);
                    startActivity(intent);
            }
        });

    }

}