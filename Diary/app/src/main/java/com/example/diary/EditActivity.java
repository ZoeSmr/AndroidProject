package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diary.bean.Note;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends AppCompatActivity {

    private Note note;
    private EditText etTitle,etContent;
    private NoteDBOpenHelper mNoteDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);

        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        note = (Note) intent.getSerializableExtra("note");
        if(note != null) {
            etTitle.setText(note.getTitle());
            etContent.setText(note.getContent());
        }
        mNoteDBOpenHelper = new NoteDBOpenHelper(this);
    }

    public void save(View view) {
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();
        if(TextUtils.isEmpty(title)) {
            Toast.makeText(this, "empty title", Toast.LENGTH_SHORT);
            return;
        }
        note.setTitle(title);
        note.setContent(content);
        note.setCreatedTime(getCurrentTimeFormat());
        long row = mNoteDBOpenHelper.updateData(note);
        if(row != -1) {
            Toast.makeText(this, "change successfully", Toast.LENGTH_SHORT).show();
            this.finish();
        }
        else {
            Toast.makeText(this, "change unsuccessfully", Toast.LENGTH_SHORT).show();
        }
    }

    private String getCurrentTimeFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY年MM月dd HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
}