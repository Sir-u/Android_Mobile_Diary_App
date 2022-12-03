package com.example.android_tmi_diaryapp;

public class CalendarItemDTO {
    String diary_title;

    public CalendarItemDTO(String title) {
        this.diary_title = title;
    }

    public String getTitle() {
        return diary_title;
    }

    public void setTitle(String title) {
        this.diary_title = title;
    }
}
