package com.example.freelancer_connect.user;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.freelancer_connect.user_service.ApprovingServiceFragment;
import com.example.freelancer_connect.user_service.DeniedServiceFragment;
import com.example.freelancer_connect.user_service.DisplayServiceFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DisplayServiceFragment();
            case 1:
                return new ApprovingServiceFragment();
            case 2:
                return new DeniedServiceFragment();
            default:
                return new DisplayServiceFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
