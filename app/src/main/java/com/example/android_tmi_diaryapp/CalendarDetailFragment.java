package com.example.android_tmi_diaryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_tmi_diaryapp.DTO.CalendarItemDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CalendarDetailFragment extends Fragment {
    private ArrayList<CalendarItemDTO> mCalendarItems;
    private DatabaseHelper mDBHelper;
    private String mselectedDate;
    private String mtitle;
    private String mcontent;
    private int mID;
    private boolean isExist;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomNavigaion(true);
        isExist = false;

        Bundle bundle = getArguments();
        if(bundle != null) {
            mtitle = bundle.getString("title");
            mcontent = bundle.getString("content");
            mID = bundle.getInt("ID");
            mselectedDate = bundle.getString("selectedDate");
            isExist = bundle.getBoolean("isExist");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_calendar_detail, container, false);

        setInit();

        TextView currentDate = rootview.findViewById(R.id.textview_date);// 현재 선택된 날짜
        TextView editTitle = rootview.findViewById(R.id.edit_title); // 일정 title
        TextView eidtContent = rootview.findViewById(R.id.edit_content); // 일정 content

        currentDate.setText(mselectedDate);
        editTitle.setText(mtitle);
        eidtContent.setText(mcontent);

        rootview.findViewById(R.id.btn_saveBack).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(isExist == false){
                    mDBHelper.InsertCalendar(editTitle.getText().toString(), eidtContent.getText().toString(), mselectedDate); // DB에 인서트
                } else {
                    mDBHelper.UpdateCalendar(editTitle.getText().toString(), eidtContent.getText().toString(), mselectedDate, mID);
                }

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

    private void setInit() {
        mDBHelper = new DatabaseHelper(getContext());
        mCalendarItems = new ArrayList<>();
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