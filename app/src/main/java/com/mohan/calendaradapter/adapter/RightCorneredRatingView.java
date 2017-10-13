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

public class RightCorneredRatingView extends View {

    private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF fillRectangle =new RectF();
    private Paint textPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private Paint linePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint cornerPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF rightTopCornerSquare =new RectF();
    private RectF rightBottomCornerSquare =new RectF();

    private int DEFAULT_BORDER_WIDTH = 10;
    private int DEFAULT_BORDER_COLOR = Color.GRAY;
    private int DEFAULT_TEXT_SIZE = 30;
    private int DEFAULT_TEXT_COLOR = Color.BLACK;
    private int DEFAULT_FILL_COLOR = Color.WHITE;

    private int INITIAL_SPACING= 20;


    private void init(){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(DEFAULT_FILL_COLOR);
        textPaint.setColor(DEFAULT_TEXT_COLOR);
        textPaint.setTextSize(DEFAULT_TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.CENTER);

        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(DEFAULT_BORDER_COLOR);
        linePaint.setStrokeWidth(DEFAULT_BORDER_WIDTH);

        cornerPaint.setStyle(Paint.Style.STROKE);
        cornerPaint.setColor(DEFAULT_BORDER_COLOR);
        cornerPaint.setStrokeWidth(DEFAULT_BORDER_WIDTH/2);


    }

    public void setBorderColor(int color){
        linePaint.setColor(color);
        invalidate();
    }
    public void setBorderWidth(int width){
        linePaint.setStrokeWidth(width);
        invalidate();
    }


    public RightCorneredRatingView(Context context) {
        super(context);
        init();
    }

    public RightCorneredRatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RightCorneredRatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        fillRectangle.set(5,5,width,height-5);
        canvas.drawRect(fillRectangle,paint);
        canvas.drawText("10",width/2,(height/2)+(textPaint.getTextSize()/2),textPaint);
        canvas.drawLine(0,0,width-INITIAL_SPACING,0,linePaint);  // top horizontal line
        canvas.drawLine(0,height,width-INITIAL_SPACING,height,linePaint);      // bottom horizolnal line
        canvas.drawLine(width,INITIAL_SPACING,width,height-INITIAL_SPACING,linePaint);  // bottom right vertical line\
        canvas.drawLine(0,0,0,height,linePaint);   // left vertical line

        rightTopCornerSquare.set(width-40,2.5f,width-2.5f,40);
        rightBottomCornerSquare.set(width-40,height-40,width-2.5f,height-2.5f);

        canvas.drawArc(rightTopCornerSquare,270,90,false,cornerPaint);
        canvas.drawArc(rightBottomCornerSquare,0,90,false,cornerPaint);




    }
}
