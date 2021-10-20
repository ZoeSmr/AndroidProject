package com.example.scalcultor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class UnitConversion extends AppCompatActivity {

    EditText et_input, et_out, et_input2, et_out2;
    Spinner spinner3, spinner4, spinner5, spinner6;
    Button btn2_CE,btn2_js;
    int ids[]={R.id.btn2_0, R.id.btn2_1, R.id.btn2_2, R.id.btn2_3, R.id.btn2_4, R.id.btn2_5, R.id.btn2_6, R.id.btn2_7, R.id.btn2_8, R.id.btn2_9};
    int k;
    double num1;
    String temp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_conversion);

        et_input = findViewById(R.id.et_input);
        et_out = findViewById(R.id.et_out);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner4 = (Spinner) findViewById(R.id.spinner4);

        btn2_CE = findViewById(R.id.btn2_CE);
        btn2_CE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_input.setText("");
                et_out.setText("");
            }
        });
        for(int i=0;i<ids.length;i++){
            Button btn = findViewById(ids[i]);

            if(btn != null)
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()){
                            case  R.id.btn2_0:
                                et_input.setText(et_input.getText()+"0");
                                break;
                            case  R.id.btn2_1:
                                et_input.setText(et_input.getText()+"1");
                                break;
                            case  R.id.btn2_2:
                                et_input.setText(et_input.getText()+"2");
                                break;
                            case  R.id.btn2_3:
                                et_input.setText(et_input.getText()+"3");
                                break;
                            case  R.id.btn2_4:
                                et_input.setText(et_input.getText()+"4");
                                break;
                            case  R.id.btn2_5:
                                et_input.setText(et_input.getText()+"5");
                                break;
                            case  R.id.btn2_6:
                                et_input.setText(et_input.getText()+"6");
                                break;
                            case  R.id.btn2_7:
                                et_input.setText(et_input.getText()+"7");
                                break;
                            case  R.id.btn2_8:
                                et_input.setText(et_input.getText()+"8");
                                break;
                            case  R.id.btn2_9:
                                et_input.setText(et_input.getText()+"9");
                                break;
                        }
                    }
                });
        }
        btn2_js = findViewById(R.id.btn2_js);
        //建立数据源
        String[] mltems = getResources().getStringArray(R.array.unit_length);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mltems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定Adapter到控件
        spinner3.setAdapter(adapter);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        k = 0;//mm
                        break;
                    case 1:
                        k = 1;//cm
                        break;
                    case 2:
                        k = 2;//dm
                        break;
                    case 3:
                        k = 3;//m
                        break;
                    case 4:
                        k = 4;//km
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinner4.setAdapter(adapter);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0://mm
                        btn2_js.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                temp = et_input.getText().toString();
                                num1 = Double.parseDouble(temp);
                                switch (k) {
                                    case 0:
                                        et_out.setText(temp);
                                        break;
                                    case 1:
                                        num1 = num1 * 10;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                    case 2:
                                        num1 = num1 * 100;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                    case 3:
                                        num1 = num1 * 1000;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                    case 4:
                                        num1 = num1 * 1000000;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                }
                            }
                        });
                        break;
                    case 1://cm
                        btn2_js.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                temp = et_input.getText().toString();
                                num1 = Double.parseDouble(temp);
                                switch (k) {
                                    case 0:
                                        num1 = num1 / 10;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                    case 1:
                                        et_out.setText(temp);
                                        break;
                                    case 2:
                                        num1 = num1 * 10;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                    case 3:
                                        num1 = num1 * 100;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                    case 4:
                                        num1 = num1 * 1000000;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                }
                            }
                        });
                        break;
                    case 2://dm
                        btn2_js.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                temp = et_input.getText().toString();
                                num1 = Double.parseDouble(temp);
                                switch (k) {
                                    case 0:
                                        num1 = num1 / 100;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                    case 1:
                                        num1 = num1 / 10;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                    case 2:
                                        et_out.setText(temp);
                                        break;
                                    case 3:
                                        num1 = num1 * 10;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                    case 4:
                                        num1 = num1 * 10000;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                }
                            }
                        });
                        break;
                    case 3://m
                        btn2_js.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                temp = et_input.getText().toString();
                                num1 = Double.parseDouble(temp);
                                switch (k) {
                                    case 0:
                                        num1 = num1 / 1000;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                    case 1:
                                        num1 = num1 / 100;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                    case 2:
                                        num1 = num1 / 10;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                    case 3:
                                        et_out.setText(temp);
                                        break;
                                    case 4:
                                        num1 = num1 * 1000;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                }
                            }
                        });
                        break;
                    case 4://km
                        btn2_js.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                temp = et_input.getText().toString();
                                num1 = Double.parseDouble(temp);
                                switch (k) {
                                    case 0:
                                        num1 = num1 / 1000 / 1000;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                    case 1:
                                        num1 = num1 / 100 / 1000;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                    case 2:
                                        num1 = num1 / 10 / 1000;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                    case 3:
                                        num1 = num1 / 1000;
                                        et_out.setText(String.valueOf(num1));
                                        break;
                                    case 4:
                                        et_out.setText(temp);
                                        break;
                                }
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        et_input2 = findViewById(R.id.et_input2);
        et_out2 = findViewById(R.id.et_out2);
        spinner5 = (Spinner) findViewById(R.id.spinner5);
        spinner6 = (Spinner) findViewById(R.id.spinner6);

        for(int i=0;i<ids.length;i++){
            Button btn = findViewById(ids[i]);

            if(btn != null)
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()){
                            case  R.id.btn2_0:
                                et_input2.setText(et_input2.getText()+"0");
                                break;
                            case  R.id.btn2_1:
                                et_input2.setText(et_input2.getText()+"1");
                                break;
                            case  R.id.btn2_2:
                                et_input2.setText(et_input2.getText()+"2");
                                break;
                            case  R.id.btn2_3:
                                et_input2.setText(et_input2.getText()+"3");
                                break;
                            case  R.id.btn2_4:
                                et_input2.setText(et_input2.getText()+"4");
                                break;
                            case  R.id.btn2_5:
                                et_input2.setText(et_input2.getText()+"5");
                                break;
                            case  R.id.btn2_6:
                                et_input2.setText(et_input2.getText()+"6");
                                break;
                            case  R.id.btn2_7:
                                et_input2.setText(et_input2.getText()+"7");
                                break;
                            case  R.id.btn2_8:
                                et_input2.setText(et_input2.getText()+"8");
                                break;
                            case  R.id.btn2_9:
                                et_input2.setText(et_input2.getText()+"9");
                                break;
                        }
                    }
                });
        }
        //建立数据源
        String[] mltems2 = getResources().getStringArray(R.array.unit_area);
        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mltems2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定Adapter到控件
        spinner5.setAdapter(adapter2);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        k = 0;//mm
                        break;
                    case 1:
                        k = 1;//cm
                        break;
                    case 2:
                        k = 2;//dm
                        break;
                    case 3:
                        k = 3;//m
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinner6.setAdapter(adapter2);
        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0://mm
                        btn2_js.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                temp = et_input2.getText().toString();
                                num1 = Double.parseDouble(temp);
                                switch (k) {
                                    case 0:
                                        et_out2.setText(temp);
                                        break;
                                    case 1:
                                        num1 = num1 * 100;
                                        et_out2.setText(String.valueOf(num1));
                                        break;
                                    case 2:
                                        num1 = num1 * 10000;
                                        et_out2.setText(String.valueOf(num1));
                                        break;
                                    case 3:
                                        num1 = num1 * 1000 * 1000;
                                        et_out2.setText(String.valueOf(num1));
                                        break;
                                }
                            }
                        });
                        break;
                    case 1://cm
                        btn2_js.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                temp = et_input2.getText().toString();
                                num1 = Double.parseDouble(temp);
                                switch (k) {
                                    case 0:
                                        num1 = num1 / 100;
                                        et_out2.setText(String.valueOf(num1));
                                        break;
                                    case 1:
                                        et_out2.setText(temp);
                                        break;
                                    case 2:
                                        num1 = num1 * 100;
                                        et_out2.setText(String.valueOf(num1));
                                        break;
                                    case 3:
                                        num1 = num1 * 100 * 100;
                                        et_out2.setText(String.valueOf(num1));
                                        break;
                                }
                            }
                        });
                        break;
                    case 2://dm
                        btn2_js.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                temp = et_input2.getText().toString();
                                num1 = Double.parseDouble(temp);
                                switch (k) {
                                    case 0:
                                        num1 = num1 / 10000;
                                        et_out2.setText(String.valueOf(num1));
                                        break;
                                    case 1:
                                        num1 = num1 / 100;
                                        et_out2.setText(String.valueOf(num1));
                                        break;
                                    case 2:
                                        et_out2.setText(temp);
                                        break;
                                    case 3:
                                        num1 = num1 * 100;
                                        et_out2.setText(String.valueOf(num1));
                                        break;
                                }
                            }
                        });
                        break;
                    case 3://m
                        btn2_js.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                temp = et_input2.getText().toString();
                                num1 = Double.parseDouble(temp);
                                switch (k) {
                                    case 0:
                                        num1 = num1 / 1000 / 1000;
                                        et_out2.setText(String.valueOf(num1));
                                        break;
                                    case 1:
                                        num1 = num1 / 10000;
                                        et_out2.setText(String.valueOf(num1));
                                        break;
                                    case 2:
                                        num1 = num1 / 100;
                                        et_out2.setText(String.valueOf(num1));
                                        break;
                                    case 3:
                                        et_out2.setText(temp);
                                        break;
                                }
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}