package com.example.freelancer_connect.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.freelancer_connect.R;

public class FragmentAddAccountDecentralization  extends Fragment {

        private EditText edtUsername, edtPassword;
        private CheckBox chkAccount, chkNotification, chkAuthenticate, chkStatistic, chkService;
        private Button btnSave;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_add_account_decentralization, container, false);

            edtUsername = view.findViewById(R.id.edt_username);
            edtPassword = view.findViewById(R.id.edt_password);
            chkAccount = view.findViewById(R.id.chk_account);
            chkNotification = view.findViewById(R.id.chk_notification);
            chkAuthenticate = view.findViewById(R.id.chk_authenticate);
            chkStatistic = view.findViewById(R.id.chk_statistic);
            chkService = view.findViewById(R.id.chk_service);
            btnSave = view.findViewById(R.id.btn_create_account);

            btnSave.setOnClickListener(v -> {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                boolean acc = chkAccount.isChecked();
                boolean noti = chkNotification.isChecked();
                boolean auth = chkAuthenticate.isChecked();
                boolean stat = chkStatistic.isChecked();
                boolean serv = chkService.isChecked();

            });

            return view;
        }

}
