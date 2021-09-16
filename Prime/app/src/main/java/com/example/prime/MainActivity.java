package com.example.prime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText text, tv_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.editText);
        tv_out = findViewById(R.id.tv_out);

        final Runnable myWorker = new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                int num = Integer.parseInt(text.getText().toString());
                if(isPrime(num)) {
                    tv_out.setText("是素数");
                }
                else {
                    tv_out.setText("不是素数");
                }

                Looper.loop();
            }
        };

        Button btn_check = findViewById(R.id.btn_check);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null, myWorker, "WorkThread");
                workThread.start();
            }
        });
    }

    public static boolean isPrime(int a) {
        boolean flag = true;
        if(a < 2) {
            return false;
        }
        else {
            for(int i = 2; i <= Math.sqrt(a); i++) {
                if(a % i == 0) {
                    flag = false;
                    break;
                }

            }
        }
        return flag;
    }
}