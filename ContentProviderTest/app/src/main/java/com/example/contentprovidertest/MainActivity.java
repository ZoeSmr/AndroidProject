package com.example.contentprovidertest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_search, btn_add, btn_del, btn_update;
    private ContentResolver resolver;
    private Uri uri_diary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uri_diary = Uri.parse("content://com.example.diary.myprovider/note");
        resolver = getContentResolver();

        btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query();
            }
        });

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });

        btn_del = (Button) findViewById(R.id.btn_del);
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

        btn_update = (Button) findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

    }

    private void query() {
        Cursor cursor = resolver.query(uri_diary, null, null, null, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            Log.e("zoe", "query:" + title);
        }
        cursor.close();
    }

    private void add() {
        ContentValues values = new ContentValues();
        values.put("title", "zhangyi");
        values.put("content", "123456789");
        resolver.insert(uri_diary, values);
    }

    private void delete() {
        resolver.delete(uri_diary, "title=?", new String[]{"zhangyi"});
    }

    private void update() {
        ContentValues values = new ContentValues();
        values.put("title", "123456");
        values.put("content", "abcdefg");
        resolver.update(uri_diary, values, "title=?", new String[]{"zhangyi"});
    }
}