package com.epatientenprotokoll.epatientenprotokoll.activities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class StaticTimeline extends View {

    private Paint paint = new Paint();
    private static final int numberOfGridFields = 14;
    private static final int timelineSteps = 3;

    public StaticTimeline(Context context) {
        super(context);
        init();
    }

    public StaticTimeline(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StaticTimeline(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2f);
        paint.setTextSize(20);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTimeline(canvas);
    }

    private void drawTimeline(Canvas canvas){

        int squareWidth = getWidth() / numberOfGridFields;

        //draw horizontal line
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, paint);

        //draw arrow head
        canvas.drawLine(getWidth(), getHeight() / 2, getWidth() - 15, getHeight() / 2 + 10, paint);
        canvas.drawLine(getWidth(), getHeight() / 2, getWidth() - 15, getHeight() / 2 - 10, paint);

        //draw timeline steps
        int x = 2 * squareWidth;
        int timeLabel = timelineSteps;

        while(x < getWidth() - squareWidth){
            canvas.drawLine(x, getHeight() / 2, x, getHeight() / 2 + 10, paint);
            canvas.drawLine(x, getHeight() / 2, x, getHeight() / 2 - 10, paint);
            canvas.drawText(timeLabel + "",x + squareWidth / 2 - 5, getHeight() / 2 - 5, paint);
            x += squareWidth;
            timeLabel += 3;
        }
    }
}
