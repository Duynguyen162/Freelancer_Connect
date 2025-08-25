package com.example.freelancer_connect.notify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.customer_model.Notification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NotifyFragment extends Fragment {
    private RecyclerView notifyRecyclerView;
    private NotificationAdapter notificationAdapter;
    private List<Notification> notificationList;
    public NotifyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notify, container, false);
        notifyRecyclerView = view.findViewById(R.id.notify_recycler_view);
        notifyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationList = new ArrayList<>();
        notificationAdapter = new NotificationAdapter(notificationList);
        notifyRecyclerView.setAdapter(notificationAdapter);

        fetchNotification();

        return view;
    }

    private void fetchNotification() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("notifications").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Notification notification = document.toObject(Notification.class);
                        notificationList.add(notification);
                    }
                    notificationAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Tải dữ liệu thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}