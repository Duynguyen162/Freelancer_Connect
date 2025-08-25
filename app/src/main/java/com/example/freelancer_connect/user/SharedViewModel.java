package com.example.freelancer_connect.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> sharedData = new MutableLiveData<>();
    public void setData(String email) {
        sharedData.setValue(email);
    }
    public LiveData<String> getData() {
        return sharedData;
    }
}
