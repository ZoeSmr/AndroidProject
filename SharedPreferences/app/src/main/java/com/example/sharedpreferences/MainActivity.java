package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//Android存储
//1.sharedPreferences 用户偏好
//2.外部存储 SD卡中存储
//3.SQLite数据库存储
//4.ContentProvider 内部提供者 应用：通讯录      延伸：ContentObserver 观察者模式

//sharedPreferences：保存应用中简单的一些配置信息，如：音量、保存用户名和密码

//1.存储
//SharedPreferences对象声明，then初始化：getSharedPreferences（保存的文件名，MODE_PRIVATE）  MODE_PRIVATE：本应用可以用  推荐使用
//保存数据的对象SharedPreferences.Editor，sharedPreferences.edit()
//存入数据：putString（存入数据的名称，
//
// 存入数据的字符串对象名），有String、Boolean等类型
//调用commit方法或apply方法提交

//2.取出
//sharedPreferences.getString（存入数据的名称，默认值显示（null就可以））

public class MainActivity extends AppCompatActivity {

    private Button btn_commit;
    private Button btn_read;
    private EditText user_name, user_id;
    private final static String SharedPreferencesFileName="config";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_commit = findViewById(R.id.btn_commit);
        btn_read = findViewById(R.id.btn_read);
        user_name = findViewById(R.id.username);
        user_id = findViewById(R.id.userid);
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = user_name.getText().toString();
                String id = user_id.getText().toString();

                editor.putString("name", user);
                editor.putString("id", id);
                editor.apply();
            }
        });

        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_name = sharedPreferences.getString("name", null);
                String str_id = sharedPreferences.getString("id", null);
                if(str_name != null && str_id != null) {
                    Toast.makeText(MainActivity.this, "姓名:" + str_name + "学号" + str_id, Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "无数据", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}