package com.example.freelancer_connect.home_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.provider.Provider;
import com.example.freelancer_connect.provider.ProviderAdapter;
import com.example.freelancer_connect.provider.ProviderDetailInfo;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements MenuProvider {

    private List<Provider> listProvider;
    private RecyclerView homeRecyclerView;
    private ProviderAdapter providerAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        homeRecyclerView = rootView.findViewById(R.id.recycler_view_service_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        homeRecyclerView.setLayoutManager(linearLayoutManager);
        providerAdapter = new ProviderAdapter(getContext());
        providerAdapter.setData(getListProvider());
        homeRecyclerView.setAdapter(providerAdapter);
        return rootView;
    }



    private List<Provider> getListProvider() {
        List<Provider> list = new ArrayList();
        list.add(new Provider(R.drawable.img_user, "Gia sư Toán", "Chi phí: 700.000đ", "Đã được thuê: 500", "5"));
        list.add(new Provider(R.drawable.img_user, "Thiết kế nội thất", "Chi phí: 900.000đ", "Đã được thuê: 500", "4.5"));
        list.add(new Provider(R.drawable.img_user, "Sửa chữa điện lạnh", "Chi phí: 1.700.000đ", "Đã được thuê: 500", "4.7"));
        list.add(new Provider(R.drawable.img_user, "Thiết kế website", "Chi phí: 700.000đ", "Đã được thuê: 500", "4.8"));
        list.add(new Provider(R.drawable.img_user, "Trang điểm", "Chi phí: 500.000đ", "Đã được thuê: 500", "5"));

        return list;
    }

    private void onItemClick(Provider provider) {
        Intent intent = new Intent(getContext(), ProviderDetailInfo.class);
        intent.putExtra("EXTRA_DATA_KEY", provider.getPrice()); // Truyền dữ liệu đi kèm nếu cần
        startActivity(intent);
    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}