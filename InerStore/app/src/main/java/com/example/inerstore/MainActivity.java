package com.example.inerstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//内部存储
//打开内部存储文件的方法：openFileOutput

public class MainActivity extends AppCompatActivity {

    private EditText username, id;
    private Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        id = (EditText) findViewById(R.id.id);
        btn_save = (Button) findViewById(R.id.save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInner();
            }
        });
    }

    //保存的方法
    public void saveInner() {
        FileOutputStream fos = null;
        //获取EditText内部输入的内容
        String input_name = username.getText().toString();
        String input_id = id.getText().toString();

        try {
            fos = this.openFileOutput("inner.txt", MODE_PRIVATE);
            fos.write(input_name.getBytes());
            fos.write(input_id.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}