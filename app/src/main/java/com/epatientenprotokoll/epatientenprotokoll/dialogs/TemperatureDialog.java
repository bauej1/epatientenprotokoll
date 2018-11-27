package com.epatientenprotokoll.epatientenprotokoll.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.epatientenprotokoll.epatientenprotokoll.R;

public class TemperatureDialog {

    private Button bCancel, bSubmit, bUp, bDown;
    private TextView tValueInt, tValuePoint;
    private final Dialog dialog;

    private static final int minTemp = 30;
    private static final int maxTemp = 45;


    public TemperatureDialog(Activity a){
        dialog = new Dialog(a);
        showDialog();
    }

    public void showDialog(){
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_temperature);

        bCancel = dialog.findViewById(R.id.bCancel);
        bSubmit = dialog.findViewById(R.id.bSubmit);
        bUp = dialog.findViewById(R.id.bUp);
        bDown = dialog.findViewById(R.id.bDown);
        tValueInt = dialog.findViewById(R.id.tValueInt);
        tValuePoint = dialog.findViewById(R.id.tValuePoint);

        bCancel.setOnClickListener(event -> {
            closeDialog();
        });

        bUp.setOnClickListener(event -> {
            increaseIntegerTemperature();
        });

        bDown.setOnClickListener(event -> {
            decreaseIntegerTemperature();
        });

        bSubmit.setOnClickListener(event -> {

        });

        dialog.show();
    }

    private void closeDialog(){
        dialog.dismiss();
    }

    private void increaseIntegerTemperature(){
        int intTemp = Integer.parseInt(tValueInt.getText().toString());
        if(intTemp < maxTemp) ++intTemp;
        tValueInt.setText(intTemp + "");
    }

    private void decreaseIntegerTemperature(){
        int intTemp = Integer.parseInt(tValueInt.getText().toString());
        if(intTemp > minTemp) --intTemp;
        tValueInt.setText(intTemp + "");
    }
}
