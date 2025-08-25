package com.example.freelancer_connect.home_page;


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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.customer_adapter.ServiceAdapter;
import com.example.freelancer_connect.customer_model.Service;
import com.example.freelancer_connect.user_service.AddMyServiceActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment  {

    private Spinner categorySpinner;
    private RecyclerView recyclerView;
    private ServiceAdapter serviceAdapter;
    private SearchView searchView;
    private List<Service> originalServiceList = new ArrayList<>();
    private List<Service> spinnerFilteredServiceList = new ArrayList<>();
    private List<Service> searchFilteredServiceList = new ArrayList<>();

    private List<String> categoryList = new ArrayList<>();
    private FirebaseFirestore db;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        db = FirebaseFirestore.getInstance();

        categorySpinner = rootView.findViewById(R.id.spinner_service_category);

        recyclerView = rootView.findViewById(R.id.recycler_view_service_item);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        serviceAdapter = new ServiceAdapter(searchFilteredServiceList, new OnClickItemService() {
            @Override
            public void onServiceClickListener(Service service) {
                Intent intent = new Intent(getContext(), ServiceDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_service", service);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(serviceAdapter);

        MenuHost menuHost = requireActivity();
        menuHost.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu);
                MenuItem searchItem = menu.findItem(R.id.action_search);
                SearchView searchView = (SearchView) searchItem.getActionView();
                searchView.setMaxWidth(Integer.MAX_VALUE);

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        filterDataBySearchView(query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        filterDataBySearchView(newText);
                        return true;
                    }
                });
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);


        setupSpinner();
        fetchDataFromFirestore();

        return rootView;
    }

    private void fetchDataFromFirestore() {
        CollectionReference servicesRef = db.collection("services");
        servicesRef.whereEqualTo("status", "Đã được duyệt")
                .get()
                .addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                originalServiceList.clear();

                for (QueryDocumentSnapshot document : task.getResult()) {
                    Service service = document.toObject(Service.class);
                    originalServiceList.add(service);
                }

                spinnerFilteredServiceList.addAll(originalServiceList);
                searchFilteredServiceList.addAll(originalServiceList);
                serviceAdapter.notifyDataSetChanged();
            } else {

            }
        });
    }

    private void setupSpinner() {
        categoryList.add("Tất cả");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categoryList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);
        db.collection("categories").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String categoryName = document.getString("name");
                        categoryList.add(categoryName);
                    }
                    spinnerAdapter.notifyDataSetChanged();
                } else {

                }
            }
        });

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = (String) parent.getItemAtPosition(position);
                filterDataBySpinner(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void filterDataBySpinner(String category) {
        spinnerFilteredServiceList.clear();
        if (category.equals("Tất cả")) {
            spinnerFilteredServiceList.addAll(originalServiceList);
        } else {
            for (Service service : originalServiceList) {
                if (service.getCategoryName().equalsIgnoreCase(category)) {
                    spinnerFilteredServiceList.add(service);
                }
            }
        }
        searchFilteredServiceList.clear();
        searchFilteredServiceList.addAll(spinnerFilteredServiceList);
        serviceAdapter.notifyDataSetChanged();
    }

    private void filterDataBySearchView(String query) {
        searchFilteredServiceList.clear();
        if (query.isEmpty()) {
            searchFilteredServiceList.addAll(spinnerFilteredServiceList);
        } else {
            for (Service service : spinnerFilteredServiceList) {
                if (service.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    searchFilteredServiceList.add(service);
                }
            }
        }
        serviceAdapter.notifyDataSetChanged();
    }
}