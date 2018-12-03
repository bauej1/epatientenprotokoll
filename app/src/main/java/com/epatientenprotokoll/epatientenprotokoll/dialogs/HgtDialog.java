package com.epatientenprotokoll.epatientenprotokoll.dialogs;

import android.app.Activity;
import android.widget.Button;
import com.epatientenprotokoll.epatientenprotokoll.R;

public class HgtDialog extends MotherDialog {

    private Button bIntUp, bIntDown;

    private static final int minHgt = 0;      //Minimal breath frequency
    private static final int maxHgt = 50;     //Maximal breath frequency

    public HgtDialog(Activity activity, int layoutId){
        super(activity, layoutId);

        bIntUp = dialog.findViewById(R.id.bIntUp);
        bIntDown = dialog.findViewById(R.id.bIntDown);

        bIntUp.setOnClickListener(event -> {
            increaseHgt();
        });

        bIntDown.setOnClickListener(event -> {
            decreaseHgt();
        });

        bSubmit.setOnClickListener(event -> {
            super.submitData(tValueInt.getText().toString(), "");
        });
    }

    private void increaseHgt(){
        increaseIntValue(maxHgt);
    }

    private void decreaseHgt(){
        decreaseIntValue(minHgt);
    }
}
