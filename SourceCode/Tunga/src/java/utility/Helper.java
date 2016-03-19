/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.prefs.Preferences;

/**
 *
 * @author MyPC
 */
public class Helper {

    public static String baseUrl() {
        DataProcess dt = new DataProcess();
        Preferences pref = dt.readConfig("app");
        return pref.get("baseurl", null);
    }

    public static String currency(float number) {
        Locale locale = Locale.US;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        return numberFormat.format(number);
    }
}
