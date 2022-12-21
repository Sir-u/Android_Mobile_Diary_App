package com.example.android_tmi_diaryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android_tmi_diaryapp.DTO.PhoneBookItemDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import kotlinx.coroutines.ObsoleteCoroutinesApi;

public class PhoneBookDetailFragment extends Fragment {
    private ArrayList<PhoneBookItemDTO> mPhoneBookItemDTO;
    private PhoneBookDBActivity mPhoneBookDBActivity;
    private String mName;
    private String mNumber;
    private int mID;
    private boolean isExist;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomNavigaion(true);
        isExist = false;

        Bundle bundle = getArguments();
        if (bundle != null) {
            mName = bundle.getString("name");
            mNumber = bundle.getString("number");
            mID = bundle.getInt("ID");
            isExist = bundle.getBoolean("isExist");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_phonebook_detail, container, false);

        setInit();

        TextView pbName = rootview.findViewById(R.id.ev_pb_name);
        TextView pbNumber = rootview.findViewById(R.id.ev_pb_number);

        pbName.setText(mName);
        pbNumber.setText(mNumber);

        rootview.findViewById(R.id.pb_check_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPhoneBookDBActivity.InsertPhoneBook(pbName.getText().toString(), pbNumber.getText().toString());

                getParentFragmentManager()
                        .beginTransaction()
                        .remove(PhoneBookDetailFragment.this)
                        .commitAllowingStateLoss();
            }
        });

        return rootview;
    }

    private void setInit() {
        mPhoneBookDBActivity = new PhoneBookDBActivity(getContext());
        mPhoneBookItemDTO = new ArrayList<>();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideBottomNavigaion(false);
        backPhoneBook();
    }

    public void hideBottomNavigaion(Boolean isNavigationHide) {
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.menu_bottom_navigation);
        if (isNavigationHide == true)
            bottomNavigationView.setVisibility(View.GONE);
        else
            bottomNavigationView.setVisibility(View.VISIBLE);
    }

    private void backPhoneBook() {
        ((MainActivity)getActivity()).OnPhoneBook();
    }

}
