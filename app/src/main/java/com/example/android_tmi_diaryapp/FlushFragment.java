package com.example.android_tmi_diaryapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Timer;
import java.util.TimerTask;

public class FlushFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_flush, container, false);

        Animation alphaAnim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
        EditText editText = (EditText) rootview.findViewById(R.id.Edit_flush);


        rootview.findViewById(R.id.btn_flush).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.startAnimation(alphaAnim);

                Timer timer = new Timer(true);

                final Handler handler = new Handler(){
                    public void handleMessage(Message msg){
                        editText.setText(null);
                        // 원래 하려던 동작 (UI변경 작업 등)
                    }
                };

                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        Message msg = handler.obtainMessage();
                        handler.sendMessage(msg);
                        timer.cancel();
                    }

                    @Override
                    public boolean cancel() {
                        return super.cancel();
                    }
                };

                timer.schedule(timerTask, 4001);
            }
        });

        return rootview;
    }


}
