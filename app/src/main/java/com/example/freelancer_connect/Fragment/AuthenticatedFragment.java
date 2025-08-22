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
import com.example.freelancer_connect.adapter.ApprovedServiceAdapter;
import com.example.freelancer_connect.model.Service;

import java.util.List;

public class AuthenticatedFragment extends Fragment {
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
            // Lấy danh sách Service từ bundle
            List<Service> serviceList = (List<Service>) bundle.getSerializable("service_list");
            recyclerView.setAdapter(new ApprovedServiceAdapter(getContext(),serviceList));
        }

        return view;
    }
}
