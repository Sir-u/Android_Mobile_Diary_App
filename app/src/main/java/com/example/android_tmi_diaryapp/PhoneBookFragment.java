package com.example.android_tmi_diaryapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_tmi_diaryapp.DTO.PhoneBookItemDTO;

import java.util.ArrayList;

public class PhoneBookFragment extends Fragment implements PhoneBookRVAdapter.ItemClickListener{

    private RecyclerView mPhoneBookRVView;
    private PhoneBookRVAdapter mPhoneBookRVAdapter;
    private ArrayList<PhoneBookItemDTO> mPhoneBookItems;
    private PhoneBookDBActivity mPhoneBookDBActivity;
    private String mPbName;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_phonebook, container, false);

        mPhoneBookRVView = rootview.findViewById(R.id.rv_phonebook_container);

        setInit();

        return rootview;
    }

    @Override
    public void onItemClick(PhoneBookItemDTO phoneBookItemDTO) {
        String call = phoneBookItemDTO.getNumber();
        String tel = "tel:" + call;
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(tel)));
    }
//
//
//        MemoDetailFragment memoDetailFragment = new MemoDetailFragment();
//
//        Bundle bundle = new Bundle();
//        bundle.putInt("ID", phoneBookItemDTO.getId());
//        bundle.putString("name", phoneBookItemDTO.getName());
//        bundle.putString("number", phoneBookItemDTO.getNumber());
//        bundle.putBoolean("isExist", true);
//        memoDetailFragment.setArguments(bundle);
//
//        getParentFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_conainer, memoDetailFragment, "memoDetailFragment")
//                .commitAllowingStateLoss();
//    }


    private void setInit() {
        mPhoneBookDBActivity = new PhoneBookDBActivity(getContext());
        mPhoneBookItems = new ArrayList<>();

//        mPhoneBookDBActivity.InsertPhoneBook("윤희창", "010-3425-3513"); // DB에 인서트
//        PhoneBookItemDTO item = new PhoneBookItemDTO();
//        item.setName("윤희창");
//        item.setNumber("010-3425-3513");
//        addItem(item);


        loadRecentDB();
    }

//    public void addItem(PhoneBookItemDTO _item){
//        mPhoneBookItems.add(0, _item);
//    }


    private void loadRecentDB(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mPhoneBookRVView.setLayoutManager(layoutManager);
        mPhoneBookItems = mPhoneBookDBActivity.getPhoneBookItem();
        mPhoneBookRVAdapter = new PhoneBookRVAdapter(mPhoneBookItems, getContext(), this);
        mPhoneBookRVView.setHasFixedSize(true);
        mPhoneBookRVView.setAdapter(mPhoneBookRVAdapter);
    }


}
