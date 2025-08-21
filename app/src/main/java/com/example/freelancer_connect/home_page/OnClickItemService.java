package com.example.freelancer_connect.home_page;

import com.example.freelancer_connect.customer_model.Service;
import com.google.firebase.firestore.DocumentSnapshot;

public interface OnClickItemService {
    void onServiceClickListener(Service service);
}
