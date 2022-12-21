package com.example.android_tmi_diaryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android_tmi_diaryapp.DTO.MemoItemDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MemoDetailFragment extends Fragment {
    private ArrayList<MemoItemDTO> mMemoItems;
    private MemoDBActivity mDBActivity;
    private String mmTitle;
    private String mmContent;
    private int mmID;
    private boolean isExist;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        hideBottomNavigaion(true);
        isExist = false;

        Bundle bundle = getArguments();
        if(bundle != null) {
            mmTitle = bundle.getString("title");
            mmContent = bundle.getString("content");
            mmID = bundle.getInt("ID");
            isExist = bundle.getBoolean("isExist");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_memo_detail, container, false);

        setInit();

        TextView memoTitle = rootview.findViewById(R.id.memo_title);        // memo title
        TextView memoContent = rootview.findViewById(R.id.memo_content);    // memo content

        memoTitle.setText(mmTitle);
        memoContent.setText(mmContent);

        rootview.findViewById(R.id.memo_button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isExist == false){
                    mDBActivity.InsertMemo(memoTitle.getText().toString(), memoContent.getText().toString());
                } else {
                    mDBActivity.UpdateMemo(memoTitle.getText().toString(), memoContent.getText().toString(), mmID);
                }

                getParentFragmentManager()
                        .beginTransaction()
                        .remove(MemoDetailFragment.this)
                        .commitAllowingStateLoss();
            }
        });

        rootview.findViewById(R.id.memo_button_cancel).setOnClickListener(new View.OnClickListener(){
            MemoFragment memoFragment = new MemoFragment();

            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                memoFragment.setArguments(bundle);

                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_conainer, memoFragment, "memoFragment")
                        .commitAllowingStateLoss();
            }
        });
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
