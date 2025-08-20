package com.example.freelancer_connect;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class AddServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_service);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView calendarIcon = findViewById(R.id.calendar_icon);
        EditText createDate = findViewById(R.id.create_date);
        calendarIcon.setOnClickListener(v -> {
            // Lấy ngày hiện tại làm mặc định
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    v.getContext(),
                    (view, year1, month1, dayOfMonth) -> {
                        // format ngày: dd/MM/yyyy
                        String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                        createDate.setText(selectedDate);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });
    }
}