package com.example.android_tmi_diaryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_tmi_diaryapp.DTO.CalendarItemDTO;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarFragment extends Fragment implements CalendarRVAdapter.ItemClickListener{

    private RecyclerView mCalendarRVView;
    private CalendarRVAdapter mCalanderRVAdapter;
    private ArrayList<CalendarItemDTO> mCalendarItems;
    private CalendarDatabaseHelper mDBHelper;
    private CalendarView mcalendarView;
    private String mselectedDate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        mselectedDate = year + "." + (month + 1) + "." + day;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_calendar, container, false);

        mcalendarView = (CalendarView)rootview.findViewById(R.id.calendarView);
        mCalendarRVView = rootview.findViewById(R.id.rv_calendar_container);

        setInit();

        mcalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth){
                mselectedDate = year + "." + (month + 1) + "." + dayOfMonth;
                loadRecentDB();
            }
        });

        rootview.findViewById(R.id.btn_goDetail).setOnClickListener(new View.OnClickListener() {
            CalendarDetailFragment calendarDetailFragment = new CalendarDetailFragment();

            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("selectedDate", mselectedDate);
                calendarDetailFragment.setArguments(bundle);

                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_conainer, calendarDetailFragment, "calendarDetailFragment")
                        .commitAllowingStateLoss();
            }
        });

        return rootview;
    }

    private void setInit() {
        mDBHelper = new CalendarDatabaseHelper(getContext());
        mCalendarItems = new ArrayList<>();

        loadRecentDB();
    }

    private void loadRecentDB() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mCalendarRVView.setLayoutManager(layoutManager);
        // 저장되어있던 db를 가져온다.
        mCalendarItems = mDBHelper.getCalendarItems(mselectedDate);
        mCalanderRVAdapter = new CalendarRVAdapter(mCalendarItems, getContext(), mselectedDate, this);
        mCalendarRVView.setHasFixedSize(true);
        mCalendarRVView.setAdapter(mCalanderRVAdapter);
    }


    @Override
    public void onItemClick(CalendarItemDTO calendarItemDTO) {
        CalendarDetailFragment calendarDetailFragment = new CalendarDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("ID", calendarItemDTO.getId());
        bundle.putString("title", calendarItemDTO.getTitle());
        bundle.putString("content", calendarItemDTO.getContent());
        bundle.putString("selectedDate", mselectedDate);
        bundle.putBoolean("isExist", true);
        calendarDetailFragment.setArguments(bundle);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_conainer, calendarDetailFragment, "calendarDetailFragment")
                .commitAllowingStateLoss();
    }
}
