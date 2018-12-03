package com.epatientenprotokoll.epatientenprotokoll.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.epatientenprotokoll.epatientenprotokoll.R;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * This class contains all controls to collect the data for used materials during the mission and how the patient is positionned.
 * It handles also all user interaction with the controls.
 *
 * @author
 *
 */
public class MaterialFragment extends Fragment{

    MultiStateToggleButton mstbMaterial1;
    MultiStateToggleButton mstbMaterial2;
    MultiStateToggleButton mstbHalskragen;
    MultiStateToggleButton mstbRettungsbrett;
    MultiStateToggleButton mstbWundversorgung;
    MultiStateToggleButton mstbAbgegebenesMaterial;
    MultiStateToggleButton mstbSeitenlage;
    List<String> materialArray1;
    List<String> materialArray2;
    List<String> halskragenArray;
    List<String> rettungsbrettArray;
    List<String> wundversorgungArray;
    List<String> abgegebenesMaterialArray;
    List<String> seitenlageArray;
    android.widget.ToggleButton tbSeitenlage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_material, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Assigns all values to view
        mstbMaterial1 = getView().findViewById(R.id.mstb_material1);
        mstbMaterial2 = getView().findViewById(R.id.mstb_material2);
        mstbHalskragen = getView().findViewById(R.id.mstb_halskragen);
        mstbRettungsbrett = getView().findViewById(R.id.mstb_rettungsbrett);
        mstbWundversorgung = getView().findViewById(R.id.mstb_wundversorgung);
        mstbAbgegebenesMaterial = getView().findViewById(R.id.mstb_abgegebenMaterial);
        mstbSeitenlage = getView().findViewById(R.id.mstb_Seitenlage);
        materialArray1 = Arrays.asList(getResources().getStringArray(R.array.material_array1));
        materialArray2 = Arrays.asList(getResources().getStringArray(R.array.material_array2));
        halskragenArray = Arrays.asList(getResources().getStringArray(R.array.halskragen_array));
        rettungsbrettArray = Arrays.asList(getResources().getStringArray(R.array.rettungsbrett_array));
        wundversorgungArray = Arrays.asList(getResources().getStringArray(R.array.wundversorgung_array));
        abgegebenesMaterialArray = Arrays.asList(getResources().getStringArray(R.array.abgegebenMaterial_array));
        seitenlageArray = Arrays.asList(getResources().getStringArray(R.array.lagerung_seitenlage));

        tbSeitenlage = getView().findViewById(R.id.tbSeitenlage);

        // Enables multiple choice for multi state toggle buttons
        mstbMaterial1.enableMultipleChoice(true);
        mstbMaterial2.enableMultipleChoice(true);
        mstbAbgegebenesMaterial.enableMultipleChoice(true);

        mstbMaterial2.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int position) {
                // Checks if position 0 is selected
                if (position == 0){
                    // Sets multi state button Halskragen visible
                    mstbHalskragen.setVisibility(View.VISIBLE);
                    mstbHalskragen.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
                        @Override
                        public void onValueChanged(int value) {
                            // Sets position 0 as selected and overwrites position 0 of materialArray2 with selected position of halskragenArray
                            materialArray2.set(position, "Halskragen " + halskragenArray.get(value));
                            // Resets element of materialArray2 and set position 0 as selected
                            mstbMaterial2.setElements(materialArray2, materialArray2.get(position));
                            // Sets multi state toggle button Halskragen gone
                            mstbHalskragen.setVisibility(View.GONE);
                        }
                    });
                }

                // Checks if position 1 is selected
                if (position == 1){
                    // Sets multi state button Rettungsbrett visible
                    mstbRettungsbrett.setVisibility(View.VISIBLE);
                    mstbRettungsbrett.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
                        @Override
                        public void onValueChanged(int value) {
                            // Sets position 1 as selected and overwrites position 1 of materialArray2 with selected position of rettungsbrettArray
                            materialArray2.set(position, "Rettungsbrett " + rettungsbrettArray.get(value));
                            // Resets element of materialArray2 and set position 1 as selected
                            mstbMaterial2.setElements(materialArray2, materialArray2.get(position));
                            // Sets multi state toggle button Halskragen gone
                            mstbRettungsbrett.setVisibility(View.GONE);
                        }
                    });
                }

                // Checks if position 2 is selected
                if (position == 2) {
                    // Sets multi state button Wundversorgung visible
                    mstbWundversorgung.setVisibility(View.VISIBLE);
                    mstbWundversorgung.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
                        @Override
                        public void onValueChanged(int value) {
                            // Sets position 2 as selected and overwrites position 2 of materialArray2 with selected position of wundversorgungArray
                            materialArray2.set(position, "Wundversorgung " + wundversorgungArray.get(value));
                            // Resets element of materialArray2 and set position 2 as selected
                            mstbMaterial2.setElements(materialArray2, materialArray2.get(position));
                            // Sets multi state toggle button Halskragen gone
                            mstbWundversorgung.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });

        tbSeitenlage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checks if toggleButton Seitenlage is checked
                if (tbSeitenlage.isChecked()){
                    // Sets multi state toggle button as visible
                    mstbSeitenlage.setVisibility(View.VISIBLE);
                } else {
                    // Sets multi state toggle button as gone
                    mstbSeitenlage.setVisibility(View.GONE);
                }
            }
        });

    }
}
