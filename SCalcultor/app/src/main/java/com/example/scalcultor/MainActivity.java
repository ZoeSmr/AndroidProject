package com.example.scalcultor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private String expression = "";
    private boolean last_equal = false;//上一次的按键是否为等号

    protected EditText text1;//第一行，用来显示按过等号之后的完整表达式
    protected EditText text2;//第二行，用来显示表达式和结果

    private View board2;

    private int screen_width;
    private int screen_height;

    private LinearLayout display;

    private Button[] buttons2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (EditText)findViewById(R.id.text1);
        text2 = (EditText)findViewById(R.id.text2);

        buttons2 = new Button[30];

        initScienceBoard(buttons2);//初始化科学计算器键盘
        board2 = (View)findViewById(R.id.board2);


        if(savedInstanceState != null){
            text1.setText(savedInstanceState.getString("text1"));
            text2.setText(savedInstanceState.getString("text2"));
        }
    }

    //活动被回收时，保存临时数据
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("text1", text1.getText().toString());
        outState.putString("text2", text2.getText().toString());
    }

    //为了得到用户区域的高度，重写onWindowFocusChanged,这个方法在onResume之后被调用
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(hasFocus){
            Dimension dimen1 = getAreaOne(this);
            Dimension dimen2 = getAreaTwo(this);
            Dimension dimen3 = getAreaThree(this);
            Log.v("one=>","Area one : \n\tWidth: "+dimen1.mWidth + ";\tHeight: "+dimen1.mHeight);
            Log.v("two=>","\nArea two: \n\tWidth: "+dimen2.mWidth + ";\tHeight: "+dimen2.mHeight);
            Log.v("three","\nArea three: \n\tWidth: "+dimen3.mWidth + ";\tHeight: "+dimen3.mHeight);


            screen_width = dimen3.mWidth;
            screen_height = dimen3.mHeight;

            initWidthAndHeight();
        }
    }

    //初始化键盘，显示区域的宽和高
    private void initWidthAndHeight(){
        //显示区域的高度只和 始终为用户区域高度的三分之一
        display  = (LinearLayout)findViewById(R.id.display);
        android.view.ViewGroup.LayoutParams lp =display.getLayoutParams();
        lp.height=screen_height/3;

        //科学计算器
        //让每个科学计算器的按钮的高度为tablelayout的1/6
        for(int i = 0; i < buttons2.length; i++) {
            buttons2[i].setHeight(screen_height*2/3/6);
        }
    }


    //初始化科学计算器键盘
    private void initScienceBoard(final Button[] buttons){
        buttons[0] = (Button)findViewById(R.id.zero2);
        buttons[1] = (Button)findViewById(R.id.one2);
        buttons[2] = (Button)findViewById(R.id.two2);
        buttons[3] = (Button)findViewById(R.id.three2);
        buttons[4] = (Button)findViewById(R.id.four2);
        buttons[5] = (Button)findViewById(R.id.five2);
        buttons[6] = (Button)findViewById(R.id.six2);
        buttons[7] = (Button)findViewById(R.id.seven2);
        buttons[8] = (Button)findViewById(R.id.eight2);
        buttons[9] = (Button)findViewById(R.id.nine2);

        buttons[10] = (Button)findViewById(R.id.empty2);
        buttons[11] = (Button)findViewById(R.id.delete2);
        buttons[12] = (Button)findViewById(R.id.divide2);
        buttons[13] = (Button)findViewById(R.id.multiple2);
        buttons[14] = (Button)findViewById(R.id.minus2);
        buttons[15] = (Button)findViewById(R.id.plus2);
        buttons[16] = (Button)findViewById(R.id.equal2);
        buttons[17] = (Button)findViewById(R.id.dot2);

        initCommonBtns(buttons);


        //初始化剩余的12个按钮
        buttons[18] = (Button)findViewById(R.id.sin);
        buttons[19] = (Button)findViewById(R.id.cos);
        buttons[20] = (Button)findViewById(R.id.tan);
        buttons[21] = (Button)findViewById(R.id.ln);
        buttons[22] = (Button)findViewById(R.id.log);

        buttons[23] = (Button)findViewById(R.id.factorial);
        buttons[24] = (Button)findViewById(R.id.power);
        buttons[25] = (Button)findViewById(R.id.sqrt);
        buttons[26] = (Button)findViewById(R.id.pi);
        buttons[27] = (Button)findViewById(R.id.left_parentheses);
        buttons[28] = (Button)findViewById(R.id.right_parentheses);
        buttons[29] = (Button)findViewById(R.id.e);

        buttons[18].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                expression += buttons[18].getText() + "(";
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        buttons[19].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                expression += buttons[19].getText() + "(";
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        buttons[20].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                expression += buttons[20].getText() + "(";
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        buttons[21].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                expression += buttons[21].getText() + "(";
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        buttons[22].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                expression += buttons[22].getText() + "(";
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        buttons[23].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                expression += buttons[23].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        buttons[24].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                expression += buttons[24].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        buttons[25].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                expression += buttons[25].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        buttons[26].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                expression += Math.PI;
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        buttons[27].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                expression += buttons[27].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        buttons[28].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                expression += buttons[28].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        buttons[29].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                expression += "2.71828182845904523536";
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
    }

    //初始化18个按钮
    private void initCommonBtns(final Button[] buttons){
        //添加监听事件
        //数字0～9
        for(int i = 0; i < 10; i++){
            final int m = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(last_equal){
                        expression = "";//这次按的数字，如果上次按了等号，则清空表达式
                        last_equal = false;
                    }
                    expression += buttons[m].getText();
                    text2.setText(expression);
                    text2.setSelection(expression.length());
                }
            });
        }
        //empty
        buttons[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = "";
                text2.setText("0");
                text1.setText(null);
                last_equal = false;
            }
        });
        //delete
        buttons[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expression.length() < 1){
                    return;
                }
                expression = expression.substring(0,expression.length()-1);
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        //divide
        buttons[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += buttons[12].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        //multiple
        buttons[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += buttons[13].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        //minus
        buttons[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += buttons[14].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        //plus
        buttons[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += buttons[15].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
        //equal
        buttons[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(last_equal) return;//如果上次还是按的等号，那么什么也不做


                AnimationSet animSet = new AnimationSet(true);
                TranslateAnimation ta = new TranslateAnimation(0,0,0,-100);
                ta.setDuration(80);
                AlphaAnimation aa = new AlphaAnimation(1f, 0f);
                aa.setDuration(75);
                animSet.addAnimation(ta);
                animSet.addAnimation(aa);
                text2.startAnimation(animSet);
                animSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        text1.setText(expression + "=");
                        text1.setSelection(expression.length()+1);//在第一行显示计算表达式
                        try{
                            String result = new Calculate().calculate(expression);
                            text2.setText(result);//在第二行显示计算结果
                        }catch(Exception exception){
                            text2.setText("表达式错误!");
                            expression = "";
                        }

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });


                // 为下一次按计算器键盘做准备。
                // 如果下次按的是数字，那么清空第二行重新输入第一个数。
                // 如果是非数字，那就当这次的结果是输入的第一个数，直接参与运算。
                last_equal = true;

            }


        });
        buttons[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += buttons[17].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
                last_equal = false;
            }
        });
    }

    //屏幕高度
    private Dimension getAreaOne(Activity activity){
        Dimension dimen = new Dimension();
        Display disp = activity.getWindowManager().getDefaultDisplay();
        Point outP = new Point();
        disp.getSize(outP);
        dimen.mWidth = outP.x ;
        dimen.mHeight = outP.y;
        return dimen;
    }
    //不算状态栏的高度
    private Dimension getAreaTwo(Activity activity){
        Dimension dimen = new Dimension();
        Rect outRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        System.out.println("top:"+outRect.top +" ; left: "+outRect.left) ;
        dimen.mWidth = outRect.width() ;
        dimen.mHeight = outRect.height();
        return dimen;
    }
    //不算状态栏，标题栏的高度
    private Dimension getAreaThree(Activity activity){
        Dimension dimen = new Dimension();
        // 用户绘制区域
        Rect outRect = new Rect();
        activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect);
        dimen.mWidth = outRect.width() ;
        dimen.mHeight = outRect.height();
        // end
        return dimen;
    }
    private class Dimension {
        public int mWidth ;
        public int mHeight ;
        public Dimension(){}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help:
                Toast.makeText(this, "这是帮助", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
                break;
            case R.id.change:
                Intent intent = new Intent(this, ConversionSystem.class);
                startActivity(intent);
                break;
            case R.id.converse:
                Intent intent1 = new Intent(this, UnitConversion.class);
                startActivity(intent1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}