package com.example.freelancer_connect.home_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.customer_model.Service;

public class ServiceDetailActivity extends AppCompatActivity {
    private ImageView btnBack;
    private Button btnGetContact;
    private TextView serviceName, serviceDescription, serviceCategory, serviceArea, servicePrice, serviceTime, serviceNumContact,
    serviceAverageStar, serviceReviewCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_service_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_service_detail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        serviceName = findViewById(R.id.service_name_text);
        serviceDescription = findViewById(R.id.service_description_text);
        serviceCategory = findViewById(R.id.service_category_text);
        serviceArea = findViewById(R.id.service_area_text);
        servicePrice = findViewById(R.id.service_price_text);
        serviceTime = findViewById(R.id.service_time_text);
        serviceAverageStar = findViewById(R.id.service_average_rate);
        serviceNumContact = findViewById(R.id.service_num_contact);
        serviceReviewCount = findViewById(R.id.service_review_count);

        btnBack = findViewById(R.id.service_detail_button_back);
        btnGetContact = findViewById(R.id.button_get_service_contact);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        Service service = (Service) bundle.get("object_service");
        serviceName.setText(service.getTitle());
        serviceDescription.setText(service.getDescription());
        serviceCategory.setText(service.getCategoryName());
        serviceArea.setText(service.getServiceArea());
        servicePrice.setText(service.getPrice());
        serviceTime.setText(service.getOperatingTime());
        serviceAverageStar.setText(service.getAverageRating());
        serviceNumContact.setText("Đã liên lạc: " + service.getNumContact());
        serviceReviewCount.setText(service.getReviewCount() + " người đánh giá");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnGetContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = createInfoDialog();
                alertDialog.show();
            }
        });
    }

    AlertDialog createInfoDialog() {
        Bundle bundle = getIntent().getExtras();
        Service service = new Service();
        if (bundle != null) {
            service = (Service) bundle.get("object_service");
            service.setPhone(service.getPhone());
            service.setEmail(service.getEmail());
        }

        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.service_contact_info_dialog, null);

        TextView txtPhone = dialogLayout.findViewById(R.id.service_contact_info_phone);
        txtPhone.setText(service.getPhone());

        TextView txtEmail = dialogLayout.findViewById(R.id.service_contact_info_email);
        txtEmail.setText(service.getEmail());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogLayout);

        return builder.create();
    }

}