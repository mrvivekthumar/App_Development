package com.example.tab_layout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tab_layout.fragments.DashboardFragment;
import com.example.tab_layout.fragments.StaffFragment;
import com.example.tab_layout.fragments.StudentFragment;

public class MyViewAdapter extends FragmentStateAdapter {
    public MyViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DashboardFragment();
            case 1:
                return new StudentFragment();
            case 2:
                return new StaffFragment();
            default:
                return new DashboardFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
