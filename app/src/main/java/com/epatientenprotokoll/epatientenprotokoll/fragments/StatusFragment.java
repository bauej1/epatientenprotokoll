package com.epatientenprotokoll.epatientenprotokoll.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.epatientenprotokoll.epatientenprotokoll.R;
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

    }
}
