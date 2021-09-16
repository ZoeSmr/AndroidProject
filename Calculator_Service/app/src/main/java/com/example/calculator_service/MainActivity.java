package com.example.calculator_service;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn_start, btn_end;

    public Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_point;
    public Button btn_mul, btn_div, btn_sub, btn_add;
    public Button btn_c, btn_eq;
    public EditText et_out;
    public boolean clear_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_div = (Button) findViewById(R.id.btn_div);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_c = (Button) findViewById(R.id.btn_c);
        btn_eq = (Button) findViewById(R.id.btn_eq);
        et_out = (EditText) findViewById(R.id.et_out);

        btn_0.setOnClickListener(this::onClick);
        btn_1.setOnClickListener(this::onClick);
        btn_2.setOnClickListener(this::onClick);
        btn_3.setOnClickListener(this::onClick);
        btn_4.setOnClickListener(this::onClick);
        btn_5.setOnClickListener(this::onClick);
        btn_6.setOnClickListener(this::onClick);
        btn_7.setOnClickListener(this::onClick);
        btn_8.setOnClickListener(this::onClick);
        btn_9.setOnClickListener(this::onClick);
        btn_mul.setOnClickListener(this::onClick);
        btn_div.setOnClickListener(this::onClick);
        btn_sub.setOnClickListener(this::onClick);
        btn_add.setOnClickListener(this::onClick);
        btn_eq.setOnClickListener(this::onClick);
        btn_c.setOnClickListener(this::onClick);

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalService.class);
                startService(intent);
            }
        });

        btn_end = findViewById(R.id.btn_end);
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalService.class);
                stopService(intent);
            }
        });

    }

    public void onClick(View v) {
        String str = et_out.getText().toString();
        switch (v.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                if(clear_flag) {
                    clear_flag = false;
                    str = "";
                    et_out.setText("");
                }
                et_out.setText(str + ((Button)v).getText());
                break;
            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_div:
            case R.id.btn_mul:
                if(clear_flag) {
                    clear_flag = false;
                    str = "";
                    et_out.setText("");
                }
                if(str.contains("+") || str.contains("-") || str.contains("÷") || str.contains("×")) {
                    str = str.substring(0, str.indexOf(" "));
                }
                et_out.setText(str + " " + ((Button)v).getText() + " ");
                break;
            case R.id.btn_c:
                if(clear_flag) {
                    clear_flag = false;
                }
                str = "";
                et_out.setText("");
                break;
            case R.id.btn_eq:
                getResult();
                break;
        }
    }

    private void getResult() {
        String exp = et_out.getText().toString();
        if(exp == null || exp.equals("")) return;
        if(!exp.contains(" ")) return;
        if(clear_flag) {
            clear_flag = false;
            return;
        }
        clear_flag = true;
        String str1 = exp.substring(0, exp.indexOf(" "));
        String op = exp.substring(exp.indexOf(" ")+1, exp.indexOf(" ")+2);
        String str2 = exp.substring(exp.indexOf(" ")+3);
        double cnt = 0;
        if(!str1.equals("") && !str2.equals("")) {
            double d1 = Double.parseDouble(str1);
            double d2 = Double.parseDouble(str2);
            if(op.equals("+")) cnt = d1 + d2;
            if(op.equals("-")) cnt = d1 - d2;
            if(op.equals("×")) cnt = d1 * d2;
            if(op.equals("÷")) cnt = d1 / d2;
            if(!str1.contains(".") && !str2.contains(".") && !op.equals("÷")) {
                int res = (int) cnt;
                et_out.setText(res + "");
            }
            else {
                et_out.setText(cnt + "");
            }
        }
        else et_out.setText("");
    }


}