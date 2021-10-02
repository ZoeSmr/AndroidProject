package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.example.diary.adapter.MyAdapter;
import com.example.diary.bean.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FloatingActionButton mBtnAdd;
    private List<Note> mNotes;
    private MyAdapter mAdapter;
    private NoteDBOpenHelper mNoteDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshDataFromDB();
    }

    private void refreshDataFromDB() {
        mNotes = getDataFromDB();
        mAdapter.refreshData(mNotes);
    }

    private void initEvent() {
        mAdapter = new MyAdapter(this, mNotes);
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initData() {

        mNotes = new ArrayList<>();
        mNoteDBOpenHelper = new NoteDBOpenHelper(this);

/*        for(int i = 0; i < 10; i++) {
            Note note = new Note();
            note.setTitle("title" + i);
            note.setContent("content" + i);
            note.setCreatedTime(getCurrentTimeFormat());
            mNotes.add(note);
        }*/

//        mNotes = getDataFromDB();

    }

    private List<Note> getDataFromDB() {
        return mNoteDBOpenHelper.queryAllFromDB();
    }

/*    private String getCurrentTimeFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY年MM月dd HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);

    }*/

    private void initView() {
        mRecyclerView = findViewById(R.id.rlv);
    }


    public void add(View view) {
        Intent intent = new Intent(this, AddActivity.class);

        startActivity(intent);
    }
}