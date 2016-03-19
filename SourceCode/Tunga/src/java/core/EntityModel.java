/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.prefs.Preferences;
import utility.DataProcess;
import utility.Helper;

/**
 *
 * @author notte
 */
public class EntityModel extends DataProcess{
    public static EntityModel em = new EntityModel();

    public static boolean login(String u, String p) {
        Preferences login = Helper.readConfig("login");
        return (u.equals(login.get("username", null)) && p.equals(login.get("password", null)));
    }
}
