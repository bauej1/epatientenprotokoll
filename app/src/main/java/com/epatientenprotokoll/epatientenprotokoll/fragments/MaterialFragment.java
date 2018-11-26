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
    MultiStateToggleButton mstbLagerung;
    MultiStateToggleButton mstbLiegend;
    MultiStateToggleButton mstbSeitenlage;
    List<String> materialArray1;
    List<String> materialArray2;
    List<String> halskragenArray;
    List<String> rettungsbrettArray;
    List<String> wundversorgungArray;
    List<String> abgegebenesMaterialArray;
    List<String> lagerungArray;
    List<String> lagerungFinalArray;
    List<String> liegendArray;
    List<String> seitenlageArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_material, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mstbMaterial1 = getView().findViewById(R.id.mstb_material1);
        mstbMaterial2 = getView().findViewById(R.id.mstb_material2);
        mstbHalskragen = getView().findViewById(R.id.mstb_halskragen);
        mstbRettungsbrett = getView().findViewById(R.id.mstb_rettungsbrett);
        mstbWundversorgung = getView().findViewById(R.id.mstb_wundversorgung);
        mstbAbgegebenesMaterial = getView().findViewById(R.id.mstb_abgegebenMaterial);
        mstbLagerung = getView().findViewById(R.id.mstb_lagerung);
        mstbLiegend = getView().findViewById(R.id.mstb_liegend);
        mstbSeitenlage = getView().findViewById(R.id.mstb_seitenlage);
        materialArray1 = Arrays.asList(getResources().getStringArray(R.array.material_array1));
        materialArray2 = Arrays.asList(getResources().getStringArray(R.array.material_array2));
        halskragenArray = Arrays.asList(getResources().getStringArray(R.array.halskragen_array));
        rettungsbrettArray = Arrays.asList(getResources().getStringArray(R.array.rettungsbrett_array));
        wundversorgungArray = Arrays.asList(getResources().getStringArray(R.array.wundversorgung_array));
        abgegebenesMaterialArray = Arrays.asList(getResources().getStringArray(R.array.abgegebenMaterial_array));
        lagerungArray = Arrays.asList(getResources().getStringArray(R.array.lagerung_array));
        lagerungFinalArray = Arrays.asList(getResources().getStringArray(R.array.lagerung_array));
        liegendArray = Arrays.asList(getResources().getStringArray(R.array.lagerung_liegend));
        seitenlageArray = Arrays.asList(getResources().getStringArray(R.array.lagerung_seitenlage));

        mstbMaterial1.enableMultipleChoice(true);
        mstbMaterial2.enableMultipleChoice(true);
        mstbAbgegebenesMaterial.enableMultipleChoice(true);

        mstbMaterial2.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int position) {
                if (position == 0){
                    mstbHalskragen.setVisibility(View.VISIBLE);
                    mstbHalskragen.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
                        @Override
                        public void onValueChanged(int value) {
                            materialArray2.set(position, "Halskragen " + halskragenArray.get(value));
                            mstbMaterial2.setElements(materialArray2, materialArray2.get(position));
                            mstbHalskragen.setVisibility(View.GONE);
                        }
                    });
                }

                if (position == 1){
                    mstbRettungsbrett.setVisibility(View.VISIBLE);
                    mstbRettungsbrett.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
                        @Override
                        public void onValueChanged(int value) {
                            materialArray2.set(position, "Rettungsbrett " + rettungsbrettArray.get(value));
                            mstbMaterial2.setElements(materialArray2, materialArray2.get(position));
                            mstbRettungsbrett.setVisibility(View.GONE);
                        }
                    });
                }

                if (position == 2) {
                    mstbWundversorgung.setVisibility(View.VISIBLE);
                    mstbWundversorgung.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
                        @Override
                        public void onValueChanged(int value) {
                            materialArray2.set(position, "Wundversorgung " + wundversorgungArray.get(value));
                            mstbMaterial2.setElements(materialArray2, materialArray2.get(position));
                            mstbWundversorgung.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });

        mstbLagerung.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int position) {
                if (position == 0){
                    mstbLiegend.setVisibility(View.VISIBLE);
                    mstbLiegend.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
                        @Override
                        public void onValueChanged(int value) {
                            lagerungArray.set(1, lagerungFinalArray.get(1));
                            lagerungArray.set(position, "Liegend \n" + liegendArray.get(value));
                            mstbLagerung.setElements(lagerungArray, lagerungArray.get(position));
                            mstbLiegend.setVisibility(View.GONE);
                        }
                    });
                }

                if (position == 1){
                    mstbSeitenlage.setVisibility(View.VISIBLE);
                    mstbSeitenlage.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
                        @Override
                        public void onValueChanged(int value) {
                            lagerungArray.set(0, lagerungFinalArray.get(0));
                            lagerungArray.set(position, "Seitenlage \n" + seitenlageArray.get(value));
                            mstbLagerung.setElements(lagerungArray, lagerungArray.get(position));
                            mstbSeitenlage.setVisibility(View.GONE);
                        }
                    });
                }
            }

        });


    }
}
