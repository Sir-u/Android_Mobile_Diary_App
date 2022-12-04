package com.example.android_tmi_diaryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarFragment extends Fragment {

    private RecyclerView mCalendarRVView;
    private CalendarRVAdapter mCalanderRVAdapter;
    private ArrayList<CalendarItemDTO> mCalendarItems;
    private DatabaseHelper mDBHelper;
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
        mCalanderRVAdapter = new CalendarRVAdapter();
        mCalendarRVView.setAdapter(mCalanderRVAdapter);

        setInit();

        mcalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth){
                mselectedDate = year + "." + (month + 1) + "." + dayOfMonth;
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
        mDBHelper = new DatabaseHelper(getContext());
        mCalendarItems = new ArrayList<>();
    }
}
