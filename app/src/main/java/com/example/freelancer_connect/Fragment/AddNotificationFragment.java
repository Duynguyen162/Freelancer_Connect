package com.example.freelancer_connect.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.model.Notification;
import com.example.freelancer_connect.model.NotificationRepository;

import java.util.ArrayList;
import java.util.List;

public class AddNotificationFragment extends Fragment {

    public static NotificationRepository NotificationRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_notification, container, false);

        EditText edtTieuDe = view.findViewById(R.id.edt_tieu_de);
        EditText edtNoiDung = view.findViewById(R.id.edt_noi_dung);
        Button btnGui = view.findViewById(R.id.btn_gui_thong_bao);

        btnGui.setOnClickListener(v -> {
            String title = edtTieuDe.getText().toString().trim();
            String content = edtNoiDung.getText().toString().trim();

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getContext(), "Đã thêm thông báo", Toast.LENGTH_SHORT).show();

            // Clear input
            edtTieuDe.setText("");
            edtNoiDung.setText("");
        });

        return view;
    }
}
