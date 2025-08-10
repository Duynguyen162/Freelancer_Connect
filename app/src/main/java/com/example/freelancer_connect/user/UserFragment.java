package com.example.freelancer_connect.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.freelancer_connect.MainActivity;
import com.example.freelancer_connect.R;

public class UserFragment extends Fragment {
    private TextView txtUserName;
    private EditText edtLoginName, edtID, edtEmail, edtPhone, edtDOB;
    private RadioGroup rdGender;
    private RadioButton rbMale, rbFemale;
    private LinearLayout btnLogOut;


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
        edtLoginName = rootView.findViewById(R.id.edit_text_user_login_name);
        edtLoginName.setEnabled(false);
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
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User user = new User(R.drawable.img_user, "Hồ Hoàng Tuấn", "tuan2004", "2251172545", "hotuan@gmail.com", "091683233333", "30/03/2004", "Nam");
        txtUserName.setText(user.getUserName());
        edtLoginName.setText(user.getUserLoginName());
        edtID.setText(user.getUserID());
        edtEmail.setText(user.getUserEmail());
        edtPhone.setText(user.getUserPhone());
        edtDOB.setText(user.getUserDOB());
        if (user.getUserGender().equals("Nam")) {
            rdGender.check(R.id.user_radio_button_male);
            rbFemale.setEnabled(false);

        } else {
            rdGender.check(R.id.user_radio_button_female);
            rbMale.setEnabled(false);
        }
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}