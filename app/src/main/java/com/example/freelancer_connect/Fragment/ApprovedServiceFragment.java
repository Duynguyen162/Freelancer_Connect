package com.example.freelancer_connect.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.adapter.AuthenticatedServiceAdapter;
import com.example.freelancer_connect.model.User;

import java.util.List;

public class ApprovedServiceFragment extends Fragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_waiting_approve, container, false);

        recyclerView = view.findViewById(R.id.recycler_service_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        if (bundle != null) {
            List<User> userList = (List<User>) bundle.getSerializable("user_list");
            recyclerView.setAdapter(new AuthenticatedServiceAdapter(userList));
        }

        return view;
    }
}
