package com.example.freelancer_connect.home_page;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.provider.IClickItemProviderListener;
import com.example.freelancer_connect.provider.Provider;
import com.example.freelancer_connect.provider.ProviderAdapter;
import com.example.freelancer_connect.provider.ProviderDetailInfo;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements MenuProvider {
    private RecyclerView homeRecyclerView;
    private ProviderAdapter providerAdapter;
    private SearchView searchView;
    private Spinner categorySpinner;



    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        homeRecyclerView = rootView.findViewById(R.id.recycler_view_provider_item);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        homeRecyclerView.setLayoutManager(gridLayoutManager);
        providerAdapter = new ProviderAdapter(getListProvider(), new IClickItemProviderListener() {
            @Override
            public void onClickItemProvider(Provider provider) {
                onClickGoToProviderDetail(provider);
            }
        });
        homeRecyclerView.setAdapter(providerAdapter);
        categorySpinner = rootView.findViewById(R.id.spinner_provider_category);
        String categories[]  = {"Tất cả", "Gia sư", "Sửa chữa"};
        categorySpinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, categories));
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Tất cả")) {
                    providerAdapter.setFilteredList(providerAdapter.getOriginalList());
                    providerAdapter.setFinalFilteredList(providerAdapter.getFilteredList());
                    providerAdapter.notifyDataSetChanged();
                } else {
                    List<Provider> newList = new ArrayList<>();
                    for (Provider provider : providerAdapter.getOriginalList()) {
                        if (provider.getTilte().toLowerCase().contains(selectedItem.toLowerCase())) {
                            newList.add(provider);
                        }
                    }
                    providerAdapter.setFilteredList(newList);
                    providerAdapter.setFinalFilteredList(providerAdapter.getFilteredList());
                    providerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private List<Provider> getListProvider() {
        List<Provider> list = new ArrayList();
        list.add(new Provider(R.drawable.img_user, "Gia sư Toán", "700.000đ", "Đã được thuê: 500", "5"));
        list.add(new Provider(R.drawable.img_user, "Thiết kế nội thất", "900.000đ", "Đã được thuê: 500", "4.5"));
        list.add(new Provider(R.drawable.img_user, "Gia sư tiếng Anh", "1.700.000đ", "Đã được thuê: 500", "4.7"));
        list.add(new Provider(R.drawable.img_user, "Thiết kế website", "700.000đ", "Đã được thuê: 500", "4.8"));
        list.add(new Provider(R.drawable.img_user, "Trang điểm", "500.000đ", "Đã được thuê: 500", "5"));

        list.add(new Provider(R.drawable.img_user, "Thiết kế nội thất", "900.000đ", "Đã được thuê: 500", "4.5"));
        list.add(new Provider(R.drawable.img_user, "Sửa chữa máy tính", "1.700.000đ", "Đã được thuê: 500", "4.7"));
        list.add(new Provider(R.drawable.img_user, "Thiết kế website", "700.000đ", "Đã được thuê: 500", "4.8"));
        list.add(new Provider(R.drawable.img_user, "Trang điểm", "500.000đ", "Đã được thuê: 500", "5"));

        return list;
    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) requireActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                providerAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                providerAdapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void onClickGoToProviderDetail(Provider provider) {
        Intent intent = new Intent(getContext(), ProviderDetailInfo.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_provider", provider);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}