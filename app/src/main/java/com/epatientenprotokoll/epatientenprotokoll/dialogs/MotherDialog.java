package com.epatientenprotokoll.epatientenprotokoll.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.epatientenprotokoll.epatientenprotokoll.R;
import com.epatientenprotokoll.epatientenprotokoll.model.Tool;

/**
 * This class represents the super class of all underlying MotherDialog Subclasses for values like breath-frequency, pulsox, ...
 */
public class MotherDialog {

    private int layoutId;
    protected final Dialog dialog;
    protected Button bCancel, bSubmit, bIntUp, bIntDown;
    protected TextView tValueInt;

    public MotherDialog(Activity a, int layoutId){
        this.dialog = new Dialog(a);
        this.layoutId = layoutId;

        showDialog();

        bCancel = dialog.findViewById(R.id.bCancel);
        bSubmit = dialog.findViewById(R.id.bSubmit);
        bIntUp = dialog.findViewById(R.id.bIntUp);
        bIntDown = dialog.findViewById(R.id.bIntDown);
        tValueInt = dialog.findViewById(R.id.tValueInt);

        bCancel.setOnClickListener(event -> {
            closeDialog();
        });
    }

    protected void increaseIntValue(int max){
        int value = Integer.parseInt(tValueInt.getText().toString());
        if(value < max) ++value;
        tValueInt.setText(value + "");
    }

    protected void decreaseIntValue(int min){
        int value = Integer.parseInt(tValueInt.getText().toString());
        if(value > min) --value;
        tValueInt.setText(value + "");
    }

    protected void initControls(){
    }

    /**
     * Shows the motherDialog correctly on the tablet screen and initializes variables.
     */
    private void showDialog(){
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(layoutId);
        dialog.show();
    }

    /**
     * Closes the motherDialog when cancel is clicked by the user.
     */
    protected void closeDialog(){
        dialog.dismiss();
    }

    /**
     * Submits the data collected from the motherDialog and writes it into the grid-table.
     */
    protected void submitData(String tValueInt, String tValuePoint){
        double value = Double.parseDouble(tValueInt + "." + tValuePoint);
        Tool.getInstance().getCurrentTool().setStoredValue(value);
        closeDialog();
    }
}
