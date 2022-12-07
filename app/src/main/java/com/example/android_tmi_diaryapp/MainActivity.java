package com.example.android_tmi_diaryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private CalendarFragment fragmentCalendar = new CalendarFragment();
    private FlushhFragment fragmentFlush = new FlushhFragment();
    private MemoFragment fragmentMemo = new MemoFragment();
    private PhoneBookFragment fragmentPhoneBook = new PhoneBookFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OnCalendar();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_conainer, fragmentCalendar).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.menu_calendar:
                    transaction
                            .replace(R.id.fragment_conainer, fragmentCalendar, "fragmentCalendar")
                            .commitAllowingStateLoss();
                    break;
                case R.id.menu_flush:
                    transaction
                        .replace(R.id.fragment_conainer, fragmentFlush, "fragmentFlush")
                        .commitAllowingStateLoss();
                    break;

                case R.id.menu_phoneBook:
                    transaction
                            .replace(R.id.fragment_conainer, fragmentPhoneBook, "fragmentPhoneBook")
                            .commitAllowingStateLoss();
                    break;

                case R.id.menu_memo:
                    transaction
                            .replace(R.id.fragment_conainer, fragmentMemo, "fragmentMemo")
                            .commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }

    public void OnCalendar() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_conainer, fragmentCalendar)
                .commitAllowingStateLoss();
    }
}