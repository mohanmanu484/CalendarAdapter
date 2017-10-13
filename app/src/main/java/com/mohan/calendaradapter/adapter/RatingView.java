package com.mohan.calendaradapter.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mohang on 13/10/17.
 */

public class RatingView extends View {

    private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF rectF=new RectF();
    private Paint textPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private Paint linePaint=new Paint(Paint.ANTI_ALIAS_FLAG);

    private int DEFAULT_BORDER_WIDTH = 10;
    private int DEFAULT_BORDER_COLOR = Color.GRAY;
    private int DEFAULT_TEXT_SIZE = 30;
    private int DEFAULT_TEXT_COLOR = Color.BLACK;
    private int DEFAULT_FILL_COLOR = Color.WHITE;


    private void init(){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(DEFAULT_FILL_COLOR);
        textPaint.setColor(DEFAULT_TEXT_COLOR);
        textPaint.setTextSize(DEFAULT_TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.CENTER);

        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(DEFAULT_BORDER_COLOR);
        linePaint.setStrokeWidth(DEFAULT_BORDER_WIDTH);

    }

    public void setBorderColor(int color){
        linePaint.setColor(color);
        invalidate();
    }
    public void setBorderWidth(int width){
        linePaint.setStrokeWidth(width);
        invalidate();
    }


    public RatingView(Context context) {
        super(context);
        init();
    }

    public RatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = 100;
        int desiredHeight = 100;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width=getMeasuredWidth();
        int height=getMeasuredHeight();
        rectF.set(5,5,width,height-5);
        canvas.drawRect(rectF,paint);
        canvas.drawText("1",width/2,(height/2)+(textPaint.getTextSize()/2),textPaint);
        canvas.drawLine(0,0,width,0,linePaint);
        canvas.drawLine(0,height,width,height,linePaint);
        canvas.drawLine(0,0,0,height,linePaint);
    }
}
