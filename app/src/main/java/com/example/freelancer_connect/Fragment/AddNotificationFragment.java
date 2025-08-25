package com.example.freelancer_connect.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.model.Notification;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddNotificationFragment extends Fragment {

    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_notification, container, false);

        EditText edtTieuDe = view.findViewById(R.id.edt_tieu_de);
        EditText edtNoiDung = view.findViewById(R.id.edt_noi_dung);
        Button btnGui = view.findViewById(R.id.btn_gui_thong_bao);

        db = FirebaseFirestore.getInstance();

        btnGui.setOnClickListener(v -> {
            String title = edtTieuDe.getText().toString().trim();
            String content = edtNoiDung.getText().toString().trim();

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tạo map để lưu lên Firestore
            Map<String, Object> notificationMap = new HashMap<>();
            notificationMap.put("title", title);
            notificationMap.put("content", content);
            notificationMap.put("timestamp", Timestamp.now());

            // Thêm vào collection "notifications"
            db.collection("notifications")
                    .add(notificationMap)
                    .addOnSuccessListener(documentReference -> {
                        // Sau khi thêm xong, cập nhật documentId
                        documentReference.update("documentId", documentReference.getId());

                        Toast.makeText(getContext(), "Đã thêm thông báo", Toast.LENGTH_SHORT).show();

                        // Clear input
                        edtTieuDe.setText("");
                        edtNoiDung.setText("");
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        return view;
    }
}
