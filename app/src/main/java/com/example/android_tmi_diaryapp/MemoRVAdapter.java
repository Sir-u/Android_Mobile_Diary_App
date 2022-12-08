package com.example.android_tmi_diaryapp;

import android.app.Person;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_tmi_diaryapp.DTO.MemoItemDTO;

import java.util.ArrayList;

public class MemoRVAdapter extends RecyclerView.Adapter<MemoRVAdapter.ViewHolder> {
    ArrayList<MemoItemDTO> itemDTOS = new ArrayList<MemoItemDTO>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.rv_memo, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        MemoItemDTO item = itemDTOS.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return itemDTOS.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.memo_title);
        }

        public void setItem(MemoItemDTO item){
            titleView.setText(item.getTitle());
        }


    }

}
