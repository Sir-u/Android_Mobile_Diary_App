package com.example.android_tmi_diaryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CalendarDetailFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomNavigaion(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_calendar_detail, container, false);


        rootview.findViewById(R.id.btn_saveBack).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                getParentFragmentManager()
                        .beginTransaction()
                        .remove(CalendarDetailFragment.this)
                        .commitAllowingStateLoss();
            }

        });

        return rootview;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideBottomNavigaion(false);
        backCalendar();
    }

    public void hideBottomNavigaion(Boolean isNavigationHide) {
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.menu_bottom_navigation);
        if (isNavigationHide == true)
            bottomNavigationView.setVisibility(View.GONE);
        else
            bottomNavigationView.setVisibility(View.VISIBLE);
    }

    private void backCalendar() {
        ((MainActivity)getActivity()).OnCalendar();
    }
}