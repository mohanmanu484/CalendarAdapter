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

public class LeftCorneredRatingView extends View {

    private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF fillRectangle =new RectF();
    private Paint textPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private Paint linePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint cornerPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF leftTopCornerSquare =new RectF();
    private RectF leftBottomCornerSquare =new RectF();

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


    public LeftCorneredRatingView(Context context) {
        super(context);
        init();
    }

    public LeftCorneredRatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LeftCorneredRatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        canvas.drawText("1",width/2,(height/2)+(textPaint.getTextSize()/2),textPaint);
        canvas.drawLine(INITIAL_SPACING,0,width,0,linePaint);  // top horizontal line
        canvas.drawLine(INITIAL_SPACING,height,width,height,linePaint);      // bottom horizolnal line
        canvas.drawLine(0,INITIAL_SPACING,0,height-INITIAL_SPACING,linePaint);  // bottom vertical line

        leftTopCornerSquare.set(2.5f,2.5f,40,40);
        leftBottomCornerSquare.set(2.5f,height-40,40,height-2.5f);

        canvas.drawArc(leftTopCornerSquare,180,90,false,cornerPaint);
        canvas.drawArc(leftBottomCornerSquare,180,-90,false,cornerPaint);




    }
}
