package com.epatientenprotokoll.epatientenprotokoll.dialogs;

import android.app.Activity;
import android.widget.Button;
import com.epatientenprotokoll.epatientenprotokoll.R;

public class Co2Dialog extends MotherDialog {

    private Button bIntUp, bIntDown;

    private static final int minCo2 = 0;      //Minimal breath frequency
    private static final int maxCo2 = 60;     //Maximal breath frequency

    public Co2Dialog(Activity activity, int layoutId){
        super(activity, layoutId);

        bIntUp = dialog.findViewById(R.id.bIntUp);
        bIntDown = dialog.findViewById(R.id.bIntDown);

        bIntUp.setOnClickListener(event -> {
            increaseIntValue(maxCo2);
        });

        bIntDown.setOnClickListener(event -> {
            decreaseIntValue(minCo2);
        });

        bSubmit.setOnClickListener(event -> {
            super.submitData(tValueInt.getText().toString(), "");
        });
    }
}
