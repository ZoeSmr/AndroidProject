package com.example.drawactivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.MonthDisplayHelper;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DrawLine extends View {

    private List<Point> allpoint=new ArrayList<Point>();//保存所有的操作坐标，只要有一个坐标触发就通过allpoint集合保存
    public DrawLine(Context context, AttributeSet attrs) {//构造方法作用接收Context，同时接收属性集合
        super(context, attrs);//调用父类的构造方法
        super.setOnTouchListener(new OnTouchListenerImpl());//相当于定义了一个触摸事件
    }
    private class OnTouchListenerImpl implements OnTouchListener{

        //  现在触摸事件只是保存了所有的坐标点，如果想要这些内容显示，则需要复写View类的一个方法
//  OnDraw（）表示重绘（绘图）；
        public boolean onTouch(View v, MotionEvent event) {
            Point point=new Point((int)event.getX(),(int)event.getY());//将我们的坐标点保存到Point类中
            if(event.getAction()==MotionEvent.ACTION_DOWN){//用户按下 表示从新保存点
                DrawLine.this.allpoint=new ArrayList<Point>();//从新绘制一张图
                DrawLine.this.allpoint.add(point);
            }else if(event.getAction()==MotionEvent.ACTION_UP){//用户松开
                DrawLine.this.allpoint.add(point);//记录坐标点
                DrawLine.this.postInvalidate();//移动一点画一点移一点画一点（重新绘图形）

            }else if(event.getAction()==MotionEvent.ACTION_MOVE){//用户移动
                DrawLine.this.allpoint.add(point);//记录坐标点
                DrawLine.this.postInvalidate();//移动一点画一点移一点画一点（重新绘图形）
            }

            return true;//要改为true表示下面不在执行了
        }


    }
    // 由于这个类没有太多复杂的操作，所以直接将这个类配置到布局管理器中即可（这是一个新的组件类）
    @Override
    protected void onDraw(Canvas canvas) {//进行绘图
        Paint paint=new Paint();//依靠此类开始画线
        paint.setColor(Color.RED);//定义图的颜色
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        if(DrawLine.this.allpoint.size()>1){//现有坐标点保存的时候进行开始绘图
            Iterator<Point> iter=DrawLine.this.allpoint.iterator();//固定操作 通过点绘图
            Point first=null;
            Point last=null;
            while(iter.hasNext()){
                if(first==null){
                    first=(Point)iter.next();//取出坐标
                }else{
                    if(last!=null){//前一阶段完成
                        first=last;//重新开始下一阶段
                    }
                    last=(Point)iter.next();//结束点坐标
                    canvas.drawLine(first.x, first.y, last.x, last.y, paint);

                }
            }
        }

    }

}
