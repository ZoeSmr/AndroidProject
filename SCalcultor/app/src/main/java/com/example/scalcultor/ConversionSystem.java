package com.example.scalcultor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ConversionSystem extends AppCompatActivity {

    Spinner spinner1,spinner2;
    EditText et_shuru,et_shuchu;
    Button btn_CE,btn_js;
    int ids[]={R.id.btn_0,R.id.btn_1,R.id.btn_2,R.id.btn_3,R.id.btn_4,R.id.btn_5, R.id.btn_6,R.id.btn_7,
            R.id.btn_8,R.id.btn_9,R.id.btn_A,R.id.btn_B,R.id.btn_C,R.id.btn_D,R.id.btn_E,R.id.btn_F};
    String  temp=null,num1=null,num2=null,num3=null,num4=null;
    int k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_system);

        btn_CE=findViewById(R.id.btn_CE);
        btn_js=findViewById( R.id.btn_js);
        btn_CE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_shuru.setText("");
                et_shuchu.setText("");
            }
        });
        for(int i=0;i<ids.length;i++){
            Button btn = findViewById(ids[i]);

            if(btn != null)
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()){
                            case  R.id.btn_0:
                                et_shuru.setText(et_shuru.getText()+"0");
                                break;
                            case  R.id.btn_1:
                                et_shuru.setText(et_shuru.getText()+"1");
                                break;
                            case  R.id.btn_2:
                                et_shuru.setText(et_shuru.getText()+"2");
                                break;
                            case  R.id.btn_3:
                                et_shuru.setText(et_shuru.getText()+"3");
                                break;
                            case  R.id.btn_4:
                                et_shuru.setText(et_shuru.getText()+"4");
                                break;
                            case  R.id.btn_5:
                                et_shuru.setText(et_shuru.getText()+"5");
                                break;
                            case  R.id.btn_6:
                                et_shuru.setText(et_shuru.getText()+"6");
                                break;
                            case  R.id.btn_7:
                                et_shuru.setText(et_shuru.getText()+"7");
                                break;
                            case  R.id.btn_8:
                                et_shuru.setText(et_shuru.getText()+"8");
                                break;
                            case  R.id.btn_9:
                                et_shuru.setText(et_shuru.getText()+"9");
                                break;
                            case  R.id.btn_A:
                                et_shuru.setText(et_shuru.getText()+"A");
                                break;
                            case  R.id.btn_B:
                                et_shuru.setText(et_shuru.getText()+"B");
                                break;
                            case  R.id.btn_C:
                                et_shuru.setText(et_shuru.getText()+"C");
                                break;
                            case  R.id.btn_D:
                                et_shuru.setText(et_shuru.getText()+"D");
                                break;
                            case  R.id.btn_E:
                                et_shuru.setText(et_shuru.getText()+"E");
                                break;
                            case  R.id.btn_F:
                                et_shuru.setText(et_shuru.getText()+"F");
                                break;
                        }
                    }
                });
        }
        et_shuru=findViewById(R.id.et_shuru);
        et_shuchu=findViewById(R.id.et_shuchu);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        //建立数据源
        String[] mltems = getResources().getStringArray(R.array.data);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mltems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定Adapter到控件
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                switch (pos){
                    case 0:
                        k=2;
                        setEnabled(2);
                        break;
                    case 1:
                        k=8;
                        setEnabled(8);
                        break;
                    case 2:
                        k=10;
                        setEnabled(10);
                        break;
                    case 3:
                        k=16;
                        setEnabled(16);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int pos, long id) {
                switch (pos){
                    case 0:
                        btn_js.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            temp = et_shuru.getText().toString();
                            num3 = Integer.valueOf(temp, k).toString();//num3为转换后的十进制数
                            num1 = Integer.toBinaryString(Integer.parseInt(num3));//把num3转换成二进制
                            et_shuchu.setText(num1);
                        }
                    });break;
                    case 1:
                        btn_js.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            temp = et_shuru.getText().toString();
                            num3 = Integer.valueOf(temp, k).toString();
                            num2 = Integer.toOctalString(Integer.parseInt(num3));//把num3转换成八进制
                            et_shuchu.setText(num2);
                        }
                    });break;
                    case 2:
                        btn_js.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            temp = et_shuru.getText().toString();
                            num3 = Integer.valueOf(temp, k).toString();
                            et_shuchu.setText(num3);//把十进制
                        }
                    });break;
                    case 3:
                        btn_js.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            temp = et_shuru.getText().toString();
                            num3 = Integer.valueOf(temp, k).toString();
                            num4 = Integer.toHexString(Integer.parseInt(num3));//把num3转换成十六进制
                            et_shuchu.setText(num4);
                        }
                    });break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void setEnabled(int count){
        int i =0,size = Math.min(count,ids.length);
        for( i=0;i<size;i++){
            Button btn = findViewById(ids[i]);
            if(btn != null){
                btn.setEnabled(true);
            }
        }
        for( i=size;i<ids.length;i++){
            Button btn = findViewById(ids[i]);
            if(btn != null){
                btn.setEnabled(false);
            }
        }
    }
}