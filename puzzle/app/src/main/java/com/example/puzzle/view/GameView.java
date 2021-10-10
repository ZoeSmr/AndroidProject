package com.example.puzzle.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.puzzle.Patch;
import com.example.puzzle.Point;
import com.example.puzzle.R;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {

    private Context myContext;
    private Resources myResources;
    private Bitmap myBitmap;
    private int bitmapH;
    private int bitmapW;
    private int viewH;//view的高度
    private int viewW;//view的宽度
    private int canvasH;//一格画布的高度
    private int canvasW;//一格画布的宽度
    private int level = 3;
    private int padding = 3;
    private ArrayList<Patch> patches;
    private Random random = new Random();
    private OnFinishListener listener;

    public GameView(Context context) {
        this(context, null);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        myContext = context;
        myResources = getResources();
        myBitmap = ((BitmapDrawable)myResources.getDrawable(R.drawable.puzzle)).getBitmap();
        bitmapH = myBitmap.getHeight() / level;
        bitmapW = myBitmap.getWidth() / level;
        
        initPatches();
    }

    //设置等级
    public void setLevel(int level) {
        this.level = level;
        bitmapW = myBitmap.getWidth() / level;
        bitmapH = myBitmap.getHeight() / level;
        canvasW = viewW / level;
        canvasH = viewH / level;
        initPatches();
        invalidate();
    }

    //设置图片
    public void setMyBitmap(Bitmap bitmap) {
        this.myBitmap = bitmap;
        invalidate();
    }

    public void setOnFinishListener(OnFinishListener listener){
        this.listener = listener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if(heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,widthSpecSize);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAllPic(patches, canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewW = w;
        viewH = h;
        canvasW = w / level;
        canvasH = h / level;
    }

    private void drawAllPic(ArrayList<Patch> patches, Canvas canvas) {
        int size = patches.size();
        for(int i = 0; i < size; i++) {
            drawPic(canvas,patches.get(i));
        }
    }

    private void drawPic(Canvas canvas, Patch patch) {
        if(patch.isEmpty()) return;
        Rect rb = new Rect(patch.getBitmapPoint().getX()*bitmapW + padding, patch.getBitmapPoint().getY()*bitmapH + padding,
                (patch.getBitmapPoint().getX()+1)*bitmapW - padding, (patch.getBitmapPoint().getY()+1)*bitmapH - padding);
        Rect rc = new Rect(patch.getCanvasPoint().getX()*canvasW + padding, patch.getCanvasPoint().getY()*canvasH + padding,
                (patch.getCanvasPoint().getX()+1)*canvasW - padding, (patch.getCanvasPoint().getY()+1)*canvasH - padding);
        canvas.drawBitmap(myBitmap, rb, rc, null);
    }

    private void initPatches() {
        patches = new ArrayList<Patch>();
        Patch emptyPatch = null;

        for(int i = 0; i < level; i++) {
            for(int j = 0; j < level; j++) {
                Patch patch = new Patch();

                Point bpoint = new Point();
                bpoint.setX(i);
                bpoint.setY(j);

                Point cpoint = new Point();
                cpoint.setX(i);
                cpoint.setY(j);

                patch.setBitmapPoint(bpoint);
                patch.setCanvasPoint(cpoint);

                if(bpoint.getX() == (level - 1) && bpoint.getY() == (level - 1)) {
                    patch.setEmpty(true);
                    emptyPatch = patch;
                }
                patches.add(patch);
            }
        }

        for(int i = 0; i < 50*level; i++) {
            emptyPatch = exchange(emptyPatch);
        }
    }

    private Patch exchange(Patch emptyPatch) {
        int randomType = random.nextInt(4);
        Point cpoint = new Point();

        switch (randomType) {
            case 0:
                if(emptyPatch.getCanvasPoint().getX() - 1 >= 0){
                    cpoint.setX(emptyPatch.getCanvasPoint().getX() - 1);
                    cpoint.setY(emptyPatch.getCanvasPoint().getY());
                }
                else {
                    cpoint.setX(emptyPatch.getCanvasPoint().getX() + 1);
                    cpoint.setY(emptyPatch.getCanvasPoint().getY());
                }
                break;
            case 1:
                if(emptyPatch.getCanvasPoint().getX() + 1 < level){
                    cpoint.setX(emptyPatch.getCanvasPoint().getX() + 1);
                    cpoint.setY(emptyPatch.getCanvasPoint().getY());
                }
                else {
                    cpoint.setX(emptyPatch.getCanvasPoint().getX() - 1);
                    cpoint.setY(emptyPatch.getCanvasPoint().getY());
                }
                break;
            case 2:
                if(emptyPatch.getCanvasPoint().getY() - 1 >= 0) {
                    cpoint.setX(emptyPatch.getCanvasPoint().getX());
                    cpoint.setY(emptyPatch.getCanvasPoint().getY() - 1);
                }
                else {
                    cpoint.setX(emptyPatch.getCanvasPoint().getX());
                    cpoint.setY(emptyPatch.getCanvasPoint().getY() + 1);
                }
                break;
            case 3:
                if(emptyPatch.getCanvasPoint().getY() + 1 < level) {
                    cpoint.setX(emptyPatch.getCanvasPoint().getX());
                    cpoint.setY(emptyPatch.getCanvasPoint().getY() + 1);
                }
                else {
                    cpoint.setX(emptyPatch.getCanvasPoint().getX());
                    cpoint.setY(emptyPatch.getCanvasPoint().getY() - 1);
                }
                break;
        }

        Patch patch = findPatch(cpoint);
        return change(patch, emptyPatch);

    }

    //一个图片碎片跟空的图片碎片对调
    private Patch change(Patch patch, Patch emptyPatch) {
        patch.setEmpty(true);
        emptyPatch.setEmpty(false);
        emptyPatch.setBitmapPoint(patch.getBitmapPoint());
        return patch;
    }

    //根据画布上的位置查找相应的图片碎片对象
    private Patch findPatch(Point cpoint) {
        Patch patch = null;
        int size = patches.size();
        for(int i = 0; i < size; i++) {
            Patch patch1 = patches.get(i);
            if(patch1.getCanvasPoint().getX() == cpoint.getX() && patch1.getCanvasPoint().getY() == cpoint.getY()){
                patch = patch1;
                break;
            }
        }
        return patch;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        click((int)event.getX(), (int)event.getY());
        return super.onTouchEvent(event);
    }

    private void click(int x, int y) {
        Point clickPoint = new Point();
        clickPoint.setX(x / canvasW);
        clickPoint.setY(y / canvasH);
        checkMove(clickPoint);
    }

    private void checkMove(Point clickPoint) {
        Patch clickPatch = findPatch(clickPoint);
        Patch emptyPatch = findEmptyPatch();

        if(isNeighbor(clickPatch, emptyPatch)) {
            change(clickPatch, emptyPatch);
            invalidate();
            if(isFinish()) {
                listener.onFinish();
            }
        }
    }

    private boolean isFinish() {
        boolean flag = true;
        int size = patches.size();
        for(int i = 0; i < size; i++) {
            Patch patch = patches.get(i);
            if(patch.getCanvasPoint().getX() != patch.getBitmapPoint().getX() || patch.getBitmapPoint().getY() != patch.getCanvasPoint().getY()) {
                if(patch.getCanvasPoint().getX() == level - 1 && patch.getCanvasPoint().getY() == level - 1 && patch.isEmpty()){

                }else{
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    private boolean isNeighbor(Patch clickPatch, Patch emptyPatch) {
        Point nearPoint = new Point();
        nearPoint.setX(clickPatch.getCanvasPoint().getX()+1);
        nearPoint.setY(clickPatch.getCanvasPoint().getY());
        if(emptyPatch.getCanvasPoint().equals(nearPoint)) return true;

        nearPoint.setX(clickPatch.getCanvasPoint().getX()-1);
        nearPoint.setY(clickPatch.getCanvasPoint().getY());
        if(emptyPatch.getCanvasPoint().equals(nearPoint)) return true;

        nearPoint.setX(clickPatch.getCanvasPoint().getX());
        nearPoint.setY(clickPatch.getCanvasPoint().getY()+1);
        if(emptyPatch.getCanvasPoint().equals(nearPoint)) return true;

        nearPoint.setX(clickPatch.getCanvasPoint().getX());
        nearPoint.setY(clickPatch.getCanvasPoint().getY()-1);
        if(emptyPatch.getCanvasPoint().equals(nearPoint)) return true;

        return false;
    }

    private Patch findEmptyPatch() {
        Patch patch = null;
        int size = patches.size();
        for(int i = 0; i < size; i++) {
            Patch patch1 = patches.get(i);
            if(patch1.isEmpty()) {
                patch = patch1;
                break;
            }
        }
        return patch;
    }
}
