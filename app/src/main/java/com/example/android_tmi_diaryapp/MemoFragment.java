package com.example.android_tmi_diaryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MemoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_memo, container, false);

        rootview.findViewById(R.id.memo_button_add).setOnClickListener(new View.OnClickListener() {
            MemoDetailFragment memoDetailFragment = new MemoDetailFragment();

            public void onClick(View v) {
                Bundle bundle = new Bundle();
                memoDetailFragment.setArguments(bundle);

                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_conainer, memoDetailFragment, "memoDetailFragment")
                        .commitAllowingStateLoss();
            }
        });


        return rootview;
    }
}
