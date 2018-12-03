package com.epatientenprotokoll.epatientenprotokoll.dialogs;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import com.epatientenprotokoll.epatientenprotokoll.R;

public class PainDialog extends MotherDialog {

    private TextView tValueInt;
    private Button bIntUp, bIntDown;

    private static final int minPain = 0;      //Minimal breath frequency
    private static final int maxPain = 10;     //Maximal breath frequency

    public PainDialog(Activity activity, int layoutId){
        super(activity, layoutId);

        tValueInt = dialog.findViewById(R.id.tValueInt);
        bIntUp = dialog.findViewById(R.id.bIntUp);
        bIntDown = dialog.findViewById(R.id.bIntDown);

        bIntUp.setOnClickListener(event -> {
            increasePain();
        });

        bIntDown.setOnClickListener(event -> {
            decreasePain();
        });

        bSubmit.setOnClickListener(event -> {
            super.submitData(tValueInt.getText().toString(), "");
        });
    }

    private void increasePain(){
        int freq = Integer.parseInt(tValueInt.getText().toString());
        if(freq < maxPain) ++freq;
        tValueInt.setText(freq + "");
    }

    private void decreasePain(){
        int freq = Integer.parseInt(tValueInt.getText().toString());
        if(freq > minPain) --freq;
        tValueInt.setText(freq + "");
    }
}
