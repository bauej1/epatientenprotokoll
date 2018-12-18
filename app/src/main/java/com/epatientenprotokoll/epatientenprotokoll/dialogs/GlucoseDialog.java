package com.epatientenprotokoll.epatientenprotokoll.dialogs;

import android.app.Activity;
import android.widget.Button;

import com.epatientenprotokoll.epatientenprotokoll.R;

public class GlucoseDialog extends MotherDialog {

    private Button b50ml, b100ml;
    private final double ML_100 = 100.0;
    private final double ML_50 = 50.0;


    public GlucoseDialog(Activity a, int layoutId) {
        super(a, layoutId);
        initControls();
    }

    protected void initControls(){

        b50ml = dialog.findViewById(R.id.b50ml);
        b100ml = dialog.findViewById(R.id.b100ml);

        b100ml.setOnClickListener(event -> {
            super.submitData(ML_100 + "", "");
        });

        b50ml.setOnClickListener(event -> {
            super.submitData(ML_50 + "", "");
        });
    }
}
