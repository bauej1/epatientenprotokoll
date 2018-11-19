package com.epatientenprotokoll.epatientenprotokoll.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.epatientenprotokoll.epatientenprotokoll.R;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
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

    ToggleButton tGerman;
    ToggleButton tFrench;
    ToggleButton tMale;
    ToggleButton tFemale;
    MultiStateToggleButton mstbOperation;
    MultiStateToggleButton mstbAccident;
    List<String> operationArray;
    List<String> accidentArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_master_data, container, false);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        tGerman = getView().findViewById(R.id.tGerman);
        tFrench = getView().findViewById(R.id.tFrench);
        tMale = getView().findViewById(R.id.tMale);
        tFemale = getView().findViewById(R.id.tFemale);
        mstbOperation = getView().findViewById(R.id.mstb_multi_id);
        mstbAccident = getView().findViewById(R.id.mstb_multi_id2);
        operationArray = Arrays.asList(getResources().getStringArray(R.array.operation_array));
        accidentArray = Arrays.asList(getResources().getStringArray(R.array.accident_array));


        tGerman.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (tGerman.isChecked()) {
                    tFrench.setChecked(false);
                }
            }
        });

        tFrench.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (tFrench.isChecked()) {
                    tGerman.setChecked(false);
                }
            }
        });

        tMale.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (tMale.isChecked()) {
                    tFemale.setChecked(false);
                }
            }
        });

        tFemale.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (tFemale.isChecked()) {
                    tMale.setChecked(false);
                }
            }
        });

        mstbOperation.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int position) {
                if (position == 1) {
                    //final MultiStateToggleButton mstbAccident = new MultiStateToggleButton(getActivity());
                    mstbAccident.setVisibility(View.VISIBLE);

                    mstbAccident.setOnValueChangedListener(new org.honorato.multistatetogglebutton.ToggleButton.OnValueChangedListener() {
                        @Override
                        public void onValueChanged(int position) {
//                            if (position == 0) {
//                                operationArray.set(1, accidentArray.get(0));
//                                mstbOperation.setElements(operationArray);
//                            } else if (position == 1) {
//                                operationArray.set(1, accidentArray.get(1));
//                                mstbOperation.setElements(operationArray);
//                            } else if (position == 2) {
//                                operationArray.set(1, accidentArray.get(2));
//                                mstbOperation.setElements(operationArray);
//                            }

                            operationArray.set(1, accidentArray.get(position));
                            mstbOperation.setElements(operationArray);
                            mstbAccident.setVisibility(View.INVISIBLE);
                        }
                    });
                }

                if (position == 6){
                    mstbAccident.setVisibility(View.INVISIBLE);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    final MultiStateToggleButton mstbAndere = new MultiStateToggleButton(getActivity());
                    final EditText input = new EditText(getActivity());
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(input);

                    builder.setPositiveButton(R.string.speichern, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (input.getText().toString().trim().length() > 0) {
                                operationArray.set(6, input.getText().toString());
                                mstbOperation.setElements(operationArray);
                                Log.d(TAG, "operationArray" + operationArray);
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
