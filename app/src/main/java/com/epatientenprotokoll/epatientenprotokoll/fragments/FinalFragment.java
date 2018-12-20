package com.epatientenprotokoll.epatientenprotokoll.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epatientenprotokoll.epatientenprotokoll.R;
import com.nex3z.togglebuttongroup.MultiSelectToggleGroup;
import com.nex3z.togglebuttongroup.button.LabelToggle;

import java.util.Arrays;
import java.util.List;

public class FinalFragment extends Fragment {

    MultiSelectToggleGroup tbgUrgency;
    MultiSelectToggleGroup tbgPresent;
    List<String> urgencyArray;
    List<String> presentArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_final, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        tbgUrgency = getActivity().findViewById(R.id.tbgDringlichkeit);
        tbgPresent = getActivity().findViewById(R.id.tbgAnwesend);
        urgencyArray = Arrays.asList(getResources().getStringArray(R.array.urgency_array));
        presentArray = Arrays.asList(getResources().getStringArray(R.array.present_array));

        for (String text : urgencyArray){
            LabelToggle toggle = new LabelToggle(getActivity());
            toggle.setText(text);

            int color = Color.rgb(108, 108, 108);
            toggle.setMarkerColor(color);
            tbgUrgency.addView(toggle);
        }

        for (String text : presentArray) {
            LabelToggle toggle = new LabelToggle(getActivity());
            toggle.setText(text);

            int color = Color.rgb(108, 108, 108);
            toggle.setMarkerColor(color);
            tbgPresent.addView(toggle);
        }

    }
}
