package com.epatientenprotokoll.epatientenprotokoll.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.epatientenprotokoll.epatientenprotokoll.R;
import com.nex3z.togglebuttongroup.MultiSelectToggleGroup;
import com.nex3z.togglebuttongroup.button.LabelToggle;
import com.epatientenprotokoll.epatientenprotokoll.activities.MainActivity;

/**
 * This class contains all controls to collect the data about the actual status and anamnesis of the patient.
 * It handles also all user interaction with the controls.
 *
 * @author
 *
 */
public class StatusFragment extends Fragment{

    ImageButton suspected_information;
    ImageButton naca_information;
    EditText etSuspected;
    EditText etNaca;
    EditText etAnamnesis;

    MultiSelectToggleGroup gcs1;
    MultiSelectToggleGroup gcs2;
    MultiSelectToggleGroup gcs3;


    ToggleButton eyes1;
    ToggleButton eyes2;
    ToggleButton eyes3;
    ToggleButton eyes4;

    RadioGroup toggleGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_status, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Assigns all variables to views
        suspected_information = getView().findViewById(R.id.suspected_information);
        naca_information = getView().findViewById(R.id.naca_information);
        etSuspected = getView().findViewById(R.id.etSuspected);
        etNaca = getView().findViewById(R.id.etNaca);
        etAnamnesis = getView().findViewById(R.id.etAnamnese);
        gcs1 = getView().findViewById(R.id.tbgAugen);
        gcs2 = getView().findViewById(R.id.tbgAntwort);
        gcs3 = getView().findViewById(R.id.tbgMotorisch);
        eyes1 = getView().findViewById(R.id.tbEyes1);
        eyes2 = getView().findViewById(R.id.tbEyes2);
        eyes3 = getView().findViewById(R.id.tbEyes3);
        eyes4 = getView().findViewById(R.id.tbEyes4);


        eyes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eyes1.isChecked()){
                    eyes1.setBackgroundResource(R.color.black);
                    eyes1.setTextColor(getResources().getColor(R.color.white));
                } else {
                    eyes1.setBackgroundResource(R.color.grey);
                    eyes1.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });




        toggleGroup = getView().findViewById(R.id.toggleGroup);

        toggleGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int j = 0; j < group.getChildCount(); j++) {
                    final ToggleButton view = (ToggleButton) group.getChildAt(j);
                    view.setChecked(view.getId() == checkedId);
                }
            }
        });


        suspected_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creates popup menu
                PopupMenu popup = new PopupMenu(getActivity(), suspected_information);
                // Inflates popup menu
                popup.getMenuInflater().inflate(R.menu.suspected_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        // Sets selected item to EditText
                        etSuspected.setText(item.getTitle());
                        return true;
                    }
                });

                // Shows popup
                popup.show();
            }
        });

        naca_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creates popup menu
                PopupMenu popup = new PopupMenu(getActivity(), naca_information);
                // Inflates popup menu
                popup.getMenuInflater().inflate(R.menu.naca_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        // Sets selected item to EditText
                        etNaca.setText(item.getTitle());
                        return true;
                    }
                });

                // Shows popup
                popup.show();
            }
        });

        String[] eyesArray = getResources().getStringArray(R.array.eyes_array);
        for (String text : eyesArray) {
            LabelToggle toggle = new LabelToggle(getActivity());
            toggle.setText(text);

            int color = Color.rgb(108, 108, 108);
            toggle.setMarkerColor(color);
            gcs1.addView(toggle);
        }

        String[] respArray = getResources().getStringArray(R.array.response_array);
        for (String text : respArray) {
            LabelToggle toggle = new LabelToggle(getActivity());
            toggle.setText(text);

            int color = Color.rgb(108, 108, 108);
            toggle.setMarkerColor(color);
            gcs2.addView(toggle);
        }

        String[] reacArray = getResources().getStringArray(R.array.reaction_array);
        for (String text : reacArray) {
            LabelToggle toggle = new LabelToggle(getActivity());
            toggle.setText(text);

            int color = Color.rgb(108, 108, 108);
            toggle.setMarkerColor(color);
            gcs3.addView(toggle);
        }

        etAnamnesis.setImeOptions(EditorInfo.IME_ACTION_DONE);
        etAnamnesis.setRawInputType(InputType.TYPE_CLASS_TEXT);
    }
}
