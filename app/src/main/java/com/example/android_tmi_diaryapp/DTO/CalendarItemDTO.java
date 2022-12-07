package com.example.android_tmi_diaryapp.DTO;

public class CalendarItemDTO {
    private int id;             // 켈린더리스트 아이템의 고유 아이디
    private String title;       // 할일 제목
    private String content;     // 할일 내용
    private String date;        // 할일 날자

    public CalendarItemDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
