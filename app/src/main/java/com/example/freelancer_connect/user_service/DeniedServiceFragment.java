package com.example.freelancer_connect.user_service;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.provider.Provider;

import java.util.ArrayList;
import java.util.List;

public class DeniedServiceFragment extends Fragment {
    RecyclerView recyclerView;

    public DeniedServiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_denied_service, container, false);
        recyclerView = rootView.findViewById(R.id.denied_service_recycler_view);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserServiceAdapter adapter = new UserServiceAdapter(initializeData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private List<Provider> initializeData() {
        List<Provider> list = new ArrayList<>();

        list.add(new Provider(R.drawable.img_user, "Gia sư Toán", "700.000đ", "Đã được thuê: 500", "5"));

        return list;
    }
}