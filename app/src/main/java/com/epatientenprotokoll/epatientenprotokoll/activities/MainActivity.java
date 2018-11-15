package com.epatientenprotokoll.epatientenprotokoll.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ToggleButton;
import com.epatientenprotokoll.epatientenprotokoll.R;
import com.epatientenprotokoll.epatientenprotokoll.fragments.MasterDataFragment;
import com.epatientenprotokoll.epatientenprotokoll.fragments.MaterialFragment;
import com.epatientenprotokoll.epatientenprotokoll.fragments.StatusFragment;

public class MainActivity extends AppCompatActivity {

    ToggleButton tbMasterData;
    ToggleButton tbStatus;
    ToggleButton tbMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbMasterData = findViewById(R.id.tbMasterData);
        tbStatus = findViewById(R.id.tbStatus);
        tbMaterial = findViewById(R.id.tbMaterial);

        tbMasterData.setOnClickListener(i -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.layout_placeholder, new MasterDataFragment());
            transaction.commit();
        });

        tbStatus.setOnClickListener(i -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.layout_placeholder, new StatusFragment());
            transaction.commit();
        });

        tbMaterial.setOnClickListener(i -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.layout_placeholder, new MaterialFragment());
            transaction.commit();
        });
    }

}
