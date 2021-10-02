package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diary.bean.Note;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    private EditText etTitle, etContent;
    private NoteDBOpenHelper mNoteDBOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        mNoteDBOpenHelper = new NoteDBOpenHelper(this);
    }

    public void add(View view) {
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();
        if(TextUtils.isEmpty(title)) {
            Toast.makeText(this, "empty title", Toast.LENGTH_SHORT);
            return;
        }

        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setCreatedTime(getCurrentTimeFormat());
        long row = mNoteDBOpenHelper.insertData(note);
        if(row != -1) {
            Toast.makeText(this, "add successfully", Toast.LENGTH_SHORT).show();
            this.finish();
        }
        else {
            Toast.makeText(this, "add unsuccessfully", Toast.LENGTH_SHORT).show();
        }
    }

    private String getCurrentTimeFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY年MM月dd HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
}