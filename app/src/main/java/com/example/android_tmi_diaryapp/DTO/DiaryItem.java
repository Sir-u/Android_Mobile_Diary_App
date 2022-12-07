package com.example.android_tmi_diaryapp.DTO;

public class DiaryItem {
    private String name;
    private String number;
    private int id;

    public DiaryItem()
    {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
