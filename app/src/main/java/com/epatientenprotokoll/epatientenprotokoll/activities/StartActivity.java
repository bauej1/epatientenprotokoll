package com.epatientenprotokoll.epatientenprotokoll.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.epatientenprotokoll.epatientenprotokoll.components.LanguageHelper;
import com.epatientenprotokoll.epatientenprotokoll.R;
import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class StartActivity extends AppCompatActivity {

   private TextView weekday;
   private TextView date;
   private ImageButton startProtocolButton;
   private MultiStateToggleButton languageToggleButton;
   private List<String> languageArray;
   private SimpleDateFormat dayFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // prepare the language and the date format depending on the locale
        if(LanguageHelper.isLocaleSet()){
            if(LanguageHelper.getCurrentLocale().equals(new Locale("fr"))){
                dayFormat = new SimpleDateFormat("EEEE", Locale.FRENCH);
            } else {
                dayFormat = new SimpleDateFormat("EEEE", Locale.GERMAN);
            }
        } else {
            LanguageHelper.changeLocale(getResources(), "de");
            dayFormat = new SimpleDateFormat("EEEE", Locale.GERMAN);
        }

        // Assigns all variables to view
        weekday = findViewById(R.id.tvWeekday);
        date = findViewById(R.id.tvDate);
        startProtocolButton = findViewById(R.id.startProtocolButton);
        languageToggleButton = findViewById(R.id.mstb_lang);
        languageArray = Arrays.asList(getResources().getStringArray(R.array.lang_array));

        // Sets the current date
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int month = (cal.get(Calendar.MONTH)) + 1;
        int year = cal.get(Calendar.YEAR);
        String dayOfMonthString = String.valueOf(dayOfMonth);
        String monthString = String.valueOf(month);
        String yearString = String.valueOf(year);
        date.setText(dayOfMonthString + "." + monthString + "." + yearString);

        // Sets the current weekday
        String dayOfWeek = dayFormat.format(cal.getTime());
        weekday.setText(dayOfWeek + ", ");

        // Sets the languages of the toggleButton and selects german as default
        languageToggleButton.setElements(languageArray, languageArray.get(1));

        languageToggleButton.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                // Changes weekday to french, if language french is selected
                if (value == 0){
                    LanguageHelper.changeLocale(getResources(), "fr");
                    recreate();
                }
                // Changes weekday to german, if language german is selected
                if (value == 1) {
                    LanguageHelper.changeLocale(getResources(), "de");
                    recreate();
                }
            }
        });

        // Opens the MainActivity if startButton is clicked
        startProtocolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
