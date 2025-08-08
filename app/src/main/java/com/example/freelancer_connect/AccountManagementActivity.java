package com.example.freelancer_connect;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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

        // Tạo danh sách mẫu
        userList = new ArrayList<>();
        userList.add(new User("Nguyễn A", "123456", R.drawable.ic_logo));
        userList.add(new User("Trần B", "789012", R.drawable.ic_logo));
        userList.add(new User("Lê C", "345678", R.drawable.ic_logo));
        // Bạn có thể thêm bao nhiêu user tuỳ ý

        adapter = new UserAdapter(userList);
        recyclerViewUsers.setAdapter(adapter);

        recyclerViewUsers.setLayoutManager(new GridLayoutManager(this, 3));

    }
}
