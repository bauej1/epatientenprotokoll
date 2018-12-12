package com.epatientenprotokoll.epatientenprotokoll.fragments;

import android.app.AlertDialog;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.epatientenprotokoll.epatientenprotokoll.R;
import com.epatientenprotokoll.epatientenprotokoll.activities.MainActivity;
import com.nex3z.togglebuttongroup.MultiSelectToggleGroup;
import com.nex3z.togglebuttongroup.button.LabelToggle;

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

    MultiSelectToggleGroup tbgMaterialAbgegeben;
    MultiSelectToggleGroup tbgMaterial1;
    MultiSelectToggleGroup tbgHalskragen;
    MultiSelectToggleGroup tbgRettungsbrett;
    MultiSelectToggleGroup tbgWundversorgung;

    View separator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_material, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Assigns all values to view
//        mstbMaterial1 = getView().findViewById(R.id.mstb_material1);
//        mstbMaterial2 = getView().findViewById(R.id.mstb_material2);
//        mstbHalskragen = getView().findViewById(R.id.mstb_halskragen);
//        mstbRettungsbrett = getView().findViewById(R.id.mstb_rettungsbrett);
//        mstbWundversorgung = getView().findViewById(R.id.mstb_wundversorgung);
        mstbAbgegebenesMaterial = getView().findViewById(R.id.mstb_abgegebenMaterial);
        mstbSeitenlage = getView().findViewById(R.id.mstb_Seitenlage);
        materialArray1 = Arrays.asList(getResources().getStringArray(R.array.material_array1));
        materialArray2 = Arrays.asList(getResources().getStringArray(R.array.material_array2));
        halskragenArray = Arrays.asList(getResources().getStringArray(R.array.halskragen_array));
        rettungsbrettArray = Arrays.asList(getResources().getStringArray(R.array.rettungsbrett_array));
        wundversorgungArray = Arrays.asList(getResources().getStringArray(R.array.wundversorgung_array));
        abgegebenesMaterialArray = Arrays.asList(getResources().getStringArray(R.array.abgegebenMaterial_array));
        seitenlageArray = Arrays.asList(getResources().getStringArray(R.array.lagerung_seitenlage));
        tbgMaterialAbgegeben = getView().findViewById(R.id.tbgMaterial);
        tbgMaterial1 = getView().findViewById(R.id.tbgMaterial1);
        tbgHalskragen = getView().findViewById(R.id.tbgHalskragen);
        tbgRettungsbrett = getView().findViewById(R.id.tbgRettungsbrett);
        tbgWundversorgung = getView().findViewById(R.id.tbgWundversorgung);
        separator = getView().findViewById(R.id.separator);

        String[] materialAbgegebenArray = getResources().getStringArray(R.array.abgegebenMaterial_array);
        int countAbgegeben = 0;
        int countMaterial = 0;
        int countHalskragen = 0;
        int countRettungsbrett = 0;
        int countWundversorgung = 0;

        for (String text : materialAbgegebenArray) {
            LabelToggle toggle = new LabelToggle(getActivity());
            toggle.setId(countAbgegeben);
            toggle.setText(text);

            int color = Color.rgb(108, 108, 108);
            toggle.setMarkerColor(color);
            tbgMaterialAbgegeben.addView(toggle);
            ++countAbgegeben;
        }

        tbgMaterialAbgegeben.setOnCheckedChangeListener(new MultiSelectToggleGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedStateChanged(MultiSelectToggleGroup group, int checkedId, boolean isChecked) {
                Log.d("IDabgegeben", "IDabgegeben: " + checkedId);
            }
        });

        for (String text : materialArray1) {
            LabelToggle toggle = new LabelToggle(getActivity());
            toggle.setText(text);
            toggle.setId(countMaterial);

            int color = Color.rgb(108, 108, 108);
            toggle.setMarkerColor(color);
            tbgMaterial1.addView(toggle);
            ++countMaterial;
        }

        for (String text : halskragenArray){
            LabelToggle toggle = new LabelToggle(getActivity());
            toggle.setText(text);
            toggle.setId(countHalskragen);
            int color = Color.rgb(172, 172, 172);
            toggle.setMarkerColor(color);
            tbgHalskragen.addView(toggle);
            ++countHalskragen;
        }

        for (String text : rettungsbrettArray){
            LabelToggle toggle = new LabelToggle(getActivity());
            toggle.setText(text);
            toggle.setId(countRettungsbrett);
            int color = Color.rgb(172, 172, 172);
            toggle.setMarkerColor(color);
            tbgRettungsbrett.addView(toggle);
            ++countRettungsbrett;
        }

        for (String text : wundversorgungArray){
            LabelToggle toggle = new LabelToggle(getActivity());
            toggle.setText(text);
            toggle.setId(countWundversorgung);
            int color = Color.rgb(172, 172, 172);
            toggle.setMarkerColor(color);
            tbgWundversorgung.addView(toggle);
            ++countWundversorgung;
        }

        tbgMaterial1.setOnCheckedChangeListener(new MultiSelectToggleGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedStateChanged(MultiSelectToggleGroup group, int position, boolean isChecked) {
                Log.d("IDabcd", "IDabcd: " + position);
                Log.d("IDabcd", "IDabcd:" + group.getCheckedIds());

                if (position == 8){
                    separator.setVisibility(View.VISIBLE);
                    tbgHalskragen.setVisibility(View.VISIBLE);

                    tbgHalskragen.setOnCheckedChangeListener(new MultiSelectToggleGroup.OnCheckedStateChangeListener() {
                        @Override
                        public void onCheckedStateChanged(MultiSelectToggleGroup group, int checkedId, boolean isChecked) {
                            materialArray1.set(position, "Halskragen " + halskragenArray.get(checkedId));
                            Log.d("halskragen", "Halskragen" + halskragenArray.get(checkedId));
                            int countHalskragenNew = 0;


                            tbgMaterial1.removeAllViews();
                            separator.setVisibility(View.GONE);
                            tbgHalskragen.setVisibility(View.GONE);

                            for (String text : materialArray1) {
                                LabelToggle toggle = new LabelToggle(getActivity());
                                toggle.setText(text);
                                toggle.setId(countHalskragenNew);
                                tbgMaterial1.addView(toggle);
                                ++countHalskragenNew;
                            }

                            // tbgMaterial1.check(8);
                            tbgMaterial1.check(8);
                            return;

                        }
                    });
                }

                if (position == 9) {
                    separator.setVisibility(View.VISIBLE);
                    tbgRettungsbrett.setVisibility(View.VISIBLE);
                    tbgRettungsbrett.setOnCheckedChangeListener(new MultiSelectToggleGroup.OnCheckedStateChangeListener() {
                        @Override
                        public void onCheckedStateChanged(MultiSelectToggleGroup group, int checkedId, boolean isChecked) {
                            rettungsbrettArray.set(checkedId, "Rettungsbrett" + rettungsbrettArray.get(checkedId));
                            separator.setVisibility(View.GONE);
                            tbgRettungsbrett.setVisibility(View.GONE);
                        }
                    });
                }

                if (position == 10) {
                    separator.setVisibility(View.VISIBLE);
                    tbgWundversorgung.setVisibility(View.VISIBLE);
                    tbgWundversorgung.setOnCheckedChangeListener(new MultiSelectToggleGroup.OnCheckedStateChangeListener() {
                        @Override
                        public void onCheckedStateChanged(MultiSelectToggleGroup group, int checkedId, boolean isChecked) {
                            wundversorgungArray.set(checkedId, "Wundversorgung" + wundversorgungArray.get(checkedId));
                            separator.setVisibility(View.GONE);
                            tbgWundversorgung.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });

        tbSeitenlage = getView().findViewById(R.id.tbSeitenlage);

        // Enables multiple choice for multi state toggle buttons
//        mstbMaterial1.enableMultipleChoice(true);
//        mstbMaterial2.enableMultipleChoice(true);
//        mstbAbgegebenesMaterial.enableMultipleChoice(true);
//
//        mstbMaterial2.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
//            @Override
//            public void onValueChanged(int position) {
//                // Checks if position 0 is selected
//                if (position == 0){
//                    // Sets multi state button Halskragen visible
//                    mstbHalskragen.setVisibility(View.VISIBLE);
//                    mstbHalskragen.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
//                        @Override
//                        public void onValueChanged(int value) {
//                            // Sets position 0 as selected and overwrites position 0 of materialArray2 with selected position of halskragenArray
//                            materialArray2.set(position, "Halskragen " + halskragenArray.get(value));
//                            // Resets element of materialArray2 and set position 0 as selected
//                            mstbMaterial2.setElements(materialArray2, materialArray2.get(position));
//                            // Sets multi state toggle button Halskragen gone
//                            mstbHalskragen.setVisibility(View.GONE);
//                        }
//                    });
//                }
//
//                // Checks if position 1 is selected
//                if (position == 1){
//                    // Sets multi state button Rettungsbrett visible
//                    mstbRettungsbrett.setVisibility(View.VISIBLE);
//                    mstbRettungsbrett.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
//                        @Override
//                        public void onValueChanged(int value) {
//                            // Sets position 1 as selected and overwrites position 1 of materialArray2 with selected position of rettungsbrettArray
//                            materialArray2.set(position, "Rettungsbrett " + rettungsbrettArray.get(value));
//                            // Resets element of materialArray2 and set position 1 as selected
//                            mstbMaterial2.setElements(materialArray2, materialArray2.get(position));
//                            // Sets multi state toggle button Halskragen gone
//                            mstbRettungsbrett.setVisibility(View.GONE);
//                        }
//                    });
//                }
//
//                // Checks if position 2 is selected
//                if (position == 2) {
//                    // Sets multi state button Wundversorgung visible
//                    mstbWundversorgung.setVisibility(View.VISIBLE);
//                    mstbWundversorgung.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
//                        @Override
//                        public void onValueChanged(int value) {
//                            // Sets position 2 as selected and overwrites position 2 of materialArray2 with selected position of wundversorgungArray
//                            materialArray2.set(position, "Wundversorgung " + wundversorgungArray.get(value));
//                            // Resets element of materialArray2 and set position 2 as selected
//                            mstbMaterial2.setElements(materialArray2, materialArray2.get(position));
//                            // Sets multi state toggle button Halskragen gone
//                            mstbWundversorgung.setVisibility(View.GONE);
//                        }
//                    });
//                }
//            }
//        });
//
//        tbSeitenlage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Checks if toggleButton Seitenlage is checked
//                if (tbSeitenlage.isChecked()){
//                    // Sets multi state toggle button as visible
//                    mstbSeitenlage.setVisibility(View.VISIBLE);
//                } else {
//                    // Sets multi state toggle button as gone
//                    mstbSeitenlage.setVisibility(View.GONE);
//                }
//            }
//        });

    }
}
