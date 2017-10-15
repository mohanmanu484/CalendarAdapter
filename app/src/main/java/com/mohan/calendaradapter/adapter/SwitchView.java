package com.mohan.calendaradapter.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.mohan.calendaradapter.R;

/**
 * Created by mohang on 14/10/17.
 */

public class SwitchView extends View {

    Paint fillPaint =new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint switchPaint =new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF rectF=new RectF();
    private CheckedChangeListener checkedChangeListener;
    private float pos=1;
    private boolean checked;
    private int onFillColor=Color.YELLOW;
    private int offFillColor=Color.WHITE;
    private int onSwitchColor=Color.GRAY;
    private int offSwichColor=Color.BLUE;
    private final ValueAnimator onAnimator = ValueAnimator.ofFloat(1,1.5f,2,2.5f,3 );
    private final ValueAnimator offAnimator = ValueAnimator.ofFloat(3,2.5f,2f,1.5f,1);

    public static final String TAG = "SwitchView";





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
                    R.styleable.SwitchView,
                    0, 0);

            try {
                onFillColor = a.getColor(R.styleable.SwitchView_switchOnFillColor, Color.YELLOW);
                offFillColor = a.getColor(R.styleable.SwitchView_switchOffFillColor, Color.WHITE);
                onSwitchColor = a.getColor(R.styleable.SwitchView_switchOnFillColor, Color.GRAY);
                offSwichColor = a.getColor(R.styleable.SwitchView_switchOffFillColor, Color.BLUE);
                checked =a.getBoolean(R.styleable.SwitchView_checked,false);
                pos=checked?3:1;

            } finally {
                a.recycle();
            }
        }
        setOnTouchListener(onTouchListener);
        fillPaint.setStyle(Paint.Style.FILL);
        switchPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width=getMeasuredWidth();
        int height = getMeasuredHeight();

        Log.d(TAG, "onDraw: canvas width "+width);
        Log.d(TAG, "onDraw: canvas height "+height);

        rectF.set(0,0,width,height);
        fillPaint.setColor(checked ?offFillColor:onFillColor);
        switchPaint.setColor(checked ?offSwichColor:onSwitchColor);
        canvas.drawRoundRect(rectF,height/2,height/2, fillPaint);
        canvas.drawCircle((width/4)*pos,height/2,height/3, switchPaint);

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



    public void addCheckedChangeListener(CheckedChangeListener checkedChangeListener){
        this.checkedChangeListener=checkedChangeListener;
    }

    public interface CheckedChangeListener{
        void onCheckedChange(boolean checked);
    }

    OnTouchListener onTouchListener=new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()){

                case  MotionEvent.ACTION_DOWN :
                toggleState();
                Log.d(TAG, "onTouch: clicked");
                return true;

            }



            return false;
        }
    };

    private void toggleState() {
        ValueAnimator angelAnimator= checked ?offAnimator:onAnimator;
        checked =!checked;
        Log.d(TAG, "toggleState: "+checked);
        angelAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: "+value);
                pos=value;
               invalidate();
            }
        });
        angelAnimator.setDuration(300);
        angelAnimator.start();
    }
}
