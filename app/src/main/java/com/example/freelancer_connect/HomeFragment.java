package com.example.freelancer_connect;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<Service> listService;
    private RecyclerView homeRecyclerView;
    private ServiceAdapter serviceAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        listService = setData();
        homeRecyclerView = rootView.findViewById(R.id.recycler_view_service_item);
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        serviceAdapter = new ServiceAdapter(listService);
        homeRecyclerView.setAdapter(serviceAdapter);
        return rootView;
    }

    private List<Service> setData() {
        List<Service> list = new ArrayList<>();
        list.add(new Service(R.drawable.tutor_service, "Gia sư tiếng Anh", "150.000 đồng", "4.5"));
        list.add(new Service(R.drawable.design_service, "Thiết kế", "200.000 đồng", "5"));
        list.add(new Service(R.drawable.fixing_service, "Sửa chữa", "500.000 đồng", "4"));
        list.add(new Service(R.drawable.tutor_service, "Gia sư tiếng Anh", "150.000 đồng", "4.5"));
        list.add(new Service(R.drawable.design_service, "Thiết kế", "200.000 đồng", "5"));
        list.add(new Service(R.drawable.fixing_service, "Sửa chữa", "500.000 đồng", "4"));
        list.add(new Service(R.drawable.tutor_service, "Gia sư tiếng Anh", "150.000 đồng", "4.5"));
        list.add(new Service(R.drawable.design_service, "Thiết kế", "200.000 đồng", "5"));
        list.add(new Service(R.drawable.fixing_service, "Sửa chữa", "500.000 đồng", "4"));

        return list;
    }
}