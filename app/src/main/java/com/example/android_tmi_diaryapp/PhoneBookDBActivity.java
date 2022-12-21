package com.example.android_tmi_diaryapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.android_tmi_diaryapp.DTO.PhoneBookItemDTO;

import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

public class PhoneBookDBActivity extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "PhoneBook.db";

    public PhoneBookDBActivity(@Nullable Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 데이터베이스가 생성이 될 때 호출
        // 데이터베이스 -> 테이블 -> 컬럼 -> 값
        db.execSQL("CREATE TABLE IF NOT EXISTS PhoneBookList (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, number TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public ArrayList<PhoneBookItemDTO> getPhoneBookItem(){
        ArrayList<PhoneBookItemDTO> diaryItems = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PhoneBookList ORDER BY name ASC", null);
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String number = cursor.getString(cursor.getColumnIndexOrThrow("number"));

                PhoneBookItemDTO pbItem = new PhoneBookItemDTO();
                pbItem.setId(id);
                pbItem.setName(name);
                pbItem.setNumber(number);
                diaryItems.add(pbItem);
            }
        }

        cursor.close();
        return diaryItems;
    }

    public ArrayList<PhoneBookItemDTO> SearchPhoneBook(String _name){
        ArrayList<PhoneBookItemDTO> diaryItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM PhoneBookList WHERE name=?", new String[]{_name});
        //Log.d("검색할 이름", _name);

        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String number = cursor.getString(cursor.getColumnIndexOrThrow("number"));
                //Log.d("이름 나오나?", name);

                PhoneBookItemDTO pbItem = new PhoneBookItemDTO();
                pbItem.setId(id);
                pbItem.setName(name);
                pbItem.setNumber(number);
                diaryItems.add(pbItem);
            }
        }

        cursor.close();

        return diaryItems;
    }

    // INSERT 문
    public void InsertPhoneBook(String _name, String _number){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO PhoneBookList (name, number) VALUES('" + _name + "', '"+ _number +"');");
    }

    // UPDATE 문
    public void UpdatePhoneBook(String _name, String _number, int _id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE PhoneBookList SET name='" + _name +"', number='"+ _number +"' WHERE id='" + _id + "'");
    }


    // DELETE 문
    public  void DeletePhoneBook(int _id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM PhoneBookList WHERE id = '"+ _id + "'");
    }
}


