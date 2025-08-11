package com.example.freelancer_connect;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer_connect.adapter.UserAdapter;
import com.example.freelancer_connect.model.User;

import java.util.ArrayList;
import java.util.List;

public class AccountManagementActivity extends AppCompatActivity {
    private RecyclerView recyclerViewUsers;
    private UserAdapter adapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_management);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_account_management), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);

        userList = new ArrayList<>();
        userList.add(new User("Nguyễn A", "123456", "a@gmail.com", "Client", "0901234567", "Hà Nội", R.drawable.ic_logo));
        userList.add(new User("Trần B", "789012", "b@gmail.com", "Freelancer", "0907654321", "Đà Nẵng", R.drawable.ic_logo));
        userList.add(new User("Lê C", "345678", "c@gmail.com", "Freelancer", "0911122233", "Hồ Chí Minh", R.drawable.ic_logo));

        adapter = new UserAdapter(userList);
        recyclerViewUsers.setAdapter(adapter);
        recyclerViewUsers.setLayoutManager(new GridLayoutManager(this, 2));

        adapter.setOnItemClickListener(user -> {
            Intent intent = new Intent(AccountManagementActivity.this, ProfileCustomActivity.class);
            intent.putExtra("name", user.getName());
            intent.putExtra("code", user.getCode());
            intent.putExtra("email", user.getEmail());
            intent.putExtra("role", user.getRole());
            intent.putExtra("phone", user.getPhone());
            intent.putExtra("address", user.getAddress());
            intent.putExtra("avatarResId", user.getAvatarResId());
            startActivity(intent);
        });
    }
}
