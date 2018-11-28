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
    Activity mainActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_master_data, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mstbOperation = getView().findViewById(R.id.mstb_multi_id);
        mstbAccident = getView().findViewById(R.id.mstb_multi_id2);
        etName = getView().findViewById(R.id.etName);
        etLastname = getView().findViewById(R.id.etLastname);
        birthday = getView().findViewById(R.id.lazyDatePicker);
        operationArray = Arrays.asList(getResources().getStringArray(R.array.operation_array));
        accidentArray = Arrays.asList(getResources().getStringArray(R.array.accident_array));
        mainActivity = (MainActivity) getActivity();

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
                patientName = etName.getText().toString();
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
                patientLastname = etLastname.getText().toString();
                ((MainActivity) mainActivity).setPatientLastname(patientLastname);
                Log.d(TAG, "PatientLastname: " + patientLastname);
            }
        });

        birthday.setOnDatePickListener(new LazyDatePicker.OnDatePickListener() {
            @Override
            public void onDatePick(Date dateSelected) {
                //patientBirthdate = birthday.getDate().toString();
                patientBirthdate = birthday.dateToString(dateSelected, DATE_FORMAT);
                ((MainActivity) mainActivity).setPatientBirthdate(patientBirthdate);
                Log.d(TAG, "PatientBirthdate: " + patientBirthdate);
            }
        });

        mstbOperation.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int position) {
                if (position == 1) {
                    mstbAccident.setVisibility(View.VISIBLE);

                    mstbAccident.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
                        @Override
                        public void onValueChanged(int position) {
                            operationArray.set(1, accidentArray.get(position));
                            mstbOperation.setElements(operationArray, operationArray.get(1));
                            mstbAccident.setVisibility(View.INVISIBLE);
                        }
                    });
                }

                if (position == 6){
                    mstbAccident.setVisibility(View.INVISIBLE);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    final EditText input = new EditText(getActivity());
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(input);


                    builder.setPositiveButton(R.string.speichern, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (input.getText().toString().trim().length() > 0) {
                                operationArray.set(6, input.getText().toString());
                                mstbOperation.setElements(operationArray, operationArray.get(6));
                            } else {
                                //checkBox.toggle();
                                return;
                            }
                        }
                    })
                            .setNegativeButton(R.string.abbrechen, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    return;
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                    Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                    negativeButton.setTextColor(Color.parseColor("#838182"));
                }

                Log.d(TAG, "Position: " + position);
            }
        });

    }
}
