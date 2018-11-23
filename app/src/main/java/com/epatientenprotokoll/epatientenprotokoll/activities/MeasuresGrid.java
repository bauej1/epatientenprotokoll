package com.epatientenprotokoll.epatientenprotokoll.activities;

import android.content.Context;
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
        abstract void onDrag(View view, int columnStart, int columnEnd);
    }

    String[][] table;
    OnClickListener onClickListener;
    OnDragListener onDragListener;
    Paint paint = new Paint();
    int ventilationStart;
    int ventilationEnd;

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

    public void setTable(String[][] table) {
        this.table = table;

        invalidate();
    }

    public void setVentilation(int ventilationStart, int ventilationEnd) {
        this.ventilationStart = ventilationStart;
        this.ventilationEnd = ventilationEnd;
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
        for (int row = 0; row <= getRowCount(); ++row) {
            int y = getHeight() / getRowCount() * row;
            canvas.drawLine(0, y, getWidth(), y, paint);
        }

        for (int column = 0; column <= getColumnCount(); ++column) {
            int x = getWidth() / getColumnCount() * column;
            canvas.drawLine(x, 0, x, getHeight(), paint);
        }
    }

    private void drawCells(Canvas canvas) {
        for (int row = 0; row < getRowCount(); ++row) {
            for (int column = 0; column < getColumnCount(); ++column) {
                String text = table[row][column];

                if(text == null) {
                    continue;
                }

                int x = getWidth() / getColumnCount() * column + 4;
                int y = getHeight() / getRowCount() * (row + 1) - 4;

                canvas.drawText(text, x, y, paint);
            }
        }
    }

    private void drawVentilation(Canvas canvas) {
        if(ventilationEnd == 0) {
            return;
        }

        int row = 3;
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
                        onDragListener.onDrag(this, dragStartColumn, column + 1);
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
}
