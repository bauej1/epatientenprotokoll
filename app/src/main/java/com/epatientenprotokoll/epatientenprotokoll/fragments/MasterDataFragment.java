package com.epatientenprotokoll.epatientenprotokoll.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
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
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

    EditText etName;
    EditText etLastname;
    EditText etAnamnesis;
    LazyDatePicker birthday;
    String patientName;
    String patientLastname;
    String patientBirthdate;
    List<String> operationArray;
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
        etAnamnesis = getView().findViewById(R.id.etAnamnese);
        birthday = getView().findViewById(R.id.lazyDatePicker);
        operationArray = Arrays.asList(getResources().getStringArray(R.array.operation_array));
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

        gender.setMaxSelectCount(2);
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
                for(int i = 0; i < patientName.length(); ++i){
                    String c = patientName.charAt(i) + "";
                    if(!c.matches("-?\\d+")){
                        // Calls setPatientName method from MainActivity to pass value to MainActivity
                        ((MainActivity) mainActivity).setPatientName(patientName, false);
                    } else {
                        ((MainActivity) mainActivity).setPatientName(patientName, true);
                    }
                }
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
                for(int i = 0; i < patientLastname.length(); ++i){
                    String c = patientLastname.charAt(i) + "";
                    if(!c.matches("-?\\d+")){
                        // Calls setPatientLastname method from MainActivity to pass value to MainActivity
                        ((MainActivity) mainActivity).setPatientLastname(patientLastname, false);
                    } else {
                        ((MainActivity) mainActivity).setPatientLastname(patientLastname, true);
                    }
                }
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
    }
}
