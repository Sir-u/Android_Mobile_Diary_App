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
    private CalendarFragment fragmentDiary = new CalendarFragment();
    private FlushhFragment fragmentFlush = new FlushhFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_conainer, fragmentDiary)
                .commitAllowingStateLoss();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_conainer, fragmentDiary).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.menu_diary:
                    transaction
                            .replace(R.id.fragment_conainer, fragmentDiary)
                            .commitAllowingStateLoss();
                    break;
                case R.id.menu_flush:
                    transaction
                        .replace(R.id.fragment_conainer, fragmentFlush)
                        .commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }

}