package com.example.freelancer_connect;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.freelancer_connect.Fragment.AddNotificationFragment;
import com.example.freelancer_connect.Fragment.ChartFragment;
import com.example.freelancer_connect.Fragment.GeneralStatisticsFragment;
import com.example.freelancer_connect.Fragment.StatisticsByGroupFragment;

public class StatisticalManagementActivity extends AppCompatActivity {
    Button buttonGeneralStatistics,buttonStatisticsByGroup,buttonChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_statistical_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        buttonGeneralStatistics= findViewById(R.id.button_general_statistics);
        buttonStatisticsByGroup = findViewById(R.id.button_statistics_by_group);
        buttonChart = findViewById(R.id.button_chart);


        // Mặc định hiển thị fragment đầu tiên
        buttonGeneralStatistics.setSelected(true);
        loadFragment(new GeneralStatisticsFragment());

// Các nút bấm chuyển tab:
        buttonGeneralStatistics.setOnClickListener(v -> {
            loadFragment(new GeneralStatisticsFragment());
            buttonGeneralStatistics.setSelected(true);
            buttonStatisticsByGroup.setSelected(false);
            buttonChart.setSelected(false);

        });
        buttonStatisticsByGroup.setOnClickListener(v -> {
            loadFragment(new StatisticsByGroupFragment());
            buttonGeneralStatistics.setSelected(false);
            buttonStatisticsByGroup.setSelected(true);
            buttonChart.setSelected(false);
        });
        buttonChart.setOnClickListener(v -> {
            loadFragment(new ChartFragment());
            buttonGeneralStatistics.setSelected(false);
            buttonStatisticsByGroup.setSelected(false);
            buttonChart.setSelected(true);
        });

    }
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_Container, fragment)
                .commit();
    }
}