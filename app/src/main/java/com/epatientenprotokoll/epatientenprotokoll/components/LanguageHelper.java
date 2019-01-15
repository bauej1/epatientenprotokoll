package com.epatientenprotokoll.epatientenprotokoll.components;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * This Class helps to change the Local for changing the app language.
 */
public class LanguageHelper {
    private static Configuration config = null;

    /**
     * Changes the language of the application.
     * @param res
     * @param locale
     */
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

    /**
     * Checks if a Locale is set.
     * @return - boolean
     */
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
