package com.next.view.react;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.next.R;

import static android.graphics.PorterDuff.Mode.CLEAR;

public class PorterDufferModeView extends View {

    public enum LocalMode {
        ADD,
        CLEAR,
        DARKEN,
        DST,
        DST_ATOP,
        DST_IN,
        DST_OUT,
        DST_OVER,
        LIGHTEN,
        MULTIPLY,
        OVERLAY,
        SCREEN,
        SRC,
        SRC_ATOP,
        SRC_IN,
        SRC_OUT,
        SRC_OVER,
        XOR;
    }
    private int mMode;
    private int mCircleRadio=30;
    private int mRectSize = 30;
    private int mCircleColor;
    private int mRectColor;
    private int mIndex;
    public PorterDufferModeView(Context context) {
        super(context);
    }

    public PorterDufferModeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.porter_duffer_mode_view_style);
        mCircleRadio = typedArray.getInt(R.styleable.porter_duffer_mode_view_style_circleRadio,20);
        mRectSize = typedArray.getInt(R.styleable.porter_duffer_mode_view_style_rectSize,30);
        mCircleColor = typedArray.getColor(R.styleable.porter_duffer_mode_view_style_srcColor, Color.RED);
        mRectColor = typedArray.getColor(R.styleable.porter_duffer_mode_view_style_dstColor, Color.BLACK);
        mMode = typedArray.getInt(R.styleable.porter_duffer_mode_view_style_mode, CLEAR.ordinal());
    }

    public void setMode(int mMode) {
        this.mMode = mMode;
        this.invalidate();
    }

    public void setCircleRadio(int mCircleRadio) {
        this.mCircleRadio = mCircleRadio;
        this.invalidate();
    }

    public void setRectSize(int mRectSize) {
        this.mRectSize = mRectSize;
        this.invalidate();
    }

    public void setCircleColor(int mCircleColor) {
        this.mCircleColor = mCircleColor;
        this.invalidate();
    }

    public void setRectColor(int mRectColor) {
        this.mRectColor = mRectColor;
        this.invalidate();
    }

    private PorterDuff.Mode getCurrentMode(){
        for(LocalMode lm: LocalMode.values()) {
            if(mMode == lm.ordinal()) {
                String mName = lm.toString();
                for (PorterDuff.Mode m : PorterDuff.Mode.values()) {
                    if (mName.equals(m.toString())) return m;
                }
            }
        }
        return CLEAR;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = this.getWidth()-200,height = this.getMeasuredHeight();
        Paint paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        canvas.drawColor(Color.BLUE);
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
        canvas.drawText(this.getCurrentMode().toString(),width/2,height/2-this.mCircleRadio-50,paint);
        int src  = canvas.saveLayer(0,0,this.getWidth(),this.getHeight(),null, Canvas.ALL_SAVE_FLAG);

        paint.setColor(this.mCircleColor);
        canvas.drawCircle(width/2,height/2,this.mCircleRadio,paint);

        paint.setXfermode(new PorterDuffXfermode(getCurrentMode()));
        paint.setColor(this.mRectColor);
        canvas.drawRect( width/2  , height/2 ,width/2 + this.mRectSize, height/2 + this.mRectSize,paint);

        paint.setXfermode(null);
        canvas.restoreToCount(src);
        paint.setColor(Color.WHITE);
        canvas.drawLine(0,height,getMeasuredWidth(),getMeasuredHeight(),paint);
    }
}
