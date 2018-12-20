package com.epatientenprotokoll.epatientenprotokoll.components;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageHelper {
    private static Configuration config = null;

    public static void changeLocale(Resources res, String locale){
        config = new Configuration(res.getConfiguration());

        if (locale.equals("fr")){
            config.locale = new Locale("fr");
        } else {
            config.locale = new Locale("de");
        }

        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    public static Locale getCurrentLocale(){
        return config.locale;
    }

    public static boolean isLocaleSet(){
        if(config == null)
            return false;

        if(config.locale != null){
            return true;
        } else {
            return false;
        }
    }
}
