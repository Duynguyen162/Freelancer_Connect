package com.example.freelancer_connect.provider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.freelancer_connect.CustomerActivity;
import com.example.freelancer_connect.R;

public class ProviderDetailInfo extends AppCompatActivity {
    private ImageView image;
    private TextView service;
    private TextView description;
    private TextView category;
    private TextView area;
    private TextView price;
    private TextView time;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_provider_detail_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        image = findViewById(R.id.detail_image);
        service = findViewById(R.id.detail_service_text);
        description = findViewById(R.id.detail_description_text);
        category = findViewById(R.id.detail_category_text);
        area = findViewById(R.id.detail_area_text);
        price = findViewById(R.id.detail_price_text);
        time = findViewById(R.id.detail_time_text);
        btnBack = findViewById(R.id.user_detail_btn_back);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Provider provider = (Provider) bundle.get("object_provider");
        image.setImageResource(provider.getImage());
        service.setText(provider.getTilte());
        price.setText(provider.getPrice());
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProviderDetailInfo.this, CustomerActivity.class);
                startActivity(intent);
            }
        });
    }
}