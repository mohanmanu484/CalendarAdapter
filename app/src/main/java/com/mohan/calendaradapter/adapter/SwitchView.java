package com.mohan.calendaradapter.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mohan.calendaradapter.R;

/**
 * Created by mohang on 14/10/17.
 */

public class SwitchView extends View {

    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint innerRadius=new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF rectF=new RectF();
    private int mFillColor;
    private int mSwitchColor;

    public SwitchView(Context context) {
        super(context);
        init(null);
    }

    public SwitchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SwitchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    private void init(AttributeSet attributeSet){

        if (attributeSet != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attributeSet,
                    R.styleable.CommonRatingView,
                    0, 0);

            try {
                mFillColor = a.getColor(R.styleable.SwitchView_switchFillColor, Color.WHITE);
                mSwitchColor = a.getColor(R.styleable.SwitchView_switchButtoncolor, Color.BLACK);

            } finally {
                a.recycle();
            }
        }



        paint.setColor(mFillColor);
        paint.setStyle(Paint.Style.FILL);

        innerRadius.setColor(mSwitchColor);
        innerRadius.setStyle(Paint.Style.FILL);



    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width=getMeasuredWidth();
        int height = getMeasuredHeight();
        rectF.set(0,0,width,height);
        canvas.drawRoundRect(rectF,height/2,height/2,paint);
        canvas.drawCircle((width/4)*3,height/2,height/3,innerRadius);

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
        setMeasuredDimension(width, width/2);
    }

}
