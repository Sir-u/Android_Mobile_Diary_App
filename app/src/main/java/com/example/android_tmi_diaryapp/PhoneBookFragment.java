package com.example.android_tmi_diaryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android_tmi_diaryapp.DTO.PhoneBookItemDTO;

public class PhoneBookFragment extends Fragment implements PhoneBookRVAdapter.ItemClickListener{

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_phonebook, container, false);



        return rootview;
    }

    @Override
    public void onItemClick(PhoneBookItemDTO phoneBookItemDTO) {
        MemoDetailFragment memoDetailFragment = new MemoDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("ID", phoneBookItemDTO.getId());
        bundle.putString("name", phoneBookItemDTO.getName());
        bundle.putString("number", phoneBookItemDTO.getNumber());
        bundle.putBoolean("isExist", true);
        memoDetailFragment.setArguments(bundle);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_conainer, memoDetailFragment, "memoDetailFragment")
                .commitAllowingStateLoss();
    }
}
