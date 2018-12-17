package com.epatientenprotokoll.epatientenprotokoll.dialogs;

import android.app.Activity;
import android.widget.Button;

import com.epatientenprotokoll.epatientenprotokoll.R;

public class AdrenalinDialog extends MotherDialog {

    private Button b5ml, b1ml;
    private final double ML_1 = 1.0;
    private final double ML_5 = 5.0;


    public AdrenalinDialog(Activity a, int layoutId) {
        super(a, layoutId);
        initControls();
    }

    protected void initControls(){

        b5ml = dialog.findViewById(R.id.b5ml);
        b1ml = dialog.findViewById(R.id.b1ml);

        b1ml.setOnClickListener(event -> {
            super.submitData(ML_1 + "", "");
        });

        b5ml.setOnClickListener(event -> {
            super.submitData(ML_5 + "", "");
        });
    }
}
