package com.example.freelancer_connect.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.adapter.UserPermissionAdapter;
import com.example.freelancer_connect.model.UserPermission;

import java.util.ArrayList;
import java.util.List;

public class FragmentListAccountDecentralization extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_account_decentralization, container, false);

        ListView listView = view.findViewById(R.id.list_view_users);

        List<UserPermission> users = new ArrayList<>();
        users.add(new UserPermission("Nguyễn Duy Hiếu", "Quản lí 1", true, false, true, false, false));
        users.add(new UserPermission("Nguyễn Hoàng", "Quản lí 2", false, true, false, true, false));

        UserPermissionAdapter adapter = new UserPermissionAdapter(requireContext(), users);
        listView.setAdapter(adapter);

        return view;
    }
}
