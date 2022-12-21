package com.example.android_tmi_diaryapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_tmi_diaryapp.DTO.PhoneBookItemDTO;

import java.util.ArrayList;

public class PhoneBookFragment extends Fragment implements PhoneBookRVAdapter.ItemClickListener { //, TextWatcher {

    private RecyclerView mPhoneBookRVView;
    private PhoneBookRVAdapter mPhoneBookRVAdapter;
    private ArrayList<PhoneBookItemDTO> mPhoneBookItems;
    private PhoneBookDBActivity mPhoneBookDBActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_phonebook, container, false);
        TextView pbName = rootview.findViewById(R.id.PB_Search);

        mPhoneBookRVView = rootview.findViewById(R.id.rv_phonebook_container);

        setInit();

        rootview.findViewById(R.id.pb_search_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = pbName.getText().toString();

                //Log.d("이름 전달", name);
                mPhoneBookDBActivity.SearchPhoneBook(name);
                loadSearchDB(name);
            }
        });

        rootview.findViewById(R.id.pb_add_button).setOnClickListener(new View.OnClickListener() {
            private PhoneBookDetailFragment mPhoneBookDetailFragment = new PhoneBookDetailFragment();

            public void onClick(View v) {
                Bundle bundle = new Bundle();

                mPhoneBookDetailFragment.setArguments(bundle);

                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_conainer, mPhoneBookDetailFragment, "PhoneBookDetailFragment")
                        .commitAllowingStateLoss();
            }
        });


        return rootview;
    }

    @Override
    public void onItemClick(PhoneBookItemDTO phoneBookItemDTO) {
        String call = phoneBookItemDTO.getNumber();
        String tel = "tel:" + call;
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(tel)));
    }

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

    private void loadSearchDB(String name){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mPhoneBookRVView.setLayoutManager(layoutManager);
        mPhoneBookItems = mPhoneBookDBActivity.SearchPhoneBook(name);
        mPhoneBookRVAdapter = new PhoneBookRVAdapter(mPhoneBookItems, getContext(), this);
        mPhoneBookRVView.setHasFixedSize(true);
        mPhoneBookRVView.setAdapter(mPhoneBookRVAdapter);
    }
}
