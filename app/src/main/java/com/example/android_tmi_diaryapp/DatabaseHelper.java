package com.example.android_tmi_diaryapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.android_tmi_diaryapp.DTO.CalendarItemDTO;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "DiaryApp.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 데이터 베이스가 생성이 될 때 호출

        // 데이터베이스 -> 데이터 -> 컬럼 -> 값
        db.execSQL("CREATE TABLE IF NOT EXISTS CalendarList (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, content TEXT NOT NULL, date TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    // SELECT 문 (할일 목록들을 조회)
    public ArrayList<CalendarItemDTO> getCalendarItems(String selectedDate) {
        ArrayList<CalendarItemDTO> calendarItems = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CalendarList WHERE date='" + selectedDate + "' ORDER BY id DESC", null);
        if(cursor.getCount() != 0) {
            // 조회된 데이터가 있을때 내부 수정
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));

                CalendarItemDTO calendarItemDTO = new CalendarItemDTO();
                calendarItemDTO.setId(id);
                calendarItemDTO.setTitle(title);
                calendarItemDTO.setContent(content);
                calendarItemDTO.setDate(date);
                calendarItems.add(calendarItemDTO);
            }
        }
        cursor.close();

        return calendarItems;
    }

    // INSERT 문 (할일 목록을 db에 넣는다.)
    public void InsertCalendar(String _title, String _content, String _date){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO CalendarList (title, content, date) VALUES('" + _title +"', '" + _content +"','" + _date +"');");
    }

    // UPDATE 문 (할일 목록을 수정한다.)
    public void UpdateCalendar(String _title, String _content, String _date, int _id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE CalendarList SET title='" + _title +"', content= '" + _content +"', date='" + _date +"' WHERE id='" + _id + "' ");
    }

    // DELETE 문 (할일 목록을 삭제한다.)
    public void DeleteCalendar(int _id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM CalendarList WHERE id='" + _id +"'");
    }

}
