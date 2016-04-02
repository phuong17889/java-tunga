/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import model.InvoiceModel;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;

/**
 *
 * @author MyPC
 */
public class Helper {

    public static String baseUrl() {
        Preferences pref = readConfig("app");
        return pref.get("baseurl", null);
    }

    public static String socketUrl() {
        Preferences pref = readConfig("app");
        String url = pref.get("baseurl", null);
        return url.replace("http", "ws");
    }

    public static String currency(float number) {
        Locale locale = Locale.US;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        return numberFormat.format(number);
    }

    public static Preferences readConfig(String node) {
        Preferences pref = null;
        try {
            Ini ini = new Ini(new File(appPath() + "config/main.ini"));
            java.util.prefs.Preferences prefs = new IniPreferences(ini);
            pref = prefs.node(node);
        } catch (IOException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pref;
    }

    public static String appPath() {
        String reponsePath = "";
        try {
            String path = Helper.class.getResource("").getPath();
            String fullPath = URLDecoder.decode(path, "UTF-8");
            String pathArr[] = fullPath.split("/WEB-INF/classes");
            fullPath = pathArr[0];
            reponsePath = new File(fullPath).getPath() + File.separatorChar;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reponsePath;
    }

    public static String random() {
        Random randomGenerator = new Random();
        String token = "";
        for (int i = 0; i < 8; i++) {
            token += "" + randomGenerator.nextInt(10);
        }
        if (InvoiceModel.find("WHERE token = '" + token + "'") != null) {
            token = random();
        }
        return token;
    }

    public static String implode(String delimiter, String... data) {
        Predicate<String> IS_NOT_SPACES_ONLY = Pattern.compile("^\\s*$").asPredicate().negate();
        return Arrays.stream(data).filter(IS_NOT_SPACES_ONLY).collect(Collectors.joining(delimiter));
    }

    public static String ucFirst(String string) {
        String[] newString = string.split("");
        return string.replace(newString[0], newString[0].toUpperCase());
    }

    public static Date calcToTime(String fromTime) {
        Date dt = null;
        try {
            DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.US);
            dt = df.parse(fromTime);
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.HOUR, 6);
            dt = c.getTime();
            return dt;
        } catch (ParseException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
}
