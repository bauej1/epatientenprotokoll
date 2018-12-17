package com.epatientenprotokoll.epatientenprotokoll.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
    List<String> materialArray1;
    List<String> materialArray2;
    List<String> halskragenArray;
    List<String> rettungsbrettArray;
    List<String> wundversorgungArray;
    List<String> abgegebenesMaterialArray;
    List<String> seitenlageArray;
    android.widget.ToggleButton tbSeitenlage;
    android.widget.ToggleButton tbLiegend;
    android.widget.ToggleButton tbObereExtr;
    android.widget.ToggleButton tbUntereExtr;
    android.widget.ToggleButton tbSitzend;
    android.widget.ToggleButton tbBeideExtr;

    MultiSelectToggleGroup tbgMaterialAbgegeben;
    MultiSelectToggleGroup tbgMaterial1;
    MultiSelectToggleGroup tbgHalskragen;
    MultiSelectToggleGroup tbgRettungsbrett;
    MultiSelectToggleGroup tbgWundversorgung;
    MultiSelectToggleGroup tbgSeitenlage;

    Drawable dLiegend;

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
        tbSeitenlage = getView().findViewById(R.id.tbSeitenlage);
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
        tbgSeitenlage = getView().findViewById(R.id.tbgSeitenlage);
        separator = getView().findViewById(R.id.separator);

        dLiegend = ContextCompat.getDrawable(getContext(), R.drawable.lagerung_liegend);
        tbLiegend = getView().findViewById(R.id.tbLiegend);
        tbObereExtr = getView().findViewById(R.id.tbObereExtr);
        tbUntereExtr = getView().findViewById(R.id.tbUntereExtr);
        tbSitzend = getView().findViewById(R.id.tbSitzend);
        tbBeideExtr = getView().findViewById(R.id.tbBeideExtr);
        tbSeitenlage = getView().findViewById(R.id.tbSeitenlage);


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

        tbgHalskragen.setMaxSelectCount(1);

        for (String text : rettungsbrettArray){
            LabelToggle toggle = new LabelToggle(getActivity());
            toggle.setText(text);
            toggle.setId(countRettungsbrett);
            int color = Color.rgb(172, 172, 172);
            toggle.setMarkerColor(color);
            tbgRettungsbrett.addView(toggle);
            ++countRettungsbrett;
        }

        tbgRettungsbrett.setMaxSelectCount(1);

        for (String text : wundversorgungArray){
            LabelToggle toggle = new LabelToggle(getActivity());
            toggle.setText(text);
            toggle.setId(countWundversorgung);
            int color = Color.rgb(172, 172, 172);
            toggle.setMarkerColor(color);
            tbgWundversorgung.addView(toggle);
            ++countWundversorgung;
        }

        tbgWundversorgung.setMaxSelectCount(1);

        tbLiegend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbLiegend.setBackgroundResource(R.color.darkGrey);
                tbObereExtr.setBackgroundResource(R.color.lightGrey);
                tbUntereExtr.setBackgroundResource(R.color.lightGrey);
                tbSitzend.setBackgroundResource(R.color.lightGrey);
                tbBeideExtr.setBackgroundResource(R.color.lightGrey);
                tbSeitenlage.setBackgroundResource(R.color.lightGrey);

                tbgSeitenlage.setVisibility(View.GONE);
            }
        });

        tbObereExtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbLiegend.setBackgroundResource(R.color.lightGrey);
                tbObereExtr.setBackgroundResource(R.color.darkGrey);
                tbUntereExtr.setBackgroundResource(R.color.lightGrey);
                tbSitzend.setBackgroundResource(R.color.lightGrey);
                tbBeideExtr.setBackgroundResource(R.color.lightGrey);
                tbSeitenlage.setBackgroundResource(R.color.lightGrey);

                tbgSeitenlage.setVisibility(View.GONE);
            }
        });

        tbUntereExtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbLiegend.setBackgroundResource(R.color.lightGrey);
                tbObereExtr.setBackgroundResource(R.color.lightGrey);
                tbUntereExtr.setBackgroundResource(R.color.darkGrey);
                tbSitzend.setBackgroundResource(R.color.lightGrey);
                tbBeideExtr.setBackgroundResource(R.color.lightGrey);
                tbSeitenlage.setBackgroundResource(R.color.lightGrey);

                tbgSeitenlage.setVisibility(View.GONE);
            }
        });

        tbSitzend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbLiegend.setBackgroundResource(R.color.lightGrey);
                tbObereExtr.setBackgroundResource(R.color.lightGrey);
                tbUntereExtr.setBackgroundResource(R.color.lightGrey);
                tbSitzend.setBackgroundResource(R.color.darkGrey);
                tbBeideExtr.setBackgroundResource(R.color.lightGrey);
                tbSeitenlage.setBackgroundResource(R.color.lightGrey);

                tbgSeitenlage.setVisibility(View.GONE);
            }
        });

        tbBeideExtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbLiegend.setBackgroundResource(R.color.lightGrey);
                tbObereExtr.setBackgroundResource(R.color.lightGrey);
                tbUntereExtr.setBackgroundResource(R.color.lightGrey);
                tbSitzend.setBackgroundResource(R.color.lightGrey);
                tbBeideExtr.setBackgroundResource(R.color.darkGrey);
                tbSeitenlage.setBackgroundResource(R.color.lightGrey);

                tbgSeitenlage.setVisibility(View.GONE);
            }
        });

        tbSeitenlage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbLiegend.setBackgroundResource(R.color.lightGrey);
                tbObereExtr.setBackgroundResource(R.color.lightGrey);
                tbUntereExtr.setBackgroundResource(R.color.lightGrey);
                tbSitzend.setBackgroundResource(R.color.lightGrey);
                tbBeideExtr.setBackgroundResource(R.color.lightGrey);
                tbSeitenlage.setBackgroundResource(R.color.darkGrey);

                tbgSeitenlage.setVisibility(View.VISIBLE);
                tbgSeitenlage.setMaxSelectCount(1);
                for (String text : seitenlageArray) {
                    LabelToggle toggle = new LabelToggle(getActivity());
                    toggle.setText(text);

                    int color = Color.rgb(108, 108, 108);
                    toggle.setMarkerColor(color);
                    tbgSeitenlage.addView(toggle);
                }
            }
        });

    }
}
