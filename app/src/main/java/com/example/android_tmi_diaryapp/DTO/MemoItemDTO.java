package com.example.android_tmi_diaryapp.DTO;

public class MemoItemDTO {
    private String title;
    private String content;
    private int id;

    public MemoItemDTO() {}

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
