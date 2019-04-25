package com.bashizip.badassview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class SimpleView extends View {

    private Paint paint;
    private float mRadius;
    private int mColor;

    public SimpleView(Context context) {
        super(context);
        init(null);
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
        invalidate();
        requestLayout();
    }

    public void setmRadius(float mRadius) {
        this.mRadius = mRadius;
        invalidate();
        requestLayout();
    }

    private void init(@Nullable AttributeSet set) {

        paint = new Paint();

        if (set != null) {

            TypedArray typedArray = getContext().obtainStyledAttributes(R.styleable.SimpleView);
            mColor = typedArray.getColor(R.styleable.SimpleView_mColor, Color.BLUE);
            mRadius = typedArray.getFloat(R.styleable.SimpleView_mRadius, 100.0f);

            paint.setColor(mColor);
            paint.setAntiAlias(true);

            typedArray.recycle();
        }

    }


    public SimpleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SimpleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SimpleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        float cx = getWidth() / 2;
        float cy = getHeight() / 2;

        canvas.drawCircle(cx, cy, mRadius, paint);

        invalidate();

    }
}
