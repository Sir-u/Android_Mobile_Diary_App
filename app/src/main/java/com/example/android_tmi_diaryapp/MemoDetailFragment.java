package com.example.android_tmi_diaryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android_tmi_diaryapp.DTO.MemoItemDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MemoDetailFragment extends Fragment {
    private ArrayList<MemoItemDTO> mMemoItems;
    private MemoDBActivity mDBActivity;
    private String mTitle;
    private String mContent;
    private int mID;
    private boolean isExist;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        hideBottomNavigaion(true);
        isExist = false;

        Bundle bundle = getArguments();
        if(bundle != null) {
            mTitle = bundle.getString("title");
            mContent = bundle.getString("content");
            mID = bundle.getInt("ID");
            isExist = bundle.getBoolean("isExist");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_memo_detail, container, false);



        return rootview;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideBottomNavigaion(false);
        backMemo();
    }

    private void setInit() {
        mDBActivity = new MemoDBActivity(getContext());
        mMemoItems = new ArrayList<>();
    }


    public void hideBottomNavigaion(Boolean isNavigationHide) {
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.menu_bottom_navigation);
        if (isNavigationHide == true)
            bottomNavigationView.setVisibility(View.GONE);
        else
            bottomNavigationView.setVisibility(View.VISIBLE);
    }

    private void backMemo() {
        ((MainActivity)getActivity()).OnMemo();
    }

}
