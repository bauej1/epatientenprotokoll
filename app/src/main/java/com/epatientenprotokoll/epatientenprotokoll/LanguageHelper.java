package com.epatientenprotokoll.epatientenprotokoll;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageHelper {

    public static void changeLocale(Resources res, String locale){
        Configuration config;
        config = new Configuration(res.getConfiguration());

        if (locale.equals("fr")){
            config.locale = new Locale("fr");
        } else {
            config.locale = new Locale("de");
        }

        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
