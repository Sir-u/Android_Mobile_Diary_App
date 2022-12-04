package com.example.android_tmi_diaryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarFragment extends Fragment {

    private RecyclerView mCalendarRVView;
    private CalendarRVAdapter mCalanderRVAdapter;
    private ArrayList<CalendarItemDTO> mCalendarItems;
    private CalendarDetailFragment calendarDetailFragment = new CalendarDetailFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_calendar, container, false);

        mCalendarRVView = rootview.findViewById(R.id.rv_calendar_container);
        mCalanderRVAdapter = new CalendarRVAdapter();
        mCalendarRVView.setAdapter(mCalanderRVAdapter);

        rootview.findViewById(R.id.btn_goDetail).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_conainer, calendarDetailFragment, "calendarDetailFragment")
                        .commitAllowingStateLoss();
            }
        });

        return rootview;
    }
}
