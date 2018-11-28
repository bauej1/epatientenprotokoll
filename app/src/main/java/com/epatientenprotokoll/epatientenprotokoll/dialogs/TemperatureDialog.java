package com.epatientenprotokoll.epatientenprotokoll.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.epatientenprotokoll.epatientenprotokoll.R;
import com.epatientenprotokoll.epatientenprotokoll.model.Tool;

/**
 * This Class represents the Dialog when the user wants to capture the body temperature.
 */
public class TemperatureDialog {

    private Button bCancel, bSubmit, bIntUp, bIntDown, bPointUp, bPointDown;
    private TextView tValueInt, tValuePoint;
    private final Dialog dialog;

    private static final int minTemp = 30;      //Minimal Body Temperature before comma
    private static final int maxTemp = 45;      //Maximal Body Temperature before comma
    private static final int maxCommaTemp = 9;  //Maximal Body Temperature after comma
    private static final int minCommaTemp = 0;  //Minimal Body Temperature after comma

    public TemperatureDialog(Activity a){
        dialog = new Dialog(a);
        showDialog();
    }

    /**
     * Shows the Dialog and handles all interactions with buttons.
     */
    public void showDialog(){
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_temperature);

        bCancel = dialog.findViewById(R.id.bCancel);
        bSubmit = dialog.findViewById(R.id.bSubmit);
        bIntUp = dialog.findViewById(R.id.bIntUp);
        bIntDown = dialog.findViewById(R.id.bIntDown);
        bPointUp = dialog.findViewById(R.id.bPointUp);
        bPointDown = dialog.findViewById(R.id.bPointDown);
        tValueInt = dialog.findViewById(R.id.tValueInt);
        tValuePoint = dialog.findViewById(R.id.tValuePoint);

        bCancel.setOnClickListener(event -> {
            closeDialog();
        });

        bIntUp.setOnClickListener(event -> {
            increaseIntegerTemperature();
        });

        bIntDown.setOnClickListener(event -> {
            decreaseIntegerTemperature();
        });

        bPointUp.setOnClickListener(event -> {
            increasePointTemperature();
        });

        bPointDown.setOnClickListener(event -> {
            decreasePointTemperature();
        });

        bSubmit.setOnClickListener(event -> {
            submitData();
        });

        dialog.show();
    }

    /**
     * Closes dialog
     */
    private void closeDialog(){
        dialog.dismiss();
    }

    /**
     * Increases the body temperature before the comma by one.
     */
    private void increaseIntegerTemperature(){
        int intTemp = Integer.parseInt(tValueInt.getText().toString());
        if(intTemp < maxTemp) ++intTemp;
        tValueInt.setText(intTemp + "");
    }

    /**
     * Decreases the body temperature before the comma by one.
     */
    private void decreaseIntegerTemperature(){
        int intTemp = Integer.parseInt(tValueInt.getText().toString());
        if(intTemp > minTemp) --intTemp;
        tValueInt.setText(intTemp + "");
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

    /**
     * Submits the captured body temperature and sets it into the model of the current used tool.
     */
    private void submitData(){
        double value = Double.parseDouble(tValueInt.getText() + "." + tValuePoint.getText());
        Tool.getInstance().getCurrentTool().setValue(value);
        dialog.dismiss();
    }
}
