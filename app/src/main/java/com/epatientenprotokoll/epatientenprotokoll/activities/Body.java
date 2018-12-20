package com.epatientenprotokoll.epatientenprotokoll.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.epatientenprotokoll.epatientenprotokoll.R;
import com.epatientenprotokoll.epatientenprotokoll.model.Marker;

import java.util.ArrayList;

public class Body extends LinearLayout {

    private Paint paint = new Paint();
    private ImageButton ibBroken, ibPain, ibTrauma;
    private ImageView body;
    private Drawable currentMarker;
    private ArrayList<Marker> markers;
    private int currentId = 1;

    public Body(Context context) {
        super(context);
        init();
    }

    public Body(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Body(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setWillNotDraw(false);

        inflate(getContext(), R.layout.custom_body_view, this);

        ibBroken = findViewById(R.id.ibBroken);
        ibPain = findViewById(R.id.ibPain);
        ibTrauma = findViewById(R.id.ibTrauma);
        body = findViewById(R.id.imageBody);

        markers = new ArrayList<>();

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2f);
        paint.setTextSize(20);
        paint.setAntiAlias(true);

        ibBroken.setOnClickListener(event -> {
            setMarkerType(ibBroken.getDrawable());
        });

        ibPain.setOnClickListener(event -> {
            setMarkerType(ibPain.getDrawable());
        });

        ibTrauma.setOnClickListener(event -> {
            setMarkerType(ibTrauma.getDrawable());
        });

        body.setOnTouchListener((view, motionEvent) -> {

            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();

            createNewMarker(x,y);

            return false;
        });
    }

    private void createNewMarker(int x, int y){

        System.out.println("Body: Clicked on X: " + x + " Y: " + y);

        Marker newMark = new Marker(currentId++, x, y, currentMarker);
        markers.add(newMark);

        invalidate();
    }

    private void setMarkerType(Drawable drawable){
        currentMarker = drawable;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        System.out.println("Body: onDraw");

        drawMarkers(canvas);
    }

    private void drawMarkers(Canvas canvas){

        Bitmap baseBitmap = ((BitmapDrawable)body.getDrawable()).getBitmap();                                       //Bitmap of body
        Bitmap tempBitmap = Bitmap.createBitmap(body.getWidth(), body.getHeight(), Bitmap.Config.ARGB_4444);        //Empty Bitmap with size of body image to fill with markers
        Canvas tempCanvas = new Canvas(tempBitmap);                                                                 //new Canvas to draw the markers

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(baseBitmap, body.getWidth(), body.getHeight(), false);//Scaled Bitmap of body because otherwise it would not be the right size
        tempCanvas.drawBitmap(scaledBitmap, 0,0, paint);

        for(Marker m : markers){
            Bitmap symbol = ((BitmapDrawable)m.getMarker()).getBitmap();
            tempCanvas.drawBitmap(symbol, (float) m.getX(), (float) m.getY(), paint);
        }
        body.setImageBitmap(tempBitmap);
    }
}
