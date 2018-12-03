package com.epatientenprotokoll.epatientenprotokoll.dialogs;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import com.epatientenprotokoll.epatientenprotokoll.R;

public class PulsoxDialog extends MotherDialog {

    private TextView tValueInt;
    private Button bIntUp, bIntDown;

    private static final int minPulsox = 0;      //Minimal breath frequency
    private static final int maxPulsox = 100;     //Maximal breath frequency

    public PulsoxDialog(Activity activity, int layoutId){
        super(activity, layoutId);

        tValueInt = dialog.findViewById(R.id.tValueInt);
        bIntUp = dialog.findViewById(R.id.bIntUp);
        bIntDown = dialog.findViewById(R.id.bIntDown);

        bIntUp.setOnClickListener(event -> {
            increasePulsox();
        });

        bIntDown.setOnClickListener(event -> {
            decreasePulsox();
        });

        bSubmit.setOnClickListener(event -> {
            super.submitData(tValueInt.getText().toString(), "");
        });
    }

    private void increasePulsox(){
        int freq = Integer.parseInt(tValueInt.getText().toString());
        if(freq < maxPulsox) ++freq;
        tValueInt.setText(freq + "");
    }

    private void decreasePulsox(){
        int freq = Integer.parseInt(tValueInt.getText().toString());
        if(freq > minPulsox) --freq;
        tValueInt.setText(freq + "");
    }
}
