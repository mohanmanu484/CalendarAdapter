package com.mohan.calendaradapter.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mohan.calendaradapter.R;

/**
 * Created by mohang on 13/10/17.
 */

public class TagView extends View {

    private Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private String tagText = "Amol";
    private int tagTextColor;
    private int fillColor;
    private int minSize=50;

    private RectF rectF = new RectF();
    private Path path = new Path();
    private float textSize;


    public TagView(Context context) {
        super(context);
        init(null);
    }

    public TagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = minSize;
        int desiredHeight = minSize;

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


    private void init(AttributeSet attributeSet) {

        if (attributeSet != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attributeSet,
                    R.styleable.TagView,
                    0, 0);

            try {
                tagText = a.getString(R.styleable.TagView_android_text);
                tagTextColor = a.getColor(R.styleable.TagView_android_textColor, Color.WHITE);
                fillColor = a.getColor(R.styleable.TagView_fillColor, Color.RED);
                textSize= a.getDimension(R.styleable.TagView_android_textSize,30);
            } finally {
                a.recycle();
            }
        }

        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setColor(fillColor);


        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(tagTextColor);
        textPaint.setTextSize(textSize);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        path.moveTo(0, 0);
        path.lineTo(width, 0);
        path.lineTo(width, height);
        path.lineTo(0, height);
        path.lineTo(width/10, height / 2);
        path.close();
        canvas.drawPath(path, linePaint);
        canvas.drawText(tagText,(width/2)+(width/10)-(textPaint.getTextSize()/2),(height/2)+(textPaint.getTextSize()/2),textPaint);



    }
}
