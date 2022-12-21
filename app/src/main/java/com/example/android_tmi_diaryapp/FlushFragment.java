package com.example.android_tmi_diaryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FlushFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_flush, container, false);

        EditText editText = (EditText) rootview.findViewById(R.id.Edit_flush);
        Animation alphaAnim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);

        class FlushThread extends Thread {
            private static final String TAG = "ExampleThread";

            public FlushThread() {
                // 초기화 작업
            }

            public void run() {
                boolean isTimeOut = false;
                boolean stopThread = false;

                while (!stopThread) {
                    try {
                        if(isTimeOut) {
                            editText.setText(null);
                            stopThread = true;
                        }
                        isTimeOut = true;
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        rootview.findViewById(R.id.btn_flush).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FlushThread flushThread = new FlushThread();
                editText.startAnimation(alphaAnim);

                flushThread.start();
            }
        });

        return rootview;
    }


}
