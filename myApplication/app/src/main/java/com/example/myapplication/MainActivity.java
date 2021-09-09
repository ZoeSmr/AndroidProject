package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //private static final String TAG = "zoe";

    //private static final String TAG = "led";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TextView tv_one = findViewById(R.id.tv_one);
//        tv_one.setText("ZZZ");


//        Button btn = findViewById(R.id.btn);
//
//        //点击事件
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e(TAG, msg:"onClick:")
//            }
//        })
//
//        //长按事件
//        btn.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    Log.e(TAG, "onLongClick: ", );
//                    return false;
//                }
//        });
//
//        //触摸事件
//        btn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.e(TAG, "onTouch: ", );
//                return false;
//            }
//        });


        //获取输入框的内容
//        Button btn = findViewById(R.id.btn);
//        EditText et = findViewById(R.id.user_name);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String text = et.getText().toString();
//                Log.e(TAG, "onClick: "+text );
//
//            }
//        });

//    按钮点击显示隐藏进度条
        ProgressBar progressBar = findViewById(R.id.pb);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(progressBar.getVisibility() == View.GONE) {
                    progressBar.setVisibility(View.VISIBLE);
                }
                else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }


}