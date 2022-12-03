package com.example.android_tmi_diaryapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarRVAdapter extends RecyclerView.Adapter<CalendarRVAdapter.ViewHolder> {
    private ArrayList<CalendarItemDTO> mCalendarItemDTO;

    public CalendarRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_diary, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(CalendarRVAdapter.ViewHolder holder, int position) {
        holder.onBind(mCalendarItemDTO.get(position));
    }

    public void setFriendList(ArrayList<CalendarItemDTO> list){
        this.mCalendarItemDTO = list;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return mCalendarItemDTO.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ViewHolder(View itemView){
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.rv_calendar_title);
        }

        void onBind(CalendarItemDTO item) {
            title.setText(item.getTitle());
        }
    }

}
