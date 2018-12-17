package com.epatientenprotokoll.epatientenprotokoll.dialogs;

import android.app.Activity;
import android.widget.Button;

import com.epatientenprotokoll.epatientenprotokoll.R;

public class FentanylDialog extends MotherDialog {

    private Button b2ml, b10ml;
    private final double ML_2 = 2.0;
    private final double ML_10 = 10.0;


    public FentanylDialog(Activity a, int layoutId) {
        super(a, layoutId);
        initControls();
    }

    protected void initControls(){

        b2ml = dialog.findViewById(R.id.b2ml);
        b10ml = dialog.findViewById(R.id.b10ml);

        b10ml.setOnClickListener(event -> {
            super.submitData(ML_10 + "", "");
        });

        b2ml.setOnClickListener(event -> {
            super.submitData(ML_2 + "", "");
        });
    }
}
