package com.example.android_tmi_diaryapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.android_tmi_diaryapp.DTO.DiaryItem;

import java.util.ArrayList;

public class DiaryActivity extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Diary.db";

    public DiaryActivity(@Nullable Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 데이터베이스가 생성이 될 때 호출
        // 데이터베이스 -> 테이블 -> 컬럼 -> 값
        db.execSQL("CREATE TABLE IF NOT EXISTS DiaryList (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, number TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public ArrayList<DiaryItem> getDiaryItem(){
        ArrayList<DiaryItem> diaryItems = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DiaryList ORDER BY name DESC", null);
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String number = cursor.getString(cursor.getColumnIndex("number"));

                DiaryItem diaryItem = new DiaryItem();
                diaryItem.setId(id);
                diaryItem.setName(name);
                diaryItem.setNumber(number);
            }
        }

        cursor.close();
        return diaryItems;
    }

    // INSERT 문
    public void InsertDiary(String _name, String _number){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO DiaryList (name, number) VALUES('" + _name + "', '"+ _number +"');");
    }

    // UPDATE 문
    public void UpdateDiary(String _name, String _number, int _id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE DiaryList SET name='" + _name +"', number='"+ _number +"' WHERE id='" + _id + "'");
    }

    // DELETE 문
    public  void DeleteDiary(int _id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM DiaryList WHERE id = '"+ _id + "'");
    }
}


