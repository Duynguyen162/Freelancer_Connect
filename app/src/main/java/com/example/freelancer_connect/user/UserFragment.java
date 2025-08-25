package com.example.freelancer_connect.user;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.freelancer_connect.LoginActivity;
import com.example.freelancer_connect.R;

import com.example.freelancer_connect.customer_model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.Calendar;


public class UserFragment extends Fragment {
    private TextView txtUserName;
    private EditText edtID, edtEmail, edtPhone, edtDOB;
    private RadioGroup rdGender;
    private RadioButton rbMale, rbFemale;
    private LinearLayout btnLogOut, btnEdit, btnManageService;
    final Calendar myCalendar = Calendar.getInstance();
    private FirebaseFirestore db;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);


        txtUserName = rootView.findViewById(R.id.text_view_user_name);
        txtUserName.setEnabled(false);
        edtID = rootView.findViewById(R.id.edit_text_user_id);
        edtID.setEnabled(false);
        edtEmail = rootView.findViewById(R.id.edit_text_user_email);
        edtEmail.setEnabled(false);
        edtPhone = rootView.findViewById(R.id.edit_text_user_phone);
        edtPhone.setEnabled(false);
        edtDOB = rootView.findViewById(R.id.edit_text_user_dob);
        edtDOB.setEnabled(false);
        rdGender = rootView.findViewById(R.id.radio_group_user_gender);
        rbMale = rootView.findViewById(R.id.user_radio_button_male);
        rbFemale = rootView.findViewById(R.id.user_radio_button_female);
        btnLogOut = rootView.findViewById(R.id.user_button_logout);
        btnEdit = rootView.findViewById(R.id.user_button_edit_profile);
        btnManageService = rootView.findViewById(R.id.user_button_manage_service);

        db = FirebaseFirestore.getInstance();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            String userEmail = getArguments().getString("user_email");
            fetchUserByEmail(userEmail);
        }

        edtID.setEnabled(false);
        edtEmail.setEnabled(false);
        edtPhone.setEnabled(false);
        edtDOB.setEnabled(false);
        rbMale.setEnabled(false);
        rbFemale.setEnabled(false);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = edtEmail.getText().toString();
                Intent intent = new Intent(getActivity(), EditUserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user_email", userEmail);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnManageService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserEmailToManageServiceActivity();
            }
        });
    }

    private void fetchUserByEmail(String email) {
        CollectionReference usersRef = db.collection("users");
        usersRef.whereEqualTo("email", email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot result = task.getResult();
                    if (result != null && !result.isEmpty()) {
                        QueryDocumentSnapshot document = (QueryDocumentSnapshot) result.getDocuments().get(0);

                        User user = document.toObject(User.class);

                        txtUserName.setText(user.getName());
                        edtID.setText(user.getCccd());
                        edtEmail.setText(user.getEmail());
                        edtPhone.setText(user.getPhone());
                        edtDOB.setText(user.getBirthday());
                        if (user.getGender().equals("Nam")) {
                            rbMale.setChecked(true);
                        } else {
                            rbFemale.setChecked(true);
                        }
                    }
                }
            }
        });
    }
    
    public void sendUserEmailToManageServiceActivity() {
         Intent intent = new Intent(getActivity(), ManageServiceActivity.class);
         Bundle bundle = new Bundle();
         bundle.putString("user_phone", edtPhone.getText().toString());
         bundle.putString("user_email", edtEmail.getText().toString());
         intent.putExtras(bundle);
         startActivity(intent);
    }
}