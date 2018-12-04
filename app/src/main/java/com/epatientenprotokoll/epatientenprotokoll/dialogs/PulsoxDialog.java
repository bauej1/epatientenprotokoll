package com.epatientenprotokoll.epatientenprotokoll.dialogs;

import android.app.Activity;
import android.widget.Button;
import com.epatientenprotokoll.epatientenprotokoll.R;

public class PulsoxDialog extends MotherDialog {

    private Button bIntUp, bIntDown;

    private static final int minPulsox = 0;      //Minimal breath frequency
    private static final int maxPulsox = 100;     //Maximal breath frequency

    public PulsoxDialog(Activity activity, int layoutId){
        super(activity, layoutId);

        bIntUp = dialog.findViewById(R.id.bIntUp);
        bIntDown = dialog.findViewById(R.id.bIntDown);

        bIntUp.setOnClickListener(event -> {
            increaseIntValue(maxPulsox);
        });

        bIntDown.setOnClickListener(event -> {
            decreaseIntValue(minPulsox);
        });

        bSubmit.setOnClickListener(event -> {
            super.submitData(tValueInt.getText().toString(), "");
        });
    }
}
