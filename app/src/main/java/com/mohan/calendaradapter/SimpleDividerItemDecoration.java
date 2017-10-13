package com.mohan.calendaradapter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
	private Paint mPaint;
	private final static int OFFSET = 2;

	public SimpleDividerItemDecoration(){
		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);
		/*final float thickness = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				2, context.getResources().getDisplayMetrics());*/
		mPaint.setStrokeWidth(2.0f);
	}


	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();

		// we want to retrieve the position in the list
		final int position = params.getViewAdapterPosition();

		// and add a separator to any view but the last one
		outRect.set((int) mPaint.getStrokeWidth(), (int) mPaint.getStrokeWidth(), (int) mPaint.getStrokeWidth(), (int) mPaint.getStrokeWidth()); // left, top, right, bottom
		/*if (position < state.getItemCount()) {
			//outRect.set((int) mPaint.getStrokeWidth(), (int) mPaint.getStrokeWidth(), (int) mPaint.getStrokeWidth(), (int) mPaint.getStrokeWidth()); // left, top, right, bottom
		} else {
			outRect.setEmpty(); // 0, 0, 0, 0
		}*/
	}



	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		// we set the stroke width before, so as to correctly draw the line we have to offset by width / 2
		final int offset = (int) (mPaint.getStrokeWidth() / 2);

		// this will iterate over every visible view
		for (int i = 0; i < parent.getChildCount(); i++) {
			// get the view
			final View view = parent.getChildAt(i);
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();

			// get the position
			final int position = params.getViewAdapterPosition();

			// and finally draw the separator
			if (position < state.getItemCount()) {
				c.drawLine(view.getLeft(), view.getBottom() + offset, view.getRight(), view.getBottom() + offset, mPaint);
			}
		}
	}
}