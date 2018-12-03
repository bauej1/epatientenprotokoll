package com.epatientenprotokoll.epatientenprotokoll.dialogs;

import android.app.Activity;
import android.widget.Button;
import com.epatientenprotokoll.epatientenprotokoll.R;

public class BreathFrequencyDialog extends MotherDialog {

    private Button bIntUp, bIntDown;

    private static final int minBreathFrequency = 0;      //Minimal breath frequency
    private static final int maxBreathFrequency = 90;     //Maximal breath frequency

    public BreathFrequencyDialog(Activity activity, int layoutId){
        super(activity, layoutId);
        initControls();
    }

    protected void initControls(){
        bIntUp = dialog.findViewById(R.id.bIntUp);
        bIntDown = dialog.findViewById(R.id.bIntDown);

        bIntUp.setOnClickListener(event -> {
            increaseBreathFrequency();
        });

        bIntDown.setOnClickListener(event -> {
            decreaseBreathFrequency();
        });

        bSubmit.setOnClickListener(event -> {
            super.submitData(tValueInt.getText().toString(), "");
        });
    }

    private void increaseBreathFrequency(){
        increaseIntValue(maxBreathFrequency);
    }

    private void decreaseBreathFrequency(){
        decreaseIntValue(minBreathFrequency);
    }
}
