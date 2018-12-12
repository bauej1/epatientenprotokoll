package com.epatientenprotokoll.epatientenprotokoll.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.epatientenprotokoll.epatientenprotokoll.R;
import com.epatientenprotokoll.epatientenprotokoll.activities.MainActivity;
import com.mikhaellopez.lazydatepicker.LazyDatePicker;
import com.nex3z.togglebuttongroup.MultiSelectToggleGroup;
import com.nex3z.togglebuttongroup.button.LabelToggle;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * This class contains all controls for the master data and administrative data of the patient and the mission.
 * It handles also all user interaction with the controls.
 *
 * @author
 *
 */
public class MasterDataFragment extends Fragment{
    // Sets the date format
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    MultiStateToggleButton mstbOperation;
    MultiStateToggleButton mstbAccident;
    EditText etName;
    EditText etLastname;
    LazyDatePicker birthday;
    String patientName;
    String patientLastname;
    String patientBirthdate;
    List<String> operationArray;
    List<String> accidentArray;
    List<String> genderArray;
    List<String> languageArray;
    Activity mainActivity;

    MultiSelectToggleGroup dummy;
    MultiSelectToggleGroup gender;
    MultiSelectToggleGroup language;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_master_data, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Assigns all values to view
        //mstbOperation = getView().findViewById(R.id.mstb_multi_id);
        //mstbAccident = getView().findViewById(R.id.mstb_multi_id2);
        etName = getView().findViewById(R.id.etName);
        etLastname = getView().findViewById(R.id.etLastname);
        birthday = getView().findViewById(R.id.lazyDatePicker);
        operationArray = Arrays.asList(getResources().getStringArray(R.array.operation_array));
        accidentArray = Arrays.asList(getResources().getStringArray(R.array.accident_array));
        genderArray = Arrays.asList(getResources().getStringArray(R.array.gender_array));
        languageArray = Arrays.asList(getResources().getStringArray(R.array.language_array));
        language = getView().findViewById(R.id.language);
        gender = getView().findViewById(R.id.gender);
        mainActivity = (MainActivity) getActivity();

        for (String text : languageArray){
            LabelToggle toggle = new LabelToggle(getActivity());
            toggle.setText(text);

            int color = Color.rgb(108, 108, 108);
            toggle.setMarkerColor(color);
            language.addView(toggle);
        }

        for (String text : genderArray){
            LabelToggle toggle = new LabelToggle(getActivity());
            toggle.setText(text);

            int color = Color.rgb(108, 108, 108);
            toggle.setMarkerColor(color);
            gender.addView(toggle);
        }

        dummy = getView().findViewById(R.id.group_dummy);
        dummy.setMaxSelectCount(1);

        String[] dummyText = getResources().getStringArray(R.array.operation_array);

        for (String text : dummyText) {
            LabelToggle toggle = new LabelToggle(getActivity());
            toggle.setText(text);

            int color = Color.rgb(108, 108, 108);
            toggle.setMarkerColor(color);
            dummy.addView(toggle);
        }

        // Sets the date format of birthday
        birthday.setDateFormat(LazyDatePicker.DateFormat.DD_MM_YYYY);

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Gets patient name to string
                patientName = etName.getText().toString();
                // Calls setPatientName method from MainActivity to pass value to MainActivity
                ((MainActivity) mainActivity).setPatientName(patientName);
                Log.d(TAG, "Patientname: " + patientName);
            }
        });

        etLastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Gets patient lastname to string
                patientLastname = etLastname.getText().toString();
                // Calls setPatientLastname from MainActivity to pass value to MainActivity
                ((MainActivity) mainActivity).setPatientLastname(patientLastname);
                Log.d(TAG, "PatientLastname: " + patientLastname);
            }
        });

        birthday.setOnDatePickListener(new LazyDatePicker.OnDatePickListener() {
            @Override
            public void onDatePick(Date dateSelected) {
                // Gets patient birthdate to string
                patientBirthdate = birthday.dateToString(dateSelected, DATE_FORMAT);
                // Calls setPatientBirthdate from MainActivity to pass value to MainActivity
                ((MainActivity) mainActivity).setPatientBirthdate(patientBirthdate);
                Log.d(TAG, "PatientBirthdate: " + patientBirthdate);
            }
        });

//        mstbOperation.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
//            @Override
//            public void onValueChanged(int position) {
//                // Checks if position 1 is selected
//                if (position == 1) {
//                    // Sets accident multi state toggle as visible
//                    mstbAccident.setVisibility(View.VISIBLE);
//
//                    mstbAccident.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
//                        @Override
//                        public void onValueChanged(int position) {
//                            // Sets position 1 as selected and overwrite position 1 of operationArray with selected position of accidentArray
//                            operationArray.set(1, accidentArray.get(position));
//                            // Resets element of operationArray and set position 1 as selected
//                            mstbOperation.setElements(operationArray, operationArray.get(1));
//                            // Sets accident multi state toggle as invisible
//                            mstbAccident.setVisibility(View.INVISIBLE);
//                        }
//                    });
//                }
//
//                // Checks if position 6 is selected
//                if (position == 6){
//                    // Sets accident multi state toggle to invisible
//                    mstbAccident.setVisibility(View.INVISIBLE);
//
//                    // Builds a new alert dialog and its components
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                    final EditText input = new EditText(getActivity());
//                    input.setInputType(InputType.TYPE_CLASS_TEXT);
//                    builder.setView(input);
//
//                    builder.setPositiveButton(R.string.speichern, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            // Checks if any input is made
//                            if (input.getText().toString().trim().length() > 0) {
//                                // Sets position 6 as selected and overwrite position 6 with inserted string
//                                operationArray.set(6, input.getText().toString());
//                                // Resets operation multi state toggle button with operation and set position 6 as selected
//                                mstbOperation.setElements(operationArray, operationArray.get(6));
//                            } else {
//                                return;
//                            }
//                        }
//                    })
//                            .setNegativeButton(R.string.abbrechen, new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    return;
//                                }
//                            });
//
//                    // Shows the dialogBox
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//
//                    // Sets styling of negative button
//                    Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
//                    negativeButton.setTextColor(Color.parseColor("#838182"));
//                }
//
//                Log.d(TAG, "Position: " + position);
//            }
//        });

    }
}
