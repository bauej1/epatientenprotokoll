package com.epatientenprotokoll.epatientenprotokoll.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.epatientenprotokoll.epatientenprotokoll.R;

/**
 * This class contains all controls to collect the data for used materials during the mission and how the patient is positionned.
 * It handles also all user interaction with the controls.
 *
 * @author
 *
 */
public class MaterialFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_material, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}
