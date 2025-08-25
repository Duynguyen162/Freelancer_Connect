package com.example.freelancer_connect.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.freelancer_connect.R;

public class ChartFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout XML cho Chart
        return inflater.inflate(R.layout.fragment_chart, container, false);
    }
}
