package com.epatientenprotokoll.epatientenprotokoll.dialogs;

import android.app.Activity;
import android.widget.Button;
import com.epatientenprotokoll.epatientenprotokoll.R;

public class PainDialog extends MotherDialog {

    private Button bIntUp, bIntDown;

    private static final int minPain = 0;      //Minimal breath frequency
    private static final int maxPain = 10;     //Maximal breath frequency

    public PainDialog(Activity activity, int layoutId){
        super(activity, layoutId);

        bIntUp = dialog.findViewById(R.id.bIntUp);
        bIntDown = dialog.findViewById(R.id.bIntDown);

        bIntUp.setOnClickListener(event -> {
            increaseIntValue(maxPain);
        });

        bIntDown.setOnClickListener(event -> {
            decreaseIntValue(minPain);
        });

        bSubmit.setOnClickListener(event -> {
            super.submitData(tValueInt.getText().toString(), "");
        });
    }
}
