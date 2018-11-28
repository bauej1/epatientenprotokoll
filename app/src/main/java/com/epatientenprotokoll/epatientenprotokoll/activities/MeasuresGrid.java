package com.epatientenprotokoll.epatientenprotokoll.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MeasuresGrid extends View {

    abstract static class OnClickListener {
        abstract void onClick(View view, int row, int column);
    }

    abstract static class OnDragListener {
        abstract void onDrag(View view, int columnStart, int columnEnd, int row);
    }

    private static final int maxYValue = 220;
    private static final int yAxisLabelDiff = 15;

    private int[][] table;
    private OnClickListener onClickListener;
    private OnDragListener onDragListener;
    private Paint paint = new Paint();
    private int ventilationStart;
    private int ventilationEnd;
    private int row;

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
        paint.setTextSize(50);
    }

    public void setTable(int[][] table) {
        this.table = table;

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

                int x = getWidth() / getColumnCount() * column + 4;
                int y = getHeight() / getRowCount() * (row + 1) - 4;

                if(table[row][column] > 1000){
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), table[row][column]);

                    if(bitmap == null) {
                        continue;
                    }
                    canvas.drawBitmap(bitmap, x, y, paint);
                } else if(table[row][column] > 0) {
                    String text = table[row][column] + "°C";        //here comes the evaluation which Stringadapter shoudl take place blabla

                    if(text == null){
                        continue;
                    }
                    canvas.drawText(text, x, y, paint);
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

            if (row < 0 || row >= getRowCount() || column < 0 || column >= getColumnCount()) {
                return false;
            }

            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                dragStartColumn = column;
            }

            if(event.getAction() == MotionEvent.ACTION_UP) {
                if(dragStartColumn != column) {
                    if (onDragListener != null) {
                        onDragListener.onDrag(this, dragStartColumn, column + 1, row);
                    }
                }
                else {
                    if (onClickListener != null) {
                        onClickListener.onClick(this, row, column);
                    }
                }
            }

            return true;
        }

        return false;
    }

    private int getRowCount() {
        return table.length;
    }

    private int getColumnCount() {
        return table[0].length;
    }

    private String getYGridValues(int column){
        return maxYValue - (column * 10) + "";
    }

    private String getXGridValues(){
        return "";
    }

    /**
     * Draws all horizontal lines in the grid.
     * @param canvas
     */
    private void drawHorizontalLines(Canvas canvas){
        for (int row = 1; row <= getRowCount(); ++row) {
            int y = getHeight() / getRowCount() * row;
            if(row <= getRowCount()-3){
                canvas.drawText(getYGridValues(row), 0, y - yAxisLabelDiff, paint);
            }
            if(row == getRowCount() - 3){
                paint.setStrokeWidth(5f);
            } else {
                paint.setStrokeWidth(2f);
            }
            canvas.drawLine(getHeight() / getRowCount() * 2, y, getWidth(), y, paint); //canvas.drawLine(0, y, getWidth(), y, paint);
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
