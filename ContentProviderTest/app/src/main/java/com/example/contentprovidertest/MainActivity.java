package com.example.contentprovidertest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_search;
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



    }

    private void query() {
        Cursor cursor = resolver.query(uri_diary, null, null, null, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
          //  @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
            Log.e("zoe", "query:" + title);
        }
        cursor.close();
    }
}