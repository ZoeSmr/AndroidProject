package com.example.transmit_data;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btn_start_second);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //启动第二个activity
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SecondActivity.class);
                intent.putExtra("Highlight", "4+1+1");
                intent.putExtra("year", 2010);
                //startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView backText = findViewById(R.id.back_text);

        if(resultCode == 1) {
            String str = data.getStringExtra("back_data");
            backText.setText("返回的数据是" + str);
        }
    }
}