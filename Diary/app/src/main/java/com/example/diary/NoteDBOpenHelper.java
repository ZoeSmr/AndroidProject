package com.example.diary;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.diary.bean.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteDBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "noteSQLite.db";
    private static final String TABLE_NAME_NOTE = "note";
    private static final String CREATE_TABLE_SQL = "create table " + TABLE_NAME_NOTE + " (id integer primary key autoincrement, title text, content text, create_time text)";

    public NoteDBOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertData(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", note.getTitle());
        values.put("content", note.getContent());
        values.put("create_time", note.getCreatedTime());
        return db.insert(TABLE_NAME_NOTE, null, values);
    }

    public List<Note> queryAllFromDB() {
        SQLiteDatabase db = getWritableDatabase();
        List<Note> noteList = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME_NOTE, null, null, null, null, null, null);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                @SuppressLint("Range") String createTime = cursor.getString(cursor.getColumnIndex("create_time"));

                Note note = new Note();
                note.setId(id);
                note.setTitle(title);
                note.setContent(content);
                note.setCreatedTime(createTime);

                noteList.add(note);
            }
            cursor.close();
        }
        return noteList;
    }

    public int updateData(Note note) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", note.getTitle());
        values.put("content", note.getContent());
        values.put("create_time", note.getCreatedTime());

        return db.update(TABLE_NAME_NOTE, values, "id = ?", new String[]{note.getId()});
    }
}
