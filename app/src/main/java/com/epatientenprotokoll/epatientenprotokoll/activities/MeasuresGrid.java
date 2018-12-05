package com.epatientenprotokoll.epatientenprotokoll.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.epatientenprotokoll.epatientenprotokoll.model.ActionMeasurement;
import com.epatientenprotokoll.epatientenprotokoll.model.Measurement;
import com.epatientenprotokoll.epatientenprotokoll.model.ValueMeasurement;

public class MeasuresGrid extends View {

    abstract static class OnClickListener {
        abstract void onClick(View view, int row, int column);
    }

    abstract static class OnDragListener {
        abstract void onDrag(View view, int columnStart, int columnEnd, int row);
    }

    abstract static class OnLongClickListener {
        abstract void onLongClick(View view, int row, int column);
    }

    //Constants
    private static final int maxYValue = 220;
    private static final int yAxisLabelDiff = 15;

    //Common
    private Measurement[][] table;
    private OnClickListener onClickListener;
    private OnDragListener onDragListener;
    private OnLongClickListener onLongClickListener;
    private Paint paint = new Paint();

    //Drag
    private int ventilationStart;
    private int ventilationEnd;
    private int row;

    //LongClick
    private final Handler handler = new Handler();
    private int longClickRow;
    private int longClickColumn;
    private View longClickView;
    public boolean isLongClick = false;

    public MeasuresGrid(Context context) {
        super(context);
        init();
    }

    public MeasuresGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MeasuresGrid(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2f);
        paint.setTextSize(30);
        paint.setAntiAlias(true);
    }

    public void setTable(Measurement[][] table) {
        this.table = table;

        invalidate();
    }

    public void deleteMeasurement(int row, int column){

        if(this.table.length < 0 || this.table[row][column] == null){
            return;
        }
        this.table[row][column] = null;
        invalidate();
    }

    public void setVentilation(int ventilationStart, int ventilationEnd, int row) {
        this.ventilationStart = ventilationStart;
        this.ventilationEnd = ventilationEnd;
        this.row = row;
    }

    void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    void setOnDragListener(OnDragListener onDragListener) {
        this.onDragListener = onDragListener;
    }

    void setOnLongClickListener(OnLongClickListener onLongClickListener){
        this.onLongClickListener = onLongClickListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (table == null) {
            return;
        }

        drawGrid(canvas);
        drawCells(canvas);
        drawVentilation(canvas);
    }

    private void drawGrid(Canvas canvas) {
        drawHorizontalLines(canvas);
        drawVerticalLines(canvas);
    }

    private void drawCells(Canvas canvas) {
        for (int row = 0; row < getRowCount(); ++row) {
            for (int column = 0; column < getColumnCount(); ++column) {

                int x = getWidth() / getColumnCount() * column;
                int y = getHeight() / getRowCount() * row;

                if(table[row][column] instanceof ActionMeasurement){

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), (int)table[row][column].getStoredValue());
                    if(bitmap != null) canvas.drawBitmap(bitmap, x, y, paint);

                } else if(table[row][column] instanceof ValueMeasurement) {

                    String text = table[row][column].getStoredValue() + table[row][column].getUnit();
                    if(text != null) canvas.drawText(text, x, y + (getHeight() / getRowCount()) - 10, paint);   //+ (getHeight() / getRowCount()) - 10 is to align the text in the middle of the cell
                }
            }
        }
    }

    private void drawVentilation(Canvas canvas) {
        if(ventilationEnd == 0) {
            return;
        }

        int row = this.row;
        System.out.println("row : " + row);
        boolean upwards = true;

        for(int column = ventilationStart; column < ventilationEnd; ++column) {
            int startX = getWidth() / getColumnCount() * column;
            int endX = getWidth() / getColumnCount() * (column + 1);
            int startY = getHeight() / getRowCount() * (upwards ? row : row + 1);
            int endY = getHeight() / getRowCount() * (upwards ? row + 1 : row);
            canvas.drawLine(startX, startY, endX, endY, paint);

            upwards = !upwards;
        }
    }

    int dragStartColumn = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_UP) {
            int row = (int) (event.getY() / getHeight() * getRowCount());
            int column = (int) (event.getX() / getWidth() * getColumnCount());

            System.out.println("klick row " + row);

            if (row < 0 || row >= getRowCount() || column < 2 || column >= getColumnCount()) {
                return false;
            }

            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    dragStartColumn = column;
                    longClickRow = row;
                    longClickColumn = column;
                    longClickView = this;
                    handler.postDelayed(mLongPressed, ViewConfiguration.getLongPressTimeout());
                    break;
                case MotionEvent.ACTION_UP:
                    handler.removeCallbacks(mLongPressed);
                    if(dragStartColumn != column) {
                        if (onDragListener != null) {
                            onDragListener.onDrag(this, dragStartColumn, column + 1, row);
                        }
                    }
                    else {
                        if (onClickListener != null && !isLongClick) {
                            onClickListener.onClick(this, row, column);
                        }
                    }
                    break;
            }
            if(isLongClick) isLongClick = false;

            return true;
        }

        return false;
    }

    Runnable mLongPressed = new Runnable() {
        @Override
        public void run() {
            isLongClick = true;
            onLongClickListener.onLongClick(longClickView, longClickRow, longClickColumn);
        }
    };

    private int getRowCount() {
        return table.length - 1;
    }

    private int getColumnCount() {
        return table[0].length - 1;
    }

    private String getYGridValues(int row){
        return maxYValue - (row * 10) + "";
    }

    private String getXGridValues(){
        return "";
    }

    /**
     * Draws all horizontal lines in the grid.
     * @param canvas
     */
    private void drawHorizontalLines(Canvas canvas){
        for (int row = 0; row <= getRowCount(); ++row) {
            int y = getHeight() / getRowCount() * row;
            if(row <= getRowCount()-3){
                canvas.drawText(getYGridValues(row), 0, y - yAxisLabelDiff, paint);
            }
            if(row == getRowCount() - 3){
                paint.setStrokeWidth(5f);
            } else {
                paint.setStrokeWidth(2f);
            }
            canvas.drawLine(getHeight() / getRowCount() * 2, y, getWidth(), y, paint);
            //canvas.drawLine(0, y, getWidth(), y, paint);
        }
    }

    /**
     * Draws all vertical lines in the grid.
     * @param canvas
     */
    private void drawVerticalLines(Canvas canvas){
        for (int column = 2; column <= getColumnCount(); ++column) {
            int x = getWidth() / getColumnCount() * column;
            canvas.drawLine(x, 0, x, getHeight(), paint);
        }
    }
}
