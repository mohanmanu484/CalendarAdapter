package com.mohan.calendaradapter.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mohang on 12/10/17.
 */

public class SquareView extends View {

    Paint textPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF rectF=new RectF();
    RectF strokeF=new RectF();
    Paint rectanglePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint strokePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint cornerPaint=new Paint(Paint.ANTI_ALIAS_FLAG);

    RectF blankRect=new RectF();

    RectF strokeLeftTopCornerRect=new RectF(109,250,119,260);


    public SquareView(Context context) {
        super(context);
        init();
    }

    public SquareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SquareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(20);
        rectanglePaint.setStyle(Paint.Style.FILL);
        rectanglePaint.setColor(Color.YELLOW);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(5);
        strokePaint.setColor(Color.RED);
        rectF.set(110,110,200,190);
        strokeF.set(100,100,200,200);

        blankRect.set(110,250,200,350);
        cornerPaint.setStrokeWidth(5);
        cornerPaint.setStyle(Paint.Style.STROKE);
        cornerPaint.setColor(Color.RED);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        if(width > height){
            width = height;
        }
        else{
            height = width;
        }

        int newWidthMeasureSpec=MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        int newHeightMeasureSpec=MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);

        super.onMeasure(newWidthMeasureSpec, newHeightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawLine(50,20,100,20,rectanglePaint);
        RectF rightTopCorner=new RectF();
        rightTopCorner.set(90,20,110,40);
        canvas.drawLine(110,30,110,80,rectanglePaint);
        canvas.drawArc(rightTopCorner,270,90,true,strokePaint);

        RectF rightBottomCorner=new RectF();
        rightBottomCorner.set(90,70,110,90);
        canvas.drawArc(rightBottomCorner,0,90,true,strokePaint);
        canvas.drawLine(50,90,100,90,rectanglePaint);

       // canvas.drawRect(strokeF,strokePaint);
        canvas.drawRoundRect(strokeF, 10, 10, strokePaint);
        canvas.save();
        canvas.drawRect(rectF,rectanglePaint);
        canvas.save();
        canvas.drawText("1",rectF.centerX(),rectF.centerY(),textPaint);


        ///// actual experiment



        canvas.drawRect(blankRect,rectanglePaint);
        canvas.drawLine(115,249,195,249,strokePaint);

        canvas.drawLine(109,255,109,345,strokePaint);
        canvas.drawLine(115,351,195,351,strokePaint);

        canvas.drawArc(strokeLeftTopCornerRect,180,90,false,cornerPaint);

    }
}
