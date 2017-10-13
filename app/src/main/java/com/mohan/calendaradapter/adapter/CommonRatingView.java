package com.mohan.calendaradapter.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.mohan.calendaradapter.R;

/**
 * Created by mohang on 13/10/17.
 */

public class CommonRatingView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF rectF = new RectF();
    private Paint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF fillRect = new RectF();

    private RectF leftTopCornerSquare = new RectF();
    private RectF leftBottomCornerSquare = new RectF();

    private int INITIAL_SPACING= 20;

    private int mBorderWidth = 10;
    private int mBorderColor = Color.GRAY;
    private int mTextSize = 30;
    private int mTextColor = Color.BLACK;
    private int mFillColor = Color.WHITE;
    private String mRatingText = "";
    private int selectedState = States.NORMAL.ordinal();
    private int corners = Corners.ZERO.ordinal();
    private RectF rightTopCornerSquare=new RectF();
    private RectF rightBottomCornerSquare=new RectF();
    private Paint cornerPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mSelectedColor=Color.RED;


    private void init(AttributeSet attributeSet) {

        if (attributeSet != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attributeSet,
                    R.styleable.CommonRatingView,
                    0, 0);

            try {
                selectedState = a.getInteger(R.styleable.CommonRatingView_state, States.NORMAL.ordinal());
                mRatingText = a.getString(R.styleable.CommonRatingView_android_text);
                mBorderColor = a.getColor(R.styleable.CommonRatingView_borderColor, Color.GRAY);
                mTextColor = a.getColor(R.styleable.CommonRatingView_android_textColor, Color.BLACK);
                mFillColor = a.getColor(R.styleable.CommonRatingView_ratingFillColor, Color.WHITE);
                mSelectedColor = a.getColor(R.styleable.CommonRatingView_selectedStateColor, Color.RED);
                corners = a.getInteger(R.styleable.CommonRatingView_corners,Corners.ZERO.ordinal());
                if (selectedState == States.SELECTED.ordinal()) {
                    mTextColor = Color.WHITE;
                    mFillColor = Color.RED;
                } else if (selectedState == States.GRAYED_OUT.ordinal()) {
                    mBorderColor = Color.GRAY;
                    mTextColor = Color.GRAY;
                    mFillColor = Color.WHITE;
                }

            } finally {
                a.recycle();
            }
        }


        paint.setStyle(Paint.Style.FILL);
        paint.setColor(mFillColor);
        textPaint.setColor(mTextColor);
        textPaint.setTextSize(mTextSize);
        textPaint.setTextAlign(Paint.Align.CENTER);

        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(mBorderColor);
        linePaint.setStrokeWidth(mBorderWidth);

        cornerPaint.setStyle(Paint.Style.STROKE);
        cornerPaint.setColor(mBorderColor);
        cornerPaint.setStrokeWidth(mBorderWidth/2);

    }

    public void setSelectedState(States selectedState) {
        this.selectedState = selectedState.ordinal();
        paint.setColor(mSelectedColor);
        textPaint.setColor(Color.WHITE);
        invalidate();
    }

    public void setBorderColor(int color) {
        linePaint.setColor(color);
        invalidate();
    }

    public void setBorderWidth(int width) {
        linePaint.setStrokeWidth(width);
        invalidate();
    }


    public CommonRatingView(Context context) {
        super(context);
        init(null);
    }

    public CommonRatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CommonRatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
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
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if (selectedState == States.NORMAL.ordinal() || selectedState == States.GRAYED_OUT.ordinal()) {

            if(corners==Corners.LEFT.ordinal()) {
                drawLeftCorneredView(canvas, width, height);
            }else if(corners==Corners.RIGHT.ordinal()){
                drawRightCorneredView(canvas, width, height);
            }else {
                drawZeroCorneredView(canvas, width, height);
            }
        } else {
            /*paint.setColor(Color.RED);
            textPaint.setColor(Color.WHITE);*/
            if(corners==Corners.LEFT.ordinal()) {
                drawLeftCorneredView(canvas, width, height);
            }else if(corners==Corners.RIGHT.ordinal()){
                drawRightCorneredView(canvas, width, height);
            }else {
                fillRect.set(0, 0, width, height);
                paint.setColor(mSelectedColor);
                canvas.drawRect(fillRect, paint);
            }

        }
        canvas.drawText(mRatingText, width / 2, (height / 2) + (textPaint.getTextSize() / 2), textPaint);
    }

    private void drawRightCorneredView(Canvas canvas, int width, int height) {
        rectF.set(5,5,width-5,height-5);
        canvas.drawRoundRect(rectF,2,2,paint);
      //  canvas.drawText("10",width/2,(height/2)+(textPaint.getTextSize()/2),textPaint);
        canvas.drawLine(0,0,width-INITIAL_SPACING,0,linePaint);  // top horizontal line
        canvas.drawLine(0,height,width-INITIAL_SPACING,height,linePaint);      // bottom horizolnal line
        canvas.drawLine(width,INITIAL_SPACING,width,height-INITIAL_SPACING,linePaint);  // bottom right vertical line\
        canvas.drawLine(0,0,0,height,linePaint);   // left vertical line
        drawRightCorner(canvas, width, height);
    }

    private void drawRightCorner(Canvas canvas, int width, int height) {
        rightTopCornerSquare.set(width-40,2.5f,width-2.5f,40);
        rightBottomCornerSquare.set(width-40,height-40,width-2.5f,height-2.5f);

        canvas.drawArc(rightTopCornerSquare,270,90,false,cornerPaint);
        canvas.drawArc(rightBottomCornerSquare,0,90,false,cornerPaint);
    }

    private void drawLeftCorneredView(Canvas canvas, int width, int height) {
        rectF.set(5,5,width,height-5);
        canvas.drawRoundRect(rectF,2,2,paint);
       // canvas.drawText("1",width/2,(height/2)+(textPaint.getTextSize()/2),textPaint);
        canvas.drawLine(INITIAL_SPACING,0,width,0,linePaint);  // top horizontal line
        canvas.drawLine(INITIAL_SPACING,height,width,height,linePaint);      // bottom horizolnal line
        canvas.drawLine(0,INITIAL_SPACING,0,height-INITIAL_SPACING,linePaint);
        drawLeftCorner(canvas,height);
    }

    private void drawZeroCorneredView(Canvas canvas, int width, int height) {
        rectF.set(5, 5, width, height - 5);
        canvas.drawRect(rectF, paint);
        canvas.drawLine(0, 0, width, 0, linePaint);
        canvas.drawLine(0, height, width, height, linePaint);
        canvas.drawLine(0, 0, 0, height, linePaint);
    }

    public enum States {
        NORMAL, SELECTED, GRAYED_OUT
    }
    public enum Corners {
        ZERO, LEFT, RIGHT
    }

    private void drawLeftCorner(Canvas canvas, int height) {
        leftTopCornerSquare.set(2.5f, 2.5f, 40, 40);
        leftBottomCornerSquare.set(2.5f, height - 40, 40, height - 2.5f);
        canvas.drawArc(leftTopCornerSquare, 180, 90, false, cornerPaint);
        canvas.drawArc(leftBottomCornerSquare, 180, -90, false, cornerPaint);
    }
}
