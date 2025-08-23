package com.example.freelancer_connect.user_service;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.customer_model.Service;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DisplayServiceFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyServiceAdapter myServiceAdapter;
    private ArrayList<Service> serviceArrayList;
    private Button btnAdd;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public DisplayServiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_display_service, container, false);
        recyclerView = rootView.findViewById(R.id.display_service_recycler_view);
        serviceArrayList = new ArrayList<>();
        myServiceAdapter = new MyServiceAdapter(serviceArrayList, new OnServiceDeleteListener() {
            @Override
            public void onServiceDelete(String id, int position) {
                deleteService(id, position);
            }
        }, new OnServiceUpdateListener() {
            @Override
            public void onServiceUpdate(String id, int position) {
                updateService(id, position);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myServiceAdapter);
        btnAdd = rootView.findViewById(R.id.display_service_button_add);

        fetchDataFromFireStore();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddMyServiceActivity.class);
                startActivity(intent);
            }
        });
    }

    public void fetchDataFromFireStore() {
        CollectionReference serviceRef = db.collection("services");
        Query query = serviceRef.whereEqualTo("status", "Đã được duyệt");

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Service service = document.toObject(Service.class);
                        service.setId(document.getId());
                        serviceArrayList.add(service);
                    }
                    myServiceAdapter.notifyDataSetChanged();
                } else {
                    Log.w("Firestore", "Lỗi khi lấy dữ liệu: ", task.getException());
                }
            }
        });
    }

    public void deleteService(String id, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customLayout = inflater.inflate(R.layout.layout_delete_confirm_dialog, null);
        builder.setView(customLayout);
        final AlertDialog dialog = builder.create();
        Button btnConfirm =  customLayout.findViewById(R.id.button_confirm_delete);
        Button btnCancel =  customLayout.findViewById(R.id.button_cancel_delete);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                db.collection("services").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        serviceArrayList.remove(position);
                        myServiceAdapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Lỗi khi xóa: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void updateService(String id, int positon) {
        Intent intent = new Intent(requireContext(), EditMyServiceActivity.class);
        intent.putExtra("service_id", id);
        intent.putExtra("service_position", positon);
        startActivity(intent);
    }
}