package com.example.chatbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> myMsgList = new ArrayList<>();
    private EditText input_text;
    private Button send_btn;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_text = findViewById(R.id.input);
        send_btn = findViewById(R.id.send_btn);
        msgRecyclerView = findViewById(R.id.msg_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(linearLayoutManager);

        adapter = new MsgAdapter(myMsgList);
        msgRecyclerView.setAdapter(adapter);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = input_text.getText().toString();
                content = content.trim();
                Sendmsg(content);
                Receivemsg("Copy:" + content);
            }
        });
    }

    private void Sendmsg(String content) {
        Msg msg = new Msg(content, Msg.TYPE_SENT);
        myMsgList.add(msg);
        adapter.notifyItemInserted(myMsgList.size()-1);
        msgRecyclerView.scrollToPosition(myMsgList.size()-1);
        input_text.setText("");
    }

    private void Receivemsg(String content) {
        Msg msg = new Msg(content, Msg.TYPE_RECEIVED);
        myMsgList.add(msg);
        adapter.notifyItemInserted(myMsgList.size()-1);
        msgRecyclerView.scrollToPosition(myMsgList.size()-1);
        input_text.setText("");
    }
}