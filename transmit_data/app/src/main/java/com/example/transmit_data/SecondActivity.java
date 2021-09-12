package com.example.transmit_data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView transmitText = (TextView) findViewById(R.id.tv_transmit);
        Button btnBack = findViewById(R.id.btn_back);

        //获取传递数据
        String text = getIntent().getStringExtra("Highlight");
        int year = getIntent().getIntExtra("year", 0);

        transmitText.setText(text +"\t" + year);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("back_data", text + "\t" + year);

                setResult(1, intent);
                finish();
            }
        });


    }
}