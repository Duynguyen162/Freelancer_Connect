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
import com.example.freelancer_connect.adapter.ServiceAdapter;
import com.example.freelancer_connect.model.Service;

import java.util.ArrayList;
import java.util.List;

public class ServiceManagementFragment extends Fragment {

    private RecyclerView recyclerView;
    private ServiceAdapter adapter;
    private List<Service> serviceList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_waiting_approve, container, false);

        recyclerView = view.findViewById(R.id.recycler_service_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Lấy dữ liệu từ arguments
        if (getArguments() != null) {
            serviceList = (List<Service>) getArguments().getSerializable("service_list");
        }

        adapter = new ServiceAdapter(serviceList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
