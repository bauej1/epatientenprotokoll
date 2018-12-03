package com.epatientenprotokoll.epatientenprotokoll.dialogs;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import com.epatientenprotokoll.epatientenprotokoll.R;

/**
 * This Class represents the MotherDialog when the user wants to capture the body temperature.
 */
public class TemperatureDialog extends MotherDialog {

    private Button bIntUp, bIntDown, bPointUp, bPointDown;
    private TextView tValuePoint;

    private static final int minTemp = 30;      //Minimal Body Temperature before comma
    private static final int maxTemp = 45;      //Maximal Body Temperature before comma
    private static final int maxCommaTemp = 9;  //Maximal Body Temperature after comma
    private static final int minCommaTemp = 0;  //Minimal Body Temperature after comma

    public TemperatureDialog(Activity activity, int layoutId){
        super(activity, layoutId);

        bIntDown = dialog.findViewById(R.id.bIntDown);
        bIntUp = dialog.findViewById(R.id.bIntUp);
        bPointDown = dialog.findViewById(R.id.bPointDown);
        bPointUp = dialog.findViewById(R.id.bPointUp);
        tValuePoint = dialog.findViewById(R.id.tValuePoint);

        bIntDown.setOnClickListener(event -> {
            decreaseIntegerTemperature();
        });

        bIntUp.setOnClickListener(event -> {
            increaseIntegerTemperature();
        });

        bPointDown.setOnClickListener(event -> {
            decreasePointTemperature();
        });

        bPointUp.setOnClickListener(event -> {
            increasePointTemperature();
        });

        bSubmit.setOnClickListener(event -> {
            super.submitData(tValueInt.getText().toString(), tValuePoint.getText().toString());
        });
    }


    /**
     * Increases the body temperature before the comma by one.
     */
    private void increaseIntegerTemperature(){
        increaseIntValue(maxTemp);
    }

    /**
     * Decreases the body temperature before the comma by one.
     */
    private void decreaseIntegerTemperature(){
        decreaseIntValue(minTemp);
    }

    /**
     * Increases the body temperature after the comma by one.
     */
    private void increasePointTemperature(){
        int pointTemp = Integer.parseInt(tValuePoint.getText().toString());
        if(pointTemp < maxCommaTemp){
            ++pointTemp;
            tValuePoint.setText(pointTemp + "");
        } else {
            increaseIntegerTemperature();
            tValuePoint.setText(minCommaTemp + "");
        }
    }

    /**
     * Decreases the body temperature after the comma by one.
     */
    private void decreasePointTemperature(){
        int pointTemp = Integer.parseInt(tValuePoint.getText().toString());
        if(pointTemp > minCommaTemp){
            --pointTemp;
            tValuePoint.setText(pointTemp + "");
        } else {
            decreaseIntegerTemperature();
            tValuePoint.setText(maxCommaTemp + "");
        }

    }
}
