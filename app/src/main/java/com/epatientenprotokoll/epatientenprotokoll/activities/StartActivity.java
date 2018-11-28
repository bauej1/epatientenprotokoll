package com.epatientenprotokoll.epatientenprotokoll.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextClock;
import android.widget.TextView;

import com.epatientenprotokoll.epatientenprotokoll.R;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    TextView weekday;
    TextView date;
    TextClock actualTime;
    MultiStateToggleButton languageToggleButton;
    List<String> languageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        weekday = findViewById(R.id.tvWeekday);
        date = findViewById(R.id.tvDate);
        actualTime = findViewById(R.id.textClockStart);
        languageToggleButton = findViewById(R.id.mstb_lang);
        languageArray = Arrays.asList(getResources().getStringArray(R.array.lang_array));

        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int month = (cal.get(Calendar.MONTH)) + 1;
        int year = cal.get(Calendar.YEAR);
        String dayOfMonthString = String.valueOf(dayOfMonth);
        String monthString = String.valueOf(month);
        String yearString = String.valueOf(year);
        date.setText(dayOfMonthString + "." + monthString + "." + yearString);

        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        String dayOfWeek = dayFormat.format(cal.getTime());
        weekday.setText(dayOfWeek + ", ");

        languageToggleButton.setElements(languageArray, languageArray.get(1));
    }
}
