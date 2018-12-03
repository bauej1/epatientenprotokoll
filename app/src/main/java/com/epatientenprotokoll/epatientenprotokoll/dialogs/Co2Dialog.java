package com.epatientenprotokoll.epatientenprotokoll.dialogs;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import com.epatientenprotokoll.epatientenprotokoll.R;

public class Co2Dialog extends MotherDialog {

    private TextView tValueInt;
    private Button bIntUp, bIntDown;

    private static final int minCo2 = 0;      //Minimal breath frequency
    private static final int maxCo2 = 60;     //Maximal breath frequency

    public Co2Dialog(Activity activity, int layoutId){
        super(activity, layoutId);

        tValueInt = dialog.findViewById(R.id.tValueInt);
        bIntUp = dialog.findViewById(R.id.bIntUp);
        bIntDown = dialog.findViewById(R.id.bIntDown);

        bIntUp.setOnClickListener(event -> {
            increaseCo2();
        });

        bIntDown.setOnClickListener(event -> {
            decreaseCo2();
        });

        bSubmit.setOnClickListener(event -> {
            super.submitData(tValueInt.getText().toString(), "");
        });
    }

    private void increaseCo2(){
        int freq = Integer.parseInt(tValueInt.getText().toString());
        if(freq < maxCo2) ++freq;
        tValueInt.setText(freq + "");
    }

    private void decreaseCo2(){
        int freq = Integer.parseInt(tValueInt.getText().toString());
        if(freq > minCo2) --freq;
        tValueInt.setText(freq + "");
    }
}
