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
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CalendarDetailFragment extends Fragment {
    private ArrayList<CalendarItemDTO> mCalendarItems;
    private DatabaseHelper mDBHelper;
    private String mselectedDate;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomNavigaion(true);

        Bundle bundle = getArguments();
        if(bundle != null) {
            mselectedDate = bundle.getString("selectedDate");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_calendar_detail, container, false);

        setInit();

        TextView currentDate = rootview.findViewById(R.id.textview_date);
        currentDate.setText(mselectedDate); // 현재 선택된 날짜 설정
        TextView eidtTitle = rootview.findViewById(R.id.edit_title); // 일정 title
        TextView eidtContent = rootview.findViewById(R.id.edit_content); // 일정 content

        rootview.findViewById(R.id.btn_saveBack).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mDBHelper.InsertCalendar(eidtTitle.getText().toString(), eidtContent.getText().toString(), mselectedDate); // DB에 인서트
                CalendarItemDTO item = new CalendarItemDTO();
                item.setTitle(eidtTitle.getText().toString());
                item.setContent(eidtContent.getText().toString());
                item.setDate(mselectedDate);
                addItem(item);

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

    // 현재 어뎁터에 새로운 게시물 아이템을 전달받아 추가한다.
    public void addItem(CalendarItemDTO _item){
        mCalendarItems.add(0, _item);
    }
}