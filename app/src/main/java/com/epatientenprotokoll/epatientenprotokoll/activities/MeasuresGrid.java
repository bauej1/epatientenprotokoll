package com.epatientenprotokoll.epatientenprotokoll.activities;

import android.content.Context;
import android.drm.DrmStore;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.epatientenprotokoll.epatientenprotokoll.R;
import com.epatientenprotokoll.epatientenprotokoll.model.ActionMeasurement;
import com.epatientenprotokoll.epatientenprotokoll.model.DrugMeasurement;
import com.epatientenprotokoll.epatientenprotokoll.model.Measurement;
import com.epatientenprotokoll.epatientenprotokoll.model.Tool;
import com.epatientenprotokoll.epatientenprotokoll.model.ValueMeasurement;

public class MeasuresGrid extends View {

    abstract static class OnClickListener {
        abstract void onClick(View view, int row, int column);
    }

    abstract static class OnDragListener {
        abstract void onDrag(View view, int columnStart, int columnEnd, int rowStart, int rowEnd);
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
    private int rowEnd;

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

    public void setVentilation(int ventilationStart, int ventilationEnd, int rowStart, int rowEnd) {
        this.ventilationStart = ventilationStart;
        this.ventilationEnd = ventilationEnd;
        this.row = rowStart;
        this.rowEnd = rowEnd;
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
        //drawVentilation(canvas);
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

                if(table[row][column] instanceof ActionMeasurement){                                                        //Action Measurements

                    if(table[row][column].isMultiMeasure()){
                        if(table[row][column].getId() == 1){
                            drawVentilation(canvas, table[row][column].getX1(), table[row][column].getX2(), table[row][column].getY1());
                        } else {
                            drawDragLine(canvas, table[row][column]);
                        }
                    } else {
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), (int)table[row][column].getStoredValue());
                        if(bitmap != null) canvas.drawBitmap(bitmap, x, y, paint);
                    }

                } else if(table[row][column] instanceof ValueMeasurement) {                                                //Value Measurements

                    String text = table[row][column].getStoredValue() + table[row][column].getUnit();
                    if(text != null) canvas.drawText(text, x, y + (getHeight() / getRowCount()) - 10, paint);   //+ (getHeight() / getRowCount()) - 10 is to align the text in the middle of the cell

                } else if(table[row][column] instanceof DrugMeasurement){                                                  //Drug Measurements

                    String text = table[row][column].getDrugName() + ": " + table[row][column].getStoredValue() + table[row][column].getUnit();
                    if(text != null) canvas.drawText(text, x, y + (getHeight() / getRowCount()) - 10, paint);   //+ (getHeight() / getRowCount()) - 10 is to align the text in the middle of the cell
                }
            }
        }
    }

    private void drawVentilation(Canvas canvas, int columnStart, int columnEnd, int row) {
        if(columnEnd == 0) {
            return;
        }

        System.out.println("row : " + row);
        boolean upwards = true;

        //Start-Bullet
        canvas.drawCircle(getWidth() / getColumnCount() * columnStart, getHeight() / getRowCount() * (upwards ? row : row + 1), 8, paint);

        //Ventilation-Line
        for(int column = columnStart; column < columnEnd; ++column) {
            int startX = getWidth() / getColumnCount() * column;
            int endX = getWidth() / getColumnCount() * (column + 1);
            int startY = getHeight() / getRowCount() * (upwards ? row : row + 1);
            int endY = getHeight() / getRowCount() * (upwards ? row + 1 : row);
            canvas.drawLine(startX, startY, endX, endY, paint);

            upwards = !upwards;
        }

        //End-Bullet
        canvas.drawCircle(getWidth() / getColumnCount() * columnEnd, getHeight() / getRowCount() * (upwards ? row : row + 1), 8, paint);
    }

    private void drawDragLine(Canvas canvas, Measurement m){
        Bitmap bitmap;
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.arrowdown);
        boolean bloodPressureActive = false;
        int columnStart = m.getX1();
        int columnEnd = m.getX2();
        int rowStart = m.getY1();
        int rowEnd = m.getY2();

        switch(m.getId()){
            case 5: //heart massage
                bitmap = BitmapFactory.decodeResource(getResources(), (int)m.getStoredValue());
                break;
            case 6: //blood pressure
                bloodPressureActive = true;
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.arrowup);
                break;
            default:
                bitmap = null;
        }

        if(bitmap != null){
            if(bloodPressureActive){
                canvas.drawBitmap(bitmap,getWidth() / getColumnCount() * columnStart,  getHeight() / getRowCount() * rowStart, paint);
                canvas.drawBitmap(bitmap2, getWidth() / getColumnCount() * columnEnd, getHeight() / getRowCount() * rowEnd, paint);
            } else {
                canvas.drawBitmap(bitmap,getWidth() / getColumnCount() * columnStart,  getHeight() / getRowCount() * rowStart, paint);
                canvas.drawBitmap(bitmap, getWidth() / getColumnCount() * columnEnd, getHeight() / getRowCount() * rowEnd, paint);
            }
        }
    }

    int dragStartColumn = -1;
    int dragStartRow = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_UP) {
            int row = (int) (event.getY() / getHeight() * getRowCount());
            int column = (int) (event.getX() / getWidth() * getColumnCount());

            if (row < 0 || row >= getRowCount() || column < 2 || column >= getColumnCount()) {
                return false;
            }

            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    dragStartColumn = column;
                    dragStartRow = row;
                    longClickRow = row;
                    longClickColumn = column;
                    longClickView = this;
                    handler.postDelayed(mLongPressed, ViewConfiguration.getLongPressTimeout());
                    break;
                case MotionEvent.ACTION_UP:
                    handler.removeCallbacks(mLongPressed);
                    if(dragStartColumn != column || dragStartRow != row) {
                        if (onDragListener != null) {
                            onDragListener.onDrag(this, dragStartColumn, column, dragStartRow , row );
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
