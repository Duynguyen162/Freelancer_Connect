package com.example.freelancer_connect;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer_connect.adapter.UserAdapter;
import com.example.freelancer_connect.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AccountManagementActivity extends AppCompatActivity {
    private RecyclerView recyclerViewUsers;
    private UserAdapter adapter;
    private List<User> userList, fullUserList;
    private DatabaseReference databaseReference;

    private EditText edtSearch;
    private ImageView btnBack, btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);

        // Nếu muốn tránh nội dung đè vào thanh điều hướng dưới, chỉ thêm bottom inset cho RecyclerView
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
        ViewCompat.setOnApplyWindowInsetsListener(recyclerViewUsers, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), bars.bottom);
            return insets;
        });

        recyclerViewUsers.setLayoutManager(new GridLayoutManager(this, 3));

        edtSearch = findViewById(R.id.edtSearch);
        btnBack = findViewById(R.id.btnBack);
        btnSearch = findViewById(R.id.btnSearch);

        // Thêm TextWatcher để search realtime khi gõ
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String query = s.toString().trim();
                searchUser(query);
            }
        });

        userList = new ArrayList<>();
        fullUserList = new ArrayList<>();
        adapter = new UserAdapter(userList);
        recyclerViewUsers.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Load dữ liệu Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                userList.clear();
                fullUserList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    User user = data.getValue(User.class);
                    if (user != null) {
                        // fallback ảnh mặc định nếu thiếu
                        if (user.getAvatarResId() == 0) {
                            user.setAvatarResId(R.drawable.ic_avatar_default);
                        }
                        userList.add(user);
                        fullUserList.add(user);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("Firebase", "Lỗi đọc dữ liệu", error.toException());
            }
        });

        // Nút Back: về HomeActivity (đổi thành MainActivity nếu trang chủ của bạn là MainActivity)
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(AccountManagementActivity.this, HomeAdminActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Nút search (vẫn giữ nếu muốn search thủ công)
        btnSearch.setOnClickListener(v -> {
            String query = edtSearch.getText().toString().trim();
            searchUser(query);
        });

        // Click item → Profile
        adapter.setOnItemClickListener(user -> {
            Intent intent = new Intent(AccountManagementActivity.this, ProfileCustomActivity.class);
            intent.putExtra("name", user.getName());
            intent.putExtra("code", user.getCode());
            intent.putExtra("email", user.getEmail());
            intent.putExtra("role", user.getRole());
            intent.putExtra("phone", user.getPhone());
            intent.putExtra("address", user.getAddress());
            intent.putExtra("avatarResId", user.getAvatarResId());
            intent.putExtra("avatarUrl", user.getAvatarUrl());
            startActivity(intent);
        });
    }

    private void searchUser(String query) {
        if (TextUtils.isEmpty(query)) {
            adapter.updateList(fullUserList);
            return;
        }
        List<User> filtered = new ArrayList<>();
        for (User u : fullUserList) {
            String q = query.toLowerCase();
            if ((u.getName() != null && u.getName().toLowerCase().contains(q)) ||
                    (u.getEmail() != null && u.getEmail().toLowerCase().contains(q)) ||
                    (u.getCode() != null && u.getCode().toLowerCase().contains(q))) {
                filtered.add(u);
            }
        }
        adapter.updateList(filtered);
    }
}
