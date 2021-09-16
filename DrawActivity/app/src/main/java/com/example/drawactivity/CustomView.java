package com.example.drawactivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomView extends View {
    float startX, startY, endX, endY;
    private List<Path> pointList = new ArrayList<Path>();
    Bitmap bitmap;
    Paint paint;
    Path path;
    Canvas canvas;
    boolean booleanOnTouch = false;


    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        if(bitmap != null) canvas.drawBitmap(bitmap, 0, 0, paint);
        //super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startX = event.getX();
        startY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path = new Path();
                path.moveTo(startX, startY);
                endX = startX;
                endY = startY;
                booleanOnTouch = true;
                pointList.add(path);
                break;
            case MotionEvent.ACTION_MOVE:
                if(booleanOnTouch) {
                    path.quadTo(endX, endY, startX, startY);
                    endX = startX;
                    endY = startY;
                    drawStroke();
                }
                break;
            case MotionEvent.ACTION_UP:
                if(booleanOnTouch) {
                    path.quadTo(endX, endY, startX, startY);
                    drawStroke();
                    booleanOnTouch = false;
                }
                break;
        }
        return true;
    }

    public void drawStroke() {
        if(canvas == null) {
            bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas();
            canvas.setBitmap(bitmap);
            paint = new Paint();
            paint.setColor(Color.YELLOW);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            paint.setAntiAlias(true);
        }
        for(Path path : pointList) {
            canvas.drawPath(path, paint);
           // canvas.drawLine(startX, startY, endX, endY, paint);
        }
        invalidate();
    }


}
